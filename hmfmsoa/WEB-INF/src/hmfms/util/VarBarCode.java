package hmfms.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>��    ��: ���Ŀ��</p>
 * <p>��    ��: ����������ʵ���࣬������ṩ������������(Interleaved 2-of-5)�ķ���
 * �����Ƿ�ȫ������;��ż��־λ(����--����ಹ��,ż��);����hmtl��</p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2010-12-13 ����12:35:38</p>
 * @author ��Ʒ������
 * @version 2.0
 * VarBarCode
 */
public class VarBarCode extends AbstractUtil {
	private static final Log logger = LogFactory.getLog(VarBarCode.class);
	private String sBarCode = "";
	public String sHtml = "";
	public String sErrMsg = "";
	public boolean bOddNum = false;//������־

	//��Ӧ��λ25��,��Ӧ˳��:0~9
	private String[] sBC = { "00110", "10001", "01001", "11000", "00101", "10100", "01100", "00011", "10010", "01010" };
	private int WIDTH = 2;
	private int HEIGHT = 35;
	private String BIMAGE = "../../../images/black.gif";
	private String WIMAGE = "../../../images/white.gif";

	//<td height="35"><img src="black.gif" width="2" height="35"></td>

	/**
	 * ������Ҫת�����������Դ��
	 * @param sBarCode ��Ҫת������������ַ���
	 * @return void
	 */
	public void setsBarCode(String sBarCode) {

		if( ObjectUtil.isEmpty(sBarCode) )
			throw new IllegalArgumentException("��ת��Ϊ�������Ϣ����Ϊ�գ�");
		this.sBarCode = sBarCode;
	}

	/**
	 * �õ�html��
	 * @return String
	 */
	public String getsHtml() {

		return sHtml;
	}

	/**
	 * �õ�������Ϣ
	 * @return String
	 */
	public String getsErrMsg() {

		return sErrMsg;
	}

	/**
	 * �����Ƿ�ȫ������
	 * @return boolean
	 */
	private boolean check() {

		int iLength = 0;

		iLength = sBarCode.length();

		if( iLength == 0 )//�жϲ�Ϊ��
		{
			sErrMsg = "Ϊ��!";
			return false;
		}
		else if( iLength % 2 != 0 )//���ַ���������ʱ������ಹ0��Ϊż��
		{
			bOddNum = true;
			sBarCode = "0" + sBarCode;
			iLength++;
		}
		if( iLength < 6 )//���ִ�lengthС��6λʱ������ಹ0����6λ
		{
			for(int m = iLength; m < 6; m++) {
				sBarCode = "0" + sBarCode;
			}
		}

		for(int i = 0; i < iLength; i++)//����Ƿ�ȫ������
		{
			if( !Character.isDigit(sBarCode.charAt(i)) ) {
				sErrMsg = "����ȫΪ����!";
				return false;
			}
		}

		return true;
	}

	/**
	 * ת����������
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

			//����html����
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
					//����λ
					if( sMm.equals("0") ) {
						sHtml += sM0;
					}
					else if( sMm.equals("1") ) {
						sHtml += sM1;
					}
					//ż��λ
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
