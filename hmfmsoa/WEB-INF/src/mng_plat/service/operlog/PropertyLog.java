/**
 * 
 */
package mng_plat.service.operlog;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p>标    题: 物业监管平台（二期）</p>
 * <p>描    述: </p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2013-5-6 下午02:40:01</p>
 * @author xchao
 * @version 1.1
 */
public class PropertyLog {

	static ResourceBundle resources = loadProperties("operlog");

	static ResourceBundle loadProperties(String fileName) {

		return ResourceBundle.getBundle(fileName, Locale.getDefault());
	}

	/**
	 * 返回properties文件中名字为name的字符串值。如果没有找到, 则返回默认值defaultValue.
	 * @param name 名字。
	 * @param defaultValue 默认值。
	 * @return 返回properties文件中名字为name的值。如果没有找到, 则返回默认值defaultValue.
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
