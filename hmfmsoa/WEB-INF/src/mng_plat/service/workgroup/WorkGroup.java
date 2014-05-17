package mng_plat.service.workgroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hmfms.services.codes.WorkGroupType;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.BusinessException;
import fd.util.Assert;
import hmfms.util.ObjectUtil;
import hmfms.web.User;

public class WorkGroup {

	public User currUser = null;
	public SQLExecutor db = null;

	/**
	 * 构造函数
	 * @param user {@link User} 当前登录的用户
	 * @param db {@link SQLExecutor} db
	 */
	public WorkGroup(User user, SQLExecutor db) {

		this.currUser = user;
		this.db = db;
	}

	/**
	 * 获取BaseInfo实例
	 * @param user {@link User} 当前登录的用户
	 * @param db {@link SQLExecutor} db
	 * @return new BaseInfoQuer实例
	 */
	public static WorkGroup getInstance(User user, SQLExecutor db) {

		return new WorkGroup(user, db);
	}

	/**
	 * 获取BaseInfo实例
	 * @param user {@link User} 当前登录的用户
	 * @return new BaseInfoQuer实例
	 */
	public static WorkGroup getInstance(User user) {

		return new WorkGroup(user, null);
	}

	/**
	 * 获取BaseInfo实例
	 * @return new BaseInfoQuer实例
	 */
	public static WorkGroup getInstance() {

		return new WorkGroup(null, null);
	}

	/**
	 * 获取用户归属的工作组和角色
	 * @return {@link Map} 返回map，格式为Map<"ro_roleid\group_id",List<组或角色ID>>
	 */
	public Map<String, List<String>> getTellerGroupOrRole() {

		Assert.hasText(currUser.getTellID(), "用户编号不能为空");
		SqlOperator dbo = new SqlOperator();
		dbo.addSql("select * from group_role_tellers where te_operid = ?");
		dbo.addParam(currUser.getTellID());
		Result rs = (db == null) ? dbo.query(true) : dbo.query(db);
		if( rs.isEmpty() ) {
			throw new BusinessException("用户未分配权限，请联系管理员");
		}
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> roleList = new ArrayList<String>();
		List<String> groupList = new ArrayList<String>();
		for(int i = 0; i < rs.getRowCount(); i++) {
			String role_id = rs.getString(i, "ro_roleid");
			String group_id = rs.getString(i, "group_id");
			if( !ObjectUtil.isEmpty(role_id) ) {
				roleList.add(role_id);
			}
			if( !ObjectUtil.isEmpty(group_id) ) {
				groupList.add(group_id);
			}
		}
		map.put("ro_roleid", roleList);
		map.put("group_id", groupList);
		return map;
	}

	/**
	 * 获取用户归属的工作组和角色
	 * @return 返回角色信息和组信息
	 */
	@SuppressWarnings("static-access")
	public Map<String, Result> getTellerGroupOrRoleByName() {

		Map<String, List<String>> map = this.getInstance(currUser, db).getTellerGroupOrRole();
		List<String> roleId_list = map.get("ro_roleid");
		String[] roleId = new String[roleId_list.size()];
		for(int i = 0; i < roleId_list.size(); i++) {
			roleId[i] = roleId_list.get(i);
		}
		List<String> groupId_list = map.get("group_id");
		String[] groupId = new String[groupId_list.size()];
		for(int i = 0; i < groupId_list.size(); i++) {
			groupId[i] = groupId_list.get(i);
		}
		Result rsRole = new Result(new ArrayList<Object>());
		if(!ObjectUtil.isEmpty(roleId)){
			SqlOperator dbo = new SqlOperator();
			dbo.addSql("select * from role where");
			dbo.addORParam("ro_roleid", roleId);
			rsRole = (db == null) ? dbo.query(true) : dbo.query(db);
		}
		
		Result rsGroup = getWorkGroup(WorkGroupType.GongZuoYeWuZu, groupId);

		Map<String, Result> mapRs = new HashMap<String, Result>();
		mapRs.put("rsRole", rsRole);
		mapRs.put("rsGroup", rsGroup);
		return mapRs;
	}

	/**
	 *  查询工作住
	 * @param workGroupType
	 * @param groupId
	 * @return
	 */
	public Result getWorkGroup(WorkGroupType workGroupType, String[] groupId) {

		Result rs = new Result(new ArrayList<Object>());
		if( !ObjectUtil.isEmpty(groupId) ) {
			SqlOperator dbo = new SqlOperator();
			dbo.addSql("select * from workgroup where workgroup_type = ?");
			dbo.addParam(workGroupType);
			dbo.addORParam("group_id", groupId);
			rs = (db == null) ? dbo.query(true) : dbo.query(db);
		}
		return rs;
	}
}
