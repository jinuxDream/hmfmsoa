/**
 * 
 */
package mng_plat.biz.sysmng.mngmenu;

import fd.commons.jdbc.Result;
import fd.commons.web.ActionResult;
import hmfms.base.ActionResultHmfms;
import hmfms.base.BaseAction;
import hmfms.services.codes.MenuFunLevel;
import hmfms.services.entity.Menu_info;
import hmfms.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>标    题: 房屋维修资金管理系统银行端</p>
 * <p>描    述: </p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2011-3-3 下午09:55:55</p>
 * @author 产品开发部
 * @version 1.1
 * MngMenuAction
 */
public class MngMenuAction extends BaseAction {

	/**
	 * 前台首页
	 * @param req
	 * @param rep
	 */
	public void index(HttpServletRequest req, HttpServletResponse rep) {

		getUser(req);
		/* 从页面取筛选数据 */
		String menucode = req.getParameter("ms_code1");
		MngMenuManager manage = new MngMenuManager();
		/* 调用业务处理方法 */
		Result rsMenu = manage.getAllMenu(menucode); // 取所有菜单数据
		/* 处理向页面返回的数据 */
		req.setAttribute("rsMenu", rsMenu);
	}

	/**
	 * 增加系统菜单
	 * @param req
	 * @param rep
	 */
	public void addmenu(HttpServletRequest req, HttpServletResponse rep) {

		MngMenuManager manage = new MngMenuManager();
		/* 调用业务处理方法 */
		Result rsPriMenu = manage.getMenuByLevel(MenuFunLevel.YiJiCaiDan); // 取一级菜单
		Result rsSubMenu = manage.getMenuByLevel(MenuFunLevel.ErJiCaiDan); // 取二级菜单
		/* 处理向页面返回的数据 */
		req.setAttribute("rsPriMenu", rsPriMenu);
		req.setAttribute("rsSubMenu", rsSubMenu);
	}

	/**
	 * 增加系统菜单成功后,跳转到成功页
	 * @param req
	 * @param rep
	 * @return
	 */
	public ActionResult addmenuok(HttpServletRequest req, HttpServletResponse rep) {

		/* 使用菜单总表实体 */
		Menu_info menu = new Menu_info();
		/* 拷贝页面数据到菜单总表实体 */
		RequestUtil.copyProperties(req, menu);
		/* 调用业务处理的新增菜单方法 */
		MngMenuManager manage = new MngMenuManager();
		manage.addMenu(menu);
		/* 根据页面指定的跳转路径,调用公共方法使之跳到对应页面 */
		return ActionResultHmfms.toPageOk(req, false);
	}

	/**
	 * 编辑系统菜单
	 * @param req
	 * @param rep
	 */
	public void editmenu(HttpServletRequest req, HttpServletResponse rep) {

		/* 从页面取到需要编辑的菜单编码 */
		String menucode = req.getParameter("menu_id");
		/* 调用业务方法取指定菜单编码的详细 */
		MngMenuManager manage = new MngMenuManager();
		Result rsMenu = manage.getMenuByCode(menucode);
		Result rsSubMenu = manage.getMenuByLevel(MenuFunLevel.ErJiCaiDan); // 取二级菜单
		/* 页面返回详细 */
		req.setAttribute("rsMenu", rsMenu);
		req.setAttribute("rsSubMenu", rsSubMenu);
	}

	/**
	 * 编辑系统菜单成功后,跳转到成功页
	 * @param req
	 * @param rep
	 * @return 从页面传入的指定成功页
	 */
	public ActionResult editmenuok(HttpServletRequest req, HttpServletResponse rep) {

		/* 使用菜单总表实体 */
		Menu_info menu = new Menu_info();
		/* 拷贝页面数据到菜单总表实体 */
		RequestUtil.copyProperties(req, menu);
		/* 调用业务处理的新增菜单方法 */
		MngMenuManager manage = new MngMenuManager();
		manage.editMenu(menu);
		/* 根据页面指定的跳转路径,调用公共方法使之跳到对应页面 */
		return ActionResultHmfms.toPageOk(req, false);
	}

	/**
	 * 删除系统菜单
	 * 
	 * @param req
	 * @param rep
	 */
	public void delmenu(HttpServletRequest req, HttpServletResponse rep) {

		/* 从页面取到需要编辑的菜单编码 */
		String menucode = req.getParameter("menu_id");
		/* 调用业务方法取指定菜单编码的详细 */
		MngMenuManager manage = new MngMenuManager();
		Result rsMenu = manage.getMenuByCode(menucode);
		Result rsSubMenu = manage.getMenuByLevel(MenuFunLevel.ErJiCaiDan); // 取二级菜单
		/* 页面返回详细 */
		req.setAttribute("rsMenu", rsMenu);
		req.setAttribute("rsSubMenu", rsSubMenu);
	}

	/**
	 * 删除系统菜单成功后,跳转到成功页
	 * @param req
	 * @param rep
	 * @return 从页面传入的指定成功页
	 */
	public ActionResult delmenuok(HttpServletRequest req, HttpServletResponse rep) {

		/* 使用菜单总表实体 */
		Menu_info menu = new Menu_info();
		/* 拷贝页面数据到菜单总表实体 */
		RequestUtil.copyProperties(req, menu);
		/* 调用业务处理的新增菜单方法 */
		MngMenuManager manage = new MngMenuManager();
		/* 先判断是否存在下级菜单 */
		manage.isHasSub(menu);
		manage.delMenu(menu);
		/* 根据页面指定的跳转路径,调用公共方法使之跳到对应页面 */
		return ActionResultHmfms.toPageOk(req, false);
	}
}
