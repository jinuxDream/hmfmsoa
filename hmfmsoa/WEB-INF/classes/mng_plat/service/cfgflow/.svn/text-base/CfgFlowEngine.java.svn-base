package mng_plat.service.cfgflow;

import hmfms.services.codes.IsFlag;
import hmfms.services.codes.TranStatus;
import hmfms.services.codes.TranType;
import hmfms.services.codes.WorkGroupType;
import hmfms.util.Debug;
import hmfms.util.ObjectUtil;
import hmfms.util.StringUtil;
import hmfms.web.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.ResultRow;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.AppSystemException;
import fd.exception.BusinessException;
import fd.util.Assert;

/**
 * <p>标    题: 物业监管平台（二期）</p>
 * <p>描    述: 流程引擎总控程序</p>
 * <p>版    权: Copyright (c) 2013</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2013-5-20 上午11:32:05</p>
 * @author xchao
 * @version 1.1
 */
public class CfgFlowEngine {

	public static String allFlow = "1000000000000000000";//默认流程
	private static final Log logger = LogFactory.getLog(CfgFlowEngine.class);

	private static Map<String, ResultRow> mapFlowScope = new HashMap<String, ResultRow>();
	private static Map<String, ResultRow> mapTranDefineRole = new HashMap<String, ResultRow>();
	private static Map<String, Result> tranFlowAll = new HashMap<String, Result>();
	private static Map<String, Result> tranFlowAllTranStatus = new HashMap<String, Result>();
	private static Map<String, ResultRow> userRole = new HashMap<String, ResultRow>();
	static {
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			SqlOperator dbo = new SqlOperator();

			/**
			 * 查询使用范围信息
			 * @return {@link Result}
			 */
			dbo.addSql("select * from flow_scope");
			Result rsRes = dbo.query(db);
			for(int i = 0; i < rsRes.getRowCount(); i++) {
				mapFlowScope.put(rsRes.getString(i, "flow_id"), rsRes.getResultRow(i));
			}

			dbo.clear();
			dbo.addSql("select tran_role_id,tran_type,flow_scope where_id,ro_roleid roleid,tran_role from ");
			dbo.addSql(" tran_role tr join flow_scope fs on tr.flow_id = fs.flow_id ");
			rsRes = dbo.query(db);
			for(int i = 0; i < rsRes.getRowCount(); i++) {

				String roleid = rsRes.getString(i, "roleid");//组ID或角色ID
				String tran_type = rsRes.getString(i, "tran_type");//交易类型
				String whereID = rsRes.getString(i, "where_id");//使用范围（信息ID）
				String key = roleid + "-" + tran_type + "-" + whereID;

				mapTranDefineRole.put(key, rsRes.getResultRow(i));
			}

			dbo.clear();
			dbo.addSql("select * from tran_flow where is_enable = ?");
			dbo.addParam(IsFlag.Shi);
			dbo.addSql(" order by flow_oper_id ");
			rsRes = dbo.query(db);
			for(int j = 0; j < rsRes.getRowCount(); j++) {
				String key = rsRes.getString(j, "tran_role_id");
				Result valueRs = (Result)tranFlowAll.get(key);
				if( null == valueRs ) {
					valueRs = new Result(new ArrayList<Object>());
				}
				valueRs.add(rsRes.getResultRow(j));
				tranFlowAll.put(key, valueRs);

				key = rsRes.getString(j, "curr_status") + rsRes.getString(j, "tran_role_id");
				valueRs = (Result)tranFlowAllTranStatus.get(key);
				if( null == valueRs ) {
					valueRs = new Result(new ArrayList<Object>());
				}

				valueRs.add(rsRes.getResultRow(j));
				tranFlowAllTranStatus.put(key, valueRs);
			}

			dbo.clear();
			dbo.addSql("select * from role");
			rsRes = dbo.query(db);
			for(int i = 0; i < rsRes.getRowCount(); i++) {
				userRole.put(rsRes.getString(i, "ro_roleid"), rsRes.getResultRow(i));
			}
		}
		catch(Exception e) {
			throw new BusinessException("配置错误，请联系管理员");
		}
		finally {
			db.close();
		}

	}

	//**********************开始--修改用户登录时，将所有更用户有关的信息全部放着user中
	/**
	 * 获取当前用户的流程信息
	 * @param user_id
	 */
	public static Map<String, ResultRow> getMapTranDefineGroup(String te_operid) {

		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			SqlOperator dbo = new SqlOperator();
			Map<String, ResultRow> mapTranDefineGroup = new HashMap<String, ResultRow>();

			/**/
			dbo.addSql("select tran_role_id,wg.group_id groupId,tran_role,flow_id,grt.te_operid");
			dbo.addSql("from group_role_tellers grt join workgroup wg on grt.group_id = wg.group_id");
			dbo.addSql(" join tran_role tr on wg.group_id = tr.group_id");
			dbo.addSql("where workgroup_type = ? and grt.te_operid = ?");
			dbo.addParam(WorkGroupType.ShenPiLiuChengZu.toString());
			dbo.addParam(te_operid);
			Result rsRes = dbo.query(db);
			//如果用户工作组对于的流程为空，查询用户对应的角色流程信息(将用户组流程信息和角色流程信息合并)
			dbo.clear();
			dbo.addSql(" select tran_role_id, tr.GROUP_ID groupid, tran_role, flow_id, grt.te_operid ");
			dbo.addSql(" from group_role_tellers grt join tran_role tr on grt.RO_ROLEID = tr.RO_ROLEID where te_operid = ? ");
			dbo.addParam(te_operid);
			rsRes.add(dbo.query(db));

			for(int i = 0; i < rsRes.getRowCount(); i++) {
				String flow_id = rsRes.getString(i, "flow_id");//流程适用ID
				ResultRow rsRowFs = mapFlowScope.get(flow_id);
				String tran_type = rsRowFs.getString("tran_type");//交易类型
				String where_id = rsRowFs.getString("flow_scope");//使用范围ID  有可能是org_id 有可能是 info_id

				String flowID = rsRes.getString(i, "groupId");//组ID或角色ID
				String key = flowID + "-" + tran_type + "-" + where_id;

				mapTranDefineGroup.put(key, rsRes.getResultRow(i));
			}
			return mapTranDefineGroup;
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
	 * 获取用户组角色map
	 * @param user_id
	 */
	public static Map<String, Result> getUserFlowGroup(String te_operid) {

		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			SqlOperator dbo = new SqlOperator();
			//这个map的value为什么不是ResultRow，而要把整个Result反复放进来很多次。
			Map<String, Result> userFlowGroup = new HashMap<String, Result>();

			dbo.clear();
			dbo.addSql("select wg.group_id,flow_scope,tran_type,te_operid from  ");
			dbo.addSql(" group_role_tellers grt,workgroup wg,tran_role tr,flow_scope fs ");
			dbo.addSql(" where grt.group_id = wg.group_id  ");
			dbo.addSql(" and wg.GROUP_ID = tr.GROUP_ID  ");
			dbo.addSql(" and tr.FLOW_ID = fs.FLOW_ID ");
			dbo.addSql("  and grt.RO_ROLEID = tr.RO_ROLEID ");
			dbo.addSql(" and workgroup_type = ? and te_operid = ?");
			dbo.addParam(WorkGroupType.ShenPiLiuChengZu.toString());
			dbo.addParam(te_operid);
			Result rsRes = dbo.query(db);
			/**
			 * 将用户组流程信息和用户角色信息合并
			 */
			dbo.clear();
			dbo.addSql("SELECT tr.GROUP_ID, flow_scope, tran_type, te_operid");
			dbo.addSql(" FROM group_role_tellers grt, tran_role tr, flow_scope fs ");
			dbo.addSql(" WHERE grt.ro_roleid = tr.ro_roleid and ");
			dbo.addSql(" tr.flow_id = fs.flow_id and grt.ro_roleid = tr.ro_roleid ");
			dbo.addSql("  AND te_operid = ? ");
			dbo.addParam(te_operid);
			rsRes.add(dbo.query(db));
			for(int i = 0; i < rsRes.getRowCount(); i++) {
				String key = rsRes.getString(i, "tran_type");
				Result valueRs = (Result)userFlowGroup.get(key);
				if( null == valueRs ) {//这个判断没必要有
					valueRs = new Result(new ArrayList<Object>());
				}
				valueRs.add(rsRes.getResultRow(i));
				userFlowGroup.put(key, valueRs);
			}
			return userFlowGroup;
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

	//**********************end--修改用户登录时，将所有更用户有关的信息全部放着user中

	/**
	 * 根据交易类型获取交易的按钮
	 * @param user {@link User} 
	 * @param tranType {@link TranType} 交易类型
	 * @param tranStatus {@link TranStatus} 当前交易类型
	 * @return
	 */
	protected static Map<String, Map<String, ResultRow>> getTranListButtonAll(User user, TranType tranType, String flowScope, TranStatus tranStatus) {

		//Result rs = userFlowGroup.get(tranType + user.getTellID());//交易类型+组ID或角色ID
		Map<String, Result> userFlowGroup = user.getUserFlowGroup();
		Result rs = userFlowGroup.get(tranType);//交易类型+组ID或角色ID

		Map<String, ResultRow> retMapAll = new TreeMap<String, ResultRow>();
		Map<String, ResultRow> retMapScope = new TreeMap<String, ResultRow>();
		String key = "";
		if( rs != null ) {
			for(int i = 0; i < rs.getRowCount(); i++) {
				String flowId = rs.getString(i, "group_id");
				String flow_scope = rs.getString(i, "flow_scope");
				//key = user.getTellID() + "-" + flowId + "-" + tranType + "-" + flow_scope;
				//xc 修改，将mapTranDefineGroup修改为从user中获取
				key = flowId + "-" + tranType + "-" + flow_scope;
				Map<String, ResultRow> mapTranDefineGroup = user.getMapTranDefineGroup();
				ResultRow rsTR = mapTranDefineGroup.get(key);
				if( rsTR == null ) {
					Debug.info(logger, "没有为当前用户配置[" + TranType.getValue(tranType) + "]交易流程");
					throw new BusinessException(" 没有为当前用户配置[" + TranType.getValue(tranType) + "]交易流程 ");
				}
				String tranRole_id = rsTR.getString("tran_role_id");

				Result rsTf = new Result(new ArrayList<Object>());
				if( null != tranStatus ) {
					rsTf = tranFlowAllTranStatus.get(tranStatus + tranRole_id);
				}
				else {
					rsTf = tranFlowAll.get(tranRole_id);
				}
				if( rsTf == null ) {
					continue;
				}
				for(int j = 0; j < rsTf.getRowCount(); j++) {
					rsTf.setObject(j, "flow_scope", flow_scope);
					retMapAll.put(rsTf.getString(j, "tran_oper"), rsTf.getResultRow(j));
					retMapScope.put(rsTf.getString(j, "tran_oper") + "-" + flow_scope, rsTf.getResultRow(j));
				}
			}
		}
		else {
			key = user.getRoled_id() + "-" + tranType + "-" + flowScope;
			ResultRow rsTR = mapTranDefineRole.get(key);
			if( rsTR == null ) {
				key = user.getRoled_id() + "-" + tranType + "-" + allFlow;
				rsTR = mapTranDefineRole.get(key);
			}
			if( rsTR == null ) {
				Debug.info(logger, "没有为当前用户配置[" + TranType.getValue(tranType) + "]交易流程");
				throw new BusinessException(" 没有为当前用户配置[" + TranType.getValue(tranType) + "]交易流程 ");
			}
			String tranRole_id = rsTR.getString("tran_role_id");

			Result rsTf = new Result(new ArrayList<Object>());
			if( null != tranStatus ) {
				rsTf = tranFlowAllTranStatus.get(tranStatus + tranRole_id);
			}
			else {
				rsTf = tranFlowAll.get(tranRole_id);
			}
			if( rsTf == null ) {
				throw new BusinessException("未找到对应的流程配置!");
			}
			for(int j = 0; j < rsTf.getRowCount(); j++) {
				rsTf.setObject(j, "flow_scope", flowScope);
				retMapAll.put(rsTf.getString(j, "tran_oper"), rsTf.getResultRow(j));
				retMapScope.put(rsTf.getString(j, "tran_oper") + "-" + flowScope, rsTf.getResultRow(j));
			}
		}
		Map<String, Map<String, ResultRow>> map = new HashMap<String, Map<String, ResultRow>>();
		map.put("retMapAll", retMapAll);
		map.put("retMapScope", retMapScope);
		return map;
	}

	/**
	 * 获取交易的所有 操作按钮
	 * @param user {@link User} user
	 * @param tranType {@link TranType} 交易类型
	 * @param info_id {@link String} 信息范围
	 * @return
	 */
	protected static Map<String, ResultRow> getTranListButton(User user, TranType tranType, String flowScope) {

		return getTranListButtonAll(user, tranType, flowScope, null).get("retMapAll");
	}

	/**
	 * 获取交易的所有 操作按钮
	 * @param user {@link User} user
	 * @param tranType {@link TranType} 交易类型
	 * @param info_id {@link String} 信息范围
	 * @return
	 */
	protected static Map<String, ResultRow> getTranListButtonScope(User user, TranType tranType, String flowScope) {

		return getTranListButtonAll(user, tranType, flowScope, null).get("retMapScope");
	}

	/**
	 * 获取根据交易状态获取交易下一步操作按钮
	 * @param user
	 * @param tranType
	 * @param tranStatus
	 * @return
	 */
	protected static Map<String, ResultRow> getTranListButtonScope(User user, TranType tranType, String flowScope, TranStatus tranStatus) {

		return getTranListButtonAll(user, tranType, flowScope, tranStatus).get("retMapScope");
	}

	/**
	 * 获取根据交易状态获取交易下一步操作按钮
	 * @param user
	 * @param tranType
	 * @param tranStatus
	 * @return
	 */
	protected static Map<String, ResultRow> getTranListButton(User user, TranType tranType, String flowScope, TranStatus tranStatus) {

		return getTranListButtonAll(user, tranType, flowScope, tranStatus).get("retMapAll");
	}

	/**
	 * 根据交易类型及当前操作员角色获取可操作的交易状态集合
	 * @param user {@link String} 当前用户
	 * @param tranType {@link TranType} 交易类型
	 * @param flowScope {@link String} 适用范围（见cfgflow配置）
	 * @return
	 */
	public TranStatus[] getTranStatusWithRole(User user, TranType tranType, String flowScope) {

		Assert.notNull(tranType, "交易类型不能为空!");

		Map<String, ResultRow> retSet = getTranListButton(user, tranType, flowScope);
		if( null == retSet || retSet.size() == 0 ) {
			Debug.info(logger, "没有为当前用户配置[" + TranType.getValue(tranType) + "]交易角色");
			retSet = new HashMap<String, ResultRow>();
		}

		Object key[] = retSet.keySet().toArray();
		Set<String> trSet = new TreeSet<String>();
		for(int i = 0; i < retSet.size(); i++) {
			String valueSet = retSet.get(key[i]).getString("curr_status");
			trSet.add(valueSet);
		}
		Iterator<String> it = trSet.iterator();
		TranStatus[] tranStatus = new TranStatus[trSet.size()];
		for(int i = 0; i < trSet.size(); i++) {
			tranStatus[i] = TranStatus.getObject((String)it.next());
		}
		return tranStatus;
	}

	/**
	 * 根据交易类型及当前操作员角色获取可操作的交易状态集合
	 * @param user
	 * @param tranType
	 * @return
	 */
	public static String[] getTranStatusOrFlowScope(User user, TranType tranType, String flowScope) {

		Assert.notNull(tranType, "交易类型不能为空!");

		Map<String, ResultRow> retSet = getTranListButtonScope(user, tranType, flowScope);
		if( null == retSet || retSet.size() == 0 ) {
			Debug.info(logger, "没有为当前用户配置[" + TranType.getValue(tranType) + "]交易角色");
			retSet = new HashMap<String, ResultRow>();
		}

		Object key[] = retSet.keySet().toArray();
		Set<String> trSet = new TreeSet<String>();
		for(int i = 0; i < retSet.size(); i++) {
			ResultRow rsRow = retSet.get(key[i]);
			String valueSet = rsRow.getString("curr_status") + rsRow.getString("flow_scope");
			trSet.add(valueSet);
		}
		Iterator<String> it = trSet.iterator();
		String[] tranStatus_fs = new String[trSet.size()];
		for(int i = 0; i < trSet.size(); i++) {
			tranStatus_fs[i] = (String)it.next();
		}
		return tranStatus_fs;
	}

	/**
	 * 获取交易新增的交易目标状态
	 * @param user
	 * @param tranType
	 * @param tranStatus
	 * @return
	 */
	public static TranStatus getTranOperDestStatus(User user, TranType tranType, String flowScope, String tranOper) {

		Assert.notNull(tranType, "交易类型不能为空!");

		Map<String, ResultRow> map = getTranListButtonScope(user, tranType, flowScope);
		ResultRow retRs = map.get(tranOper.toString() + "-" + flowScope);
		if( null == retRs ) {
			throw new BusinessException("未找到[" + TranType.getValue(tranType) + "]交易的[" + PropertyButton.getString(tranOper) + "]操作配置");
		}
		return TranStatus.getObject(retRs.getString("next_status"));
	}

	/**
	 * 获取当前用户配置的流程范围。
	 * 如果配置了特殊流程，其流程范围ID就是DEPTCODE。
	 * 如果使用默认流程，则返回默认流程范围ID
	 * @param user
	 * @param tranType
	 * @param flowScope 当前用户的DeptCode
	 * @return
	 */
	public static String getFlowScope(User user, TranType tranType, String flowScope) {

		Result rs = new Result(new ArrayList<Object>());

		//xc 修改，将mapTranDefineGroup修改为从user中获取
		Map<String, Result> userFlowGroup = user.getUserFlowGroup();
		//这个Map返回了rs，但是不对rs取值，那么为什么需要把rs放到Map里面？简单的放一个TRUE/FALSE不就可以了吗
		rs = userFlowGroup.get(tranType.toString());
		//rs = userFlowGroup.get(tranType + user.getTellID());//组ID或角色ID
		if( rs != null ) {
			//flowScope是deptcode,默认用这个取值是为了取特殊流程，如果取不出来，则意味着没有配置特殊流程，返回默认流程
			String key = user.getRoled_id() + "-" + tranType + "-" + flowScope;
			ResultRow rsTR = mapTranDefineRole.get(key);
			if( rsTR == null ) {//null表示没有特殊流程
				key = user.getRoled_id() + "-" + tranType + "-" + allFlow;
				ResultRow defaultFlow = mapTranDefineRole.get(key);
				if( defaultFlow == null )
					throw new BusinessException("没有为操作员[" + user.getTellID() + "]配置流程，请联系系统管理员！");
				else
					return allFlow;
			}
			else {
				return flowScope;
			}
		}
		else {//为什么可以有等于null的情况存在？填充userFlowGroup的SQL如果取不到数据，难道不是错误吗？或者，在什么情况下取不到数据？
			return flowScope;
		}
	}

	/**
	 *  返回html按钮区域
	 * @param user {@link User} 用户信息
	 * @param tranType {@link TranType} 交易类型
	 * @param operType {@link String} 该值有2个值 ，一个是index，一个是oper
	 * 			index显示首页面按钮，使用的是view_url,该url不能为空
	 * 			oper显示详细页面的按钮，使用的是oper_url,如果该oper_url没有配置，详细页面不显示该按钮
	 * @param flowScope {@link String} 流程范围id，给指段为特殊流程id，会出现两类值，一个是1000000000000，二是机构id
	 * 			1000000000000个为默认流程ID
	 * 			机构id为提示流程
	 * @param tranStatus {@link TranStatus} 交易状态（在次状态下显示的按钮）
	 * @param special_conditions {@link String} 特殊状态下显示不同的按钮
	 * @param listButton {@link List} 不根据交易状态显示的按钮列表
	 * @return
	 */
	protected static Map<String, Object> getButtonListHtml(User user, TranType tranType, String operType, String flowScope, TranStatus tranStatus,
					String special_conditions, List<String> listButton) {

		Map<String, ResultRow> buttonList = getTranListButton(user, tranType, flowScope);

		if( "oper".equals(operType) ) {
			Set<String> viewBut = buttonList.keySet();
			Iterator<String> ItViewBut = viewBut.iterator();
			buttonList = new HashMap<String, ResultRow>();
			for(int i = 0; i < viewBut.size(); i++) {
				String buttonCode = (String)ItViewBut.next();
				Map<String, ResultRow> dd = getTranListButtonScope(user, tranType, flowScope, tranStatus);
				ResultRow rs = dd.get(buttonCode + "-" + flowScope);
				if( null != rs ) {
					buttonList.put(buttonCode, rs);
				}
			}
		}

		Set<String> viewBut = buttonList.keySet();
		Iterator<String> ItViewBut = viewBut.iterator();
		StringBuffer HtmlButton = new StringBuffer();
		for(int i = 0; i < viewBut.size(); i++) {
			String buttonCode = (String)ItViewBut.next();
			String buttonBtn = PropertyButton.getString(buttonCode);
			ResultRow rsRow = buttonList.get(buttonCode);
			String view_url = rsRow.getString("view_url");
			String oper_url = rsRow.getString("oper_url");
			/**
			 * 如果是审核退回，将按钮连接修改为onclick时间
			 */
			if( ("oper".equals(operType) || "view".equals(operType)) ) {
				if( PropertyButton.getAuditRedo_arry().indexOf(buttonCode) != -1 ) {
					oper_url = "putback";
				}
			}

			String strspc = rsRow.getString("special_conditions");
			String strspcView = ObjectUtil.isEmpty(strspc) ? "" : "?strspc=" + strspc;
			String strspcOper = ObjectUtil.isEmpty(strspc) ? "" : "&strspc=" + strspc;

			view_url = view_url + strspcView;
			if( ObjectUtil.isEmpty(view_url) ) {
				throw new BusinessException("流程ID为" + rsRow.getString("flow_oper_id") + "view_url为空，请确认！");
			}
			//如果是操作页面（也就是预览页面），且操作url为空，该页面不显示此按钮
			if( ("oper".equals(operType) || "view".equals(operType)) ) {
				if( ObjectUtil.isEmpty(oper_url) ) {
					continue;
				}
			}
			//结束
			oper_url = oper_url + "?tranOpr=" + buttonCode + strspcOper;

			if( !ObjectUtil.isEmpty(special_conditions) ) {
				if( !special_conditions.equals(strspc) ) {
					continue;
				}
			}
			if( ("oper".equals(operType) || "view".equals(operType)) ) {
				if( PropertyButton.getAddButton_arry().indexOf(buttonCode) != -1 || PropertyButton.getEditButton_arry().indexOf(buttonCode) != -1 ) {
					continue;
				}
			}
			/**
			 * 不根据交易状态、显示的按钮操作按钮以list方式显示
			 */
			if( ("oper".equals(operType) || "view".equals(operType)) ) {
				if( listButton != null ) {
					if( !listButton.contains(buttonCode) ) {
						continue;
					}
				}
			}
			HtmlButton.append("<button class=\"btn\" type=\"button\"");

			/**
			 * 如果配置没有.do按钮以onclick方式进行操作
			 */
			String isflag = "index".equals(operType) ? view_url : oper_url;
			if( isflag.indexOf(".") == -1 ) {
				HtmlButton.append(" onClick=\" ");
				if( "index".equals(operType) ) {
					String[] viewArry = StringUtil.split(view_url, "?");
					if( viewArry.length == 2 ) {
						HtmlButton.append(viewArry[0]).append("('" + viewArry[1] + "')\";");
					}
					else {
						HtmlButton.append(viewArry[0]).append("()\";");
					}

				}
				else {
					String[] operArry = StringUtil.split(oper_url, "?");
					if( operArry.length == 2 ) {
						HtmlButton.append(operArry[0]).append("('" + operArry[1] + "')\";");
					}
					else {
						HtmlButton.append(operArry[0]).append("()\";");
					}
				}
			}
			else {
				HtmlButton.append(" btn_href=\" ");
				HtmlButton.append("index".equals(operType) ? view_url : oper_url).append("\"");
			}
			HtmlButton.append("index".equals(operType) ? "" : "istip=\"1\"");
			if( listButton != null && listButton.size() > 0 ) {
				HtmlButton.append(listButton.contains(buttonCode) ? "force" : "");
			}
			HtmlButton.append(">").append(buttonBtn).append("</button>");
		}
		if( "oper".equals(operType) || "view".equals(operType) ) {
			HtmlButton.append("<button class=\"btn\" type=\"button\"  onClick=\"javascript:history.back()\"; ");
			HtmlButton.append(">").append("返回").append("</button>");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("HtmlButton", HtmlButton.toString());
		return map;
	}

	/**
	 * 查询
	 * @param tranType
	 * @return
	 */
	protected static String getButtonTranStatusHtml(User user, TranType tranType, String flowScope, TranStatus tranStatus) {

		Map<String, ResultRow> buttonList = getTranListButtonScope(user, tranType, flowScope, tranStatus);

		Set<String> viewBut = buttonList.keySet();
		Iterator<String> ItViewBut = viewBut.iterator();
		Map<String, String> mapTSAll = new HashMap<String, String>();
		for(int i = 0; i < viewBut.size(); i++) {

			String buttonCode = (String)ItViewBut.next();
			ResultRow rsRow = buttonList.get(buttonCode);
			String[] butSf = StringUtil.split(buttonCode, "-");
			buttonCode = butSf[0];
			String buttonBtn = PropertyButton.getString(buttonCode);

			String curr_status = rsRow.getString("curr_status");
			String flow_scope = rsRow.getString("flow_scope");

			/**
			 * 添加特殊流程条件，start
			 */
			String strspc = rsRow.getString("special_conditions");
			strspc = ObjectUtil.isEmpty(strspc) ? "" : "-" + strspc;
			/**
			 * 添加特殊流程条件，end 
			 */

			//statr 保存状态显示按钮区域
			StringBuffer butAll = new StringBuffer();
			butAll.append(buttonBtn).append(",");

			/**
			 * xchao-edit添加不根据交易状态控制的按钮
			 */
			if( !TranStatus.BuShouKongZhi.toString().equals(curr_status) ) {
				String mapStr = mapTSAll.get(curr_status + "-" + flow_scope + strspc);
				if( mapStr == null ) {
					mapTSAll.put(curr_status + "-" + flow_scope + strspc, butAll.toString());
				}
				else {
					mapTSAll.put(curr_status + "-" + flow_scope + strspc, butAll.append(mapStr).toString());
				}
				mapTSAll.put(curr_status + "-" + flow_scope + strspc, butAll.toString());
			}
			else {
				String mapStr = mapTSAll.get(TranStatus.BuShouKongZhi.toString() + strspc);
				if( mapStr == null ) {
					mapTSAll.put(TranStatus.BuShouKongZhi.toString() + strspc, butAll.toString());
				}
				else {
					mapTSAll.put(TranStatus.BuShouKongZhi.toString() + strspc, butAll.append(mapStr).toString());
				}
				mapTSAll.put(TranStatus.BuShouKongZhi.toString() + strspc, butAll.toString());
			}
			/**
			 * xchao-edit添加不根据交易状态控制的按钮
			 */
			//end
		}
		StringBuffer HtmlButton = new StringBuffer();
		HtmlButton.append("<div class=\"btn_condition\">");
		Set<String> setTranStatusBut = mapTSAll.keySet();
		Iterator<String> itTranStatusBut = setTranStatusBut.iterator();
		for(int i = 0; i < setTranStatusBut.size(); i++) {
			String currTranStratus = (String)itTranStatusBut.next();
			String TSAllBtn = (String)mapTSAll.get(currTranStratus);
			TSAllBtn = TSAllBtn.substring(0, TSAllBtn.length() - 1);
			HtmlButton.append("<div id='").append(currTranStratus).append("'>").append(TSAllBtn).append("</div>");
		}
		HtmlButton.append("</div>");

		return HtmlButton.toString();
	}

	/***
	 * 首页显示按钮区域
	 * @param tranType 交易类型
	 * @param isDefaultAddButton 是否默认显示新增按钮。
	 * @return
	 */
	private static String getButton(User user, TranType tranType, List<String> listButton) {

		Map<String, Object> map = getButtonListHtml(user, tranType, "index", allFlow, null, "", listButton);
		String buttonTranStatusHtml = getButtonTranStatusHtml(user, tranType, allFlow, null);
		StringBuffer sb = new StringBuffer();
		sb.append(map.get("HtmlButton").toString());
		sb.append(buttonTranStatusHtml);
		return sb.toString();
	}

	/************************************************开发调用***********************************************/

	/**
	 * 没有交易的按钮显示
	 * @param user
	 * @param tranType
	 * @param listButton
	 * @return
	 */
	public static String getButtonHtml(User user, TranType tranType, List<String> listButton) {

		return getButton(user, tranType, listButton);
	}

	public static String getButtonHtmlOper(User user, TranType tranType, List<String> listButton) {

		Map<String, Object> map = getButtonListHtml(user, tranType, "view", allFlow, null, "", listButton);
		return map.get("HtmlButton").toString();
	}

	/**
	 * 默认显示新增按钮(目前是基础数据在用)
	 * @param user
	 * @param tranType
	 * @return
	 */
	public static String getButtonHtmlIndex(User user, TranType tranType) {

		List<String> listButton = new ArrayList<String>();
		listButton.add(PropertyButton.getBaseInfo_AddButton());
		return getButton(user, tranType, listButton);
	}

	/**
	 * 详细信息页面显示按钮
	 * @param tranType 交易类型
	 * @return
	 */
	public static String getButtonHtmlView(User user, TranType tranType, String flowScope, TranStatus tranStatus) {

		Map<String, Object> map = getButtonListHtml(user, tranType, "oper", flowScope, tranStatus, "", null);
		return map.get("HtmlButton").toString();
	}

	public static String getButtonHtmlView(User user, TranType tranType, String flowScope, TranStatus tranStatus, String special_conditions) {

		Map<String, Object> map = getButtonListHtml(user, tranType, "oper", flowScope, tranStatus, special_conditions, null);
		return map.get("HtmlButton").toString();
	}

	public static void main(String[] args) {

	/*User user = new User();
	user.setDeptCode("1000");
	user.setTellID("10000");
	String[] roledArry = new String[] { "SJ" };
	user.setRoled_Arry(roledArry);
	SQLExecutor db = new SQLExecutor();
	System.out.println(CfgFlowEngine.getInstance(user, db).getTranStatusWithRole(TranType.XiaoQuJiuCuo, "1013"));
	//System.out.println(CfgFlowEngine.getInstance(user, db).getTranListButton(user, TranType.JiChuXinXiWeiHu, "100000999888187000"));
	System.out
					.println(CfgFlowEngine.getInstance(user, db).getTranOperDestStatus(TranType.JiChuXinXiWeiHu, "100000999888187000",
									TranOper.BianJi));
	db.close();*/
	}
}
