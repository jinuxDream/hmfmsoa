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
 * ��ɫ��
 */
public class Role extends TableEntity
{
	private String ro_roleid; //��ɫ����
	private String ro_name; //��ɫ����
	private String ro_desc; //����
	private String ro_crt_oper; //������
	private String ro_crt_date; //��������

	/** ȡ�ã���ɫ���� */
	public String getRo_roleid(){
		return ro_roleid;
	}
	/** ���ã���ɫ���� */
	public void setRo_roleid(String ro_roleid){
		this.ro_roleid=ro_roleid;
	}
	/** ȡ�ã���ɫ���� */
	public String getRo_name(){
		return ro_name;
	}
	/** ���ã���ɫ���� */
	public void setRo_name(String ro_name){
		this.ro_name=ro_name;
	}
	/** ȡ�ã����� */
	public String getRo_desc(){
		return ro_desc;
	}
	/** ���ã����� */
	public void setRo_desc(String ro_desc){
		this.ro_desc=ro_desc;
	}
	/** ȡ�ã������� */
	public String getRo_crt_oper(){
		return ro_crt_oper;
	}
	/** ���ã������� */
	public void setRo_crt_oper(String ro_crt_oper){
		this.ro_crt_oper=ro_crt_oper;
	}
	/** ȡ�ã��������� */
	public String getRo_crt_date(){
		return ro_crt_date;
	}
	/** ���ã��������� */
	public void setRo_crt_date(String ro_crt_date){
		this.ro_crt_date=ro_crt_date;
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** ��ɫ�� */
	public Role(){
		primaryKeys.add("ro_roleid");
	}
}

