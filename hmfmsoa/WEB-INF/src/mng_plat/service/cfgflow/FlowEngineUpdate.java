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
 * �����̷����仯�ǵĴ���ʽ
 * <p>��    ��: ��ҵ���ƽ̨�����ڣ�</p>
 * <p>��    ��: </p>
 * <p>��    Ȩ: Copyright (c) 2014</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2014-3-21 ����01:32:54</p>
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

			/*δ��ɵĽ������ʹ�����*/
			List<String> unfinishedTranType = new ArrayList<String>();

			/*������ɫID*/
			Map<String, List<String>> addRoleIdByTranType = new HashMap<String, List<String>>();

			/*ɾ����ɫID*/
			Map<String, List<String>> delRoleIdByTranType = new HashMap<String, List<String>>();

			/**
			 * ���ݽ������Ͳ�ѯ����ʹ�÷�Χ��Ϣ
			 */
			Result rsTranType = TranType.getCodeList();
			for(int i = 0; i < rsTranType.getRowCount(); i++) {

				String tran_type = rsTranType.getString(i, "ci_sp_code");

				/**
				 * �ܻ�ȡ��ɾ������ӵĽ�ɫ
				 * 	�Աȷ�ʽ��1����ѯ��ʱ���б����׵����н�ɫ
				 * 			 2����ѯ�����������еĽ�ɫ
				 * 	��ת��list���жԱ�
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

				/*��ȡ����list�ظ���ֵ*/
				List<String> ListRepeat = new ArrayList<String>();
				for(String kk : listRoidTo) {
					if( listRoidTempTo.contains(kk) ) {
						ListRepeat.add(kk);
					}
				}

				/*������ɫID*/
				List<String> addRoleId = new ArrayList<String>();

				/*ɾ����ɫID*/
				List<String> delRoleId = new ArrayList<String>();

				/*ȥ���ظ��ģ����Ǳ�����Ҫɾ���Ľ�ɫid*/
				for(String kk : listRoidTo) {
					if( !ListRepeat.contains(kk) ) {
						delRoleId.add(kk);
					}
				}
				/*ȥ���ظ��� �����Ǳ�����Ҫ��ӵĽ�ɫID*/
				for(String kk : listRoidTempTo) {
					if( !ListRepeat.contains(kk) ) {
						addRoleId.add(kk);
					}
				}
				addRoleIdByTranType.put(tran_type, addRoleId);
				delRoleIdByTranType.put(tran_type, delRoleId);

				dbo.clear();
				/**
				 * ���ݽ������Ͳ�ѯÿ�����׵����̷�Χ
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
				 * ���ж�ͬһ�������������Ǳ�����ʱ�������Ƿ����
				 * 		1.1 �������� ֱ�ӽ���������ý������͵����������б仯��
				 * 		1.2 �����ȣ����ж�ʹ�÷�Χ�Ƿ�һ��
				 * 			1.2.1 ���ʹ�÷�Χ��һ�� ֱ�ӽ���������ý������͵����������б仯��
				 * 			1.2.2 ���ʹ�÷�Χһ�£��������ҡ����׽�ɫ�������Ϣ��
				 */

				/*�������*/
				if( rsFs.getRowCount() != rsFsTemp.getRowCount() ) {
					unfinishedTranType.add(tran_type);
					continue;
				}

				/*List<String> listFlowID = new ArrayList<String>();//�����������ݿ�һ�µ�flow_id
				List<String> listFlowIDTemp = new ArrayList<String>();//������ʷ���ݿ�һ�µ�flow_id*/
				/*�������*/
				boolean bool = false;
				for(int j = 0; j < rsFs.getRowCount(); j++) {
					String fsTemp = rsFsTemp.getString(j, "flow_scope");

					/*���ݽ������ͣ�ʹ�÷�ΧID��ѯʹ�÷�Χ��Ϣ(��������)*/
					dbo.clear();
					dbo.addSql("select * from flow_scope where tran_type = ? and flow_scope = ? ");
					dbo.addParam(tran_type);
					dbo.addParam(fsTemp);
					Result rsFsTo = dbo.query(db);

					/*���ݽ������ͣ�ʹ�÷�ΧID��ѯ��Χ��Ϣ����ʱ���ݣ�*/
					dbo.clear();
					dbo.addSql("select * from flow_scope_temp where tran_type = ? and flow_scope = ?");
					dbo.addParam(tran_type);
					dbo.addParam(fsTemp);
					Result rsFsTempTo = dbo.query(db);
					/**
					 * �Աȣ�������ǵ�����һ��ȣ���ʾ�����б仯
					 */
					if( rsFsTo.getRowCount() != rsFsTempTo.getRowCount() ) {
						bool = true;
					}
					/*listFlowID.add(rsFsTo.getString(0, "flow_id"));
					listFlowIDTemp.add(rsFsTempTo.getString(0,"flow_id"));*/
				}
				/*����������һ����������ͬ,��ʾ�ý��������б仯��ֱ�ӳ�ѭ������¼�仯�Ľ������ͣ��������������͵�ѭ��*/
				if( bool ) {
					unfinishedTranType.add(tran_type);
					continue;
				}

				/**
				 * �ڡ����ݽ������Ͳ�ѯ ���׽�ɫ������Ϣ
				 * 		2.1 ���жϸý����µĽ��׽�ɫ���������Ƿ�һ��
				 * 		2.2�������һ�£�������ѯ�Ƿ�һ��
				 * 			�����һ�£�ֱ�ӽ�������ʾ�������б仯
				 * 			���һ�£�������ѯ���̿�����Ϣ�Ƿ�һ��
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
					 * ��ѯ���������У���ʱ����û�еģ����û��
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
				 * �� �������2�������һ�½��ŶԱȽ��׿�������
				 * 		���ж������Ƿ�һ�£������һ�£�ֱ�ӽ�������ʾ�����б仯
				 * 		���һ�£���ʾ����û�б仯
				 */

				//�鿴�������ݽ������̿��Ʊ���Ϣ
				dbo.clear();
				dbo.addSql(" select tr.ro_roleid, tr.flow_id, tr.GROUP_ID, tr.tran_role, flow_scope,");
				dbo.addSql(" next_status,tran_oper,curr_status,view_url,");
				dbo.addSql(" nvl(tf.OPER_URL,'isnull')OPER_URL,nvl(tf.special_conditions,'isnull') special_conditions");
				dbo.addSql(" from flow_scope fs,tran_role tr, tran_flow tf ");
				dbo.addSql(" where fs.flow_id = tr.flow_id and tr.tran_role_id = tf.tran_role_id and tran_type = ?");
				dbo.addParam(tran_type);
				Result rsTf = dbo.query(db);

				//�鿴��ʱ�������̿��Ʊ���Ϣ
				dbo.clear();
				dbo.addSql(" select tr.ro_roleid, tr.flow_id, tr.GROUP_ID, tr.tran_role, flow_scope, ");
				dbo.addSql(" next_status,tran_oper,curr_status,view_url, ");
				dbo.addSql("  nvl(tf.OPER_URL,'isnull')OPER_URL,nvl(tf.special_conditions,'isnull') special_conditions ");
				dbo.addSql(" from flow_scope_temp fs,tran_role_temp tr, tran_flow_temp tf ");
				dbo.addSql(" where fs.flow_id = tr.flow_id and tr.tran_role_id = tf.tran_role_id and tran_type = ?");
				dbo.addParam(tran_type);
				Result rsTfTemp = dbo.query(db);

				/*�����̿�����Ϣ�ԣ���ɫiD����id�����׽�ɫ����Χ����һ״̬����ť����ǰ״̬��view��ַ��������ַ���������̣�ΪΨһ��ʶ���жԱȣ�
				 * �����һ�һ�¾���Ϊ�����б仯
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
					 * ʹ�������map����ȡֵ�������ȡ����������Ϊ�����б仯 
					 */
					Object obj = mapTf.get("[" + ro_roleid + "]" + "[" + group_id + "]" + "[" + tran_role + "]" + "[" + flow_scope + "]" + "["
									+ next_status + "]" + "[" + tran_oper + "]" + "[" + curr_status + "]" + "[" + view_url + "]" + "[" + oper_url
									+ "]" + "[" + special_conditions + "]");

					/*
					 * objΪnull��ʶδ��ȡ����ͬ�����̣���bool����Ϊtrue����ѭ���⽫��������add��δ��ɵĽ�������list��
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
		 * ��ѯ�����б仯��δ��ɵĽ���
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
				sb.append("��������Ϊ����").append(TranType.getValue(unfinishedTranType.get(i))).append("��\n\r\n\r");
				for(int j = 0; j < rs.getRowCount(); j++) {
					sb.append("            ");
					sb.append("������Ϊ��").append(rs.getString(j, "batch_no")).append(",");
					sb.append("��ǰ״̬Ϊ��").append(TranStatus.getValue(rs.getString(j, "tran_status"))).append("\n\r\n\r");
				}
				FileUtil.SaveWriteFile(filePath, sb.toString());
			}
		}

		Map<String, List<String>> addRoleId = (Map<String, List<String>>)map.get("addRoleIdByTranType");
		Map<String, List<String>> delRoleId = (Map<String, List<String>>)map.get("delRoleIdByTranType");

		/**
		 * �鿴����������Ҫ��ӵĽ�ɫ��Ϣ
		 */
		if( addRoleId != null ) {
			Object[] addkeyArry = addRoleId.keySet().toArray();
			for(int i = 0; i < addkeyArry.length; i++) {
				String key = (String)addkeyArry[i];
				List<String> list = addRoleId.get(key);
				if( list.size() > 0 ) {
					StringBuffer sb = new StringBuffer();
					sb.append("��").append(TranType.getValue(key)).append("��").append("��Ҫ��ӵĽ�ɫȨ��Ϊ:");
					for(int j = 0; j < list.size(); j++) {
						sb.append(list.get(j)).append("   ");
					}
					FileUtil.SaveWriteFile(filePath, sb.toString());
				}
			}
		}

		/**
		 * �鿴�������Ͷ�����Ҫɾ���Ľ�ɫid��Ϣ
		 */
		if( delRoleId != null ) {
			Object[] delkeyArry = delRoleId.keySet().toArray();
			for(int i = 0; i < delkeyArry.length; i++) {
				String key = (String)delkeyArry[i];
				List<String> list = delRoleId.get(key);
				if( list.size() > 0 ) {
					StringBuffer sb = new StringBuffer();
					sb.append("��").append(TranType.getValue(key)).append("��").append("��Ҫɾ���Ľ�ɫȨ��Ϊ��");
					for(int j = 0; j < list.size(); j++) {
						sb.append(list.get(j)).append("   ");
					}
					FileUtil.SaveWriteFile(filePath, sb.toString());
				}
			}
		}
	}
}
