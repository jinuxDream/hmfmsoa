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
 * ϵͳ���Ʊ�
 */
public class Oper_system_name extends TableEntity
{
	private String batch_no;
	private String data_type;
	private String sn_id; //ϵͳ��ID
	private String sn_name; //ϵͳ����


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
	/** ȡ�ã�ϵͳ��ID */
	public String getSn_id(){
		return sn_id;
	}
	/** ���ã�ϵͳ��ID */
	public void setSn_id(String sn_id){
		this.sn_id=sn_id;
	}
	/** ȡ�ã�ϵͳ���� */
	public String getSn_name(){
		return sn_name;
	}
	/** ���ã�ϵͳ���� */
	public void setSn_name(String sn_name){
		this.sn_name=sn_name;
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** ϵͳ���Ʊ� */
	public Oper_system_name(){
		primaryKeys.add("batch_no");
		primaryKeys.add("data_type");
		primaryKeys.add("sn_id");
	}
}

