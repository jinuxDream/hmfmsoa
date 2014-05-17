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
 * ��λ��Ϣ��
 */
public class Oper_org_info extends TableEntity
{
	private String batch_no;
	private String data_type;
	private Long org_id; //��λID
	private String dept_type; //��λ���
	private String org_name; //����


	/** ȡ�ã�������־ID */
	public String getBatch_no(){
		return batch_no;
	}
	/** ���ã�������־ID */
	public void setBatch_no(String batch_no){
		this.batch_no=batch_no;
	}
	/** ȡ�ã������������� */
	public String getData_type(){
		return data_type;
	}
	/** ���ã������������� */
	public void setData_type(String data_type){
		this.data_type=data_type;
	}
	/** ȡ�ã���λID */
	public Long getOrg_id(){
		return org_id;
	}
	/** ���ã���λID */
	public void setOrg_id(Long org_id){
		this.org_id=org_id;
	}
	/** ���ã���λID */
	public void setOrg_id(String org_id){
		if(!hmfms.util.ObjectUtil.isEmpty(org_id))
			this.org_id=new Long(org_id);
	}
	/** ȡ�ã���λ��� */
	public String getDept_type(){
		return dept_type;
	}
	/** ���ã���λ��� */
	public void setDept_type(String dept_type){
		this.dept_type=dept_type;
	}
	/** ȡ�ã����� */
	public String getOrg_name(){
		return org_name;
	}
	/** ���ã����� */
	public void setOrg_name(String org_name){
		this.org_name=org_name;
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** ��λ��Ϣ�� */
	public Oper_org_info(){
		primaryKeys.add("batch_no");
		primaryKeys.add("data_type");
		primaryKeys.add("org_id");
	}
}

