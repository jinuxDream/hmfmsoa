/**
 * 
 */
package mng_plat.biz.sysmng.mngrole;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.commons.key.KeyGenerator;
import fd.exception.AppSystemException;
import fd.exception.BusinessException;
import fd.util.Assert;
import hmfms.base.BaseDeal;
import hmfms.services.codes.MenuFunLevel;
import hmfms.services.entity.Role;
import hmfms.services.entity.Role_menu;
import hmfms.util.DateUtil;
import hmfms.util.ObjectUtil;

import java.util.List;

/**
 * <p>标    题: 房屋维修资金管理系统银行端</p>
 * <p>描    述: </p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2011-3-3 下午09:55:16</p>
 * @author 产品开发部
 * @version 1.1
 * MngRoleManager
 */
public class MngRoleManager extends BaseDeal {

	/**
	 * 生成index页所需要的全部角色数据,包括指定的筛选角色
	 * 
	 * @param rolecode
	 * @return Result
	 */
	public Result getAllRole(String rolecode) {

		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			dbo.addSql("select * from role");
			dbo.addSql("WHERE ro_roleid <> ?");
			dbo.addParam("XTGL");
			dbo.addEqualParam("ro_roleid", rolecode);
			dbo.addSql("ORDER BY ro_roleid ASC ");
			db = new SQLExecutor();
			Result rs = dbo.query(db);
			return rs;
		}
		catch (Exception e) {
	        if(e instanceof BusinessException) throw (BusinessException)e;
	        else throw new AppSystemException(e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}

	/**
	 * 根据角色编码取其对应结果集
	 * 
	 * @param rolecode
	 *            String 角色编码
	 * @return Result 详细结果集
	 */
	public Result getRoleByCode(String rolecode) {

		Assert.hasText(rolecode, "调用getRoleByCode时,请使用标准功能!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			dbo.addSql("select * from role where ro_roleid = ?");
			dbo.addParam(rolecode);
			db = new SQLExecutor();
			Result rs = dbo.query(db);
			return rs;
		}
		catch (Exception e) {
	        if(e instanceof BusinessException) throw (BusinessException)e;
	        else throw new AppSystemException(e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}

	/**
	 * 新增角色
	 * 
	 * @param Role
	 *            角色实体
	 */
	public void addRole(Role role) {

		Assert.notNull(role, "调用addRole时,请使用标准实体类!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			KeyGenerator keygen = KeyGenerator.getInstance();
			long roleid = keygen.getNextKey("roleid"); // 从KeyTable生成角色编码
			String rid = String.valueOf(roleid);
			if( rid.length() == 1 )
				rid = "00" + rid;
			else if( rid.length() == 2 )
				rid = "0" + rid;
			role.setRo_roleid(rid);
			role.setRo_crt_date(DateUtil.getSysDate());
			int i = dbo.add(role, db);
			if( i != 1 ) {
				throw new BusinessException("新增角色失败!请检查数据!角色编码为[" + role.getRo_roleid() + "]");
			}
		}
		catch (Exception e) {
	        if(e instanceof BusinessException) throw (BusinessException)e;
	        else throw new AppSystemException(e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}

	/**
	 * 编辑角色
	 * 
	 * @param Role
	 *            Role 角色实体
	 */
	public void editRole(Role role) {

		Assert.notNull(role, "调用editRole时,请使用标准实体类!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			int i = dbo.update(role, db);
			if( i != 1 ) {
				throw new BusinessException("更改角色失败!请检查数据!角色编码为[" + role.getRo_roleid() + "]");
			}
		}
		catch (Exception e) {
	        if(e instanceof BusinessException) throw (BusinessException)e;
	        else throw new AppSystemException(e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}

	/**
	 * 删除角色
	 * 
	 * @param Role
	 *            Role 角色实体
	 */
	public void delRole(Role role) {

		Assert.notNull(role, "调用delRole时,请使用标准实体类!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			db.beginTrans();
			int i = dbo.delete(role, db);
			if( i != 1 ) {
				throw new BusinessException("删除角色失败!请检查数据!角色编码为[" + role.getRo_roleid() + "]");
			}
			Role_menu role_menu=new Role_menu();
			role_menu.setRo_roleid(role.getRo_roleid());
			dbo.delete(role_menu, db);
			db.commit();
		}
		catch (Exception e) {
			if (db != null) db.rollback();
	        if(e instanceof BusinessException) throw (BusinessException)e;
	        else throw new AppSystemException(e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}

	/**
	 * 判断是否存在操作员与此角色有关联,即如果存在关联则不能删除
	 * 
	 * @param Role
	 * @return
	 */
	public void isHasTellers(Role role) {

		Assert.notNull(role, "调用isHasTellers时,请使用标准实体类!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			dbo.addSql(" select * from tellers te,group_role_tellers gr where te.te_operid = gr.te_operid and ro_roleid = ? ");
			dbo.addParam(role.getRo_roleid());
			Result rs = dbo.query(db);
			if( rs.getRowCount() != 0 ) {
				throw new BusinessException("该角色有操作员在使用!请检查后再操作!角色编码为[" + role.getRo_roleid() + "]");
			}
		}
		catch (Exception e) {
	        if(e instanceof BusinessException) throw (BusinessException)e;
	        else throw new AppSystemException(e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}

	/**
	 * 获取所有的菜单项 author zhangcs
	 * 
	 * @param user_id
	 * @return
	 */
	public Result getMenuListByRole(String roleid) {

		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			Result rs0 = this.getMenu(roleid, null, MenuFunLevel.YiJiCaiDan.toString(), db);
			for(int i = 0; i < rs0.getRowCount(); i++) {
				Result rs1 = this.getMenu(roleid, rs0.getString(i, "menu_id"), MenuFunLevel.ErJiCaiDan.toString(), db);
				rs0.setObject(i, "submenu", rs1);
				for(int j = 0; j < rs1.getRowCount(); j++) {
					Result rs2 = this.getMenu(roleid, rs1.getString(j, "menu_id"), MenuFunLevel.JuTiJiaoYi.toString(), db);
					rs1.setObject(j, "submenu", rs2);
				}
			}
			return rs0;
		}
		catch (Exception e) {
	        if(e instanceof BusinessException) throw (BusinessException)e;
	        else throw new AppSystemException(e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}

	/**
	 * 提取菜单信息 author zhangcs
	 * 
	 * @param strRoleCode
	 * @return
	 */
	private Result getMenu(String strRoleCode, String parent, String level, SQLExecutor db) {

		// SQLExecutor db=null;
		SqlOperator dbo = new SqlOperator();
		dbo.addSql(" SELECT *   FROM (");
		dbo.addSql(" SELECT   m.menu_id, m.menu_name, m.menu_level, m.parent_id, m.url_link, ");
		dbo.addSql("   m.menu_desc, r.menu_id rcode, m.menu_id mcode ");
		dbo.addSql(" FROM menu_info m  LEFT JOIN (SELECT * FROM role_menu WHERE ro_roleid = ?) r");
		dbo.addSql(" ON m.menu_id = r.menu_id ");
		dbo.addParam(strRoleCode);
		StringBuffer bf = new StringBuffer();
		if( !ObjectUtil.isEmpty(level) ) {
			bf.append(" m.menu_level=?");
			dbo.addParam(level);
		}
		if( !ObjectUtil.isEmpty(parent) ) {

			if( bf.length() > 1 )
				bf.append("and");
			bf.append(" m.parent_id=?");
			dbo.addParam(parent);
		}
		if( bf.length() > 1 ) {
			dbo.addSql(" where ");
			dbo.addSql(bf.toString());
		}
		dbo.addSql("ORDER BY pos_code) selected ");
		Result rs = dbo.query(db);
		return rs;
	}

	/**
	 * 权限分配
	 * 
	 * @param rolemenu
	 *            Role_menu 角色菜单实体
	 * @param menuArray
	 *            List 菜单数组
	 */
	public void allocate(Role_menu rolemenu, List menuArray) {

		Assert.notNull(menuArray, "调用allocate时,确保菜单分配正确!");
		Assert.notNull(rolemenu, "调用allocate时,请使用标准实体类!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			db.beginTrans();
			dbo.delete(rolemenu, db); // 清空原有分配菜单,方便适用于第一次分配,不判断删除的记录数
			for(int i = 0; i < menuArray.size(); i++) {
				String menuid = menuArray.get(i).toString();
				rolemenu.setMenu_id(menuid);
				int j = dbo.add(rolemenu, db);
				if( j != 1 ) {
					throw new BusinessException("分配权限失败,菜单编码为[" + rolemenu.getMenu_id() + "]");
				}
			}
			db.commit();
		}
		catch (Exception e) {
			if (db != null) db.rollback();
	        if(e instanceof BusinessException) throw (BusinessException)e;
	        else throw new AppSystemException(e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}
}
