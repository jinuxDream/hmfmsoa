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
 * <p>��    ��: ��ҵ���ƽ̨�����ڣ�</p>
 * <p>��    ��: </p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2013-5-6 ����11:22:22</p>
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
			throw new AppSystemException("��ʼ��ϵͳ����ʧ��", e);
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

		Assert.hasText(paraName, "�������Ʋ���Ϊ�գ�");
		return StringUtil.getString(getParaValueByName(paraName), defaultValue);
	}

	/**
	 * @param paraName
	 * @return
	 */
	public static String getParaValueByName(String paraName) {

		Assert.hasText(paraName, "�������Ʋ���Ϊ�գ�");
		String retValue = null;
		if( paraMap != null )
			retValue = paraMap.get(paraName);
		if( ObjectUtil.isEmpty(retValue) ) {
			Debug.error(logger, "��ȡ����[" + paraName + "]ʧ��");
		}
		return retValue;
	}

	public static Map<String, String> getParaMap() {

		return paraMap;
	}

}
