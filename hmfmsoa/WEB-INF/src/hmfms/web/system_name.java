package hmfms.web;

import java.util.HashSet;
import java.util.Set;

import fd.commons.TableEntity;

/**
 * 系统名字 管理 20140516
 * 
 * @author hongzhi
 * 
 */
public class system_name extends TableEntity {

	public String sn_id;
	public String sn_name;
	private Set primaryKeys = new HashSet();

	public system_name() {
		super();
	}

	public system_name(String sn_id, String sn_name) {
		super();
		this.sn_id = sn_id;
		this.sn_name = sn_name;
	}

	public String getSn_id() {
		return sn_id;
	}

	public void setSn_id(String sn_id) {
		this.sn_id = sn_id;
	}

	public String getSn_name() {
		return sn_name;
	}

	public void setSn_name(String sn_name) {
		this.sn_name = sn_name;
	}

	@Override
	public boolean isPrimaryKey(String name) {
		// TODO Auto-generated method stub
		return primaryKeys.contains(name);
	}
}
