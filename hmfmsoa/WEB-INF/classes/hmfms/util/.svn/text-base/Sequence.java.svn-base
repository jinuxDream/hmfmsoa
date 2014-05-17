package hmfms.util;

import hmfms.util.AbstractUtil;
import fd.commons.jdbc.DBInitProperty;
import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.key.KeyGenerator;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 获取自动增长的序列号
 *  			使用方式：
 *  				按照表名取：
 *  					long seq1 = Sequence.nextval(&quot;resolution_info&quot;);
 *  				按照对象名字取：
 *  					Resolution_info obj = new Resolution_info();
 *  					long seq2 = Sequence.nextval(obj);</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 下午12:38:26</p>
 * @author 产品开发部
 * @version 2.0
 * Sequence
 */
public final class Sequence extends AbstractUtil {

	public static int dbtype = DBInitProperty.getInt("dbtype", SQLExecutor.DBTYPE_ORACLE);

	private Sequence() {

	}

	/**
	 * 按照表名取SEQUENCE
	 * @param seqname SEQUENCE名
	 * @param db
	 * @return long sequence
	 * @throws
	 */
	public static long nextval(String seqname, SQLExecutor db) {

		long seqno = -1;
		if( SQLExecutor.DBTYPE_ORACLE == dbtype ) {
			seqno = genNextvalFromDBSeq(seqname, db);
		}
		else{
			seqno = genNextvalFromKeyPool(seqname);
		}
		
		return seqno;
	}

	/**
	 * 按照表名取SEQUENCE
	 * @param seqname SEQUENCE名
	 * @return long sequence
	 * @throws
	 */
	public static long nextval(String seqname) {

		long seqno = -1;
		if( SQLExecutor.DBTYPE_ORACLE == dbtype ) {
			seqno = genNextvalFromDBSeq(seqname);
		}
		else{
			seqno = genNextvalFromKeyPool(seqname);
		}
		return seqno;
	}

	private static long genNextvalFromDBSeq(String tablename, SQLExecutor db) {

		String seqname = "seq_" + tablename;
		long seqno = -1;
		try{
			String sql = "select " + seqname + ".nextval as seq from dual";
			Result rs = db.execute(sql);
			if( rs.getRowCount() != 1 )
				throw new RuntimeException("数据库查询异常：sequence 查询失败【" + seqname + "】！");
			seqno = rs.getLong(0, "seq");// }
			if( seqno < 1 )
				throw new RuntimeException("sequence 查询异常【" + seqname + ".nextval=" + seqno + "】！");
			return seqno;
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static long genNextvalFromDBSeq(String tablename) {

		String seqname = "seq_" + tablename;
		SQLExecutor db = null;
		long seqno = -1;
		try {
			String sql = "select " + seqname + ".nextval as seq from dual";
			db = new SQLExecutor();
			Result rs = db.execute(sql);
			if( rs.getRowCount() != 1 ) {
				throw new RuntimeException("数据库查询异常：sequence 查询失败【" + seqname + "】！");
			}
			seqno = rs.getLong(0, "seq"); //}
			if( seqno < 1 ) {
				throw new RuntimeException("sequence 查询异常【" + seqname + ".nextval=" + seqno + "】！");
			}
			return seqno;
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}

	private static long genNextvalFromKeyPool(String seqname) {

		KeyGenerator keygen = KeyGenerator.getInstance();
		long seqno = keygen.getNextKey(seqname);
		return seqno;
	}

	/**
	 * 按照对象名取SEQUENCE
	 * 
	 * @param obj
	 *            SEQUENCE对应的对象名字
	 * @return long sequence
	 * @throws
	 */
	/*
	 * 这个方法或许没必要提供 public static long nextval(Object obj) { if(obj instanceof String){ return nextval(obj.toString()); }
	 * else{ String objName = obj.getClass().getName(); objName = objName.substring(objName.lastIndexOf('.')+1); return
	 * nextval(objName); } }
	 */
}
