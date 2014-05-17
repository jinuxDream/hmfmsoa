/**
 * 
 */
package hmfms.util;

import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author BruceYu
 *
 */
public class DigitalUtil {
	private static final Log logger = LogFactory.getLog(DigitalUtil.class);
	/**
	 * 判断数字字符串是否符合要求（对整数部分和小数部分都有定义，以及是否固定长度）
	 * @param digitStr 数字字符串
	 * @param integerLength 定义整数部分的长度
	 * @param decimalLength 定义小数部分的长度
	 * @param isFixedLength 是否限定长度
	 * @return true为符合要求，false为不符合要求
	 */
	private static boolean isLegalDigital(String digitStr, int integerLength, int decimalLength, boolean isFixedLength) {

		if( null == digitStr ) {
			return false;
		}
		
		if(0 == integerLength && 0 == decimalLength) {
			if( isNotNumeric(digitStr) ) {
				return false;
			}
		} else if( 0 >= decimalLength ) {
			if(isFixedLength) {
				if(!Pattern.matches("^\\d{" + integerLength + "}$", digitStr)) {
					return false;
				}
			} else {
				if(!Pattern.matches("^\\d{1," + integerLength + "}$", digitStr)) {
					return false;
				}
			}
		} else if( 0 >= integerLength ) {
			if(isFixedLength) {
				if(!Pattern.matches("^\\d+\\.\\d{" + decimalLength + "}$", digitStr)) {
					return false;
				}
			} else {
				if(!Pattern.matches("^\\d+\\.\\d{1," + decimalLength + "}$", digitStr)) {
					return false;
				}
			}
			
		} else {
			if(isFixedLength) {
				if(!Pattern.matches("^\\d{" + integerLength + "}\\.\\d{" + decimalLength + "}$", digitStr)) {
					return false;
				}
			} else {
				if(!Pattern.matches("(^\\d{1," + integerLength + "}$)|(^0\\.\\d{1," + decimalLength + "}$)" +
						"|(^\\d{1," + integerLength + "}\\.\\d{1," + decimalLength + "}$)", digitStr)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 判断不限定长度的数字字符串是否符合要求（对整数部分和小数部分都有定义，但不限定长度）
	 * @param digitStr 数字字符串
	 * @param integerLength 定义整数部分的长度
	 * @param decimalLength 定义小数部分的长度
	 * @return true为符合要求，false为不符合要求
	 */
	public static boolean isLegalDigital(String digitStr, int integerLength, int decimalLength) {
		return isLegalDigital(digitStr, integerLength, decimalLength, false);
	}
	
	/**
	 * 判断限定长度的数字字符串是否符合要求（对整数部分和小数部分都有定义且限定长度）
	 * @param digitStr 数字字符串
	 * @param integerLength 定义整数部分的长度
	 * @param decimalLength 定义小数部分的长度
	 * @return true为符合要求，false为不符合要求
	 */
	public static boolean isLegalDigitalFixedLength(String digitStr, int integerLength, int decimalLength) {
		return isLegalDigital(digitStr, integerLength, decimalLength, true);
	}
	
	/**
	 * 判断含不限定长度的小数字符串是否符合要求(不限定小数长度)
	 * @param digitStr 数字字符串
	 * @param integerLength 定义小数部分的长度
	 * @return true为符合要求，false为不符合要求
	 */
	public static boolean isLegalFloat(String digitStr, int decimalLength ) {
		return isLegalDigital(digitStr, -1, decimalLength, false);
	}
	
	/**
	 * 判断含限定长度的小数字符串是否符合要求(限定小数长度)
	 * @param digitStr 数字字符串
	 * @param integerLength 定义小数部分的长度
	 * @return true为符合要求，false为不符合要求
	 */
	public static boolean isLegalFloatFixedLength(String digitStr, int decimalLength) {
		return isLegalDigital(digitStr, -1, decimalLength, true);
	}
	
	/**
	 * 判断含不限定长度的整数字符串是否符合要求(不限定整数长度)
	 * @param digitStr 数字字符串
	 * @param integerLength 定义整数部分的长度
	 * @return true为符合要求，false为不符合要求
	 */
	public static boolean isLegalInteger(String digitStr, int integerLength ) {
		return isLegalDigital(digitStr, integerLength, -1, false);
	}
	
	/**
	 * 判断含限定长度的整数字符串是否符合要求(限定整数长度)
	 * @param digitStr 数字字符串
	 * @param integerLength 定义整数部分的长度
	 * @return true为符合要求，false为不符合要求
	 */
	public static boolean isLegalIntegerFixedLength(String digitStr, int integerLength) {
		return isLegalDigital(digitStr, integerLength, -1, true);
	}
	
	/**
	 * 判断此字符串中是否为一个数字（可以包含正负号和小数点的判断）
	 * @param str 字符串判断对象
	 * @param includePoint 是否包含小数点
	 * @param includeSign 是否包含正负号
	 * @return true为数字字符串，false不为数字字符串
	 */
	public static boolean isNumeric(String str, boolean includePoint, boolean includeSign) {

		if( ObjectUtil.isEmpty(str) ) {
			return false;
		}

		boolean canUseDecimal = includePoint;
		int length = str.length();
		for(int i = length; --i >= 0;) {
			int chr = str.charAt(i);
			if( chr < 0x30 || chr > 0x39 ) {
				if( includeSign && i == 0 && (chr == 0x2B || chr == 0x2D) ) {
					continue;
				}
				else if( i != length - 1 && i != 0 && (canUseDecimal && chr == 0x2E) ) {
					canUseDecimal = false;
					continue;
				}
				else {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 判断此字符串中是否为一个数字（包含正负号和小数点）
	 * @param str 字符串判断对象
	 * @return true为数字字符串，false不为数字字符串
	 */
	public static boolean isNumeric(String str) {

		return isNumeric(str, true, true);
	}

	/**
	 * 判断此字符串中是否为一个数字（可以包含正负号和小数点）
	 * @param str 字符串判断对象
	 * @return true不为数字字符串，false为数字字符串
	 */
	public static boolean isNotNumeric(String str) {

		return !isNumeric(str);
	}

	public static void main(String[] args) {

		String str = "12.22.2";
		Debug.info(logger,""+isLegalDigital(str, 6, 2));
	}
}
