package UserDemandTrack;

import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.commons.jdbc.SqlOperator;
import fd.exception.AppSystemException;
import fd.exception.BusinessException;
import fd.util.Assert;
import hmfms.base.BaseDeal;
import hmfms.services.codes.ChangeStat;
import hmfms.services.entity.Demand_track;
import hmfms.services.key.PrimayKeyGener;
import hmfms.util.DateUtil;
import hmfms.util.ObjectUtil;
import hmfms.web.User;
import hmfms.web.commons.PageCounter;

public class UserTrackManager extends BaseDeal{
	/**
	 * �û����������ҳ
	 * @param user
	 * @param page
	 * @return
	 */
	public Result index(User user, PageCounter page) {
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			SqlOperator dbo = new SqlOperator();
			dbo.clear();
			dbo.addSql("select * from demand_track");
			dbo.addSql("where dt_state<>?");
			dbo.addParam(ChangeStat.jujieshou.toString());
			dbo.addSql("order by dt_stat_date,dt_end_date");
			Result rs = dbo.query(page,db);
			return rs;
		} catch (Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException(e);
		}finally{
			if(db != null){
				db.close();
			}
		}
	}
	
	/**
	 * �����û�������ٵ���ϸҳ��
	 * @return
	 */
	public Result addCheck(){
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			SqlOperator dbo = new SqlOperator();
			dbo.addSql("select * from demand_track");
			Result rs = dbo.query(db);
			return rs;
		}catch(Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException(e);
		}
		finally {
			if( null != db ) {
				/*�ر�����*/
				db.close();
			}
		}
	}
	
	/**
	 * �����������û�������ٵ�
	 * @param user
	 * @param dt
	 */
	public void addCheckOk(User user,Demand_track dt){
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			SqlOperator dbo = new SqlOperator();
			dt.setDt_no(PrimayKeyGener.getNextId(db));
			dt.setDt_date(DateUtil.format2DB(dt.getDt_date()));
			dt.setDt_state(ChangeStat.xinjieshou.toString());
			dt.setDt_stat_date(ObjectUtil.isEmpty(dt.getDt_stat_date()) ? DateUtil.getSysDate() : DateUtil.format2DB(dt.getDt_stat_date()));
			dt.setDt_end_date(ObjectUtil.isEmpty(dt.getDt_end_date()) ? DateUtil.getSysDate() : DateUtil.format2DB(dt.getDt_end_date()));
			dt.setDt_sav_time(DateUtil.getSysTime());
			if (dbo.add(dt, db) != 1) {
				throw new BusinessException("����ʧ�ܣ�");
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
	
	/**
	 * ����ϵͳ��ID��ѯ��ϵͳ����(������)
	 * @param user
	 * @param sn_id
	 * @return
	 */
	public String genOptionsWithDefault(User user,String sn_id){
		SQLExecutor db = null;
		Result rs = null;
		try {
			db = new SQLExecutor();
			SqlOperator dbo = new SqlOperator();
			Demand_track dt = new Demand_track();//�û��������
			dt.setSn_id(sn_id);
			rs = dbo.gets(dt, db);
			
			//���������Ϊ���ҽ����������0 ƴ�ӳ�������Ϣ����
			if(rs!=null&&rs.getRowCount()>0){
				StringBuffer optionStr = new StringBuffer();
				for(int i = 0;i<rs.getRowCount();i++){
					optionStr.append("<option value = \""+rs.getString(i, "dt_no")+"\">");
					optionStr.append(rs.getString(i, "dt_sys"));
					optionStr.append(rs.getString(i, "dt_state"));
					optionStr.append("</option>");
				}
				return optionStr.toString();
			}
		} catch (Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException(e);
		}
		finally {
			if( db != null )
				db.close();
		}
		return "";
	}
	
	
	
	/**
	 * �༭�û�������ٵ���ϸҳ��
	 * @param user
	 * @param dt
	 * @param dt_no
	 * @return
	 */
	public void editCheckOk(User user,Demand_track dt,String dt_no,String dt_prop_name,String dt_invol_demand,String dt_stat_date,
			String dt_end_date,String dt_date,String dt_work,String dt_implement,String dt_impl_pers,String dt_test_pas,
			String dt_assess,String dt_content,String dt_remark){
		Assert.hasText(dt_no,"������뵥ID����Ϊ��");
		Assert.hasText(user.getTellID(),"�û��Ĳ���ID����Ϊ�գ�");
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			/*���ݱ�����뵥ID��ѯ�û����������Ϣ�Ƿ����*/
			SqlOperator dbo = new SqlOperator();
			dt.setDt_prop_name(dt_prop_name);
			dt.setDt_invol_demand(dt_invol_demand);
			dt.setDt_stat_date(ObjectUtil.isEmpty(dt.getDt_stat_date()) ? DateUtil.getSysDate() : DateUtil.format2DB(dt.getDt_stat_date()));
			dt.setDt_end_date(ObjectUtil.isEmpty(dt.getDt_end_date()) ? DateUtil.getSysDate() : DateUtil.format2DB(dt.getDt_end_date()));
			dt.setDt_date(DateUtil.format2DB(dt.getDt_date()));
			dt.setDt_work(dt_work);
			dt.setDt_implement(dt_implement);
			dt.setDt_impl_pers(dt_impl_pers);
			dt.setDt_test_pas(dt_test_pas);
			dt.setDt_assess(dt_assess);
			dt.setDt_content(dt_content);
			dt.setDt_remark(dt_remark);
			
			if(dbo.update(dt,db) != 1){
				throw new BusinessException("�û�������ٱ༭����ʧ�ܣ�");
			}
			/*�ύ����*/
		    db.commit();
		}catch(Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException(e);
		}
		finally {
			if( null != db ) {
				/*�ر�����*/
				db.close();
			}
		}
	}
	
	/**
	 * �༭��ѯ�û�������ٵ���ϸҳ��
	 * @param user
	 * @param dt_no
	 * @return
	 */
	public Result getSectChkInfo(User user,String dt_no){
		Assert.notNull(user, "��ǰ�û�������Ϊ�գ�");
		Assert.hasText(dt_no, "������뵥ID����Ϊ�գ�");
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			SqlOperator dbo = new SqlOperator();
			dbo.clear();
			dbo.addSql("select * from demand_track");
			dbo.addSql("where dt_no=?");
			dbo.addParam(dt_no);
			Result rs = dbo.query(db);
			return rs;
		} catch (Exception e) {
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException(e);
		}
		finally {
			if( db != null )
				db.close();
		}
	}
	
	
	public void delCheckOk(String dt_no){
		Assert.hasText(dt_no, "������뵥����Ϊ�գ�");
		SqlOperator dbo = new SqlOperator();
		SQLExecutor db = null;
		try {
			db = new SQLExecutor();
			/*��������*/
			db.beginTrans();
			dbo.addSql("select * from demand_track");
			dbo.addSql("where dt_no = ?");
			dbo.addParam(dt_no);
			dbo.addSql("and dt_state = ?");
			dbo.addParam(ChangeStat.yanqi.toString());
			Result rs = dbo.query(db);
			dbo.clear();
			if(rs.getRowCount() < 1){
				throw new BusinessException("���û�������ٱ�����!");
			}
			
			dbo.clear();
			dbo.addSql("update demand_track set dt_state = ?");
			dbo.addParam(ChangeStat.shishizhong.toString());
			dbo.addSql("where dt_no = ?");
			dbo.addParam(dt_no);
			if (dbo.execute(db) != 1) {
				throw new BusinessException("�޸ı��״̬ʧ�ܣ�");
			}
			/*�ύ����*/
			db.commit();
		} catch (Exception e) {
			if( db != null ) {
				/*����ع�*/
				db.rollback();
			}
			if( e instanceof BusinessException )
				throw (BusinessException)e;
			else
				throw new AppSystemException(e);
		}
		finally {
			if( db != null ) {
				db.close();
			}
		}
	}
}
