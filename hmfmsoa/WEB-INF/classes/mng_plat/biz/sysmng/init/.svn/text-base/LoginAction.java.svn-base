package mng_plat.biz.sysmng.init;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.SqlOperator;
import fd.commons.web.ActionResult;
import hmfms.base.BaseAction;
import hmfms.services.codes.OperStatus;
import hmfms.util.Debug;
import hmfms.util.ObjectUtil;
import hmfms.util.RequestUtil;
import hmfms.web.User;
import hmfms.web.commons.PageCounter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mng_plat.service.workgroup.WorkGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginAction extends BaseAction {

	private static final Log logger = LogFactory.getLog(LoginAction.class);

	public void index(HttpServletRequest request, HttpServletResponse response) {

		User user = getUser(request);
		String roled_id = user.getRoled_id();
		String[] group_id = user.getGroup_Arry();
		String htmlMenu = htmlMenu(user, roled_id, group_id, request);
		request.setAttribute("htmlMenu", htmlMenu);
	}

	/**
	 * 装载主页面的底部页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public void foot(HttpServletRequest request, HttpServletResponse response) {

		getUser(request);
	}

	/**
	 * 根据角色装载主页面的顶部页面 (纵向)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public void top(HttpServletRequest request, HttpServletResponse response) {

		getUser(request);
	}

	public void rightImage(HttpServletRequest request, HttpServletResponse response) {

		getUser(request);
	}

	public void leftImage(HttpServletRequest request, HttpServletResponse response) {

		getUser(request);
	}

	public String htmlMenu(User user, String roled_id, String[] group_id, HttpServletRequest request) {

		StringBuffer sb = new StringBuffer();
		LoginMng mng = new LoginMng();
		Result rsMenu = mng.getMenuListByOper(user, roled_id, group_id);
		for(int i = 0; i < rsMenu.getRowCount(); i++) {
			sb.append("<li><a><img style='margin-left: -15px' src='" + request.getContextPath() + "/images/menu/menu_arrow2.gif'/>"
							+ rsMenu.getString(i, "menu_name") + "</a>");
			sb.append("<ul class='sub_menu'>");
			Result rs1 = (Result)rsMenu.getObject(i, "submenu");
			for(int j = 0; j < rs1.getRowCount(); j++) {
				sb.append("<li> <a>" + rs1.getString(j, "menu_name") + "</a>");
				sb.append(" <ul class='sonsub_menu'>");
				Result rs2 = (Result)rs1.getObject(j, "submenu");
				for(int k = 0; k < rs2.getRowCount(); k++) {
					String strRelate = rs2.getString(k, "url_link");
					String strName = rs2.getString(k, "menu_name");
					String href = request.getContextPath() + "/" + strRelate;
					sb.append("<li><a target='mainframe' href='" + href + "'>" + strName + "</a></li>");
				}
				sb.append("</ul></li>");
			}
			sb.append("</ul></li>");
		}
		return sb.toString();
	}

	public void workspace(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute(BaseAction.USER_SESSION);
		String roledID = request.getParameter("roled_id");
		user.setRoled_id(roledID);
		String[] groupId = request.getParameterValues("group_id");
		user.setGroup_Arry(groupId);
		session.setMaxInactiveInterval(280000); // 8小时
		session.setAttribute(BaseAction.USER_SESSION, user);
		Map<String, Result> map = WorkGroup.getInstance(user).getTellerGroupOrRoleByName();
		request.setAttribute("map", map);
		Result rsRole = map.get("rsRole");
		Result rsGroup = map.get("rsGroup");
		String roled_id = user.getRoled_id();
		if( rsGroup.getRowCount() == 0 ) {
			if( ObjectUtil.isEmpty(roled_id) ) {
				roled_id = rsRole.getString(0, "ro_roleid");
				user.setRoled_id(roled_id);
			}
		}
		String ro_name = rsRole.getString(0, "ro_name");
		user.setRo_name(ro_name);
		String[] group_id = user.getGroup_Arry();
		String htmlMenu = htmlMenu(user, roled_id, group_id, request);
		request.setAttribute("htmlMenu", htmlMenu);
		PageCounter page = this.getPage(request);
		page.setPageSize(10);
	 
	}

	/**
	 * 退出系统，修改操作员的状态，清理session
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionResult logout(HttpServletRequest request, HttpServletResponse response) {

		LoginMng mng = new LoginMng();
		ActionResult ar = new ActionResult();
		User user = (User)request.getSession().getAttribute(BaseAction.USER_SESSION);
		if( user != null && user.getTellID().length() >= 9 ) {
			// 清理session中的对象
			request.getSession().removeAttribute(BaseAction.USER_SESSION);
			request.getSession().invalidate();
		}
		else {
			if( user != null ) {
				SqlOperator dbo = new SqlOperator();
				dbo.addSql("select te_sessionid from Tellers where ");
				dbo.addEqualParam("Te_operid", user.getTellID());
				Result rs = dbo.query(true);
				if( rs.getRowCount() != 1 ) {
					Debug.info(logger, "---->[system error] 根据用户ID【" + user.getTellID() + "】查询到的记录数为：" + rs.getRowCount());
				}
				if( user.getUserSessionID().equals(rs.getString(0, "te_sessionid")) ) {
					String errMsg = mng.updateOperStatus(user.getTellID(), OperStatus.ZhengChangShiYong, "");
					if( errMsg != null ) {
						ar.setERROR(errMsg);
						ar.setForwardUri("/jsp/common/error.jsp", true);
					}
				}
				// 清理session中的对象
				request.getSession().removeAttribute(BaseAction.USER_SESSION);
				request.getSession().invalidate();
			}
		}
		if( "true".equals(request.getParameter("toclose")) ) {
			return null;
		}
		String toClose = "true".equals(request.getParameter("toclose")) ? "?toClose=true" : "";
		ar.setForwardUri("/WEB-INF/jsp/mng_plat/biz/sysmng/init/logout.jsp" + toClose, true);
		return ar;
	}

	public void changePwd(HttpServletRequest request, HttpServletResponse response) {

		getUser(request);
	}

	public ActionResult changePwdOK(HttpServletRequest request, HttpServletResponse response) {

		ActionResult ar = new ActionResult();
		String strOpID = request.getParameter("opid");
		String strOldPass = request.getParameter("oldpass");
		String strNewPass = request.getParameter("newpass");
		String strOldUnLock = request.getParameter("oldunlock");
		String strNewUnLock = request.getParameter("newunlock");
		LoginMng mng = new LoginMng();
		String errMng = mng.modiPass(strOpID, strOldPass, strNewPass, strOldUnLock, strNewUnLock);
		if( !(null == errMng) ) {
			String retpm = "pagepath=操作失败&rdok_tips=" + errMng + "&ok=0";
			ar.setForwardUri("/jsp/commons/changePwdOK.jsp" + RequestUtil.encodeFullURL(retpm), false);
		}
		else {
			String retpm = "pagepath=操作成功&rdok_tips=密码修改成功&ok=1";
			ar.setForwardUri("/jsp/commons/changePwdOK.jsp" + RequestUtil.encodeFullURL(retpm), false);
		}
		return ar;
	}
}
