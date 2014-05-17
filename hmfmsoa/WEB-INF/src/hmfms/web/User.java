package hmfms.web;

import java.util.Map;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.ResultRow;

/**
 * <p>��    ��: ���Ŀ��</p>
 * <p>��    ��: </p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2010-12-13 ����11:26:24</p>
 * @author ��Ʒ������
 * @version 2.0
 * User
 */
public class User {

	private String tellID; //����ԱID
	private String tellName; //����Ա����
	private String deptType; //����Ա�������
	private String deptCode; //����Ա���ű���
	private String deptName; //����Ա��������
	private String userSessionID; //�û���¼���JSESSION
	private String branch_id; //����ID
	private String branch_code; //�������
	private String branch_name;//��������
	private String branch_level; //��������
	private String sysCode;//�������
	private String oper_tty;//��¼�ն�
	private String sect_id;//��Ŀ��Ϣid
	private String sect_code;//��Ŀ����
	private String ro_name;//��ɫ����
	
	
	public String getRo_name() {
	
		return ro_name;
	}



	
	public void setRo_name(String roName) {
	
		ro_name = roName;
	}

	private String roled_id;//��ɫID
	private String[] group_Arry;//��ID
	
	
	private Map<String, ResultRow>  mapTranDefineGroup;
	private Map<String, Result>  userFlowGroup;
	
	
	
	public Map<String, ResultRow> getMapTranDefineGroup() {
	
		return mapTranDefineGroup;
	}


	
	public void setMapTranDefineGroup(Map<String, ResultRow> mapTranDefineGroup) {
	
		this.mapTranDefineGroup = mapTranDefineGroup;
	}


	
	public Map<String, Result> getUserFlowGroup() {
	
		return userFlowGroup;
	}


	
	public void setUserFlowGroup(Map<String, Result> userFlowGroup) {
	
		this.userFlowGroup = userFlowGroup;
	}


	public String getRoled_id() {
	
		return roled_id;
	}

	
	public void setRoled_id(String roledId) {
	
		roled_id = roledId;
	}

	
	public String[] getGroup_Arry() {
	
		return group_Arry;
	}


	
	public void setGroup_Arry(String[] groupArry) {
	
		group_Arry = groupArry;
	}

	private UserAgent user_agent;
	
	
	
	public UserAgent getUser_agent() {
	
		return user_agent;
	}

	
	public void setUser_agent(UserAgent userAgent) {
	
		user_agent = userAgent;
	}

	public String getSect_id() {
		return sect_id;
	}

	public void setSect_id(String sect_id) {
		this.sect_id = sect_id;
	}

	public String getSect_code() {
		return sect_code;
	}

	public void setSect_code(String sect_code) {
		this.sect_code = sect_code;
	}

	public String getSect_name() {
		return sect_name;
	}

	public void setSect_name(String sect_name) {
		this.sect_name = sect_name;
	}

	private String sect_name;//��Ŀ����

	public String getOper_tty() {

		return oper_tty;
	}

	public void setOper_tty(String oper_tty) {

		this.oper_tty = oper_tty;
	}

	public String getBranch_name() {

		return branch_name;
	}

	public void setBranch_name(String branch_name) {

		this.branch_name = branch_name;
	}

	public String getSysCode() {

		return sysCode;
	}

	public void setSysCode(String sysCode) {

		this.sysCode = sysCode;
	}

	/**
	 * @return ���ص�ǰ��¼����Ա�������ŵı���.<br>
	 * ���ݲ������Ͳ�ͬ���ñ���ĺ��岻ͬ���ֱ�Ϊ��<br>
	 * ���ؾֱ��롢�����̱��롢С���������롢��ҵ������ҵ�����
	 */
	public String getDeptCode() {

		return deptCode;
	}

	/**
	 * @param deptCode The deptCode to set.
	 */
	public void setDeptCode(String deptCode) {

		this.deptCode = deptCode;
	}

	/**
	 * @return ���ص�ǰ��¼����Ա�������ŵ���������
	 */
	public String getDeptName() {

		return deptName;
	}

	/**
	 * @param deptName The deptName to set.
	 */
	public void setDeptName(String deptName) {

		this.deptName = deptName;
	}

	/**
	 * @return ���ص�ǰ��¼����Ա�������ŵ����ͣ�������<br>
	 * 00-�з��ؾ� 01-���ط��ؾ� 02-������ 03-��ҵ������ҵ<br>
	 * 04-ҵί�� 05-С������ 06-���ذ��´�
	 */
	public String getDeptType() {

		return deptType;
	}

	/**
	 * @param deptType The deptType to set.
	 */
	public void setDeptType(String deptType) {

		this.deptType = deptType;
	}

	/**
	 * @return ���ص�ǰ��¼����Ա�Ľ�ɫ����
	 */
	/**
	 * @return ���ص�ǰ��¼����Ա�ĵ�¼ID
	 */
	public String getTellID() {

		return tellID;
	}

	/**
	 * @param tellID The tellID to set.
	 */
	public void setTellID(String tellID) {

		this.tellID = tellID;
	}

	/**
	 * @return ���ص�ǰ��¼����Ա����������
	 */
	public String getTellName() {

		return tellName;
	}

	/**
	 * @param tellName The tellName to set.
	 */
	public void setTellName(String tellName) {

		this.tellName = tellName;
	}

	/**
	 * @return the userSessionID
	 */
	public String getUserSessionID() {

		return this.userSessionID;
	}

	/**
	 * @param userSessionID the userSessionID to set
	 */
	public void setUserSessionID(String userSessionID) {

		this.userSessionID = userSessionID;
	}

	public String getBranch_code() {

		return branch_code;
	}

	public void setBranch_code(String branch_code) {

		this.branch_code = branch_code;
	}

	public String getBranch_id() {

		return branch_id;
	}

	public void setBranch_id(String branch_id) {

		this.branch_id = branch_id;
	}

	public String getBranch_level() {

		return branch_level;
	}

	public void setBranch_level(String branch_level) {

		this.branch_level = branch_level;
	}

}
