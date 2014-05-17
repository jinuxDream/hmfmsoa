package mng_plat.service.cfg;

/**
 * <p>标    题: 物业监管平台（二期）</p>
 * <p>描    述: 系统基础数据数配置</p>
 * <p>版    权: Copyright (c) 2013</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2013-5-16 下午04:15:05</p>
 * @author xchao
 * @version 1.1
 */
public class CfgSysBase {

	/**
	 * 获取CfgSysBase实例
	 */
	public static CfgSysBase getInstance() {

		return new CfgSysBase();
	}

	/**
	 * 系统中 是否存在物业管理区域
	 * @return
	 */
	public static boolean isCsp() {

		return true;
	}

	/**
	 * 系统中 是否有自然幢
	 * @return
	 */
	public static boolean isBuild() {

		return false;
	}

	/**
	 * 系统中 是否有门牌单元
	 * @return
	 */
	public static boolean isUnit() {

		return true;
	}
	
	/**
	 * 系统中 是否做物理删除
	 * @return
	 */
}
