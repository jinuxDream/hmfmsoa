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
 * <p>��    ��: ���Ŀ��</p>
 * <p>��    ��: ʹ�ÿ������ת�����ƣ��豻����action�̳еĳ���</p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2010-12-13 ����11:16:25</p>
 * @author ��Ʒ������
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
	public static final String BLACKBALL = "blackball"; //���⽻��
	public static final String BACKPAGE = "backpage"; //���⽻��ҳ�ķ��ذ�ť��Ҫ��.do
	public static final String ALTERNATIVERESULTSET = "alternativeresultset"; //���⽻�׽�����Ĳ�����
	public static final String MESSAGE = "message.redo";
	public static final String OMUTEX = "OMutex"; //�������⽻���б�
	public static final String ReturnFlag = "flag"; //������������;���ƽ��׵ķ��ؽ����ʶ
	public static final String FLAG_TRUE = "TRUE"; //������������;���ƽ��׵ķ���Ϊ��
	public static final String FLAG_FALSE = "FALSE"; //������������;���ƽ��׵ķ���Ϊ��
	public static final String FLAG_OMUTEX = "OMutex"; //������������;���ƽ��׵ķ���Ϊ���ڻ��⽻��
	public static final String BACK_PAGE = "back_page"; //����,����ҳ���ص�ǰһҳ
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	public static final String ERROR = "error";

	/**
	 * ����ҵ����֤���ﲻ��������С���û��Ƿ�ѡ���ҵ�������hmfms��BaseAction���Ѿ�������֤���ܣ���<br>
	 * ��Ϊ�����ظ��Ĺ��������ᵼ���޸ĵ�ʱ�����߶��ġ�<BR>
	 * ʹ����������ACTION������С���û���Ӧ�ô�һ��forward������<br>
	 * ����ֻ����Ҫ��SESSION��֤
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
				throw new Exception("ϵͳ�쳣��");
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
			/*��¼������־*/
			User user = getUser(request);
			if( !"init".equals(path) && !"userindex/datacheck".equals(path) && path.toLowerCase().indexOf("listener") == -1 ) {
				OperLog.saveOperLog(user, action, request);
			}
		}
		else {
			try {
				User user = getUser(request);
				OperLog.saveOperLog(user, "99_�ǳ�", request);
			}
			catch(Exception e) {
				Debug.exception(logger, "��¼������־ʧ��", e);
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