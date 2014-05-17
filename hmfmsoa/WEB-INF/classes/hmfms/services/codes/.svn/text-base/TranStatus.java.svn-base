package hmfms.services.codes;																														
import hmfms.services.exception.NotFoundException;                                    
import hmfms.util.Constants;                                                         
import java.util.*;                                                                  
import fd.commons.jdbc.Result;                                                       
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
/**Created by automatic  */												 
/**代码类型名：交易状态  */												 
public class TranStatus extends CodesItem {                                      
	private static final Log logger = LogFactory.getLog(TranStatus.class);                                                      
	private TranStatus(String code){super(CodesItem.tranStatus,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.tranStatus,code);                         
	}                                                                                   
	public static String getValue(TranStatus code){	                                
		return CodesItem.getValue(CodesItem.tranStatus,code.toString());              
	}                                                                                   
	public static Result getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.tranStatus);                           
	}                                                                                   
	public static final TranStatus getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.tranStatus);                         
		return map==null?null:(TranStatus)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		Result rs = null;                                                                 
		try{rs=getCodeFromDB(CodesItem.tranStatus);}catch(NotFoundException e)        
		{if(Constants.DEV_DEBUG) Debug.exception(logger,e);return;}	                          
		Map map=new HashMap(rs.getRowCount());String temp=null;                           
		Map map2=new HashMap(rs.getRowCount());                                           
		for (int i=0; i<rs.getRowCount();i++){                                            
			temp=rs.getString(i,"ci_sp_code");                                              
			map.put(temp,rs.getString(i,"ci_sp_name"));                                     
			map2.put(temp,new TranStatus(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.tranStatus,map);                                
		mapCodeObject.put(CodesItem.tranStatus,map2);		                              
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



	/**编辑<BianJi>  */
	public static final TranStatus BianJi                                  		= getObject("00");
	/**待审核<TiJiaoShenHe>  */
	public static final TranStatus TiJiaoShenHe                            		= getObject("01");
	/**审核通过<ShenHeTongGuo>  */
	public static final TranStatus ShenHeTongGuo                           		= getObject("02");
	/**交易完成<JiaoYiWanCheng>  */
	public static final TranStatus JiaoYiWanCheng                          		= getObject("04");
	/**撤销<CheXiao>  */
	public static final TranStatus CheXiao                                 		= getObject("05");
	/**不受控制<BuShouKongZhi>  */
	public static final TranStatus BuShouKongZhi                           		= getObject("99");
}

