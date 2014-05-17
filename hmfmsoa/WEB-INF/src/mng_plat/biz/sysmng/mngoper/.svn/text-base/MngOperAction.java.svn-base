package mng_plat.biz.sysmng.mngoper;

import fd.commons.jdbc.Result;
import fd.commons.web.ActionResult;
import hmfms.base.ActionResultHmfms;
import hmfms.base.BaseAction;
import hmfms.services.codes.OperStatus;
import hmfms.services.entity.Tellers;
import hmfms.util.RequestUtil;
import hmfms.web.User;
import hmfms.web.commons.PageCounter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>标    题: 物业监管平台（二期）</p>
 * <p>描    述: 操作员管理</p>
 * <p>版    权: Copyright (c) 2014</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2014-1-7 下午02:49:04</p>
 * @author xchao
 * @version 1.1
 */
public class MngOperAction extends BaseAction {

	/**
	 * 首页面查询
	 * @param req
	 * @param rep
	 */
	public void index(HttpServletRequest req, HttpServletResponse rep) {

		User user = getUser(req);
		Tellers tellers = new Tellers();
		String te_name_ind = req.getParameter("te_name_ind");
		String te_id_ind = req.getParameter("te_id_ind");
		String te_state_ind = req.getParameter("te_state_ind");
		String ro_roleid_ind = req.getParameter("ro_roleid_ind");
		tellers.setTe_name(te_name_ind);
		tellers.setTe_state(te_state_ind);
		tellers.setTe_operid(te_id_ind);
		MngOperManager manage = new MngOperManager();
		PageCounter page = new PageCounter(req);
		Result rsOper = manage.getIndexResult(page, tellers, user, ro_roleid_ind);
		req.setAttribute("te_name_ind", te_name_ind);
		req.setAttribute("te_state_ind", te_state_ind);
		req.setAttribute("ro_roleid_ind", rsOper);
		req.setAttribute("rsOper", rsOper);
	}

	/**
	 * 新增
	 * @param req
	 * @param rep
	 */
	public void addoper(HttpServletRequest req, HttpServletResponse rep) {

	}

	/**
	 * 新增保存
	 * @param req
	 * @param rep
	 * @return 
	 */
	public ActionResult addoperok(HttpServletRequest request, HttpServletResponse rep) {

		User user = getUser(request);
		Tellers tellers = new Tellers();
		String type = request.getParameter("te_dept_type");
		RequestUtil.copyProperties(request, tellers);
		tellers.setTe_dept_type(type);
		tellers.setTe_state(OperStatus.XinJiaCaoZuoYuan.toString());
		MngOperManager manage = new MngOperManager();
		manage.addOperator(tellers, user);
		request.setAttribute("te_operid", tellers.getTe_operid());
		Map<Object, Object> mpParam = new HashMap<Object, Object>();
		mpParam.put("operatorid", tellers.getTe_operid());
		String tool_buttons = "打印,open-printoper.do";
		String msg_detail = "新增操作员成功 <br>操作员ID号为：" + tellers.getTe_operid();
		request.setAttribute(ActionResultHmfms.TOOL_BUTTONS, tool_buttons);//按钮信息
		request.setAttribute(ActionResultHmfms.MESSAGE_DETAIL, msg_detail);//子消息信息。（二级信息）
		return ActionResultHmfms.toPageOkWithParam(request, mpParam, false);
	}

	/**
	 * 打印操作员
	 * 
	 * @param req
	 * @param rep
	 */
	public void printoper(HttpServletRequest req, HttpServletResponse rep) {

		getUser(req);
		String operid = req.getParameter("operatorid");
		MngOperManager manage = new MngOperManager();
		Result retMp = manage.getOperator(operid);
		req.setAttribute("retMp", retMp);
	}

	/**
	 * 部门选择
	 * 
	 * @param req
	 * @param rep
	 */
	public void getdept(HttpServletRequest req, HttpServletResponse rep) {

		User user = getUser(req);
		String type = req.getParameter("te_dept_type");
		String org_name = req.getParameter("org_name");
		String org_code = req.getParameter("org_code");
		PageCounter pageMng = new PageCounter(req);
		MngOperManager manage = new MngOperManager();
		Result rsDept = manage.getDeptResult(pageMng, user, org_name, org_code, type);
		req.setAttribute("rsDept", rsDept);
	}

	/**
	 * 查询操作员详细
	 * 
	 * @param req
	 * @param rep
	 */
	public void getoper(HttpServletRequest req, HttpServletResponse rep) {

		getUser(req);
		String operid = req.getParameter("te_operid");
		MngOperManager manage = new MngOperManager();
		Result retMp = manage.getOperator(operid);
		req.setAttribute("retMp", retMp);
	}

	/**
	 * 编辑操作员
	 * 
	 * @param req
	 * @param rep
	 */
	public void editoper(HttpServletRequest req, HttpServletResponse rep) {

		String operid = req.getParameter("te_operid");
		MngOperManager manage = new MngOperManager();
		Result retMp = manage.getOperator(operid);
		req.setAttribute("retMp", retMp);
	}
	/**
	 * 编辑操作员成功后,跳转到成功页
	 * 
	 * @param req
	 * @param rep
	 * @return 从页面传入的指定成功页
	 */
	public ActionResult editoperok(HttpServletRequest req, HttpServletResponse rep) {

		getUser(req);
		MngOperManager manage = new MngOperManager();
		String operid = req.getParameter("te_operid");
		String te_name = req.getParameter("te_name");
		String te_remark = req.getParameter("te_remark");
		manage.editOperator(te_name,te_remark,operid);
		return ActionResultHmfms.toPageOk(req, false);
	}
	/**
	 * 密码重置
	 */
	public ActionResult resetPassword(HttpServletRequest req, HttpServletResponse rep) {

		getUser(req);
		String operid = req.getParameter("te_operid");
		MngOperManager manager = new MngOperManager();
		manager.resetPassword(operid);
		return ActionResultHmfms.toPageOk(req, false);
	}
	/**
	 * 修改角色状态
	 * @param req
	 * @param rep
	 * @author xchao
	 */
	public ActionResult upedateTe_state(HttpServletRequest req, HttpServletResponse rep) {

		String te_operid = req.getParameter("te_operid");
		String flag = req.getParameter("flag");
		MngOperManager manage = new MngOperManager();
		manage.upedateTe_state(te_operid, flag);
		return ActionResultHmfms.toPageOk(req, false);
	}

	/**
	 * 删除操作员成功后,跳转到成功页
	 * 
	 * @param req
	 * @param rep
	 * @return 从页面传入的指定成功页
	 */
	public ActionResult deloperok(HttpServletRequest req, HttpServletResponse rep) {

		getUser(req);
		String operid = req.getParameter("operatorid");
		MngOperManager manage = new MngOperManager();
		manage.deleteOperator(operid);
		return ActionResultHmfms.toPageOk(req, false);
	}

}
