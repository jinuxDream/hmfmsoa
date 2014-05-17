package hmfms.services.codes;																															
import java.util.*;                                                                    
import fd.commons.jdbc.Result;                                                         
import fd.commons.jdbc.SQLExecutor;                                                    
import fd.commons.jdbc.AbstractSQLParamObject;                                        
import hmfms.services.exception.NotFoundException;                                   
import hmfms.util.BeanUtil;                                                           
import hmfms.util.Debug;                                                           
import org.apache.commons.logging.Log;                                                           
import org.apache.commons.logging.LogFactory;                                                           
                                                                                       
public abstract class CodesItem extends AbstractSQLParamObject{                        
	private static final Log logger = LogFactory.getLog(CodesItem.class);                                                      
	/**单位类别   */
	protected static final String deptType=new String("1");
	/**节点类型   */
	protected static final String nodeType=new String("2");
	/**工作组类型   */
	protected static final String workGroupType=new String("3");
	/**操作员状态   */
	protected static final String operStatus=new String("4");
	/**菜单功能级别   */
	protected static final String menuFunLevel=new String("5");
	/**交易类型   */
	protected static final String tranType=new String("14");
	/**交易角色   */
	protected static final String tranRole=new String("15");
	/**交易状态   */
	protected static final String tranStatus=new String("16");
	/**是否标志   */
	protected static final String isFlag=new String("17");
	/**操作日志类型   */
	protected static final String operLogType=new String("18");
	/**变更状态   */
	protected static final String changeStat=new String("19");
	/**变更类型   */
	protected static final String changeType=new String("20");
	/**参数类别   */
	protected static final String paraType=new String("21");
	/**操作数据类型   */
	protected static final String operDataType=new String("23");
	
	protected static final String systemname=new String("24");

	protected static final Map mapCat= new HashMap(100);
	static{
		mapCat.put("DeptType",deptType);
		mapCat.put("NodeType",nodeType);
		mapCat.put("WorkGroupType",workGroupType);
		mapCat.put("OperStatus",operStatus);
		mapCat.put("MenuFunLevel",menuFunLevel);
		mapCat.put("TranType",tranType);
		mapCat.put("TranRole",tranRole);
		mapCat.put("TranStatus",tranStatus);
		mapCat.put("IsFlag",isFlag);
		mapCat.put("OperLogType",operLogType);
		mapCat.put("ChangeStat",changeStat);
		mapCat.put("ChangeType",changeType);
		mapCat.put("ParaType",paraType);
		mapCat.put("OperDataType",operDataType);
		mapCat.put("systemname",systemname);
	}


	protected static final  Map mapCategoryCode=new HashMap(100);																			
	protected static final  Map mapCodeObject=new HashMap(100);																				
	public boolean equals(String obj) {                                                               
		return this.code.equals(obj);                                                        
	}                                                                                                 
	public boolean equals(Object obj) {  
		return this.code.equals(obj.toString());                                                           
	}                                                                                            
	public String toString(){ return this.code; }                                                     
	                                                                                                  
	/**根据指定的代码值转换成中文名字                                                                 
	 * @param category   本代码所属的类别的编号                                                       
	 * @param code   本代码的代码值                                                                   
	 * @return                                                                                        
	 * @throws                                                                                        
	 */                                                                                               
	protected static String getValue(String category,String code){                                    
		Map map=(Map)mapCategoryCode.get(category);                                                     
		if(map==null) return "";                                    						              
		String temp=(String)map.get(code);                                    						  
		return (temp==null)? "" : temp;                                    								
	}                                                                                                 
	public static Result getCodeList(String categoryCode){                                            
		List list = new ArrayList();                                                                    
		Result rs=new Result(list);	                                                                    
		HashMap newMap=null;                                                                            
		Map mp=(Map)mapCategoryCode.get(categoryCode);                                                  
		if(mp==null) return rs;                                               											    
		String temp;	                                                                                  
		for (Iterator it=mp.keySet().iterator();it.hasNext();){                                         
			temp=(String)it.next();                                                                       
			newMap=new HashMap(3);                                                                        
			list.add(newMap);                                                                             
			newMap.put("ci_sp_code",temp);                                                              
			newMap.put("ci_sp_class",categoryCode);                                                     
			newMap.put("ci_sp_name",(String)mp.get(temp));                                              
		}                                                                                               
		BeanUtil.sortResult(rs,"ci_sp_code");                                                           
		return rs;		                                                                                  
	}                                                                                                 
	public static Map getCategoryItems(String categoryName){
		String categoryCode = (String)mapCat.get(categoryName);
		if(categoryCode==null)
			throw new RuntimeException(categoryName + "代码大类别未进行初始化！");
		Map mp=(Map)mapCategoryCode.get(categoryCode);
		return mp;
	}
	/**直接转换成中文名字                                                                             
	 * @无参数                                                                                        
	 * @return                                                                                        
	 */                                                                                               
	public  String getValue(){                                                                        
		 return getValue( this.category,this.code);                                                     
	}                                                                                                 
	/**                                                                                               
	 * 构造函数.                                                                                      
	 * @param category   本代码所属的类别的编号                                                       
	 * @param code   本代码的代码值                                                                   
	 * @return                                                                                        
	 * @throws                                                                                        
	 */                                                                                               
	protected  CodesItem(String category,String code){                                                
		this.category=category;                                                                         
		this.code=code;                                                                                 
	}                                                                                                 
	                                                                                                  
	protected static final Result getCodeFromDB(String category)throws NotFoundException{             
		SQLExecutor db = null;                                                                          
		Result rs = null;                                                                               
		                                                                                                
		try{                                                                                            
			db = new SQLExecutor(SQLExecutor.SQL_SHOW_CLOSE, true);                                   
			db.addParam(category);                                                                        
			rs = db.execute("select * from CODE_INFO where ci_sp_class =? order by ci_sp_code");        
		}catch(Exception e){                                                                            
			Debug.exception(logger,e);                                                                
			throw new NotFoundException("当前使用的代码项在数据库无法取到 code=" + category);             
		}finally{                                                                                       
			if(db != null) db.close();                                                                    
		}                                                                                               
		if(rs.getRowCount()<1){                                                                         
			throw new NotFoundException("当前使用的代码项在数据库无法取到 code="+ category);            
		}                                                                                               
		return rs;                                                                                      
	}                                                                                                 
	private String code;                                                                              
	protected String category;                                                                        
	public  String getCategoryCode(){                                                
		return category;                                                                                 
	}                                                                                                 
}                                                                                                  



