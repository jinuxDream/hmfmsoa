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
public class Oper_workgroup_menu extends TableEntity
{
	private String batch_no;
	private String data_type;
	private Long workmenu_id; //工作组菜单ID
	private String menu_id; //菜单ID
	private Long group_id; //工作组id


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
	public Oper_workgroup_menu(){
		primaryKeys.add("batch_no");
		primaryKeys.add("data_type");
		primaryKeys.add("workmenu_id");
	}
}

