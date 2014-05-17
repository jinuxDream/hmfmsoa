package hmfms.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 条形码生成实用类，这个类提供了生成条形码(Interleaved 2-of-5)的方法
 * 检验是否全是数字;奇偶标志位(奇数--在左侧补零,偶数);生成hmtl串</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 下午12:35:38</p>
 * @author 产品开发部
 * @version 2.0
 * VarBarCode
 */
public class VarBarCode extends AbstractUtil {
	private static final Log logger = LogFactory.getLog(VarBarCode.class);
	private String sBarCode = "";
	public String sHtml = "";
	public String sErrMsg = "";
	public boolean bOddNum = false;//奇数标志

	//对应错位25码,对应顺序:0~9
	private String[] sBC = { "00110", "10001", "01001", "11000", "00101", "10100", "01100", "00011", "10010", "01010" };
	private int WIDTH = 2;
	private int HEIGHT = 35;
	private String BIMAGE = "../../../images/black.gif";
	private String WIMAGE = "../../../images/white.gif";

	//<td height="35"><img src="black.gif" width="2" height="35"></td>

	/**
	 * 设置需要转换成条形码的源码
	 * @param sBarCode 需要转换成条形码的字符串
	 * @return void
	 */
	public void setsBarCode(String sBarCode) {

		if( ObjectUtil.isEmpty(sBarCode) )
			throw new IllegalArgumentException("需转换为条码的信息不能为空！");
		this.sBarCode = sBarCode;
	}

	/**
	 * 得到html串
	 * @return String
	 */
	public String getsHtml() {

		return sHtml;
	}

	/**
	 * 得到错误信息
	 * @return String
	 */
	public String getsErrMsg() {

		return sErrMsg;
	}

	/**
	 * 检验是否全是数字
	 * @return boolean
	 */
	private boolean check() {

		int iLength = 0;

		iLength = sBarCode.length();

		if( iLength == 0 )//判断不为空
		{
			sErrMsg = "为空!";
			return false;
		}
		else if( iLength % 2 != 0 )//当字符是奇数个时，在左侧补0变为偶数
		{
			bOddNum = true;
			sBarCode = "0" + sBarCode;
			iLength++;
		}
		if( iLength < 6 )//当字串length小于6位时，在左侧补0补足6位
		{
			for(int m = iLength; m < 6; m++) {
				sBarCode = "0" + sBarCode;
			}
		}

		for(int i = 0; i < iLength; i++)//检查是否全是数字
		{
			if( !Character.isDigit(sBarCode.charAt(i)) ) {
				sErrMsg = "必须全为数字!";
				return false;
			}
		}

		return true;
	}

	/**
	 * 转换成条形码
	 * @return boolean
	 */
	public boolean varHtml() {

		if( check() ) {
			sHtml = "";
			int m = 0;
			int n = 0;
			String sM = "";
			String sN = "";
			String sMm = "";
			String sNn = "";

			String sM0 = "<td height=" + String.valueOf(HEIGHT) + "><img src=" + BIMAGE + " width=" + String.valueOf(WIDTH) + " height="
							+ String.valueOf(HEIGHT) + "></td>";
			String sM1 = "<td height=" + String.valueOf(HEIGHT) + "><img src=" + BIMAGE + " width=" + String.valueOf(WIDTH * 2) + " height="
							+ String.valueOf(HEIGHT) + "></td>";
			String sN0 = "<td height=" + String.valueOf(HEIGHT) + "><img src=" + WIMAGE + " width=" + String.valueOf(WIDTH) + " height="
							+ String.valueOf(HEIGHT) + "></td>";
			String sN1 = "<td height=" + String.valueOf(HEIGHT) + "><img src=" + WIMAGE + " width=" + String.valueOf(WIDTH * 2) + " height="
							+ String.valueOf(HEIGHT) + "></td>";

			//生成html代码
			sHtml += "<tr>";
			sHtml += sM0;
			sHtml += sN0;
			sHtml += sM0;
			sHtml += sN0;
			for(int i = 0; i < sBarCode.length(); i = i + 2) {
				m = Integer.parseInt(sBarCode.substring(i, i + 1));
				sM = sBC[m];
				n = Integer.parseInt(sBarCode.substring(i + 1, i + 2));
				sN = sBC[n];

				for(int j = 0; j < 5; j++) {
					sMm = sM.substring(j, j + 1);
					sNn = sN.substring(j, j + 1);
					//奇数位
					if( sMm.equals("0") ) {
						sHtml += sM0;
					}
					else if( sMm.equals("1") ) {
						sHtml += sM1;
					}
					//偶数位
					if( sNn.equals("0") ) {
						sHtml += sN0;
					}
					else if( sNn.equals("1") ) {
						sHtml += sN1;
					}
				}
			}
			sHtml += sM1;
			sHtml += sN0;
			sHtml += sM0;
			sHtml += "</tr>";
		}
		else {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {

		String sTemp = "1";
		String sHtml = "";
		VarBarCode myBC = new VarBarCode();
		myBC.setsBarCode(sTemp);
		if( myBC.varHtml() ) {
			sHtml = myBC.getsHtml();
		}
		else {
			sHtml = "ERROR";
		}
		Debug.info(logger,"sHtml:" + sHtml);
	}
}
