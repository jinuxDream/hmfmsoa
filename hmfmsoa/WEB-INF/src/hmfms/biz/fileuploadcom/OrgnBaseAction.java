package hmfms.biz.fileuploadcom;

import fd.commons.web.filters.BizTraceFilter;
import hmfms.util.BeanUtil;
import hmfms.util.Constants;
import hmfms.util.Debug;
import hmfms.util.FileUtil;
import hmfms.util.ObjectUtil;
import hmfms.util.Property;
import hmfms.web.User;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 不使用框架内置转发机制，需被所有action继承的超类</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:20:13</p>
 * @author 产品开发部
 * @version 2.0
 * OrgnBaseAction
 */
public abstract class OrgnBaseAction extends Action {
	private static final Log logger = LogFactory.getLog(OrgnBaseAction.class);
	public static final boolean DEBUG = Constants.DEV_DEBUG;
	public static final boolean INFO_OUT = Constants.INFO_OUT;
	public static final boolean ERROR_OUT = Constants.ERROR_OUT;
	public static final String TIMEOUT_FORWARD = "timeout";
	public static final String SEND_TIMEOUT = "g_fd_sendmsg2bank_readbackmsg_timeout";
	public static final String TIMEOUT_FORWARD_PAGE = "/jsp/common/WorkArea.jsp";
	public static final String USER_SESSION = "hmfms.user.session";
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	public static final String ERROR = "error";
	public static final String ERROR_FROM_WHERE = "g_attr_error_from_where_uri";
	public static final String BLACKBALL = "blackball"; //互斥交易
	public static final String BACKPAGE = "backpage"; //互斥交易页的返回按钮需要的.do
	public static final String ALTERNATIVERESULTSET = "alternativeresultset"; //互斥交易结果集的参数名
	public static final String MESSAGE = "message.redo";
	public static final String ERROR_MESSAGE = "error.message";
	public static final String OMUTEX = "OMutex"; //开户互斥交易列表
	public static final String ReturnFlag = "flag"; //开户，含有在途控制交易的返回结果标识
	public static final String FLAG_TRUE = "TRUE"; //开户，含有在途控制交易的返回为真
	public static final String FLAG_FALSE = "FALSE"; //开户，含有在途控制交易的返回为假
	public static final String FLAG_OMUTEX = "OMutex"; //开户，含有在途控制交易的返回为存在互斥交易
	public static final String BACK_PAGE = "back_page"; //开户,互斥页返回的前一页

	/* （非 Javadoc）
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/*
		 response.setHeader("Cache-Control","no-cache"); 
		 //Forces caches to obtain a new copy of the page from the origin server
		 response.setHeader("Cache-Control","no-store");
		 //Directs caches not to store the page under any circumstance
		 response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
		 response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility 
		 */
		String uri = getURI(request);
		//String module=uri.substring(0,10);
		if( !uri.startsWith("/jsp/common") ) {
			if( uri.startsWith("/jsp/null") ) {
				uri = uri + "?tojsp=" + request.getParameter("tojsp");
			}
		}
		ActionForward forward = executeAction(mapping, form, request, response);
		//String strURI = request.getServletPath();
		String strForward = forward.getName();
		if( ERROR.equals(strForward) ) {
			request.setAttribute(ERROR_FROM_WHERE, mapping.getPath());
		}
		return forward;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected abstract ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
					throws Exception;

	/**
	 * @param request
	 * @return
	 */
	public User getUser(HttpServletRequest request) {

		User user = (User)request.getSession().getAttribute(USER_SESSION);
		if( user == null ) {
			throw new RuntimeException("系统异常：无法取得用户登录信息！");
		}
		else {
			return user;
		}
	}

	/**
	 * @param source
	 * @param dest
	 */
	protected void copyObject(Object source, Object dest) {

		BeanUtil.copyObject(source, dest);
	}

	/**
	 * @param mapping
	 * @param request
	 */
	protected void removeFormBean(ActionMapping mapping, HttpServletRequest request) {

		if( mapping.getAttribute() != null ) {
			if( "request".equals(mapping.getScope()) ) {
				request.removeAttribute(mapping.getAttribute());
			}
			else {
				request.getSession().removeAttribute(mapping.getAttribute());
			}
		}
	}

	/**
	 * @param request
	 * @return
	 */
	protected String getURI(HttpServletRequest request) {

		//getAction(request);
		return request.getServletPath();
	}

	/**
	 * @param request
	 * @return
	 */
	protected String getAction(HttpServletRequest request) {

		String uri = request.getServletPath();
		String action = uri.substring(uri.lastIndexOf('/') + 1, uri.lastIndexOf('.'));
		//Debug.info(logger, "action=" + action );
		return action;
	}

	/**
	 * @param formFile
	 * @return
	 * @throws Exception
	 */
	protected Map uploadFile(FormFile formFile) throws Exception {

		return uploadFile(formFile, Property.getInt("maxFileSize", 1024000 * 4));
	}

	/**
	 * @param formFile
	 * @param maxFileSize
	 * @return
	 * @throws Exception
	 */
	protected Map uploadFile(FormFile formFile, int maxFileSize) throws Exception {

		ByteArrayOutputStream baos = null;
		InputStream stream = null;
		String filename = formFile.getFileName();
		String fileExtName = FileUtil.getFileExtName(filename);
		if( ".jsp".equalsIgnoreCase(fileExtName) || ".cgi".equalsIgnoreCase(fileExtName) || ".do".equalsIgnoreCase(fileExtName) ) {
			throw new Exception("Forbidened file:" + filename);
		}
		Map fileinfo = new HashMap(2);
		debug("upload formFile is -->" + formFile);
		debug("upload file is -->" + filename);
		debug("fileinfo is empty-->" + fileinfo.isEmpty());
		if( ObjectUtil.isEmpty(filename) ) {
			return fileinfo;
		}
		fileinfo.put("orgn_filename", filename);
		try {
			//retrieve the file data
			baos = new ByteArrayOutputStream();
			stream = formFile.getInputStream();
			if( formFile.getFileSize() < maxFileSize ) {
				byte[] buffer = new byte[8192];
				int bytesRead = 0;
				while( (bytesRead = stream.read(buffer, 0, 8192)) != -1 ) {
					baos.write(buffer, 0, bytesRead);
				}
//				String tempFile = Property.getString("tempFilePath", "/") + FileUtil.genFileNumber() + fileExtName;
				String filepath   =   getClass().getProtectionDomain().getCodeSource().getLocation().getPath(); 
				if   (filepath.indexOf( "WEB-INF")   >   0)   { 
					filepath = filepath.substring(0, filepath.indexOf( "/WEB-INF/")); 
				}	
				String upldedName = Property.getString("webFileTemp","")+FileUtil.genFileNumber() + fileExtName;
				String tempFile = filepath + upldedName;					
				//File upfile = new File( houseTemplateFilePath+StringUtil.genEventCode()+".xls" );
				//FileUtil.WriteFile( houseTemplateFile, data);
				FileUtil.saveBytesToFile(tempFile, baos.toByteArray());
				fileinfo.put("upld_filename", tempFile);
				return fileinfo;
			}
			else {
				String msg = "上传文件[" + filename + "]大小超过限制！";
				Debug.info(logger,msg);
				throw new Exception(msg);
			}
		}
		catch(Exception fnfe) {
			throw fnfe;
		}
		finally {
			try {
				if( stream != null ) {
					stream.close();
				}
			}
			catch(Exception fnfe) {}
			try {
				if( baos != null ) {
					baos.close();
				}
			}
			catch(Exception fnfe) {}
			//或许不用关闭？？？？？
		}
	}

	/**
	 * @param mapping
	 * @param request
	 * @param msg
	 * @return
	 */
	protected ActionForward saveError(ActionMapping mapping, HttpServletRequest request, String msg) {

		ActionMessages ams = new ActionMessages();
		if( ObjectUtil.isEmpty(msg) ) {
			msg = "没有取得系统错误提示信息！";
		}
		ams.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ERROR_MESSAGE, msg));
		saveErrors(request, ams);
		return mapping.findForward(ERROR);
	}

	/**
	 * @param mapping
	 * @param request
	 * @param msg
	 * @return
	 */
	protected ActionForward saveBlackball(ActionMapping mapping, HttpServletRequest request, String msg) {

		ActionMessages ams = new ActionMessages();
		if( ObjectUtil.isEmpty(msg) ) {
			msg = "没有取得系统错误提示信息！";
		}
		ams.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ERROR_MESSAGE, msg));
		saveErrors(request, ams);
		return mapping.findForward(BLACKBALL);
	}

	/**
	 * @param mapping
	 * @param request
	 * @param msg
	 * @return
	 */
	protected ActionForward saveInput(ActionMapping mapping, HttpServletRequest request, String msg) {

		ActionMessages mes = new ActionMessages();
		if( ObjectUtil.isEmpty(msg) ) {
			msg = "没有取得系统错误提示信息！";
		}
		mes.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(MESSAGE, msg));
		this.saveErrors(request, mes);
		return mapping.getInputForward();
	}

	/**
	 * @param msg
	 */
	public void debug(String msg) {

		if( DEBUG ) {
			Debug.info(logger,BizTraceFilter.getBizUUID() + "[WEB debug]" + msg);
		}
	}

	/**
	 * @param msg
	 */
	public void info(String msg) {

		if( INFO_OUT ) {
			Debug.info(logger,BizTraceFilter.getBizUUID() + "[WEB info]" + msg);
		}
	}

	/**
	 * @param msg
	 */
	public void error(String msg) {

		if( ERROR_OUT ) {
			Debug.info(logger,BizTraceFilter.getBizUUID() + "[WEB error]" + msg);
		}
	}
}