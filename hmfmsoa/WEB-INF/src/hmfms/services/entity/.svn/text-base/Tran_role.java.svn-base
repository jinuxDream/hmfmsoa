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
 * 交易角色定义表
 */
public class Tran_role extends TableEntity
{
	private Long tran_role_id; //交易角色定义ID
	private String ro_roleid; //角色编码
	private Long flow_id; //流程适用ID
	private Long group_id; //工作组id
	private String tran_role; //交易角色

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
	/** 取得：角色编码 */
	public String getRo_roleid(){
		return ro_roleid;
	}
	/** 设置：角色编码 */
	public void setRo_roleid(String ro_roleid){
		this.ro_roleid=ro_roleid;
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
	/** 取得：工作组id */
	public Long getGroup_id(){
		return group_id;
	}
	/** 设置：工作组id */
	public void setGroup_id(Long group_id){
		this.group_id=group_id;
	}
	/** 设置：工作组id */
	public void setGroup_id(String group_id){
		if(!hmfms.util.ObjectUtil.isEmpty(group_id))
			this.group_id=new Long(group_id);
	}
	/** 取得：交易角色 */
	public String getTran_role(){
		return tran_role;
	}
	/** 设置：交易角色 */
	public void setTran_role(String tran_role){
		this.tran_role=tran_role;
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** 交易角色定义表 */
	public Tran_role(){
		primaryKeys.add("tran_role_id");
	}
}

