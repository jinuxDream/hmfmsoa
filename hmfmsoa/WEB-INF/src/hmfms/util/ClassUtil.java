package hmfms.util;



/**
 * <p>�ļ�����ClassUtil.java
 * <p>�� ��: ����ά���ʽ����ϵͳ</p>
 * <p>�� ��: xxxxx</p>
 * <p>�� Ȩ: Copyright (c) 2010</p>
 * <p>�� ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2011-7-12</p>
 * @author  �Ŵ���
 * @version 1.0
 */
public class ClassUtil {
    /**
	    * ��̬װ��ָ�����Ƶ���
	    * ��������˳�򣬳��Լ����ࣺ
	    * ʹ�õ�ǰ�̵߳�getContextClassLoader()��װ������������
	    * ʹ��������� Class.forName()������
	    * ʹ�õ�ǰ������������������ ClassUtil.class.getClassLoader()
	    * ʹ�õ��ô˷�������ļ����������� callingClass.getClassLoader()
	    * @param className Ҫװ�ص�����
	    * @param callingClass ���ô˷�������
	    * @throws ClassNotFoundException ����޷����أ����׳����쳣��
	    */
    public static Class loadClass(String className, Class callingClass) throws ClassNotFoundException {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException ex) {
                try {
                    return ClassUtil.class.getClassLoader().loadClass(className);
                } catch (ClassNotFoundException exc) {
                    return callingClass.getClassLoader().loadClass(className);
                }
            }
        }
    }

}
