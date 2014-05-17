package hmfms.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 获取配置信息。可代替struts的tag,对应application.properties</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 下午12:01:33</p>
 * @author 产品开发部
 * @version 2.0
 * PropertyUtil
 */
public class PropertyUtil extends AbstractUtil {

	static private ResourceBundle application = ResourceBundle.getBundle("application");

	public static String getMessage(String key) {

		return getMessage(key, null);
	}

	public static String getMessage(String key, String[] args) {

		String message;
		try {
			message = application.getString(key);
		}
		catch(MissingResourceException mse) {
			message = "can not find key:" + key;
		}
		if( args != null )
			message = MessageFormat.format(message, (Object[])args);
		return message;
	}
}
