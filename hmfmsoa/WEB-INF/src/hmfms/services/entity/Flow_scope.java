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
 * 流程适用范围
 */
public class Flow_scope extends TableEntity
{
	private Long flow_id; //流程适用ID
	private String tran_type; //交易类型
	private Long flow_scope; //适用范围

	/** 取得：流程适用ID */
	public Long getFlow_id(){
		return flow_id;
	}
	/** 设置：流程适用ID */
	public void setFlow_id(Long flow_id){
		this.flow_id=flow_id;
	}
	/** 设置：流程适用ID */
	public void setFlow_id(String flow_id){
		if(!hmfms.util.ObjectUtil.isEmpty(flow_id))
			this.flow_id=new Long(flow_id);
	}
	/** 取得：交易类型 */
	public String getTran_type(){
		return tran_type;
	}
	/** 设置：交易类型 */
	public void setTran_type(String tran_type){
		this.tran_type=tran_type;
	}
	/** 取得：适用范围 */
	public Long getFlow_scope(){
		return flow_scope;
	}
	/** 设置：适用范围 */
	public void setFlow_scope(Long flow_scope){
		this.flow_scope=flow_scope;
	}
	/** 设置：适用范围 */
	public void setFlow_scope(String flow_scope){
		if(!hmfms.util.ObjectUtil.isEmpty(flow_scope))
			this.flow_scope=new Long(flow_scope);
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** 流程适用范围 */
	public Flow_scope(){
		primaryKeys.add("flow_id");
	}
}

