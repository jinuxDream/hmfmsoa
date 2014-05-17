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
 * 角色表
 */
public class Role extends TableEntity
{
	private String ro_roleid; //角色编码
	private String ro_name; //角色名称
	private String ro_desc; //描述
	private String ro_crt_oper; //创建人
	private String ro_crt_date; //创建日期

	/** 取得：角色编码 */
	public String getRo_roleid(){
		return ro_roleid;
	}
	/** 设置：角色编码 */
	public void setRo_roleid(String ro_roleid){
		this.ro_roleid=ro_roleid;
	}
	/** 取得：角色名称 */
	public String getRo_name(){
		return ro_name;
	}
	/** 设置：角色名称 */
	public void setRo_name(String ro_name){
		this.ro_name=ro_name;
	}
	/** 取得：描述 */
	public String getRo_desc(){
		return ro_desc;
	}
	/** 设置：描述 */
	public void setRo_desc(String ro_desc){
		this.ro_desc=ro_desc;
	}
	/** 取得：创建人 */
	public String getRo_crt_oper(){
		return ro_crt_oper;
	}
	/** 设置：创建人 */
	public void setRo_crt_oper(String ro_crt_oper){
		this.ro_crt_oper=ro_crt_oper;
	}
	/** 取得：创建日期 */
	public String getRo_crt_date(){
		return ro_crt_date;
	}
	/** 设置：创建日期 */
	public void setRo_crt_date(String ro_crt_date){
		this.ro_crt_date=ro_crt_date;
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** 角色表 */
	public Role(){
		primaryKeys.add("ro_roleid");
	}
}

