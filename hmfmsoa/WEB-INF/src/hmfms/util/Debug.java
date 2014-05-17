package hmfms.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.web.filters.BizTraceFilter;

/**
 * <p>��    ��: ���Ŀ��</p>
 * <p>��    ��: </p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2010-12-13 ����11:55:11</p>
 * @author ��Ʒ������
 * @version 2.0
 * Debug
 */
public final class Debug {

	private static final int toStringFormatWidth = 16;

	private static String formatWithSpaces(String s) {

		StringBuffer sb = new StringBuffer(s);
		if( s.length() < toStringFormatWidth ) {
			for(int i = 0; i < toStringFormatWidth - s.length(); i++) {
				sb.append(" ");
			}
			return (sb.toString());
		}
		else {
			return (sb.substring(0, toStringFormatWidth));
		}
	}

	private static boolean isNull(List results, int row, String columnName) {

		Object o = getObject(results, row, columnName);
		return (o == null);
	}

	private static Object getObject(List results, int irow, String columnName) {

		if( irow >= results.size() ) {
			return null;
		}
		Map row = (HashMap)results.get(irow);
		return (row.get(columnName.toLowerCase()));
	}

	private static String getString(List results, int row, String columnName) {

		Object o = getObject(results, row, columnName);
		if( o == null ) {
			return "";
		}
		if( o instanceof BigDecimal ) {
			BigDecimal b = (BigDecimal)o;
			return (b.toString());
		}
		else if( o instanceof Integer ) {
			return (((Integer)o).toString());
		}
		else {
			String s = (String)o;
			return (s);
		}
	}

	/**
	 * ��results����ת����String�����Դ�ӡ
	 * @param results
	 * @return
	 */
	public static String toString(List results) {

		final int size = results.size();
		if( size < 1 ) {
			return "Empty!";
		}
		StringBuffer out = new StringBuffer("\n");
		Map tmpRow = (HashMap)results.get(0);
		Iterator it = tmpRow.keySet().iterator();
		List columnNames = new ArrayList();
		while( it.hasNext() ) {
			columnNames.add(it.next());
		}
		for(int col = 0; col < columnNames.size(); col++) {
			String formattedColName = formatWithSpaces((String)columnNames.get(col));
			out.append(formattedColName);
		}
		out.deleteCharAt(out.length() - 2);
		int len = out.length();
		out.append("\n");
		for(int i = 0; i < len - 1; i++) {
			out.append("-");
		}
		out.append("\n");
		for(int irow = 0; irow < size; irow++) {
			for(int col = 0; col < columnNames.size(); col++) {
				String formattedColName = null;
				String columnName = (String)columnNames.get(col);
				if( isNull(results, irow, columnName) ) {
					formattedColName = formatWithSpaces("NULL");
				}
				else {
					formattedColName = formatWithSpaces(getString(results, irow, columnName));
				}
				out.append(formattedColName);
			}
			out.deleteCharAt(out.length() - 2);
			out.append("\n");
		}
		return (out.toString());
	}

	/**
	 * ִ��sql���ӡ��������Result
	 * @param db
	 * @param sql
	 * @param isRun
	 * @return
	 */

	public static Result printSql(Log logger,SQLExecutor db, String sql, boolean isRun) {

		if( !isRun ) {
			return null;
		}
		Result rs = db.execute(sql);
		if( logger.isInfoEnabled() )
			logger.info("---------->:\n" + rs + "\n----------->.");
		return rs;
	}

	public static void debug(Log logger, String msg) {

		if( logger.isDebugEnabled() )
			logger.debug(BizTraceFilter.getBizUUID() + "[AP]" + msg);
	}

	public static void info(Log logger, String msg) {

		if( logger.isInfoEnabled() )
			logger.info(BizTraceFilter.getBizUUID() + "[AP]" + msg);
	}

	public static void error(Log logger, String msg) {

		logger.error(BizTraceFilter.getBizUUID() + "[AP]" + msg);//error��������isXXXEnabled�ж�
	}

	public static void exception(Log logger, String msg, Exception ex) {

		logger.error(BizTraceFilter.getBizUUID() + "[AP-EX]" + msg, ex);
	}

	public static void exception(Log logger, Exception ex) {

		logger.error(BizTraceFilter.getBizUUID() + "[AP-EX]", ex);
	}
}