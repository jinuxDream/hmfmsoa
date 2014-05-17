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
 * 交易信息表
 */
public class Trade extends TableEntity
{
	private String batch_no; //申请编号
	private String te_operid; //操作员编号
	private String tran_type; //交易类型
	private String tran_status; //交易状态
	private String create_date; //创建日期
	private String create_time; //创建时间
	private String end_date; //截止日期
	private String end_time; //截止时间
	private Long flow_scope; //流程范围
	private String remk; //备注

	/** 取得：申请编号 */
	public String getBatch_no(){
		return batch_no;
	}
	/** 设置：申请编号 */
	public void setBatch_no(String batch_no){
		this.batch_no=batch_no;
	}
	/** 取得：操作员编号 */
	public String getTe_operid(){
		return te_operid;
	}
	/** 设置：操作员编号 */
	public void setTe_operid(String te_operid){
		this.te_operid=te_operid;
	}
	/** 取得：交易类型 */
	public String getTran_type(){
		return tran_type;
	}
	/** 设置：交易类型 */
	public void setTran_type(String tran_type){
		this.tran_type=tran_type;
	}
	/** 取得：交易状态 */
	public String getTran_status(){
		return tran_status;
	}
	/** 设置：交易状态 */
	public void setTran_status(String tran_status){
		this.tran_status=tran_status;
	}
	/** 取得：创建日期 */
	public String getCreate_date(){
		return create_date;
	}
	/** 设置：创建日期 */
	public void setCreate_date(String create_date){
		this.create_date=create_date;
	}
	/** 取得：创建时间 */
	public String getCreate_time(){
		return create_time;
	}
	/** 设置：创建时间 */
	public void setCreate_time(String create_time){
		this.create_time=create_time;
	}
	/** 取得：截止日期 */
	public String getEnd_date(){
		return end_date;
	}
	/** 设置：截止日期 */
	public void setEnd_date(String end_date){
		this.end_date=end_date;
	}
	/** 取得：截止时间 */
	public String getEnd_time(){
		return end_time;
	}
	/** 设置：截止时间 */
	public void setEnd_time(String end_time){
		this.end_time=end_time;
	}
	/** 取得：流程范围 */
	public Long getFlow_scope(){
		return flow_scope;
	}
	/** 设置：流程范围 */
	public void setFlow_scope(Long flow_scope){
		this.flow_scope=flow_scope;
	}
	/** 设置：流程范围 */
	public void setFlow_scope(String flow_scope){
		if(!hmfms.util.ObjectUtil.isEmpty(flow_scope))
			this.flow_scope=new Long(flow_scope);
	}
	/** 取得：备注 */
	public String getRemk(){
		return remk;
	}
	/** 设置：备注 */
	public void setRemk(String remk){
		this.remk=remk;
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** 交易信息表 */
	public Trade(){
		primaryKeys.add("batch_no");
	}
}

