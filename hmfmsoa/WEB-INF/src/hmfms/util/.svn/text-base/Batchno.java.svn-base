package hmfms.util;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 申请编号生成类</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:47:47</p>
 * @author 产品开发部
 * @version 2.0
 * Batchno
 */
public class Batchno extends AbstractUtil {

	private static final Batchno m_instance = new Batchno();

	private static final int POOL_SIZE = 1;
	private KeyPool key = null;

	/**
	 * 获取一个新的申请编号值
	 * @return
	 */
	public String getNumber() {

		StringBuffer number = new StringBuffer(12);
		number.append(key.getNextKey());
		//前面补0
		int len = 6 - number.length();
		for(int i = 0; i < len; i++) {
			number.insert(0, '0');
		}
		number.insert(0, DateUtil.getSysDate().substring(2));
		return number.toString();
	}
	
	private Batchno() {

		key = new KeyPool(POOL_SIZE);
	}

	/**
	 * 获取Batchno单例对象
	 * @return
	 */
	public static Batchno getInstance() {

		return m_instance;
	}
}

class KeyPool {

	private int keyMax;
	private int keyMin;
	private int nextKey;
	private int poolSize;
	private String keyname;

	/**
	 * @param poolSize 请慎用除1以外的其他值
	 * @param key_name 对应keytable表的key_name字段值
	 */
	KeyPool(int poolSize) {

		this.poolSize = poolSize;
		this.keyname = "batchno";
		retrieveFromDB();
	}

	int getKeyMax() {

		return keyMax;
	}

	int getKeyMin() {

		return keyMin;
	}

	/**
	 * @return
	 */
	synchronized int getNextKey() {

		if( nextKey > keyMax ) {
			retrieveFromDB();
		}
		return nextKey++;
	}

	/**
	 * 
	 */
	private void retrieveFromDB() {

		Result rs = null;
		SQLExecutor db = null;
		int keyFromDB = -1;
		try {
			db = new SQLExecutor();
			db.beginTrans();
			String update = "UPDATE keytable SET value = value + ? WHERE key_name = '" + this.keyname + "'";
			db.addParam(this.poolSize);
			db.execute(update);
			int result = db.getNumRecordsUpdated();
			if( result != 1 )
				throw new RuntimeException("数据库更新异常：表为keytable！");
			rs = db.execute("SELECT value FROM keytable WHERE key_name = '" + this.keyname + "'  ");
			if( rs == null || rs.isEmpty() )
				throw new RuntimeException("数据库查询异常：表为keytable！");
			keyFromDB = rs.getInt(0, "value");
			db.commit();
		}
		catch(Exception e) {
			if( db != null )
				db.rollback();
			throw new RuntimeException(e);
		}
		finally {
			if( db != null )
				db.close();

		}

		keyMax = keyFromDB;
		keyMin = keyFromDB - poolSize + 1;
		nextKey = keyMin;
	}
	
}