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
 * 申请编号生成表
 */
public class Keytable extends TableEntity
{
	private String key_name; //key_name
	private Long value; //value

	/** 取得：key_name */
	public String getKey_name(){
		return key_name;
	}
	/** 设置：key_name */
	public void setKey_name(String key_name){
		this.key_name=key_name;
	}
	/** 取得：value */
	public Long getValue(){
		return value;
	}
	/** 设置：value */
	public void setValue(Long value){
		this.value=value;
	}
	/** 设置：value */
	public void setValue(String value){
		if(!hmfms.util.ObjectUtil.isEmpty(value))
			this.value=new Long(value);
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** 申请编号生成表 */
	public Keytable(){
		primaryKeys.add("key_name");
	}
}

