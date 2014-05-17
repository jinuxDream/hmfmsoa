package hmfms.services.entity;
/*
 * <Auto Created by fd.CreateTable> 不得擅自修改！
 */

import java.math.BigDecimal;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import fd.commons.TableEntity;

/**
 * 上传附件信息表
 */
public class Attachments extends TableEntity
{
	private Long attch_id; //附件序号
	private String table_name; //附件对于的表名
	private Long refno_id; //附件关联的主键ID
	private String comm_date; //提交日期
	private String orgn_filename; //原始文件名
	private String file_path; //文件存放路径
	private String filename; //自定义文件名
	private Long file_size; //文件大小

	/** 取得：附件序号 */
	public Long getAttch_id(){
		return attch_id;
	}
	/** 设置：附件序号 */
	public void setAttch_id(Long attch_id){
		this.attch_id=attch_id;
	}
	/** 设置：附件序号 */
	public void setAttch_id(String attch_id){
		if(!hmfms.util.ObjectUtil.isEmpty(attch_id))
			this.attch_id=new Long(attch_id);
	}
	/** 取得：附件对于的表名 */
	public String getTable_name(){
		return table_name;
	}
	/** 设置：附件对于的表名 */
	public void setTable_name(String table_name){
		this.table_name=table_name;
	}
	/** 取得：附件关联的主键ID */
	public Long getRefno_id(){
		return refno_id;
	}
	/** 设置：附件关联的主键ID */
	public void setRefno_id(Long refno_id){
		this.refno_id=refno_id;
	}
	/** 设置：附件关联的主键ID */
	public void setRefno_id(String refno_id){
		if(!hmfms.util.ObjectUtil.isEmpty(refno_id))
			this.refno_id=new Long(refno_id);
	}
	/** 取得：提交日期 */
	public String getComm_date(){
		return comm_date;
	}
	/** 设置：提交日期 */
	public void setComm_date(String comm_date){
		this.comm_date=comm_date;
	}
	/** 取得：原始文件名 */
	public String getOrgn_filename(){
		return orgn_filename;
	}
	/** 设置：原始文件名 */
	public void setOrgn_filename(String orgn_filename){
		this.orgn_filename=orgn_filename;
	}
	/** 取得：文件存放路径 */
	public String getFile_path(){
		return file_path;
	}
	/** 设置：文件存放路径 */
	public void setFile_path(String file_path){
		this.file_path=file_path;
	}
	/** 取得：自定义文件名 */
	public String getFilename(){
		return filename;
	}
	/** 设置：自定义文件名 */
	public void setFilename(String filename){
		this.filename=filename;
	}
	/** 取得：文件大小 */
	public Long getFile_size(){
		return file_size;
	}
	/** 设置：文件大小 */
	public void setFile_size(Long file_size){
		this.file_size=file_size;
	}
	/** 设置：文件大小 */
	public void setFile_size(String file_size){
		if(!hmfms.util.ObjectUtil.isEmpty(file_size))
			this.file_size=new Long(file_size);
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** 上传附件信息表 */
	public Attachments(){
		primaryKeys.add("attch_id");
	}
}

