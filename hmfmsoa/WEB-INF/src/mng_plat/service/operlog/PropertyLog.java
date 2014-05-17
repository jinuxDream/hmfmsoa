/**
 * 
 */
package mng_plat.service.operlog;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p>��    ��: ��ҵ���ƽ̨�����ڣ�</p>
 * <p>��    ��: </p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2013-5-6 ����02:40:01</p>
 * @author xchao
 * @version 1.1
 */
public class PropertyLog {

	static ResourceBundle resources = loadProperties("operlog");

	static ResourceBundle loadProperties(String fileName) {

		return ResourceBundle.getBundle(fileName, Locale.getDefault());
	}

	/**
	 * ����properties�ļ�������Ϊname���ַ���ֵ�����û���ҵ�, �򷵻�Ĭ��ֵdefaultValue.
	 * @param name ���֡�
	 * @param defaultValue Ĭ��ֵ��
	 * @return ����properties�ļ�������Ϊname��ֵ�����û���ҵ�, �򷵻�Ĭ��ֵdefaultValue.
	 */
	public static String getString(String name, String defaultValue) {

		try {
			return resources.getString(name);
		}
		catch(Exception e) {
			return defaultValue;
		}
	}
}
