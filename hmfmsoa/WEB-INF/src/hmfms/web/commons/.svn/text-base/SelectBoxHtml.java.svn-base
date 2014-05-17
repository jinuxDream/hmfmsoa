/**
 * 
 */
package hmfms.web.commons;

import hmfms.util.ObjectUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mng_plat.service.baseinfo.BaseInfoQuery;
import fd.commons.jdbc.Result;
import fd.commons.jdbc.ResultRow;
import fd.util.Assert;

/**
 * @author 张传生
 * 2006-1-4
 */
public class SelectBoxHtml {

	/**
	 * 产生一个下拉列表框<select></select>元素的html代码。
	 * 此方法为静态方法，不用产生对象。	 * 
	 * @param request,取JSP中的request即可
	 * @param selName <select>元素使用的名字，用于在后台处理时，用此名字值，如在后台：request.getParameter("selName")
	 * @param submitFormName 当用户选择下拉列表框后，执行提交动作的form的名称
	 * @param action 当用户选择下拉列表框后，刷新页面的action
	 * @param rsCodeList 下拉列表框后，刷新页面的action
	 * @param defautSel 下拉列表框的默认被选择中项。
	 * @return String 
	 */
	public static final String OPTINOS_ALL_STR = "all";

	public static String createFromList(String selectBoxName, String onchange, Result rsCodeList, String defaultSel, boolean hasAll) {

		StringBuffer form = new StringBuffer();
		form.append("<select name='").append(selectBoxName).append("' onchange= \"　").append(onchange).append("\">");
		if( hasAll )
			form.append("<option value='all' ").append("all".equals(defaultSel) ? "selected" : "").append(">--全部--</option>");
		for(int i = 0; i < rsCodeList.getRowCount(); i++) {
			String ci_sp_code = rsCodeList.getString(i, "ci_sp_code");
			form.append("<option value='" + ci_sp_code).append("' ");
			form.append(ci_sp_code.equalsIgnoreCase(defaultSel) ? "selected" : "        ").append(" >");
			form.append(rsCodeList.getString(i, "ci_sp_name"));
			form.append("</option>\r\n");
		}
		form.append("</select>");
		return form.toString();
	}

	public static String createFromListWithRefresh(HttpServletRequest request, String selectBoxName, String submitFormName, String action,
					Result rsCodeList, String defaultSel, boolean hasAll) {

		StringBuffer form = new StringBuffer();
		String selStatus = (String)request.getParameter(selectBoxName);
		form.append("<select name='").append(selectBoxName).append("' onchange= \"$.webUtil.submit('").append(action).append("',{frm:$('form[name=")
						.append(submitFormName).append("]')});\">");
		if( selStatus == null )
			selStatus = defaultSel;
		if( hasAll )
			form.append("<option value='all' ").append("all".equals(selStatus) ? "selected" : "").append(">--全部--</option>");
		for(int i = 0; i < rsCodeList.getRowCount(); i++) {
			String ci_sp_code = rsCodeList.getString(i, "ci_sp_code");
			form.append("<option value='" + ci_sp_code).append("' ");
			form.append(ci_sp_code.equalsIgnoreCase(selStatus) ? "selected" : "        ").append(" >");
			form.append(rsCodeList.getString(i, "ci_sp_name"));
			form.append("</option>\r\n");
		}
		form.append("</select>");
		return form.toString();
	}

	
	/**
	 * 组装一个代码信息的option，带有默认选中效果 
	 * 用法：<select name="cat_type"><%=CodeHtmlOptions.genOptions(HouseProperty.getCodeList(), "1")%></select>
	 * @param rsCodeList 某个代码类的getCodeList方法返回值
	 * @param selectedOption 默认被选中的一个option的code
	 * @return String 生成的下拉列表html代码
	 */
	public static String genOptions4QuanBu(Result rsCodeList, String selectedOption) {

		String options = ObjectUtil.getNullString();
		options = options + "<option value=\""+OPTINOS_ALL_STR+"" + "\"" + ">" + "全部" + "</option>\n";
		for(int i = 0; i < rsCodeList.getRowCount(); i++) {
			String selected = "";
			String ci_sp_code = rsCodeList.getString(i, "ci_sp_code");
			if( ci_sp_code.equalsIgnoreCase(selectedOption) ) {
				selected = " selected";
			}
			options = options + "<option value=\"" + ci_sp_code + "\"" + selected + ">" + rsCodeList.getString(i, "ci_sp_name") + "</option>\n";
		}
		return options;
	}
	/**
	 * 组装一个代码信息的option
	 * 用法：<select name="cat_type"><%=CodeHtmlOptions.genOptions(HouseProperty.getCodeList())%></select>
	 * @param rsCodeList 某个代码类的getCodeList方法返回值
	 * @return String 生成的下拉列表html代码
	 */
	public static String genOptions(Result rsCodeList) {

		String sp_class = rsCodeList.getString(0, "ci_sp_class");
		String options = "";
		for(int i = 0; i < rsCodeList.getRowCount(); i++) {
			String ci_sp_code = rsCodeList.getString(i, "ci_sp_code");
			options = options + "<option value=\"" + ci_sp_code + "\"" + ">" + rsCodeList.getString(i, "ci_sp_name") + "</option>\n";
		}
		return options;
	}

	/**
	 *  组装一个代码信息的option
	 * @param rsCodeList
	 * @param noOptions 不显示的信息
	 * @return
	 */
	public static String genOptions(Result rsCodeList, List<String> noOptions) {

		String options = "";
		for(int i = 0; i < rsCodeList.getRowCount(); i++) {
			String ci_sp_code = rsCodeList.getString(i, "ci_sp_code");
			if( !noOptions.contains(ci_sp_code) ) {
				options = options + "<option value=\"" + ci_sp_code + "\"" + ">" + rsCodeList.getString(i, "ci_sp_name") + "</option>\n";
			}
		}
		return options;
	}
	public static String genOptionsWithDefault(Result rsCodeList,String defaultValue,List<String> noOptions) {

		String options = "";
		for(int i = 0; i < rsCodeList.getRowCount(); i++) {
			String ci_sp_code = rsCodeList.getString(i, "ci_sp_code");
			if( !noOptions.contains(ci_sp_code) ) {
				String selected = ci_sp_code.equals(defaultValue) ? " selected " : " ";
				options = options + "<option value=\"" + ci_sp_code + "\"" + selected + ">" + rsCodeList.getString(i, "ci_sp_name") + "</option>\n";
			}
		}
		return options;
	}

	public static String genOptionsToJs(Result rsCodeList) {

		String sp_class = rsCodeList.getString(0, "ci_sp_class");
		String options = "";
		for(int i = 0; i < rsCodeList.getRowCount(); i++) {
			String ci_sp_code = rsCodeList.getString(i, "ci_sp_code");
			options = options + "<option value='" + ci_sp_code + "' >" + rsCodeList.getString(i, "ci_sp_name") + "</option>";
		}
		return options;
	}

	/**
	 * 组装一个代码信息的option
	 * 用法：<select name="cat_type"><%=CodeHtmlOptions.genOptions(HouseProperty.getCodeList())%></select>
	 * @param rsCodeList 某个代码类的getCodeList方法返回值
	 * @return String 生成的下拉列表html代码
	 */
	public static String genOptionsWithDefault(Result rsCodeList, String defaultValue) {

		String sp_class = rsCodeList.getString(0, "ci_sp_class");
		String options = "";
		for(int i = 0; i < rsCodeList.getRowCount(); i++) {
			String ci_sp_code = rsCodeList.getString(i, "ci_sp_code");
			String selected = ci_sp_code.equals(defaultValue) ? " selected " : " ";
			options = options + "<option value=\"" + ci_sp_code + "\"" + selected + ">" + rsCodeList.getString(i, "ci_sp_name") + "</option>\n";
		}
		return options;
	}

	private ArrayList codeList = new ArrayList();

	public void addCode(String code, String name) {

		HashMap newMap = null;
		newMap = new HashMap(2);
		codeList.add(newMap);
		newMap.put("ci_sp_code", code);
		newMap.put("ci_sp_name", name);

	}

	/**
	 * 产生一个下拉列表框<select></select>元素的html代码。
	 * 此访求非静态的，需要先产生对象，并调用"addCode"将下拉列表框内的选择添加完成后才能调用。
	 * @param request,取JSP中的request即可
	 * @param selName <select>元素使用的名字，用于在后台处理时，用此名字值，如在后台：request.getParameter("selName")
	 * @param submitFormName 当用户选择下拉列表框后，执行提交动作的form的名称
	 * @param action 当用户选择下拉列表框后，刷新页面的action
	 * @param defautSel 下拉列表框的默认被选择中项。
	 * @return String
	 */
	public String createWithRefresh(HttpServletRequest request, String selectBoxName, String submitFormName, String action, String defaultSel,
					boolean hasAll) {

		return SelectBoxHtml.createFromListWithRefresh(request, selectBoxName, submitFormName, action, new Result(codeList), defaultSel, hasAll);

	}

	/**
	 * 产生一个下拉列表框<select></select>元素的html代码。
	 * 此访求非静态的，需要先产生对象，并调用"addCode"将下拉列表框内的选择添加完成后才能调用。
	 * @param selectBoxName <select>元素使用的名字，用于在后台处理时，用此名字值，如在后台：request.getParameter("selName")
	 * @param onChange 当用户选择下拉列表框后，执行javascript脚本
	 * @param defautSel 下拉列表框的默认被选择中项。
	 * @param hasAll 是否要添加一个"全部"选项。true 添加，false 不添加
	 * @return String
	 */
	public String create(String selectBoxName, String onChange, String defaultSel, boolean hasAll) {

		return SelectBoxHtml.createFromList(selectBoxName, onChange, new Result(codeList), defaultSel, hasAll);

	}

	/**
	 * 返回页面select区县option。
	 * @param bool {@link Boolean} 是否需要全部
	 * @param info_id_dist {@link String} 回选显示的Id
	 * @return
	 */
	public static String genOptionsHpbHtml(boolean bool, String info_id) {

		Map<String, ResultRow> rsHpb = BaseInfoQuery.mapHpb;
		StringBuffer form = new StringBuffer();
		if( bool ) {
			form.append("<option value=''>--请选择--</option>");
		}
		Object key[] = rsHpb.keySet().toArray();
		for(int i = 0; i < rsHpb.size(); i++) {
			String ci_sp_code = rsHpb.get(key[i]).getString("hpb_id");
			ResultRow hpbRow = rsHpb.get(ci_sp_code);
			form.append("<option value='" + ci_sp_code).append("' ");
			form.append(ci_sp_code.equalsIgnoreCase(info_id) ? "selected" : "  ").append(" >");
			form.append(hpbRow.getString("hp_name"));
			form.append("</option>\r\n");
		}
		return form.toString();
	}
	
	
	/**
	 * 返回页面select区县option。
	 * @param bool {@link Boolean} 是否需要全部
	 * @param info_id_dist {@link String} 回选显示的Id
	 * @return
	 */
	public static String genOptionsCityAndHpbHtml(boolean bool, String info_id) {

		Map<String, ResultRow> rsHpb = BaseInfoQuery.mapcityHpb;
		StringBuffer form = new StringBuffer();
		if( bool ) {
			form.append("<option value=''>--请选择--</option>");
		}
		Object key[] = rsHpb.keySet().toArray();
		for(int i = 0; i < rsHpb.size(); i++) {
			String ci_sp_code = rsHpb.get(key[i]).getString("hpb_id");
			ResultRow hpbRow = rsHpb.get(ci_sp_code);
			form.append("<option value='" + ci_sp_code).append("' ");
			form.append(ci_sp_code.equalsIgnoreCase(info_id) ? "selected" : "  ").append(" >");
			form.append(hpbRow.getString("hp_name"));
			form.append("</option>\r\n");
		}
		return form.toString();
	}
	
	

	/**
	 * 返回页面select环线option。
	 * @param bool {@link Boolean} 是否需要全部
	 * @param info_id_dist {@link String} 回选显示的Id
	 * @return
	 */
	public static String genOptionsLoopHtml(boolean bool, String info_id) {

		Map<String, ResultRow> rsLoop = BaseInfoQuery.mapLoop;
		StringBuffer form = new StringBuffer();
		if( bool ) {
			form.append("<option value=''>--请选择--</option>");
		}
		Object key[] = rsLoop.keySet().toArray();
		for(int i = 0; i < rsLoop.size(); i++) {
			String ci_sp_code = rsLoop.get(key[i]).getString("loop_id");
			ResultRow loopRow = rsLoop.get(ci_sp_code);
			form.append("<option value='" + ci_sp_code).append("' ");
			form.append(ci_sp_code.equalsIgnoreCase(info_id) ? "selected" : "  ").append(" >");
			form.append(loopRow.getString("lp_name"));
			form.append("</option>\r\n");
		}
		return form.toString();
	}

	/**
	 * 根据区县获取区县下的街道
	 * @param hpbId
	 * @param street_id
	 * @return
	 */
	public static String getStreetByHpb(String hpbId, String street_id) {

		Assert.hasText(hpbId, "区县ID不能为空");
		Result mapStreetHpb = BaseInfoQuery.mapStreetHpb.get(hpbId);
		StringBuffer form = new StringBuffer();
		if( mapStreetHpb != null ) {
			for(int i = 0; i < mapStreetHpb.getRowCount(); i++) {
				String ci_sp_code = mapStreetHpb.getString(i, "street_id");
				form.append("<option value='" + ci_sp_code).append("' ");
				form.append(ci_sp_code.equalsIgnoreCase(street_id) ? "selected" : "  ").append(" >");
				form.append(mapStreetHpb.getString(i, "str_name"));
				form.append("</option>\r\n");
			}
		}
		return form.toString();
	}

	/**
	 * 返回页面select街道option。
	 * @param bool {@link Boolean} 是否需要全部
	 * @param info_id_dist {@link String} 回选显示的Id
	 * @return
	 */
	public static String genOptionsStreetHtml(boolean bool, String info_id) {

		Map<String, ResultRow> rsStreet = BaseInfoQuery.mapStreet;
		StringBuffer form = new StringBuffer();
		if( bool ) {
			form.append("<option value=''>--请选择--</option>");
		}
		Object key[] = rsStreet.keySet().toArray();
		for(int i = 0; i < rsStreet.size(); i++) {
			String ci_sp_code = rsStreet.get(key[i]).getString("street_id");
			ResultRow streetRow = rsStreet.get(ci_sp_code);
			form.append("<option value='" + ci_sp_code).append("' ");
			form.append(ci_sp_code.equalsIgnoreCase(info_id) ? "selected" : "  ").append(" >");
			form.append(streetRow.getString("str_name"));
			form.append("</option>\r\n");
		}
		return form.toString();
	}

 
}
