package hmfms.services.entity;
/*
 * <Auto Created by fd.CreateTable> ���������޸ģ�
 */

import java.math.BigDecimal;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import fd.commons.TableEntity;

/**
 * ���׽�ɫ�����
 */
public class Tran_role extends TableEntity
{
	private Long tran_role_id; //���׽�ɫ����ID
	private String ro_roleid; //��ɫ����
	private Long flow_id; //��������ID
	private Long group_id; //������id
	private String tran_role; //���׽�ɫ

	/** ȡ�ã����׽�ɫ����ID */
	public Long getTran_role_id(){
		return tran_role_id;
	}
	/** ���ã����׽�ɫ����ID */
	public void setTran_role_id(Long tran_role_id){
		this.tran_role_id=tran_role_id;
	}
	/** ���ã����׽�ɫ����ID */
	public void setTran_role_id(String tran_role_id){
		if(!hmfms.util.ObjectUtil.isEmpty(tran_role_id))
			this.tran_role_id=new Long(tran_role_id);
	}
	/** ȡ�ã���ɫ���� */
	public String getRo_roleid(){
		return ro_roleid;
	}
	/** ���ã���ɫ���� */
	public void setRo_roleid(String ro_roleid){
		this.ro_roleid=ro_roleid;
	}
	/** ȡ�ã���������ID */
	public Long getFlow_id(){
		return flow_id;
	}
	/** ���ã���������ID */
	public void setFlow_id(Long flow_id){
		this.flow_id=flow_id;
	}
	/** ���ã���������ID */
	public void setFlow_id(String flow_id){
		if(!hmfms.util.ObjectUtil.isEmpty(flow_id))
			this.flow_id=new Long(flow_id);
	}
	/** ȡ�ã�������id */
	public Long getGroup_id(){
		return group_id;
	}
	/** ���ã�������id */
	public void setGroup_id(Long group_id){
		this.group_id=group_id;
	}
	/** ���ã�������id */
	public void setGroup_id(String group_id){
		if(!hmfms.util.ObjectUtil.isEmpty(group_id))
			this.group_id=new Long(group_id);
	}
	/** ȡ�ã����׽�ɫ */
	public String getTran_role(){
		return tran_role;
	}
	/** ���ã����׽�ɫ */
	public void setTran_role(String tran_role){
		this.tran_role=tran_role;
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** ���׽�ɫ����� */
	public Tran_role(){
		primaryKeys.add("tran_role_id");
	}
}

