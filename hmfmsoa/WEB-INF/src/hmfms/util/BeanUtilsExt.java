package hmfms.util;


import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import fd.commons.TableEntity;
import fd.commons.jdbc.SqlOperator;

public class BeanUtilsExt
{
	/**
	 * 将bean对象转换为map
	 * @param obj
	 * @param mp
	 */
	public static void convertEnToMap(Object obj,Map <String ,Object > mp ){
		
		PropertyDescriptor propertys[] = PropertyUtils.getPropertyDescriptors(obj);
		int len = propertys.length;
		try{
		for (int i = 0; i < len; i++) {
			String name = propertys[i].getName();
			if (PropertyUtils.isReadable(obj, name)&&(mp.get(name)==null) && (!"class".equals(name))) {
				Object  value= PropertyUtils.getSimpleProperty(obj, name);
				if(value instanceof Map || value instanceof Object[]||value instanceof HashSet ) continue;
				mp.put(name, value);
			}						
		}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("系统异常，对象转换异常！", e);
		}
	}

	/**
	 * 将bean对象转换为map,如果map中有值，将覆盖掉原来的值（一般用在从数据库中查询出来，将页面的值覆盖）
	 * @param obj
	 * @param mp
	 */
	public static void convertEnToMapCoverageMap(Object obj,Map <String ,Object > mp ){
		
		PropertyDescriptor propertys[] = PropertyUtils.getPropertyDescriptors(obj);
		int len = propertys.length;
		try{
		for (int i = 0; i < len; i++) {
			String name = propertys[i].getName();
			if (PropertyUtils.isReadable(obj, name) && (!"class".equals(name))) {
				Object  value= PropertyUtils.getSimpleProperty(obj, name);
				if(!ObjectUtil.isEmpty(value)){
					if(value instanceof Map || value instanceof Object[]||value instanceof HashSet ) continue;
					mp.put(name, value);
				}
			}						
		}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("系统异常，对象转换异常！", e);
		}
	}
	
	public static int isContainsKeyNotPrim(TableEntity enty,Map <String ,Object > mp ){
		
		PropertyDescriptor propertys[] = PropertyUtils.getPropertyDescriptors(enty);
		int len = propertys.length;
		int icount=0;
		try{
			for (int i = 0; i < len; i++) {
				String name = propertys[i].getName();
				if (PropertyUtils.isReadable(enty, name)&& (!"class".equals(name))) {
					if(mp.containsKey(name)&&(!enty.isPrimaryKey(name))) {
						icount++;
					}
				}						
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("系统异常！", e);
		}
		return icount;

	}
	
}
