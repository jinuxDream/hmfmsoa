package hmfms.util;



/**
 * <p>文件名：ClassUtil.java
 * <p>标 题: 房屋维修资金管理系统</p>
 * <p>描 述: xxxxx</p>
 * <p>版 权: Copyright (c) 2010</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2011-7-12</p>
 * @author  张传生
 * @version 1.0
 */
public class ClassUtil {
    /**
	    * 动态装载指定名称的类
	    * 按照如下顺序，尝试加载类：
	    * 使用当前线程的getContextClassLoader()类装载器，加载类
	    * 使用最基本的 Class.forName()加载类
	    * 使用当前类的类加载器，加载类 ClassUtil.class.getClassLoader()
	    * 使用调用此方法的类的加载器，加载 callingClass.getClassLoader()
	    * @param className 要装载的类名
	    * @param callingClass 调用此方法的类
	    * @throws ClassNotFoundException 如果无法加载，将抛出此异常。
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
