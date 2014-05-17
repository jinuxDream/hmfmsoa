package hmfms.web.commons;

import hmfms.util.Debug;
import hmfms.util.ObjectUtil;
import fd.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>标 题: 房屋维修资金门户网站</p>
 * <p>描 述: 验证码检测工具</p>
 * <p>版 权: Copyright (c) 2009</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-1-7 </p>
 * @author huangmh
 * @version 1.0 VerificationCodeUtil.java
 */
public class VerificationCodeUtil {

	private static final Log logger = LogFactory.getLog(VerificationCodeUtil.class);
	public static final String RANDOM_CODE = "randomCode";

	public static void checkRandomCode(String random_code, HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		String randomCode = session.getAttribute(VerificationCodeUtil.RANDOM_CODE).toString().replaceAll(" ", "").trim();
		/* 验证码 */
		if( ObjectUtil.isEmpty(random_code) ) {
			throw new BusinessException("请输入验证码！");
		}
		if( ObjectUtil.isEmpty(randomCode) ) {
			throw new BusinessException("验证码已失效！");
		}
		if( !random_code.equals(randomCode.trim()) ) {
			throw new BusinessException("验证码错误，请重新输入！");
		}
		Debug.info(logger, "验证码=" + randomCode + "验证通过");
	}
}
