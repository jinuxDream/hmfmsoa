package hmfms.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>��    ��: ���Ŀ��</p>
 * <p>��    ��: ���ڴ���ʵ����</p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2010-12-13 ����11:54:42</p>
 * @author ��Ʒ������
 * @version 2.0
 * DateUtil
 */
public class DateUtil extends AbstractUtil {
	private static final Log logger = LogFactory.getLog(DateUtil.class);
	//private static String DATE_MODE = SysParaUtil.getParaValueByName("DATE_MODE","0");

	/**
	 * ��ȡyyyyMMdd��ʽ��ϵͳ���ڣ�Ĭ��ȡ��������ǰ���ڣ���ϵͳ�������������˵�ǰ�����գ�����DATE_MODE��������Ϊ1����ȡ��������
	 * @return
	 */
	public static String getSysDate() {

//		if( "1".equals(DATE_MODE) ) {
//			try {
//				String retDate = SysParaUtil.getParaValueByName("WORK_DATE");
//				Debug.debug(logger,"###ȡϵͳ��������DATE_MODE=" + DATE_MODE);
//				return retDate;
//			}
//			catch(Exception e) {
//				Debug.debug(logger,"###ȡ����������DATE_MODE=" + DATE_MODE);
//				SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
//				String s = simpledateformat.format(Calendar.getInstance().getTime());
//				return s;
//			}
//		}
//		else {
			/*Ĭ��ȡ����������*/
//			Debug.debug(logger,"###ȡ����������DATE_MODE=" + DATE_MODE);
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
			String s = simpledateformat.format(Calendar.getInstance().getTime());
			return s;
//		}
	}

	/**
	 * ��ȡkkmmss��ʽ��ϵͳʱ��
	 * @return
	 */
	public static String getSysTime() {

		SimpleDateFormat simpledateformat = new SimpleDateFormat("kkmmss");
		String s = simpledateformat.format(Calendar.getInstance().getTime());
		return s;
	}

	/**
	 * �жϲ����ַ����Ƿ�ΪyyyyMMdd��ʽ������
	 * @param date
	 * @return
	 */
	public static boolean isDate(String date) {

		if( date.length() != 8 )
			return false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			dateFormat.parse(date);
		}
		catch(Exception e) {
			return false;
		}

		String year = date.substring(0, 4);
		int y = Integer.parseInt(year);
		if( y < 1975 || y > 2100 )
			return false;

		String month = date.substring(4, 6);
		int m = Integer.parseInt(month);
		if( m < 1 || m > 12 )
			return false;

		String day = date.substring(6);
		int d = Integer.parseInt(day);
		if( d < 1 )
			return false;

		String lastday = getMonthLastDate(date);
		int ld = Integer.parseInt(lastday.substring(6));
		if( d > ld )
			return false;//���ڱ������һ�죬���ؼ�

		return true;
	}

	/**
	 * �жϲ����ַ����Ƿ�Ϊkkmmss��ʽ��ʱ��
	 * @param time
	 * @return
	 */
	public static boolean isTime(String time) {

		if( time.length() != 6 )
			return false;

		String hour = time.substring(0, 2);
		int h = Integer.parseInt(hour);
		if( h < 0 || h > 24 )
			return false;

		String miet = time.substring(2, 4);
		int m = Integer.parseInt(miet);
		if( m < 0 || m > 59 )
			return false;
		if( h == 24 && m != 0 )
			return false;

		String sd = time.substring(4);
		int s = Integer.parseInt(sd);
		if( s < 0 || s > 59 )
			return false;
		if( h == 24 && s != 0 )
			return false;

		return true;
	}

	/**
	 * ��YYYY-MM-DD��ʽ�����ַ�����ʽ����yyyyMMdd��ʽ
	 * @param date YYYY-MM-DD��ʽ�����ַ���
	 * @return
	 */
	public static String format2DB(String date) {

		if( ObjectUtil.isEmpty(date) )
			return "";
		return date.replaceAll("-", "");
	}

	/**
	 * ��kk:mm:ss��ʽʱ���ַ�����ʽ����kkmmss��ʽ
	 * @param time kk:mm:ss��ʽʱ���ַ���
	 * @return
	 */
	public static String formatTime2DB(String time) {

		if( ObjectUtil.isEmpty(time) )
			return "";
		return time.replaceAll(":", "");
	}
	
	/**
	 * ��yyyyMMdd��ʽ�����ַ�����ʽ����YYYY-MM-DD��ʽ
	 * @param date yyyyMMdd��ʽ�����ַ���
	 * @return
	 */
	public static String formatFromDB(String date) {

		if( ObjectUtil.isEmpty(date) )
			return "";
		StringBuffer buf = new StringBuffer(date);
		return buf.insert(6, '-').insert(4, '-').toString();
	}

	/**
	 * ��kkmmss��ʽʱ���ַ�����ʽ����kk:mm:ss��ʽ
	 * @param time kkmmss��ʽʱ���ַ���
	 * @return
	 */
	public static String formatTimeFromDB(String time) {

		if( ObjectUtil.isEmpty(time) )
			return "";
		StringBuffer buf = new StringBuffer(time);
		return buf.insert(2, ':').insert(5, ':').toString();
	}

	/**
	 * ��yyyyMMdd��ʽ�����ַ�����ʽ����YYYY-MM-DD��ʽ,��kkmmss��ʽʱ���ַ�����ʽ����kk:mm:ss��ʽ,�м�ӿո��ƴ��
	 * @param date yyyyMMdd��ʽ�����ַ���
	 * @param time kkmmss��ʽʱ���ַ���
	 * @return
	 */
	public static String formatDateTimeFromDB(String date, String time) {
		return DateUtil.formatFromDB(date)+" "+DateUtil.formatTimeFromDB(time);
//		if( ObjectUtil.isEmpty(date) || date.length() < 8 )
//			date = "        ";
//		if( ObjectUtil.isEmpty(time) || date.length() < 6 )
//			time = "      ";
//		StringBuffer buf = new StringBuffer(date);
//		buf.insert(6, '-').insert(4, '-');
//		StringBuffer buf1 = new StringBuffer(time);
//		buf1.insert(2, ':').insert(5, ':');
//		return buf.toString() + " " + buf1.toString();
	}
	
	/**
	 * ��yyyyMMddkkmmss��ʽ�����ַ�����ʽ����YYYY-MM-DD kk:mm:ss��ʽ,�м�ӿո��ƴ��
	 * @param date time yyyyMMddkkmmss��ʽ�����ַ���
	 * @return
	 */
	public static String formatDateTimeFromDB(String datetime) {
		if( ObjectUtil.isEmpty(datetime) || datetime.length() < 14 )
			return datetime;
		String date = datetime.substring(0, 8);
		String time = datetime.substring(8, 14);
		
		return DateUtil.formatFromDB(date)+" "+DateUtil.formatTimeFromDB(time);
	}

	/**
	 * ���ݵ�ǰ��ݻ�ȡȡ�����б���ݼ��ϣ���2005�꿪ʼ����ǰ��ݺ�3��
	 * @return
	 */
	public static List getYear() {

		List vRet = new ArrayList();
		int iCurrYear = Integer.parseInt((getSysDate()).substring(0, 4));
		for(int i = 0; i <= iCurrYear - 2005 + 3; i++) {
			vRet.add(Integer.toString(2005 + i));
		}
		return vRet;
	}

	/**
	 * ���ݸ�������ݺͼ�����ȡ��Ӧ�ļ�������
	 * @param ogYear ���
	 * @param iValue ����
	 * @return
	 */
	public static String getQuarterFirstDate(String ogYear, int iValue) {

		String strRetDate = "";
		switch(iValue) {
		case 1:
			strRetDate = ogYear + "0101";
			break;
		case 2:
			strRetDate = ogYear + "0401";
			break;
		case 3:
			strRetDate = ogYear + "0701";
			break;
		case 4:
			strRetDate = ogYear + "1001";
			break;
		}

		return strRetDate;
	}

	/**
	 * ���ݸ�������ݺͼ�����ȡ��Ӧ�ļ�ĩ����
	 * @param ogYear ���
	 * @param iValue ����
	 * @return
	 */
	public static String getQuarterLastDate(String ogYear, int iValue) {

		String strRetDate = "";
		switch(iValue) {
		case 1:
			strRetDate = ogYear + "0331";
			break;
		case 2:
			strRetDate = ogYear + "0630";
			break;
		case 3:
			strRetDate = ogYear + "0930";
			break;
		case 4:
			strRetDate = ogYear + "1231";
			break;
		}

		return strRetDate;
	}

	/**
	 * ���ݸ�������ݺͰ������ȡ��Ӧ�İ��꿪ʼ����
	 * @param ogYear ���
	 * @param iValue ����� 1���ϰ��꣬2���°���
	 * @return
	 */
	public static String getHalfYearFirstDate(String ogYear, int iValue) {

		String strRetDate = "";
		switch(iValue) {
		case 1:
			strRetDate = ogYear + "0101";
			break;
		case 2:
			strRetDate = ogYear + "0701";
			break;
		}

		return strRetDate;
	}

	/**
	 * ���ݸ�������ݺͰ������ȡ��Ӧ�İ����������
	 * @param ogYear ���
	 * @param iValue ����� 1���ϰ��꣬2���°���
	 * @return
	 */
	public static String getHalfYearLastDate(String ogYear, int iValue) {

		String strRetDate = "";
		switch(iValue) {
		case 1:
			strRetDate = ogYear + "0630";
			break;
		case 2:
			strRetDate = ogYear + "1231";
			break;
		}

		return strRetDate;
	}

	/**
	 * ���ݲ���ogdate���õ�ogdate����µ����һ������ڣ����磺
	 * getLastDate("200308")=20030831
	 * ����ogdate������6λ��yyyyMM����8λ��yyyyMMdd��
	 * @param ogdate
	 * @return
	 */
	public static String getMonthLastDate(String ogdate) {

		if( ogdate.length() == 6 )
			ogdate = ogdate + "01";
		else {//��ogdate���ǰ6λ��01�Ĵ�����20030805-->20030801
			ogdate = ogdate.substring(0, 6) + "01";
		}
		ogdate = getNextDateByMonth(ogdate, 1);
		ogdate = getNextDateByNum(ogdate, -1);
		return ogdate;
	}

	/**
	 * ���ݸ�������ݺͰ������һ�������
	 * @param ogYear ���
	 * @param iValue ����� 1���ϰ��꣬2���°���
	 * @return yyyyH��ʽ
	 */
	public static String getNextHalfYear(String ogYear, int iValue) {
		String strRetDate = "";
		switch(iValue) {
		case 1:
			strRetDate = ogYear + "2";
			break;
		case 2:
			strRetDate = (Integer.parseInt(ogYear)+1) + "1";
			break;
		}
		return strRetDate;
	}

	/**
	 * ���ݸ�������ݺͼ�����һ������
	 * @param ogYear ���
	 * @param iValue ����
	 * @return yyyyQ��ʽ
	 */
	public static String getNextQuarter(String ogYear, int iValue) {
		String strRetDate = "";
		switch(iValue) {
		case 1:
			strRetDate = ogYear + "2";
			break;
		case 2:
			strRetDate = ogYear + "3";
			break;
		case 3:
			strRetDate = ogYear + "4";
			break;
		case 4:
			strRetDate = (Integer.parseInt(ogYear)+1) + "1";
			break;
		}
		return strRetDate;
	}

	/**
	 * ���ݸ�������ݺ��·���һ���·�
	 * @param ogYear ���
	 * @param iValue �·�
	 * @return yyyyMM��ʽ
	 */
	public static String getNextMonth(String ogYear, int iValue) {
		String strRetDate = "";
		if(iValue==12){
			strRetDate = (Integer.parseInt(ogYear)+1) + "01";
		}
		else if(iValue==11){
			strRetDate = ogYear + "12";
		}
		else if(iValue==10){
			strRetDate = ogYear + "11";
		}
		else if(iValue==9){
			strRetDate = ogYear + "10";
		}
		else{
			strRetDate = ogYear + "0" + (iValue+1);
		}
		return strRetDate;
	}

	/**
	 * ���ݲ���ogdate���õ�ogdate����µ����һ�������գ����磺
	 * getMonthLastDateNoWeekend("200308")=20030829
	 * ����ogdate������6λ��yyyyMM����8λ��yyyyMMdd��
	 * @param ogdate
	 * @return 
	 */
	public static String getMonthLastWorkDate(String ogdate) {

		String sDate = getMonthLastDate(ogdate);
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		java.util.Date date = simpledateformat.parse(sDate, new ParsePosition(0));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int iWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if( iWeek == 7 )
			sDate = getNextDateByNum(sDate, -1);
		if( iWeek == 1 )
			sDate = getNextDateByNum(sDate, -2);
		//calendar.add(2, i);
		date = calendar.getTime();
		return sDate;
	}

	/**
	 * �õ���������+i���Ժ������
	 * @param s yyyyMMdd��ʽ����
	 * @param i �����Ǹ���
	 * @return
	 */
	public static String getNextDateByNum(String s, int i) {

		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		java.util.Date date = simpledateformat.parse(s, new ParsePosition(0));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(5, i);
		date = calendar.getTime();
		s = simpledateformat.format(date);
		return s;
	}

	/**
	 * �õ���������+i���Ժ������
	 * @param s yyyyMMdd��ʽ����
	 * @param i �����Ǹ���
	 * @return
	 */
	public static String getNextDateByMonth(String s, int i) {

		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		java.util.Date date = simpledateformat.parse(s, new ParsePosition(0));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(2, i);
		date = calendar.getTime();
		s = simpledateformat.format(date);
		return s;
	}

	/**
	 * �õ���������+i���Ժ������
	 * @param s yyyyMMdd��ʽ����
	 * @param i �����Ǹ���
	 * @return
	 */
	public static String getNextDateByYear(String s, int i) {

		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		java.util.Date date = simpledateformat.parse(s, new ParsePosition(0));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, i);
		date = calendar.getTime();
		s = simpledateformat.format(date);
		return s;
	}

	/**
	 * �õ�"yyyy��MM��dd��"��ʽ����
	 * @param fdate yyyyMMdd��ʽ����
	 * @return
	 */
	public static String getCNDate(String fdate) {

		if( ObjectUtil.isEmpty(fdate) )
			return "";
		String cur_date = fdate;
		cur_date = cur_date.substring(0, 4) + "��" + cur_date.substring(4, 6) + "��" + cur_date.substring(6) + "��";
		return cur_date;
	}

	/**
	 * �õ�"kkʱmm��ss��"��ʽʱ��
	 * @param ftime kkmmss��ʽʱ��
	 * @return
	 */
	public static String getCNTime(String ftime) {

		String cur_time = ftime;
		cur_time = cur_time.substring(0, 2) + "ʱ" + cur_time.substring(2, 4) + "��" + cur_time.substring(4) + "��";
		return cur_time;
	}

	/**
	 * ��������������������
	 * @param startDate ��ʽ��yyyy-MM-dd
	 * @param endDate ��ʽ��yyyy-MM-dd
	 * @return ������������������
	 */
	public static int getDatePeriod(String startDate, String endDate) {

		String[] date1 = startDate.split("-");
		String[] date2 = endDate.split("-");

		GregorianCalendar gc1 = new GregorianCalendar(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2]));

		GregorianCalendar gc2 = new GregorianCalendar(Integer.parseInt(date2[0]), Integer.parseInt(date2[1]), Integer.parseInt(date2[2]));

		long longDate1 = gc1.getTimeInMillis();
		long longDate2 = gc2.getTimeInMillis();
		long period = longDate2 - longDate1;

		period /= 24 * 60 * 60 * 1000;

		return (int)period;
	}

	/**
	 * ��������������������
	 * @param startDate ��ʽ��yyyyMMdd
	 * @param endDate ��ʽ��yyyyMMdd
	 * @return ������������������
	 */
	public static int dateMargin(String startDate, String endDate) {

		String d1 = format2DB(startDate);
		String d2 = format2DB(endDate);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(df.parse(d1, new ParsePosition(0)));
		Date end = df.parse(d2, new ParsePosition(0));

		int margin = 0;
		int step = startDate.compareTo(endDate) > 0 ? -1 : 1;
		while( calendar.getTime().compareTo(end) != 0 ) {
			calendar.add(Calendar.DATE, step);
			margin += step;

		}
		return margin;

	}

	/**
	 *��������ʱ��Ĳ��λ���룩
	 *@param begin_dt ��ʽ��yyyyMMddkkmmss
	 *@param end_dt  ��ʽ��yyyyMMddkkmmss
	 *@return ��룩
	 */
	public static int getSecsDiff(String begin_dt, String end_dt) {

		if( begin_dt == null || end_dt == null )
			return 0;
		if( begin_dt.length() == 8 )
			begin_dt = begin_dt + "000000";
		if( begin_dt.length() == 6 )
			begin_dt = getSysDate() + begin_dt;
		if( end_dt.length() == 8 )
			end_dt = end_dt + "000000";
		if( end_dt.length() == 6 )
			end_dt = getSysDate() + end_dt;

		int iBYYYY = Integer.parseInt(begin_dt.substring(0, 4));
		int iBMM = Integer.parseInt(begin_dt.substring(4, 6));
		int iBDD = Integer.parseInt(begin_dt.substring(6, 8));
		int iBhh = Integer.parseInt(begin_dt.substring(8, 10));
		int iBmm = Integer.parseInt(begin_dt.substring(10, 12));
		int iBss = Integer.parseInt(begin_dt.substring(12, 14));
		int iEYYYY = Integer.parseInt(end_dt.substring(0, 4));
		int iEMM = Integer.parseInt(end_dt.substring(4, 6));
		int iEDD = Integer.parseInt(end_dt.substring(6, 8));
		int iEhh = Integer.parseInt(end_dt.substring(8, 10));
		int iEmm = Integer.parseInt(end_dt.substring(10, 12));
		int iEss = Integer.parseInt(end_dt.substring(12, 14));
		Calendar BeginDate = new GregorianCalendar(iBYYYY, iBMM, iBDD, iBhh, iBmm, iBss);
		Calendar EndDate = new GregorianCalendar(iEYYYY, iEMM, iEDD, iEhh, iEmm, iEss);
		long lBegin = BeginDate.getTime().getTime();
		long lEnd = EndDate.getTime().getTime();
		//long lDiff = (lEnd > lBegin) ? (lEnd - lBegin) : (lBegin - lEnd);
		long lDiff = lBegin - lEnd;
		BeginDate = null;
		EndDate = null;
		return (int)(lDiff / 1000);
	}

	/**
	 * �жϴ��������Ƿ�Ϊ�����գ�����������14λ��8λ��
	 * @param date ��ʽ��yyyyMMddkkmmss��yyyyMMdd
	 * @return
	 */
	public static boolean isWorkDay(String date) {

		if( date == null )
			return false;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date newDate = dateFormat.parse(date);
			calendar.setTime(newDate);
		}
		catch(Exception e) {
			Debug.exception(logger,e);
			return false;
		}
		if( Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK) || Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK) ) {
			return false;
		}
		return true;
	}

	/**
	 * �жϴ��������Ƿ�Ϊ����
	 * @param date ��ʽ��yyyyMMdd
	 * @return
	 */
	public static boolean isWeekEndFRIDAY(String date) {

		if( date == null )
			return false;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date newDate = dateFormat.parse(date);
			calendar.setTime(newDate);
		}
		catch(Exception e) {
			Debug.exception(logger,e);
			return false;
		}
		if( Calendar.FRIDAY == calendar.get(Calendar.DAY_OF_WEEK) ) {
			return true;
		}
		return false;
	}

	/**
	 * �жϴ��������Ƿ�Ϊ��һ
	 * @param date ��ʽ��yyyyMMdd
	 * @return
	 */
	public static boolean isWeekStartMONDAY(String date) {

		if( date == null )
			return false;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date newDate = dateFormat.parse(date);
			calendar.setTime(newDate);
		}
		catch(Exception e) {
			Debug.exception(logger,e);
			return false;
		}
		if( Calendar.MONDAY == calendar.get(Calendar.DAY_OF_WEEK) ) {
			return true;
		}
		return false;
	}

	
	public static String getDayOfWeek(String fdate) {
		if( fdate == null )
			return "";
		String[] weekDays = {"������", "����һ", "���ڶ�", "������", "������", "������", "������"};
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        
		try {
			Date newDate = dateFormat.parse(fdate);
			calendar.setTime(newDate);
		} catch (ParseException e) {
			Debug.exception(logger,e);
			return "";
		}
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    } 
	/**
	 * ȡ�������е���
	 * @param fdate ��ʽ��yyyyMMdd��yyyy-MM-dd
	 * @return
	 */
	public static String getYear(String fdate) {

		if( ObjectUtil.isEmpty(fdate) )
			return "";
		String cur_date = fdate.substring(0, 4);
		return cur_date;
	}

	/**
	 * ȡ�������е���
	 * @param fdate ��ʽ��yyyyMMdd��yyyy-MM-dd
	 * @return
	 */
	public static String getMonth(String fdate) {

		if( ObjectUtil.isEmpty(fdate) )
			return "";
		String cur_date = fdate.replaceAll("-", "");
		cur_date = cur_date.substring(4, 6);
		return cur_date;
	}

	/**
	 * ȡ�������е���
	 * @param fdate ��ʽ��yyyyMMdd��yyyy-MM-dd
	 * @return
	 */
	public static String getDay(String fdate) {

		if( ObjectUtil.isEmpty(fdate) )
			return "";
		String cur_date = fdate.replaceAll("-", "");
		cur_date = cur_date.substring(6);
		return cur_date;
	}

	/**
	 * �������ڷ��ؼ���
	 * @param fdate ��ʽ��yyyyMMdd��yyyy-MM-dd
	 * @return
	 */
	public static String getQuarter(String fdate) {

		if( ObjectUtil.isEmpty(fdate) )
			return "";
		String month = getMonth(fdate);
		String cur_date="";
		if("01".equals(month)||"02".equals(month)||"03".equals(month)){
			cur_date="1";
		}
		if("04".equals(month)||"05".equals(month)||"06".equals(month)){
			cur_date="2";
		}
		if("07".equals(month)||"08".equals(month)||"09".equals(month)){
			cur_date="3";
		}
		if("10".equals(month)||"11".equals(month)||"12".equals(month)){
			cur_date="4";
		}
		return cur_date;
	}

	/**
	 * �������ڷ��ؼ���
	 * @param fdate ��ʽ��yyyyMMdd��yyyy-MM-dd
	 * @return
	 */
	public static String getHalfYear(String fdate) {

		if( ObjectUtil.isEmpty(fdate) )
			return "";
		String month = getMonth(fdate);
		String cur_date="";
		if("01".equals(month)||"02".equals(month)||"03".equals(month)||"04".equals(month)||"05".equals(month)||"06".equals(month)){
			cur_date="1";
		}
		if("07".equals(month)||"08".equals(month)||"09".equals(month)||"10".equals(month)||"11".equals(month)||"12".equals(month)){
			cur_date="2";
		}
		return cur_date;
	}

	/**
	 * �ж������Ƿ���ϸ�ʽҪ��
	 * @param date
	 * @return
	 */
	public static boolean isInPattern(String date, String pattern) {

		if( date == null )
			return false;
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			dateFormat.parse(date);
			return true;
		}
		catch(Exception e) {
			//Debug.exception(logger,e);
			return false;
		}
	}

	/**
	 * ���������ַ������java.util.Date����
	 * @param String���͵�����
	 * @param �������ڵĸ�ʽ
	 * @return Date����
	 * */
	public static Date getDateByString(String dateByString, String pattern) throws Exception {

		Date date = null;
		if( dateByString == null || dateByString.trim().equals("") )
			return null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);

			date = format.parse(dateByString);
		}
		catch(Exception e) {
			String error = "��������ڸ�ʽ����ȷ��������" + pattern + "��ʽ������";
			throw new Exception(error, e);
		}
		return date;
	}

	/**
	 * ����java.util.Date������ָ����ʽ�������ַ���
	 * @param Date����
	 * @param ������ڵĸ�ʽ
	 * @return String���͵�����
	 * */
	public static String getStringByDate(Date date, String pattern) {

		if( date == null )
			return null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

    /**
     * ���ݹ������ڻ����һ��Ϣ��,��������ձ���Ϊ��Ϣ�շ����ϼ��Ƚ�Ϣ��
     * @param workDate yyyyMMdd��ʽ�����ַ���
     * @param cutday ��̯���ڣ�ͨ��Ϊ21
     * @return
     */
    public static String getLastIntDate(String workDate,int cutday){
    	String retDate="";
    	/**
    	 * ��Ϣ�£���ǰ����/((����ǰ�·ݣ�1��/3*3+3)*100+��̯��)*3+����ǰ�·ݣ�1��/3*3+3-3
    	 * */
    	int flag=(Integer.parseInt(getMonth(workDate))-1)/3*3+3;
    	int month=Integer.parseInt(getMonth(workDate)+getDay(workDate))	/((flag)*100+cutday)*3+flag-3;
    	int year=(month==0)?Integer.parseInt(getYear(workDate))-1:Integer.parseInt(getYear(workDate));
    	month=(month==0)?12:month;
    	retDate=year+StringUtil.fillString(new Integer(month).toString(), '0', 2)+"20";
    	return retDate;
    }
}
