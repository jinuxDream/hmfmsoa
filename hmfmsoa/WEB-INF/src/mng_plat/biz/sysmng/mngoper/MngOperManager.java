package mng_plat.biz.sysmng.mngoper;

import fd.commons.jdbc.Page;
import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.AppSystemException;
import fd.exception.BusinessException;
import fd.util.Assert;
import hmfms.base.BaseDeal;
import hmfms.services.codes.DeptType;
import hmfms.services.codes.OperStatus;
import hmfms.services.entity.Group_role_tellers;
import hmfms.services.entity.Tellers;
import hmfms.services.key.PrimayKeyGener;
import hmfms.util.DateUtil;
import hmfms.web.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mng_plat.service.cfg.CfgSysPara;

/**
 * 
 * <p>��    ��: ��ҵ���ƽ̨�����ڣ�</p>
 * <p>��    ��: ����Ա����</p>
 * <p>��    Ȩ: Copyright (c) 2014</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2014-1-8 ����10:45:30</p>
 * @author xchao
 * @version 1.1
 */
public class MngOperManager extends BaseDeal {

	/**
	 * @param page
	 * @param tellers
	 * @param user
	 * @return
	 */
	public Result getIndexResult(Page page, Tellers tellers, User user, String ro_roleid) {

		Assert.notNull(page, "����getIndexResult,��ʹ�ñ�׼��ҳ����!");
		Assert.notNull(tellers, "����getIndexResult,��ʹ�ñ�׼ʵ��!");
		Assert.notNull(user, "����getIndexResult,��ʹ�ñ�׼ʵ��!");
		Assert.hasText(user.getDeptType(), "����getIndexResult,��ǰ����Ա��Ϣ�쳣");
		Assert.hasText(user.getDeptCode(), "����getIndexResult,��ǰ����Ա��Ϣ�쳣");
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			String dept_type = user.getDeptType();
			String dept_code = user.getDeptCode();
			SqlOperator dbo = new SqlOperator();
			dbo.addSql("SELECT teller.*, oi.org_id orgId, oi.org_name");
			dbo.addSql("FROM   tellers teller,org_info oi");
			dbo.addSql(" ,group_role_tellers grt ");
			if( dept_type.equals(DeptType.QuJu.toString()) ) { //�����û�
				dbo.addSql(" ,( select t1.csp_id as te_depart_code from csp t1  join  sect t2 on t1.csp_id=t2.csp_id where t2.hpb_id=?");//��ҵ
				dbo.addSql(" union select ho_id as te_depart_code from hpb_off t1 where t1.hpb_id=?");//���ܰ�
				dbo.addSql(" union select t1.cs_id as te_depart_code from csp_sect t1, ");//С����������ҵ
				dbo.addSql(" csp t2, sect s where t1.csp_id=t2.csp_id AND s.csp_id = t2.csp_id  AND s.cs_id = t1.cs_id and s.hpb_id=? ) t1  ");
				dbo.addParam(dept_code);
				dbo.addParam(dept_code);
				dbo.addParam(dept_code);
			}
			if( dept_type.equals(DeptType.WuYeGongSi.toString()) ) { //��ҵ��ҵ�û�
				dbo.addSql(" ,( select t1.cs_id as te_depart_code from csp_sect t1 where t1.csp_id=?");
				dbo.addSql(" union select t1.hoc_id as te_depart_code from  ");
				dbo.addSql(" hoc t1, sect t2, csp t3 where t2.hoc_id= t1.hoc_id and t2.hpb_id=t3.hpb_id and t3.csp_id=?) t1 ");
				dbo.addParam(dept_code);
				dbo.addParam(dept_code);
			}
			dbo.addSql("WHERE  teller.org_id = oi.org_id");
			dbo.addSql(" and teller.te_operid = grt.te_operid ");
			/*if (dept_type.equals(DeptType.ShiJu.toString())&& user.getRoleName().contains("����")) {
				dbo.addSql("and teller.te_dept_type = ? ");
				dbo.addParam(DeptType.BaiYiHuiYuanDanWei.toString());
			}*/
			if( dept_type.equals(DeptType.QuJu.toString()) || dept_type.equals(DeptType.WuYeGongSi.toString()) ) { //�����û�������ҵ��ҵ�û�
				dbo.addSql(" and teller.org_id = t1.te_depart_code ");
			}
			dbo.addCondParam("teller.te_operid", "<>", "99");
			dbo.addCondParam("teller.te_state", "<>", OperStatus.ZhuXiao.toString());
			dbo.addCondParam("grt.ro_roleid", "<>", "XTGLY");
			dbo.addLikeParam("teller.te_name", tellers.getTe_name());
			dbo.addEqualParam("teller.TE_DEPT_TYPE", ro_roleid);
			dbo.addEqualParam("te_state", tellers.getTe_state());
			dbo.addEqualParam("teller.te_operid", tellers.getTe_operid());
			dbo.setOrder("order by teller.te_operid");
			Result rsMap = dbo.query(page, db);
			for(int i = 0; i < rsMap.getRowCount(); i++) {
				dbo.clear();
				dbo.addSql("select distinct  role.ro_roleid,ro_name from group_role_tellers grt join role on grt.ro_roleid = role.ro_roleid ");
				dbo.addSql(" where te_operid = ?");
				dbo.addParam(rsMap.getString(i, "te_operid"));
				Result rs = dbo.query(db);
				String roleName = "";
				for(int j = 0; j < rs.getRowCount(); j++) {
					roleName = roleName + "," + rs.getString(j, "ro_name");
				}
				if( roleName.length() != 0 ) {
					roleName = roleName.substring(1, roleName.length());
				}
				rsMap.setObject(i, "ro_name", roleName);
			}
			return rsMap;
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
	 * ��ѯ��λ��Ϣ
	 * @param page
	 * @param user
	 * @param org_name
	 * @param org_code
	 * @param sel_dept_type
	 */
	public Result getDeptResult(Page page, User user, String org_name, String org_id, String sel_dept_type) {

		Assert.notNull(page, "����getdept,��ʹ�ñ�׼��ҳ����!");
		Assert.notNull(user, "����getdept,��ʹ�ñ�׼ʵ��!");
		Assert.hasText(user.getDeptType(), "����getdept,��ǰ����Ա��Ϣ�쳣");
		Assert.hasText(user.getDeptCode(), "����getdept,��ǰ����Ա��Ϣ�쳣");
		SQLExecutor db = null;
		try {
			SqlOperator dbo = new SqlOperator();
			String dept_type = user.getDeptType();
			db = new SQLExecutor();
			String[] dept_arr = null;
			List<String> tell_list = new ArrayList<String>();
			//ȡ���û������µĲ���Ա
			if( dept_type.equals(DeptType.QuJu.toString()) ) { //�����û�
				//��ҵ��ҵ�û�
				if( sel_dept_type.equals(DeptType.WuYeGongSi.toString()) ) {
					dbo.clear();
					dbo.addSql("SELECT t1.*");
					dbo.addSql("  FROM sect s, csp t1");
					dbo.addSql(" WHERE s.csp_id = t1.csp_id AND s.hpb_id = ? ");
					dbo.addParam(user.getDeptCode());
					Result wyqy_rs = dbo.query(db);
					for(int i = 0; i < wyqy_rs.getRowCount(); i++) {//
						tell_list.add(wyqy_rs.getString(i, "csp_id"));
					}
				}
				else if( sel_dept_type.equals(DeptType.FangGuanBan.toString()) ) {
					dbo.clear();
					dbo.addSql(" select * from hpb_off t1 where t1.hpb_id=?");
					dbo.addParam(user.getDeptCode());
					Result fangba_rs = dbo.query(db);
					for(int i = 0; i < fangba_rs.getRowCount(); i++) {
						tell_list.add(fangba_rs.getString(i, "ho_id"));
					}
				}
				else if( sel_dept_type.equals(DeptType.XiaoQuGuanLiChu.toString()) ) {//С�������û�
					dbo.clear();
					dbo.addSql("SELECT t1.*");
					dbo.addSql(" FROM csp_sect t1, csp t2, sect s");
					dbo.addSql(" WHERE t1.csp_id = t2.csp_id");
					dbo.addSql("   AND s.csp_id = t2.csp_id");
					dbo.addSql("   AND s.cs_id = t1.cs_id");
					dbo.addSql("   AND s.hpb_id = ? ");
					dbo.addParam(user.getDeptCode());
					Result xqglc_rs = dbo.query(db);
					dbo.clear();
					for(int i = 0; i < xqglc_rs.getRowCount(); i++) {
						tell_list.add(xqglc_rs.getString(i, "cs_id"));
					}
				}
				dept_arr = new String[tell_list.size()];
				for(int i = 0; i < tell_list.size(); i++) {
					dept_arr[i] = tell_list.get(i).toString();
				}
			}
			else if( dept_type.equals(DeptType.WuYeGongSi.toString()) ) { //��ҵ��ҵ�û�
				//С�������û�
				dbo.clear();
				dbo.addSql(" select t1.* from csp_sect t1 where t1.csp_id=?");
				dbo.addParam(user.getDeptCode());
				Result xqglc_rs = dbo.query(db);
				dbo.clear();
				for(int i = 0; i < xqglc_rs.getRowCount(); i++) {
					tell_list.add(xqglc_rs.getString(i, "cs_id"));
				}
				//ҵί���û�  
				dbo.clear();
				dbo.addSql(" select t1.* from hoc t1, sect t2, csp t3 where t2.hoc_id= t1.hoc_id ");
				dbo.addSql(" and t2.hpb_id=t3.hpb_id and t2.csp_id = t3.csp_id and t3.csp_id=?");
				dbo.addParam(user.getDeptCode());
				Result yewehui_rs = dbo.query(db);
				for(int i = 0; i < yewehui_rs.getRowCount(); i++) {
					tell_list.add(yewehui_rs.getString(i, "hoc_id"));
				}
				dbo.clear();
				dept_arr = new String[tell_list.size()];
				for(int i = 0; i < tell_list.size(); i++) {
					dept_arr[i] = tell_list.get(i).toString();
				}
			}
			dbo.clear();
			dbo.addSql("SELECT * FROM org_info WHERE dept_type = ? ");
			dbo.addParam(sel_dept_type);
			Long org_idLong = null;
			try {
				org_idLong = new Long(org_id);
			}
			catch(Exception e) {
				org_idLong = null;
			}
			
			if( dept_type.equals(DeptType.QuJu.toString()) || dept_type.equals(DeptType.WuYeGongSi.toString()) ) { //�����û�������ҵ��ҵ�û�
				
				if(dept_arr.length==0){
					return new Result(new ArrayList());
				}//add by xt
				int count_ch_id = dept_arr.length / 1000;	//oracle in columns can not > 1000INT
				for (int i = 0; i < count_ch_id + 1; i++) {
					StringBuffer sql_buf = new StringBuffer();
					for (int j = 1000 * i; j < 1000 * (i + 1) && j < dept_arr.length; j++) {
						sql_buf.append(dept_arr[j] + ",");
					}
					sql_buf.deleteCharAt(sql_buf.length() - 1);	//���һ����ϵ��Id�����","
					if (i < 1) {
						dbo.addSql(" and ORG_ID  in ( ");
						dbo.addSql(sql_buf.toString());
						dbo.addSql(" )");
					}
					else {
						dbo.addSql("or ORG_ID in ( ");
						dbo.addSql(sql_buf.toString());
						dbo.addSql(" )");
					}
				}
			}
			dbo.addEqualParam("org_id", org_idLong);
			dbo.addLikeParam("org_name", org_name);
			Result rs = dbo.query(page, db);
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

	private static Map<String, String> mapRole = new HashMap<String, String>();
	static {
		mapRole.put(DeptType.ShiJu.toString(), "SJ");
		mapRole.put(DeptType.QuJu.toString(), "QXJ");
		mapRole.put(DeptType.KaiFaShang.toString(), "KFS");
		mapRole.put(DeptType.XiaoQuGuanLiChu.toString(), "XQGLC");
		mapRole.put(DeptType.FangGuanBan.toString(), "FGB");
		mapRole.put(DeptType.WuYeGongSi.toString(), "WYGS");
	}

	/**
	 * ��������Ա
	 * 
	 * @param tellers {@link Tellers} ����Աʵ��
	 * @param user {@link User} ��ǰ����Աʵ��
	 */
	public void addOperator(Tellers tellers, User user) {

		Assert.notNull(tellers, "����addOperator,��ʹ�ñ�׼ʵ��!");
		Assert.notNull(user, "����addOperator,��ʹ�ñ�׼ʵ��!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			db.beginTrans();
			String operid = PrimayKeyGener.getOperId(db);
			tellers.setTe_operid(operid);
			tellers.setTe_crt_date(DateUtil.getSysDate());
			tellers.setTe_passwd(CfgSysPara.getParaValueByName("OPER_INITPASS", "1"));
			tellers.setTe_unlock(CfgSysPara.getParaValueByName("OPER_INITPASS", "1"));
			tellers.setTe_crt_teller(user.getTellID());
			int i = dbo.add(tellers, db);
			if( i != 1 ) {
				throw new BusinessException("��������Աʧ��,����Ա����Ϊ[" + tellers.getTe_name() + "]!");
			}
			Group_role_tellers grt = new Group_role_tellers();
			grt.setRo_roleid(mapRole.get(tellers.getTe_dept_type()));
			grt.setUrt_id(PrimayKeyGener.getNextId(db));
			grt.setTe_operid(operid);
			i = dbo.add(grt, db);
			if( i != 1 ) {
				throw new BusinessException("��������Աʧ��,����Ա����Ϊ[" + tellers.getTe_name() + "]!");
			}
			db.commit();
		}
		catch(Exception e) {
			if( db != null ) {
				db.rollback();
			}
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
	 * ȡ����Ա��ص�ȫ����Ϣ
	 * 
	 * @param te_operid
	 *            {@link String} ����Ա���
	 * @return {@link Map}
	 */
	public Result getOperator(String te_operid) {

		Assert.hasText(te_operid, "����Ա��Ϣ��Ч!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			dbo.addSql("select * from tellers where te_operid = ?");
			dbo.addParam(te_operid);
			Result rs = dbo.query(db);
			dbo.clear();
			dbo.addSql("select distinct  role.ro_roleid,ro_name from group_role_tellers grt join role on grt.ro_roleid = role.ro_roleid ");
			dbo.addSql(" where te_operid = ?");
			dbo.addParam(rs.getString(0, "te_operid"));
			Result rsRol = dbo.query(db);
			String roleName = "";
			for(int j = 0; j < rsRol.getRowCount(); j++) {
				roleName = roleName + "," + rsRol.getString(j, "ro_name");
			}
			if( roleName.length() != 0 ) {
				roleName = roleName.substring(1, roleName.length());
			}
			rs.setObject(0, "ro_name", roleName);
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
	 * �༭����Ա
	 * 
	 * @param tellers
	 *            Tellers ����Աʵ��
	 */
	public void editOperator(String te_name, String te_remark, String te_operid) {

		Assert.hasText(te_operid, "����ԱID����Ϊ��");
		Assert.hasText(te_name, "����Ա���Ʋ���Ϊ��");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			dbo.addSql("update tellers set te_name = ?,te_remark = ? where te_operid = ?");
			dbo.addParam(te_name);
			dbo.addParam(te_remark);
			dbo.addParam(te_operid);
			if( dbo.execute(db) != 1 ) {
				throw new BusinessException("�������Աʧ��,����Ա����Ϊ[" + te_name + "]!");
			}
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
	 * ��������
	 *
	 */
	public void resetPassword(String operid) {

		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			Tellers tellago = new Tellers();
			tellago.setTe_operid(operid);
			Tellers telldb = (Tellers)dbo.get(tellago, db);
			telldb.setTe_passwd(CfgSysPara.getParaValueByName("OPER_INITPASS", "1"));
			telldb.setTe_unlock(CfgSysPara.getParaValueByName("OPER_INITPASS", "1"));
			if( dbo.update(telldb, db) != 1 ) {
				throw new BusinessException("[" + operid + "]" + "����Ա��������ʧ�ܣ�");
			}
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
	 * �޸Ľ�ɫ״̬
	 * @param te_operid
	 * @author xchao
	 */
	public void upedateTe_state(String te_operid, String flag) {

		Assert.hasText(te_operid, "����Ա��Ϣ��Ч!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			String isState = null;
			if( "0".equals(flag) ) {
				isState = OperStatus.ZhengChangShiYong.toString();
			}
			else {
				isState = OperStatus.ZanTingShiYong.toString();
			}
			dbo.addSql("select * from tellers where te_operid=?");
			dbo.addParam(te_operid);
			Result rs = dbo.query(db);
			if( isState.equals(OperStatus.ZhengChangShiYong.toString()) ) {
				if( !rs.getString(0, "te_state").equals(OperStatus.ZanTingShiYong.toString())
								&& !rs.getString(0, "te_state").equals(OperStatus.XinJiaCaoZuoYuan.toString()) ) {
					throw new BusinessException("�ò���Ա״̬�ǡ�" + OperStatus.getValue(rs.getString(0, "te_state")) + "������������");
				}
			}
			else {
				if( !rs.getString(0, "te_state").equals(OperStatus.ZhengChangShiYong.toString()) ) {
					throw new BusinessException("�ò���Ա״̬�ǡ�" + OperStatus.getValue(rs.getString(0, "te_state")) + "�������ܽ���");
				}
			}
			dbo.clear();
			dbo.addSql("update TELLERS set te_state = ?, te_remark='' where te_operid = ?");
			dbo.addParam(isState);
			dbo.addParam(te_operid);
			if( dbo.execute(db) != 1 )
				throw new BusinessException("���²���Ա��Ϣ����");
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
	 * ɾ������Ա
	 * 
	 * @param te_operid
	 *            {@link String} ����Ա���
	 */
	public void deleteOperator(String te_operid) {

		Assert.hasText(te_operid, "�޷�ȡ�ò���Ա��Ϣ!");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			dbo.addSql("update tellers set te_state = ? where te_operid = ?");
			dbo.addParam(OperStatus.ZhuXiao.toString());
			dbo.addParam(te_operid);
			int i = dbo.execute(db);
			if( i != 1 ) {
				throw new BusinessException("ɾ������Աʧ��,����Ա��Ϊ��[" + te_operid + "]!");
			}
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
}
