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
 * 工作组菜单关系
 */
public class Workgroup_menu extends TableEntity
{
	private Long workmenu_id; //工作组菜单ID
	private String menu_id; //菜单ID
	private Long group_id; //工作组id

	/** 取得：工作组菜单ID */
	public Long getWorkmenu_id(){
		return workmenu_id;
	}
	/** 设置：工作组菜单ID */
	public void setWorkmenu_id(Long workmenu_id){
		this.workmenu_id=workmenu_id;
	}
	/** 设置：工作组菜单ID */
	public void setWorkmenu_id(String workmenu_id){
		if(!hmfms.util.ObjectUtil.isEmpty(workmenu_id))
			this.workmenu_id=new Long(workmenu_id);
	}
	/** 取得：菜单ID */
	public String getMenu_id(){
		return menu_id;
	}
	/** 设置：菜单ID */
	public void setMenu_id(String menu_id){
		this.menu_id=menu_id;
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
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** 工作组菜单关系 */
	public Workgroup_menu(){
		primaryKeys.add("workmenu_id");
	}
}

