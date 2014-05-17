package hmfms.util;

import fd.commons.jdbc.Result;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: BEAN操作实用类</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:48:57</p>
 * @author 产品开发部
 * @version 2.0
 * BeanUtil
 */
public final class BeanUtil extends AbstractUtil {
	private static final Log logger = LogFactory.getLog(BeanUtil.class);
	/**
	 * 按某列对result对象内的行进行升序排序
	 * @param result 要进行排序的result对象
	 * @param sortColumn 排序列
	 */
	public static void sortResult(Result result, final String sortColumn) {

		sortResult(result, sortColumn, "");
	}

	/**
	 * @param result 要进行排序的result对象
	 * @param sortColumn 排序列
	 * @param desc 升序或降序，空代表升序
	 */
	public static void sortResult(Result result, final String sortColumn, final String desc) {

		List list = result.toList();
		java.util.Collections.sort(list, new java.util.Comparator() {

			public final int compare(Object a, Object b) {

				final Object rowA = ((java.util.Map)a).get(sortColumn);
				final Object rowB = ((java.util.Map)b).get(sortColumn);

				int xiao = -1;
				int da = 1;
				if( rowA == null && rowB == null )
					return 0;
				if( rowA == null )
					return xiao;
				if( rowB == null )
					return da;
				int compareResult = compareTo(rowA, rowB);
				return compareResult;
			}
		});
		if( desc != null && desc.equalsIgnoreCase("desc") ) {
			java.util.Collections.reverse(list);
		}
	}

	/**
	 * 复制对象中同名的属性至目标对象
	 * @param source 被复制的对象
	 * @param dest 目标对象
	 * @return
	 */
	public static String copyObject(Object source, Object dest) {

		try {
			BeanUtils.copyProperties(dest, source);
			return null;
		}
		catch(Exception e) {
			Debug.exception(logger,e);
			return e.getMessage();
		}
	}

	/**
	 * 复制map中的同名值到目标对象
	 * @param source 被复制的map
	 * @param dest 目标对象
	 * @return
	 */
	public static String copyObjectByMap(Map source, Object dest) {

		try {
			Iterator itNew = source.keySet().iterator();
			while( itNew.hasNext() ) {
				String colname = (String)itNew.next();
				//Debug.info(logger,colname + "=" + source.get(colname));
				//Debug.info(logger,colname + "=" + PropertyUtils.getPropertyType(dest, colname));
				Class clzDest = PropertyUtils.getPropertyType(dest, colname);
				if( clzDest == null )
					continue;
				//Debug.info(logger,colname + "=" + clz.getName());
				Object value = source.get(colname);
				if( value == null )
					continue;
				if( clzDest.getName().endsWith("String") )
					value = value.toString();
				else if( clzDest.getName().endsWith("Long") )
					value = new Long(value.toString());
				else if( clzDest.getName().endsWith("BigDecimal") )
					value = new java.math.BigDecimal(value.toString());
				else if( clzDest.getName().endsWith("Integer") )
					value = new Integer(value.toString());
				else
					throw new RuntimeException("不支持的数据类型转化！" + clzDest.getName());

				PropertyUtils.setSimpleProperty(dest, colname, value);
			}
			return null;
		}
		catch(Exception e) {
			Debug.exception(logger,e);
			return e.getMessage();
		}
	}

	/**
	 * 比较两个对象各属性值是否相等
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean compareObjectProperties(Object obj1, Object obj2) {

		try {
			if( obj1 == null || obj2 == null )
				return false;
			PropertyDescriptor[] obj1Descriptors = PropertyUtils.getPropertyDescriptors(obj1);
			//PropertyDescriptor[] obj2Descriptors = PropertyUtils.getPropertyDescriptors(obj2);
			int len = obj1Descriptors.length;
			for(int i = 0; i < len; i++) {
				String name = obj1Descriptors[i].getName();
				Object valueObj1 = null, valueObj2 = null;
				//Debug.info(logger,name + " type=" + PropertyUtils.getPropertyType(obj1, name));
				if( PropertyUtils.isWriteable(obj1, name) ) {
					valueObj1 = PropertyUtils.getSimpleProperty(obj1, name);
					valueObj2 = PropertyUtils.getSimpleProperty(obj2, name);
					if( valueObj1 == null && valueObj2 == null )
						continue;
					else if( valueObj1 == null )
						return false;
					else if( valueObj2 == null )
						return false;
					boolean compareResult = compareValue(valueObj1, valueObj2);
					//Debug.info(logger,name + " valueObj1 type=" + PropertyUtils.getPropertyType(obj1, name) + ", value=" + valueObj1);
					//Debug.info(logger,name + " valueObj2 type=" + PropertyUtils.getPropertyType(obj2, name) + ", value=" + valueObj2);
					if( !compareResult )
						return false;
				}
			}
			return true;
		}
		catch(Exception e) {
			throw new RuntimeException("compareObjectProperties Exception.", e);
		}

	}

	/**
	 * 比较日期、字符串、包装类等对象值是否相等
	 * @param valueObj1
	 * @param valueObj2
	 * @return
	 */
	private static boolean compareValue(Object valueObj1, Object valueObj2) {

		if( valueObj1 instanceof Integer ) {
			if( !(valueObj2 instanceof Integer) )
				return false;
			int value1 = ((Integer)valueObj1).intValue();
			int value2 = ((Integer)valueObj2).intValue();
			return (value1 == value2);
		}
		else if( valueObj1 instanceof Short ) {
			if( !(valueObj2 instanceof Short) )
				return false;
			short sh1 = ((Short)valueObj1).shortValue();
			short sh2 = ((Short)valueObj2).shortValue();
			return (sh1 == sh2);
		}
		else if( valueObj1 instanceof String ) {
			if( !(valueObj2 instanceof String) )
				return false;
			String value1 = ((String)valueObj1);
			String value2 = ((String)valueObj2);
			return (value1.equals(value2));
		}
		else if( valueObj1 instanceof java.math.BigDecimal ) {
			if( !(valueObj2 instanceof java.math.BigDecimal) )
				return false;
			java.math.BigDecimal value1 = ((java.math.BigDecimal)valueObj1);
			java.math.BigDecimal value2 = ((java.math.BigDecimal)valueObj2);
			return ((value1.compareTo(value2)) == 0);
		}
		else if( valueObj1 instanceof Double ) {
			if( !(valueObj2 instanceof Double) )
				return false;
			Double value1 = ((Double)valueObj1);
			Double value2 = ((Double)valueObj2);
			return ((value1.compareTo(value2)) == 0);
		}
		else if( valueObj1 instanceof Float ) {
			if( !(valueObj2 instanceof Float) )
				return false;
			Float value1 = ((Float)valueObj1);
			Float value2 = ((Float)valueObj2);
			return ((value1.compareTo(value2)) == 0);
		}
		else if( valueObj1 instanceof Long ) {
			if( !(valueObj2 instanceof Long) )
				return false;
			Long value1 = ((Long)valueObj1);
			Long value2 = ((Long)valueObj2);
			return ((value1.compareTo(value2)) == 0);
		}
		else if( valueObj1 instanceof Boolean ) {
			if( !(valueObj2 instanceof Boolean) )
				return false;
			boolean value1 = ((Boolean)valueObj1).booleanValue();
			boolean value2 = ((Boolean)valueObj2).booleanValue();
			return (value1 == value2);
		}
		else if( valueObj1 instanceof java.sql.Date ) {
			throw new RuntimeException("Unspuly type!");
		}
		else {
			Debug.info(logger,"valueObj1=" + valueObj1);
			throw new RuntimeException("Unknown value type!");
		}
	}

	/**
	 * 比较日期、字符串、包装类等对象值的大小关系
	 * @param valueObj1
	 * @param valueObj2
	 * @return 0:相等,-1:valueObj1<valueObj2,1:valueObj1>valueObj2
	 */
	private static int compareTo(Object valueObj1, Object valueObj2) {

		if( valueObj1 instanceof Integer ) {
			if( !(valueObj2 instanceof Integer) )
				return -1;
			int value1 = ((Integer)valueObj1).intValue();
			int value2 = ((Integer)valueObj2).intValue();
			if( value1 < value2 )
				return -1;
			else if( value1 == value2 )
				return 0;
			else
				return 1;
		}
		else if( valueObj1 instanceof Short ) {
			if( !(valueObj2 instanceof Short) )
				return -1;
			short value1 = ((Short)valueObj1).shortValue();
			short value2 = ((Short)valueObj2).shortValue();
			if( value1 < value2 )
				return -1;
			else if( value1 == value2 )
				return 0;
			else
				return 1;
		}
		else if( valueObj1 instanceof String ) {
			if( !(valueObj2 instanceof String) )
				return -1;
			String value1 = ((String)valueObj1);
			String value2 = ((String)valueObj2);
			return (value1.compareTo(value2));
		}
		else if( valueObj1 instanceof java.math.BigDecimal ) {
			if( !(valueObj2 instanceof java.math.BigDecimal) )
				return -1;
			java.math.BigDecimal value1 = ((java.math.BigDecimal)valueObj1);
			java.math.BigDecimal value2 = ((java.math.BigDecimal)valueObj2);
			return ((value1.compareTo(value2)));
		}
		else if( valueObj1 instanceof Double ) {
			if( !(valueObj2 instanceof Double) )
				return -1;
			Double value1 = ((Double)valueObj1);
			Double value2 = ((Double)valueObj2);
			return ((value1.compareTo(value2)));
		}
		else if( valueObj1 instanceof Float ) {
			if( !(valueObj2 instanceof Float) )
				return -1;
			Float value1 = ((Float)valueObj1);
			Float value2 = ((Float)valueObj2);
			return ((value1.compareTo(value2)));
		}
		else if( valueObj1 instanceof Long ) {
			if( !(valueObj2 instanceof Long) )
				return -1;
			Long value1 = ((Long)valueObj1);
			Long value2 = ((Long)valueObj2);
			return ((value1.compareTo(value2)));
		}
		else if( valueObj1 instanceof Boolean ) {
			if( !(valueObj2 instanceof Boolean) )
				return -1;
			boolean value1 = ((Boolean)valueObj1).booleanValue();
			boolean value2 = ((Boolean)valueObj2).booleanValue();
			if( value1 == value2 )
				return 0;
			else
				return -1;
		}
		else if( valueObj1 instanceof java.sql.Date ) {
			throw new RuntimeException("Unspuly type!");
		}
		else {
			Debug.info(logger,"valueObj1=" + valueObj1);
			throw new RuntimeException("Unknown value type!");
		}
	}

	/**
	 * 如果bean中的属性是String，则本方法会将其设置为""，如果为数字，则设置为0。<br>
	 * 本函数还未完成。因为无法取到属性的类型是什么
	 * @param bean
	 */
	public static void makeEmptyObject(Object bean) {

		if( bean == null )
			throw new RuntimeException("参数对象为空");
		PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(bean);
		int len = origDescriptors.length;
		for(int i = 0; i < len; i++) {
			String name = origDescriptors[i].getName();
			if( PropertyUtils.isWriteable(bean, name) ) {
				Object value = null;
				try {
					value = PropertyUtils.getSimpleProperty(bean, name);
				}
				catch(Exception ex) {
					// 获取属性异常，则继续处理下一个属性
				}
				if( value instanceof String ) {
					try {
						PropertyUtils.setSimpleProperty(bean, name, "");
					}
					catch(Exception ex) {
						// 获取属性异常，则继续处理下一个属性
					}
				}
			}
		}
	}

	public static void setBeanProperty(Object bean, String propertyName, String propertyValue) {

		String errorInfo = "class=" + bean.getClass().getName() + "，name=" + propertyName;

		PropertyDescriptor descriptor;
		try {
			descriptor = new PropertyDescriptor(propertyName, bean.getClass());
		}
		catch(IntrospectionException e) {
			throw new RuntimeException("没有此Bean属性：" + errorInfo, e);
		}

		Method setter = descriptor.getWriteMethod();
		Class paramClazz = setter.getParameterTypes()[0];
		Object paramValue;
		try {
			Constructor paramCtor = paramClazz.getConstructor(new Class[] { String.class });
			paramValue = paramCtor.newInstance(new Object[] { propertyValue });
		}
		catch(Exception e) {
			throw new RuntimeException("无法将String转换成Bean属性类型：" + errorInfo, e);
		}

		try {
			setter.invoke(bean, new Object[] { paramValue });
		}
		catch(Exception e) {
			throw new RuntimeException("无法将属性值赋入Bean：" + errorInfo, e);
		}
	}

	public static void setMapProperty(Map bean, String propertyName, String[] propertyValue) {

		if( propertyValue.length == 1 )
			bean.put(propertyName, propertyValue[0]);
		else
			bean.put(propertyName, propertyValue);
	}
	
}
