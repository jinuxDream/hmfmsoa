package hmfms.base;
import org.apache.struts.action.ActionForm;


/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: </p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:19:01</p>
 * @author 产品开发部
 * @version 2.0
 * BaseForm
 */
public class BaseForm extends ActionForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID =  - 1135023873984348608L;
	private String method;
	public String getMethod()
	{
		return method;
	}
	public void setMethod(String method)
	{
		this.method = method;
	}
}