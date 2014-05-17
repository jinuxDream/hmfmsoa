package hmfms.services.codes;																														
import hmfms.services.exception.NotFoundException;                                    
import hmfms.util.Constants;                                                         
import java.util.*;                                                                  
import fd.commons.jdbc.Result;                                                       
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
/**Created by automatic  */												 
/**代码类型名：操作日志类型  */												 
public class OperLogType extends CodesItem {                                      
	private static final Log logger = LogFactory.getLog(OperLogType.class);                                                      
	private OperLogType(String code){super(CodesItem.operLogType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.operLogType,code);                         
	}                                                                                   
	public static String getValue(OperLogType code){	                                
		return CodesItem.getValue(CodesItem.operLogType,code.toString());              
	}                                                                                   
	public static Result getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.operLogType);                           
	}                                                                                   
	public static final OperLogType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.operLogType);                         
		return map==null?null:(OperLogType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		Result rs = null;                                                                 
		try{rs=getCodeFromDB(CodesItem.operLogType);}catch(NotFoundException e)        
		{if(Constants.DEV_DEBUG) Debug.exception(logger,e);return;}	                          
		Map map=new HashMap(rs.getRowCount());String temp=null;                           
		Map map2=new HashMap(rs.getRowCount());                                           
		for (int i=0; i<rs.getRowCount();i++){                                            
			temp=rs.getString(i,"ci_sp_code");                                              
			map.put(temp,rs.getString(i,"ci_sp_name"));                                     
			map2.put(temp,new OperLogType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.operLogType,map);                                
		mapCodeObject.put(CodesItem.operLogType,map2);		                              
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



	/**新增<XinZeng>  */
	public static final OperLogType XinZeng                                 		= getObject("01");
	/**修改<XiuGai>  */
	public static final OperLogType XiuGai                                  		= getObject("02");
	/**删除<ShanChu>  */
	public static final OperLogType ShanChu                                 		= getObject("03");
	/**查看<ChaKan>  */
	public static final OperLogType ChaKan                                  		= getObject("04");
}

