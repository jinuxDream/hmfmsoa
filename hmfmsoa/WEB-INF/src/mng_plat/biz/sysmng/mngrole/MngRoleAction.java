/**
 * 
 */
package mng_plat.biz.sysmng.mngrole;

import fd.commons.jdbc.Result;
import fd.commons.web.ActionResult;
import hmfms.base.ActionResultHmfms;
import hmfms.base.BaseAction;
import hmfms.services.entity.Role;
import hmfms.services.entity.Role_menu;
import hmfms.util.RequestUtil;
import hmfms.util.StringUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>标    题: 房屋维修资金管理系统银行端</p>
 * <p>描    述: </p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2011-3-3 下午09:55:22</p>
 * @author 产品开发部
 * @version 1.1
 * MngRoleAction
 */
public class MngRoleAction extends BaseAction {

	/**
	 * 前台首页
	 * 
	 * @param req
	 * @param rep
	 */
	public void index(HttpServletRequest req, HttpServletResponse rep) {

		/* 从页面取筛选数据 */
		String rolecode = req.getParameter("rolecode");
		MngRoleManager manage = new MngRoleManager();
		/* 调用业务处理方法 */
		Result rsRole = manage.getAllRole(rolecode);
		/* 处理向页面返回的数据 */
		req.setAttribute("rsRole", rsRole);
	}

	/**
	 * 增加用户角色
	 * 
	 * @param req
	 * @param rep
	 */
	public void addrole(HttpServletRequest req, HttpServletResponse rep) {

	}

	/**
	 * 增加用户角色成功后,跳转到成功页
	 * 
	 * @param req
	 * @param rep
	 * @return
	 */
	public ActionResult addroleok(HttpServletRequest req, HttpServletResponse rep) {

		/* 使用角色实体 */
		Role role = new Role();
		/* 拷贝页面数据到菜单总表实体 */
		RequestUtil.copyProperties(req, role);
		/* 调用业务处理的新增菜单方法 */
		MngRoleManager manage = new MngRoleManager();
		manage.addRole(role);
		/* 根据页面指定的跳转路径,调用公共方法使之跳到对应页面 */
		return ActionResultHmfms.toPageOk(req, false);
	}

	/**
	 * 编辑用户角色
	 * 
	 * @param req
	 * @param rep
	 */
	public void editrole(HttpServletRequest req, HttpServletResponse rep) {

		/* 从页面取选中角色 */
		String rolecode = req.getParameter("roleid");
		MngRoleManager manage = new MngRoleManager();
		/* 调用业务处理方法 */
		Result rsRole = manage.getRoleByCode(rolecode);
		/* 处理向页面返回的数据 */
		req.setAttribute("rsRole", rsRole);
	}

	/**
	 * 编辑用户角色成功后,跳转到成功页
	 * 
	 * @param req
	 * @param rep
	 * @return 从页面传入的指定成功页
	 */
	public ActionResult editroleok(HttpServletRequest req, HttpServletResponse rep) {

		/* 使用角色实体 */
		Role role = new Role();
		/* 拷贝页面数据到菜单总表实体 */
		RequestUtil.copyProperties(req, role);
		/* 调用业务处理的新增菜单方法 */
		MngRoleManager manage = new MngRoleManager();
		/* 先判断是否存在下级菜单 */
		manage.editRole(role);
		/* 根据页面指定的跳转路径,调用公共方法使之跳到对应页面 */
		return ActionResultHmfms.toPageOk(req, false);
	}

	/**
	 * 删除用户角色
	 * 
	 * @param req
	 * @param rep
	 */
	public void delrole(HttpServletRequest req, HttpServletResponse rep) {

		/* 从页面取选中角色 */
		String rolecode = req.getParameter("roleid");
		MngRoleManager manage = new MngRoleManager();
		/* 调用业务处理方法 */
		Result rsRole = manage.getRoleByCode(rolecode);
		/* 处理向页面返回的数据 */
		req.setAttribute("rsRole", rsRole);
	}

	/**
	 * 删除用户角色成功后,跳转到成功页
	 * 
	 * @param req
	 * @param rep
	 * @return 从页面传入的指定成功页
	 */
	public ActionResult delroleok(HttpServletRequest req, HttpServletResponse rep) {

		/* 使用角色实体 */
		Role role = new Role();
		/* 拷贝页面数据到菜单总表实体 */
		RequestUtil.copyProperties(req, role);
		/* 调用业务处理的新增菜单方法 */
		MngRoleManager manage = new MngRoleManager();
		/* 先判断是否存在下级菜单 */
		manage.isHasTellers(role);
		manage.delRole(role);
		/* 根据页面指定的跳转路径,调用公共方法使之跳到对应页面 */
		return ActionResultHmfms.toPageOk(req, false);
	}

	/**
	 * 角色详细信息页
	 * 
	 * @param req
	 * @param rep
	 */
	public void getrole(HttpServletRequest req, HttpServletResponse rep) {

		/* 从页面取选中角色 */
		String rolecode = req.getParameter("roleid");
		MngRoleManager manage = new MngRoleManager();
		/* 调用业务处理方法 */
		Result rsRole = manage.getRoleByCode(rolecode);
		/* 处理向页面返回的数据 */
		req.setAttribute("rsRole", rsRole);
	}

	/**
	 * 分配权限,取菜单
	 * 
	 * @param req
	 * @param rep
	 */
	public void allocate(HttpServletRequest req, HttpServletResponse rep) {

		/* 从页面取选中角色 */
		String roleid = req.getParameter("roleid");
		MngRoleManager manage = new MngRoleManager();
		/* 调用业务处理方法 */
		Result rs = manage.getMenuListByRole(roleid);
		/* 处理向页面返回的数据 */
		req.setAttribute("rsMenu", rs);
	}

	/**
	 * 分配权限成功,跳转到成功页
	 * 
	 * @param req
	 * @param rep
	 * @return
	 */
	public ActionResult allocateok(HttpServletRequest req, HttpServletResponse rep) {

		String menu = req.getParameter("menu");
		Role_menu rolemenu = new Role_menu();
		RequestUtil.copyProperties(req, rolemenu);
		List menuArray = StringUtil.parseStringToArrayList(menu, "|");
		MngRoleManager manage = new MngRoleManager();
		manage.allocate(rolemenu, menuArray);
		return ActionResultHmfms.toPageOk(req, false);
	}
}
