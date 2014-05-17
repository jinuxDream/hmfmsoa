package hmfms.services.key;

import fd.commons.jdbc.SQLExecutor;
import fd.commons.key.KeyGenerator;
import fd.exception.AppSystemException;
import fd.exception.BusinessException;
import hmfms.util.DateUtil;
import hmfms.util.Sequence;

import java.util.Random;

public class PrimayKeyGener {

	/**
	 * 生成表ID，20位，生成规则：
	 * 2位系统前缀+6位系统日期+12位序列值(1开头)
	 * 
	 * @return String 主键值
	 */
	public static String getNextId() {

		SQLExecutor db = null;
		try {
			db = new SQLExecutor(SQLExecutor.SQL_SHOW_CLOSE, true);
			return getNextId(db);
		}
		catch(Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException(e);
		}
		finally {
			if( db != null )
				db.close();
		}
	}

	public static String getNextId(SQLExecutor db) {

		long val = getNextSequence("mng_plat", db);
		long number = 1000000000L + val;
		StringBuffer str = new StringBuffer();
		str.append(DateUtil.getSysDate().substring(2));
		str.append(number);
		return str.toString();
	}

	/**
	 * 获取没有关联关系的外键
	 * 通常用于没有实际关联关系时设置非空外键，如房间的归属楼宇、归属门牌单元；门牌单元的归属楼宇等
	 * @return
	 */
	public static String getDefaultNullId() {

		return "000000000000000000";
	}
	
	/**
	 * 生成6位随机数
	 * @return
	 */
	public static String getRandomStr() {

		Random r = new Random();
		long i = r.nextInt(100000);
		long number = i + 900000L;
		return Long.toString(number);
	}

	/**
	 * 获取操作员ID
	 * @param db
	 * @return
	 */
	public static String getOperId(SQLExecutor db) {

		long val = getNextSequence("tellers", db);
		long number = 100000L + val;
		StringBuffer str = new StringBuffer();
		str.append(number);
		return str.toString();
	}
	
	/**
	 * 获取工作组id和角色id
	 * @param db
	 * @return
	 */
	public static String getGroupId(SQLExecutor db) {

		KeyGenerator keygen = KeyGenerator.getInstance();
		long roleid = keygen.getNextKey("roleid"); // 从KeyTable生成组编码
		long number = 100000L + roleid;
		StringBuffer str = new StringBuffer();
		str.append(number);
		return str.toString();
	}

	private static int getNextSequence(String tablename, SQLExecutor db) {

		return new Long(Sequence.nextval(tablename, db)).intValue();
	}

}
