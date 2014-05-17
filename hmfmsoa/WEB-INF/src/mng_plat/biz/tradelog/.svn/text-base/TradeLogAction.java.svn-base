package mng_plat.biz.tradelog;

import fd.commons.jdbc.Result;
import fd.commons.web.ActionResult;
import hmfms.base.ActionResultHmfms;
import hmfms.base.BaseAction;
import hmfms.web.User;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mng_plat.service.tran.TradeRecorder;

public class TradeLogAction extends BaseAction {

	//填写退回原因页面
	public ActionResult reviewBack(HttpServletRequest request, HttpServletResponse response) {

		ActionResult ar = new ActionResult();
		ar.setForwardUri("/WEB-INF/jsp/mng_plat/biz/tradelog/repealsubmit.jsp", true);
		return ar;

	}

	/**
	 * 审核退回
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionResult setTradeGoBack(HttpServletRequest request, HttpServletResponse response) {

		User user = getUser(request);
		String work_id = getWorkId(request);
		String batch_no = request.getParameter("batch_no");
		String remark = request.getParameter("remark");
		String back_home_url = request.getParameter(ActionResultHmfms.BACK_HOME_URL);
		TradeRecorder.auditRedo(user, batch_no, work_id, remark);
		Map<Object, Object> mpParam = new HashMap<Object, Object>();
		request.setAttribute(ActionResultHmfms.IS_CLOSE, "2");//如果要关闭窗口，传入IS_CLOSE = "2"
		request.setAttribute(ActionResultHmfms.BACK_HOME_URL, back_home_url);
		return ActionResultHmfms.toPageOkWithParam(request, mpParam, false);
	}

	public ActionResult listGoBackLog(HttpServletRequest request, HttpServletResponse response) {

		String batch_no = request.getParameter("batch_no");
		String tran_status = request.getParameter("tran_status");
		ActionResult ar = new ActionResult();
		Result rs = TradeRecorder.listGoBackLog(batch_no, tran_status);
		request.setAttribute("redoList", rs);
		ar.setForwardUri("/WEB-INF/jsp/mng_plat/biz/tradelog/listGoBackLog.jsp", true);
		return ar;

	}
}
