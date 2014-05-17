package hmfms.services.codes;																														
import hmfms.services.exception.NotFoundException;                                    
import hmfms.util.Constants;                                                         
import java.util.*;                                                                  
import fd.commons.jdbc.Result;                                                       
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
/**Created by automatic  */												 
/**代码类型名：参数类别  */												 
public class ParaType extends CodesItem {                                      
	private static final Log logger = LogFactory.getLog(ParaType.class);                                                      
	private ParaType(String code){super(CodesItem.paraType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.paraType,code);                         
	}                                                                                   
	public static String getValue(ParaType code){	                                
		return CodesItem.getValue(CodesItem.paraType,code.toString());              
	}                                                                                   
	public static Result getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.paraType);                           
	}                                                                                   
	public static final ParaType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.paraType);                         
		return map==null?null:(ParaType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		Result rs = null;                                                                 
		try{rs=getCodeFromDB(CodesItem.paraType);}catch(NotFoundException e)        
		{if(Constants.DEV_DEBUG) Debug.exception(logger,e);return;}	                          
		Map map=new HashMap(rs.getRowCount());String temp=null;                           
		Map map2=new HashMap(rs.getRowCount());                                           
		for (int i=0; i<rs.getRowCount();i++){                                            
			temp=rs.getString(i,"ci_sp_code");                                              
			map.put(temp,rs.getString(i,"ci_sp_name"));                                     
			map2.put(temp,new ParaType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.paraType,map);                                
		mapCodeObject.put(CodesItem.paraType,map2);		                              
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



	/**系统参数<XiTongCanShu>  */
	public static final ParaType XiTongCanShu                            		= getObject("01");
	/**业务参数<YeWuCanShu>  */
	public static final ParaType YeWuCanShu                              		= getObject("02");
}

