package mng_plat.service.tran;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.AppSystemException;
import fd.exception.BusinessException;
import fd.util.Assert;
import hmfms.services.codes.TranStatus;
import hmfms.services.codes.TranType;
import hmfms.services.entity.Trade;
import hmfms.services.entity.Tran_log;
import hmfms.util.DateUtil;
import hmfms.web.User;
import mng_plat.service.baseinfo.TradeInfoQuery;
import mng_plat.service.cfg.CfgSysRelation;
import mng_plat.service.cfgflow.CfgFlowEngine;
import mng_plat.service.cfgflow.PropertyButton;
import mng_plat.service.operlog.OperLog;

/**
 * <p>标    题: 物业监管平台（二期）</p>
 * <p>描    述: 交易流程，交易保存，公共函数</p>
 * <p>版    权: Copyright (c) 2013</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2013-5-29 下午05:11:10</p>
 * @author xchao
 * @version 1.1
 */
public class TradeRecorder {

	protected TradeContext context = new TradeContext();

	public class TradeContext {

		protected String batchNo = "";
		protected User currUser;
		protected SQLExecutor db = null;
		protected String tran_date = "";
		protected String tran_remark = "";
		protected String tran_time = "";
		protected String tranType = "";
		protected String userName = "";

		public String getBatchNo() {

			return batchNo;
		}

		public User getCurrUser() {

			return currUser;
		}

		public SQLExecutor getDb() {

			return db;
		}

		public String getTran_date() {

			return tran_date;
		}

		public String getTran_remark() {

			return tran_remark;
		}

		public String getTran_time() {

			return tran_time;
		}

		public String getTranType() {

			return tranType;
		}

		public String getUserName() {

			return userName;
		}

	}

	public void setTranDateTime(String date, String time) {

		this.context.tran_date = date;
		this.context.tran_time = time;
	}

	public void setTranRemark(String tran_remark) {

		this.context.tran_remark = tran_remark;
	}

	/**
	 * 构造函数
	 * @param user {@link User} 当前登录的用户
	 * @param db {@link SQLExecutor} db
	 */
	public TradeRecorder(String batchNo, User user, String tranType, SQLExecutor db) {

		this.context.db = db;
		this.context.batchNo = batchNo;
		this.context.tranType = tranType;
		this.context.currUser = user;
		this.context.tran_date = DateUtil.getSysDate();
		this.context.tran_time = DateUtil.getSysTime();
	}

	public static TradeRecorder getInstance(String batchNo, User user, String tranType, SQLExecutor db) {

		return new TradeRecorder(batchNo, user, tranType, db);
	}

	/**
	 * 审核交易状态修改
	 * @param tran_action
	 */
	public void doNextStep(String buttoncode, String work_id) {

		doNextStep(buttoncode, null, work_id); //更改状态
	}

	/**
	 * 写退回日志用
	 * @param tran_action
	 * @param map
	 */
	public void doNextStep(String buttoncode, String remark, String work_id) {

		Trade rsTrade = TradeInfoQuery.getInstance(context.currUser, context.db).getTrade(context.batchNo);

		if( !context.tranType.equals(rsTrade.getTran_type()) ) {
			throw new BusinessException("根据申请编号：" + context.batchNo + ",查出的交易类型与您给的交易类型不一直");
		}
		String flow_scope = rsTrade.getFlow_scope().toString();

		TranStatus stepStatus = getNextStatus(buttoncode, flow_scope);
		SqlOperator dbo = new SqlOperator();
		dbo.addSql("update trade set tran_status = ?  ");
		dbo.addParam(stepStatus.toString());
		if( TranStatus.JiaoYiWanCheng.toString().equals(stepStatus.toString()) ) {
			dbo.addSql(",end_date = ? ,end_time = ? ");
			dbo.addParam(context.tran_date);
			dbo.addParam(context.tran_time);
		}
		dbo.addSql(" where batch_no = ? ");
		dbo.addParam(context.batchNo);
		int icount = dbo.execute(context.db);
		if( icount != 1 ) {
			throw new BusinessException("更新交易表失败！");
		}

		//更新退回记录
		OperLog.setReDoFinished(context.batchNo, context.db);
		/**
		 * 如果是审核退回的情况
		 */
		if( PropertyButton.getAuditRedo_arry().indexOf(buttoncode) != -1 ) {
			OperLog.writeGoBackLogByBatchNo(context.batchNo, remark, context.currUser, context.db);
			
		}  
		
		/*如果是交易完成，将操作表的数据保存到正式表中*/
		if( TranStatus.JiaoYiWanCheng.toString().equals(stepStatus.toString()) ) {
			 
			OperLog.commitOperInfo(CfgSysRelation.MapTranTable.get(rsTrade.getTran_type()), context.batchNo, context.db);
		}
		Tran_log tranLog = new Tran_log();
		tranLog.setBatch_no(context.batchNo);
		tranLog.setWork_id(work_id);
		dbo.add(tranLog, context.db);
	}

	/**
	 * 
	 * @param buttoncode
	 * @param info_id
	 * @return
	 */
	private TranStatus getNextStatus(String buttoncode, String flow_scope) {

		TranType tran_Type = TranType.getObject(context.tranType);
		User user = context.currUser;

		TranStatus tanStatus = CfgFlowEngine.getTranOperDestStatus(user, tran_Type, flow_scope, buttoncode);
		if( tanStatus == null ) {
			throw new BusinessException("未找到交易类型为[" + TranType.getValue(context.tranType) + ",的下一状态");
		}
		return tanStatus;
	}

	/**
	 * 基础数据使用的保存交易方法
	 * @param work_id {@link String} 操作日志ID
	 * @return
	 */
	public String saveTrade(String work_id) {

		return saveTrade(work_id, PropertyButton.getBaseInfo_AddButton());
	}

	/**
	 * 保存交易表
	 * @param info_id {@link String} 项目信息或物业管理区域
	 * @return
	 */
	public String saveTrade(String work_id, String buttoncode) {

		TranType tran_Type = TranType.getObject(context.tranType);
		String flow_scope = CfgFlowEngine.getFlowScope(context.currUser, tran_Type, context.currUser.getDeptCode());

		TranStatus netStatus = getNextStatus(buttoncode, flow_scope);

		SqlOperator dbo = new SqlOperator();
		Trade trade = new Trade();
		trade.setBatch_no(context.batchNo);
		trade.setTe_operid(context.currUser.getTellID());
		trade.setTran_type(context.tranType);
		trade.setTran_status(netStatus.toString());
		trade.setCreate_date(DateUtil.getSysDate());
		trade.setCreate_time(DateUtil.getSysTime());
		trade.setEnd_date(DateUtil.getSysDate());
		trade.setEnd_time(DateUtil.getSysTime());
		trade.setFlow_scope(flow_scope);
		if( dbo.add(trade, context.db) != 1 ) {
			throw new BusinessException("保存交易表失败！");
		}
		Tran_log tranLog = new Tran_log();
		tranLog.setBatch_no(context.batchNo);
		tranLog.setWork_id(work_id);
		dbo.add(tranLog, context.db);
		return context.batchNo;
	}

	/**
	 * 删除交易
	 * @return
	 */
	public String delTrade(String work_id) {

		SqlOperator dbo = new SqlOperator();
		Trade trade = new Trade();
		trade.setBatch_no(context.batchNo);
		trade.setEnd_date(DateUtil.getSysDate());
		trade.setEnd_time(DateUtil.getSysTime());
		trade.setTran_status(TranStatus.CheXiao.toString());
		if( dbo.update(trade, context.db) != 1 ) {
			throw new BusinessException("删除交易表失败！");
		}
		Tran_log tranLog = new Tran_log();
		tranLog.setBatch_no(context.batchNo);
		tranLog.setWork_id(work_id);
		dbo.add(tranLog, context.db);
		return context.batchNo;
	}

	/**
	 * 更新交易表
	 * @param work_id 操作id
	 * @param buttoncode
	 * @return
	 */
	public String updateTrade(String work_id, String buttoncode) {

		TranType tran_Type = TranType.getObject(context.tranType);
		String flow_scope = CfgFlowEngine.getFlowScope(context.currUser, tran_Type, context.currUser.getDeptCode());

		TranStatus netStatus = getNextStatus(buttoncode, flow_scope);

		SqlOperator dbo = new SqlOperator();
		Trade trade = new Trade();
		trade.setBatch_no(context.batchNo);
		trade.setTe_operid(context.currUser.getTellID());
		trade.setTran_type(context.tranType);
		trade.setTran_status(netStatus.toString());
		trade.setEnd_date(DateUtil.getSysDate());
		trade.setEnd_time(DateUtil.getSysTime());
		trade.setFlow_scope(flow_scope);
		if( dbo.update(trade, context.db) != 1 ) {
			throw new BusinessException("更新交易表失败！");
		}
		Tran_log tranLog = new Tran_log();
		tranLog.setBatch_no(context.batchNo);
		tranLog.setWork_id(work_id);
		dbo.add(tranLog, context.db);
		return context.batchNo;
	}

	/**
	 * 审核退回
	 * @param user {@link User} user
	 * @param batch_no {@link String} 申请编号
	 * @param tranOpr {@link String} 按钮名称
	 */
	public static void auditRedo(User user, String batch_no, String work_id, String option) {

		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			db.beginTrans();
			String tranOpr = PropertyButton.getAuditRedo_arry();
			String tranType = TradeInfoQuery.getInstance(user, db).getTrade(batch_no).getTran_type();
			TradeRecorder.getInstance(batch_no, user, tranType, db).doNextStep(tranOpr, option, work_id);
			db.commit();
		}
		catch(Exception e) {
			if( null != db ) {
				db.rollback();
			}
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
	 * 查看退回记录
	 * @param batch_no	申请编号或交易组号
	 * @param tran_status	
	 * @return
	 */
	public static Result listGoBackLog(String batch_no,String tran_status) {

		Assert.hasText(batch_no, "业务编号不能为空。");
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			SqlOperator dbo = new SqlOperator();
			dbo.addSql("select * from trade tr join trade_redo_log trl  on tr.batch_no = trl.batch_no ");
			dbo.addSql(" where tr.batch_no=?  ");
			dbo.addParam(batch_no);
			dbo.addSql(" order by oper_datetime desc ");
			Result rs = dbo.query(db);
			return rs;
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
	 * 保存交易表
	 * @param 
	 * @return
	 */
	public String saveTrade(String work_id, TranStatus tranStatus) {

		TranType tran_Type = TranType.getObject(context.tranType);
		String flow_scope = CfgFlowEngine.getFlowScope(context.currUser, tran_Type, context.currUser.getDeptCode());

		SqlOperator dbo = new SqlOperator();
		Trade trade = new Trade();
		trade.setBatch_no(context.batchNo);
		trade.setTe_operid(context.currUser.getTellID());
		trade.setTran_type(context.tranType);
		trade.setTran_status(tranStatus.toString());
		trade.setCreate_date(DateUtil.getSysDate());
		trade.setCreate_time(DateUtil.getSysTime());
		trade.setEnd_date(DateUtil.getSysDate());
		trade.setEnd_time(DateUtil.getSysTime());
		trade.setFlow_scope(flow_scope);
		if( dbo.add(trade, context.db) != 1 ) {
			throw new BusinessException("保存交易表失败！");
		}
		Tran_log tranLog = new Tran_log();
		tranLog.setBatch_no(context.batchNo);
		tranLog.setWork_id(work_id);
		dbo.add(tranLog, context.db);
		return context.batchNo;
	}
	/**
	 * 更新交易表
	 * @param work_id 操作id
	 * @param buttoncode
	 * @return
	 */
	public String updateTrade(String work_id, TranStatus netStatus) {

		SqlOperator dbo = new SqlOperator();
		Trade trade = new Trade();
		trade.setBatch_no(context.batchNo);
		trade.setTe_operid(context.currUser.getTellID());
		trade.setTran_type(context.tranType);
		trade.setTran_status(netStatus.toString());
		trade.setEnd_date(DateUtil.getSysDate());
		trade.setEnd_time(DateUtil.getSysTime());
		if( dbo.update(trade, context.db) != 1 ) {
			throw new BusinessException("更新交易表失败！");
		}
		Tran_log tranLog = new Tran_log();
		tranLog.setBatch_no(context.batchNo);
		tranLog.setWork_id(work_id);
		dbo.add(tranLog, context.db);
		return context.batchNo;
	}
}
