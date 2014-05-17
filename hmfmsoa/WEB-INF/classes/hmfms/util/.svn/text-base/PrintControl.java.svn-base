package hmfms.util;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 打印输出实用类</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:59:30</p>
 * @author 产品开发部
 * @version 2.0
 * PrintControl
 */
public class PrintControl extends AbstractUtil {

	private static String WHITE_STRING = "&nbsp;";

	// private static String WHITE_STRING = " ";
	private static String fillString(String old, String filedString, int num, boolean head) {

		String tmp = "";
		for(int i = 0; i < num; i++) {
			tmp += filedString;
		}
		if( head )
			return tmp + old;
		else
			return old + tmp;
	}

	public static String genLine(String line, int len) {

		int head_len = len / 2;
		int tail_len = len - head_len;

		int line_len = line.getBytes().length;
		int head_line_len = line_len / 2;
		int tail_line_len = line_len - head_line_len;

		String new_line = fillString(line, WHITE_STRING, head_len - head_line_len, true);
		new_line = fillString(new_line, WHITE_STRING, tail_len - tail_line_len, false);
		// Debug.info(logger,"string len=" + line.length());
		// Debug.info(logger,"string byte len=" + line.getBytes().length);
		// Debug.info(logger,"string byte len=" + new_line.length());
		// Debug.info(logger,"line =" + line);
		// Debug.info(logger,"new_line =" + new_line);

		return new_line;
	}

	/**
	 * 打印字体带下划线，样式靠左
	 * 
	 * @param line
	 * @param len
	 * @return
	 */
	public static String genLineLeft(String line, int len) {

		/* 如果line.getBytes().length > len 则不需要加空格 */
		int line_len = line.getBytes().length;
		if( line_len >= len ) {
			return line;
		}
		else {
			int tail_line_len = len - line_len;
			String new_line = fillString(line, WHITE_STRING, tail_line_len, false);
			return new_line;
		}

	}

	public static String genLine1(String line, int len) {

		if( line.getBytes().length > len ) {
			for(int i = 0; i < len; i++) {
				String strTemp = line.substring(0, i + 1);
				if( strTemp.getBytes().length == len ) {
					return line.substring(0, i + 1);
				}
				else if( strTemp.getBytes().length > len ) {
					return line.substring(0, i);
				}
			}
			return line.substring(0, len / 2);
		}
		int head_len = len / 2;
		int tail_len = len - head_len;

		int line_len = line.getBytes().length;
		int head_line_len = line_len / 2;
		int tail_line_len = line_len - head_line_len;

		String new_line = fillString(line, WHITE_STRING, head_len - head_line_len, true);
		new_line = fillString(new_line, WHITE_STRING, tail_len - tail_line_len, false);
		// Debug.info(logger,"string len=" + line.length());
		// Debug.info(logger,"string byte len=" + line.getBytes().length);
		// Debug.info(logger,"string byte len=" + new_line.length());
		// Debug.info(logger,"line =" + line);
		// Debug.info(logger,"new_line =" + new_line);

		return new_line;
	}

	public static String addspace(String str, int len) {

		int iStrLen = 0;// 字符串长度
		int iSpaceLen = 0;// 空格长度
		/*
		 * 计算字符串长度， 放入iStrLen
		 */
		for(int i = 0; i < str.length(); i++) {
			char sTemp = str.charAt(i);// 第i位字符
			/* 判断当前字符是英文还是汉字 */
			if( sTemp <= 128 ) { /* 为英文或数字,长度+1 */
				iStrLen += 1;
			}
			else {
				/* 为汉字,长度+2 */
				iStrLen += 2;
			}
		}
		/* 字符串两端补空格的长度 */
		iSpaceLen = (2 * len - iStrLen) / 2;
		/* 补入空格 */
		for(int i = 0; i < iSpaceLen; i++) {
			str = WHITE_STRING + str + WHITE_STRING;
		}
		/*
		 * 若余一个字符的长度 则在str前加空格
		 */
		if( (2 * len - iStrLen) % 2 == 1 )
			str = WHITE_STRING + str;
		return str;
	}

	public static String addspace0(String str, int len) {

		int iStrLen = 0;// 字符串长度
		int iSpaceLen = 0;// 空格长度
		/*
		 * 计算字符串长度， 放入iStrLen
		 */
		for(int i = 0; i < str.length(); i++) {
			char sTemp = str.charAt(i);// 第i位字符
			/* 判断当前字符是英文还是汉字 */
			if( sTemp <= 128 ) { /* 为英文或数字,长度+1 */
				iStrLen += 1;
			}
			else {
				/* 为汉字,长度+2 */
				iStrLen += 2;
			}
		}
		/* 字符串两端补空格的长度 */
		iSpaceLen = (len - iStrLen) / 2;
		/* 补入空格 */
		for(int i = 0; i < iSpaceLen; i++) {
			str = WHITE_STRING + str + WHITE_STRING;
		}
		/*
		 * 若余一个字符的长度 则在str前加空格
		 */
		if( (len - iStrLen) % 2 == 1 )
			str = WHITE_STRING + str;
		return str;
	}

	public static void main(String[] args) {

		genLine("s斯s蒂芬", 12);
		genLine("s1s23", 12);
	}
}
