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
 * 角色-菜单关系(权限表)
 */
public class Role_menu extends TableEntity
{
	private String ro_roleid; //角色编码
	private String menu_id; //菜单ID

	/** 取得：角色编码 */
	public String getRo_roleid(){
		return ro_roleid;
	}
	/** 设置：角色编码 */
	public void setRo_roleid(String ro_roleid){
		this.ro_roleid=ro_roleid;
	}
	/** 取得：菜单ID */
	public String getMenu_id(){
		return menu_id;
	}
	/** 设置：菜单ID */
	public void setMenu_id(String menu_id){
		this.menu_id=menu_id;
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** 角色-菜单关系(权限表) */
	public Role_menu(){
		primaryKeys.add("ro_roleid");
		primaryKeys.add("menu_id");
	}
}

