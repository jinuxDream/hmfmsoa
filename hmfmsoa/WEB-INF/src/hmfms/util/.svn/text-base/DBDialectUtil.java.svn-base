/**
 * 
 */
package hmfms.util;

import fd.commons.jdbc.DBInitProperty;
import fd.commons.jdbc.SQLExecutor;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 数据库方言实用类</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 下午01:37:45</p>
 * @author 产品开发部
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
	 * 查询语句按行号查询前缀
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
	 * 查询语句按行号查询后缀
	 * @param rowNo 起始行号,从0开始,到总行数减1
	 * @param rowCount 结果集条数
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
	 * 四舍五入前辍
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
	 * 四舍五入后辍
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
	 * 查询字符串拼接函数
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
	 * 查询字符串子串函数
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
	 * 查询字符串空串函数
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
	 * sql中拼接字段
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
	 * 分摊使用精度函数
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