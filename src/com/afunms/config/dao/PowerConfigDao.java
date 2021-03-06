package com.afunms.config.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.afunms.common.base.BaseDao;
import com.afunms.common.base.BaseVo;
import com.afunms.common.base.DaoInterface;
import com.afunms.config.model.PowerConfig;

/**
 * @description ��Դ�澯����Dao
 * @author wangxiangyong
 * @date Dec 28, 2011
 */

@SuppressWarnings("unchecked")
public class PowerConfigDao extends BaseDao implements DaoInterface {
	public PowerConfigDao() {
		super("system_powerconfig");
	}

	public List loadByIpaddress(String ip) {
		List list = new ArrayList(5);
		try {
			rs = conn.executeQuery("select * from system_powerconfig where ipaddress='" + ip + "'");
			while (rs.next()) {
				list.add(loadFromRS(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		} finally {
			conn.close();
		}
		return list;
	}

	@Override
	public BaseVo loadFromRS(ResultSet rs) {
		PowerConfig powerConfig = new PowerConfig();
		try {
			powerConfig.setId(rs.getInt("id"));
			powerConfig.setIpaddress(rs.getString("ipaddress"));
			powerConfig.setName(rs.getString("name"));
			powerConfig.setAlarmvalue(rs.getInt("alarmvalue"));
			powerConfig.setAlarmlevel(rs.getString("alarmlevel"));
			powerConfig.setAlarmtimes(rs.getInt("alarmtimes"));
			powerConfig.setEnabled(rs.getInt("enabled"));
			powerConfig.setBak(rs.getString("bak"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return powerConfig;
	}

	public boolean save(BaseVo vo) {
		PowerConfig config = (PowerConfig) vo;
		StringBuffer sql = new StringBuffer(100);
		sql.append("insert into system_powerconfig(ipaddress,name,alarmvalue,alarmlevel,alarmtimes,enbaled,bak)values(");
		sql.append("'");
		sql.append(config.getIpaddress());
		sql.append("','");
		sql.append(config.getName());
		sql.append("',");
		sql.append(config.getAlarmvalue());
		sql.append(",'");
		sql.append(config.getAlarmlevel());
		sql.append(",");
		sql.append(config.getAlarmtimes());
		sql.append(",");
		sql.append(config.getEnabled());
		sql.append(",'");
		sql.append(config.getBak());
		sql.append("')");
		return saveOrUpdate(sql.toString());
	}

	public boolean update(BaseVo vo) {
		PowerConfig config = (PowerConfig) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("update system_powerconfig set ipaddress='");
		sql.append(config.getIpaddress());
		sql.append("',name='");
		sql.append(config.getName());
		sql.append("',alarmvalue=");
		sql.append(config.getAlarmvalue());
		sql.append(",alarmlevel='");
		sql.append(config.getAlarmlevel());
		sql.append(",alarmtimes=");
		sql.append(config.getAlarmtimes());
		sql.append(",enbaled=");
		sql.append(config.getEnabled());
		sql.append(",bak='");
		sql.append(config.getBak());
		sql.append("' where id=");
		sql.append(config.getId());
		return saveOrUpdate(sql.toString());
	}

	public boolean updateAlarmlevelByID(String id, String alarmlevel) {
		StringBuffer sql = new StringBuffer();
		sql.append("update system_powerconfig set alarmlevel='");
		sql.append(alarmlevel);
		sql.append("' where id=");
		sql.append(id);
		return saveOrUpdate(sql.toString());
	}

	public boolean updateEnabled(int id, int enable) {
		StringBuffer sql = new StringBuffer();
		sql.append("update system_powerconfig set enabled=");
		sql.append(enable);
		sql.append(" where id=");
		sql.append(id);
		return saveOrUpdate(sql.toString());
	}

	public boolean updateValue(int id, int value, int times) {
		StringBuffer sql = new StringBuffer();
		sql.append("update system_powerconfig set alarmvalue=");
		sql.append(value);
		sql.append(",alarmtimes=");
		sql.append(times);
		sql.append(" where id=");
		sql.append(id);
		return saveOrUpdate(sql.toString());
	}

}
