package hmfms.web;

import java.util.Map;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.ResultRow;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: </p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:26:24</p>
 * @author 产品开发部
 * @version 2.0
 * User
 */
public class User {

	private String tellID; //操作员ID
	private String tellName; //操作员名称
	private String deptType; //操作员部门类别
	private String deptCode; //操作员部门编码
	private String deptName; //操作员部门名称
	private String userSessionID; //用户登录后的JSESSION
	private String branch_id; //机构ID
	private String branch_code; //机构编号
	private String branch_name;//机构名称
	private String branch_level; //机构级别
	private String sysCode;//区域代码
	private String oper_tty;//登录终端
	private String sect_id;//项目信息id
	private String sect_code;//项目编码
	private String ro_name;//角色名称
	
	
	public String getRo_name() {
	
		return ro_name;
	}



	
	public void setRo_name(String roName) {
	
		ro_name = roName;
	}

	private String roled_id;//角色ID
	private String[] group_Arry;//组ID
	
	
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

	private String sect_name;//项目名称

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
	 * @return 返回当前登录操作员所属部门的编码.<br>
	 * 根据部门类型不同，该编码的含义不同，分别为：<br>
	 * 房地局编码、开发商编码、小区管理处编码、物业管理企业编码等
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
	 * @return 返回当前登录操作员所属部门的中文名称
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
	 * @return 返回当前登录操作员所属部门的类型，包括：<br>
	 * 00-市房地局 01-区县房地局 02-开发商 03-物业管理企业<br>
	 * 04-业委会 05-小区管理处 06-房地办事处
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
	 * @return 返回当前登录操作员的角色名称
	 */
	/**
	 * @return 返回当前登录操作员的登录ID
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
	 * @return 返回当前登录操作员的中文名称
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
