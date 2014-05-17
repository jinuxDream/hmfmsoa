package hmfms.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 计时器实用类</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 下午12:36:23</p>
 * @author 产品开发部
 * @version 2.0
 * Watch
 */
public class Watch extends AbstractUtil {

	private static final Log logger = LogFactory.getLog(Watch.class);
	private long startTime;
	private long endTime;
	private long tempTime;
	public static final int SHOW_MILLIS = 0;
	public static final int SHOW_SECOND = 1;
	public static final int SHOW_MINITOR = 2;

	private int fenmu = 1000;
	private String ss = "秒";

	/**
	 * 获得当前时间到开始时间(最近一次调用start())的间隔时间
	 * @return 单位毫秒
	 */
	public long getElapsedTimeMillis() {

		long curTime = System.currentTimeMillis();
		long e = curTime - startTime;
		startTime = curTime;
		return e;
	}

	/**
	 * 开始计时
	 */
	public void start() {

		startTime = System.currentTimeMillis();
		tempTime = startTime;
	}

	/**
	 * 开始计时
	 * @param show 设置计时单位
	 */
	public void start(int show) {

		if( show == SHOW_MILLIS ) {
			fenmu = 1;
			ss = "毫秒";
		}
		else if( show == SHOW_SECOND ) {
			fenmu = 1000;
			ss = "秒";
		}
		else if( show == SHOW_MINITOR ) {
			fenmu = 6000;
			ss = "分";
		}
		else {
			fenmu = 1000;
			ss = "秒";
		}
		startTime = System.currentTimeMillis();
		tempTime = startTime;
	}

	/**
	 * 停止计时
	 */
	public void stop() {

		endTime = System.currentTimeMillis();
	}

	/**
	 * 获得结束时间(最近一次调用stop())到开始时间(最近一次调用start())的间隔时间
	 * @return 单位毫秒
	 */
	public long getTimeElapsed() {

		return (endTime - startTime);
	}

	/**
	 * 根据时间显示单位设置打印上一次调用本函数后的间隔时间
	 * @return
	 */
	public String elapsed() {

		long curTime = System.currentTimeMillis();
		String msg = "--> Elapsed Time is " + (curTime - tempTime) / fenmu + ss;
		tempTime = curTime;
		Debug.info(logger, msg);
		return msg;
	}

	/**
	 * 根据时间显示单位设置打印上一次调用本函数后的间隔时间，并在之前打印tip
	 * @param tip 要显示的信息
	 * @return
	 */
	public String elapsed(String tip) {

		long curTime = System.currentTimeMillis();
		String msg = "--> " + tip + " Elapsed Time is " + (curTime - tempTime) / fenmu + ss;
		tempTime = curTime;
		Debug.info(logger, msg);
		return msg;
	}

	/**
	 * 根据时间显示单位设置打印计时(最近一次调用stop()与stop())间隔时间
	 * @return
	 */
	public void print() {

		Debug.info(logger, "--> Total Time is " + (endTime - startTime) / fenmu + ss);
	}

	/**
	 * 根据时间显示单位设置打印计时(最近一次调用stop()与stop())间隔时间，并在之前打印tip
	 * @param tip 要显示的信息
	 */
	public void print(String tip) {

		Debug.info(logger, "--> " + tip + " Total Time is " + (endTime - startTime) / fenmu + ss);
	}
}
