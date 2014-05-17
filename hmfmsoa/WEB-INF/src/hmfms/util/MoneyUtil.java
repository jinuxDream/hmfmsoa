package hmfms.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>��    ��: ���Ŀ��</p>
 * <p>��    ��: �������ַ���</p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2010-12-15 ����04:21:23</p>
 * @author ��Ʒ������
 * @version 2.0
 * MoneyUtil
 */
public class MoneyUtil extends AbstractUtil {

	private static final Log logger = LogFactory.getLog(MoneyUtil.class);

	//����:���ַ���,��Ҫ�������ַ���,��i�ε��ñ�����,ǰһ�ε���ʱ�Ƿ�ɹ�
	private static String toChinese(String sStr, String sFour, int i, boolean bPre) {

		String sStruct = "";//�ش����
		for(int j = 0; j < 4; j++) {
			if( sFour.charAt(j) != '0' )//����ÿһλ��ֵʱ����ǰ���Ƿ���Ҫ�ӡ��㡱 
			{
				if( j == 0 )//����ǧλ
				{
					if( !bPre ) {
						sStruct = sStruct + '0';
					}
					sStruct = sStruct + sFour.charAt(j);
				}
				else//����١�ʮ����λ
				{
					if( sFour.charAt(j - 1) == '0' ) {
						sStruct = sStruct + '0';
					}
					sStruct = sStruct + sFour.charAt(j);
				}

				switch(j)//���������ǡ��͡��֡�
				{
				case 0: {
					if( i == 3 ) {
						sStruct = sStruct + '��';
					}
					else {
						sStruct = sStruct + 'Ǫ';
					}
					break;
				}
				case 1: {
					if( i == 3 ) {
						sStruct = sStruct + '��';
					}
					else {
						sStruct = sStruct + '��';
					}
					break;
				}
				case 2: {
					sStruct = sStruct + 'ʰ';
					break;
				}
				case 3: {
					if( !sStruct.equals("") ) {
						switch(i)//����λ
						{
						case 0: {
							sStruct = sStruct + "��";
							break;
						}
						case 1: {
							sStruct = sStruct + "��";
							break;
						}
						case 2: {
							sStruct = sStruct + "Ԫ";
							break;
						}
						}
					}
				}
				}
			}
			else//����λΪ��ʱ������λ
			{
				if( !sStruct.equals("") && j == 3 ) {
					switch(i) {
					case 0: {
						sStruct = sStruct + "��";
						break;
					}
					case 1: {
						sStruct = sStruct + "��";
						break;
					}
					}
				}
				if( i == 2 && j == 3 && (!sStr.equals("") || !sStruct.equals("")) )//�Ƿ�ӡ�Բ����
				{
					sStruct = sStruct + "Ԫ";
				}
			}
		}
		return sStruct;
	}

	/**
	 * ת��Ϊ��д���ֵĽ��ֵ
	 * @param digit �������λС��,���������֧�ֽ��999999999999.99
	 * @return String ת��Ϊ��д���ֵĽ��ֵ
	 */
	public static String toRMB(String digit) {

		return toRMB(Double.parseDouble(digit));
	}

	/**
	 * ת��Ϊ��д���ֵĽ��ֵ
	 * @param digit �������λС��,���������֧�ֽ��999999999999.99
	 * @return String 
	 */
	public static String toRMB(double digit) {

		//�����ݸ�ʽ��Ϊ��λС��
		DecimalFormat df = new DecimalFormat("#.0000");
		StringBuffer sbDigit = new StringBuffer(df.format(digit));
		sbDigit.replace(sbDigit.length() - 2, sbDigit.length(), "00");
		String sDigit = "";//��doubleת��Ϊstring
		sDigit = sbDigit.toString();
		sDigit = sDigit.substring(0, sDigit.length() - 5) + sDigit.substring(sDigit.length() - 4);//ȥ��С����

		//���ַ�������16λ�����ڷ���
		//sDigit = sDigit + "00";
		if( sDigit.length() > 16 ) {
			return "�������";
		}

		if( sDigit.length() < 16 ) {
			int iLength = 16 - sDigit.length();
			for(int i = 0; i < iLength; i++) {
				sDigit = "0" + sDigit;
			}
		}
		if( sDigit.equals("0000000000000000") ) {
			return "��Ԫ��";
		}
		String sChinese = sDigit;
		String sFour = "";//ÿ��λ����һ��string
		boolean bPreStr = true;//ǰһ��string�Ƿ���ɹ�
		sDigit = "";//���ַ���
		//���ַ�����Ϊ���飬ÿһ�鵥��������������󴮽�
		for(int i = 0; i < 4; i++) {
			sFour = toChinese(sDigit, sChinese.substring(i * 4, i * 4 + 4), i, bPreStr);
			//Debug.info(logger,sFour);
			if( sFour.length() == 0 || sFour.length() == 1 ) {
				bPreStr = false;
			}
			else if( sFour.charAt(sFour.length() - 2) < '0' || sFour.charAt(sFour.length() - 2) > '9' ) {
				bPreStr = false;
			}
			else {
				bPreStr = true;
			}
			sDigit = sDigit + sFour;
		}
		//ȥ���ַ�����ǰ��ġ�0��
		for(;;) {
			if( sDigit.charAt(0) == '0' ) {
				sDigit = sDigit.substring(1);
			}
			else {
				break;
			}
		}

		sChinese = "";
		for(int i = 0; i < sDigit.length(); i++) {
			if( sDigit.charAt(i) >= '0' && sDigit.charAt(i) <= '9' ) {
				switch(sDigit.charAt(i)) {
				case '1': {
					sChinese = sChinese + "Ҽ";
					break;
				}
				case '2': {
					sChinese = sChinese + "��";
					break;
				}
				case '3': {
					sChinese = sChinese + "��";
					break;
				}
				case '4': {
					sChinese = sChinese + "��";
					break;
				}
				case '5': {
					sChinese = sChinese + "��";
					break;
				}
				case '6': {
					sChinese = sChinese + "½";
					break;
				}
				case '7': {
					sChinese = sChinese + "��";
					break;
				}
				case '8': {
					sChinese = sChinese + "��";
					break;
				}
				case '9': {
					sChinese = sChinese + "��";
					break;
				}
				case '0': {
					sChinese = sChinese + "��";
					break;
				}
				}
			}
			else {
				sChinese = sChinese + sDigit.charAt(i);
			}
		}

		if( !sDigit.endsWith("��") )//��"��"����"��"
		{
			sChinese = sChinese + "��";
		}

		return sChinese;
	}

	static int indexOfPoint; //С�����λ��
	static int lengthOfInt; //�������ֵĳ���
	static int numOfCommer; //��������
	static int indexOfFirstCommer; //�׸����ŵ�λ��
	static String intPart = ""; //��������
	static String decimalPart = ""; //С������

	/**
	 * ��������ַ������ת����ǧ��λ�ַ������
	 * @param amt ����ַ���
	 * @return
	 */
	public static String toAmtString(String amt) {

		String ret = "";
		BigDecimal d = ObjectUtil.getZeroBigDecimal();
		boolean flag = false;
		try {
			d = new BigDecimal(amt);
			if( d.compareTo(ObjectUtil.getZeroBigDecimal()) < 0 ) {
				d = d.abs();
				flag = true;
			}
		}
		catch(java.lang.NumberFormatException ex) {

		}
		dividedString(d.toString());
		ret = convertIntPart(intPart) + convertDecimalPart(decimalPart, 2);
		if( flag )
			ret = "-" + ret;
		return ret;
	}

	/**
	 * ��������ַ������ת������2λС���ַ������
	 * @param amt ����ַ���
	 * @return
	 */
	public static String save2DecimalPart(String amt) {

		dividedString(amt);
		return intPart + convertDecimalPart(decimalPart, 2);
	}

	/**
	 * ��������ַ������ת������nλС���ַ������
	 * @param amt ����ַ���
	 * @param scale С��λ��
	 * @return
	 */
	public static String saveNDecimalPart(String amt, int scale) {

		dividedString(amt);
		return intPart + convertDecimalPart(decimalPart, scale);
	}

	/**
	 * �ֽ�������С��
	 * @param amt
	 */
	private static void dividedString(String amt) {

		try {
			Double.parseDouble(amt);
		}
		catch(Exception e) {
			Debug.info(logger, "����ת������ֵ�͵��ַ���");
			return;
		}

		if( amt.indexOf(".") == -1 ) {
			indexOfPoint = amt.length();
			lengthOfInt = amt.length();
			intPart = amt;
			decimalPart = "";
			/*
			 Debug.info(logger,"");
			 Debug.info(logger,"��С����");
			 Debug.info(logger,"С����λ�ã�"+indexOfPoint);
			 Debug.info(logger,"�������ȣ�"+lengthOfInt);
			 */
		}
		else {
			indexOfPoint = amt.indexOf(".");
			lengthOfInt = amt.substring(0, indexOfPoint).length();
			intPart = amt.substring(0, indexOfPoint);
			decimalPart = amt.substring(indexOfPoint + 1, amt.length());
			/*
			 Debug.info(logger,"");
			 Debug.info(logger,"��С����");
			 Debug.info(logger,"С����λ�ã�"+indexOfPoint);
			 Debug.info(logger,"�������ȣ�"+lengthOfInt);
			 Debug.info(logger,"С�����֣�"+decimalPart);
			 */
		}
	}

	/**
	 * ת���������֣���λ��һ����
	 * @param intPart
	 * @return
	 */
	private static String convertIntPart(String intPart) {

		int pointer = 0; //ָ��
		String result = "";
		if( lengthOfInt % 3 == 0 ) {
			numOfCommer = lengthOfInt / 3 - 1;
			indexOfFirstCommer = 3;
			/*
			 Debug.info(logger,"");
			 Debug.info(logger,"������0");
			 Debug.info(logger,"����������"+numOfCommer);
			 Debug.info(logger,"�׸����ŵ�λ�ã�"+indexOfFirstCommer);
			 */
		}
		else {
			numOfCommer = lengthOfInt / 3;
			indexOfFirstCommer = lengthOfInt % 3;
			/*
			 Debug.info(logger,"");
			 Debug.info(logger,"������"+lengthOfInt%3);
			 Debug.info(logger,"����������"+numOfCommer);
			 Debug.info(logger,"�׸����ŵ�λ�ã�"+indexOfFirstCommer);
			 */
		}

		for(int i = 0; i <= numOfCommer;) {
			if( i == 0 ) {
				result = intPart.substring(pointer, indexOfFirstCommer);
				pointer += indexOfFirstCommer;
			}
			else if( i != numOfCommer ) {
				result = result + "," + intPart.substring(pointer, pointer + 3);
				pointer += 3;
			}
			else {
				result = result + "," + intPart.substring(pointer, pointer + 3);
			}
			i++;
		}

		return result;
	}

	/**
	 * ת��С�����֣�ֻȡnλС��
	 * @param decimalPart
	 * @param decimalNum
	 * @return
	 */
	private static String convertDecimalPart(String decimalPart, int decimalNum) {

		//����nλС��
		if( decimalPart.length() > decimalNum ) {
			//Debug.info(logger,"����"+decimalNum+"λС��");
			decimalPart = decimalPart.substring(0, decimalNum);
			return "." + decimalPart;
		}
		//��С��

		else if( decimalPart.equals("") || decimalPart == null ) {
			//Debug.info(logger,"��С��");
			for(int i = 0; i < decimalNum; i++)
				decimalPart = decimalPart + "0";
			return "." + decimalPart;
		}

		//С��nλС��
		else if( decimalPart.length() < decimalNum ) {
			//Debug.info(logger,"С��"+decimalNum+"λС��");
			for(int i = 0; i < (decimalNum - decimalPart.length()); i++)
				decimalPart = decimalPart + "0";
			return "." + decimalPart;
		}
		//else Debug.info(logger,"����"+decimalNum+"λС��");
		return "." + decimalPart;
	}

	public static void main(String args[]) {

		String a = toAmtString("12");
		Debug.info(logger, "result=" + a);
		a = toAmtString("12.");
		Debug.info(logger, "result=" + a);
		a = toAmtString("12.0");
		Debug.info(logger, "result=" + a);
		a = toAmtString("12.1");
		Debug.info(logger, "result=" + a);
		a = toAmtString("12.12");
		Debug.info(logger, "result=" + a);
		a = toAmtString("12.123");
		Debug.info(logger, "result=" + a);

		Debug.info(logger, "");
		Debug.info(logger, save2DecimalPart("1"));
		Debug.info(logger, save2DecimalPart("1."));
		Debug.info(logger, save2DecimalPart("1.0"));
		Debug.info(logger, save2DecimalPart("1.1"));
		Debug.info(logger, save2DecimalPart("1.12"));
		Debug.info(logger, save2DecimalPart("1.123"));
	}
}
