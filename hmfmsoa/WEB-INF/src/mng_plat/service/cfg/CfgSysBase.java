package mng_plat.service.cfg;

/**
 * <p>��    ��: ��ҵ���ƽ̨�����ڣ�</p>
 * <p>��    ��: ϵͳ��������������</p>
 * <p>��    Ȩ: Copyright (c) 2013</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2013-5-16 ����04:15:05</p>
 * @author xchao
 * @version 1.1
 */
public class CfgSysBase {

	/**
	 * ��ȡCfgSysBaseʵ��
	 */
	public static CfgSysBase getInstance() {

		return new CfgSysBase();
	}

	/**
	 * ϵͳ�� �Ƿ������ҵ��������
	 * @return
	 */
	public static boolean isCsp() {

		return true;
	}

	/**
	 * ϵͳ�� �Ƿ�����Ȼ��
	 * @return
	 */
	public static boolean isBuild() {

		return false;
	}

	/**
	 * ϵͳ�� �Ƿ������Ƶ�Ԫ
	 * @return
	 */
	public static boolean isUnit() {

		return true;
	}
	
	/**
	 * ϵͳ�� �Ƿ�������ɾ��
	 * @return
	 */
}
