package hmfms.util;

/**
 * <p>��    ��: ���Ŀ��</p>
 * <p>��    ��: ��ӡ���ʵ����</p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2010-12-13 ����11:59:30</p>
 * @author ��Ʒ������
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
	 * ��ӡ������»��ߣ���ʽ����
	 * 
	 * @param line
	 * @param len
	 * @return
	 */
	public static String genLineLeft(String line, int len) {

		/* ���line.getBytes().length > len ����Ҫ�ӿո� */
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

		int iStrLen = 0;// �ַ�������
		int iSpaceLen = 0;// �ո񳤶�
		/*
		 * �����ַ������ȣ� ����iStrLen
		 */
		for(int i = 0; i < str.length(); i++) {
			char sTemp = str.charAt(i);// ��iλ�ַ�
			/* �жϵ�ǰ�ַ���Ӣ�Ļ��Ǻ��� */
			if( sTemp <= 128 ) { /* ΪӢ�Ļ�����,����+1 */
				iStrLen += 1;
			}
			else {
				/* Ϊ����,����+2 */
				iStrLen += 2;
			}
		}
		/* �ַ������˲��ո�ĳ��� */
		iSpaceLen = (2 * len - iStrLen) / 2;
		/* ����ո� */
		for(int i = 0; i < iSpaceLen; i++) {
			str = WHITE_STRING + str + WHITE_STRING;
		}
		/*
		 * ����һ���ַ��ĳ��� ����strǰ�ӿո�
		 */
		if( (2 * len - iStrLen) % 2 == 1 )
			str = WHITE_STRING + str;
		return str;
	}

	public static String addspace0(String str, int len) {

		int iStrLen = 0;// �ַ�������
		int iSpaceLen = 0;// �ո񳤶�
		/*
		 * �����ַ������ȣ� ����iStrLen
		 */
		for(int i = 0; i < str.length(); i++) {
			char sTemp = str.charAt(i);// ��iλ�ַ�
			/* �жϵ�ǰ�ַ���Ӣ�Ļ��Ǻ��� */
			if( sTemp <= 128 ) { /* ΪӢ�Ļ�����,����+1 */
				iStrLen += 1;
			}
			else {
				/* Ϊ����,����+2 */
				iStrLen += 2;
			}
		}
		/* �ַ������˲��ո�ĳ��� */
		iSpaceLen = (len - iStrLen) / 2;
		/* ����ո� */
		for(int i = 0; i < iSpaceLen; i++) {
			str = WHITE_STRING + str + WHITE_STRING;
		}
		/*
		 * ����һ���ַ��ĳ��� ����strǰ�ӿո�
		 */
		if( (len - iStrLen) % 2 == 1 )
			str = WHITE_STRING + str;
		return str;
	}

	public static void main(String[] args) {

		genLine("s˹s�ٷ�", 12);
		genLine("s1s23", 12);
	}
}
