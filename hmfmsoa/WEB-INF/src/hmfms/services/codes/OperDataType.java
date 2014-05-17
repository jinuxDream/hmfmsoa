package hmfms.services.codes;																														
import hmfms.services.exception.NotFoundException;                                    
import hmfms.util.Constants;                                                         
import java.util.*;                                                                  
import fd.commons.jdbc.Result;                                                       
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
/**Created by automatic  */												 
/**代码类型名：操作数据类型  */												 
public class OperDataType extends CodesItem {                                      
	private static final Log logger = LogFactory.getLog(OperDataType.class);                                                      
	private OperDataType(String code){super(CodesItem.operDataType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.operDataType,code);                         
	}                                                                                   
	public static String getValue(OperDataType code){	                                
		return CodesItem.getValue(CodesItem.operDataType,code.toString());              
	}                                                                                   
	public static Result getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.operDataType);                           
	}                                                                                   
	public static final OperDataType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.operDataType);                         
		return map==null?null:(OperDataType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		Result rs = null;                                                                 
		try{rs=getCodeFromDB(CodesItem.operDataType);}catch(NotFoundException e)        
		{if(Constants.DEV_DEBUG) Debug.exception(logger,e);return;}	                          
		Map map=new HashMap(rs.getRowCount());String temp=null;                           
		Map map2=new HashMap(rs.getRowCount());                                           
		for (int i=0; i<rs.getRowCount();i++){                                            
			temp=rs.getString(i,"ci_sp_code");                                              
			map.put(temp,rs.getString(i,"ci_sp_name"));                                     
			map2.put(temp,new OperDataType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.operDataType,map);                                
		mapCodeObject.put(CodesItem.operDataType,map2);		                              
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



	/**注册数据<ZhuCeShuJu>  */
	public static final OperDataType ZhuCeShuJu                              		= getObject("0");
	/**更改前数据<GengGaiQianShuJu>  */
	public static final OperDataType GengGaiQianShuJu                        		= getObject("1");
	/**更改后数据<GengGaiHouShuJu>  */
	public static final OperDataType GengGaiHouShuJu                         		= getObject("2");
	/**注销数据<ZhuXiaoShuJu>  */
	public static final OperDataType ZhuXiaoShuJu                            		= getObject("3");
	/**撤销<CheXiao>  */
	public static final OperDataType CheXiao                                 		= getObject("4");
	/**业务数据<YeWuShuJu>  */
	public static final OperDataType YeWuShuJu                               		= getObject("5");
}

