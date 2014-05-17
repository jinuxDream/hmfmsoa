package hmfms.services.util;

import hmfms.util.StringUtil;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import fd.commons.TableEntity;
import fd.commons.jdbc.Result;
import fd.commons.jdbc.SQLExecutor;
import fd.exception.BusinessException;

public class SQLExecuteUtil {

	private static final String FULL_TABLE_NAME_PREFIX = "hmfms.services.entity.";

	public static Result selectByMapResult(String tableName, Map<String, Object> data, SQLExecutor db) {

		TableEntity entity = getTableEntityByTableName(tableName);
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append(" select * from  ").append(tableName);
		StringBuffer where = new StringBuffer();
		PropertyDescriptor propertys[] = PropertyUtils.getPropertyDescriptors(entity);
		int len = propertys.length;
		int icount = 0;//��¼ɾ����������
		for(int i = 0; i < len; i++) {
			String prptyName = propertys[i].getName();
			if( !entity.isPrimaryKey(prptyName) )
				continue;
			Object value = data.get(prptyName);
			if( value == null )
				continue;//�������������ɾ������Ϊ�գ������ȡ��һ����
			if( where.length() > 0 )
				where.append(" and ");
			where.append(prptyName).append("=?");
			db.addParam(value);
			icount++;
		}
		if( icount < 1 )
			throw new BusinessException("��ѯ�����ṩ��ѯ����");
		if( where.length() < 1 )
			throw new BusinessException("δ�ṩ��ѯ���������ṩ��ѯ������");
		sqlSelect.append(" where ").append(where);
		return db.execute(sqlSelect.toString());
	}

	public static int selectByMap(String tableName, Map<String, Object> data, SQLExecutor db) {

		return selectByMapResult(tableName, data, db).getRowCount();
	}

	/********************************************�ײ���ɾ��************************************************/

	/**
	 * ����map�е����ݣ��������ݵ�ָ�������ݱ�TableEntity
	 * @param obj
	 * @param data
	 * @param db
	 * @return
	 */
	public static int saveByMap(String tableName, Map<String, Object> data, SQLExecutor db) {

		TableEntity entity = getTableEntityByTableName(tableName);
		StringBuffer sqlInsert = new StringBuffer();
		sqlInsert.append("insert into ").append(tableName).append("(");
		PropertyDescriptor propertys[] = PropertyUtils.getPropertyDescriptors(entity);
		int len = propertys.length;
		StringBuffer values = new StringBuffer();

		for(int i = 0; i < len; i++) {
			String name = propertys[i].getName();
			Object value = data.get(name);
			if( !PropertyUtils.isWriteable(entity, name) || (value == null) )
				continue;
			sqlInsert.append(name).append(",");
			values.append("?").append(",");
			db.addParam(value);
		}
		sqlInsert.deleteCharAt(sqlInsert.length() - 1);
		values.deleteCharAt(values.length() - 1);

		sqlInsert.append(" ) values ( ").append(values).append(")");
		db.execute(sqlInsert.toString());
		return db.getNumRecordsUpdated();

	}

	/**
	 * ��������,����������
	 * @param obj
	 * @param data
	 * @param db
	 * @return
	 */
	public static int updateRelationByMap(String tableName, Map<String, Object> data, SQLExecutor db) {

		TableEntity entity = getTableEntityByTableName(tableName);
		StringBuffer sqlUpdate = new StringBuffer(10);
		sqlUpdate.append(" update  ").append(tableName).append(" set ");
		PropertyDescriptor propertys[] = PropertyUtils.getPropertyDescriptors(entity);
		int len = propertys.length;
		StringBuffer where = new StringBuffer();
		for(int i = 0; i < len; i++) {
			String prptyName = propertys[i].getName();
			Object value = data.get(prptyName);
			if( prptyName.equals("info_id") )
				continue;
			if( !PropertyUtils.isWriteable(entity, prptyName) || (value == null) )
				continue;
			sqlUpdate.append(prptyName).append("=?").append(",");
			db.addParam(value);

		}
		for(int i = 0; i < len; i++) {
			String prptyName = propertys[i].getName();
			Object value = data.get(prptyName);
			if( !entity.isPrimaryKey(prptyName) )
				continue;
			if( value == null )
				throw new BusinessException("���ݸ���ʱ�������ṩ����������");
			if( where.length() > 0 )
				where.append(" and ");
			where.append(prptyName).append("=?");
			db.addParam(value);
		}
		sqlUpdate.deleteCharAt(sqlUpdate.length() - 1);
		if( where.length() < 1 )
			throw new BusinessException("δ�ṩ�������������ṩ����������");
		sqlUpdate.append(" where ").append(where);
		db.execute(sqlUpdate.toString());
		return db.getNumRecordsUpdated();

	}

	/**
	 * ��������,����������
	 * @param obj
	 * @param data
	 * @param db
	 * @return
	 */
	public static int updateByMap(String tableName, Map<String, Object> data, SQLExecutor db) {

		TableEntity entity = getTableEntityByTableName(tableName);
		StringBuffer sqlUpdate = new StringBuffer(10);
		sqlUpdate.append(" update  ").append(tableName).append(" set ");
		PropertyDescriptor propertys[] = PropertyUtils.getPropertyDescriptors(entity);
		int len = propertys.length;
		StringBuffer where = new StringBuffer();
		int counter = 0;
		for(int i = 0; i < len; i++) {
			String prptyName = propertys[i].getName();
			Object value = data.get(prptyName);
			if( entity.isPrimaryKey(prptyName) )
				continue;
			if( !PropertyUtils.isWriteable(entity, prptyName) || (value == null) )
				continue;
			sqlUpdate.append(prptyName).append("=?").append(",");
			db.addParam(value);
			counter++;
		}

		if( counter == 0 )
			return 1; //������ű���û���ֶ���Ҫ���£���ֱ�ӷ���1

		for(int i = 0; i < len; i++) {
			String prptyName = propertys[i].getName();
			Object value = data.get(prptyName);
			if( !entity.isPrimaryKey(prptyName) )
				continue;
			if( value == null )
				throw new BusinessException("���ݸ���ʱ�������ṩ����������");
			if( where.length() > 0 )
				where.append(" and ");
			where.append(prptyName).append("=?");
			db.addParam(value);
		}
		sqlUpdate.deleteCharAt(sqlUpdate.length() - 1);
		if( where.length() < 1 )
			throw new BusinessException("δ�ṩ�������������ṩ����������");
		sqlUpdate.append(" where ").append(where);
		db.execute(sqlUpdate.toString());
		return db.getNumRecordsUpdated();
	}

	/**
	 * ����Map����������ֵ��ɾ����Ϣ
	 * @param obj
	 * @param data
	 * @param db
	 * @return
	 */
	public static int deleteByMap(String tableName, Map<String, Object> data, SQLExecutor db) {

		TableEntity entity = getTableEntityByTableName(tableName);
		StringBuffer sqlDelete = new StringBuffer();
		sqlDelete.append(" delete   ").append(tableName);
		StringBuffer where = new StringBuffer();
		PropertyDescriptor propertys[] = PropertyUtils.getPropertyDescriptors(entity);
		int len = propertys.length;
		int icount = 0;//��¼ɾ����������
		for(int i = 0; i < len; i++) {
			String prptyName = propertys[i].getName();
			//			if(!obj.isPrimaryKey(prptyName)) continue;//��������ֱ��ȡ��һ��
			Object value = data.get(prptyName);
			if( value == null )
				continue;//�������������ɾ������Ϊ�գ������ȡ��һ����
			if( where.length() > 0 )
				where.append(" and ");
			where.append(prptyName).append("=?");
			db.addParam(value);
			icount++;
		}
		if( icount < 1 )
			throw new BusinessException("����ɾ��ʱ�������ṩɾ��������");
		if( where.length() < 1 )
			throw new BusinessException("δ�ṩɾ�����������ṩɾ��������");
		sqlDelete.append(" where ").append(where);
		db.execute(sqlDelete.toString());
		return db.getNumRecordsUpdated();

	}

	/**
	 * ����ʵ���ȡ��Ӧ������ֵ
	 * @param tableName ��������
	 * @param data ���Ӧ����
	 * @return
	 */
	public static Map<String, Object> getEntityKey(TableEntity tableName, Map<String, Object> data) {

		PropertyDescriptor propertys[] = PropertyUtils.getPropertyDescriptors(tableName);
		int len = propertys.length;
		HashMap<String, Object> pks = new HashMap<String, Object>();
		for(int i = 0; i < len; i++) {
			String prptyName = propertys[i].getName();
			if( tableName.isPrimaryKey(prptyName) ) {
				Object value = data.get(prptyName);
				pks.put(prptyName, value);
			}
		}
		if( pks.size() < 1 )
			throw new BusinessException("��ȡʵ����Ӧ������ֵ,�������б����ṩ����ֵ!");
		return pks;

	}

	/********************************************˽�й�����************************************************/

	/**
	 * ���ݱ�����ȡ��Ӧ��ʵ��
	 * @param tableName
	 * @return
	 */
	public static TableEntity getTableEntityByTableName(String tableName) {

		tableName = StringUtil.upperFirstWord(tableName);
		String fullName = FULL_TABLE_NAME_PREFIX + tableName;
		TableEntity entity = null;
		try {
			Class<?> clazz = Class.forName(fullName);
			entity = (TableEntity)clazz.newInstance();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return entity;

	}
}
