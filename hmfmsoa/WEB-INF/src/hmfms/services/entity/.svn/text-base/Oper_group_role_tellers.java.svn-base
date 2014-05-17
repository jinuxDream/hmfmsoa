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
 * 操作员组角色关系
 */
public class Oper_group_role_tellers extends TableEntity
{
	private String batch_no;
	private String data_type;
	private Long urt_id; //用户操作员角色关系ID
	private String ro_roleid; //角色编码
	private Long group_id; //工作组id
	private String te_operid; //操作员编号


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
	/** 取得：用户操作员角色关系ID */
	public Long getUrt_id(){
		return urt_id;
	}
	/** 设置：用户操作员角色关系ID */
	public void setUrt_id(Long urt_id){
		this.urt_id=urt_id;
	}
	/** 设置：用户操作员角色关系ID */
	public void setUrt_id(String urt_id){
		if(!hmfms.util.ObjectUtil.isEmpty(urt_id))
			this.urt_id=new Long(urt_id);
	}
	/** 取得：角色编码 */
	public String getRo_roleid(){
		return ro_roleid;
	}
	/** 设置：角色编码 */
	public void setRo_roleid(String ro_roleid){
		this.ro_roleid=ro_roleid;
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
	/** 取得：操作员编号 */
	public String getTe_operid(){
		return te_operid;
	}
	/** 设置：操作员编号 */
	public void setTe_operid(String te_operid){
		this.te_operid=te_operid;
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** 操作员组角色关系 */
	public Oper_group_role_tellers(){
		primaryKeys.add("batch_no");
		primaryKeys.add("data_type");
		primaryKeys.add("urt_id");
	}
}

