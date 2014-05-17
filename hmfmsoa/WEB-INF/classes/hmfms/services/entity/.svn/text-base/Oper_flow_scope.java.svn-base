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
public class Oper_flow_scope extends TableEntity
{
	private String batch_no;
	private String data_type;
	private Long flow_id; //流程适用ID
	private String tran_type; //交易类型
	private Long flow_scope; //适用范围


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
	public Oper_flow_scope(){
		primaryKeys.add("batch_no");
		primaryKeys.add("data_type");
		primaryKeys.add("flow_id");
	}
}

