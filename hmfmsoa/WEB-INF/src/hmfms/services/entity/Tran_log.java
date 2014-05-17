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
 * ������־������
 */
public class Tran_log extends TableEntity
{
	private String batch_no; //������
	private Long work_id; //������־id

	/** ȡ�ã������� */
	public String getBatch_no(){
		return batch_no;
	}
	/** ���ã������� */
	public void setBatch_no(String batch_no){
		this.batch_no=batch_no;
	}
	/** ȡ�ã�������־id */
	public Long getWork_id(){
		return work_id;
	}
	/** ���ã�������־id */
	public void setWork_id(Long work_id){
		this.work_id=work_id;
	}
	/** ���ã�������־id */
	public void setWork_id(String work_id){
		if(!hmfms.util.ObjectUtil.isEmpty(work_id))
			this.work_id=new Long(work_id);
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** ������־������ */
	public Tran_log(){
		primaryKeys.add("batch_no");
		primaryKeys.add("work_id");
	}
}

