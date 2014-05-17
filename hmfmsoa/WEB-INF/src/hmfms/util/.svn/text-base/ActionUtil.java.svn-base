package hmfms.util;

import fd.exception.BusinessException;
import hmfms.base.BaseAction;
import hmfms.web.User;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: </p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:40:16</p>
 * @author 产品开发部
 * @version 2.0
 * ActionUtil
 */
public class ActionUtil extends AbstractUtil {
	private static final Log logger = LogFactory.getLog(ActionUtil.class);
	
	private ActionUtil() {

	}

	public static final int AUDIT_SYSTEM_ERROR = -2; //系统异常
	public static final int AUDIT_NOSELECTED_HOC = -1;
	public static final int AUDIT_NOSESSION = 0;
	public static final int AUDIT_PASS = 1;
	public static final int AUDIT_UNLOCKED = 2;
	public static final String AUDIT_UNLOCKED_TIPMSG = "该用户已经被解锁，请退出后重新登录！";

	/**
	 * 登陆用户SESSION检查。
	 * 
	 * @param request
	 * @return int ActionUtil.AUDIT_PASS : SESSION验证通过<br>
	 *         ActionUtil.AUDIT_NOSESSION : 用户SESSION已经不存在<br>
	 *         ActionUtil.AUDIT_NOSELECTED_HOC : 用户为小区管理处，登陆后没有选择要操作的业委会，或者业委会基金帐号不合法<br>
	 */
	public static int userAuthenticate(HttpServletRequest request) {

		// if(DEBUG) return AUDIT_PASS;
		String uri = request.getServletPath();
		// debug("uri=" + uri);
		if( uri.startsWith("/web/") ) {
			return AUDIT_PASS;
		}
		User user = (User)request.getSession().getAttribute(BaseAction.USER_SESSION);
		if( user == null ) {
			return AUDIT_NOSESSION;
		}
		else {
			return checkUnlock(user);
		}
	}

	/**
	 * 检查当前登录用户的SESSION与操作员表中记录的该用户ID是否相等。（如果用户被解锁过，则必然不相等）
	 * @param user
	 * @return
	 */
	private static int checkUnlock(User user) {

		return AUDIT_PASS;
	}

	/**
	 * 检查是否通过epass验证。<br>
	 * 需要进行检查的页面必须设置隐藏域：need_check_epass，并设置值为true。<br>
	 * 注意：本函数存在弊病，比如用户自己把页面中的need_check_epass隐藏域去掉，将绕过EPASS检查。<br>
	 * 解决办法是，把所有需要进行检查的.do，配置到一个文件中，系统启动时读取进来，这样将不再需要need_check_epass隐藏域
	 * 
	 * @param request
	 * @return String 如果为null，表示通过验证，否则返回的是错误提示信息。
	 */
	public static String checkEpass(HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

	/**
	 * 得到URI，例如：/jsp/a0101/getList.do
	 * 
	 * @param request
	 * @return
	 */
	public static String getURI(HttpServletRequest request) {

		// getAction(request);
		return request.getServletPath();
	}

	/**
	 * 确定当前SESSION的User对象实例
	 * 
	 * @param request
	 * @return
	 */
	public static User getUser(HttpServletRequest request) {

		User user = (User)request.getSession().getAttribute(BaseAction.USER_SESSION);
		if( user == null ) {
			throw new RuntimeException("您的会话已失效，请重新登录！");
		}
		else {
			return user;
		}
	}

	/**
	 * 取得一个resource的InputStream
	 * @param request
	 * @return
	 */
	public static InputStream getResourceIS(HttpServletRequest request,String path)
	{
		path="/WEB-INF/classes"+path;
		Debug.info(logger,"path======="+path);
		InputStream is=request.getSession().getServletContext().getResourceAsStream(path);
		if(is==null){
			throw new BusinessException("can not find Resource file :"+path);
		}
		return is;
	}	
}