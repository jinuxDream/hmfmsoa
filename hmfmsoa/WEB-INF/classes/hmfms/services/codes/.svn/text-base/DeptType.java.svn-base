package hmfms.services.codes;																														
import hmfms.services.exception.NotFoundException;                                    
import hmfms.util.Constants;                                                         
import java.util.*;                                                                  
import fd.commons.jdbc.Result;                                                       
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
/**Created by automatic  */												 
/**代码类型名：单位类别  */												 
public class DeptType extends CodesItem {                                      
	private static final Log logger = LogFactory.getLog(DeptType.class);                                                      
	private DeptType(String code){super(CodesItem.deptType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.deptType,code);                         
	}                                                                                   
	public static String getValue(DeptType code){	                                
		return CodesItem.getValue(CodesItem.deptType,code.toString());              
	}                                                                                   
	public static Result getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.deptType);                           
	}                                                                                   
	public static final DeptType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.deptType);                         
		return map==null?null:(DeptType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		Result rs = null;                                                                 
		try{rs=getCodeFromDB(CodesItem.deptType);}catch(NotFoundException e)        
		{if(Constants.DEV_DEBUG) Debug.exception(logger,e);return;}	                          
		Map map=new HashMap(rs.getRowCount());String temp=null;                           
		Map map2=new HashMap(rs.getRowCount());                                           
		for (int i=0; i<rs.getRowCount();i++){                                            
			temp=rs.getString(i,"ci_sp_code");                                              
			map.put(temp,rs.getString(i,"ci_sp_name"));                                     
			map2.put(temp,new DeptType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.deptType,map);                                
		mapCodeObject.put(CodesItem.deptType,map2);		                              
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



	/**市局<ShiJu>  */
	public static final DeptType ShiJu                                   		= getObject("80");
	/**区局<QuJu>  */
	public static final DeptType QuJu                                    		= getObject("81");
	/**开发建设单位<KaiFaShang>  */
	public static final DeptType KaiFaShang                              		= getObject("82");
	/**物业服务企业<WuYeGongSi>  */
	public static final DeptType WuYeGongSi                              		= getObject("83");
	/**业主大会<YeZhuDaHui>  */
	public static final DeptType YeZhuDaHui                              		= getObject("91");
	/**居委会<JuWeiHui>  */
	public static final DeptType JuWeiHui                                		= getObject("92");
	/**房管办<FangGuanBan>   */
	public static final DeptType FangGuanBan                             		= getObject("93");
	/**小区管理处<XiaoQuGuanLiChu>  */
	public static final DeptType XiaoQuGuanLiChu                         		= getObject("94");
	/**业委会<YeWeiHui>  */
	public static final DeptType YeWeiHui                                		= getObject("95");
}

