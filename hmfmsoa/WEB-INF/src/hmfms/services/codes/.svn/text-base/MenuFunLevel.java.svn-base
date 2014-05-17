package hmfms.services.codes;																														
import hmfms.services.exception.NotFoundException;                                    
import hmfms.util.Constants;                                                         
import java.util.*;                                                                  
import fd.commons.jdbc.Result;                                                       
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
/**Created by automatic  */												 
/**代码类型名：菜单功能级别  */												 
public class MenuFunLevel extends CodesItem {                                      
	private static final Log logger = LogFactory.getLog(MenuFunLevel.class);                                                      
	private MenuFunLevel(String code){super(CodesItem.menuFunLevel,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.menuFunLevel,code);                         
	}                                                                                   
	public static String getValue(MenuFunLevel code){	                                
		return CodesItem.getValue(CodesItem.menuFunLevel,code.toString());              
	}                                                                                   
	public static Result getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.menuFunLevel);                           
	}                                                                                   
	public static final MenuFunLevel getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.menuFunLevel);                         
		return map==null?null:(MenuFunLevel)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		Result rs = null;                                                                 
		try{rs=getCodeFromDB(CodesItem.menuFunLevel);}catch(NotFoundException e)        
		{if(Constants.DEV_DEBUG) Debug.exception(logger,e);return;}	                          
		Map map=new HashMap(rs.getRowCount());String temp=null;                           
		Map map2=new HashMap(rs.getRowCount());                                           
		for (int i=0; i<rs.getRowCount();i++){                                            
			temp=rs.getString(i,"ci_sp_code");                                              
			map.put(temp,rs.getString(i,"ci_sp_name"));                                     
			map2.put(temp,new MenuFunLevel(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.menuFunLevel,map);                                
		mapCodeObject.put(CodesItem.menuFunLevel,map2);		                              
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



	/**一级菜单<YiJiCaiDan>  */
	public static final MenuFunLevel YiJiCaiDan                              		= getObject("0");
	/**二级菜单<ErJiCaiDan>  */
	public static final MenuFunLevel ErJiCaiDan                              		= getObject("1");
	/**具体交易<JuTiJiaoYi>  */
	public static final MenuFunLevel JuTiJiaoYi                              		= getObject("2");
}

