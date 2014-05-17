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
 * 交易退回记录表
 */
public class Trade_redo_log extends TableEntity
{
	private String batch_no; //申请编号
	private String oper_datetime; //操作日期
	private String te_operid; //操作员编号
	private String te_name; //操作人名称
	private String remark; //操作备注
	private String tran_status; //操作后状态
	private String tran_status_old; //操作前状态
	private String tran_type; //交易类型
	private String redo_status; //是否已处理退回
	private Long to_org_id; //退向部门
	private String to_operid; //退向操作员编号
	private String audit_credit_date; //系统创建日期
	private String audit_credit_time; //系统创建时间

	/** 取得：申请编号 */
	public String getBatch_no(){
		return batch_no;
	}
	/** 设置：申请编号 */
	public void setBatch_no(String batch_no){
		this.batch_no=batch_no;
	}
	/** 取得：操作日期 */
	public String getOper_datetime(){
		return oper_datetime;
	}
	/** 设置：操作日期 */
	public void setOper_datetime(String oper_datetime){
		this.oper_datetime=oper_datetime;
	}
	/** 取得：操作员编号 */
	public String getTe_operid(){
		return te_operid;
	}
	/** 设置：操作员编号 */
	public void setTe_operid(String te_operid){
		this.te_operid=te_operid;
	}
	/** 取得：操作人名称 */
	public String getTe_name(){
		return te_name;
	}
	/** 设置：操作人名称 */
	public void setTe_name(String te_name){
		this.te_name=te_name;
	}
	/** 取得：操作备注 */
	public String getRemark(){
		return remark;
	}
	/** 设置：操作备注 */
	public void setRemark(String remark){
		this.remark=remark;
	}
	/** 取得：操作后状态 */
	public String getTran_status(){
		return tran_status;
	}
	/** 设置：操作后状态 */
	public void setTran_status(String tran_status){
		this.tran_status=tran_status;
	}
	/** 取得：操作前状态 */
	public String getTran_status_old(){
		return tran_status_old;
	}
	/** 设置：操作前状态 */
	public void setTran_status_old(String tran_status_old){
		this.tran_status_old=tran_status_old;
	}
	/** 取得：交易类型 */
	public String getTran_type(){
		return tran_type;
	}
	/** 设置：交易类型 */
	public void setTran_type(String tran_type){
		this.tran_type=tran_type;
	}
	/** 取得：是否已处理退回 */
	public String getRedo_status(){
		return redo_status;
	}
	/** 设置：是否已处理退回 */
	public void setRedo_status(String redo_status){
		this.redo_status=redo_status;
	}
	/** 取得：退向部门 */
	public Long getTo_org_id(){
		return to_org_id;
	}
	/** 设置：退向部门 */
	public void setTo_org_id(Long to_org_id){
		this.to_org_id=to_org_id;
	}
	/** 设置：退向部门 */
	public void setTo_org_id(String to_org_id){
		if(!hmfms.util.ObjectUtil.isEmpty(to_org_id))
			this.to_org_id=new Long(to_org_id);
	}
	/** 取得：退向操作员编号 */
	public String getTo_operid(){
		return to_operid;
	}
	/** 设置：退向操作员编号 */
	public void setTo_operid(String to_operid){
		this.to_operid=to_operid;
	}
	/** 取得：系统创建日期 */
	public String getAudit_credit_date(){
		return audit_credit_date;
	}
	/** 设置：系统创建日期 */
	public void setAudit_credit_date(String audit_credit_date){
		this.audit_credit_date=audit_credit_date;
	}
	/** 取得：系统创建时间 */
	public String getAudit_credit_time(){
		return audit_credit_time;
	}
	/** 设置：系统创建时间 */
	public void setAudit_credit_time(String audit_credit_time){
		this.audit_credit_time=audit_credit_time;
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** 交易退回记录表 */
	public Trade_redo_log(){
		primaryKeys.add("batch_no");
		primaryKeys.add("oper_datetime");
	}
}

