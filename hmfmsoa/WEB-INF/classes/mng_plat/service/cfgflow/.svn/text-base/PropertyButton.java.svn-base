/**
 * 
 */
package mng_plat.service.cfgflow;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p>标    题: 物业监管平台（二期）</p>
 * <p>描    述: </p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2013-5-6 下午02:40:01</p>
 * @author xchao
 * @version 1.1
 */
public class PropertyButton {

	static ResourceBundle resources = loadProperties("buttonname");

	static ResourceBundle loadProperties(String fileName) {

		return ResourceBundle.getBundle(fileName, Locale.getDefault());
	}

	/**
	 * 基础数据使用的新增按钮名称
	 * @return
	 */
	public static String getBaseInfo_AddButton() {

		return "101xzbutton";
	}

	/**
	 * 基础数据使用的编辑按钮名称
	 * @return
	 */
	public static String getBaseInfo_EditButton() {

		return "102bjbutton";
	}

	/**
	 * 获取审核退回按钮
	 * @return
	 */
	public static String getAuditRedo_arry() {

		return "106shthbutton";
	}

	/**
	 * 返回properties文件中名字为name的字符串值。如果没有找到, 则返回默认值defaultValue.
	 * @param name 名字。
	 * @param defaultValue 默认值。
	 * @return 返回properties文件中名字为name的值。如果没有找到, 则返回默认值defaultValue.
	 */
	public static String getString(String name) {

		try {
			return resources.getString(name);
		}
		catch(Exception e) {
			return "无按钮";
		}
	}

	//**********按钮定义，使用在某些地方的判断，如保存交易使用的按钮名称，编辑使用的名称（因为每个业务需要显示的按钮名称不一样，所以有这么一个定义）*************************************/

	/**
	 * 获取新增按钮 数组(保存交易时，使用的按钮名称ID)
	 * @return
	 */
	public static String getAddButton_arry() {

		return getString("addbutton");
	}

	/**
	 * 小区四查使用的新增按钮名称
	 * @return
	 */
	public static String getSectCheckAddButton() {

		return "111xzzbbutton";
	}

	/**
	 * 小区四查使用的新增按钮名称
	 * @return
	 */
	public static String getDelOqButton() {

		return "117sc";
	}
	
	/**
	 * 小区四查使用的新增按钮名称
	 * @return
	 */
	public static String getDelThreeOqButton() {

		return "117sczb";
	}
	
	/**
	 * 获取编辑按钮（编辑交易是，显示的编辑按钮名称）
	 * @return
	 */
	public static String getEditButton_arry() {

		return getString("editbutton");
	}
	
	
	/**
	 * 小区四查使用的新增按钮名称
	 * @return
	 */
	public static String getDelButton() {

		return "117sc";
	}
	
	
	
	/**
	 * 小区四查使用的新增按钮名称
	 * @return
	 */
	public static String getAddCheckButton() {

		return "141lrzg";
	}
}
