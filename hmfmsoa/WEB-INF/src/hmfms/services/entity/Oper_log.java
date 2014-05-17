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
 * ������־��
 */
public class Oper_log extends TableEntity
{
	private Long work_id; //������־id
	private String oper_datetime; //��������
	private String te_operid; //����Ա���
	private String is_ok; //�Ƿ�ɹ�
	private String te_name; //����Ա��¼��
	private String log_type; //������־����
	private String oper_cont; //��������
	private String oper_reason; //����ԭ��
	private String oper_host; //��������IP
	private String oper_tty; //�����ն�IP
	private String oper_tty_agent; //�����ն��豸
	private String oper_tty_system; //�����ն�ϵͳ
	private String oper_browser; //���������
	private String remark; //��ע

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
	/** ȡ�ã��������� */
	public String getOper_datetime(){
		return oper_datetime;
	}
	/** ���ã��������� */
	public void setOper_datetime(String oper_datetime){
		this.oper_datetime=oper_datetime;
	}
	/** ȡ�ã�����Ա��� */
	public String getTe_operid(){
		return te_operid;
	}
	/** ���ã�����Ա��� */
	public void setTe_operid(String te_operid){
		this.te_operid=te_operid;
	}
	/** ȡ�ã��Ƿ�ɹ� */
	public String getIs_ok(){
		return is_ok;
	}
	/** ���ã��Ƿ�ɹ� */
	public void setIs_ok(String is_ok){
		this.is_ok=is_ok;
	}
	/** ȡ�ã�����Ա��¼�� */
	public String getTe_name(){
		return te_name;
	}
	/** ���ã�����Ա��¼�� */
	public void setTe_name(String te_name){
		this.te_name=te_name;
	}
	/** ȡ�ã�������־���� */
	public String getLog_type(){
		return log_type;
	}
	/** ���ã�������־���� */
	public void setLog_type(String log_type){
		this.log_type=log_type;
	}
	/** ȡ�ã��������� */
	public String getOper_cont(){
		return oper_cont;
	}
	/** ���ã��������� */
	public void setOper_cont(String oper_cont){
		this.oper_cont=oper_cont;
	}
	/** ȡ�ã�����ԭ�� */
	public String getOper_reason(){
		return oper_reason;
	}
	/** ���ã�����ԭ�� */
	public void setOper_reason(String oper_reason){
		this.oper_reason=oper_reason;
	}
	/** ȡ�ã���������IP */
	public String getOper_host(){
		return oper_host;
	}
	/** ���ã���������IP */
	public void setOper_host(String oper_host){
		this.oper_host=oper_host;
	}
	/** ȡ�ã������ն�IP */
	public String getOper_tty(){
		return oper_tty;
	}
	/** ���ã������ն�IP */
	public void setOper_tty(String oper_tty){
		this.oper_tty=oper_tty;
	}
	/** ȡ�ã������ն��豸 */
	public String getOper_tty_agent(){
		return oper_tty_agent;
	}
	/** ���ã������ն��豸 */
	public void setOper_tty_agent(String oper_tty_agent){
		this.oper_tty_agent=oper_tty_agent;
	}
	/** ȡ�ã������ն�ϵͳ */
	public String getOper_tty_system(){
		return oper_tty_system;
	}
	/** ���ã������ն�ϵͳ */
	public void setOper_tty_system(String oper_tty_system){
		this.oper_tty_system=oper_tty_system;
	}
	/** ȡ�ã���������� */
	public String getOper_browser(){
		return oper_browser;
	}
	/** ���ã���������� */
	public void setOper_browser(String oper_browser){
		this.oper_browser=oper_browser;
	}
	/** ȡ�ã���ע */
	public String getRemark(){
		return remark;
	}
	/** ���ã���ע */
	public void setRemark(String remark){
		this.remark=remark;
	}
	private Set primaryKeys = new HashSet();
	public boolean isPrimaryKey(String name){ return primaryKeys.contains(name); } 
	/** ������־�� */
	public Oper_log(){
		primaryKeys.add("work_id");
	}
}

