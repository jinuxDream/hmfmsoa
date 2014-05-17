/**
 * 
 */
package hmfms.util;

import fd.commons.jdbc.DBInitProperty;
import fd.commons.jdbc.SQLExecutor;

/**
 * <p>��    ��: ���Ŀ��</p>
 * <p>��    ��: ���ݿⷽ��ʵ����</p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2010-12-13 ����01:37:45</p>
 * @author ��Ʒ������
 * @version 2.0
 * DBDialectUtil
 */
public class DBDialectUtil extends AbstractUtil {
	private static int DBTYPE=DBInitProperty.getInt("dbtype", SQLExecutor.DBTYPE_ORACLE);
	//private static String CUTMODE=SysParaUtil.getParaValueByName("CUT_MODE", "1");
	
	public static String getCastForNumOrderPrefix() {

		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
			return " cast( ";
		}
		else{
			return " ";
		}
	}

	public static String getCastForNumOrderPostfix() {

		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
			return " as DECIMAL(12)) ";
		}
		else{
			return " ";
		}
	}
	
	public static String getConvertToNumPrefix(){
		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
			return " convert(";
		}
		else{
			return " ";
		}
	}
	public static String getConvertToNumPostfix(){
		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
			return " ,DECIMAL(12))";
		}
		else{
			return " ";
		}
	}

	public static String getConvertToCharPrefix(){
		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
			return " convert(";
		}
		else{
			return " ";
		}
	}
	public static String getConvertToCharPostfix(){
		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
			return " ,CHAR(18))";
		}
		else{
			return " ";
		}
	}
	
	/**
	 * ��ѯ��䰴�кŲ�ѯǰ׺
	 * @return
	 */
	public static String getRowPrefix() {
		
		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
			return " ";
		}
		else{
			return " select * from ( select row_.*, rownum rownum_  from ( ";
		}
		
	}

	/**
	 * ��ѯ��䰴�кŲ�ѯ��׺
	 * @param rowNo ��ʼ�к�,��0��ʼ,����������1
	 * @param rowCount ���������
	 * @return
	 */
	public static String getRowPostfix(int startRowNo,int rowCount) {
	
		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
			return " limit "+startRowNo+","+rowCount+" ";
		}
		else{
			return " )row_ where rownum <= "+(startRowNo+rowCount)+") where rownum_ >  "+startRowNo+" ";
			
		}
		
	}
	/**
	 * ��������ǰ�
	 * @param num_digits
	 * @return
	 */
	public static String getRoundPrefix(int num_digits){
		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
			return " ROUND(";
		}
		else{
			return " ROUND(";
			
		}
	}
	/**
	 * ����������
	 * @param num_digits
	 * @return
	 */
	public static String getRoundPostfix(int num_digits){
		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
			return " ,"+num_digits+")";
		}
		else{
			return " ,"+num_digits+")";
		}
	}
	/**
	 * ��ѯ�ַ���ƴ�Ӻ���
	 * @return
	 */
	public static String getConcat() {
	
		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
			return " concat";
		}
		else{
			return " concat";
			
		}
		
	}

	/**
	 * ��ѯ�ַ����Ӵ�����
	 * @return
	 */
	public static String getSubStr() {
	
		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
			return " substring";
		}
		else{
			return " substr";
			
		}
		
	}
	/**
	 * ��ѯ�ַ����մ�����
	 * @return
	 */
	public static String getNvl() {
	
		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
			return " ifnull";
		}
		else{
			return " nvl";
		}
	}
	/**
	 * sql��ƴ���ֶ�
	 * @return
	 */
	public static String getSplitJoin() {
	
		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
			return " ";
		}
		else{
			return "||";
		}
	}
	/**
	 * ��̯ʹ�þ��Ⱥ���
	 * @return
	 */
//	public static String getCutMode() {
//		if(SQLExecutor.DBTYPE_MYSQL==DBTYPE){
//				
//			if("2".equals(CUTMODE))
//				return " CEIL ";
//			else if("3".equals(CUTMODE))
//				return " FLOOR ";
//			else
//				return " ROUND ";
//		}
//		else{
//			
//			if("2".equals(CUTMODE))
//				return " ceil ";
//			else if("3".equals(CUTMODE))
//				return " trunc ";
//			else
//				return " round ";
//		}
//	}
	
	
}