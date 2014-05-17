package hmfms.util;

import java.util.*;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: Properties文件配置实用类,对应hmfms.properties</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 下午12:00:18</p>
 * @author 产品开发部
 * @version 2.0
 * Property
 */
public class Property extends AbstractUtil {

	static ResourceBundle resources = loadProperties("hmfms");

	static ResourceBundle loadProperties(String fileName) {

		//Debug.info(logger,"Load Properties. File Name="+fileName);
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

	/**
	 * 返回properties文件中名字为name的字符串数组的值。如果没有找到, 则返回默认值defaultValue.
	 * @param name 名字。
	 * @return 返回properties文件中名字为name的值。如果没有找到, 则返回null.
	 */
	public static String[] getStringArray(String name) {

		try {
			return resources.getStringArray(name);
		}
		catch(Exception e) {
			return null;
		}
	}

	/**
	 * 返回properties文件中名字为name的整数值。如果没有找到, 则返回默认值defaultValue.
	 * @param name 名字。
	 * @param defaultValue 默认值。
	 * @return 返回properties文件中名字为name的值。如果没有找到, 则返回默认值defaultValue.
	 */
	public static int getInt(String name, int defaultValue) {

		try {
			return Integer.parseInt(resources.getString(name));
		}
		catch(Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 返回properties文件中名字为name的长整数值。如果没有找到, 则返回默认值defaultValue.
	 * @param name 名字。
	 * @param defaultValue 默认值。
	 * @return 返回properties文件中名字为name的值。如果没有找到, 则返回默认值defaultValue.
	 */
	public static long getLong(String name, long defaultValue) {

		try {
			return Long.parseLong(resources.getString(name));
		}
		catch(Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 返回properties文件中名字为name的浮点数值。如果没有找到, 则返回默认值defaultValue.
	 * @param name 名字。
	 * @param defaultValue 默认值。
	 * @return 返回properties文件中名字为name的值。如果没有找到, 则返回默认值defaultValue.
	 */

	public static float getFloat(String name, float defaultValue) {

		try {
			return Double.valueOf(resources.getString(name)).floatValue();
		}
		catch(Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 返回properties文件中名字为name的双精度浮点数值。如果没有找到, 则返回默认值defaultValue.
	 * @param name 名字。
	 * @param defaultValue 默认值。
	 * @return 返回properties文件中名字为name的值。如果没有找到, 则返回默认值defaultValue.
	 */
	public static double getDouble(String name, double defaultValue) {

		try {
			return Double.valueOf(resources.getString(name)).doubleValue();
		}
		catch(Exception e) {
			return defaultValue;
		}
	}

}
