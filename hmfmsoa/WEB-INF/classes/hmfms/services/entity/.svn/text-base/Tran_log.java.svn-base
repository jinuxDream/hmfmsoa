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
 * 交易日志操作表
 */
public class Tran_log extends TableEntity
{
	private String batch_no; //申请编号
	private Long work_id; //操作日志id

	/** 取得：申请编号 */
	public String getBatch_no(){
		return batch_no;
	}
	/** 设置：申请编号 */
	public void setBatch_no(String batch_no){
		this.batch_no=batch_no;
	}
	/** 取得：操作日志id */
	public Long getWork_id(){
		return work_id;
	}
	/** 设置：操作日志id */
	public void setWork_id(Long work_id){
		this.work_id=work_id;
	}
	/** 设置：操作日志id */
	public void setWork_id(String work_id){
		if(!hmfms.util.ObjectUtil.isEmpty(work_id))
			this.work_id=new Long(work_id);
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** 交易日志操作表 */
	public Tran_log(){
		primaryKeys.add("batch_no");
		primaryKeys.add("work_id");
	}
}

