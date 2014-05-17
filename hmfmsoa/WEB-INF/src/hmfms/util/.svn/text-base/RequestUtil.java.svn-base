package hmfms.util;

import fd.exception.AppSystemException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: </p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 下午12:31:07</p>
 * @author 产品开发部
 * @version 2.0
 * RequestUtil
 */
public class RequestUtil extends AbstractUtil {

	private static final Log logger = LogFactory.getLog(RequestUtil.class);
	public static final String ALL_SUBMIT_INFO = "GLB_ALL_SUBMIT_INFO";

	public static void goError(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {

		request.setAttribute("msg_error", msg);
		request.getRequestDispatcher("/jsp/common/error.jsp").forward(request, response);
	}

	/**
	 * 获取request内数组类型的参数
	 * @param request
	 * @param name
	 * @return
	 */
	public static String[] getArrayParam(HttpServletRequest request, String name) {

		return request.getParameterValues(name);
	}

	/**
	 * 获取request内字符串类型的参数，null值转换转换成空串
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getStringParam(HttpServletRequest request, String name) {

		String tmp_name = (String)request.getParameter(name);
		Debug.debug(logger, name + "=" + tmp_name);
		if( tmp_name == null )
			tmp_name = "";
		return tmp_name.trim();
	}

	/**
	 * 获取服务器端地址完整路径
	 * @param request
	 * @return
	 */
	public static String getServerPath(HttpServletRequest request) {

		String path = "http://";
		path += request.getServerName() + ":" + request.getServerPort();
		path += request.getContextPath() + "/";
		return path;
	}

	/**
	 * 例如：<br>
	 * jsp/common/ok.jsp?tips=操作成功&returnpage=/jsp/a0101/index.do<Br>
	 * 中的?后面的所有信息再次redirect，则需要把所有等号后面的串encode
	 * 
	 * @param str String 为问号后面的整个串
	 * @return 返回encode后的整个串，并前缀问号
	 */
	public static String encodeFullURL(String str) {

		StringBuffer buf = new StringBuffer("?");
		String[] paramPair = StringUtil.split(str, "&");
		for(int i = 0; i < paramPair.length; i++) {
			String[] pair = StringUtil.split(paramPair[i], "=");
			if( pair.length != 2 )
				throw new RuntimeException("不支持的字符串！原串为【" + str + "】，不支持的子串为【" + paramPair[i] + "】");
			buf.append(pair[0]).append('=').append(encodeURL(pair[1])).append('&');
		}
		buf.deleteCharAt(buf.length() - 1);
		return buf.toString();
	}

	/**
	 * 使用"GBK"将字符串转换为 application/x-www-form-urlencoded 格式
	 * @param str
	 * @return
	 */
	public static String encodeURL(String str) {

		try {
			String newstr = URLEncoder.encode(str, "GBK");
			return newstr;
			// return Base64.encode(newstr);
		}
		catch(UnsupportedEncodingException e) {
			// 应该改为自己的方式处理 ，比如统一把#这样的字符换为|FD|，decodeURL中再换回来
			return str;
		}
	}

	/**
	 * 使用"GBK"对 application/x-www-form-urlencoded 字符串解码
	 * @param str
	 * @return
	 */
	public static String decodeURL(String str) {

		try {
			// String newstr = Base64.decode(str);
			return URLDecoder.decode(str, "GBK");
		}
		catch(UnsupportedEncodingException e) {
			// 应该改为自己的方式处理 ，比如统一把#这样的字符换为|FD|，decodeURL中再换回来
			return str;
		}
	}

	/**
	 * 把页面GET/POST上来的所有Parameter全部转化为hidden域后放入request.setAttribute<br>
	 * 后续页面可以使用request.getAttribute(RequestUtil.ALL_SUBMIT_INFO)取得。
	 * @param request
	 */
	public static void pageParamsForward(HttpServletRequest request) {

		StringBuffer hiddens = new StringBuffer();
		Map paramMap = request.getParameterMap();
		Set params = paramMap.entrySet();
		for(Iterator it = params.iterator(); it.hasNext();) {
			Entry param = (Entry)it.next();
			String name = (String)param.getKey();
			String[] raw_values = (String[])param.getValue();
			for(int i = 0; i < raw_values.length; i++) {
				hiddens.append("<input type=\"hidden\" name=\"").append(name).append('"');
				hiddens.append(" value=\"").append(raw_values[i]).append("\">\n");
			}
		}
		request.setAttribute(ALL_SUBMIT_INFO, hiddens.toString());
	}

	/**
	 * 从request内复制Parameter到相应的实体内
	 * @param request
	 * @param bean 目标实体
	 */
	public static void copyProperties(HttpServletRequest request, Object bean) {

		Map paramMap = request.getParameterMap();
		Set params = paramMap.entrySet();
		for(Iterator it = params.iterator(); it.hasNext();) {
			Entry param = (Entry)it.next();
			String name = (String)param.getKey();
			String[] raw_values = (String[])param.getValue();
			String value = raw_values[0];
			try {

				if( bean instanceof Map ) {
					BeanUtil.setMapProperty((Map)bean, name, raw_values);
				}
				else {
					BeanUtil.setBeanProperty(bean, name, value);
				}
			}
			catch(RuntimeException e) {
				//e.printStackTrace();
			}
		}

	}

	/**
	 * 将request内的数组参数某下标的值拷贝至一个对象中
	 * @param request
	 * @param request内数组参数的下标，以0开始
	 * @param bean 目标实体
	 */
	public static void copyProperties(HttpServletRequest request, int index, Object bean) {

		Enumeration paramEnum = request.getParameterNames();
		while( paramEnum.hasMoreElements() ) {
			String name = (String)paramEnum.nextElement();
			String[] values = request.getParameterValues(name);
			for(int i = 0; i < values.length; i++) {
				if( i == index ) {
					try {
						if( bean instanceof Map ) {
							BeanUtil.setMapProperty((Map)bean, name, values);
						}
						else {
							BeanUtil.setBeanProperty(bean, name, values[i]);
						}
					}
					catch(RuntimeException e) {
						//e.printStackTrace();
					}
				}

			}
		}

	}

	/**
	 * 将request内的数组参数拷贝至一个对象List中
	 * @param request
	 * @param className 返回list内需要包含的类型class对象
	 * @return 返回一个包含的类型class对象
	 */
	public static List copyProperties(HttpServletRequest request, Class className) {

		List retList = new ArrayList();
		Map temp = new HashMap();
		Enumeration paramEnum = request.getParameterNames();
		while( paramEnum.hasMoreElements() ) {
			String name = (String)paramEnum.nextElement();
			String[] values = request.getParameterValues(name);
			for(int i = 0; i < values.length; i++) {

				Object bean = temp.get(i);
				if( bean == null ) {
					try {
						bean = className.newInstance();
					}
					catch(Exception e) {
						throw new AppSystemException("创建"+className.getName()+"对象出错",e);
					}
				}
				try {
					if( bean instanceof Map ) {
						BeanUtil.setMapProperty((Map)bean, name, values);
					}
					else {
						BeanUtil.setBeanProperty(bean, name, values[i]);
					}
				}
				catch(Exception e) {
					//e.printStackTrace();
				}
				temp.put(i, bean);
			}
		}
		for(int i = 0; i < temp.size(); i++) {
			retList.add(temp.get(i));
		}

		return retList;
	}

	/**
	 * 从request内复制Parameter到相应的实体内
	 * 如果bean中的属性是String，则本方法会将其设置为""，如果为数字，则设置为0。
	 * @param request
	 * @param bean
	 */
	public static void copyPropertiesOnPage(HttpServletRequest request, Object bean) {

		Map paramMap = request.getParameterMap();
		Set params = paramMap.entrySet();
		for(Iterator it = params.iterator(); it.hasNext();) {
			Entry param = (Entry)it.next();
			String name = (String)param.getKey();
			String[] raw_values = (String[])param.getValue();
			String value = raw_values[0];
			try {
				if( bean instanceof Map ) {
					BeanUtil.setMapProperty((Map)bean, name, raw_values);
				}
				else {
					if( value == null )
						value = "";
					BeanUtil.setBeanProperty(bean, name, value);
				}
			}
			catch(RuntimeException e) {
				// 无此属性，do nothing
			}
		}
	}

}
