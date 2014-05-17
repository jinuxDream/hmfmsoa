package hmfms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChineseUtil {

	/**
	 * 判断一个字符串是否为汉字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str) {
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = null;
		boolean flag = false;
		for (int i = 0; i < str.length(); i++) {
			String temp = "";
			if (i != str.length() - 1) {
				temp = str.substring(i, i + 1);
			} else {
				temp = str.substring(i);
			}
			m = p.matcher(temp);
			if (m.matches()) {
				flag = true;
				continue;
			} else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	// 国标码和区位码转换常量
	static final int GB_SP_DIFF = 160;

	// 存放国标一级汉字不同读音的起始区位码
	static final int[] secPosValueList = {

	1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787,

	3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086,

	4390, 4558, 4684, 4925, 5249, 5600 };

	// 存放国标一级汉字不同读音的起始区位码对应读音

	static final char[] firstLetter = {

	'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j',

	'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',

	't', 'w', 'x', 'y', 'z' };

	// 获取一个字符串的拼音码
	public static String getFirstLetter(String oriStr) {

		String str = oriStr.toLowerCase();

		StringBuffer buffer = new StringBuffer();

		char ch;

		char[] temp;

		for (int i = 0; i < str.length(); i++) { // 依次处理str中每个字符

			ch = str.charAt(i);

			temp = new char[] { ch };

			byte[] uniCode = new String(temp).getBytes();

			if (uniCode[0] < 128 && uniCode[0] > 0) { // 非汉字

				buffer.append(temp);

			} else {

				buffer.append(convert(uniCode));

			}
		}

		return buffer.toString();

	}

	/**
	 * 获取一个汉字的拼音首字母。
	 * 
	 * GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
	 * 
	 * 例如汉字“你”的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
	 * 
	 * 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n’
	 * 
	 */

	public static char convert(byte[] bytes) {

		char result = '-';

		int secPosValue = 0;

		int i;

		for (i = 0; i < bytes.length; i++) {

			bytes[i] -= GB_SP_DIFF;

		}

		secPosValue = bytes[0] * 100 + bytes[1];

		for (i = 0; i < 23; i++) {

			if (secPosValue >= secPosValueList[i]
					&& secPosValue < secPosValueList[i + 1]) {

				result = firstLetter[i];

				break;

			}
		}
		return result;
	}
	
	/**
	 * 找出字符串中的字母,并得到它的索引
	 * @param str
	 */
	public static String[] getAlphaFromString(String str) {
		String pattern = "[^A-Za-z]+";
		String[] arrays = str.split(pattern);
		int count = 0;
		String index = "";
		String[] returnString = new String[2];
		for (String string : arrays) {
			if (isAlpha(string)) {
				count++;
				index = str.indexOf(string) + "";
				returnString[0] = string;
				returnString[1] = index;
			}
			if (count == 1) {
				break;
			}
		}
		if (ObjectUtil.isEmpty(returnString[0])) {
			returnString[0] = "";
			returnString[1] = "-1";
		}
		return returnString;
	}
	
	/**
	 * 判断字符串是否为字母
	 */
	public static boolean isAlpha(String str) {
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		Matcher isAlpha = pattern.matcher(str);
		if(!isAlpha.matches()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 找出字符串中的数字,并得到它的索引
	 * @param str
	 */
	public static String[] getNumFromString(String str) {
		String pattern = "[^0-9]+";
		String[] arrays = str.split(pattern);
		int count = 0;
		String index = "";
		String[] returnString = new String[2];
		for (String string : arrays) {
			if (StringUtil.isNumeric2(string)) {
				count++;
				index = str.indexOf(string) + "";
				returnString[0] = string;
				returnString[1] = index;
			}
			if (count == 1) {
				break;
			}
		}
		if (ObjectUtil.isEmpty(returnString[0])) {
			returnString[0] = "";
			returnString[1] = "-1";
		}
		return returnString;
	}
	
	public static void main(String[] args) {
		
	}
}
