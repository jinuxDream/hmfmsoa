package mng_plat.service.cfg;

import hmfms.util.Debug;
import hmfms.util.ObjectUtil;
import hmfms.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.AppSystemException;
import fd.util.Assert;

/**
 * <p>标    题: 物业监管平台（二期）</p>
 * <p>描    述: </p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2013-5-6 上午11:22:22</p>
 * @author xchao
 * @version 1.1
 */
public class CfgSysPara {

	private static final Log logger = LogFactory.getLog(CfgSysPara.class);

	private static Map<String, String> paraMap;

	static {
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			paraMap = new HashMap<String, String>();
			SqlOperator dbo = new SqlOperator();

			dbo.addSql("select * from sys_para");
			Result rsPara = dbo.query(db);
			for(int i = 0; i < rsPara.getRowCount(); i++) {
				paraMap.put(rsPara.getString(i, "para_name").trim(), rsPara.getString(i, "para_value").trim());
			}
		}
		catch(Exception e) {
			throw new AppSystemException("初始化系统参数失败", e);
		}
		finally {
			if( db != null )
				db.close();
		}
	}

	/**
	 * @param paraName
	 * @param defaultValue
	 * @return
	 */
	public static String getParaValueByName(String paraName, String defaultValue) {

		Assert.hasText(paraName, "参数名称不能为空！");
		return StringUtil.getString(getParaValueByName(paraName), defaultValue);
	}

	/**
	 * @param paraName
	 * @return
	 */
	public static String getParaValueByName(String paraName) {

		Assert.hasText(paraName, "参数名称不能为空！");
		String retValue = null;
		if( paraMap != null )
			retValue = paraMap.get(paraName);
		if( ObjectUtil.isEmpty(retValue) ) {
			Debug.error(logger, "获取参数[" + paraName + "]失败");
		}
		return retValue;
	}

	public static Map<String, String> getParaMap() {

		return paraMap;
	}

}
