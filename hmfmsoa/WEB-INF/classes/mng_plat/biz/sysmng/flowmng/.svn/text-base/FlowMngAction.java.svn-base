package mng_plat.biz.sysmng.flowmng;

import fd.commons.jdbc.Result;
import hmfms.base.BaseAction;
import hmfms.services.util.UtilRequestMap;
import hmfms.web.User;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FlowMngAction extends BaseAction {

	public void index(HttpServletRequest request, HttpServletResponse response) {

		User user = getUser(request);
		FlowMngManager manager = new FlowMngManager();
		Map<String, Result> map = manager.getRole(user);
		request.setAttribute("map", map);
	}

	public void save(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Map<String, String>>[] map = UtilRequestMap.ReqConversionMap(request);
		
		FlowMngManager m = new FlowMngManager();
		m.addRegInfo(map);
	}
}
