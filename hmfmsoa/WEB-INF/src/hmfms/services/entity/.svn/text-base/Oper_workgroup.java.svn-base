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
 * 工作组信息表
 */
public class Oper_workgroup extends TableEntity
{
	private String batch_no;
	private String data_type;
	private Long group_id; //工作组id
	private String group_name; //工作组名称
	private String workgroup_type; //工作组类型
	private String remark; //备注


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
	/** 取得：工作组名称 */
	public String getGroup_name(){
		return group_name;
	}
	/** 设置：工作组名称 */
	public void setGroup_name(String group_name){
		this.group_name=group_name;
	}
	/** 取得：工作组类型 */
	public String getWorkgroup_type(){
		return workgroup_type;
	}
	/** 设置：工作组类型 */
	public void setWorkgroup_type(String workgroup_type){
		this.workgroup_type=workgroup_type;
	}
	/** 取得：备注 */
	public String getRemark(){
		return remark;
	}
	/** 设置：备注 */
	public void setRemark(String remark){
		this.remark=remark;
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** 工作组信息表 */
	public Oper_workgroup(){
		primaryKeys.add("batch_no");
		primaryKeys.add("data_type");
		primaryKeys.add("group_id");
	}
}

