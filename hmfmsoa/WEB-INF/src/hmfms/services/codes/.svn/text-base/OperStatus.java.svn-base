package hmfms.services.codes;																														
import hmfms.services.exception.NotFoundException;                                    
import hmfms.util.Constants;                                                         
import java.util.*;                                                                  
import fd.commons.jdbc.Result;                                                       
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
/**Created by automatic  */												 
/**代码类型名：操作员状态  */												 
public class OperStatus extends CodesItem {                                      
	private static final Log logger = LogFactory.getLog(OperStatus.class);                                                      
	private OperStatus(String code){super(CodesItem.operStatus,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.operStatus,code);                         
	}                                                                                   
	public static String getValue(OperStatus code){	                                
		return CodesItem.getValue(CodesItem.operStatus,code.toString());              
	}                                                                                   
	public static Result getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.operStatus);                           
	}                                                                                   
	public static final OperStatus getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.operStatus);                         
		return map==null?null:(OperStatus)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		Result rs = null;                                                                 
		try{rs=getCodeFromDB(CodesItem.operStatus);}catch(NotFoundException e)        
		{if(Constants.DEV_DEBUG) Debug.exception(logger,e);return;}	                          
		Map map=new HashMap(rs.getRowCount());String temp=null;                           
		Map map2=new HashMap(rs.getRowCount());                                           
		for (int i=0; i<rs.getRowCount();i++){                                            
			temp=rs.getString(i,"ci_sp_code");                                              
			map.put(temp,rs.getString(i,"ci_sp_name"));                                     
			map2.put(temp,new OperStatus(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.operStatus,map);                                
		mapCodeObject.put(CodesItem.operStatus,map2);		                              
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



	/**新加操作员<XinJiaCaoZuoYuan>  */
	public static final OperStatus XinJiaCaoZuoYuan                        		= getObject("0");
	/**正常使用<ZhengChangShiYong>  */
	public static final OperStatus ZhengChangShiYong                       		= getObject("1");
	/**暂停使用<ZanTingShiYong>  */
	public static final OperStatus ZanTingShiYong                          		= getObject("2");
	/**正在使用<ZhengZaiShiYong>  */
	public static final OperStatus ZhengZaiShiYong                         		= getObject("3");
	/**注销<ZhuXiao>  */
	public static final OperStatus ZhuXiao                                 		= getObject("4");
}

