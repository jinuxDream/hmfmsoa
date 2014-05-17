/**
 * 
 */
package hmfms.util;

import fd.exception.BusinessException;
import hmfms.web.User;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>标 题: 天津市住宅维修基金管理系统</p>
 * <p>描 述:</p>
 * <p>版 权: Copyright (c) 2008</p>
 * <p>公 司: 上海宇信泓智信息科技有限公司</p>
 * <p>创建时间: 2008-9-20 下午04:01:22</p>
 * @author lxy 
 *	rebuild by zhangcs@2010
 * @version 1.0 FileDownloadUtil
 */
public class FileDownloadUtil {

	private static final Log logger = LogFactory.getLog(FileDownloadUtil.class);

	/**
	 * 将字符内容以文件的形式下载
	 * @param request
	 * @param response
	 * @param displayFileName
	 * @param content
	 */

	public static void download(HttpServletRequest request, HttpServletResponse response, String displayFileName, String content) {

		String contentType = (String)request.getAttribute("contentType");
		if( contentType == null )
			contentType = request.getParameter("contentType");

		try {

			response.reset();
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment; attachment; filename=" + displayFileName);
			ServletOutputStream os = response.getOutputStream();
			os.write(content.getBytes("GBK"));
			os.flush();
			os.close();

		}
		catch(Exception e) {
			throw new BusinessException("文件已不存在！");
		}
	}

	/**
	 * 文件下载
	 * 
	 * @authorzhangcsh
	 * @param request
	 * @param response
	 * @param fileSavePath
	 * @param displayFileName
	 */
	public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String path, String displayFileName, Boolean isResource) {

		//isResource =TRUE 表示下载位于项目内的资源文件
		//isResource =FALSE 下载不是位于项目内的资源文件，而是用绝对路径表示的文件
		if( Boolean.TRUE.equals(isResource) ) {
			InputStream is = ActionUtil.getResourceIS(request, path);
			downloadResourceFile(request, response, is, displayFileName);
		}
		else {
			downloadFileByRealPath(request, response, path, displayFileName);
		}
	}
	/**
	 * 将转文件显示名称防止乱码
	 * @param displayFileName
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String format(String displayFileName,HttpServletRequest request) throws UnsupportedEncodingException{
		String userAgent = request.getHeader("USER-AGENT");
		if(StringUtils.contains(userAgent, "MSIE")){//IE浏览器
			displayFileName = URLEncoder.encode(displayFileName,"UTF8");
        }else if(StringUtils.contains(userAgent,"Safari")){//Safari浏览器
        	displayFileName = new String(displayFileName.getBytes("UTF-8"), "ISO8859-1");
        }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
        	displayFileName = new String(displayFileName.getBytes(), "ISO8859-1");
        }else{
        	displayFileName = URLEncoder.encode(displayFileName,"UTF8");//其他浏览器
        }
		return displayFileName;
	}
	/**
	 * 文件下载
	 * 
	 * @author huangmh
	 * @param request
	 * @param response
	 * @param fileSavePath
	 * @param displayFileName
	 */
	public static void downloadFileByRealPath(HttpServletRequest request, HttpServletResponse response, String fileSavePath, String displayFileName) {

		ServletOutputStream os = null;
		FileInputStream in = null;
		try {
			if( new File(fileSavePath).exists() ) {
				response.reset();
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.addHeader("Content-Disposition", "attachment;filename=" + format(displayFileName,request));
				os = response.getOutputStream();
				in = new FileInputStream(fileSavePath);
				byte[] data = new byte[8096];
				int iSizq = -1;
				while( (iSizq = in.read(data)) != -1 ) {
					os.write(data, 0, iSizq);
				}
				//os.flush();

			}
			else {
				throw new BusinessException("文件已不存在:" + fileSavePath);
			}
		}
		catch(BusinessException e) {
			e.printStackTrace();
			throw e;
		}
		catch(Exception e) {
			throw new BusinessException("文件下载异常！", e);
		}
		finally {
			if( null != os )
				try {
					os.close();
				}
				catch(IOException e) {}
			if( null != in )
				try {
					in.close();
				}
				catch(IOException e) {}
		}
	}

	/**
	 * 从InputStream中下载
	 * 
	 * @author zhangcs
	 * @param request
	 * @param response
	 * @param baos
	 * @param displayFileName
	 * @throws IOException
	 */
	public static void downloadResourceFile(HttpServletRequest request, HttpServletResponse response, InputStream is, String displayFileName) {

		ServletOutputStream os = null;
		try {
			response.reset();
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; attachment; filename=" + format(displayFileName,request));
			os = response.getOutputStream();
			byte[] data = new byte[8096];
			int iSizq = -1;
			while( (iSizq = is.read(data)) != -1 )
				os.write(data, 0, iSizq);
			os.flush();
		}
		catch(Exception e) {
			throw new BusinessException("文件下载异常！", e);

		}
		finally {

			if( null != os )
				try {
					os.close();
				}
				catch(IOException e) {}
		}
	}

	/**
	 * 将输出流写到临时文件，返回文件绝对路径
	 * 
	 * @param request
	 * @param baos
	 */
	public static String writeToTempFile(HttpServletRequest request, ByteArrayOutputStream baos) {

		User user = ActionUtil.getUser(request);
		if( user == null )
			throw new RuntimeException("系统异常：无法取得用户登录信息！");

		String tempPath = Property.getString("tempdownload", "/tmp");
		File tempFilePath = new File(tempPath);
		if( !tempFilePath.exists() ) {
			Debug.info(logger, "临时文件目录不存在:" + tempPath);
			throw new BusinessException("临时文件目录不存在！" + tempPath);
		}
		String tempFile = tempPath + FileUtil.FILESYSTEM_SEPARATOR + user.getTellID() + FileUtil.genFileNumber();
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(tempFile);
			baos.writeTo(fos);
			return tempFile;
		}
		catch(IOException e) {
			throw new BusinessException(e);
		}
		finally {
			if( null != fos )
				try {
					fos.close();
				}
				catch(IOException e) {}
		}
	}

	/**
	 * 将内容写到临时文件，返回文件绝对路径
	 * 
	 * @param request
	 * @param content
	 * @return
	 */
	public static String writeToTempFile(HttpServletRequest request, String content) {

		User user = ActionUtil.getUser(request);
		if( user == null )
			throw new RuntimeException("系统异常：无法取得用户登录信息！");

		String tempPath = Property.getString("tempFilePath", "/tmp");
		File tempFilePath = new File(tempPath);
		if( !tempFilePath.exists() ) {
			Debug.info(logger, "临时文件目录不存在:" + tempPath);
			throw new BusinessException("临时文件目录不存在！" + tempPath);
		}
		String tempFile = tempPath + FileUtil.FILESYSTEM_SEPARATOR + user.getTellID() + FileUtil.genFileNumber();
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(tempFile));
			fw.write(content);
			fw.flush();
			return tempFile;
		}
		catch(IOException e) {
			throw new BusinessException(e);
		}
		finally {
			if( null != fw )
				try {
					fw.close();
				}
				catch(IOException e) {}
		}
	}
}
