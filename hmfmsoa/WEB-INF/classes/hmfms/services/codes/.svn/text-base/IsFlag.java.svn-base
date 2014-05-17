package hmfms.services.codes;																														
import hmfms.services.exception.NotFoundException;                                    
import hmfms.util.Constants;                                                         
import java.util.*;                                                                  
import fd.commons.jdbc.Result;                                                       
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
/**Created by automatic  */												 
/**代码类型名：是否标志  */												 
public class IsFlag extends CodesItem {                                      
	private static final Log logger = LogFactory.getLog(IsFlag.class);                                                      
	private IsFlag(String code){super(CodesItem.isFlag,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.isFlag,code);                         
	}                                                                                   
	public static String getValue(IsFlag code){	                                
		return CodesItem.getValue(CodesItem.isFlag,code.toString());              
	}                                                                                   
	public static Result getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.isFlag);                           
	}                                                                                   
	public static final IsFlag getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.isFlag);                         
		return map==null?null:(IsFlag)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		Result rs = null;                                                                 
		try{rs=getCodeFromDB(CodesItem.isFlag);}catch(NotFoundException e)        
		{if(Constants.DEV_DEBUG) Debug.exception(logger,e);return;}	                          
		Map map=new HashMap(rs.getRowCount());String temp=null;                           
		Map map2=new HashMap(rs.getRowCount());                                           
		for (int i=0; i<rs.getRowCount();i++){                                            
			temp=rs.getString(i,"ci_sp_code");                                              
			map.put(temp,rs.getString(i,"ci_sp_name"));                                     
			map2.put(temp,new IsFlag(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.isFlag,map);                                
		mapCodeObject.put(CodesItem.isFlag,map2);		                              
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



	/**否<Fou>  */
	public static final IsFlag Fou                                     		= getObject("0");
	/**是<Shi>  */
	public static final IsFlag Shi                                     		= getObject("1");
}

