/**
 * 
 */
package mng_plat.biz.sysmng.mngmenu;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.AppSystemException;
import fd.exception.BusinessException;
import fd.util.Assert;
import hmfms.base.BaseDeal;
import hmfms.services.codes.MenuFunLevel;
import hmfms.services.entity.Menu_info;
import hmfms.services.entity.Role_menu;


/**
 * <p>标    题: 房屋维修资金管理系统银行端</p>
 * <p>描    述: </p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2011-3-3 下午09:55:50</p>
 * @author 产品开发部
 * @version 1.1
 * MngMenuManager
 */
public class MngMenuManager extends BaseDeal {

	/**
	 * 生成index页所需要的全部菜单数据,包括指定的筛选菜单
	 * 
	 * @param menucode
	 *            String 菜单编码
	 * @return Result
	 */
	public Result getAllMenu(String menucode) {

		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			dbo.addSql("SELECT   ms_sub.*, ms_pri.menu_name ms_parent_name");
			dbo.addSql(" FROM menu_info ms_sub LEFT JOIN menu_info ms_pri");
			dbo.addSql(" ON ms_sub.parent_id = ms_pri.menu_id ");
			dbo.addSql(" WHERE ms_sub.menu_id <> ' ' ");
			dbo.addLikeParam("ms_sub.menu_id", menucode);
			dbo.addSql(" ORDER BY ms_sub.menu_level, ms_sub.parent_id, ms_sub.pos_code ");
			db = new SQLExecutor();
			Result rs = dbo.query(db);
			return rs;
		}
		catch (Exception e){
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
	 * 根据菜单功能级别取对应结果集
	 * 
	 * @param mfl
	 *            菜单功能级别
	 * @return Result
	 */
	public Result getMenuByLevel(MenuFunLevel mfl) {

		Assert.notNull(mfl, "调用getMenuByLevel时,请使用标准功能!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			dbo.addSql("SELECT menu_info.*, NVL (s.isum, 0) AS isum FROM menu_info");
			dbo.addSql(" LEFT JOIN (SELECT   parent_id, COUNT (*) AS isum FROM menu_info");
//			dbo.addSql(" join (select ms_parent,count(*) as isum from menu_sum ");
			dbo.addSql(" GROUP BY parent_id) s ON s.parent_id = menu_info.menu_id ");
			dbo.addSql(" WHERE menu_level= ?");
			dbo.addParam(mfl);
			db = new SQLExecutor();
			Result rs = dbo.query(db);
			return rs;
		}
		catch (Exception e){
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
	 * 新增菜单
	 * 
	 * @param menu
	 *            菜单实体
	 */
	public void addMenu(Menu_info menu) {

		Assert.notNull(menu, "调用addMenu时,请使用标准实体类!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			//判断菜单是否存在
			dbo.addSql("select * from Menu_info where menu_id = ?");
			dbo.addParam(menu.getMenu_id());
			if(dbo.query(true).getRowCount()>0){
				throw new BusinessException(menu.getMenu_id()+"菜单已存在，请检查!");
			}
			Menu_info menu_sum = new Menu_info();
			menu_sum.setMenu_id(menu.getMenu_id());
			int i = dbo.add(menu, db);
			if( i != 1 ) {
				throw new BusinessException("新增菜单失败!请检查数据!菜单编码为[" + menu.getMenu_id() + "]");
			}
		}
		catch (Exception e){
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
	 * 根据菜单编码取其对应结果集
	 * 
	 * @param menucode
	 *            String 菜单编码
	 * @return Result 详细结果集
	 */
	public Result getMenuByCode(String menucode) {

		Assert.hasText(menucode, "调用getMenuByCode时,请使用标准功能!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			dbo.addSql("select * from menu_info where menu_id = ?");
			dbo.addParam(menucode);
			db = new SQLExecutor();
			Result rs = dbo.query(db);
			return rs;
		}
		catch (Exception e){
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
	 * 编辑菜单
	 * 
	 * @param menu
	 *            Menu_sum 菜单实体
	 */
	public void editMenu(Menu_info menu) {

		Assert.notNull(menu, "调用editMenu时,请使用标准实体类!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			int i = dbo.update(menu, db);
			if( i != 1 ) {
				throw new BusinessException("更改菜单失败!请检查数据!菜单编码为[" + menu.getMenu_id() + "]");
			}
		}
		catch (Exception e){
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
	 * 判断是否存在子菜单,即如果存在子菜单则不能删除
	 * 
	 * @param menu
	 * @return
	 */
	public void isHasSub(Menu_info menu) {

		Assert.notNull(menu, "调用isHasSub时,请使用标准实体类!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			dbo.addSql(" select * from menu_info where parent_id = ? ");
			dbo.addParam(menu.getMenu_id());
			Result rs = dbo.query(db);
			if( rs.getRowCount() == 1 ) {
				if( rs.getString(0, "parent_id").equals(menu.getMenu_id()) )
					return;
			}
			if( rs.getRowCount() != 0 )
				throw new BusinessException("该菜单存在下级菜单!请检查后再操作!菜单编码为[" + menu.getMenu_id() + "]");
		}
		catch (Exception e){
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
	 * 删除菜单
	 * 
	 * @param menu
	 *            Menu_sum 菜单实体
	 */
	public void delMenu(Menu_info menu) {

		Assert.notNull(menu, "调用delMenu时,请使用标准实体类!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			int i = dbo.delete(menu, db);
			if( i != 1 ) {
				throw new BusinessException("删除菜单失败!请检查数据!菜单编码为[" + menu.getMenu_id() + "]");
			}

			Role_menu rm = new Role_menu();
			rm.setMenu_id(menu.getMenu_id());
			dbo.delete(rm, db);
		}
		catch (Exception e){
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
