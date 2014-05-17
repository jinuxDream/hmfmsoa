package hmfms.services.codes;																														
import hmfms.services.exception.NotFoundException;                                    
import hmfms.util.Constants;                                                         
import java.util.*;                                                                  
import fd.commons.jdbc.Result;                                                       
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
/**Created by automatic  */												 
/**代码类型名：交易角色  */												 
public class TranRole extends CodesItem {                                      
	private static final Log logger = LogFactory.getLog(TranRole.class);                                                      
	private TranRole(String code){super(CodesItem.tranRole,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.tranRole,code);                         
	}                                                                                   
	public static String getValue(TranRole code){	                                
		return CodesItem.getValue(CodesItem.tranRole,code.toString());              
	}                                                                                   
	public static Result getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.tranRole);                           
	}                                                                                   
	public static final TranRole getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.tranRole);                         
		return map==null?null:(TranRole)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		Result rs = null;                                                                 
		try{rs=getCodeFromDB(CodesItem.tranRole);}catch(NotFoundException e)        
		{if(Constants.DEV_DEBUG) Debug.exception(logger,e);return;}	                          
		Map map=new HashMap(rs.getRowCount());String temp=null;                           
		Map map2=new HashMap(rs.getRowCount());                                           
		for (int i=0; i<rs.getRowCount();i++){                                            
			temp=rs.getString(i,"ci_sp_code");                                              
			map.put(temp,rs.getString(i,"ci_sp_name"));                                     
			map2.put(temp,new TranRole(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.tranRole,map);                                
		mapCodeObject.put(CodesItem.tranRole,map2);		                              
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



	/**申请<ShenQing>  */
	public static final TranRole ShenQing                                		= getObject("01");
	/**审核<ShenHe>  */
	public static final TranRole ShenHe                                  		= getObject("02");
	/**复审<FuShen>  */
	public static final TranRole FuShen                                  		= getObject("03");
}

