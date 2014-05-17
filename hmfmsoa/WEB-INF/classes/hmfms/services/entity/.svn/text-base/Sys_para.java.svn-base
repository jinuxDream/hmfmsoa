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
 * 系统参数表
 */
public class Sys_para extends TableEntity
{
	private Long para_id; //参数id
	private String para_name; //参数名称
	private String para_type; //参数类别
	private String para_value; //参数值
	private String remk; //备注

	/** 取得：参数id */
	public Long getPara_id(){
		return para_id;
	}
	/** 设置：参数id */
	public void setPara_id(Long para_id){
		this.para_id=para_id;
	}
	/** 设置：参数id */
	public void setPara_id(String para_id){
		if(!hmfms.util.ObjectUtil.isEmpty(para_id))
			this.para_id=new Long(para_id);
	}
	/** 取得：参数名称 */
	public String getPara_name(){
		return para_name;
	}
	/** 设置：参数名称 */
	public void setPara_name(String para_name){
		this.para_name=para_name;
	}
	/** 取得：参数类别 */
	public String getPara_type(){
		return para_type;
	}
	/** 设置：参数类别 */
	public void setPara_type(String para_type){
		this.para_type=para_type;
	}
	/** 取得：参数值 */
	public String getPara_value(){
		return para_value;
	}
	/** 设置：参数值 */
	public void setPara_value(String para_value){
		this.para_value=para_value;
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
	/** 系统参数表 */
	public Sys_para(){
		primaryKeys.add("para_id");
	}
}

