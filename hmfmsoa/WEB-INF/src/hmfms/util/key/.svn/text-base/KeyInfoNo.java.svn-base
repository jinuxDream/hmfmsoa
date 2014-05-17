package hmfms.util.key;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.BusinessException;

public class KeyInfoNo {
	private int keyMax;
    private int keyMin;
    private int nextKey;
    private int poolSize;
    private String tablename;
    public KeyInfoNo(int poolSize,String tablename){
		this.poolSize = poolSize;
		this.tablename = tablename;
        retrieveFromDB();
    }

    public int getKeyMax(){
		return keyMax;
	}

    public int getKeyMin(){
		return keyMin;
	}

    public synchronized int getNextKey() {
        if (nextKey > keyMax){
			retrieveFromDB();
        }
        return nextKey++;
    }
    private void retrieveFromDB(){
    	SQLExecutor db = null;
    	int keyFromDB = -1;
    	try {
			db = new SQLExecutor();
			SqlOperator dbo = new SqlOperator();
			dbo.addSql("update keytable set value = value+? where key_name = ?");
			dbo.addParam(this.poolSize);
			dbo.addParam(this.tablename);
			if(dbo.execute(db)!=1){
				throw new BusinessException("修改信息编号序列失败，key_name为："+this.tablename);
			}
			dbo.clear();
			dbo.addSql("select value from keytable where key_name = ?");
			dbo.addParam(this.tablename);
			Result rs = dbo.query(db);
			if(rs.getRowCount()==0){throw new BusinessException("数据库查询异常：表为keyInfo！");}
			keyFromDB = rs.getInt(0,"value");
		}catch(BusinessException e){
			throw e;
		} catch (Exception e) {
			throw new BusinessException(e);
		}finally{
			if(db!=null)
				db.close();
		}
		keyMax = keyFromDB;
        keyMin = keyFromDB - poolSize + 1;
        nextKey = keyMin;
	}
}
