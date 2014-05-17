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
	 * ���캯��
	 * @param user {@link User} ��ǰ��¼���û�
	 * @param db {@link SQLExecutor} db
	 */
	private TradeInfoQuery(User user, SQLExecutor db) {

		this.currUser = user;
		this.db = db;
	}

	/**
	 * ��ȡTradeInfoʵ��
	 * @param user {@link User} ��ǰ��¼���û�
	 * @param db {@link SQLExecutor} db
	 * @return new BaseInfoQuerʵ��
	 */
	public static TradeInfoQuery getInstance(User user, SQLExecutor db) {

		return new TradeInfoQuery(user, db);
	}

	public static TradeInfoQuery getInstance(SQLExecutor db) {

		return new TradeInfoQuery(null, db);
	}

	public static Trade getTradeNoDb(String batchNo) {

		Assert.hasText(batchNo, "ҵ���Ų���Ϊ�ա�");
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
	 * ��ѯ������Ϣ
	 * @param batchNo
	 * @return
	 */
	public Trade getTrade(String batchNo) {

		Assert.hasText(batchNo, "�����Ų���Ϊ�ա�");

		SqlOperator dbo = new SqlOperator();
		dbo.addSql("select trade.* from trade ");
		dbo.addSql("  where trade.batch_no = ? ");
		//dbo.addSql("and trade.tran_status <> ? ");
		dbo.addParam(batchNo);
		//dbo.addParam(TranStatus.CheXiao.toString());
		Result rs = (db == null) ? dbo.query(true) : dbo.query(db);
		if( rs.isEmpty() ) {
			throw new BusinessException("δ��ѯ��������[" + batchNo + "]����Ӧ�Ľ��ס�");
		}
		return (Trade)rs.toEntity(Trade.class, 0);
	}

	/**
	 * ��ѯ������ҳ����Ϣ
	 * @param trantype {@link String} ��������
	 * @param selTranStatus {@link String} ҳ����ѡ��Ľ���״̬
	 * @param page
	 * @return
	 */
	public Result getTradeIndex(TranType trantype, String selTranStatus, Page page) {

		Assert.hasText(trantype.toString(), "�������Ͳ���Ϊ��");
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

//		/*С����Ϣ������С����Ϣ���*/
//		if( TranType.XiaoQuXinXiXinZeng.toString().equals(trantype.toString()) || TranType.XiaoQuXinXiBianGeng.toString().equals(trantype.toString()) ) {
//			tableName = "oper_sect";
//			sqlFormat.append("select hpb.*,trade.*,ohou.*,os.sect_id,os.st_code,os.st_name_frst,os.st_addr_frst,te.te_name");
//			sqlFormat.append(" from trade trade join oper_sect os on trade.batch_no = os.batch_no  ");
//		}
//		 
	
//		else {
//			throw new BusinessException("�÷�����֧�����������ͣ�����ϵϵͳ����Ա");
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
