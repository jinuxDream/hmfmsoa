package mng_plat.biz.sysmng.init;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.AppSystemException;
import fd.exception.BusinessException;
import fd.util.Assert;
import hmfms.base.BaseDeal;
import hmfms.services.codes.DeptType;
import hmfms.services.codes.MenuFunLevel;
import hmfms.services.codes.OperStatus;
import hmfms.services.entity.Tellers;
import hmfms.util.ObjectUtil;
import hmfms.web.User;

import java.util.ArrayList;

import mng_plat.biz.sysmng.mngoper.MngOperManager;
import mng_plat.service.cfg.CfgSysPara;

public class LoginMng extends BaseDeal {

	/**
	 * 根据页面输入的操作员名称和密码进行判断操作员是否存在，并提取信息
	 * 
	 * @param strName
	 * @param strPass
	 * @return
	 */
	public Result getOperationInfo(String user_id, String pwd) throws Exception {

		/* 参数判断 */
		Assert.hasText(user_id, "当前操作员ID不能为空！");
		Assert.hasText(pwd, "当前操作员密码不能为空！");
		SQLExecutor db = null;
		try {
			SqlOperator dbo = new SqlOperator();
			db = new SQLExecutor();
			dbo.addSql("select * from tellers where te_operid = ? and te_passwd = ? ");
			dbo.addParam(user_id);
			dbo.addParam(pwd);
			Result rs = dbo.query(db);
			// 查询结果为空,秘密错误或者没有此操作员，登录失败
			if( rs.getRowCount() <= 0 ) {
				throw new BusinessException("此操作员在系统中不存在或用户名、密码错误，请检查!");
			}
			if( OperStatus.ZanTingShiYong.toString().equals(rs.getString(0, "TE_STATE").trim()) ) {
				throw new BusinessException("此操作员已被暂停使用，不能登录，请联系上级管理部门!");
			}
			// 状态检查
			String strState = rs.getString(0, "TE_STATE").trim();
			
			// 新加操作员
			if( OperStatus.XinJiaCaoZuoYuan.toString().equals(strState) ) {
				throw new BusinessException("此操作员为新加操作员，请激活后再登录!");
			}
			// 暂停使用
			else if( OperStatus.ZanTingShiYong.toString().equals(strState) ) {
				throw new BusinessException("此操作员已被暂停使用，不能登录，请联系上级管理部门!");
			}
			// 正在使用
			else if( OperStatus.ZhengZaiShiYong.toString().equals(strState) ) {
				if( !"false".equals(CfgSysPara.getParaValueByName("NEED_LOCK", "true").trim()) ) {
					throw new BusinessException("此操作员正在使用，请先解锁!");
				}
			}
			/* 组织机构判断 */
			String strOrgType = rs.getString(0, "te_dept_type");
			String strOrgId = rs.getString(0, "org_id");
			if( null == strOrgType || "".equals(strOrgType) ) {
				throw new BusinessException("提取操作员的部门信息出现异常，请稍后重试!");
			}
			if( null == strOrgId || "".equals(strOrgId) ) {
				throw new BusinessException("提取操作员的部门信息出现异常，请稍后重试!");
			}
			dbo.clear();
			dbo.addSql("select * from org_info where org_id = ? and dept_type=?");
			dbo.addParam(strOrgId);
			dbo.addParam(strOrgType);
			Result rsOrgInfo = dbo.query(db);
			if( null == rsOrgInfo ) {
				throw new BusinessException("网络错误，请稍后重试！");
			}
			else if( rsOrgInfo.getRowCount() <= 0 ) {
				throw new BusinessException("当前操作员所属单位不存在!");
			}
			rs.setObject(0, "dept_name", rsOrgInfo.getString(0, "org_name"));
			dbo.clear();
			return rs;
		}
		catch(Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException(e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}

	/**
	 * 根据页面输入的操作员名称和密码进行判断操作员是否为物业企业，并提取信息
	 * 
	 * @param strName
	 * @param strPass
	 * @author lints
	 * @return
	 */
	public Result getOperationInfoByCspOnly(String user_id, String pwd, boolean check) throws Exception {

		/* 参数判断 */
		Assert.hasText(user_id, "当前操作员ID不能为空！");
		Assert.hasText(pwd, "当前操作员密码不能为空！");
		SQLExecutor db = null;
		try {
			if( !user_id.toUpperCase().equals(pwd.toUpperCase()) ) {
				throw new BusinessException("该物业企业操作员在系统中不存在或用户名、密码错误，请检查!");
			}
			SqlOperator dbo = new SqlOperator();
			db = new SQLExecutor();
			/* 权限判断 */
			String strOrgType = DeptType.WuYeGongSi.toString();
			dbo.addSql("select * from csp where lower(csp_org_code) = lower(?)");// 修整物业企业组织机构代码显示问题
			dbo.addParam(user_id.toLowerCase());
			Result rs = dbo.query(db);
			if( !check )
				return rs;
			// 查询结果为空,角色或者没有此操作员，登录失败
			/*
			 * dbo.clear(); dbo.addSql("SELECT * FROM role_menu rm JOIN group_role_tellers grt ON grt.ro_roleid =  rm.ro_roleid ");
			 * dbo.addSql("join tellers t on t.te_operid=grt.te_operid  where  TE_DEPT_TYPE=? "); dbo.addParam(strOrgType); Result rsRole = dbo.query(db); if( null == rsRole ) { throw new
			 * BusinessException("网络错误，请稍后重试！"); } else if( rsRole.getRowCount() <= 0 ) { throw new BusinessException("当前操作员所属角色没有功能权限!"); }
			 */
			String strOrgId = rs.getString(0, "csp_id");
			if( "".equals(strOrgId) ) {
				throw new BusinessException("提取操作员的部门信息出现异常，请稍后重试!");
			}
			// 查询物业公司的id跟类型是否存在
			dbo.clear();
			dbo.addSql("select * from org_info where org_id = ? and dept_type = ?");
			dbo.addParam(strOrgId);
			dbo.addParam(strOrgType);
			Result rsOrgInfo = dbo.query(db);
			if( null == rsOrgInfo ) {
				throw new BusinessException("网络错误，请稍后重试！");
			}
			else if( rsOrgInfo.getRowCount() <= 0 ) {
				throw new BusinessException("当前操作员所属单位不存在!");
			}
			// 根据org_id查找操作员的tellers
			dbo.clear();
			dbo.addSql("select * from tellers where org_id=?");
			dbo.addParam(rs.getString(0, "csp_id"));
			Result rst = dbo.query(db);
			if( rst.isEmpty() ) {
				MngOperManager mng = new MngOperManager();
				Tellers tellers = new Tellers();
				tellers.setTe_name(rsOrgInfo.getString(0, "org_name"));
				tellers.setOrg_id(rs.getString(0, "csp_id"));
				tellers.setTe_dept_type(strOrgType);
				tellers.setTe_state(OperStatus.ZhengZaiShiYong.toString());
				User user = new User();
				user.setTellID("sys");
				mng.addOperator(tellers, user);
			}
			dbo.clear();
			dbo.addSql("select * from tellers where org_id=?");
			dbo.addParam(rs.getString(0, "csp_id"));
			rst = dbo.query(db);
			rst.setObject(0, "dept_name", rsOrgInfo.getString(0, "org_name"));
			return rst;
		}
		catch(Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException(e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}

	/**
	 * @param user_id
	 * @return
	 */
	public Result getMenuListByOper(User user, String roled_id, String[] group_id) {

		if( ObjectUtil.isEmpty(roled_id) && ObjectUtil.isEmpty(group_id) ) {
			return new Result(new ArrayList<Object>());
		}
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			Result rs0 = this.getMenu(roled_id, group_id, null, MenuFunLevel.YiJiCaiDan.toString(), db);
			for(int i = 0; i < rs0.getRowCount(); i++) {
				Result rs1 = this.getMenu(roled_id, group_id, rs0.getString(i, "menu_id"), MenuFunLevel.ErJiCaiDan.toString(), db);
				rs0.setObject(i, "submenu", rs1);
				for(int j = 0; j < rs1.getRowCount(); j++) {
					Result rs2 = this.getMenu(roled_id, group_id, rs1.getString(j, "menu_id"), MenuFunLevel.JuTiJiaoYi.toString(), db);
					rs1.setObject(j, "submenu", rs2);
				}
			}
			return rs0;
		}
		catch(Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException(e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}

	/**
	 * 提取菜单信息
	 * 
	 * @param strRoleCode
	 * @return
	 */
	private Result getMenu(String roled_id, String[] group_id, String parent, String level, SQLExecutor db) {

		SqlOperator dbo = new SqlOperator();
		dbo.addSql("select * from (");
		if( !ObjectUtil.isEmpty(group_id) ) {
			dbo.addSql(" select m.menu_id, m.menu_name, m.menu_level,m.parent_id, m.url_link, m.pos_code ");
			dbo.addSql(" from workgroup_menu w, menu_info m where w.menu_id = m.menu_id  ");
			dbo.addORParam("w.group_id", group_id);
			dbo.addEqualParam("m.menu_level", level);
			dbo.addEqualParam("m.parent_id", parent);
		}
		if( !ObjectUtil.isEmpty(group_id) && !ObjectUtil.isEmpty(roled_id) ) {
			dbo.addSql(" union all ");
		}
		if( !ObjectUtil.isEmpty(roled_id) ) {
			dbo.addSql(" select m.menu_id, m.menu_name, m.menu_level,m.parent_id, m.url_link, m.pos_code ");
			dbo.addSql(" from role_menu r, menu_info m where r.menu_id = m.menu_id ");
			dbo.addEqualParam("r.ro_roleid", roled_id);
			dbo.addEqualParam("m.menu_level", level);
			dbo.addEqualParam("m.parent_id", parent);
		}
		dbo.addSql(" ) f group by f.menu_id, f.menu_name, f.menu_level,f.parent_id, f.url_link, f.pos_code ");
		dbo.addSql(" ORDER BY pos_code ");
		Result rs = dbo.query(db);
		return rs;
	}

	/**
	 * 更新操作员状态为指定的状态
	 * 
	 * @param strTellID
	 * @param status
	 *          修改后的状态
	 * @param sessionid
	 *          ID
	 * @return 错误信息
	 */
	public String updateOperStatus(String strTellID, OperStatus status, String sessionid) {

		/* 参数判断 */
		Assert.hasText(strTellID, "操作员ID不能为空！");
		Assert.isTrue(null != status, "操作员状态不能为空！");
		SQLExecutor db = null;
		SqlOperator dbo = new SqlOperator();
		try {
			db = new SQLExecutor();
			Tellers formNew = new Tellers();
			formNew.setTe_operid(strTellID);
			formNew.setTe_state(status.toString());
			// 如果操作员修改为正在使用，sessionid不能为空
			if( OperStatus.ZhengZaiShiYong.equals(status) ) {
				Assert.hasText(strTellID, "session ID不能为空！");
			}
			formNew.setTe_sessionid(sessionid);
			int iResult = dbo.update(formNew, db);
			if( iResult != 1 ) {
				return "更新操作员状态失败！";
			}
			return null;
		}
		catch(Exception e) {
			return "系统错误";
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}

	/**
	 * 操作员修改密码
	 * 
	 * @param strOpID
	 *          (操作员ID）
	 * @param strOldPass
	 *          （操作员旧密码）
	 * @param strNewPass
	 *          （操作员新密码）
	 * @param strOldUnLock
	 *          （操作员旧解锁密码）
	 * @param strNewUnLock
	 *          （操作员新解锁密码）
	 * @return
	 */
	public String modiPass(String strOpID, String strOldPass, String strNewPass, String strOldUnLock, String strNewUnLock) {

		if( ObjectUtil.isEmpty(strOpID) )
			return "操作员ID不能为空！";
		if( ObjectUtil.isEmpty(strOldPass) )
			return "操作员旧密码不能为空";
		if( ObjectUtil.isEmpty(strNewPass) )
			return "操作员新密码不能为空";
		if( ObjectUtil.isEmpty(strOldUnLock) )
			return "操作员旧解锁密码不能为空";
		if( ObjectUtil.isEmpty(strNewUnLock) )
			return "操作员新解锁密码不能为空";
		SQLExecutor db = null;
		SqlOperator dbo = new SqlOperator();
		try {
			db = new SQLExecutor();
			dbo.addSql("select te_passwd, te_unlock from tellers ");
			dbo.addSql("where te_operid = ? ");
			dbo.addParam(strOpID);
			Result rsList = dbo.query(db);
			if( rsList.isEmpty() || rsList.getRowCount() <= 0 ) {
				return "数据库中已不存在ID号为[" + strOpID + "]的操作员";
			}
			String strPass = rsList.getString(0, "te_passwd");
			if( !strOldPass.equals(strPass) ) {
				return "输入的旧密码不正确，请确认！";
			}
			String strPass1 = rsList.getString(0, "te_unlock");
			if( !strOldUnLock.equals(strPass1) ) {
				return "输入的旧解锁密码不正确，请确认！";
			}
			Tellers formNew = new Tellers();
			formNew.setTe_operid(strOpID);
			formNew.setTe_passwd(strNewPass);
			formNew.setTe_unlock(strNewUnLock);
			int iResult = dbo.update(formNew, db);
			if( iResult <= 0 ) {
				return "更新操作员密码失败！";
			}
		}
		catch(Exception e) {
			return "系统错误";
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
		return null;
	}
	 

	   
}