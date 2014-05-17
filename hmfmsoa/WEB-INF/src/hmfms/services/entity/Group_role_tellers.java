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
 * ����Ա���ɫ��ϵ
 */
public class Group_role_tellers extends TableEntity
{
	private Long urt_id; //�û�����Ա��ɫ��ϵID
	private String ro_roleid; //��ɫ����
	private Long group_id; //������id
	private String te_operid; //����Ա���

	/** ȡ�ã��û�����Ա��ɫ��ϵID */
	public Long getUrt_id(){
		return urt_id;
	}
	/** ���ã��û�����Ա��ɫ��ϵID */
	public void setUrt_id(Long urt_id){
		this.urt_id=urt_id;
	}
	/** ���ã��û�����Ա��ɫ��ϵID */
	public void setUrt_id(String urt_id){
		if(!hmfms.util.ObjectUtil.isEmpty(urt_id))
			this.urt_id=new Long(urt_id);
	}
	/** ȡ�ã���ɫ���� */
	public String getRo_roleid(){
		return ro_roleid;
	}
	/** ���ã���ɫ���� */
	public void setRo_roleid(String ro_roleid){
		this.ro_roleid=ro_roleid;
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
	/** ȡ�ã�����Ա��� */
	public String getTe_operid(){
		return te_operid;
	}
	/** ���ã�����Ա��� */
	public void setTe_operid(String te_operid){
		this.te_operid=te_operid;
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** ����Ա���ɫ��ϵ */
	public Group_role_tellers(){
		primaryKeys.add("urt_id");
	}
}
