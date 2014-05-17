package mng_plat.service.baseinfo;

import fd.commons.jdbc.Page;
import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.BusinessException;
import fd.util.Assert;
import hmfms.services.codes.DeptType;
import hmfms.services.codes.OperDataType;
import hmfms.services.codes.TranStatus;
import hmfms.services.codes.TranType;
import hmfms.services.entity.Trade;
import hmfms.web.User;
import mng_plat.service.cfgflow.CfgFlowEngine;

public class TradeInfoQuery {

	public User currUser = null;
	public SQLExecutor db = null;

	/**
	 * 构造函数
	 * @param user {@link User} 当前登录的用户
	 * @param db {@link SQLExecutor} db
	 */
	private TradeInfoQuery(User user, SQLExecutor db) {

		this.currUser = user;
		this.db = db;
	}

	/**
	 * 获取TradeInfo实例
	 * @param user {@link User} 当前登录的用户
	 * @param db {@link SQLExecutor} db
	 * @return new BaseInfoQuer实例
	 */
	public static TradeInfoQuery getInstance(User user, SQLExecutor db) {

		return new TradeInfoQuery(user, db);
	}

	public static TradeInfoQuery getInstance(SQLExecutor db) {

		return new TradeInfoQuery(null, db);
	}

	public static Trade getTradeNoDb(String batchNo) {

		Assert.hasText(batchNo, "业务编号不能为空。");
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			return TradeInfoQuery.getInstance(db).getTrade(batchNo);
		}
		catch(Exception e) {
			throw new BusinessException(e);
		}
		finally {
			if( db != null )
				db.close();
		}
	}

	/**
	 * 查询交易信息
	 * @param batchNo
	 * @return
	 */
	public Trade getTrade(String batchNo) {

		Assert.hasText(batchNo, "申请编号不能为空。");

		SqlOperator dbo = new SqlOperator();
		dbo.addSql("select trade.* from trade ");
		dbo.addSql("  where trade.batch_no = ? ");
		//dbo.addSql("and trade.tran_status <> ? ");
		dbo.addParam(batchNo);
		//dbo.addParam(TranStatus.CheXiao.toString());
		Result rs = (db == null) ? dbo.query(true) : dbo.query(db);
		if( rs.isEmpty() ) {
			throw new BusinessException("未查询到申请编号[" + batchNo + "]所对应的交易。");
		}
		return (Trade)rs.toEntity(Trade.class, 0);
	}

	/**
	 * 查询交易首页面信息
	 * @param trantype {@link String} 交易类型
	 * @param selTranStatus {@link String} 页面上选择的交易状态
	 * @param page
	 * @return
	 */
	public Result getTradeIndex(TranType trantype, String selTranStatus, Page page) {

		Assert.hasText(trantype.toString(), "交易类型不能为空");
		String[] tranStatusFlow = CfgFlowEngine.getTranStatusOrFlowScope(currUser, trantype, CfgFlowEngine.allFlow);
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < tranStatusFlow.length; i++) {
			String tranTot = tranStatusFlow[i];
			String tranStatus = tranTot.substring(0, 2);
			String flowScope = tranTot.substring(2, tranTot.length());
			if( TranStatus.BianJi.toString().equals(tranStatus) ) {
				sb.append("(tran_status = '").append(tranStatus).append("'");
				sb.append(" and te_operid = '").append(currUser.getTellID()).append("'");
				sb.append(" and flow_scope = '").append(flowScope).append("') or");
			}
			else {
				sb.append("(tran_status = '").append(tranStatus).append("'");
				sb.append(" and flow_scope = '").append(flowScope).append("') or");
			}
		}
		String sqlSb = sb.toString();
		sqlSb = sqlSb.substring(0, sqlSb.lastIndexOf("or"));
		SqlOperator dbo = new SqlOperator();

		String tableName = "";
		StringBuffer sqlFormat = new StringBuffer();

//		/*小区信息新增，小区信息变更*/
//		if( TranType.XiaoQuXinXiXinZeng.toString().equals(trantype.toString()) || TranType.XiaoQuXinXiBianGeng.toString().equals(trantype.toString()) ) {
//			tableName = "oper_sect";
//			sqlFormat.append("select hpb.*,trade.*,ohou.*,os.sect_id,os.st_code,os.st_name_frst,os.st_addr_frst,te.te_name");
//			sqlFormat.append(" from trade trade join oper_sect os on trade.batch_no = os.batch_no  ");
//		}
//		 
	
//		else {
//			throw new BusinessException("该方法不支持这样的类型，请联系系统管理员");
//		}

		dbo.addSql(sqlFormat.toString());
		dbo.addSql("left join hpb on os.hpb_id = hpb.hpb_id ");
		dbo.addSql(" join tellers te on trade.te_operid = te.te_operid");
		dbo.addSql(" left join ( select count(1) totcount,batch_no btn from " + tableName + "");
		dbo.addSql(" where data_type in(?,?)  ");
		dbo.addParam(OperDataType.GengGaiHouShuJu.toString());
		dbo.addParam(OperDataType.ZhuCeShuJu.toString());
		dbo.addSql("  group by batch_no) ohou on os.batch_no = ohou.btn ");

		dbo.addSql(" where TRAN_TYPE = ? ");
		dbo.addParam(trantype.toString());
		dbo.addSql(" and os.data_type <> ? ");
		dbo.addParam(OperDataType.GengGaiQianShuJu.toString());

		
		
	
		dbo.addEqualParam("tran_status", selTranStatus);
		//dbo.addORParam("tran_status" + DBDialectUtil.getSplitJoin() + "flow_scope", tranStatusFlow);
		dbo.addSql(" and (");
		dbo.addSql(sqlSb);
		dbo.addSql(" ) ");
		dbo.addSql(" order by trade.batch_no desc");
		if( page != null ) {
			return (db == null) ? dbo.query(page) : dbo.query(page, db);
		}
		else {
			return (db == null) ? dbo.query(true) : dbo.query(db);
		}
	}
}
