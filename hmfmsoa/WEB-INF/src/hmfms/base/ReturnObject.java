package hmfms.base;

/**
 * <p>��    ��: ���Ŀ��</p>
 * <p>��    ��: </p>
 * <p>��    Ȩ: Copyright (c) 2010</p>
 * <p>��    ˾: �Ϻ�������Ϣ�Ƽ����޹�˾</p>
 * <p>����ʱ��: 2010-12-13 ����11:22:43</p>
 * @author ��Ʒ������
 * @version 2.0
 * ReturnObject
 */
public class ReturnObject {

	private boolean success;
	private String message;
	private Object returnObject;

	/**
	 * @return Returns the returnObject.
	 */
	public Object getReturnObject() {

		return returnObject;
	}

	/**
	 * @param returnObject The returnObject to set.
	 */
	public void setReturnObject(Object returnObject) {

		this.returnObject = returnObject;
	}

	/**
	 * @return Returns the hasError.
	 */
	public boolean isSuccess() {

		return success;
	}

	/**
	 * @param hasError The hasError to set.
	 */
	public void setSuccess(boolean hasError) {

		this.success = hasError;
	}

	/**
	 * @return Returns the message.
	 */
	public String getMessage() {

		return message;
	}

	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {

		this.message = message;
	}
}