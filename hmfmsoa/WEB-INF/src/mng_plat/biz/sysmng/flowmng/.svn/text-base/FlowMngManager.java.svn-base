package mng_plat.biz.sysmng.flowmng;

import fd.commons.TableEntity;
import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.AppSystemException;
import fd.exception.BusinessException;
import hmfms.base.BaseDeal;
import hmfms.services.key.PrimayKeyGener;
import hmfms.services.util.SQLExecuteUtil;
import hmfms.web.User;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import hmfms.services.codes.IsFlag;
import hmfms.services.codes.WorkGroupType;

import org.apache.commons.beanutils.PropertyUtils;

public class FlowMngManager extends BaseDeal {

	public Map<String, Result> getRole(User user) {

		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			Map<String, Result> map = new HashMap<String, Result>();
			SqlOperator dbo = new SqlOperator();
			dbo.addSql("select ro_roleid ci_sp_code,ro_name ci_sp_name from role");
			Result rsRole = dbo.query(db);

			map.put("rsRole", rsRole);

			dbo.clear();
			dbo.addSql("select org_id ci_sp_code,org_name ci_sp_name from org_info");
			Result rsOrg = dbo.query(db);

			map.put("rsOrg", rsOrg);

			dbo.clear();
			dbo.addSql("select group_id ci_sp_code,group_name ci_sp_name from workgroup where workgroup_type = ?");
			dbo.addParam(WorkGroupType.ShenPiLiuChengZu);
			Result rsWorkGroup = dbo.query(db);

			map.put("rsWorkGroup", rsWorkGroup);

			return map;
		}
		catch(Exception e) {
			if( db != null )
				db.rollback();
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

	protected int saveByMap(String tableName, Map<String, String> data, SQLExecutor db) {

		TableEntity entity = SQLExecuteUtil.getTableEntityByTableName(tableName);
		StringBuffer sqlInsert = new StringBuffer();
		sqlInsert.append("insert into ").append(tableName).append("(");
		PropertyDescriptor propertys[] = PropertyUtils.getPropertyDescriptors(entity);
		int len = propertys.length;
		StringBuffer values = new StringBuffer();

		for(int i = 0; i < len; i++) {
			String name = propertys[i].getName();
			Object value = data.get(name);
			if( !PropertyUtils.isWriteable(entity, name) || (value == null) )
				continue;
			sqlInsert.append(name).append(",");
			values.append("?").append(",");
			db.addParam(value);
		}
		sqlInsert.deleteCharAt(sqlInsert.length() - 1);
		values.deleteCharAt(values.length() - 1);

		sqlInsert.append(" ) values ( ").append(values).append(")");
		db.execute(sqlInsert.toString());
		return db.getNumRecordsUpdated();

	}

	/**
	 * 新增注册数据
	 * @param batchNo	
	 * @param infoType
	 * @param mapArr
	 * @param dto
	 * @param db
	 */
	public void addRegInfo(Map<String, Map<String, String>>[] mapArr) {

		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			Map<String, String> map = null;
			Iterator<String> it = null;
			String flow_id = PrimayKeyGener.getNextId(db);
			String tran_role_id = PrimayKeyGener.getNextId(db);
			for(int i = 0; i < mapArr.length; i++) {
				it = mapArr[i].keySet().iterator();
				while( it.hasNext() ) {
					String tableName = it.next();
					map = mapArr[i].get(tableName);
					map.put("flow_id", flow_id);
					map.put("tran_role_id", tran_role_id);
					map.put("flow_oper_id", PrimayKeyGener.getNextId(db));
					map.put("is_enable", IsFlag.Fou.toString());
					saveByMap(tableName, map, db);
				}
			}
		}
		catch(Exception e) {
			if( db != null )
				db.rollback();
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
