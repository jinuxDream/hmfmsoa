package hmfms.services.codes;																														
import hmfms.services.exception.NotFoundException;                                    
import hmfms.util.Constants;                                                         
import java.util.*;                                                                  
import fd.commons.jdbc.Result;                                                       
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
/**Created by automatic  */												 
/**代码类型名：节点类型  */												 
public class NodeType extends CodesItem {                                      
	private static final Log logger = LogFactory.getLog(NodeType.class);                                                      
	private NodeType(String code){super(CodesItem.nodeType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.nodeType,code);                         
	}                                                                                   
	public static String getValue(NodeType code){	                                
		return CodesItem.getValue(CodesItem.nodeType,code.toString());              
	}                                                                                   
	public static Result getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.nodeType);                           
	}                                                                                   
	public static final NodeType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.nodeType);                         
		return map==null?null:(NodeType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		Result rs = null;                                                                 
		try{rs=getCodeFromDB(CodesItem.nodeType);}catch(NotFoundException e)        
		{if(Constants.DEV_DEBUG) Debug.exception(logger,e);return;}	                          
		Map map=new HashMap(rs.getRowCount());String temp=null;                           
		Map map2=new HashMap(rs.getRowCount());                                           
		for (int i=0; i<rs.getRowCount();i++){                                            
			temp=rs.getString(i,"ci_sp_code");                                              
			map.put(temp,rs.getString(i,"ci_sp_name"));                                     
			map2.put(temp,new NodeType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.nodeType,map);                                
		mapCodeObject.put(CodesItem.nodeType,map2);		                              
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



	/**单位<DanWei>  */
	public static final NodeType DanWei                                  		= getObject("00");
	/**科室<KeShi>  */
	public static final NodeType KeShi                                   		= getObject("01");
	/**岗位<GangWei>  */
	public static final NodeType GangWei                                 		= getObject("02");
	/**职务<ZhiWu>  */
	public static final NodeType ZhiWu                                   		= getObject("03");
}

