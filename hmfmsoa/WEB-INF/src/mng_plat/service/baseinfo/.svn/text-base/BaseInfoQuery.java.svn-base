package mng_plat.service.baseinfo;

import fd.commons.jdbc.Page;
import fd.commons.jdbc.Result;
import fd.commons.jdbc.ResultRow;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.AppSystemException;
import fd.exception.BusinessException;
import fd.util.Assert;
import hmfms.web.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> 标 题: 物业监管平台（二期） </p>
 * <p> 描 述: 基础信息查询共函数 </p>
 * <p> 版 权: Copyright (c) 2010 </p>
 * <p> 公 司: 上海泓智信息科技有限公司 </p>
 * <p> 创建时间: 2013-5-10 下午04:36:03  </p>
 * @author xchao
 * @version 1.1
 */
public class BaseInfoQuery {

	public User currUser = null;
	public SQLExecutor db = null;

	/**
	 * 构造函数
	 * 
	 * @param user {@link User} 当前登录的用户
	 * @param db {@link SQLExecutor} db
	 */
	public BaseInfoQuery(User user, SQLExecutor db) {

		this.currUser = user;
		this.db = db;
	}

	/**
	 * 获取BaseInfo实例
	 * 
	 * @param user {@link User} 当前登录的用户
	 * @param db {@link SQLExecutor} db
	 * @return new BaseInfoQuer实例
	 */
	public static BaseInfoQuery getInstance(User user, SQLExecutor db) {

		return new BaseInfoQuery(user, db);
	}

	public static BaseInfoQuery getInstance(SQLExecutor db) {

		return new BaseInfoQuery(null, db);
	}

	/**
	 * 区县列表，可用于下拉款进行显示
	 * 
	 * @return Result 区县列表
	 */
	public static Map<String, ResultRow> mapHpb = new HashMap<String, ResultRow>();
	public static Map<String, ResultRow> mapcityHpb = new HashMap<String, ResultRow>();
	public static Map<String, ResultRow> mapLoop = new HashMap<String, ResultRow>();
	public static Map<String, ResultRow> mapStreet = new HashMap<String, ResultRow>();
	public static Map<String, Result> mapStreetHpb = new HashMap<String, Result>();
	static {
		SQLExecutor db = null;
		try {
			/**
			 * 区县列表
			 */
			db = new SQLExecutor();
			SqlOperator dbo = new SqlOperator();
			Result rsHpb = BaseInfoQuery.getInstance(db).getHpbList();
			for(int j = 0; j < rsHpb.getRowCount(); j++) {
				mapHpb.put(rsHpb.getString(j, "hpb_id"), rsHpb.getResultRow(j));
			}
			
			dbo.clear();
			
			Result rsCityHpb = BaseInfoQuery.getInstance(db).getCityAndHpbList();
			for(int j = 0; j < rsCityHpb.getRowCount(); j++) {
				mapcityHpb.put(rsCityHpb.getString(j, "hpb_id"), rsCityHpb.getResultRow(j));
			}
			
			/**
			 * 环线列表，可用于下拉款进行显示
			 */
			dbo.clear();
			dbo.addSql("select * from loop");
			Result rsLoop = dbo.query(db);
			for(int j = 0; j < rsLoop.getRowCount(); j++) {
				mapLoop.put(rsLoop.getString(j, "loop_id"), rsLoop.getResultRow(j));
			}
			/**
			 * 街道，可用于下拉款进行显示
			 */
			dbo.clear();
			dbo.addSql("select * from street");
			Result rsStreet = dbo.query(db);
			for(int j = 0; j < rsStreet.getRowCount(); j++) {
				String key = rsStreet.getString(j, "street_id");
				String hpb_id = rsStreet.getString(j, "hpb_id");
				mapStreet.put(key, rsStreet.getResultRow(j));

				Result valueRs = mapStreetHpb.get(hpb_id);
				if( null == valueRs ) {
					valueRs = new Result(new ArrayList<Object>());
				}
				valueRs.add(rsStreet.getResultRow(j));
				mapStreetHpb.put(hpb_id, valueRs);
			}
		}
		catch(Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException(e);
		}
		finally {
			if( db != null )
				db.close();
		}
	}
	
	
	/**
	 * 获取区县列表信息，不包括3个合并的区县及市局信息
	 * 
	 * @return
	 */
	public Result getCityAndHpbList() {

		SqlOperator dbo = new SqlOperator();
		dbo.addSql("select * from hpb where hpb_id ");
		dbo.addSql(" not in ('310103000000','310119000000','310102000000') ");
		dbo.addSql(" order by hpb_id asc ");
		Result rsHpb = dbo.query(db);
		return rsHpb;
	}
	

	/**
	 * 获取区县列表信息，不包括3个合并的区县及市局信息
	 * 
	 * @return
	 */
	public Result getHpbList() {

		SqlOperator dbo = new SqlOperator();
		dbo.addSql("select * from hpb where hpb_id ");
		dbo.addSql(" not in ('99','310103000000','310119000000','310102000000') ");
		dbo.addSql(" order by hpb_id ");
		Result rsHpb = dbo.query(db);
		return rsHpb;
	}

	


	/**
	 * 查询物业公司下的业委会信息
	 * @return
	 */
	public String[] getCspByHoc() {

		Assert.notNull(currUser, "用户登录信息不能为空!");
		Assert.hasText(currUser.getDeptCode(), "登录员所属机构ID不能为空!");
		SqlOperator dbo = new SqlOperator();
		dbo.addSql(" select t1.* from hoc t1, sect t2, csp t3 where t2.hoc_id= t1.hoc_id ");
		dbo.addSql(" and t2.hpb_id=t3.hpb_id and t2.csp_id = t3.csp_id and t3.csp_id=?");
		dbo.addParam(currUser.getDeptCode());
		Result rsHoc = dbo.query(db);
		String[] hocList = new String[rsHoc.getRowCount()];
		for(int i = 0; i < rsHoc.getRowCount(); i++) {
			hocList[i] = rsHoc.getString(i, "hoc_id");
		}
		return hocList;
	}

	/***
	 * 查询小区的详细信息
	 * 
	 * @param page
	 * @param csp_id 物业id
	 * @param csp_code  物业编码
	 * @param csp_name 物业名称
	 * @return
	 */
	public Result getSectDetail(String sect_id) {

		Assert.notNull(currUser, "用户登录信息不能为空!");
		Assert.hasText(currUser.getDeptCode(), "登录员所属机构ID不能为空!");
		Assert.hasText(sect_id, "小区id不能为空!");
		SqlOperator dbo = new SqlOperator();
		dbo.addSql("select s.*,c.csp_name,cs.cs_name,ho.ho_id,ho.ho_name,h.hpb_id,h.hp_name,str.street_id,str.str_name,l.lp_name,hoc.hoc_name");
		dbo.addSql(",csm.csm_id,csm.csm_name,csm.csm_cert_code,csm.csm_phone");
		dbo.addSql("from sect  s left join csp c on s.csp_id=c.csp_id"); // 物业公司
		dbo.addSql("left join csp_sect cs on cs.cs_id=s.cs_id"); // 小区管理处
		dbo.addSql("left join hpb_off ho on ho.ho_id=s.ho_id");// 房管办
		dbo.addSql("left join hpb h on h.hpb_id=s.hpb_id"); // 区县
		dbo.addSql("left join street str on s.street_id=str.street_id");// 街道
		dbo.addSql("left join loop l on l.loop_id=s.loop_id");// 环线
		dbo.addSql("left join hoc  hoc on s.hoc_id=hoc.hoc_id ");// 业委会
		dbo.addSql("left join csp_sect_manger csm on cs.csm_id=csm.csm_id ");// 小区经理
		dbo.addSql("where s.sect_id = ?");
		dbo.addParam(sect_id);
		return dbo.query(db);
	}

}
