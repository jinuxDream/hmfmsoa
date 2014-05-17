package mng_plat.service.cfgflow;

import hmfms.services.codes.TranStatus;
import hmfms.services.codes.TranType;
import hmfms.util.FileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.AppSystemException;
import fd.exception.BusinessException;

/**
 * 当流程发生变化是的处理方式
 * <p>标    题: 物业监管平台（二期）</p>
 * <p>描    述: </p>
 * <p>版    权: Copyright (c) 2014</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2014-3-21 下午01:32:54</p>
 * @author xchao
 * @version 1.1
 */
public class FlowEngineUpdate {

	@SuppressWarnings("unchecked")
	public static Map<String, Object> contrastByFlowEngine() {

		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			SqlOperator dbo = new SqlOperator();

			/*未完成的交易类型代码项*/
			List<String> unfinishedTranType = new ArrayList<String>();

			/*新增角色ID*/
			Map<String, List<String>> addRoleIdByTranType = new HashMap<String, List<String>>();

			/*删除角色ID*/
			Map<String, List<String>> delRoleIdByTranType = new HashMap<String, List<String>>();

			/**
			 * 根据交易类型查询流程使用范围信息
			 */
			Result rsTranType = TranType.getCodeList();
			for(int i = 0; i < rsTranType.getRowCount(); i++) {

				String tran_type = rsTranType.getString(i, "ci_sp_code");

				/**
				 * ④获取被删除或添加的角色
				 * 	对比方式，1、查询临时表中本交易的所有角色
				 * 			 2、查询生产表中所有的角色
				 * 	都转出list进行对比
				 */
				dbo.clear();
				dbo.addSql("select ro_roleid from flow_scope fs,tran_role tr where fs.flow_id = tr.flow_id ");
				dbo.addSql(" and tran_type = ?  group by ro_roleid ");
				dbo.addParam(tran_type);
				Result rsRoid = dbo.query(db);
				List listRoid = rsRoid.toList();
				List<String> listRoidTo = new ArrayList<String>();
				for(int j = 0; j < listRoid.size(); j++) {
					Map m = (Map)listRoid.get(j);
					listRoidTo.add(m.get("ro_roleid").toString());
				}

				dbo.clear();
				dbo.addSql("select ro_roleid from flow_scope_temp fs,tran_role_temp tr where fs.flow_id = tr.flow_id ");
				dbo.addSql(" and tran_type = ?  group by ro_roleid ");
				dbo.addParam(tran_type);
				Result rsRoidTemp = dbo.query(db);
				List listRoidTemp = rsRoidTemp.toList();
				List<String> listRoidTempTo = new ArrayList<String>();
				for(int j = 0; j < listRoidTemp.size(); j++) {
					Map m = (Map)listRoidTemp.get(j);
					listRoidTempTo.add(m.get("ro_roleid").toString());
				}

				/*获取两个list重复的值*/
				List<String> ListRepeat = new ArrayList<String>();
				for(String kk : listRoidTo) {
					if( listRoidTempTo.contains(kk) ) {
						ListRepeat.add(kk);
					}
				}

				/*新增角色ID*/
				List<String> addRoleId = new ArrayList<String>();

				/*删除角色ID*/
				List<String> delRoleId = new ArrayList<String>();

				/*去除重复的，就是本次需要删除的角色id*/
				for(String kk : listRoidTo) {
					if( !ListRepeat.contains(kk) ) {
						delRoleId.add(kk);
					}
				}
				/*去掉重复的 ，就是本次需要添加的角色ID*/
				for(String kk : listRoidTempTo) {
					if( !ListRepeat.contains(kk) ) {
						addRoleId.add(kk);
					}
				}
				addRoleIdByTranType.put(tran_type, addRoleId);
				delRoleIdByTranType.put(tran_type, delRoleId);

				dbo.clear();
				/**
				 * 根据交易类型查询每个交易的流程范围
				 */
				dbo.clear();
				dbo.addSql("select * from flow_scope where tran_type = ? ");
				dbo.addParam(tran_type);
				Result rsFs = dbo.query(db);

				dbo.clear();
				dbo.addSql("select * from flow_scope_temp where tran_type = ?");
				dbo.addParam(tran_type);
				Result rsFsTemp = dbo.query(db);
				/**
				 * ①判断同一个交易类型正是表与临时表行数是否相等
				 * 		1.1 如果不相等 直接结束（代表该交易类型的流程配置有变化）
				 * 		1.2 如果相等，再判断使用范围是否一致
				 * 			1.2.1 如果使用范围不一致 直接结束（代表该交易类型的流程配置有变化）
				 * 			1.2.2 如果使用范围一致，继续查找【交易角色定义表】信息。
				 */

				/*条数相等*/
				if( rsFs.getRowCount() != rsFsTemp.getRowCount() ) {
					unfinishedTranType.add(tran_type);
					continue;
				}

				/*List<String> listFlowID = new ArrayList<String>();//保存生产数据库一致的flow_id
				List<String> listFlowIDTemp = new ArrayList<String>();//保存历史数据库一致的flow_id*/
				/*条数相等*/
				boolean bool = false;
				for(int j = 0; j < rsFs.getRowCount(); j++) {
					String fsTemp = rsFsTemp.getString(j, "flow_scope");

					/*根据交易类型，使用范围ID查询使用范围信息(生产数据)*/
					dbo.clear();
					dbo.addSql("select * from flow_scope where tran_type = ? and flow_scope = ? ");
					dbo.addParam(tran_type);
					dbo.addParam(fsTemp);
					Result rsFsTo = dbo.query(db);

					/*根据交易类型，使用范围ID查询范围信息（临时数据）*/
					dbo.clear();
					dbo.addSql("select * from flow_scope_temp where tran_type = ? and flow_scope = ?");
					dbo.addParam(tran_type);
					dbo.addParam(fsTemp);
					Result rsFsTempTo = dbo.query(db);
					/**
					 * 对比，如果他们的行数一相等，表示流程有变化
					 */
					if( rsFsTo.getRowCount() != rsFsTempTo.getRowCount() ) {
						bool = true;
					}
					/*listFlowID.add(rsFsTo.getString(0, "flow_id"));
					listFlowIDTemp.add(rsFsTempTo.getString(0,"flow_id"));*/
				}
				/*如果上面的有一个条数不相同,表示该交易流程有变化，直接出循环，记录变化的交易类型，并跳出交易类型的循环*/
				if( bool ) {
					unfinishedTranType.add(tran_type);
					continue;
				}

				/**
				 * ②、根据交易类型查询 交易角色定义信息
				 * 		2.1 先判断该交易下的交易角色定义行数是否一致
				 * 		2.2如果行数一致，逐条查询是否一致
				 * 			如果不一致，直接结束，表示该流程有变化
				 * 			如果一致，继续查询流程控制信息是否一致
				 */
				dbo.clear();
				dbo.addSql("select tr.*,flow_scope from flow_scope fs,tran_role tr where fs.flow_id = tr.flow_id and tran_type = ? ");
				dbo.addParam(tran_type);
				Result rsTr = dbo.query(db);

				dbo.clear();
				dbo.addSql("select tr.*,flow_scope from flow_scope_temp fs,tran_role_temp tr where fs.flow_id = tr.flow_id and tran_type = ? ");
				dbo.addParam(tran_type);
				Result rsTrTemp = dbo.query(db);
				String[] col_key = { "ro_roleid", "group_id", "tran_role", "flow_scope" };
				Map map = rsTrTemp.genMapedResult(col_key);

				if( rsTr.getRowCount() != rsTrTemp.getRowCount() ) {
					unfinishedTranType.add(tran_type);
					continue;
				}
				bool = false;
				for(int j = 0; j < rsTr.getRowCount(); j++) {
					String ro_roleId = rsTr.getString(j, "ro_roleid");
					String group_id = rsTr.getString(j, "group_id");
					String tran_role = rsTr.getString(j, "tran_role");
					String flow_scope = rsTr.getString(j, "flow_scope");
					Object obj = map.get("[" + ro_roleId + "]" + "[" + group_id + "]" + "[" + tran_role + "]" + "[" + flow_scope + "]");
					/**
					 * 查询生产数据有，临时数据没有的，如果没有
					 */
					if( obj == null ) {
						bool = true;
					}
				}
				if( bool ) {
					unfinishedTranType.add(tran_type);
					continue;
				}

				/**
				 * ③ 如果上面2中情况都一致接着对比交易控制流程
				 * 		先判断行数是否一致，如果不一致，直接结束，表示流程有变化
				 * 		如果一致，表示流程没有变化
				 */

				//查看生产数据交易流程控制表信息
				dbo.clear();
				dbo.addSql(" select tr.ro_roleid, tr.flow_id, tr.GROUP_ID, tr.tran_role, flow_scope,");
				dbo.addSql(" next_status,tran_oper,curr_status,view_url,");
				dbo.addSql(" nvl(tf.OPER_URL,'isnull')OPER_URL,nvl(tf.special_conditions,'isnull') special_conditions");
				dbo.addSql(" from flow_scope fs,tran_role tr, tran_flow tf ");
				dbo.addSql(" where fs.flow_id = tr.flow_id and tr.tran_role_id = tf.tran_role_id and tran_type = ?");
				dbo.addParam(tran_type);
				Result rsTf = dbo.query(db);

				//查看临时表交易流程控制表信息
				dbo.clear();
				dbo.addSql(" select tr.ro_roleid, tr.flow_id, tr.GROUP_ID, tr.tran_role, flow_scope, ");
				dbo.addSql(" next_status,tran_oper,curr_status,view_url, ");
				dbo.addSql("  nvl(tf.OPER_URL,'isnull')OPER_URL,nvl(tf.special_conditions,'isnull') special_conditions ");
				dbo.addSql(" from flow_scope_temp fs,tran_role_temp tr, tran_flow_temp tf ");
				dbo.addSql(" where fs.flow_id = tr.flow_id and tr.tran_role_id = tf.tran_role_id and tran_type = ?");
				dbo.addParam(tran_type);
				Result rsTfTemp = dbo.query(db);

				/*将流程控制信息以（角色iD、组id、交易角色、范围、下一状态、按钮、当前状态、view地址、操作地址、特殊流程）为唯一标识进行对比，
				 * 如果有一项不一致就认为流程有变化
				 */
				String[] col_key_tf = { "ro_roleid", "group_id", "tran_role", "flow_scope", "next_status", "tran_oper", "curr_status", "view_url",
								"oper_url", "special_conditions" };
				Map mapTf = rsTfTemp.genMapedResult(col_key_tf);

				if( rsTf.getRowCount() != rsTfTemp.getRowCount() ) {
					unfinishedTranType.add(tran_type);
					continue;
				}

				bool = false;
				for(int j = 0; j < rsTf.getRowCount(); j++) {
					String ro_roleid = rsTf.getString(j, "ro_roleid");
					String group_id = rsTf.getString(j, "group_id");
					String tran_role = rsTf.getString(j, "tran_role");
					String flow_scope = rsTf.getString(j, "flow_scope");
					String next_status = rsTf.getString(j, "next_status");
					String tran_oper = rsTf.getString(j, "tran_oper");
					String curr_status = rsTf.getString(j, "curr_status");
					String view_url = rsTf.getString(j, "view_url");
					String oper_url = rsTf.getString(j, "oper_url");
					String special_conditions = rsTf.getString(j, "special_conditions");
					/*
					 * 使用上面的map进行取值，如果获取不到，就认为流程有变化 
					 */
					Object obj = mapTf.get("[" + ro_roleid + "]" + "[" + group_id + "]" + "[" + tran_role + "]" + "[" + flow_scope + "]" + "["
									+ next_status + "]" + "[" + tran_oper + "]" + "[" + curr_status + "]" + "[" + view_url + "]" + "[" + oper_url
									+ "]" + "[" + special_conditions + "]");

					/*
					 * obj为null标识未获取到相同的流程，将bool设置为true，在循环外将交易类型add到未完成的交易类型list中
					 */
					if( obj == null ) {
						bool = true;
					}
				}
				if( bool ) {
					unfinishedTranType.add(tran_type);
					continue;
				}
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("unfinishedTranType", unfinishedTranType);
			map.put("addRoleIdByTranType", addRoleIdByTranType);
			map.put("delRoleIdByTranType", delRoleIdByTranType);
			return map;
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

	public static String filePath = "d:/1.txt";

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Map<String, Object> map = contrastByFlowEngine();
		List<String> unfinishedTranType = (List<String>)map.get("unfinishedTranType");

		/**
		 * 查询流程有变化的未完成的交易
		 */
		if( unfinishedTranType.size() > 0 ) {
			SqlOperator dbo = new SqlOperator();
			for(int i = 0; i < unfinishedTranType.size(); i++) {
				dbo.clear();
				dbo.addSql("select * from trade where tran_status not in(?,?) and tran_type = ?");
				dbo.addParam(TranStatus.JiaoYiWanCheng.toString());
				dbo.addParam(TranStatus.CheXiao.toString());
				dbo.addParam(unfinishedTranType.get(i));
				dbo.addSql(" order by tran_type");
				Result rs = dbo.query(true);
				StringBuffer sb = new StringBuffer();
				sb.append("交易类型为：【").append(TranType.getValue(unfinishedTranType.get(i))).append("】\n\r\n\r");
				for(int j = 0; j < rs.getRowCount(); j++) {
					sb.append("            ");
					sb.append("申请编号为：").append(rs.getString(j, "batch_no")).append(",");
					sb.append("当前状态为：").append(TranStatus.getValue(rs.getString(j, "tran_status"))).append("\n\r\n\r");
				}
				FileUtil.SaveWriteFile(filePath, sb.toString());
			}
		}

		Map<String, List<String>> addRoleId = (Map<String, List<String>>)map.get("addRoleIdByTranType");
		Map<String, List<String>> delRoleId = (Map<String, List<String>>)map.get("delRoleIdByTranType");

		/**
		 * 查看交易类型需要添加的角色信息
		 */
		if( addRoleId != null ) {
			Object[] addkeyArry = addRoleId.keySet().toArray();
			for(int i = 0; i < addkeyArry.length; i++) {
				String key = (String)addkeyArry[i];
				List<String> list = addRoleId.get(key);
				if( list.size() > 0 ) {
					StringBuffer sb = new StringBuffer();
					sb.append("【").append(TranType.getValue(key)).append("】").append("需要添加的角色权限为:");
					for(int j = 0; j < list.size(); j++) {
						sb.append(list.get(j)).append("   ");
					}
					FileUtil.SaveWriteFile(filePath, sb.toString());
				}
			}
		}

		/**
		 * 查看交易类型对于需要删除的角色id信息
		 */
		if( delRoleId != null ) {
			Object[] delkeyArry = delRoleId.keySet().toArray();
			for(int i = 0; i < delkeyArry.length; i++) {
				String key = (String)delkeyArry[i];
				List<String> list = delRoleId.get(key);
				if( list.size() > 0 ) {
					StringBuffer sb = new StringBuffer();
					sb.append("【").append(TranType.getValue(key)).append("】").append("需要删除的角色权限为：");
					for(int j = 0; j < list.size(); j++) {
						sb.append(list.get(j)).append("   ");
					}
					FileUtil.SaveWriteFile(filePath, sb.toString());
				}
			}
		}
	}
}
