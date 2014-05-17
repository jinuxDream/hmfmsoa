package UserDemandTrack;

import fd.commons.jdbc.Result;
import fd.commons.web.ActionResult;
import hmfms.base.ActionResultHmfms;
import hmfms.base.BaseAction;
import hmfms.services.entity.Demand_track;
import hmfms.util.RequestUtil;
import hmfms.web.User;
import hmfms.web.commons.PageCounter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserTrackAction extends BaseAction{
	/**
	 * 用户需求跟踪首页
	 * @param request
	 * @param response
	 */
	public void index(HttpServletRequest request,HttpServletResponse response){
		User user = getUser(request);
		UserTrackManager manager = new UserTrackManager();
		PageCounter page = new PageCounter(request);
		Result rs = manager.index(user,page);
		request.setAttribute("rs", rs);
		request.setAttribute("user", user);
	}
	
	/**
	 * 用户需求跟踪新增
	 * @param request
	 * @param response
	 */
	public void addCheck(HttpServletRequest request,HttpServletResponse response){
		UserTrackManager manager = new UserTrackManager();
		Result rs = manager.addCheck();
		request.setAttribute("rs", rs);
	}
	
	/**
	 * 用户需求跟踪新增保存
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionResult addCheckOk(HttpServletRequest request,HttpServletResponse response){
		User user = getUser(request);
		Demand_track dt = new Demand_track();
		RequestUtil.copyProperties(request, dt);//从request内复制Parameter到Demand_track实体内
		UserTrackManager manager = new UserTrackManager();
		manager.addCheckOk(user,dt);
		String demand = "用户需求跟踪-新增成功"  + "<br>";
		request.setAttribute(ActionResultHmfms.MESSAGE_DETAIL, demand);
		request.setAttribute(ActionResultHmfms.GOBACK_URL, "index.do");
		return ActionResultHmfms.toPageOkWithParam(request,  null, false);
	}
	
	/**
	 * 根据系统名ID查询出系统名称(下拉框)
	 * @param request
	 * @param response
	 */
	public void getDemand_trackOptionSDO(HttpServletRequest request, HttpServletResponse response) {
		User user = getUser(request);
		String sn_id = request.getParameter("sn_id");//系统名id
		UserTrackManager manager = new UserTrackManager();
		String option = manager.genOptionsWithDefault(user, sn_id);
		renderText(response, option);
	}
	
	/**
	 * 编辑用户需求跟踪信息前
	 * 根据选择的变更申请单ID，获取要编辑的用户需求跟踪的申请单
	 * @param request
	 * @param response
	 */
	public void editCheck(HttpServletRequest request,HttpServletResponse response){
		User user = getUser(request);
		String dt_no = request.getParameter("dt_no");//变更申请单ID
		UserTrackManager manager = new UserTrackManager();
		Result rs = manager.getSectChkInfo(user,dt_no);
		request.setAttribute("rs",rs);
	}
	
	/**
	 * 用户需求跟踪编辑保存
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionResult editCheckOk(HttpServletRequest request,HttpServletResponse response){
		User user = getUser(request);
		Demand_track dt = new Demand_track();
		RequestUtil.copyProperties(request, dt);//从request内复制Parameter到Demand_track的实体内
		String dt_no = request.getParameter("dt_no");
		String dt_prop_name = request.getParameter("dt_prop_name");
		String dt_invol_demand = request.getParameter("dt_invol_demand");
		String dt_stat_date = request.getParameter("dt_stat_date");
		String dt_end_date = request.getParameter("dt_end_date");
		String dt_date = request.getParameter("dt_date");
		String dt_work = request.getParameter("dt_work");
		String dt_implement = request.getParameter("dt_implement");
		String dt_impl_pers = request.getParameter("dt_impl_pers");
		String dt_test_pas = request.getParameter("dt_test_pas");
		String dt_assess = request.getParameter("dt_assess");
		String dt_content = request.getParameter("dt_content");
		String dt_remark = request.getParameter("dt_remark");
		UserTrackManager manager = new UserTrackManager();
		manager.editCheckOk(user, dt, dt_no,dt_prop_name,dt_invol_demand,dt_stat_date,dt_end_date,
				dt_date,dt_work,dt_implement,dt_impl_pers,dt_test_pas,dt_assess,dt_content,dt_remark);
		String demand = "用户需求跟踪→编辑成功"  + "<br>";
		request.setAttribute(ActionResultHmfms.MESSAGE_DETAIL, demand);
		request.setAttribute(ActionResultHmfms.GOBACK_URL, "index.do");
		return ActionResultHmfms.toPageOkWithParam(request, null, false);
	}
}
