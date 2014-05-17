package hmfms.services.codes;																														
import hmfms.services.exception.NotFoundException;                                    
import hmfms.util.Constants;                                                         
import java.util.*;                                                                  
import fd.commons.jdbc.Result;                                                       
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
/**Created by automatic  */												 
/**代码类型名：工作组类型  */												 
public class WorkGroupType extends CodesItem {                                      
	private static final Log logger = LogFactory.getLog(WorkGroupType.class);                                                      
	private WorkGroupType(String code){super(CodesItem.workGroupType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.workGroupType,code);                         
	}                                                                                   
	public static String getValue(WorkGroupType code){	                                
		return CodesItem.getValue(CodesItem.workGroupType,code.toString());              
	}                                                                                   
	public static Result getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.workGroupType);                           
	}                                                                                   
	public static final WorkGroupType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.workGroupType);                         
		return map==null?null:(WorkGroupType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		Result rs = null;                                                                 
		try{rs=getCodeFromDB(CodesItem.workGroupType);}catch(NotFoundException e)        
		{if(Constants.DEV_DEBUG) Debug.exception(logger,e);return;}	                          
		Map map=new HashMap(rs.getRowCount());String temp=null;                           
		Map map2=new HashMap(rs.getRowCount());                                           
		for (int i=0; i<rs.getRowCount();i++){                                            
			temp=rs.getString(i,"ci_sp_code");                                              
			map.put(temp,rs.getString(i,"ci_sp_name"));                                     
			map2.put(temp,new WorkGroupType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.workGroupType,map);                                
		mapCodeObject.put(CodesItem.workGroupType,map2);		                              
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



	/**工作业务组<GongZuoYeWuZu>  */
	public static final WorkGroupType GongZuoYeWuZu                           		= getObject("1");
	/**审批流程组<ShenPiLiuChengZu>  */
	public static final WorkGroupType ShenPiLiuChengZu                        		= getObject("2");
}

