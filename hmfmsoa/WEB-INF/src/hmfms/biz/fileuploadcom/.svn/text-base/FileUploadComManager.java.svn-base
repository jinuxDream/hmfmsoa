package hmfms.biz.fileuploadcom;

import hmfms.services.entity.Attachments;
import hmfms.services.key.PrimayKeyGener;
import hmfms.util.DateUtil;
import hmfms.util.FileUtil;
import hmfms.util.ObjectUtil;
import hmfms.util.Property;
import hmfms.util.StringUtil;
import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.BusinessException;
import fd.util.Assert;

/**
 * 
 * <p>标 题: 青岛房屋维修资金</p>
 * <p>描 述: 附件上传功能</p>
 * <p>版 权: Copyright (c) 2009</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2009-9-15</p>
 * @author luohy
 * @version 1.0
 */
public final class FileUploadComManager {

	/**
	 * 根据附件的id查询附件信息表相关的所有文件<br>
	 * @param batch_no 
	 * @return 信息表的数据
	 */
	public static Result getFiles(String attch_id) {

		Assert.hasText(attch_id, "调动getFiles方法,申请编号不能为空！");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			//取信息表相关附件			
			dbo.addSql("SELECT *");
			dbo.addSql("  FROM attachments a1 ");
			dbo.addSql(" WHERE attch_id = ? ");
			dbo.addParam(attch_id);
			Result rs = dbo.query(db);
			return rs;
		}
		catch(Exception e) {
			if( e instanceof BusinessException ) {
				throw (BusinessException)e;
			}
			throw new BusinessException(e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}

	/**
	 * 根据申请编号上传附件信息表相关的所有文件<br>
	 * @param batch_no 申请编号
	 * @return 信息表的数据
	 */
	public static Result getFiles(String tablename, String batch_no) {

		Assert.hasText(batch_no, "调动getFiles方法,申请编号不能为空！");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			//取信息表相关附件			
			dbo.addSql("SELECT *");
			dbo.addSql("  FROM attachments a1 ");
			dbo.addSql(" WHERE refno_id = ? ");
			dbo.addParam(batch_no);
			dbo.addEqualParam("table_name", tablename);
			Result rs = dbo.query(db);
			return rs;
		}
		catch(Exception e) {
			if( e instanceof BusinessException ) {
				throw (BusinessException)e;
			}
			throw new BusinessException(e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}

	/**
	 * 取得附件信息文件相对路径数组
	 * @param batch_no
	 * @return
	 */
	public String[] getFilePath(String batch_no) {

		Result rs = getFiles(batch_no);
		String[] filePath_arr = new String[rs.getRowCount()];
		String filepath = getClass().getResource("").getPath();
		if( filepath.indexOf("WEB-INF") > 0 ) {
			filepath = filepath.substring(0, filepath.indexOf("/WEB-INF/"));
		}
		filepath = filepath.substring(1, filepath.length()) + Property.getString("webFileRoot", "");
		for(int i = 0; i < rs.getRowCount(); i++) { // filepath +
			filePath_arr[i] = rs.getString(i, "file_path");
		}
		return filePath_arr;
	}

	/***
	 * 把新上传的文件，存入信息表
	 * @param uploadfiles
	 * @param tran_type
	 * @param batch_no
	 * @param table_name
	 * @param db
	 * @return
	 */
	public void updateUploadFilesby(String[] uploadfiles, String tran_type, String batch_no, String table_name, SQLExecutor db) {

		SqlOperator dbo = new SqlOperator();
		for(int i = 0; i < uploadfiles.length; i++) {
			String fileid = PrimayKeyGener.getNextId(db);
			String uploadfileinfo = uploadfiles[i];
			if( ObjectUtil.isEmpty(uploadfileinfo) )
				continue;
			String[] upfileinfo = StringUtil.split(uploadfileinfo, FileUploadComAction.FILEINFO_SPLITCHAR);
			if( upfileinfo.length != 3 )
				throw new BusinessException("上传的文件数据异常！data=" + uploadfileinfo);
			Attachments attachments = new Attachments();
			attachments.setComm_date(DateUtil.getSysDate());
			attachments.setTable_name(table_name);
			attachments.setRefno_id(batch_no);
			attachments.setAttch_id(fileid);
			//将原始文件名中的法规全文作为Filename
			String File_Name = upfileinfo[2].split("\\.")[0].split("@")[0];
			attachments.setFilename(File_Name);
			attachments.setOrgn_filename(upfileinfo[2]);
			String tempFile = upfileinfo[1];//这个文件是存储于tempFilePath下的文件
			String filediskname = tempFile.substring(tempFile.lastIndexOf("/"));//文件名
			String fileuripath = tran_type + filediskname;
			attachments.setFile_path(fileuripath);
			long file_size = FileUtil.getFileSize(tempFile);//取文件大小
			attachments.setFile_size(new Long(file_size));
			if( dbo.add(attachments, db) != 1 )
				throw new BusinessException("增加附件文件失败！data=" + attachments);
			//	FileUtil.mkdir(Property.getString("uploadedFileRootPath","")+tran_type);
			//把文件从临时目录移到真实目录下
			String filepath = getClass().getResource("").getPath();
			if( filepath.indexOf("WEB-INF") > 0 ) {
				filepath = filepath.substring(0, filepath.indexOf("/WEB-INF/"));
			}
			//			if(FileUtil.moveto(tempFile, Property.getString("uploadedFileRootPath", "")+fileuripath)!=1)
			//				throw new BusinessException("文件转存失败！filename="+upfileinfo[0]+", tempFile="+tempFile);
			filepath = filepath + Property.getString("webFileRoot", "");
			int ret = FileUtil.mkdir(filepath + tran_type);
			if( ret != 1 ) {
				throw new BusinessException("路径生成失败！filepath=" + filepath + tran_type);
			}
			if( FileUtil.moveto(tempFile, filepath + fileuripath) != 1 ) {
				throw new BusinessException("文件转存失败！filename=" + upfileinfo[0] + ", tempFile=" + tempFile);
			}

		}
	}
}
