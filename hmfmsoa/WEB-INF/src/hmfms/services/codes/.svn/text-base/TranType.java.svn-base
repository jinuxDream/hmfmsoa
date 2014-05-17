package hmfms.services.codes;																														
import hmfms.services.exception.NotFoundException;                                    
import hmfms.util.Constants;                                                         
import java.util.*;                                                                  
import fd.commons.jdbc.Result;                                                       
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
/**Created by automatic  */												 
/**代码类型名：交易类型  */												 
public class TranType extends CodesItem {                                      
	private static final Log logger = LogFactory.getLog(TranType.class);                                                      
	private TranType(String code){super(CodesItem.tranType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.tranType,code);                         
	}                                                                                   
	public static String getValue(TranType code){	                                
		return CodesItem.getValue(CodesItem.tranType,code.toString());              
	}                                                                                   
	public static Result getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.tranType);                           
	}                                                                                   
	public static final TranType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.tranType);                         
		return map==null?null:(TranType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		Result rs = null;                                                                 
		try{rs=getCodeFromDB(CodesItem.tranType);}catch(NotFoundException e)        
		{if(Constants.DEV_DEBUG) Debug.exception(logger,e);return;}	                          
		Map map=new HashMap(rs.getRowCount());String temp=null;                           
		Map map2=new HashMap(rs.getRowCount());                                           
		for (int i=0; i<rs.getRowCount();i++){                                            
			temp=rs.getString(i,"ci_sp_code");                                              
			map.put(temp,rs.getString(i,"ci_sp_name"));                                     
			map2.put(temp,new TranType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.tranType,map);                                
		mapCodeObject.put(CodesItem.tranType,map2);		                              
	}                                                                                   
/////////////////////////////////////////////////////////////////////////////////		
	public int hashCode(){return super.hashCode();}
	public boolean equals(Object obj)
	{
		if( obj instanceof CodesItem)
			return (this == obj);
		else
			return super.equals(obj);
	}
/////////////////////////////////////////////////////////////////////////////////    



	/**基础信息维护<JiChuXinXiWeiHu>  */
	public static final TranType JiChuXinXiWeiHu                         		= getObject("1002");
	/**业委会信息维护<YeWeiHuiXinXinWeiHu>  */
	public static final TranType YeWeiHuiXinXinWeiHu                     		= getObject("1003");
	/**物业企业信息维护<WuYeQiYeXinXiWeiHU>  */
	public static final TranType WuYeQiYeXinXiWeiHU                      		= getObject("1004");
	/**小区经理信息维护<XiaoQuJingLiXinXiWeiHu>  */
	public static final TranType XiaoQuJingLiXinXiWeiHu                  		= getObject("1005");
	/**小区管理处信息维护<XiaoQuGuanLiChuXinXiWeiHu>  */
	public static final TranType XiaoQuGuanLiChuXinXiWeiHu               		= getObject("1006");
	/**分配物业服务范围<FenPeiWuYeFuWuFanWei>  */
	public static final TranType FenPeiWuYeFuWuFanWei                    		= getObject("1007");
	/**信用体系管理<XinYongTiXiGuanLi>  */
	public static final TranType XinYongTiXiGuanLi                       		= getObject("1008");
	/**诚信信息采集<ChengXinXinXiCaiJi>  */
	public static final TranType ChengXinXinXiCaiJi                      		= getObject("4001");
	/**诚信信息筛选<ChengXinXinXiShaiXuan>  */
	public static final TranType ChengXinXinXiShaiXuan                   		= getObject("4002");
	/**诚信计分预估<ChengXinJiFenYuGu>  */
	public static final TranType ChengXinJiFenYuGu                       		= getObject("4003");
	/**诚信计分告知<ChengXinJiFenGaoZhi>  */
	public static final TranType ChengXinJiFenGaoZhi                     		= getObject("4004");
	/**诚信计分申诉<ChengXinJiFEnShenSu>  */
	public static final TranType ChengXinJiFEnShenSu                     		= getObject("4005");
}

