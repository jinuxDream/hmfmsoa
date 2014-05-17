package hmfms.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>��    ��: ���Ŀ��</p>
 * <p>��    ��: �ַ�������ʵ����</p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2010-12-13 ����12:32:42</p>
 * @author ��Ʒ������
 * @version 2.0
 * StringUtil
 */
public final class StringUtil extends AbstractUtil {

	private static final Log logger = LogFactory.getLog(StringUtil.class);

	/**
	 * �ж�String�Ƿ�Ϊ����
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ) {
			return false;
		}
		return true;
	}

	/** 
	 * �ж�һ���ַ����ǲ�������(����������1-20) 
	 * @param str 
	 * @return 
	 */
	protected static boolean isNumeric2(String str) {

		char[] charArr = str.toCharArray();
		int index = str.indexOf("-"); // ���ŵ����� 
		for(int i = 0; i < charArr.length; i++) {
			if( i == index ) {
				continue;
			}
			else {
				if( charArr[i] >= '0' && charArr[i] <= '9' ) {
					continue;
				}
				else {
					return false;
				}
			}
		}
		return true;
	}

	public static long genEventCode() {

		return System.currentTimeMillis();
	}

	/**
	 * �Ѳ���word�ĵ�һ����ĸ��д
	 * @param word
	 * @return 
	 */
	public static final String upperFirstWord(String word) {

		String firstWord = word.substring(0, 1).toUpperCase();
		return firstWord + word.substring(1);
	}

	/**
	 * �Ѳ���word�ĵ�һ����ĸСд
	 * @param word
	 * @return 
	 */
	public static final String lowerFirstWord(String word) {

		String firstWord = word.substring(0, 1).toLowerCase();
		return firstWord + word.substring(1);
	}

	/**
	 * ���ֽ�����ת��Ϊָ��������ַ���
	 * @param strbyte
	 * @param destEncoding Ŀ�����
	 * @return
	 */
	public static String encodeByte(byte[] strbyte, String destEncoding) {

		String ret = null;

		try {
			ret = new String(strbyte, destEncoding);
		}
		catch(Exception e) {
			ret = "byte to " + destEncoding + " error. str=[" + strbyte + "]";
			Debug.exception(logger, e);
		}
		return (ret);
	}

	/**
	 * ���ֽ�����ת��Ϊָ��������ַ���
	 * @param str
	 * @param srcEncoding ԭ�ַ�������
	 * @param destEncoding Ŀ�����
	 * @return
	 */
	public static String encodeStr(String str, String srcEncoding, String destEncoding) {

		String ret = null;

		try {
			ret = new String(str.getBytes(srcEncoding), destEncoding);
		}
		catch(Exception e) {
			ret = srcEncoding + " to " + destEncoding + " error. str=" + str;
			Debug.exception(logger, e);
		}
		return (ret);
	}

	/**
	 * ISO-8859-1תUTF-8
	 * @param str
	 * @return
	 */
	public static String ISO2UTF8(String str) {

		String ret = null;

		try {
			ret = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		}
		catch(Exception e) {
			ret = "ISO2UTF8 error. str=" + str;
			Debug.exception(logger, e);
		}
		return (ret);
	}

	/**
	 * UTF-8תISO-8859-1
	 * @param str
	 * @return
	 */
	public static String UTF82ISO(String str) {

		String ret = null;

		try {
			ret = new String(str.getBytes("UTF-8"), "ISO-8859-1");
		}
		catch(Exception e) {
			ret = "UTF82ISO error. str=" + str;
			Debug.exception(logger, e);
		}
		return (ret);
	}

	/**
	 * ISO-8859-1תGBK
	 * @param str
	 * @return
	 */
	public static String ISO2GBK(String str) {

		String ret = null;

		try {
			ret = new String(str.getBytes("ISO-8859-1"), "GBK");
		}
		catch(Exception e) {
			ret = "ISO2GBK error. str=" + str;
			Debug.exception(logger, e);
		}
		return (ret);
	}

	/**
	 * GBKתISO-8859-1
	 * @param str
	 * @return
	 */
	public static String GBK2ISO(String str) {

		String ret = null;

		try {
			ret = new String(str.getBytes("GBK"), "ISO-8859-1");
		}
		catch(Exception e) {
			ret = "GBK2ISO error. str=" + str;
			Debug.exception(logger, e);
		}
		return (ret);
	}

	/**
	 * ��Դ��sOld��ΪsPartten���ַ�����sReplace�滻��
	 * @param sOld Դ�ַ���
	 * @param sPartten Ҫ�滻���ַ���
	 * @param sReplace �滻�ɵ��ַ���
	 * @return
	 */
	public static String replace(String sOld, String sPartten, String sReplace) {

		if( sOld == null ) {
			return null;
		}
		if( sPartten == null ) {
			return sOld;
		}
		if( sReplace == null ) {
			sReplace = "";
		}
		StringBuffer sBuffer = new StringBuffer();
		int isOldLen = sOld.length();
		int isParttenLen = sPartten.length();
		int iIndex = -1;
		int iHead = 0;
		while( (iIndex = sOld.indexOf(sPartten, iHead)) > -1 ) {
			sBuffer.append(sOld.substring(iHead, iIndex)).append(sReplace);
			iHead = iIndex + isParttenLen;
		}
		sBuffer.append(sOld.substring(iHead, isOldLen));
		return sBuffer.toString();
	}

	/**
	 * ������ֵ��ʽ��������λС������price=4535.234095
	 * ��˺�������Ϊ4535.23
	 * @param price
	 * @return
	 */
	public static String format2Decimal(double price) {

		java.text.NumberFormat nf = java.text.NumberFormat.getInstance(java.util.Locale.CHINESE);
		java.text.DecimalFormat df = (java.text.DecimalFormat)nf;
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		String pattern = "#0.00";
		df.applyPattern(pattern);
		df.setDecimalSeparatorAlwaysShown(true);
		return df.format(price);
	}

	/**
	 * ������ֵ��ʽ��������λС������price=4535.234095
	 * ��˺�������Ϊ4535.23
	 * @param price
	 * @return
	 */
	public static String format2Decimal(String price) {

		double d = 0.0;
		if( ObjectUtil.isEmpty(price) ) {
			d = 0.0;
		}
		else {
			try {
				d = Double.parseDouble(price);
			}
			catch(java.lang.NumberFormatException ex) {
				d = 0.0;
			}
			catch(java.lang.Exception ex) {
				d = 0.0;
			}
		}
		return format2Decimal(d);
	}

	/**
	 * �����ת����ǧ��λ��ʽ
	 * @param price
	 * @return
	 */
	public static String format2Amt(String price) {

		double d = 0.0;
		if( ObjectUtil.isEmpty(price) ) {
			d = 0.0;
		}
		else {
			try {
				d = Double.parseDouble(price);
			}
			catch(java.lang.NumberFormatException ex) {
				d = 0.0;
			}
			catch(java.lang.Exception ex) {
				d = 0.0;
			}
		}
		return MoneyUtil.toAmtString(format2Decimal(d));
	}

	/**
	 * �����ת����ǧ��λ���������ҷ�����"Ԫ"
	 * @param price
	 * @return
	 */
	public static String format2AmtWithYuan(String price) {

		return "��" + format2Amt(price) + "Ԫ";
	}

	/**
	 * ���ָ����Ŷ����ַ���������
	 * @param strlist           ���зָ����ŵ��ַ���
	 * @param ken               �ָ�����
	 * @return                  �б�
	 */
	public static final List parseStringToArrayList(String strlist, String ken) {

		StringTokenizer st = new StringTokenizer(strlist, ken);
		if( strlist == null || strlist.equals("") || st.countTokens() <= 0 ) {
			return new ArrayList();
		}
		int size = st.countTokens();
		List strv = new ArrayList();
		for(int i = 0; i < size; i++) {
			String nextstr = st.nextToken();
			if( !nextstr.equals("") ) {
				strv.add(nextstr);
			}
		}
		return strv;
	}

	/**
	 * ��Դ��str��delimΪ�ָ���ţ��ֳ�һ��String���鷵�أ���
	 * str="aaa,bbb,ccc" , delim=","
	 * ��˺�������Ϊ
	 * String[0]="aaa"
	 * String[1]="bbb"
	 * String[2]="ccc"
	 * @param str �������ַ���
	 * @param delim �ָ���
	 * @return
	 */
	public static String[] split(String str, String delim) {

		if( ObjectUtil.isEmpty(delim) ) {
			String[] strReturn = new String[1];
			strReturn[0] = str;
		}
		StringTokenizer st = new StringTokenizer(str, delim);
		int size = st.countTokens();
		if( size < 0 ) {
			return null;
		}
		String[] strReturn = new String[size];
		int i = 0;
		while( st.hasMoreTokens() ) {
			strReturn[i++] = st.nextToken();
		}
		return strReturn;
	}

	/**
	 * ��Դ��str��delimΪ�ָ���ţ��ֳ�һ��String���鷵�أ�<br>
	 * �� str="aaa,,bbb,ccc" , delim=","<br>
	 * ��˺�������Ϊ String[0]="aaa"<br>
	 * String[1]="" <br>
	 * String[2]="bbb"<br>
	 * String[3]="ccc"
	 * @param str �������ַ���
	 * @param delim �ָ��
	 * @return ���������ַ�Ϊ�գ���������string[0]=""
	 */
	public static String[] splitInNull(String str, String delim) {

		if( ObjectUtil.isEmpty(delim) ) {
			String[] strReturn = new String[1];
			strReturn[0] = str;
			return strReturn;
		}
		if( ObjectUtil.isEmpty(str) ) {
			String[] strReturn = new String[1];
			strReturn[0] = str;
			return strReturn;
		}

		StringTokenizer st = new StringTokenizer(str, delim, true);
		int i = st.countTokens();

		StringBuffer tempString = new StringBuffer();
		for(int j = 0; j < i; j++) {
			tempString.append(" " + st.nextToken() + " ");
		}

		st = new StringTokenizer(tempString.toString(), delim);
		Debug.info(logger, tempString.toString());
		i = st.countTokens();

		String[] tempArray = new String[i];
		for(int j = 0; j < i; j++) {
			tempArray[j] = st.nextToken().trim();
		}
		return tempArray;
	}

	/** 
	 * ��������ַ�������ת�����ַ�����ת��֮��ĸ�ʽ��'xxx','xxx',...,'xxx'
	 * @param stringArray
	 * @return
	 */
	public static String Array2String(String stringArray[]) {

		try {
			String StringResult = "";
			if( ObjectUtil.isEmpty(stringArray) ) {
				return StringResult;
			}
			int size = stringArray.length;
			for(int i = 0; i < size; i++) {
				if( ObjectUtil.isEmpty((String)stringArray[i]) ) {
					continue;
				}
				StringResult += "'" + (String)stringArray[i] + "',";
			}
			StringResult = StringResult.substring(0, StringResult.length() - 1);
			return StringResult;
		}
		catch(Exception ex) {
			Debug.exception(logger, ex);
			return "";
		}
	}

	/** 
	 * ��������ַ�������ת����ָ���ָ������ַ���
	 * ��delimΪ�ָ����� Array2String(array[],"-")���򷵻ش���'-'�ָ���
	 * @param stringArray
	 * @param delim �ָ��
	 * @return
	 */
	public static String Array2String(String stringArray[], String delim) {

		try {
			String StringResult = "";
			if( ObjectUtil.isEmpty(stringArray) ) {
				return StringResult;
			}
			int size = stringArray.length;
			if( size == 1 ) {
				return stringArray[0];
			}
			for(int i = 0; i < size - 1; i++) {
				if( ObjectUtil.isEmpty((String)stringArray[i]) ) {
					continue;
				}
				StringResult += (String)stringArray[i] + delim;
			}
			StringResult += (String)stringArray[size - 1];
			return StringResult;
		}
		catch(Exception ex) {
			Debug.exception(logger, ex);
			return "";
		}
	}

	/**
	 * ���ַ�����ʽ���� HTML �������
	 * @param string Ҫ��ʽ�����ַ���
	 * @return ��ʽ������ַ���
	 */
	public static String toHtml(String string) {

		if( string == null ) {
			return "";
		}
		string = string.replaceAll("&", "&amp;");
		string = string.replaceAll("\"", "&quot;");
		string = string.replaceAll("<", "&lt;");
		string = string.replaceAll(">", "&gt;");
		//string = string.replaceAll("\r\n", "\n");
		string = string.replaceAll("\n", "<br>\n");
		string = string.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		string = string.replaceAll(" ", "&nbsp;");
		return string;
	}

	/**
	 * ��sql�ڵ�'ת����''
	 * @param sql
	 * @return
	 */
	public static String toSql(String sql) {

		if( sql == null ) {
			return "";
		}
		sql = sql.replaceAll("'", "''");
		return sql;
	}

	/**
	 * ��sql�ڵ�''ת����'
	 * @param sql
	 * @return
	 */
	public static String fromSql(String sql) {

		if( sql == null ) {
			return "";
		}
		sql = sql.replaceAll("''", "'");
		return sql;
	}

	/**
	 * �õ�һ����toString��ֵ�������null�򷵻�""
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj) {

		if( null == obj || ObjectUtil.isEmpty(obj.toString()) ) {
			return "";
		}
		else {
			return obj.toString();
		}
	}

	/**
	 * �õ�һ����toString��ֵ�������null�򷵻�Ĭ��ֵ�����Ĭ��ֵΪnull�򷵻�""
	 * @param obj
	 * @param defaultObj Ĭ��ֵ
	 * @return
	 */
	public static String getString(Object obj, Object defaultObj) {

		if( null == obj || ObjectUtil.isEmpty(obj.toString()) ) {
			if( null == defaultObj )
				return "";
			else
				return defaultObj.toString();
		}
		else {
			return obj.toString();
		}
	}

	/**
	 * ���ַ�����λ��ʼ��ȡָ���ַ�����ָ�����ȵ��Ӵ������������������ɵ��ַ������ȱ仯����
	 * @param sourceString Դ�ַ���
	 * @param maxLength ��ȡ��󳤶�
	 * @return ���к�Ľ�������Դ�ַ���Ϊnull�����ؿմ�
	 */
	public static String subString(String sourceString, int maxLength) {

		String innerSourceString = sourceString;
		if( null == sourceString ){//���Ϊnull�����ؿմ�
			
			innerSourceString = "";
		}
		String endString = "";
		int trueLength = innerSourceString.length();
		if( trueLength > maxLength ){//ʵ�ʳ��ȴ�����Ҫ�ĳ���
			endString = innerSourceString.substring(0, maxLength);
		}
		else {
			endString = innerSourceString;
		}
		return endString;
	}
	
	/**
	 * ���ַ����Ƚ�ȡ�ֶδ�����������ַ�����׷��"..."���ţ�ע����Ľ�ȡ���������Ѱ���ʡ�Ժţ�ʵ�ʽ�ȡ���ݳ��ȱȲ���ֵ��1���ַ�
	 * String str = "����һֻСССС��";
	 * System.out.println(StringUtil.subStringAppendDots(str,5));
	 * ��������"����һֻ...";
	 * @param sourceString
	 * @param maxLength
	 * @return
	 */
	public static String subStringAppendDots(String sourceString, int maxLength){
		String str = null;
		if(sourceString.length()>maxLength){
			str = subString(sourceString, maxLength-1)+"...";
		}
		else{
			str = sourceString;
		}
		return str;
	}

	/**
	 * ���ֽڳ��Ƚ�ȡ�ֶδ�����������ַ�����׷��"..."���ţ�ע����Ľ�ȡ���������Ѱ���ʡ�Ժţ�ʵ�ʽ�ȡ���ݳ��ȱȲ���ֵ��2-4���ֽ�
	 * @param sourceString
	 * @param maxLength
	 * @return
	 */
	public static String subStringByBytesAppendDots(String sourceString, int maxLength){
		String str = null;
		if(getStrLenByBytes(sourceString)>maxLength){
			str = subStringByBytes(sourceString, maxLength-3)+"...";
		}
		else{
			str = sourceString;
		}
		return str;
	}

	/**
	 * ���ַ����ȴ�β����ȡ�ֶδ�����������ַ������滻Ϊ"..."���ţ�ע����Ľ�ȡ���������Ѱ���ʡ�Ժţ�ʵ�ʽ�ȡ���ݳ��ȱȲ���ֵ��1���ַ�
	 * String str = "����һֻСССС��";
	 * System.out.println(StringUtil.subStringByBytesBeginWithDots(str,5));
	 * ��������"...ССС��";
	 * @param sourceString
	 * @param maxLength
	 * @return
	 */
	public static String subStringByBytesBeginWithDots(String sourceString, int maxLength){
		String str = null;
		if(getStrLenByBytes(sourceString)>maxLength){
			//System.out.println("subStringByBytes(\""+sourceString+"\","+(getStrLenByBytes(sourceString)-maxLength+3)+","+maxLength+")");
			str = "..."+subStringByBytes(sourceString,(getStrLenByBytes(sourceString)-maxLength+3), maxLength);
		}
		else{
			str = sourceString;
		}
		return str;
	}
	
	/**
	 * ���ַ�����λ��ʼ��ȡָ���ַ�����ָ�����ȵ��Ӵ���ʹ��byte��ʽ��ȡ�ַ������ȱ������������ɵ��ַ������ȱ仯����
	 * ��ȥ����ȡ��β�����ڰ�������ַ���ɵ�����
	 * @param sourceString
	 * @param maxLength ��ȡ�ܳ���
	 * @return ���к�Ľ�������Դ�ַ���Ϊnull�����ؿմ�
	 */
	public static String subStringByBytes(String sourceString, int maxLength) {
		return subStringByBytes(sourceString, 0, maxLength);
	}

	/**
	 * ��ָ��ƫ��λ�ÿ�ʼ��ȡָ���ַ�����ָ�����ȵ��Ӵ���ʹ��byte��ʽ��ȡ�ַ������ȱ������������ɵ��ַ������ȱ仯����
	 * ��ȥ����ȡ����β���ڰ�������ַ���ɵ�����
	 * @param sourceString
	 * @param offset ƫ��λ��
	 * @param maxLength ��ȡ�ܳ���
	 * @return ���к�Ľ�������Դ�ַ���Ϊnull�����ؿմ�
	 */
	public static String subStringByBytes(String sourceString, int offset, int maxLength) {
		
		if( maxLength <= 0 )
			return "";
		if( offset < 0 )
			offset = 0;
		String retString = "";
		byte[] sourceByte = sourceString.getBytes();
		int trueLength = sourceByte.length;
		int beginIndex=0;
		int trueoffset=0;
		int trueMaxLength = maxLength;
		
		boolean bChineseFirstHalf = false;
		/*��ȡ������ƫ����,�뿪ʼ����*/
		if(trueLength>offset){
			for(int i = 0; i < offset; i++) {
				trueoffset++;//ʵ��ƫ����
				if(sourceByte[i] > 0){//��ǰλ�ò�������
					bChineseFirstHalf = false;
					beginIndex++;//��ʼָ������ƶ�
				}
				else if( sourceByte[i] < 0 && !bChineseFirstHalf ) {//��ǰλ��Ϊ��������
					bChineseFirstHalf = true;
					beginIndex++;//��ʼָ������ƶ�
				}
				else {//��ǰλ��Ϊ������β
					bChineseFirstHalf = false;
					if(i==offset-1){
						trueoffset++;//��ǰλ��Ϊ������βʵ��ƫ����������ƶ���ƫ��λ����Զ���������׺����ַ���
					}
					if(trueoffset>=trueLength){
						return "";
					}
				}
			}
		}
		else{
			return "";
		}
		/*��ȡ��������*/
		int endIndex=0;
		if(trueoffset>offset){//ƫ��λ�������ʵ�ʽ�ȡ���ȼ���
			trueMaxLength--;
		}
		//System.out.println("subStringByBytes(\""+sourceString+"\","+offset+","+maxLength+")��Ϊ��subStringByBytes(\""+sourceString+"\","+trueoffset+","+trueMaxLength+")");
		
		bChineseFirstHalf = false;
		if( trueLength <= (trueMaxLength + trueoffset) ){
			endIndex=sourceString.length();
		}
		else{
			for(int i = 0; i < (trueMaxLength + trueoffset); i++) {
				if(sourceByte[i] > 0){//��ǰλ�ò�������
					bChineseFirstHalf = false;
					endIndex++;//����ָ������ƶ�
				}
				else if( sourceByte[i] < 0 && !bChineseFirstHalf ) {//��ǰλ��Ϊ��������
					bChineseFirstHalf = true;
				}
				else {//��ǰλ��Ϊ������β
					bChineseFirstHalf = false;
					endIndex++;//����ָ������ƶ�
				}
			}
			if(endIndex>sourceString.length()){
				endIndex=sourceString.length();
			}
		}
		//System.out.println("����ִ�У�\""+sourceString+"\".substring("+beginIndex+","+endIndex+")");
		retString=sourceString.substring(beginIndex, endIndex);
		
		return retString;
	}

	/**
	 * ���������ַ����������ַ����ĳ��ȣ�ʹ��byte��ʽ��ȡ�ַ������ȱ������������ɵ��ַ������ȱ仯����
	 * @param str �ж��ַ���
	 * @param encoding �����趨
	 * @return �˱���ĳ���
	 */
	public static int getStrLenByBytes(String str, String encoding) {

		int retInt = -1;

		try {
			if( ObjectUtil.isEmpty(str) ) {
				retInt = 0;
			}
			else {

				byte[] byArr = str.getBytes(encoding);

				retInt = byArr.length;
			}
		}
		catch(UnsupportedEncodingException e) {
			Debug.exception(logger, e);
		}
		return retInt;
	}

	/**
	 * ����ϵͳ�ַ����������ַ����ĳ��ȣ�ʹ��byte��ʽ��ȡ�ַ������ȱ������������ɵ��ַ������ȱ仯����
	 * @param str �ж��ַ���
	 * @return �˱���ĳ���
	 */
	public static int getStrLenByBytes(String str) {

		return getStrLenByBytes(str, System.getProperty("file.encoding"));
	}

	/**
	 * ���ݸ�����λ�����ø������ַ�ǰ�����ַ�����λ�������������������ɵ��ַ������ȱ仯����
	 * @param strValue Դ������
	 * @param ch Ҫ�����ַ�
	 * @param iSign �����ĳ���
	 * @return
	 */
	public static String fillString(String strValue, char ch, int iSign) {

		try {
			StringBuffer strTemp = new StringBuffer();
			int iDifference = iSign - strValue.length();
			if( iDifference <= 0 ) {
				return strValue;
			}
			for(int i = 0; i < iDifference; i++) {
				strTemp.append(ch);
			}
			strTemp.append(strValue);
			return strTemp.toString();
		}
		catch(Exception ex) {
			Debug.exception(logger, ex);
			return "";
		}
	}

	/**
	 * ���ݸ�����λ�����ø������ַ������ַ�����λ�������������������ɵ��ַ������ȱ仯����
	 * 
	 * @param strValue Դ������
	 * @param ch Ҫ�����ַ�
	 * @param iSign �����ĳ���
	 * @return
	 */
	public static String fillStringOnTail(String strValue, char ch, int iSign) {

		try {
			StringBuffer strTemp = new StringBuffer();
			strTemp.append(strValue);
			int iDifference = iSign - strValue.length();

			if( iDifference <= 0 ) {
				return strValue;
			}
			for(int i = 0; i < iDifference; i++) {
				strTemp.append(ch);
			}

			return strTemp.toString();
		}
		catch(Exception ex) {
			Debug.exception(logger, ex);
			return "";
		}
	}

	/**
	 * ���ݸ�����λ�����ø������ַ�ǰ�����ַ�����λ����ʹ��byte��ʽ��ȡ�ַ������ȱ������������ɵ��ַ������ȱ仯����
	 * @param strValue Դ������
	 * @param ch Ҫ�����ַ�
	 * @param iSign �����ĳ���
	 * @return
	 */
	public static String fillStringByBytes(String strValue, char ch, int iSign) {

		try {
			StringBuffer strTemp = new StringBuffer();
			int iDifference = iSign - strValue.getBytes().length;
			if( iDifference <= 0 ) {
				return strValue;
			}
			for(int i = 0; i < iDifference; i++) {
				strTemp.append(ch);
			}
			strTemp.append(strValue);
			return strTemp.toString();
		}
		catch(Exception ex) {
			Debug.exception(logger, ex);
			return "";
		}

	}

	/**
	 * ���ݸ�����λ�����ø������ַ������ַ�����λ����ʹ��byte��ʽ��ȡ�ַ������ȱ������������ɵ��ַ������ȱ仯����
	 * @param strValue Դ������
	 * @param ch Ҫ�����ַ�
	 * @param iSign �����ĳ���
	 * @return
	 */
	public static String fillStringByBytesOnTail(String strValue, char ch, int iSign) {

		try {
			StringBuffer strTemp = new StringBuffer();
			strTemp.append(strValue);
			int iDifference = iSign - strValue.getBytes().length;

			if( iDifference <= 0 ) {
				return strValue;
			}
			for(int i = 0; i < iDifference; i++) {
				strTemp.append(ch);
			}

			return strTemp.toString();
		}
		catch(Exception ex) {
			Debug.exception(logger, ex);
			return "";
		}
	}

	/**
	 * �Ƚ������ַ����Ƿ���ȣ�����һ������ΪNull��
	 * @param str1 �ж϶���String1
	 * @param str2 �ж϶���String2
	 * @return ��ȷ���true����֮Ϊfalse
	 */
	public static boolean equals(String str1, String str2) {

		return str1 != null ? str1.equals(str2) : str2 == null;
	}

	/**
	 * �ж��ַ������Ƿ���������жϵ��ַ�
	 * @param str �����ַ���
	 * @param searchChar �ж����ַ�
	 * @return ����ʱ����true����֮Ϊfalse
	 */
	public static boolean contains(String str, char searchChar) {

		if( ObjectUtil.isEmpty(str) ) {
			return false;
		}
		else {
			return str.indexOf(searchChar) >= 0;
		}
	}

	/**
	 * �ж��ַ������Ƿ���������жϵ��ַ���
	 * @param str �����ַ���
	 * @param searchChar �ж����ַ���
	 * @return ����ʱ����true����֮Ϊfalse
	 */
	public static boolean contains(String str, String searchStr) {

		if( ObjectUtil.isEmpty(str) || ObjectUtil.isEmpty(searchStr) ) {
			return false;
		}
		else {
			return str.indexOf(searchStr) >= 0;
		}
	}

	/**
	 * �Ƚ������ַ����е�ֵ�Ƿ���ȣ��������򷴻ص�����������ֵ
	 * @param data 
	 * @param str 
	 * @return
	 */
	public static String equals(Object data, String data1, String str) {

		if( getString(data).equals(data1) ) {
			return str;
		}

		return "";
	}

	/**
	 * �Ƚ������ַ����е�ֵ�Ƿ���ȣ��������򷴻ص�����������ֵ
	 * @param data 
	 * @param str 
	 * @return
	 */
	public static String equals(String data, String data1, String str) {

		if( getString(data).equals(data1) ) {
			return str;
		}
		return "";
	}
	/**
	 * ASCII��תString
	 * @param str
	 * @return
	 */
	public static String ascii2Native(String str) {

		StringBuilder sb = new StringBuilder();
		int begin = 0;
		int index = str.indexOf("%u");
		while( index != -1 ) {
			sb.append(str.substring(begin, index));
			sb.append(ascii2Char(str.substring(index, index + 6)));
			begin = index + 6;
			index = str.indexOf("%u", begin);
		}
		sb.append(str.substring(begin));
		return sb.toString();
	}

	private static char ascii2Char(String str) {

		if( str.length() != 6 ) {
			throw new IllegalArgumentException("Ascii string of a native character must be 6 character.");
		}
		if( !"%u".equals(str.substring(0, 2)) ) {
			throw new IllegalArgumentException("Ascii string of a native character must start with \"\\u\".");
		}
		String tmp = str.substring(2, 4);
		int code = Integer.parseInt(tmp, 16) << 8;
		tmp = str.substring(4, 6);
		code += Integer.parseInt(tmp, 16);
		return (char)code;
	}
	
	
	public static String toChinese(String sDigit) {
		String sChinese = sDigit;
		sChinese = "";
		if( sDigit.charAt(0) >= '0' && sDigit.charAt(0) <= '9' ) {
			switch(sDigit.charAt(0))
			{
				case '1':
				{
					if( sDigit.charAt(1) == '0' ) {
						sChinese = sChinese + "ʮ";
						break;
					}
					sChinese = sChinese + "һ";
					break;
				}
				case '2':
				{
					sChinese = sChinese + "��";
					break;
				}
				case '3':
				{
					sChinese = sChinese + "��";
					break;
				}
				case '4':
				{
					sChinese = sChinese + "��";
					break;
				}
				case '5':
				{
					sChinese = sChinese + "��";
					break;
				}
				case '6':
				{
					sChinese = sChinese + "��";
					break;
				}
				case '7':
				{
					sChinese = sChinese + "��";
					break;
				}
				case '8':
				{
					sChinese = sChinese + "��";
					break;
				}
				case '9':
				{
					sChinese = sChinese + "��";
					break;
				}
				case '0':
				{
					sChinese = sChinese + "0";
					break;
				}
			}
		}
		return sChinese;
	}
	
	
	public static void main(String[] args) {
		String str = "��a��һֻСССС��"; 
		//String subStr=StringUtil.subStringByBytes(str,4,14);
		//String subStr=StringUtil.subStringByBytesAppendDots(str, 16);
		String subStr=StringUtil.subStringByBytesBeginWithDots(str, 18);
		System.out.println("���:"+subStr);
		
	}
}