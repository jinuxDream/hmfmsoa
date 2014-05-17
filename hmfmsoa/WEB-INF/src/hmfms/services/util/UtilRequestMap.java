package hmfms.services.util;

import hmfms.util.DateUtil;
import hmfms.util.ObjectUtil;
import hmfms.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class UtilRequestMap {

	@SuppressWarnings("unchecked")
	public static Map<String, Object>[] RequestConversionMap(HttpServletRequest request) {

		Map paramMap = request.getParameterMap();
		Set params = paramMap.entrySet();

		Map<String, String> mapTableVal = new HashMap<String, String>();
		Map<String, String[]> mapTableVals = new HashMap<String, String[]>();

		int mapSize = 0;

		for(Iterator it = params.iterator(); it.hasNext();) {
			java.util.Map.Entry param = (java.util.Map.Entry)it.next();
			String name = (String)param.getKey();
			if( name.indexOf(".") != -1 && name.indexOf(".") != 0 && name.indexOf(".") != name.length() ) {
				String raw_values[] = (String[])(String[])param.getValue();
				if( raw_values.length == 1 ) {
					mapTableVal.put(name, raw_values[0]);
				}
				else {
					mapTableVals.put(name, raw_values);
					mapSize = raw_values.length;
				}

			}
		}

		Map<String, Object> mapVal = new HashMap<String, Object>();
		Object keyVal[] = mapTableVal.keySet().toArray();
		for(int i = 0; i < mapTableVal.size(); i++) {
			String name = (String)keyVal[i];
			String[] table_column = StringUtil.split(name, ".");
			String column = table_column[1];
			String raw_values = mapTableVal.get(name);
			mapVal.put(column, isDateFormat2DB(raw_values));
		}

		Object key[] = mapTableVals.keySet().toArray();
		Map<String, Object>[] column_vals = null;
		if( mapTableVal.size() == 0 ) {
			column_vals = new Map[mapSize];
		}
		else {
			column_vals = new Map[mapSize + 1];
		}

		for(int i = 0; i < column_vals.length; i++) {
			column_vals[i] = new HashMap<String, Object>();
		}
		for(int i = 0; i < mapTableVals.size(); i++) {
			String name = (String)key[i];
			String[] table_column = StringUtil.split(name, ".");
			String column = table_column[1];
			String[] raw_values = mapTableVals.get(name);
			for(int j = 0; j < raw_values.length; j++) {
				column_vals[j].put(column, isDateFormat2DB(raw_values[j]));
			}
		}
		Map<String, Object>[] arrayMap = null;
		if( mapTableVal.size() == 0 ) {
			arrayMap = new Map[column_vals.length];
		}
		else {
			arrayMap = new Map[column_vals.length + 1];
		}
		arrayMap = column_vals;
		if( mapVal.size() != 0 ) {
			arrayMap[mapSize] = mapVal;
		}
		return arrayMap;
	}

	/**
	 * 只获取数组数据
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object>[] RequestConversionMapArray(HttpServletRequest request) {

		Map paramMap = request.getParameterMap();
		Set params = paramMap.entrySet();

		Map<String, String[]> mapTableVals = new HashMap<String, String[]>();

		int mapSize = 0;

		for(Iterator it = params.iterator(); it.hasNext();) {
			java.util.Map.Entry param = (java.util.Map.Entry)it.next();
			String name = (String)param.getKey();
			if( name.indexOf(".") != -1 && name.indexOf(".") != 0 && name.indexOf(".") != name.length() ) {
				String raw_values[] = (String[])(String[])param.getValue();
				if( raw_values.length != 1 ) {
					mapTableVals.put(name, raw_values);
					mapSize = raw_values.length;
				}
			}
		}
		Object key[] = mapTableVals.keySet().toArray();
		Map<String, Object>[] column_vals = new Map[mapSize];
		for(int i = 0; i < column_vals.length; i++) {
			column_vals[i] = new HashMap<String, Object>();
		}
		for(int i = 0; i < mapTableVals.size(); i++) {
			String name = (String)key[i];
			String[] table_column = StringUtil.split(name, ".");
			String column = table_column[1];
			String[] raw_values = mapTableVals.get(name);
			for(int j = 0; j < raw_values.length; j++) {
				column_vals[j].put(column, isDateFormat2DB(raw_values[j]));
			}
		}
		Map<String, Object>[] arrayMap = new Map[column_vals.length];
		arrayMap = column_vals;
		return arrayMap;
	}

	/**
	 * 只获取单条数据
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> RequestConversionMapOne(HttpServletRequest request) {

		Map paramMap = request.getParameterMap();
		Set params = paramMap.entrySet();

		Map<String, String> mapTableVal = new HashMap<String, String>();
		for(Iterator it = params.iterator(); it.hasNext();) {
			java.util.Map.Entry param = (java.util.Map.Entry)it.next();
			String name = (String)param.getKey();
			if( name.indexOf(".") != -1 && name.indexOf(".") != 0 && name.indexOf(".") != name.length() ) {
				String raw_values[] = (String[])(String[])param.getValue();
				if( raw_values.length == 1 ) {
					mapTableVal.put(name, raw_values[0]);
				}
			}
		}

		Map<String, Object> mapVal = new HashMap<String, Object>();
		Object keyVal[] = mapTableVal.keySet().toArray();
		for(int i = 0; i < mapTableVal.size(); i++) {
			String name = (String)keyVal[i];
			String[] table_column = StringUtil.split(name, ".");
			String column = table_column[1];
			String raw_values = mapTableVal.get(name);
			mapVal.put(column, isDateFormat2DB(raw_values));
		}
		return mapVal;
	}

	public static String isDateFormat2DB(String date) {

		if( !ObjectUtil.isEmpty(date) ) {
			String re = "^(([0-9]{4}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
			if( date.matches(re) ) {
				return DateUtil.format2DB(date);
			}
			else {
				return date;
			}
		}else{
			return "";
		}
	}

	////////////////////////////////////////这个方法基本上不会用////////////////////////////////////////////////
	/**
	 * Map<String, Map<String, String>> <表名，<字段名称，字段值>>
	 * 将页面的input 转换为map
	 * @param request
	 * @return {@link Map}
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Map<String, String>>[] ReqConversionMap(HttpServletRequest request) {

		Map paramMap = request.getParameterMap();
		Set params = paramMap.entrySet();

		Map<String, String> mapTableVal = new HashMap<String, String>();
		Map<String, String[]> mapTableVals = new HashMap<String, String[]>();

		int mapSize = 0;

		Map<String, Map<String, String>> mapColVal = new HashMap<String, Map<String, String>>();

		List<Map<String, Map<String, String>>> list = new ArrayList<Map<String, Map<String, String>>>();

		for(Iterator it = params.iterator(); it.hasNext();) {
			java.util.Map.Entry param = (java.util.Map.Entry)it.next();
			String name = (String)param.getKey();
			if( name.indexOf(".") != -1 && name.indexOf(".") != 0 && name.indexOf(".") != name.length() ) {
				String raw_values[] = (String[])(String[])param.getValue();
				String[] table_column = StringUtil.split(name, ".");
				String table = table_column[0];

				if( raw_values.length == 1 ) {
					mapTableVal.put(name, raw_values[0]);
					mapColVal.put(table, new HashMap<String, String>());
				}
				else {
					mapTableVals.put(name, raw_values);
					mapSize = raw_values.length;
					Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
					for(int i = 0; i < mapSize; i++) {
						map.put(table, new HashMap<String, String>());
						list.add(map);
					}
				}
			}
		}

		Object keyVal[] = mapTableVal.keySet().toArray();
		for(int i = 0; i < mapTableVal.size(); i++) {
			String name = (String)keyVal[i];
			String[] table_column = StringUtil.split(name, ".");
			String column = table_column[1];
			String raw_values = mapTableVal.get(name);
			String table = table_column[0];
			mapColVal.get(table).put(column, raw_values);
		}

		Map<String, Map<String, String>>[] mapSet = new HashMap[mapSize];
		Object key[] = mapTableVals.keySet().toArray();
		for(int i = 0; i < mapTableVals.size(); i++) {
			String name = (String)key[i];
			String[] table_column = StringUtil.split(name, ".");
			String column = table_column[1];
			String[] raw_values = mapTableVals.get(name);
			String table = table_column[0];
			for(int j = 0; j < raw_values.length; j++) {
				if( mapSet[j] == null ) {
					Map<String, Map<String, String>> mapTable = new HashMap<String, Map<String, String>>();
					Map<String, String> mapVal = new HashMap<String, String>();
					mapVal.put(column, raw_values[j]);
					mapTable.put(table, mapVal);
					mapSet[j] = mapTable;
				}
				else {
					Map<String, String> mapTable = mapSet[j].get(table);
					mapTable.put(column, raw_values[j]);
				}
			}
		}
		List<Map<String, Map<String, String>>> listVal = new ArrayList<Map<String, Map<String, String>>>();
		for(int i = 0; i < mapSet.length; i++) {
			listVal.add(mapSet[i]);
		}
		listVal.add(mapColVal);
		Map<String, Map<String, String>>[] mapColVals = new HashMap[listVal.size()];//定义
		mapColVals = listVal.toArray(mapColVals);
		return mapColVals;
	}
}
