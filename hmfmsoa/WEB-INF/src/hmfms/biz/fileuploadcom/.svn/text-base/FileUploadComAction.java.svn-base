package hmfms.biz.fileuploadcom;


import hmfms.util.ObjectUtil;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

public class FileUploadComAction  extends OrgnBaseAction
{
	//public static final String UPLOAD_FILES_FOLDER="estofiles/";//所上传的文件存放的根目录（相对于参数uploadedFileRootPath）
	/*
	 * 上传文件的信息的分隔符。该信息是在fileUploadDown.jsp中拼装的。
	 * 格式为：filename + "|FD|" + upld_filename + "|FD|" + orgn_filename
	 */
	public static final String FILEINFO_SPLITCHAR="|";

	protected ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0 backward compatibility

		String action = getAction(request);
		if( "fileUpload".equals(action) )
		{
			return fileUpload(mapping, form, request);
		}
		else if( "fileUploadDown".equals(action) )
		{
			return fileUploadDown(mapping, form, request);
		}
		else
		{
			ActionMessages ams = new ActionMessages();
			ams.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.message", "无效的访问链接！"));
			this.saveErrors(request, ams);
			return mapping.findForward(ERROR);
		}
	}

	private ActionForward fileUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request) throws Exception
	{
		String tran_type = request.getParameter("tran_type");//交易类型
		String index = request.getParameter("index");	//附件序号
		if(ObjectUtil.isEmpty(tran_type))
			throw new Exception("未取到交易类型！");
		request.setAttribute("tran_type", tran_type);
		request.setAttribute("index", index);
		return mapping.findForward(SUCCESS);

	}

	private ActionForward fileUploadDown(ActionMapping mapping, ActionForm form, HttpServletRequest request) throws Exception
	{
		FileUploadComForm upLoadForm = (FileUploadComForm)form;
		if( upLoadForm == null ){
			request.setAttribute("fail_message", "配置的form失效！");
			return mapping.findForward(SUCCESS);
		}
		String tran_type = upLoadForm.getTran_type();
		String index = request.getParameter("index");	//附件序号
		//String filename = upLoadForm.getOrgn_filename();
		
		if(ObjectUtil.isEmpty(tran_type)){
			request.setAttribute("fail_message", "未取到交易类型！");
			request.setAttribute("tran_type", tran_type);
			return mapping.findForward("fail");
		}
		/*if(ObjectUtil.isEmpty(filename) ){
			//request.setAttribute("fail_message", "文件名不能为空！");
			request.setAttribute("tran_type", tran_type);
			return mapping.findForward("fail");
		}*/
		// 首先取出来已经存在的所有附件
		request.setAttribute("tran_type", tran_type);
		request.setAttribute("index", index);
		

		FormFile formFile = upLoadForm.getReportFile();
		
		
		if( formFile == null ){
			request.setAttribute("fail_message", "没有找到要上传的文件！");
			request.setAttribute("tran_type", tran_type);
			return mapping.findForward("fail");
		}
		
		Map uploadFileInfo;
		String upload_file_fullpath;
		try
		{
//  			String filename = formFile.getFileName();
//			String fileExtName = FileUtil.getFileExtName(filename);
//			if (!".xls" .equalsIgnoreCase(fileExtName) )
//			{
//				throw new Exception("文件是excel类型才可以上传");
//			}
			
			uploadFileInfo = uploadFile(formFile);
			upload_file_fullpath = (String)uploadFileInfo.get("upld_filename");
			
			request.setAttribute("upld_filename", upload_file_fullpath);
			
			request.setAttribute("orgn_filename", (String)uploadFileInfo.get("orgn_filename"));
			request.setAttribute("filename", (String)uploadFileInfo.get("orgn_filename"));
			upLoadForm.setReportFile(null);
			request.setAttribute("fail_message", "null");
			return mapping.findForward(SUCCESS);
			
		}catch(Exception e){
			
			request.setAttribute("fail_message", e.getMessage());
			request.setAttribute("tran_type", tran_type);
			request.setAttribute("index", index);
			return mapping.findForward("fail");
			
		}

	}
	/**
	 * 附件一览取得
	 * @param request
	 * @param response
	 */
	public void getFlowAchments(HttpServletRequest request, HttpServletResponse response) {
		String attch_id = request.getParameter("attch_id");
		String[] filePath = new FileUploadComManager().getFilePath(attch_id);
		if( filePath.length == 0 ) {
			request.setAttribute("filePath", "");
		}
		else {
			request.setAttribute("filePath", filePath[0]);
		}
	}

}
