package hmfms.base;
import hmfms.util.Debug;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 被所有Manager类继承的超类</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:18:21</p>
 * @author 产品开发部
 * @version 2.0
 * BaseDeal
 */
public class BaseDeal {
	private static final Log logger = LogFactory.getLog(BaseDeal.class);
	public final static String EndDate4Null = "99991231";
	protected final static String EndTime4Null = "235959";
	public final static String StartDate4Null = "00000000";
	protected final static String StartTime4Null = "000000";
	
	protected static int PlatDB = getPlatDB();
	
	private static int getPlatDB()//物业平台的DB连接,用于数据迁移
	{
		Debug.info(logger, "using 数据移植 db conn!");
		return 2;
	}
}
