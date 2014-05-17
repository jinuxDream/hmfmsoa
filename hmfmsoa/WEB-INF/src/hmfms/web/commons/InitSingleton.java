package hmfms.web.commons;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;

import fd.inner.RuntimeParameter;

/**
 * <p>��    ��: ���Ŀ��</p>
 * <p>��    ��: ��ʼ�����еĵ�����</p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2010-12-13 ����11:34:06</p>
 * @author ��Ʒ������
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
				fdCoreVersion = "12";//Ĭ��1.2
			if( !("11".equals(fdCoreVersion) || "12".equals(fdCoreVersion)) )//��֧��1.1��1.2�����汾
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
