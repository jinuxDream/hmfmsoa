/**
 * 
 */
package mng_plat.biz.sysmng.init;

import java.util.Map;

import fd.commons.jdbc.Result;
/**
 * <p>��    ��: @project_name סլС����ҵ������ƽ̨</p>
 * <p>��    ��: ��ҳ����������ʾDTO</p>
 * <p>��    Ȩ: Copyright (c) 2011</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2012-7-28 ����01:59:23</p>
 * @author xiqh
 * @version 1.0
 * TaskTipDTO
 */
public class TaskTipDTO {
	private Result rsSectNeedCorrectMsg = null;//С����Ҫά����Ϣ��ʾ
	private Map<String, String> rsTradeInfoMsg = null;//��ҵ��˾�����Ϣ��ʾ
	private Map<String, String> rsSectSpecialCheckMsg = null;;//С��ר������Ϣ��ʾ
	private Result rsCspSpecialCheckMsg = null;;//��ҵר������Ϣ��ʾ
	private Map<String, String> rsSectFourthCheckMsg = null;;//С���Ĳ���Ϣ��ʾ
	private Map<String, String> rsCsphonest = null;;//������ʾ
	
	public Map<String, String> getRsTradeInfoMsg() {
	
		return rsTradeInfoMsg;
	}

	public Map<String, String> getRsCsphonest() {
		return rsCsphonest;
	}

	public void setRsCsphonest(Map<String, String> rsCsphonest) {
		this.rsCsphonest = rsCsphonest;
	}

	public void setRsTradeInfoMsg(Map<String, String> rsTradeInfoMsg) {
	
		this.rsTradeInfoMsg = rsTradeInfoMsg;
	}	
	public Map<String, String> getRsSectFourthCheckMsg() {
		return rsSectFourthCheckMsg;
	}

	public void setRsSectFourthCheckMsg(Map<String, String> rsSectFourthCheckMsg) {
		this.rsSectFourthCheckMsg = rsSectFourthCheckMsg;
	}

	public Result getRsSectNeedCorrectMsg() {
	
		return rsSectNeedCorrectMsg;
	}

	
	public void setRsSectNeedCorrectMsg(Result rsSectNeedCorrectMsg) {
	
		this.rsSectNeedCorrectMsg = rsSectNeedCorrectMsg;
	}
	public Map<String, String> getRsSectSpecialCheckMsg() {
		return rsSectSpecialCheckMsg;
	}



	public void setRsSectSpecialCheckMsg(Map<String, String> rsSectSpecialCheckMsg) {
		this.rsSectSpecialCheckMsg = rsSectSpecialCheckMsg;
	}



	public Result getRsCspSpecialCheckMsg() {
	
		return rsCspSpecialCheckMsg;
	}
	
	public void setRsCspSpecialCheckMsg(Result rsCspSpecialCheckMsg) {
	
		this.rsCspSpecialCheckMsg = rsCspSpecialCheckMsg;
	}
	
}
