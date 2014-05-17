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
 * <p>��    ��: ���Ŀ��</p>
 * <p>��    ��: BEAN����ʵ����</p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2010-12-13 ����11:48:57</p>
 * @author ��Ʒ������
 * @version 2.0
 * BeanUtil
 */
public final class BeanUtil extends AbstractUtil {
	private static final Log logger = LogFactory.getLog(BeanUtil.class);
	/**
	 * ��ĳ�ж�result�����ڵ��н�����������
	 * @param result Ҫ���������result����
	 * @param sortColumn ������
	 */
	public static void sortResult(Result result, final String sortColumn) {

		sortResult(result, sortColumn, "");
	}

	/**
	 * @param result Ҫ���������result����
	 * @param sortColumn ������
	 * @param desc ������򣬿մ�������
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
	 * ���ƶ�����ͬ����������Ŀ�����
	 * @param source �����ƵĶ���
	 * @param dest Ŀ�����
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
	 * ����map�е�ͬ��ֵ��Ŀ�����
	 * @param source �����Ƶ�map
	 * @param dest Ŀ�����
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
					throw new RuntimeException("��֧�ֵ���������ת����" + clzDest.getName());

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
	 * �Ƚ��������������ֵ�Ƿ����
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
	 * �Ƚ����ڡ��ַ�������װ��ȶ���ֵ�Ƿ����
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
	 * �Ƚ����ڡ��ַ�������װ��ȶ���ֵ�Ĵ�С��ϵ
	 * @param valueObj1
	 * @param valueObj2
	 * @return 0:���,-1:valueObj1<valueObj2,1:valueObj1>valueObj2
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
	 * ���bean�е�������String���򱾷����Ὣ������Ϊ""�����Ϊ���֣�������Ϊ0��<br>
	 * ��������δ��ɡ���Ϊ�޷�ȡ�����Ե�������ʲô
	 * @param bean
	 */
	public static void makeEmptyObject(Object bean) {

		if( bean == null )
			throw new RuntimeException("��������Ϊ��");
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
					// ��ȡ�����쳣�������������һ������
				}
				if( value instanceof String ) {
					try {
						PropertyUtils.setSimpleProperty(bean, name, "");
					}
					catch(Exception ex) {
						// ��ȡ�����쳣�������������һ������
					}
				}
			}
		}
	}

	public static void setBeanProperty(Object bean, String propertyName, String propertyValue) {

		String errorInfo = "class=" + bean.getClass().getName() + "��name=" + propertyName;

		PropertyDescriptor descriptor;
		try {
			descriptor = new PropertyDescriptor(propertyName, bean.getClass());
		}
		catch(IntrospectionException e) {
			throw new RuntimeException("û�д�Bean���ԣ�" + errorInfo, e);
		}

		Method setter = descriptor.getWriteMethod();
		Class paramClazz = setter.getParameterTypes()[0];
		Object paramValue;
		try {
			Constructor paramCtor = paramClazz.getConstructor(new Class[] { String.class });
			paramValue = paramCtor.newInstance(new Object[] { propertyValue });
		}
		catch(Exception e) {
			throw new RuntimeException("�޷���Stringת����Bean�������ͣ�" + errorInfo, e);
		}

		try {
			setter.invoke(bean, new Object[] { paramValue });
		}
		catch(Exception e) {
			throw new RuntimeException("�޷�������ֵ����Bean��" + errorInfo, e);
		}
	}

	public static void setMapProperty(Map bean, String propertyName, String[] propertyValue) {

		if( propertyValue.length == 1 )
			bean.put(propertyName, propertyValue[0]);
		else
			bean.put(propertyName, propertyValue);
	}
	
}
