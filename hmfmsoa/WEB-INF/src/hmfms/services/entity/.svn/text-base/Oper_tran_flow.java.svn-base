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
 * 交易流程控制表
 */
public class Oper_tran_flow extends TableEntity
{
	private String batch_no;
	private String data_type;
	private Long flow_oper_id; //流程ID
	private Long tran_role_id; //交易角色定义ID
	private String next_status; //下一状态
	private String tran_oper; //交易操作按钮名称
	private String curr_status; //当前状态
	private String view_url; //页面url
	private String oper_url; //操作url
	private String is_enable; //是否启用
	private String remk; //备注


	/** 取得：操作日志ID */
	public String getBatch_no(){
		return batch_no;
	}
	/** 设置：操作日志ID */
	public void setBatch_no(String batch_no){
		this.batch_no=batch_no;
	}
	/** 取得：操作数据类型 */
	public String getData_type(){
		return data_type;
	}
	/** 设置：操作数据类型 */
	public void setData_type(String data_type){
		this.data_type=data_type;
	}
	/** 取得：流程ID */
	public Long getFlow_oper_id(){
		return flow_oper_id;
	}
	/** 设置：流程ID */
	public void setFlow_oper_id(Long flow_oper_id){
		this.flow_oper_id=flow_oper_id;
	}
	/** 设置：流程ID */
	public void setFlow_oper_id(String flow_oper_id){
		if(!hmfms.util.ObjectUtil.isEmpty(flow_oper_id))
			this.flow_oper_id=new Long(flow_oper_id);
	}
	/** 取得：交易角色定义ID */
	public Long getTran_role_id(){
		return tran_role_id;
	}
	/** 设置：交易角色定义ID */
	public void setTran_role_id(Long tran_role_id){
		this.tran_role_id=tran_role_id;
	}
	/** 设置：交易角色定义ID */
	public void setTran_role_id(String tran_role_id){
		if(!hmfms.util.ObjectUtil.isEmpty(tran_role_id))
			this.tran_role_id=new Long(tran_role_id);
	}
	/** 取得：下一状态 */
	public String getNext_status(){
		return next_status;
	}
	/** 设置：下一状态 */
	public void setNext_status(String next_status){
		this.next_status=next_status;
	}
	/** 取得：交易操作按钮名称 */
	public String getTran_oper(){
		return tran_oper;
	}
	/** 设置：交易操作按钮名称 */
	public void setTran_oper(String tran_oper){
		this.tran_oper=tran_oper;
	}
	/** 取得：当前状态 */
	public String getCurr_status(){
		return curr_status;
	}
	/** 设置：当前状态 */
	public void setCurr_status(String curr_status){
		this.curr_status=curr_status;
	}
	/** 取得：页面url */
	public String getView_url(){
		return view_url;
	}
	/** 设置：页面url */
	public void setView_url(String view_url){
		this.view_url=view_url;
	}
	/** 取得：操作url */
	public String getOper_url(){
		return oper_url;
	}
	/** 设置：操作url */
	public void setOper_url(String oper_url){
		this.oper_url=oper_url;
	}
	/** 取得：是否启用 */
	public String getIs_enable(){
		return is_enable;
	}
	/** 设置：是否启用 */
	public void setIs_enable(String is_enable){
		this.is_enable=is_enable;
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
	/** 交易流程控制表 */
	public Oper_tran_flow(){
		primaryKeys.add("batch_no");
		primaryKeys.add("data_type");
		primaryKeys.add("flow_oper_id");
	}
}

