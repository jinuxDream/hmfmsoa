package mng_plat.service.operlog;

import fd.commons.TableEntity;
import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.AppSystemException;
import fd.exception.BusinessException;
import fd.util.Assert;
import hmfms.services.codes.DeptType;
import hmfms.services.codes.IsFlag;
import hmfms.services.codes.OperDataType;
import hmfms.services.codes.OperLogType;
import hmfms.services.codes.TranType;
import hmfms.services.entity.Oper_log;
import hmfms.services.entity.Org_info;
import hmfms.services.entity.Trade;
import hmfms.services.entity.Trade_redo_log;
import hmfms.services.key.PrimayKeyGener;
import hmfms.services.util.SQLExecuteUtil;
import hmfms.util.BeanUtil;
import hmfms.util.BeanUtilsExt;
import hmfms.util.DateUtil;
import hmfms.util.Debug;
import hmfms.util.ObjectUtil;
import hmfms.util.StringUtil;
import hmfms.web.User;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mng_plat.service.cfg.CfgSysPara;
import mng_plat.service.tran.TradeRecorder;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>标    题: 物业监管平台（二期）</p>
 * <p>描    述: 系统日志,操作表信息记录查询组件</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2013-5-6 下午02:39:34</p>
 * @author xchao
 * @version 1.1
 */
public class OperLog {

	private static final Log logger = LogFactory.getLog(OperLog.class);

	/**
	 * 保存操作日志
	 * @param user
	 * @param action
	 * @param request
	 */
	public static void saveOperLog(User user, String action, HttpServletRequest request) {

		SQLExecutor db = null;
		try {
			Debug.debug(logger, "saveOperLog->action=" + action);

			String tmp = PropertyLog.getString(action, "");
			String par_str;
			String operType;
			String operCont;

			if( ObjectUtil.isEmpty(tmp) ) {
				par_str = action;
			}
			else {
				par_str = StringUtil.ISO2GBK(tmp);
			}
			if( ObjectUtil.isEmpty(par_str) ) {
				throw new BusinessException("该功能未正确配置操作类型，请与研发负责人沟通！");
			}

			db = new SQLExecutor();
			db.setSQL_SHOW_LEVEL(SQLExecutor.SQL_SHOW_CLOSE);
			SqlOperator dbo = new SqlOperator();

			String[] par_arr = par_str.split(".");
			if( par_arr.length != 2 ) {
				if( "true".equals(CfgSysPara.getParaValueByName("isSaveLog", "false")) ) {
					operType = OperLogType.ChaKan.toString();
					operCont = "一般点击";
				}
				else {
					Debug.debug(logger, "未能保存平台日志！");
					return;
				}
			}
			else {
				operType = par_arr[0];
				operCont = par_arr[1];
			}

			Oper_log log = new Oper_log();
			log.setWork_id(PrimayKeyGener.getNextId());
			log.setOper_datetime(DateUtil.getSysDate() + DateUtil.getSysTime());
			log.setTe_operid(user.getTellID());
			log.setIs_ok(IsFlag.Shi.toString());
			log.setTe_name(user.getTellName());
			log.setLog_type(operType);
			log.setOper_cont(operCont);
			log.setOper_reason(request.getRequestURL().toString());
			log.setOper_host(InetAddress.getLocalHost().getHostAddress());
			log.setOper_tty_agent(user.getUser_agent().getUser_agent());
			log.setOper_tty_system(user.getUser_agent().getUser_system());
			log.setOper_browser(user.getUser_agent().getUser_browser());
			log.setOper_tty(user.getOper_tty());
			if( dbo.add(log, db) != 1 ) {
				throw new BusinessException("保存操作日志失败，请联系管理员!");
			}
			Debug.debug(logger, "成功保存平台日志,work_id=[" + log.getWork_id() + "]！");
			request.setAttribute("work_id", log.getWork_id());
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
	 * 变更退回日志是否处理状态
	 * @param batch_no {@link String} 申请编号
	 * @param db {@link SQLExecutor} db
	 */
	public static void setReDoFinished(String batch_no, SQLExecutor db) {

		SqlOperator dbo = new SqlOperator();
		dbo.addSql(" select * from  trade_redo_log where redo_status = ? and  batch_no=?  ");
		dbo.addParam(IsFlag.Fou.toString());
		dbo.addParam(batch_no);
		Result rs = dbo.query(db);
		if( rs.getRowCount() > 0 ) {
			dbo.clear();
			dbo.addSql(" update trade_redo_log set redo_status = ? where batch_no=? and redo_status =? ");
			dbo.addParam(IsFlag.Shi.toString());
			dbo.addParam(batch_no);
			dbo.addParam(IsFlag.Fou.toString());
			int ret = dbo.execute(db);
			if( ret < 1 ) {
				throw new BusinessException("更新退回处理标示失败！");
			}
		}
	}

	/**
	 * 保存审核退回信息
	 * @param batchNo {@link String} 申请编号
	 * @param remark {@link String} 退回原因
	 * @param currUser {@link User} 登录user
	 * @param db {@link SQLExecutor} db
	 */
	public static void writeGoBackLogByBatchNo(String batchNo, String remark, User currUser, SQLExecutor db) {

		Trade_redo_log redo = new Trade_redo_log();
		String sql1 = "select * from trade  where batch_no =? ";
		//查询处理前的状态
		SQLExecutor db_old = null;
		String te_operid = "";
		String te_org_id = "";
		String oldStatus = "";
		String tran_type = "";

		try {
			db_old = new SQLExecutor();
			db_old.addParam(batchNo);
			Result rs = db_old.execute(sql1);
			if( rs.getRowCount() != 1 ) {
				throw new BusinessException("未找到交易信息！");
			}
			oldStatus = rs.getString(0, "tran_status");
			tran_type = rs.getString(0, "tran_type");

			SqlOperator dbo = new SqlOperator();
			dbo.addSql(" select te_operid,org_id from tran_log tl join trade on tl.batch_no = trade.batch_no ");
			dbo.addSql(" join oper_log ol on ol.WORK_ID = tl.work_id ");
			dbo.addSql(" join tellers tell on ol.TE_OPERID = tell.TE_OPERID ");
			dbo.addSql(" where tran_status = ?  ");
			dbo.addParam(oldStatus);
			dbo.addSql(" and trade.batch_no = ? and  oper_datetime = ( ");
			dbo.addParam(batchNo);
			dbo.addSql("select max(oper_datetime) from tran_log tl join trade on tl.batch_no = trade.batch_no");
			dbo.addSql(" join oper_log ol on ol.WORK_ID = tl.work_id where tran_status = ? and trade.batch_no = ?)");
			dbo.addParam(oldStatus);
			dbo.addParam(batchNo);
			Result rsOper = dbo.query(db_old);
			if( !rsOper.isEmpty() ) {
				te_operid = rsOper.getString(0, "te_operid");
				te_org_id = rsOper.getString(0, "org_id");
			}
			//查询处理后的状态
			String currStatus;
			db.addParam(batchNo);
			Result rs_old = db.execute(sql1);
			if( rs_old.getRowCount() != 1 ) {
				throw new BusinessException("未找到交易信息！");
			}
			currStatus = rs_old.getString(0, "tran_status");

			//写日志
			redo.setBatch_no(batchNo);
			redo.setOper_datetime(DateUtil.getSysDate() + DateUtil.getSysTime());
			redo.setTe_operid(currUser.getTellID());
			redo.setTe_name(currUser.getTellName());
			redo.setRemark(remark);
			redo.setTran_status_old(oldStatus);
			redo.setTran_status(currStatus);
			redo.setAudit_credit_date(DateUtil.getSysDate()); //创建日期
			redo.setAudit_credit_time(DateUtil.getSysTime()); //创建时间
			redo.setTran_type(tran_type);
			redo.setRedo_status(IsFlag.Fou.toString());
			redo.setTo_org_id(te_org_id);
			redo.setTo_operid(te_operid);
			dbo.clear();
			if( dbo.add(redo, db) != 1 ) {
				throw new BusinessException("保存退回日志失败！");
			}
		}
		catch(Exception e) {
			throw new BusinessException(e);
		}
		finally {
			if( db_old != null )
				db_old.close();
		}
	}

	/**
	 * 基础数据保存操作表数据
	 * 仅仅针对小区、门牌、分户等基础信息
	 * @param tableEntity {@link List} 要保存数据实体
	 * @param operDataType {@link OperDataType} 操作数据类型
	 * @param batchNo {@link String} 申请编号
	 * @param user {@link User} user实体
	 * @param tranType {@link String} 交易类型
	 * @param work_id {@link String} 操作id
	 * @param buttonName {@link String} 按钮名称
	 * @param db {@link SQLExecutor} db
	 */
	public static void operBaseinfoSave(List<TableEntity> tableEntity, OperDataType operDataType, String batchNo, User user, String tranType,
					String work_id, String buttonName, SQLExecutor db) {

		/*保存基础数据上级信息，如分户保存时，保存门牌和项目信息，如果是门牌保存时，保存项目信息*/
		saveSuperInfo(tableEntity, operDataType, batchNo, tranType, db);
		/*保存交易数据及操作表数据*/
		operSave(tableEntity, false, operDataType, batchNo, user, tranType, work_id, buttonName, db);
	}


	/**
	 * 删除基础数据的所属组织机构信息
	 * @param db
	 * @param batchNo
	 */
	private static void deleSuperInfo(SQLExecutor db, String batchNo) {

		SqlOperator dbo = new SqlOperator();
		/*删除居委会信息*/
		dbo.clear();
		dbo.addSql("delete from oper_committee where batch_no = ?");
		dbo.addParam(batchNo);
		dbo.execute(db);

		/*删除开发商信息*/
		dbo.clear();
		dbo.addSql("delete from oper_dev where batch_no = ?");
		dbo.addParam(batchNo);
		dbo.execute(db);

		/*删除物业公司信息*/
		dbo.clear();
		dbo.addSql("delete from oper_csp where batch_no = ?");
		dbo.addParam(batchNo);
		dbo.execute(db);

		/*删除小区管理处信息*/
		dbo.clear();
		dbo.addSql("delete from oper_csp_sect where batch_no = ?");
		dbo.addParam(batchNo);
		dbo.execute(db);

		/*删除房办信息*/
		dbo.clear();
		dbo.addSql("delete from oper_hpb_off where batch_no = ?");
		dbo.addParam(batchNo);
		dbo.execute(db);

		/*删除业委会信息*/
		dbo.clear();
		dbo.addSql("delete from oper_hoc where batch_no = ?");
		dbo.addParam(batchNo);
		dbo.execute(db);

		/*删除小区经理信息*/
		dbo.clear();
		dbo.addSql("delete from oper_csp_sect_manger where batch_no = ?");
		dbo.addParam(batchNo);
		dbo.execute(db);
	}

	/**
	 * 保存基础数据上级信息，如分户保存时，保存门牌和项目信息，如果是门牌保存时，保存项目信息
	 * @param tableEntity {@link List} table数组
	 * @param operDataType {@link OperDataType} 数据操作类型
	 * @param batchNo {@link String} 申请编号
	 * @param tranType 
	 * @param db
	 */
	private static void saveSuperInfo(List<TableEntity> tableEntity, OperDataType operDataType, String batchNo, String tranType, SQLExecutor db) {}

	/**
	 * 备份数据
	 * @param map Map<String, List<String>> map map的key为表名，list中，第一个值为对于的主键id，后面为对于的主键值
	 * @param batchNo
	 * @param operDataType
	 * @param db
	 */
	@SuppressWarnings("unchecked")
	private static void backupInfo(Map<String, List<String>> map, String batchNo, OperDataType operDataType, SQLExecutor db) {

		SqlOperator dbo = new SqlOperator();
		Object keyVal[] = map.keySet().toArray();
		for(int i = 0; i < map.size(); i++) {
			String tableName = (String)keyVal[i];
			List<String> tableVal = map.get(tableName);
			if( tableVal.size() == 1 ) {
				continue;
			}
			dbo.clear();
			StringBuffer sb = new StringBuffer();
			dbo.addSql("select * from " + tableName + " where " + tableVal.get(0) + " in( ");
			//list第一个值为表名对于的主键ID
			for(int j = 1; j < tableVal.size(); j++) {
				sb.append("'").append(tableVal.get(j)).append("'").append(",");
			}
			String sqlVal = sb.toString().substring(0, sb.length() - 1);
			dbo.addSql(sqlVal.toString());
			dbo.addSql(")");
			Result rs = dbo.query(db);
			if( "sect".toUpperCase().equals(tableName.toUpperCase()) || "unit".toUpperCase().equals(tableName.toUpperCase()) ) {
				if( rs.isEmpty() ) {
					throw new BusinessException("小区或门牌信息不能为空！");
				}
			}
			for(int k = 0; k < rs.getRowCount(); k++) {
				Map<String, Object> data = rs.getResultRow(k).toMap();
				data.put("batch_no", batchNo);
				data.put("data_type", operDataType.toString());
				String operTableName = "oper_" + tableName.toLowerCase();
				SQLExecuteUtil.saveByMap(operTableName, data, db);
			}
		}
	}

	/**
	 * 新增交易（只针对组织机构信息）
	 * @param tableEntity {@link List} 要保存的实体list
	 * @param operDataType {@link OperDataType} 操作数据类型
	 * @param batchNo {@link String} 申请编号
	 * @param user {@link User} 用户实体
	 * @param tranType {@link String} 交易类型
	 * @param work_id {@link String} 工作id
	 * @param buttonName {@link String} 配置按钮名称
	 * @param db
	 */
	public static void operOrgInfoSave(List<TableEntity> tableEntity, OperDataType operDataType, String batchNo, User user, String tranType,
					String work_id, String buttonName, SQLExecutor db) {

		List<String> listCspId = new ArrayList<String>();
		listCspId.add("csp_id");
		List<String> listCsId = new ArrayList<String>();
		listCsId.add("cs_id");
		for(int i = 0; i < tableEntity.size(); i++) {
			TableEntity table = tableEntity.get(i);
			Map<String, Object> pks = new HashMap<String, Object>();
			BeanUtilsExt.convertEnToMap(table, pks);
			String csp_id = pks.get("csp_id") == null ? "" : pks.get("csp_id").toString();
			if( !listCspId.contains(csp_id) ) {
				listCspId.add(csp_id);
			}
			String cs_id = pks.get("cs_id") == null ? "" : pks.get("cs_id").toString();
			if( !listCsId.contains(cs_id) ) {
				listCsId.add(cs_id);
			}
		}
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		/*物业公司信息新增 ,物业公司信息变更*/
//		if( TranType.WuYeGongSiXinXiBianGeng.toString().equals(tranType) || TranType.WuYeGongSiXinXiXinZeng.toString().equals(tranType) ) {}
//		/*小区管理处信息新增，小区管理处信息变更*/
//		else if( TranType.XiaoQuGuanLiChuXinXiBianGeng.toString().equals(tranType)
//						|| TranType.XiaoQuGuanLiChuXinXiXinZeng.toString().equals(tranType) ) {
//
//			map.put("csp", listCspId);
//		}
//		/*小区经理信息新增，小区经理信息变更*/
//		else if( TranType.XiaoQuJingLiXinXiBianGeng.toString().equals(tranType) || TranType.XiaoQuJingLiXinXiXinZeng.toString().equals(tranType) ) {
//
//			map.put("csp", listCspId);//备份物业公司信息
//			map.put("csp_sect", listCsId);//备份小区管理处信息
//
//		}
//		/*业委会信息新增，业委会信息变更*/
//		else if( TranType.YeWeiHuiXinXiBianGeng.toString().equals(tranType) || TranType.YeWeiHuiXinXiXinZeng.toString().equals(tranType) ) {}
//		/*居委会信息新增，居委会信息信息变更  add by luohy 20140424 增加居委会管理功能*/
//		else if( TranType.JuWeiHuiXinXiXinZeng.toString().equals(tranType) || TranType.JuWeiHuiXinXiXinZeng.toString().equals(tranType) ) {}
//
//		else {
//			throw new BusinessException("该方法不支持此交易类型，请联系管理员！");
//		}

		backupInfo(map, batchNo, operDataType, db);
		/*保存交易数据及操作表数据*/
		operSave(tableEntity, false, operDataType, batchNo, user, tranType, work_id, buttonName, db);
	}

	/**
	 *  编辑交易（只针对组织机构信息）
	 *  物业公司、小区管理处、小区经理、物业公司
	 * @param tableEntity {@link List} 要保存的实体list
	 * @param operDataType {@link OperDataType} 操作数据类型
	 * @param batchNo {@link String} 申请编号
	 * @param user {@link User} 用户实体
	 * @param tranType {@link String} 交易类型
	 * @param work_id {@link String} 工作id
	 * @param buttonName {@link String} 配置按钮名称
	 * @param db
	 */
	public static void operOrgInfoEdit(List<TableEntity> tableEntity, OperDataType operDataType, String batchNo, User user, String tranType,
					String work_id, String buttonName, SQLExecutor db) {

		List<String> listCspId = new ArrayList<String>();
		for(int i = 0; i < tableEntity.size(); i++) {
			TableEntity table = tableEntity.get(i);
			Map<String, Object> pks = new HashMap<String, Object>();
			BeanUtilsExt.convertEnToMap(table, pks);
			String csp_id = pks.get("csp_id") == null ? "" : pks.get("csp_id").toString();
			if( !listCspId.contains(csp_id) ) {
				listCspId.add(csp_id);
			}
		}
//		/*物业公司信息新增 ,物业公司信息变更*/
//		if( TranType.WuYeGongSiXinXiBianGeng.toString().equals(tranType) || TranType.WuYeGongSiXinXiXinZeng.toString().equals(tranType) ) {}
//		/*小区管理处信息新增，小区管理处信息变更*/
//		/*小区经理信息新增，小区经理信息变更*/
//		else if( TranType.XiaoQuGuanLiChuXinXiBianGeng.toString().equals(tranType)
//						|| TranType.XiaoQuGuanLiChuXinXiXinZeng.toString().equals(tranType)
//						|| TranType.XiaoQuJingLiXinXiBianGeng.toString().equals(tranType)
//						|| TranType.XiaoQuJingLiXinXiXinZeng.toString().equals(tranType) ) {}
//		/*业委会信息新增，业委会信息变更*/
//		else if( TranType.YeWeiHuiXinXiBianGeng.toString().equals(tranType) || TranType.YeWeiHuiXinXiXinZeng.toString().equals(tranType) ) {}
//		else {
//			throw new BusinessException("该方法不支持此交易类型，请联系管理员！");
//		}
		/*保存交易数据及操作表数据*/
		operEditSave(tableEntity, false, operDataType, batchNo, user, tranType, work_id, buttonName, db);
	}

	/**保存操作表数据,同时保存交易表数据
	 * @param tableEntity {@link List} 要保存的信息表
	 * @param isInfo {@link Boolean} 是否直接保存正式表
	 * @param operDataType {@link OperDataType} 操作数据类型，
	 * 		1、如果是注册数据，操作表保存一条数据（注册数据），
	 * 		2、如果是变更后数据，操作表保存2条数据（变更前和变更后），
	 * 		3、如果是注销数据，操作表保存1条数据（注销数据） ，
	 * 		4、如果是业务数据，直接从正是表中拷贝到操作表中，
	 * @param batchNo {@link String} 申请编号
	 * @param user {@link User} 当前登录user
	 * @param tranType {@link String} 交易类型
	 * @param work_id {@link String} 操作ID
	 * @param buttonName {@link String} 保存使用配置流程中的那个按钮，填写button的ID值
	 * @param db {@link SQLExecutor} db
	 */
	@SuppressWarnings("unchecked")
	public static void operSave(List<TableEntity> tableEntity, boolean isInfo, OperDataType operDataType, String batchNo, User user, String tranType,
					String work_id, String buttonName, SQLExecutor db) {

		Assert.notNull(tableEntity, "要保存的实体list不能为空");
		Assert.notNull(isInfo, "是否保存正是表标识不能为空");
		Assert.notNull(operDataType, "操作数据类型不能为空");
		Assert.hasText(batchNo, "申请编号不能为空");
		Assert.notNull(db, "db不能为空");
		try {
			int orignalSqlShowLevel = db.getSQL_SHOW_LEVEL();
			db.setSQL_SHOW_LEVEL(SQLExecutor.SQL_SHOW_CLOSE);
			SqlOperator dbo = new SqlOperator();

			List<String> listTable = new ArrayList<String>();
			/**
			 * 如果buttonName为空，不保存交易表，意味着未配置交易流程
			 * 这种情况必须在该方法外保存交易表数据。
			 */
			if( !ObjectUtil.isEmpty(buttonName) ) {
				TradeRecorder.getInstance(batchNo, user, tranType, db).saveTrade(work_id, buttonName);
			}
			for(int i = 0; i < tableEntity.size(); i++) {
				TableEntity table = tableEntity.get(i);
				String objectName = table.getClass().getName();

				String tableName = objectName;
				tableName = tableName.substring(objectName.lastIndexOf(".") + 1, tableName.length());
				if( !listTable.contains(tableName) ) {
					listTable.add(tableName);
				}

				Map<String, Object> pks = new HashMap<String, Object>();
				String operTableName = "oper_" + tableName.toLowerCase();
				BeanUtilsExt.convertEnToMap(table, pks);
				pks = SQLExecuteUtil.getEntityKey(table, pks);

				Result rs = SQLExecuteUtil.selectByMapResult(tableName, pks, db);

				/**
				 * 判断数据操作类型
				 */
				if( OperDataType.ZhuCeShuJu.toString().equals(operDataType.toString()) ) {
					/**
					 * 如果是注册数据，什么都不做
					 */
				}
				else if( OperDataType.GengGaiHouShuJu.toString().equals(operDataType.toString())
								|| OperDataType.ZhuXiaoShuJu.toString().equals(operDataType.toString())
								|| OperDataType.YeWuShuJu.toString().equals(operDataType.toString()) ) {

					if( rs.isEmpty() ) {
						throw new BusinessException("数据类型为：更改后数据，正是表中没有数据，不能进行操作");
					}
					for(int j = 0; j < rs.getRowCount(); j++) {
						Map<String, Object> data = rs.getResultRow(j).toMap();
						data.put("batch_no", batchNo);
						data.put("data_type", OperDataType.GengGaiHouShuJu.toString().equals(operDataType.toString()) ? OperDataType.GengGaiQianShuJu
										.toString() : operDataType.toString());
						SQLExecuteUtil.saveByMap(operTableName, data, db);
					}
				}
				else {
					throw new BusinessException("此方法不支持数据类型为" + OperDataType.getValue(operDataType));
				}
				/**
				 * 如果是注册数据、更改后数据数据，保存注册数据和更改后数据
				 */
				int index = objectName.lastIndexOf(".");
				objectName = objectName.substring(0, index + 1) + "Oper_" + objectName.substring(index + 1, objectName.length()).toLowerCase();//拼接需要记录的操作表表名
				TableEntity obj_oper = (TableEntity)Class.forName(objectName).newInstance();

				if( OperDataType.ZhuCeShuJu.toString().equals(operDataType.toString()) ) {
					BeanUtil.copyObject(table, obj_oper);
					PropertyUtils.setSimpleProperty(obj_oper, "batch_no", batchNo);
					PropertyUtils.setSimpleProperty(obj_oper, "data_type", operDataType.toString());
					if( dbo.add(obj_oper, db) != 1 ) {
						throw new BusinessException("未将任何数据保存至表[" + objectName + "]，内容:[" + obj_oper + "]");
					}
				}
				else if( OperDataType.GengGaiHouShuJu.toString().equals(operDataType.toString()) ) {
					for(int j = 0; j < rs.getRowCount(); j++) {
						Map<String, Object> data = rs.getResultRow(j).toMap();
						BeanUtilsExt.convertEnToMapCoverageMap(table, data);
						data.put("batch_no", batchNo);
						data.put("data_type", operDataType.toString());
						if( SQLExecuteUtil.saveByMap(operTableName, data, db) != 1 ) {
							throw new BusinessException("未将任何数据保存至表[" + objectName + "]，内容:[" + obj_oper + "]");
						}
					}
				}
				dbo.clear();
			}
			/*
			 * 直接保存正式表数据
			 */
			if( isInfo ) {
				commitOperInfo(listTable, batchNo, db);
			}
			db.setSQL_SHOW_LEVEL(orignalSqlShowLevel);
		}
		catch(Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException("保存操作日志表信息失败，work_id" + batchNo + e.getMessage(), e);
		}
	}

	/**编辑时调用该方法
	 * 先删除操作表数据
	 * 保存操作表数据,同时保存交易表数据
	 * @param tableEntity {@link List} 要保存的信息表
	 * @param isInfo {@link Boolean} 是否直接保存正式表
	 * @param operDataType {@link OperDataType} 操作数据类型，
	 * 		1、如果是注册数据，操作表保存一条数据（注册数据），
	 * 		2、如果是变更后数据，操作表保存2条数据（变更前和变更后），
	 * 		3、如果是注销数据，操作表保存1条数据（注销数据） ，
	 * 		4、如果是业务数据，直接从正是表中拷贝到操作表中，
	 * @param batchNo {@link String} 申请编号
	 * @param user {@link User} 当前登录user
	 * @param tranType {@link String} 交易类型
	 * @param work_id {@link String} 操作ID
	 * @param buttonName {@link String} 保存使用配置流程中的那个按钮，填写button的ID值
	 * @param db {@link SQLExecutor} db
	 */
	@SuppressWarnings("unchecked")
	public static void operEditSave(List<TableEntity> tableEntity, boolean isInfo, OperDataType operDataType, String batchNo, User user,
					String tranType, String work_id, String buttonName, SQLExecutor db) {

		Assert.notNull(tableEntity, "要保存的实体list不能为空");
		Assert.notNull(isInfo, "是否保存正是表标识不能为空");
		Assert.notNull(operDataType, "操作数据类型不能为空");
		Assert.hasText(batchNo, "申请编号不能为空");
		Assert.notNull(db, "db不能为空");
		try {
			int orignalSqlShowLevel = db.getSQL_SHOW_LEVEL();
			db.setSQL_SHOW_LEVEL(SQLExecutor.SQL_SHOW_CLOSE);
			SqlOperator dbo = new SqlOperator();

			List<String> listTable = new ArrayList<String>();

			/**
			 * 如果buttonName为空，不保存交易表，意味着未配置交易流程
			 * 这种情况必须在该方法外保存交易表数据。
			 */
			if( !ObjectUtil.isEmpty(buttonName) ) {
				TradeRecorder.getInstance(batchNo, user, tranType, db).updateTrade(work_id, buttonName);
			}

			List<String> list = new ArrayList<String>();

			for(int i = 0; i < tableEntity.size(); i++) {
				TableEntity table = tableEntity.get(i);
				String objectName = table.getClass().getName();

				String tableName = objectName;
				tableName = tableName.substring(objectName.lastIndexOf(".") + 1, tableName.length());
				if( !listTable.contains(tableName) ) {
					listTable.add(tableName);
				}
				String operTableName = "oper_" + tableName.toLowerCase();
				/**
				 * 编辑时根据申请编号将操作表数据删除
				 */
				if( !list.contains(operTableName) ) {
					dbo.addSql("delete from " + operTableName + " where batch_no =? ");
					dbo.addParam(batchNo);
					int upnums = dbo.execute(db);
					if( upnums < 1 )
						throw new BusinessException("删除操作表数据失败");
					dbo.clear();
					list.add(operTableName);
				}
				/**
				 * 判断数据操作类型
				 */
				if( OperDataType.ZhuCeShuJu.toString().equals(operDataType.toString()) ) {
					/**
					 * 如果是注册数据，什么都不做
					 */
				}
				else if( OperDataType.GengGaiHouShuJu.toString().equals(operDataType.toString())
								|| OperDataType.ZhuXiaoShuJu.toString().equals(operDataType.toString())
								|| OperDataType.YeWuShuJu.toString().equals(operDataType.toString()) ) {
					Map<String, Object> pks = new HashMap<String, Object>();

					BeanUtilsExt.convertEnToMap(table, pks);
					pks = SQLExecuteUtil.getEntityKey(table, pks);

					Result rs = SQLExecuteUtil.selectByMapResult(tableName, pks, db);
					if( rs.isEmpty() ) {
						throw new BusinessException("数据类型为：更改后数据，正是表中没有数据，不能进行操作");
					}
					for(int j = 0; j < rs.getRowCount(); j++) {
						Map<String, Object> data = rs.getResultRow(j).toMap();
						data.put("batch_no", batchNo);
						data.put("data_type", OperDataType.GengGaiHouShuJu.toString().equals(operDataType.toString()) ? OperDataType.GengGaiQianShuJu
										.toString() : operDataType.toString());
						SQLExecuteUtil.saveByMap(operTableName, data, db);
					}
				}
				else {
					throw new BusinessException("此方法不支持数据类型为" + OperDataType.getValue(operDataType));
				}
				/**
				 * 如果是注册数据、更改后数据数据，保存注册数据和更改后数据
				 */
				if( OperDataType.ZhuCeShuJu.toString().equals(operDataType.toString())
								|| OperDataType.GengGaiHouShuJu.toString().equals(operDataType.toString()) ) {
					int index = objectName.lastIndexOf(".");
					objectName = objectName.substring(0, index + 1) + "Oper_" + objectName.substring(index + 1, objectName.length()).toLowerCase();//拼接需要记录的操作表表名
					TableEntity obj_oper = (TableEntity)Class.forName(objectName).newInstance();
					BeanUtil.copyObject(table, obj_oper);
					PropertyUtils.setSimpleProperty(obj_oper, "batch_no", batchNo);
					PropertyUtils.setSimpleProperty(obj_oper, "data_type", operDataType.toString());
					if( dbo.add(obj_oper, db) != 1 ) {
						throw new BusinessException("未将任何数据保存至表[" + objectName + "]，内容:[" + obj_oper + "]");
					}
				}
				dbo.clear();
			}
			/*
			 * 直接保存正式表数据
			 */
			if( isInfo ) {
				commitOperInfo(listTable, batchNo, db);
			}
			db.setSQL_SHOW_LEVEL(orignalSqlShowLevel);
		}
		catch(Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException("保存操作日志表信息失败，work_id" + batchNo + e.getMessage(), e);
		}
	}

	/**
	 * 提交信息，将操作表数据提交到真是表中
	 * @param tableName {@link List}
	 * @param batchNo
	 * @param db
	 */
	@SuppressWarnings("unchecked")
	public static void commitOperInfo(List<String> tableName, String batchNo, SQLExecutor db) {

		try {
			int orignalSqlShowLevel = db.getSQL_SHOW_LEVEL();
			db.setSQL_SHOW_LEVEL(SQLExecutor.SQL_SHOW_ALL);
			SqlOperator dbo = new SqlOperator();
			for(int i = 0; i < tableName.size(); i++) {
				Map<String, Object> pks = new HashMap<String, Object>();
				pks.put("batch_no", batchNo);
				String infoTableName = tableName.get(i);

				String operTableName = "oper_" + infoTableName.toLowerCase();
				dbo.clear();
				dbo.addSql("select * from " + operTableName + " where batch_no = ? and data_type in(?,?,?)");
				dbo.addParam(batchNo);
				dbo.addParam(OperDataType.ZhuCeShuJu.toString());
				dbo.addParam(OperDataType.GengGaiHouShuJu.toString());
				dbo.addParam(OperDataType.ZhuXiaoShuJu.toString());
				Result rs = dbo.query(db);
				if( rs.isEmpty() ) {
					throw new BusinessException("根据申请编号没有查到相应的数据！");
				}
				for(int j = 0; j < rs.getRowCount(); j++) {
					String data_type = rs.getString(j, "data_type");
					Map<String, Object> data = rs.getResultRow(j).toMap();
					if( OperDataType.ZhuCeShuJu.toString().equals(data_type) ) {
						SQLExecuteUtil.saveByMap(infoTableName, data, db);
					}
					else if( OperDataType.GengGaiHouShuJu.toString().equals(data_type) ) {
						SQLExecuteUtil.updateByMap(infoTableName, data, db);
					}
					else if( OperDataType.ZhuXiaoShuJu.toString().equals(data_type) ) {
						SQLExecuteUtil.deleteByMap(infoTableName, data, db);
					}
				}
			}

			db.setSQL_SHOW_LEVEL(orignalSqlShowLevel);
		}
		catch(Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException("从操作表中拷贝保存正式表失败，申请编号=" + batchNo + "==" + e.getMessage(), e);
		}
	}

	

	/**
	 * 物业公司、小区管理处、业委会、白蚁时，保存org_info表信息
	 * @param db
	 * @param batch_no
	 */
	private static void saveOrgInfo(SQLExecutor db, String tranType, String batchNo) {

		SqlOperator dbo = new SqlOperator();
		/**
		 * 物业企业新增
		 */
//		if( TranType.WuYeGongSiXinXiXinZeng.toString().equals(tranType) ) {
//			dbo.addSql("select * from oper_csp where batch_no = ? and data_type = ? ");
//			dbo.addParam(batchNo);
//			dbo.addParam(OperDataType.ZhuCeShuJu.toString());
//			Result rs = dbo.query(db);
//			if( rs.getRowCount() != 1 ) {
//				throw new BusinessException("查询物业公司注册数据失败，申请编号为：" + batchNo);
//			}
//			Org_info org_info = new Org_info();
//			org_info.setOrg_id(rs.getString(0, "csp_id"));
//			org_info.setDept_type(DeptType.WuYeGongSi.toString());
//			org_info.setOrg_name(rs.getString(0, "csp_name"));
//			if( dbo.add(org_info, db) != 1 ) {
//				throw new BusinessException("保存org_info失败");
//			}
//		}
//		 
		 
	}
	/**
	 * 将正式表复制到操作表中
	 * add by xt 20140331
	 */
	public void copy2Oper(){
		
	}
	
}
