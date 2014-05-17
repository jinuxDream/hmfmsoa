package hmfms.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 打印输出定义类</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:58:58</p>
 * @author 产品开发部
 * @version 2.0
 * OutControl
 */
public class OutControl extends AbstractUtil {
	private static final Log logger = LogFactory.getLog(OutControl.class);
	/*	public OutControl()
	 {
	 }
	 public OutControl(int index)
	 {
	 }
	 public OutControl()*/
	private String number;//申请编号
	private String flag = "[补 打]";//补打标记
	private String title;//正标题
	private String adtitle;//副标题
	private String head;//抬头
	private String content;//详细内容
	private String tail;//落款
	private String date;//日期

	private String year;//年
	private String month;//月
	private String data;//日

	private int length;//一行的长度，已英文字符为标准。

	private static String p1 = "<tr><td height=15>";
	private static String p2 = "</td></tr>";

	private static String pa = "<p class=TopText>";
	private static String pb = "</p>";

	private static String font1 = "<font ";
	private static String font2 = ">";
	private static String font3 = "</font>";

	private static String br = "<br>";

	private static String space = "&nbsp;";
	private static String spacerow = "<p class=TopText>&nbsp;</p>";
	private static String spacetrue = " ";

	private static String b1 = "<b>";
	private static String b2 = "</b>";

	private static String u1 = "<u>";
	private static String u2 = "</u>";

	private static String color = "color=\"#ff0000\" ";

	private static String size = "size=\"3\" ";

	private static String face = "face=\"黑体\" ";

	private static String num = "11";
	private static String style = "style=\"font-size:11pt\" ";

	/*
	 给属性赋值
	 */
	public void setnumber(String number) {

		this.number = number;
	}

	public String getnumber() {

		return (this.number);
	}

	public void setflag(String flag) {

		this.flag = flag;
	}

	public String getflag() {

		return (this.flag);
	}

	public void settitle(String title) {

		this.title = title;
	}

	public String gettitle() {

		return (this.title);
	}

	public void setadtitle(String adtitle) {

		this.adtitle = adtitle;
	}

	public String getadtitle() {

		return (this.adtitle);
	}

	public void sethead(String head) {

		this.head = head;
	}

	public String gethead() {

		return (this.head);
	}

	public void setcontent(String content) {

		this.content = content;
	}

	public String getcontent() {

		return (this.content);
	}

	public void settail(String tail) {

		this.tail = tail;
	}

	public String gettail() {

		return (this.tail);
	}

	public void setdate(String date) {

		this.date = date;
		this.setdata();
	}

	public String getdate() {

		return (this.tail);
	}

	public void setlength(int length) {

		this.length = length;
	}

	public int getlength() {

		return (this.length);
	}

	/*
	 将数值类型的日期转化为文字日期。
	 */
	private void setdata() {

		this.year = this.date.substring(0, 4);
		this.year = this.year + "年";
		this.month = this.date.substring(4, 6);
		this.month = this.month + "月";
		this.data = this.date.substring(6, 8);
		this.data = this.data + "日";
	}

	/*
	 返回转化后的文字日期。
	 */

	public String returndate() {

		return (this.year + this.month + this.data);
	}

	/*
	 返回申请编号的网页格式文本。左对齐。
	 传入参数：
	 String -----申请编号
	 返回的参数：
	 String ------在网页上输出的文本。
	 */
	public String formatnumber(String number) {

		String stemp = "";
		stemp = p1 + font1 + style + font2 + b1 + "［申请编号］" + number + b2 + font3 + p2;
		return stemp;
	}

	/*
	 返回补打标记的网页输出文本。右对齐。
	 传入的参数：
	 String -----补打标记的文本
	 int ----以英文字符长度为标准,一行的长度。
	 返回的参数：
	 String -----在网页上输出的文本。

	 */
	public String formatflag(String flag, int len) {

		String stemp = "";
		int ltemp = this.strlength(flag);
		////Debug.info(logger,ltemp);
		for(int i = 0; i < (len - ltemp); i++) {
			stemp = stemp + space;//右对齐
		}

		stemp = p1 + b1 + (stemp + flag) + b2 + p2;
		return stemp;
	}

	/*
	 返回正标题的网页输出文本。居中。
	 传入的参数：
	 String -----正标题的文本
	 int ----以英文字符长度为标准,一行的长度。
	 返回的参数：
	 String -----在网页上输出的文本。

	 */

	public String formattitle(String title, int len) {

		String stemp = "";
		int ltemp = this.strlength(title);
		for(int i = 0; i < ((len - ltemp) / 2); i++) {
			stemp = stemp + space;
		}
		stemp = p1 + (stemp + title) + p2;
		return stemp;
	}

	/*
	 返回副标题的网页输出文本。居中。
	 传入的参数：
	 String -----副标题的文本
	 int ----以英文字符长度为标准,一行的长度。
	 返回的参数：
	 String -----在网页上输出的文本。

	 */

	public String formatadtitle(String adtitle, int len) {

		String stemp = "";
		int ltemp = this.strlength(adtitle);
		for(int i = 0; i < ((len - ltemp) / 2); i++) {
			stemp = stemp + space;
		}
		stemp = p1 + (stemp + adtitle) + p2;
		return stemp;
	}

	/*
	 返回抬头的网页格式文本。左对齐。
	 传入参数：
	 String 
	 head-----抬头的内容（带下划线部分的）
	 sadd-----附加内容
	 返回的参数：
	 String ------在网页上输出的文本。
	 */
	public String formathead(String head, String sadd) {

		String stemp = "";
		stemp = spacerow + p1 + (u1 + head + u2) + sadd + "：" + p2;
		return stemp;
	}

	/*
	 将动态的值插入固定文本的下划线中，多余的位置插入空格。在网页上分段显示。每段的结尾用"\n"
	 传入的参数：
	 String -----带下划线的文本，
	 String[]-----要在下划线上插入的值。
	 boolean----为真，分隔区间中为数字，否则计算分隔区间的字符数。
	 返回参数：
	 String ----处理好的，在网页上输出的文本。
	 */
	public String formatcontent(String content, int len, String[] sarr, boolean bool) {

		String str2 = "";
		int[] iarr = this.getintarray(content, bool);
		if( sarr.length != iarr.length )
			return null;
		String[] str1 = this.addspace(sarr, iarr, false);//将字符串数组中的每个字符串都加上对应的空格。
		str2 = this.addString(content, str1);//把加好空格的字符串数组添加到字符串文本中。
		//Debug.info(logger,str2);
		str2 = this.splitpar(str2, len, true);//分段+分行
		return str2;
	}

	public String formatcontent(String content, int len, String[] sarr, boolean bool, boolean bl) {

		String str2 = "";
		int[] iarr = this.getintarray(content, bool);
		if( sarr.length != iarr.length )
			return null;
		String[] str1 = this.addspace(sarr, iarr, false);//将字符串数组中的每个字符串都加上对应的空格。
		str2 = this.addString(content, str1);//把加好空格的字符串数组添加到字符串文本中。
		//Debug.info(logger,str2);
		str2 = this.splitpar(str2, len, bl);//分段+分行
		return str2;
	}

	/*
	 返回落款的网页输出文本。右对齐。
	 传入的参数：
	 String 
	 tail-----落款的文本（带下划线部分的内容）
	 sadd-----附加内容
	 int ----以英文字符长度为标准,一行的长度。
	 返回的参数：
	 String -----在网页上输出的文本。

	 */
	public String formattail(String tail, String sadd, int len) {

		String stemp = "";
		int ltemp = this.strlength(tail) + strlength(sadd);

		////Debug.info(logger,ltemp);
		for(int i = 0; i < (len - ltemp); i++) {
			stemp = stemp + space;//右对齐
		}

		stemp = p1 + stemp + (u1 + tail + u2) + sadd + p2;
		return stemp;
	}

	/*
	 返回落款的网页输出文本。右对齐。
	 传入的参数：
	 String 
	 tail-----落款的文本（带下划线部分的内容）
	 sadd-----附加内容
	 int ----以英文字符长度为标准,一行的长度。
	 返回的参数：
	 String -----在网页上输出的文本。

	 */
	public String formatdate(String date, int len) {

		String stemp = "";
		this.setdate(date);
		int ltemp = this.strlength(this.returndate());

		////Debug.info(logger,ltemp);
		for(int i = 0; i < (len - ltemp); i++) {
			stemp = stemp + space;//右对齐
		}

		stemp = p1 + stemp + this.returndate() + p2;
		return stemp;
	}

	/*
	 分行len为中文 长度
	 */
	public String splitrow(String strBody, int len, boolean bl) {

		String printStr1 = "";
		String printStr2 = "";
		StringBuffer sb = null;
		int k = 0;
		int j = 0;
		int m = 0;
		for(int i = 0; i < strBody.length(); i++) {
			if( i <= (strBody.length() - 6) && strBody.substring(i, i + 6).equals(space) ) {
				k = k + 1;
				printStr1 = printStr1 + strBody.substring(i, i + 6);
				i = i + 5;
			}
			else if( i <= (strBody.length() - 3) && strBody.substring(i, i + 3).equals("<u>") ) {
				k = k;
				printStr1 = printStr1 + strBody.substring(i, i + 3);
				i = i + 2;
			}
			else if( i <= (strBody.length() - 4) && strBody.substring(i, i + 4).equals("</u>") ) {
				k = k;
				printStr1 = printStr1 + strBody.substring(i, i + 4);
				i = i + 3;
			}
			else if( strBody.charAt(i) > 128 ) {
				k = k + 2;
				printStr1 = printStr1 + strBody.charAt(i);
			}
			else {
				k = k + 1;
				printStr1 = printStr1 + strBody.charAt(i);
			}
			if( j == 0 && (k == (len * 2 - 4) || k == (len * 2 - 5)) ) {
				if( bl )
					printStr2 = p1 + space + space + space + space + printStr1 + p2;
				else
					printStr2 = pa + space + space + space + space + printStr1 + pb;
				k = 0;
				printStr1 = "";
				j = 1;
				////Debug.info(logger,printStr1);
			}
			if( k == (len * 2) || k == (len * 2 - 1) ) {
				if( bl )
					printStr2 = printStr2 + p1 + printStr1 + p2;
				else
					printStr2 = printStr2 + pa + printStr1 + pb;
				k = 0;
				printStr1 = "";
			}
		}
		if( j == 0 ) {
			m = 0;
			if( bl )
				printStr2 = p1 + space + space + space + space + strBody + p2;
			else
				printStr2 = pa + space + space + space + space + strBody + pb;
		}
		if( j == 1 ) {
			m = 0;
			if( bl )
				printStr2 = printStr2 + p1 + printStr1 + p2;
			else
				printStr2 = printStr2 + pa + printStr1 + pb;
		}
		////Debug.info(logger,printStr2);
		/*StringBuffer sb =new StringBuffer(printStr2);
		 for(int i=0;i<printStr2.length();i++)
		 {
		 if(printStr2.charAt(i)==' ')
		 sb=sb.replace(i-1,i,space);
		 }
		 //Debug.info(logger,sb);
		 return sb.toString();*/
		return printStr2;
	}

	/*
	 分段+分行
	 */
	public String splitpar(String strBody, int len, boolean bl) {

		int k = 0;
		String printStr1 = "";
		String temp = "";
		for(int j = 1; j <= strBody.length(); j++) {
			if( strBody.substring(j - 1, j).equals("\n") ) {
				temp = strBody.substring(k, j);
				printStr1 = printStr1 + this.splitrow(temp, len, bl);
				k = j;
			}
		}
		if( printStr1.equals("") )
			printStr1 = this.splitrow(strBody, len, bl);
		else if( k != strBody.length() )
			printStr1 = printStr1 + this.splitrow(strBody.substring(k, strBody.length()), len, bl);
		return printStr1;
	}

	/*
	 将字符串数组添加到分隔区间中。默认的分隔区间为<u>.</u>
	 */

	public String addString(String strBody, String[] astr) {

		String printStr1 = "";
		int k = 0;
		int l = 0;
		for(int i = 0; i < strBody.length(); i++) {
			if( i <= (strBody.length() - 3) && strBody.substring(i, i + 3).equals("<u>") ) {
				printStr1 = printStr1 + strBody.substring(l, i + 3) + astr[k];
				i = i + 2;
				if( k < astr.length )
					k++;
				else
					return null;
				l = 0;
			}
			else if( i <= (strBody.length() - 4) && strBody.substring(i, i + 4).equals("</u>") ) {
				//printStr1=printStr1+strBody.substring(i,i+4);
				//i=i+3;
				//l=i+4;
				l = i;
			}
			////Debug.info(logger," i= "+i+" k= "+k+" l= "+l);
			////Debug.info(logger,printStr1);
		}
		if( l != 0 )
			printStr1 = printStr1 + strBody.substring(l, strBody.length());
		return printStr1;
	}

	/*
	 处理字符串的函数
	 传入参数：String ,int
	 传出参数：String 
	 传入参数的含义：String ---要处理的字符串
	 int ----一行显示的长度（中文字符的个数）
	 函数内部用到的参数的含义：
	 printStr1---保存每行显示的字符。
	 k---表示当前处理行中已经保存到printStr1中的字符的个数。（英文字符的个数）
	 n---当遇到连续的数字或者字母时，表示连续的数字或者字母的个数。
	 j---判断当前行是否是一段中的第一行。如果为一段中的第一行，则值为0。否则值为1。
	 printStr2---保存处理完的行。
	 m,i----表示循环记数.
	 内部处理过程：
	 1。判断传入的参数中是否有下划线标志：<u></u>
	 如果有，则<u></u>所占的字符个数不计算在内。
	 2。判断字符串中是否有连续的数字或者字母。（asc码<128）
	 如果有，计算连续的字符数，然后判断该行剩余的位置是否能完全放下该串字符。
	 如果能放下，则将该串字符放到printStr1中，从该串字符之后的位置继续处理。
	 如果放不下，则该行处理结束。该串字符放到下一行显示。然后从该串字符之后的位置继续处理。
	 3.如果当前处理行为第一行，则在printStr1前加上空格，和<p></p>,否则只加上<p></p>。
	 */
	public String change(String strBody, int len) {

		String printStr1 = "";
		String printStr2 = "";
		int k = 0;
		int j = 0;
		int n = 0;
		int m = 0;
		for(int i = 0; i < strBody.length(); i++) {
			//Debug.info(logger,"i="+i);
			//Debug.info(logger," k="+k);
			if( i < (strBody.length() - 4) && strBody.substring(i, i + 3).equals("<u>") ) {
				k = k;
				printStr1 = printStr1 + strBody.substring(i, i + 3);
				i = i + 2;
				//Debug.info(logger,"k="+k);
			}
			else if( i < (strBody.length() - 5) && strBody.substring(i, i + 4).equals("</u>") ) {
				k = k;
				printStr1 = printStr1 + strBody.substring(i, i + 4);
				i = i + 3;
				//Debug.info(logger,"k="+k);
			}
			else if( strBody.charAt(i) > 128 ) {
				k = k + 2;
				printStr1 = printStr1 + strBody.charAt(i);
				//Debug.info(logger,"printStr1:"+printStr1);
			}
			else //if(strBody.charAt(i)<128)
			{
				if( i < (strBody.length() - 5) && strBody.substring(i, i + 4).equals("</u>") ) {
					k = k;
					printStr1 = printStr1 + strBody.substring(i, i + 4);
					i = i + 3;
					//Debug.info(logger,"k="+k);
				}
				else {
					n = 0;
					for(m = i + 1; m < strBody.length(); m++) {
						//Debug.info(logger,"char:"+strBody.charAt(m));
						//Debug.info(logger,"n="+n);
						if( strBody.charAt(m) < 128 ) {
							n++;
						}
						if( strBody.charAt(m) > 128 ) {
							break;
						}
					}
					n++;
					if( j == 0 )//
					{
						if( n > (len * 2 - 4 - k) ) {
							//zhehang
							k = len * 2 - 4;
							i--;
						}
						else {
							k = k + n;
							printStr1 = printStr1 + strBody.substring(i, i + n);
							//Debug.info(logger,"printStr1 end(j=0) :"+printStr1);
							i = i + n - 1;
						}
					}
					if( j == 1 )//
					{
						if( n > (len * 2 - k) ) {
							k = len * 2;
							i--;
						}
						else {
							k = k + n;
							printStr1 = printStr1 + strBody.substring(i, i + n);
							//Debug.info(logger,"printStr1 end(j=1) :"+printStr1);
							i = i + n - 1;
						}
					}
				}
			}
			/*else
			 {
			 k=k+1;
			 printStr1=printStr1+strBody.charAt(i);
			 }
			 */
			if( j == 0 && (k == (len * 2 - 4) || k == (len * 2 - 5)) ) {
				printStr2 = "<p>" + "&nbsp;" + "&nbsp;" + "&nbsp;" + "&nbsp;" + printStr1 + "</p>";
				k = 0;
				printStr1 = "";
				j = 1;
			}
			if( j == 1 && (k == (len * 2) || k == (len * 2 - 1)) ) {
				printStr2 = printStr2 + "<p>" + printStr1 + "</p>";
				k = 0;
				printStr1 = "";
			}
			//Debug.info(logger,"printStr2:"+printStr2);
		}
		if( j == 0 ) {
			printStr2 = "<p>" + "&nbsp;" + "&nbsp;" + "&nbsp;" + "&nbsp;" + strBody + "</p>";
		}
		if( j == 1 ) {
			printStr2 = printStr2 + "<p>" + printStr1 + "</p>";
		}
		////Debug.info(logger,printStr2);
		return printStr2;
	}

	/*
	 在字符串中添加空格。使得字符串的总长度等于指定的长度。
	 传入参数：
	 String ----要处理的字符串。
	 int ---字符串加空格后的长度。
	 bool ----为真，则输入的长度以中文字符为标准。为假，则以英文字符为标准。
	 返回参数：
	 String ----处理完的字符串。
	 如果字符串的长度比指定的长度长，返回该串;
	 */

	public String addspace(String str, int len, boolean bool) {

		int lentemp = strlength(str);
		int lent = 0;
		if( bool )
			lent = len * 2;
		else
			lent = len;
		////Debug.info(logger,lentemp);
		String strtemp = "";
		if( lentemp > lent )
			return (str);
		else {
			for(int i = 0; i < (lent - lentemp) / 2; i++) {
				strtemp = space + strtemp;
			}
			strtemp = strtemp + str;
			for(int i = 0; i < (lent - lentemp - (lent - lentemp) / 2); i++) {
				strtemp = strtemp + space;
			}
		}
		return strtemp;

	}

	/*
	 在字符串数组中添加空格。使得数组中的每个字符串的总长度等于整形数组中对应的指定的长度。
	 传入参数：
	 String[] ----要处理的字符串数组。
	 int[] ---字符串加空格后的长度组成的整形数组。长度以中文字符为标准。和字符串一一对应。
	 bool ----为真，则输入的长度以中文字符为标准。为假，则以英文字符为标准。
	 返回参数：
	 String[] ----处理完的字符串数组。
	 如果字符串数组和整形数组不匹配，返回null;
	 如果字符串的长度比指定的长度长，返回该串;
	 */

	public String[] addspace(String[] strarray, int[] len, boolean bool) {

		int l = len.length;
		String[] str1 = new String[l];
		if( strarray.length != len.length )
			return null;
		else
			for(int i = 0; i < strarray.length; i++) {
				str1[i] = addspace(strarray[i], len[i], bool);
			}
		return str1;
	}

	/*
	 返回每个分隔符区间中含有指定字符的个数，返回整形数组。默认分隔区间为<u>,</u>
	 传入参数：
	 String ----要处理的有分隔符的字符串
	 char ----指定的字符。
	 返回参数：
	 int[]-----整形数组，数组中的每个int为分隔符区间中的值。按照分隔符区间的顺序排列。
	 */

	public int[] getintarray(String str, char c) {

		int k = 0;
		int j = 0;
		int l = 0;
		int m = 0;
		String strtemp = "";
		boolean b = false;
		for(int i = 0; i < str.length(); i++) {
			if( i < (str.length() - 4) && str.substring(i, i + 3).equals("<u>") )
				l = l + 1;
		}

		int[] intarray = new int[l];

		for(int i = 0; i < str.length(); i++) {
			if( i <= (str.length() - 3) && str.substring(i, i + 3).equals("<u>") ) {
				i = i + 2;
				j = i + 1;
				b = true;
			}
			else if( i <= (str.length() - 4) && str.substring(i, i + 4).equals("</u>") ) {
				intarray[m] = k;
				m++;
				k = 0;
				j = 0;
				i = i + 3;
				b = false;
			}
			else {
				if( b ) {
					if( str.charAt(i) == c )
						if( str.charAt(i) < 128 )
							k = k + 1;
						else
							k = k + 2;
				}
			}
			////Debug.info(logger,"i="+i+"char="+str.charAt(i));
			////Debug.info(logger,"k="+k+"j"+j);
			////Debug.info(logger,strtemp);
			strtemp = "";
		}
		return intarray;
	}

	/*
	 返回每个分隔符区间中含有指定字符的个数，返回整形数组。
	 传入参数：
	 String 
	 str----要处理的有分隔符的字符串
	 str1----分隔区间开始标志
	 str2----分隔区间结束标志。
	 str1不能等于str2.如果相等，返回null;
	 char ----指定的字符。
	 返回参数：
	 int[]-----整形数组，数组中的每个int为分隔符区间中的值。按照分隔符区间的顺序排列。
	 */

	public int[] getintarray(String str, char c, String str1, String str2) {

		int k = 0;
		int j = 0;
		int m = 0;
		int l = 0;
		if( str1 == str2 )
			return null;
		int l1 = str1.length();
		int l2 = str2.length();
		////Debug.info(logger,l1);
		////Debug.info(logger,l2);
		String strtemp = "";
		boolean b = false;
		for(int i = 0; i < str.length(); i++) {
			if( i < (str.length() - 4) && str.substring(i, i + 3).equals("<u>") )
				l = l + 1;
		}

		int[] intarray = new int[l];
		for(int i = 0; i < str.length(); i++) {
			if( i <= (str.length() - l1) && str.substring(i, i + l1).equals(str1) ) {
				i = i + 2;
				j = i + 1;
				b = true;
			}
			else if( i <= (str.length() - l2) && str.substring(i, i + l2).equals(str2) ) {
				strtemp = str.substring(j, j + k);
				intarray[m] = Integer.parseInt(strtemp);
				m++;
				k = 0;
				j = 0;
				i = i + 3;
				b = false;
			}
			else {
				if( b ) {
					if( str.charAt(i) == c )
						if( str.charAt(i) < 128 )
							k = k + 1;
						else
							k = k + 2;
				}
			}
			////Debug.info(logger,"i="+i+"char="+str.charAt(i));
			////Debug.info(logger,"k="+k+"j"+j);
			////Debug.info(logger,strtemp);
			strtemp = "";
		}
		return intarray;
	}

	/*
	 取得分隔符区间中的所有的数字，返回整形数组。
	 传入参数：
	 String ----要处理的有分隔区间的字符串
	 boolean------为真，分隔区间为数字，为假，计算分隔区间所有字符的个数。
	 返回参数：
	 int[]-----整形数组，数组中的每个int为分隔符区间中的值。按照分隔符区间的顺序排列。
	 */

	public int[] getintarray(String str, boolean bool) {

		int k = 0;
		int j = 0;
		int l = 0;
		int m = 0;
		String strtemp = "";
		boolean b = false;
		for(int i = 0; i < str.length(); i++) {
			if( i <= (str.length() - 4) && str.substring(i, i + 3).equals("<u>") )
				l = l + 1;
		}

		int[] intarray = new int[l];

		for(int i = 0; i < str.length(); i++) {
			if( i <= (str.length() - 3) && str.substring(i, i + 3).equals("<u>") ) {
				i = i + 2;
				j = i + 1;
				b = true;
			}
			else if( i <= (str.length() - 4) && str.substring(i, i + 4).equals("</u>") ) {
				if( bool ) {
					strtemp = str.substring(j, j + k);
					intarray[m] = Integer.parseInt(strtemp);
				}
				else
					intarray[m] = k;

				m++;
				k = 0;
				j = 0;
				i = i + 3;
				b = false;
			}
			else {
				if( b )
					if( str.charAt(i) < 128 )
						k = k + 1;
					else
						k = k + 2;
			}
			////Debug.info(logger,"i="+i+"char="+str.charAt(i));
			////Debug.info(logger,"k="+k+"j"+j);
			////Debug.info(logger,strtemp);
			strtemp = "";
		}
		return intarray;
	}

	/*
	 取得分隔符区间中的所有的数字，返回整形数组。分隔区间为<u>和</u>之间。
	 传入参数：
	 String ----要处理的有分隔符的字符串
	 int ----字符串中分隔区间的个数。
	 返回参数：
	 int[]-----整形数组，数组中的每个int为分隔符区间中的值。按照分隔符区间的顺序排列。
	 如果传入的数组的个数小于实际分隔区间中的数字的个数，抛出异常，返回null;
	 */

	public int[] getintarray(String str, int len) {

		int k = 0;
		int j = 0;
		int m = 0;
		String strtemp = "";
		boolean b = false;
		int[] intarray = new int[len];
		try {
			for(int i = 0; i < str.length(); i++) {
				if( i <= (str.length() - 3) && str.substring(i, i + 3).equals("<u>") ) {
					i = i + 2;
					j = i + 1;
					b = true;
				}
				else if( i <= (str.length() - 4) && str.substring(i, i + 4).equals("</u>") ) {
					strtemp = str.substring(j, j + k);
					intarray[m] = Integer.parseInt(strtemp);
					m++;
					k = 0;
					j = 0;
					i = i + 3;
					b = false;
				}
				else {
					if( b )
						if( str.charAt(i) < 128 )
							k = k + 1;
						else
							k = k + 2;
				}
				////Debug.info(logger,"i="+i+"char="+str.charAt(i));
				////Debug.info(logger,"k="+k+"j"+j);
				////Debug.info(logger,strtemp);
				strtemp = "";
			}
			return intarray;

		}
		catch(Exception e) {
			////Debug.info(logger,"error");
			return null;
		}
	}

	/*
	 取得分隔符区间中的所有的数字，返回整形数组。
	 传入参数：
	 String 
	 str----要处理的有分隔符的字符串
	 str1-------分隔开始标志。
	 str2-------分隔结束标志。不能等于str1.
	 返回参数：
	 int[]-----整形数组，数组中的每个int为分隔符区间中的值。按照分隔符区间的顺序排列。
	 如果传入的数组的个数小于实际分隔区间中的数字的个数，抛出异常，返回null;
	 如果传入的分隔区间str1和str2相等，则返回null.
	 */

	public int[] getintarray(String str, String str1, String str2) {

		int k = 0;
		int j = 0;
		int m = 0;
		int l = 0;
		if( str1 == str2 )
			return null;
		int l1 = str1.length();
		int l2 = str2.length();
		////Debug.info(logger,l1);
		////Debug.info(logger,l2);
		String strtemp = "";
		boolean b = false;
		for(int i = 0; i < str.length(); i++) {
			if( i < (str.length() - 4) && str.substring(i, i + 3).equals("<u>") )
				l = l + 1;
		}

		int[] intarray = new int[l];
		for(int i = 0; i < str.length(); i++) {
			if( i <= (str.length() - l1) && str.substring(i, i + l1).equals(str1) ) {
				i = i + 2;
				j = i + 1;
				b = true;
			}
			else if( i <= (str.length() - l2) && str.substring(i, i + l2).equals(str2) ) {
				strtemp = str.substring(j, j + k);
				intarray[m] = Integer.parseInt(strtemp);
				m++;
				k = 0;
				j = 0;
				i = i + 3;
				b = false;
			}
			else {
				if( b )
					if( str.charAt(i) < 128 )
						k = k + 1;
					else
						k = k + 2;
			}
			////Debug.info(logger,"i="+i+"char="+str.charAt(i));
			////Debug.info(logger,"k="+k+"j"+j);
			////Debug.info(logger,strtemp);
			strtemp = "";
		}
		return intarray;

	}

	/*
	 取得分隔符区间中的所有的数字，返回整形数组。
	 传入参数：
	 String 
	 str----要处理的有分隔符的字符串
	 str1-------分隔开始标志。
	 str2-------分隔结束标志。不能等于str1.
	 int  ----字符串中分隔区间的个数。
	 返回参数：
	 int[]-----整形数组，数组中的每个int为分隔符区间中的值。按照分隔符区间的顺序排列。
	 如果传入的数组的个数小于实际分隔区间中的数字的个数，抛出异常，返回null;
	 如果传入的分隔区间str1和str2相等，则返回null.
	 */

	public int[] getintarray(String str, int len, String str1, String str2) {

		int k = 0;
		int j = 0;
		int m = 0;
		if( str1 == str2 )
			return null;
		int l1 = str1.length();
		int l2 = str2.length();
		////Debug.info(logger,l1);
		////Debug.info(logger,l2);
		String strtemp = "";
		boolean b = false;
		int[] intarray = new int[len];
		try {
			for(int i = 0; i < str.length(); i++) {
				if( i <= (str.length() - l1) && str.substring(i, i + l1).equals(str1) ) {
					i = i + 2;
					j = i + 1;
					b = true;
				}
				else if( i <= (str.length() - l2) && str.substring(i, i + l2).equals(str2) ) {
					strtemp = str.substring(j, j + k);
					intarray[m] = Integer.parseInt(strtemp);
					m++;
					k = 0;
					j = 0;
					i = i + 3;
					b = false;
				}
				else {
					if( b )
						if( str.charAt(i) < 128 )
							k = k + 1;
						else
							k = k + 2;
				}
				////Debug.info(logger,"i="+i+"char="+str.charAt(i));
				////Debug.info(logger,"k="+k+"j"+j);
				////Debug.info(logger,strtemp);
				strtemp = "";
			}
			return intarray;

		}
		catch(Exception e) {
			////Debug.info(logger,"error");
			return null;
		}
	}

	/*
	 取得字符串的长度，中文字符的长度为2，Asc码字符的长度为1；
	 传入参数：
	 String ----要处理的字符串
	 返回参数：
	 int -----字符串的长度。
	 */

	public int strlength(String str) {

		int k = 0;
		for(int i = 0; i < str.length(); i++) {
			if( str.charAt(i) > 128 )
				k = k + 2;
			else
				k = k + 1;
		}
		return k;
	}

	public static void main(String args[]) {

		OutControl oc = new OutControl();
		/*
		 String str11="dacea呵";
		 //Debug.info(logger,str1);
		 //Debug.info(logger,change(str1,4));
		 //Debug.info(logger,addspace(str11,4));
		 */

		/* 
		 String[] str1 ={"daedfase","fdeaf","fdafe","ddfdefde","fdafde"};
		 int [] len ={5,5,5,5,5};
		 String [] str2 =new String [5];
		 str2 =addspace (str1,len);
		 for (int i=0;i<len.length ;i++)
		 {
		 //Debug.info(logger,len[i]);
		 //Debug.info(logger,str2[i]);
		 }
		 */

		/*
		 String str1 ="<u>2</u><u>55</u>";
		 //Debug.info(logger,str1.length());
		 int[] ia=new int [2];
		 ia =getintarray(str1,false);
		 for(int i=0;i<2;i++)
		 //Debug.info(logger,ia[i]);
		 */

		/*
		 String str1 ="<u>2</u><u>55</u>";
		 //Debug.info(logger,str1.length());
		 int[] ia=new int [2];
		 ia =getintarray(str1,2,"<u>","</u>");
		 try
		 {
		 for(int i=0;i<2;i++)
		 //Debug.info(logger,ia[i]);	
		 }
		 catch(Exception e) 
		 {
		 //Debug.info(logger,"error");
		 }
		 */
		/*
		 String str1 ="<u> 呵呵 </u><u>  呵   </u>";
		 //Debug.info(logger,str1.length());
		 int[] ia=new int [2];
		 ia =getintarray(str1,'呵');
		 try
		 {
		 for(int i=0;i<2;i++)
		 //Debug.info(logger,ia[i]);	
		 }
		 catch(Exception e) 
		 {
		 //Debug.info(logger,"error");
		 }
		 */

		/*
		 String str1 ="<u> aa </u><u>  aa   </u>";
		 //Debug.info(logger,str1.length());
		 int[] ia=new int [2];
		 ia =oc.getintarray(str1,'a');
		 try
		 {
		 for(int i=0;i<2;i++)
		 //Debug.info(logger,ia[i]);	
		 }
		 catch(Exception e) 
		 {
		 //Debug.info(logger,"error");
		 }
		 */

		////Debug.info(logger,oc.formatnumber("20020101"));
		////Debug.info(logger,font1+size+color+face+style+font2+font3);
		////Debug.info(logger,oc.formatflag("2002",10));
		////Debug.info(logger,oc.formatflag("（购房人专用）",30));
		////Debug.info(logger,oc.formattitle("[补 打]",10));
		////Debug.info(logger,oc.formatadtitle("（购房人专用）",30));
		////Debug.info(logger,oc.formattail("宝山区","房地局",12));
		////Debug.info(logger,oc.formatdate("20020202",14));
		//String str1 ="<u> aa </u>cccccc<u>  aa   </u>";
		//String[] astr ={"bb","bbb"};
		////Debug.info(logger,oc.addString(str1,astr));
		String str1 = "经审核，你购买的<u>6</u>区（县）<u>6</u>房屋，建筑面积<u>6</u>平方米";
		String str11 = "经审核，你购买的<u>aaa</u>区（县）<u>bbb</u>房屋，建筑面积<u>ccc</u>平方米（其中车库面积<u>ddd</u>平方米），应交纳维修基金金额为人民币（大写)<u>eee</u>￥<u>fff</u>。请于<u>ggg</u>年<u>hhh</u>月<u>iii</u>日前将维修基金交至<u>jjj</u>银行。（应交纳维修基金计算公式为<u>kkk</u>元/ 平方米×<u>lll</u>平方米）";
		String[] astr = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l" };
		String[] astr1 = { "a", "b", "c" };
		//int[] ia=oc.getintarray(str1,false);
		//for(int i=0;i<ia.length;i++)
		////Debug.info(logger,ia[i]);
		String str2 = oc.formatcontent(str1, 10, astr1, true);
		//String str2=oc.addString(str1,astr1);
		Debug.info(logger,str2);

	}
}
