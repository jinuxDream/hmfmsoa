package hmfms.services.codes;																														
import hmfms.services.exception.NotFoundException;                                    
import hmfms.util.Constants;                                                         
import java.util.*;                                                                  
import fd.commons.jdbc.Result;                                                       
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
/**Created by automatic  */												 
/**代码类型名：变更类型  */												 
public class ChangeType extends CodesItem {                                      
	private static final Log logger = LogFactory.getLog(ChangeType.class);                                                      
	private ChangeType(String code){super(CodesItem.changeType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.changeType,code);                         
	}                                                                                   
	public static String getValue(ChangeType code){	                                
		return CodesItem.getValue(CodesItem.changeType,code.toString());              
	}                                                                                   
	public static Result getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.changeType);                           
	}                                                                                   
	public static final ChangeType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.changeType);                         
		return map==null?null:(ChangeType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		Result rs = null;                                                                 
		try{rs=getCodeFromDB(CodesItem.changeType);}catch(NotFoundException e)        
		{if(Constants.DEV_DEBUG) Debug.exception(logger,e);return;}	                          
		Map map=new HashMap(rs.getRowCount());String temp=null;                           
		Map map2=new HashMap(rs.getRowCount());                                           
		for (int i=0; i<rs.getRowCount();i++){                                            
			temp=rs.getString(i,"ci_sp_code");                                              
			map.put(temp,rs.getString(i,"ci_sp_name"));                                     
			map2.put(temp,new ChangeType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.changeType,map);                                
		mapCodeObject.put(CodesItem.changeType,map2);		                              
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



	/**新增<xinzeng>  */
	public static final ChangeType xinzeng                                 		= getObject("01");
	/**修改<xiugai>  */
	public static final ChangeType xiugai                                  		= getObject("02");
	/**删除<shanchu>  */
	public static final ChangeType shanchu                                 		= getObject("03");
}

