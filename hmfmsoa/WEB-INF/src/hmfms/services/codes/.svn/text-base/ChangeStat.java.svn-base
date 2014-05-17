package hmfms.services.codes;																														
import hmfms.services.exception.NotFoundException;                                    
import hmfms.util.Constants;                                                         
import java.util.*;                                                                  
import fd.commons.jdbc.Result;                                                       
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
/**Created by automatic  */												 
/**代码类型名：变更状态  */												 
public class ChangeStat extends CodesItem {                                      
	private static final Log logger = LogFactory.getLog(ChangeStat.class);                                                      
	private ChangeStat(String code){super(CodesItem.changeStat,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.changeStat,code);                         
	}                                                                                   
	public static String getValue(ChangeStat code){	                                
		return CodesItem.getValue(CodesItem.changeStat,code.toString());              
	}                                                                                   
	public static Result getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.changeStat);                           
	}                                                                                   
	public static final ChangeStat getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.changeStat);                         
		return map==null?null:(ChangeStat)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		Result rs = null;                                                                 
		try{rs=getCodeFromDB(CodesItem.changeStat);}catch(NotFoundException e)        
		{if(Constants.DEV_DEBUG) Debug.exception(logger,e);return;}	                          
		Map map=new HashMap(rs.getRowCount());String temp=null;                           
		Map map2=new HashMap(rs.getRowCount());                                           
		for (int i=0; i<rs.getRowCount();i++){                                            
			temp=rs.getString(i,"ci_sp_code");                                              
			map.put(temp,rs.getString(i,"ci_sp_name"));                                     
			map2.put(temp,new ChangeStat(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.changeStat,map);                                
		mapCodeObject.put(CodesItem.changeStat,map2);		                              
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



	/**新接收<xinjieshou>  */
	public static final ChangeStat xinjieshou                              		= getObject("01");
	/**延期<yanqi>  */
	public static final ChangeStat yanqi                                   		= getObject("02");
	/**分析中<fenxizhong>  */
	public static final ChangeStat fenxizhong                              		= getObject("03");
	/**实施中<shishizhong>  */
	public static final ChangeStat shishizhong                             		= getObject("04");
	/**已验证<yiyanzheng>  */
	public static final ChangeStat yiyanzheng                              		= getObject("05");
	/**已发布<yifabu>  */
	public static final ChangeStat yifabu                                  		= getObject("06");
	/**拒接受<jujieshou>  */
	public static final ChangeStat jujieshou                               		= getObject("07");
}

