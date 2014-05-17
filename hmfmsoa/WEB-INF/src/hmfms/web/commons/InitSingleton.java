package hmfms.web.commons;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;

import fd.inner.RuntimeParameter;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 初始化所有的单例类</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:34:06</p>
 * @author 产品开发部
 * @version 2.0
 * InitSingleton
 */
public class InitSingleton extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		try {
			String debug = config.getInitParameter("debug");
			if( debug == null || debug.trim().equals("") )
				debug = "0";
		/*	if( Integer.parseInt(debug) > 0 ) {
				CounterConn counter = CounterConn.getInstance();
				CounterTrans counter1 = CounterTrans.getInstance();
			}
*/
			String fdCoreVersion = config.getInitParameter("fd-core-version");
			if( fdCoreVersion == null || fdCoreVersion.trim().equals("") )
				fdCoreVersion = "12";//默认1.2
			if( !("11".equals(fdCoreVersion) || "12".equals(fdCoreVersion)) )//仅支持1.1或1.2两个版本
				throw new UnavailableException("unsupport vesion [" + fdCoreVersion + "]!");

			RuntimeParameter runtimeParameter = RuntimeParameter.getInstance();
			runtimeParameter.setCoreVersion(fdCoreVersion);

		}
		catch(Exception e) {
			e.printStackTrace(System.out);
			throw new ServletException(e.getMessage());
		}

	}

}
