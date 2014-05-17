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
 * �������÷�Χ
 */
public class Oper_flow_scope extends TableEntity
{
	private String batch_no;
	private String data_type;
	private Long flow_id; //��������ID
	private String tran_type; //��������
	private Long flow_scope; //���÷�Χ


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
	/** ȡ�ã��������� */
	public String getTran_type(){
		return tran_type;
	}
	/** ���ã��������� */
	public void setTran_type(String tran_type){
		this.tran_type=tran_type;
	}
	/** ȡ�ã����÷�Χ */
	public Long getFlow_scope(){
		return flow_scope;
	}
	/** ���ã����÷�Χ */
	public void setFlow_scope(Long flow_scope){
		this.flow_scope=flow_scope;
	}
	/** ���ã����÷�Χ */
	public void setFlow_scope(String flow_scope){
		if(!hmfms.util.ObjectUtil.isEmpty(flow_scope))
			this.flow_scope=new Long(flow_scope);
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** �������÷�Χ */
	public Oper_flow_scope(){
		primaryKeys.add("batch_no");
		primaryKeys.add("data_type");
		primaryKeys.add("flow_id");
	}
}
