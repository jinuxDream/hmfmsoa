package hmfms.util.key;
import java.sql.Connection;
import java.sql.DriverManager;

import fd.commons.jdbc.DBInitProperty;
import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;

/**
 * @author 米维聪
 * 
 */
public class KeyPool
{
    private int keyMax;
    private int keyMin;
    private int nextKey;
    private int poolSize;

    public KeyPool(int poolSize)
    {
		this.poolSize = poolSize;
        retrieveFromDB();
    }

    public int getKeyMax()
    {
		return keyMax;
	}

    public int getKeyMin()
    {
		return keyMin;
	}

    public synchronized int getNextKey()
    {
        if (nextKey > keyMax)
        {
			retrieveFromDB();
        }
        return nextKey++;
    }

    private void retrieveFromDB()
    {
//    	Connection conn = null;
//    	PreparedStatement prepStatement = null;
//    	Statement statement = null;
    	Result rs = null;
    	SQLExecutor db=null;
    	int keyFromDB = -1;
    	try{
    		db=new SQLExecutor();
    		db.beginTrans();
       		rs =db.execute( "SELECT value FROM keytable WHERE key_name = 'batchno' for update ");
       		String update = "UPDATE keytable SET value = value + ? WHERE key_name = 'batchno'";
       		db.addParam(this.poolSize);
       		db.execute(update); 
      		int result = db.getNumRecordsUpdated();
       		if(result!=1) throw new RuntimeException("数据库更新异常：表为keytable！");  
      		rs =db.execute("SELECT value FROM keytable WHERE key_name = 'batchno'  ");
      		if(rs==null || rs.isEmpty())  throw new RuntimeException("数据库查询异常：表为keytable！");
    		keyFromDB = rs.getInt(0,"value");
    		db.commit();
   
    	}catch(Exception e){
    		if(db!=null) db.rollback();
    		throw new RuntimeException(e);
    	}finally{
    		if(db!=null) db.close();
    		
    	}

		keyMax = keyFromDB;
        keyMin = keyFromDB - poolSize + 1;
        nextKey = keyMin;
    }


}