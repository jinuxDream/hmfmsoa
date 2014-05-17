package hmfms.action.systemname;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.AppSystemException;
import fd.exception.BusinessException;
import hmfms.base.BaseDeal;
import hmfms.web.User;
import hmfms.web.system_name;
import hmfms.web.commons.PageCounter;

/**
 * 系统管理manager
 * 
 * @author hongzhi
 * 
 */
public class SystemnameManager extends BaseDeal {

	/**
	 * 系统管理index
	 * @param systemName
	 * @param page
	 * @return
	 */
	public Result index(User user, PageCounter page) {
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			SqlOperator dbo = new SqlOperator();
			dbo.clear();
			dbo.addSql("select * from system_name");
			Result rs = dbo.query(page, db);
			return rs;
		} catch (Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException(e);
		} finally {
			if (db != null)
				db.close();
		}
	}
	/**
	 * 新增--
	 * @return
	 */
	public void add(User user,system_name systemName){
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			SqlOperator dbo = new SqlOperator();
			systemName.setSn_id(systemName.getSn_id());
			systemName.setSn_name(systemName.getSn_name());
			if (dbo.add(systemName, db) != 1) {
				throw new BusinessException("保存失败！");
			}
		}catch(Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException(e);
		}
		finally {
			if( null != db ) {
				db.close();
			}
		}
	}
	
	
}
