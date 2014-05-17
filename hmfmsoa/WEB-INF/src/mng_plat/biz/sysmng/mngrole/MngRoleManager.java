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
 * <p>��    ��: ����ά���ʽ����ϵͳ���ж�</p>
 * <p>��    ��: </p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2011-3-3 ����09:55:16</p>
 * @author ��Ʒ������
 * @version 1.1
 * MngRoleManager
 */
public class MngRoleManager extends BaseDeal {

	/**
	 * ����indexҳ����Ҫ��ȫ����ɫ����,����ָ����ɸѡ��ɫ
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
	 * ���ݽ�ɫ����ȡ���Ӧ�����
	 * 
	 * @param rolecode
	 *            String ��ɫ����
	 * @return Result ��ϸ�����
	 */
	public Result getRoleByCode(String rolecode) {

		Assert.hasText(rolecode, "����getRoleByCodeʱ,��ʹ�ñ�׼����!");
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
	 * ������ɫ
	 * 
	 * @param Role
	 *            ��ɫʵ��
	 */
	public void addRole(Role role) {

		Assert.notNull(role, "����addRoleʱ,��ʹ�ñ�׼ʵ����!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			KeyGenerator keygen = KeyGenerator.getInstance();
			long roleid = keygen.getNextKey("roleid"); // ��KeyTable���ɽ�ɫ����
			String rid = String.valueOf(roleid);
			if( rid.length() == 1 )
				rid = "00" + rid;
			else if( rid.length() == 2 )
				rid = "0" + rid;
			role.setRo_roleid(rid);
			role.setRo_crt_date(DateUtil.getSysDate());
			int i = dbo.add(role, db);
			if( i != 1 ) {
				throw new BusinessException("������ɫʧ��!��������!��ɫ����Ϊ[" + role.getRo_roleid() + "]");
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
	 * �༭��ɫ
	 * 
	 * @param Role
	 *            Role ��ɫʵ��
	 */
	public void editRole(Role role) {

		Assert.notNull(role, "����editRoleʱ,��ʹ�ñ�׼ʵ����!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			int i = dbo.update(role, db);
			if( i != 1 ) {
				throw new BusinessException("���Ľ�ɫʧ��!��������!��ɫ����Ϊ[" + role.getRo_roleid() + "]");
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
	 * ɾ����ɫ
	 * 
	 * @param Role
	 *            Role ��ɫʵ��
	 */
	public void delRole(Role role) {

		Assert.notNull(role, "����delRoleʱ,��ʹ�ñ�׼ʵ����!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			db.beginTrans();
			int i = dbo.delete(role, db);
			if( i != 1 ) {
				throw new BusinessException("ɾ����ɫʧ��!��������!��ɫ����Ϊ[" + role.getRo_roleid() + "]");
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
	 * �ж��Ƿ���ڲ���Ա��˽�ɫ�й���,��������ڹ�������ɾ��
	 * 
	 * @param Role
	 * @return
	 */
	public void isHasTellers(Role role) {

		Assert.notNull(role, "����isHasTellersʱ,��ʹ�ñ�׼ʵ����!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			dbo.addSql(" select * from tellers te,group_role_tellers gr where te.te_operid = gr.te_operid and ro_roleid = ? ");
			dbo.addParam(role.getRo_roleid());
			Result rs = dbo.query(db);
			if( rs.getRowCount() != 0 ) {
				throw new BusinessException("�ý�ɫ�в���Ա��ʹ��!������ٲ���!��ɫ����Ϊ[" + role.getRo_roleid() + "]");
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
	 * ��ȡ���еĲ˵��� author zhangcs
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
	 * ��ȡ�˵���Ϣ author zhangcs
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
	 * Ȩ�޷���
	 * 
	 * @param rolemenu
	 *            Role_menu ��ɫ�˵�ʵ��
	 * @param menuArray
	 *            List �˵�����
	 */
	public void allocate(Role_menu rolemenu, List menuArray) {

		Assert.notNull(menuArray, "����allocateʱ,ȷ���˵�������ȷ!");
		Assert.notNull(rolemenu, "����allocateʱ,��ʹ�ñ�׼ʵ����!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			db.beginTrans();
			dbo.delete(rolemenu, db); // ���ԭ�з���˵�,���������ڵ�һ�η���,���ж�ɾ���ļ�¼��
			for(int i = 0; i < menuArray.size(); i++) {
				String menuid = menuArray.get(i).toString();
				rolemenu.setMenu_id(menuid);
				int j = dbo.add(rolemenu, db);
				if( j != 1 ) {
					throw new BusinessException("����Ȩ��ʧ��,�˵�����Ϊ[" + rolemenu.getMenu_id() + "]");
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
