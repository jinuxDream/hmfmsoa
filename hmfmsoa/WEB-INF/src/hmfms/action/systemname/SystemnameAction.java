package hmfms.action.systemname;

import fd.commons.jdbc.Result;
import fd.commons.web.ActionResult;
import hmfms.base.BaseAction;
import hmfms.util.RequestUtil;
import hmfms.web.User;
import hmfms.web.system_name;
import hmfms.web.commons.PageCounter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 系统的action系统的增删改查
 * @author hongzhi
 *
 */
public class SystemnameAction extends BaseAction {

	/**
	 * 所有的系统
	 * @param request
	 * @param response
	 */
	public void  index(HttpServletRequest request,HttpServletResponse response){
		User user = getUser(request);
		SystemnameManager manager = new SystemnameManager();
		PageCounter page = new PageCounter(request);
		Result rs = manager.index(user,page);
		request.setAttribute("rs", rs);
		
	} 
	public void  add(HttpServletRequest request,HttpServletResponse response){
		System.out.println("add---------------------------");
	} 
	public ActionResult save(HttpServletRequest request,HttpServletResponse response){
		System.out.println("1---------------------------------add");
		User user = getUser(request); 
		system_name systemName=new system_name();
		SystemnameManager manager=new SystemnameManager();
		RequestUtil.copyProperties(request, systemName);
		manager.add(user, systemName);
		String retfd="/jsp/common/pageok.jsp";
		String retpm="pagepath=用户系统新增->新增&rdok_tips=操作成功!&return=/hmfms/action/systemname/add.do";
		ActionResult ar=new ActionResult();
		ar.setForwardUri(retfd+RequestUtil.encodeURL(retpm),false);
		System.out.println("2---------------------------------add");
		return ar;
	} 
	
	public void  delete(HttpServletRequest request,HttpServletResponse response){
		
	} 
	public void  input(HttpServletRequest request,HttpServletResponse response){
		
	} 
	public void  update(HttpServletRequest request,HttpServletResponse response){
		
	} 
	
	
	
}
