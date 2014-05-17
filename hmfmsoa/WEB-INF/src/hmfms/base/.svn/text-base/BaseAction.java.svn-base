package hmfms.base;

import hmfms.util.ActionUtil;
import hmfms.util.Constants;
import hmfms.util.Debug;
import hmfms.util.StringUtil;
import hmfms.web.User;
import hmfms.web.commons.PageCounter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mng_plat.service.operlog.OperLog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fd.commons.web.ProjectAction;
import fd.exception.BusinessException;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 使用框架内置转发机制，需被所有action继承的超类</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:16:25</p>
 * @author 产品开发部
 * @version 2.0
 * BaseAction
 */
public class BaseAction extends ProjectAction {

	private static final Log logger = LogFactory.getLog(BaseAction.class);
	public static final boolean DEBUG = Constants.DEV_DEBUG;
	public static final boolean INFO_OUT = Constants.INFO_OUT;
	public static final boolean ERROR_OUT = Constants.ERROR_OUT;
	public static final String USER_SESSION = "user.session";
	public static final String ERROR_FROM_WHERE = "g_attr_error_from_where_uri";
	public static final String BLACKBALL = "blackball"; //互斥交易
	public static final String BACKPAGE = "backpage"; //互斥交易页的返回按钮需要的.do
	public static final String ALTERNATIVERESULTSET = "alternativeresultset"; //互斥交易结果集的参数名
	public static final String MESSAGE = "message.redo";
	public static final String OMUTEX = "OMutex"; //开户互斥交易列表
	public static final String ReturnFlag = "flag"; //开户，含有在途控制交易的返回结果标识
	public static final String FLAG_TRUE = "TRUE"; //开户，含有在途控制交易的返回为真
	public static final String FLAG_FALSE = "FALSE"; //开户，含有在途控制交易的返回为假
	public static final String FLAG_OMUTEX = "OMutex"; //开户，含有在途控制交易的返回为存在互斥交易
	public static final String BACK_PAGE = "back_page"; //开户,互斥页返回的前一页
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	public static final String ERROR = "error";

	/**
	 * 所有业务验证这里不做（比如小区用户是否选择过业务大会等在hmfms的BaseAction里已经做的验证功能）。<br>
	 * 因为这是重复的工作，还会导致修改的时候两边都改。<BR>
	 * 使用这个基类的ACTION，对于小区用户，应该从一期forward过来。<br>
	 * 这里只做必要的SESSION验证
	 */
	protected ActionForward preProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		String uri = request.getServletPath();
		String path = uri.substring(uri.lastIndexOf("biz") + 4, uri.lastIndexOf('/'));
		String action = getActionMethod(request);

		Debug.info(logger, "path=" + path + ",action=" + action);

		if( !"logout".equals(action) ) {
			int swt = ActionUtil.userAuthenticate(request);
			switch(swt) {
			case ActionUtil.AUDIT_SYSTEM_ERROR:
				throw new Exception("系统异常！");
			case ActionUtil.AUDIT_UNLOCKED:
				request.setAttribute("invalidateSession", ActionUtil.AUDIT_UNLOCKED_TIPMSG);
				return mapping.findForward("invalidateSession");
			case ActionUtil.AUDIT_NOSESSION:
				return mapping.findForward("invalidateSession");
			}
			String epassCheckMsg = ActionUtil.checkEpass(request, response);
			if( epassCheckMsg != null ) {
				throw new BusinessException(epassCheckMsg);
			}
			/*记录操作日志*/
			User user = getUser(request);
			if( !"init".equals(path) && !"userindex/datacheck".equals(path) && path.toLowerCase().indexOf("listener") == -1 ) {
				OperLog.saveOperLog(user, action, request);
			}
		}
		else {
			try {
				User user = getUser(request);
				OperLog.saveOperLog(user, "99_登出", request);
			}
			catch(Exception e) {
				Debug.exception(logger, "记录操作日志失败", e);
			}

		}
		return null;
	}

	protected String getWorkId(HttpServletRequest request) {

		return StringUtil.getString(request.getAttribute("work_id"));
	}

	protected User getUser(HttpServletRequest request) {

		return ActionUtil.getUser(request);
	}

	protected PageCounter getPage(HttpServletRequest request) {

		PageCounter pagepage = new PageCounter(request);
		pagepage.setPageSize(150);
		return pagepage;
	}

	protected void debug(String msg) {

		if( DEBUG ) {
			System.out.println("-->[DEBUG][web]: " + msg);
		}
	}

	protected void error(String msg) {

		System.out.println("-->[ERROR][web]: " + msg);
	}
}