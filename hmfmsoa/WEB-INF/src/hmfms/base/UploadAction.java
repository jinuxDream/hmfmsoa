package hmfms.base;

import hmfms.util.ActionUtil;
import hmfms.util.Constants;
import hmfms.util.FileUtil;
import hmfms.util.ObjectUtil;
import hmfms.util.StringUtil;
import hmfms.web.User;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mng_plat.service.cfg.CfgSysPara;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * 在配置文件：hmfms.properties中增加如下配置(已改用从系统参数中获取)：
 * tempFilePath=/java/app/eclipse/hmfms/upload/   -----SysParaUtil.getParaValueByName("tempFilePath")
 * webFileTemp=/upload/
 * 注意：webFileTemp的值要完全等于tempFilePath最后一个目录。
 *
 * @author 米维聪
 * 
 */
public abstract class UploadAction extends Action {

	public static final boolean DEBUG = Constants.DEV_DEBUG;
	public static final boolean INFO_OUT = Constants.INFO_OUT;
	public static final boolean ERROR_OUT = Constants.ERROR_OUT;
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	public static final String ERROR = "error";

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0 backward compatibility
		ActionForward forward = executeAction(mapping, form, request, response);
		return forward;
	}

	protected abstract ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
					throws Exception;

	protected String getURI(HttpServletRequest request) {

		return request.getServletPath();
	}

	protected User getUser(HttpServletRequest request) {

		return ActionUtil.getUser(request);
	}
	protected String getWorkId(HttpServletRequest request) {

		return StringUtil.getString(request.getAttribute("work_id"));
	}
	
	protected String getAction(HttpServletRequest request) {

		String uri = request.getServletPath();
		String action = uri.substring(uri.lastIndexOf('/') + 1, uri.lastIndexOf('.'));
		return action;
	}

	protected Map uploadFile(FormFile formFile) throws Exception { //改用从系统参数中获取

		return uploadFile(formFile, Integer.parseInt(CfgSysPara.getParaValueByName("maxfilesize")));
	}

	protected Map uploadFile(FormFile formFile, int maxFileSize) throws Exception {

		ByteArrayOutputStream baos = null;
		InputStream stream = null;
		String filename = formFile.getFileName();
		String fileExtName = FileUtil.getFileExtName(filename);
		if( ".jsp".equalsIgnoreCase(fileExtName) || ".cgi".equalsIgnoreCase(fileExtName) || ".do".equalsIgnoreCase(fileExtName) )
			throw new Exception("Forbidened file:" + filename);
		Map fileinfo = new HashMap(2);
		if( ObjectUtil.isEmpty(filename) )
			return fileinfo;
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

				String tempPath = CfgSysPara.getParaValueByName("tempFilePath");
				java.io.File file = new java.io.File(tempPath);
				if( !file.exists() )
					throw new Exception("临时文件目录不存在：" + tempPath);
				String tempFile = tempPath + FileUtil.FILESYSTEM_SEPARATOR + FileUtil.genFileNumber() + fileExtName;

				FileUtil.saveBytesToFile(tempFile, baos.toByteArray());
				fileinfo.put("upld_filename", tempFile);
				return fileinfo;
			}
			else {
				String msg = "上传文件[" + filename + "]大小超过限制！";
				System.out.println(msg);
				throw new Exception(msg);
			}
		}
		catch(Exception fnfe) {
			throw fnfe;
		}
		finally {
			try {
				if( stream != null )
					stream.close();
			}
			catch(Exception fnfe) {}
			try {
				if( baos != null )
					baos.close();
			}
			catch(Exception fnfe) {} //或许不用关闭？？？？？
		}

	}

	public void debug(String msg) {

		if( DEBUG )
			System.out.println("[UPLOAD debug]" + msg);
	}

	public void info(String msg) {

		if( INFO_OUT )
			System.out.println("[UPLOAD info]" + msg);
	}

	public void error(String msg) {

		if( ERROR_OUT )
			System.out.println("[UPLOAD error]" + msg);
	}
}
