/**
 * 
 */
package mng_plat.biz.sysmng.mnggroup;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.commons.key.KeyGenerator;
import fd.exception.AppSystemException;
import fd.exception.BusinessException;
import fd.util.Assert;
import hmfms.base.BaseDeal;
import hmfms.services.codes.MenuFunLevel;
import hmfms.services.codes.WorkGroupType;
import hmfms.services.entity.Workgroup;
import hmfms.services.entity.Workgroup_menu;
import hmfms.services.key.PrimayKeyGener;
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
 * MngworkgroupManager
 */
public class MngGroupManager extends BaseDeal {

	/**
	 * 生成index页所需要的全部组数据,包括指定的筛选组
	 * 
	 * @param workgroupcode
	 * @return Result
	 */
	public Result getAllWorkgroup(String workgroupcode) {

		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			dbo.addSql("select * from workgroup");
			dbo.addSql("WHERE workgroup_type <> ?");
			dbo.addParam(WorkGroupType.ShenPiLiuChengZu.toString());
			dbo.addEqualParam("group_id", workgroupcode);
			dbo.addSql("ORDER BY group_id ASC ");
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
	 * 根据组编码取其对应结果集
	 * 
	 * @param workgroupcode
	 *            String 组编码
	 * @return Result 详细结果集
	 */
	public Result getWorkgroupByCode(String workgroupcode) {

		Assert.hasText(workgroupcode, "调用getworkgroupByCode时,请使用标准功能!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			dbo.addSql("select * from workgroup where group_id = ?");
			dbo.addParam(workgroupcode);
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
	 * 新增组
	 * 
	 * @param workgroup
	 *            组实体
	 */
	public void addgroup(Workgroup workgroup) {

		Assert.notNull(workgroup, "调用addworkgroup时,请使用标准实体类!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			String roleid = PrimayKeyGener.getGroupId(db); // 从KeyTable生成组编码
			String rid = String.valueOf(roleid);
			workgroup.setGroup_id(rid);
			workgroup.setWorkgroup_type(WorkGroupType.GongZuoYeWuZu.toString());
			int i = dbo.add(workgroup, db);
			if( i != 1 ) {
				throw new BusinessException("新增组失败!请检查数据!组编码为[" + workgroup.getGroup_id() + "]");
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
	 * 编辑组
	 * 
	 * @param workgroup workgroup 组实体
	 */
	public void editgroup(Workgroup workgroup) {

		Assert.notNull(workgroup, "调用editworkgroup时,请使用标准实体类!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			int i = dbo.update(workgroup, db);
			if( i != 1 ) {
				throw new BusinessException("更改组失败!请检查数据!组编码为[" + workgroup.getGroup_id() + "]");
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
	 * 删除组
	 * 
	 * @param workgroup
	 *            workgroup 组实体
	 */
	public void delworkgroup(Workgroup workgroup) {

		Assert.notNull(workgroup, "调用delworkgroup时,请使用标准实体类!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			db.beginTrans();
			int i = dbo.delete(workgroup, db);
			if( i != 1 ) {
				throw new BusinessException("删除组失败!请检查数据!组编码为[" + workgroup.getGroup_id() + "]");
			}
			Workgroup_menu workgroup_menu=new Workgroup_menu();
			workgroup_menu.setGroup_id(workgroup.getGroup_id());
			dbo.delete(workgroup_menu, db);
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
	 * 判断是否存在操作员与此组有关联,即如果存在关联则不能删除
	 * 
	 * @param workgroup
	 * @return
	 */
	public void isHasTellers(Workgroup workgroup) {

		Assert.notNull(workgroup, "调用isHasTellers时,请使用标准实体类!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			dbo.addSql(" select * from tellers te,group_role_tellers gr where te.te_operid = gr.te_operid and group_id = ? ");
			dbo.addParam(workgroup.getGroup_id());
			Result rs = dbo.query(db);
			if( rs.getRowCount() != 0 ) {
				throw new BusinessException("该组有操作员在使用!请检查后再操作!组编码为[" + workgroup.getGroup_id() + "]");
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
	public Result getMenuListByWorkgroup(String workgroupid) {

		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			Result rs0 = this.getMenu(workgroupid, null, MenuFunLevel.YiJiCaiDan.toString(), db);
			for(int i = 0; i < rs0.getRowCount(); i++) {
				Result rs1 = this.getMenu(workgroupid, rs0.getString(i, "menu_id"), MenuFunLevel.ErJiCaiDan.toString(), db);
				rs0.setObject(i, "submenu", rs1);
				for(int j = 0; j < rs1.getRowCount(); j++) {
					Result rs2 = this.getMenu(workgroupid, rs1.getString(j, "menu_id"), MenuFunLevel.JuTiJiaoYi.toString(), db);
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
	 * @param strworkgroupCode
	 * @return
	 */
	private Result getMenu(String strWorkgroupCode, String parent, String level, SQLExecutor db) {

		// SQLExecutor db=null;
		SqlOperator dbo = new SqlOperator();
		dbo.addSql(" SELECT *   FROM (");
		dbo.addSql(" SELECT   m.menu_id, m.menu_name, m.menu_level, m.parent_id, m.url_link, ");
		dbo.addSql("   m.menu_desc, r.menu_id rcode, m.menu_id mcode ");
		dbo.addSql(" FROM menu_info m  LEFT JOIN (SELECT * FROM  workgroup_menu WHERE group_id = ?) r");
		dbo.addSql(" ON m.menu_id = r.menu_id ");
		dbo.addParam(strWorkgroupCode);
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
	 * @param workgroupmenu
	 *            workgroup_menu 组菜单实体
	 * @param menuArray
	 *            List 菜单数组
	 */
	public void allocate(Workgroup_menu workgroupmenu, List menuArray) {

		Assert.notNull(menuArray, "调用allocate时,确保菜单分配正确!");
		Assert.notNull(workgroupmenu, "调用allocate时,请使用标准实体类!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			db.beginTrans();
			dbo.delete(workgroupmenu, db); // 清空原有分配菜单,方便适用于第一次分配,不判断删除的记录数
			for(int i = 0; i < menuArray.size(); i++) {
				String menuid = menuArray.get(i).toString();
				workgroupmenu.setMenu_id(menuid);
				workgroupmenu.setWorkmenu_id(PrimayKeyGener.getNextId(db));
				int j = dbo.add(workgroupmenu, db);
				if( j != 1 ) {
					throw new BusinessException("分配权限失败,菜单编码为[" + workgroupmenu.getMenu_id() + "]");
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
