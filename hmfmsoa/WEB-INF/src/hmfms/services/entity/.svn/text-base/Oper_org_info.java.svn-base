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
 * 单位信息表
 */
public class Oper_org_info extends TableEntity
{
	private String batch_no;
	private String data_type;
	private Long org_id; //单位ID
	private String dept_type; //单位类别
	private String org_name; //名称


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
	/** 取得：单位ID */
	public Long getOrg_id(){
		return org_id;
	}
	/** 设置：单位ID */
	public void setOrg_id(Long org_id){
		this.org_id=org_id;
	}
	/** 设置：单位ID */
	public void setOrg_id(String org_id){
		if(!hmfms.util.ObjectUtil.isEmpty(org_id))
			this.org_id=new Long(org_id);
	}
	/** 取得：单位类别 */
	public String getDept_type(){
		return dept_type;
	}
	/** 设置：单位类别 */
	public void setDept_type(String dept_type){
		this.dept_type=dept_type;
	}
	/** 取得：名称 */
	public String getOrg_name(){
		return org_name;
	}
	/** 设置：名称 */
	public void setOrg_name(String org_name){
		this.org_name=org_name;
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** 单位信息表 */
	public Oper_org_info(){
		primaryKeys.add("batch_no");
		primaryKeys.add("data_type");
		primaryKeys.add("org_id");
	}
}

