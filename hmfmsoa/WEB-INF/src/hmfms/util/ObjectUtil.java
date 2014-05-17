package hmfms.util;

import fd.commons.TableEntity;
import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.util.Assert;

import java.math.BigDecimal;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 对象处理实用类</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:58:35</p>
 * @author 产品开发部
 * @version 2.0
 * ObjectUtil
 */
public class ObjectUtil extends AbstractUtil {

	/**
	 * 判断String类型对象是否为空(空串或null)
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {

		if( str == null || str.trim().equals("") ) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 *          {@link Object}
	 * @return {@link Boolean}
	 */
	public static boolean isEmpty(Object obj) {
		if (null == obj) return true;
		if (obj instanceof String) return isEmpty(obj.toString());
		return false;
	}

	/**
	 * 判断一个对象数组是否为空(没有成员)
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(Object[] array) {

		if( array == null || array.length == 0 ) {
			return true;
		}
		return false;
	}

	/**
	 * 获取值为0.00的BigDecimal对象
	 * @return
	 */
	public static BigDecimal getZeroBigDecimal() {

		return new BigDecimal(0);
	}
	
	public static String getNullString() {

		return "";
	}

	/**
	 * 判断实体数据是否在数据库存在
	 * @param db
	 * @param table
	 * @return boolean 如果不存在返回true
	 */
	public static boolean isNotExist(SQLExecutor db, TableEntity entity) {

		Assert.notNull(db, "调用CheckProject.isNotExist,请使用标准db");
		Assert.notNull(entity, "调用CheckProject.isNotExist,请使用准TableEntity");
		SqlOperator dbo = new SqlOperator();
		Result rs = dbo.gets(entity, db);
		boolean isNotExist = true;
		if( rs.getRowCount() > 0 ) {
			isNotExist = false;
		}
		return isNotExist;
	}
}
