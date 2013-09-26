package com.etech.ha.dao;

import org.springframework.stereotype.Repository;

import com.etech.ha.peer.AttendanceInfoPeer;
import com.etech.ha.peer.HolidayListPeer;
import com.etech.system.dao.BaseDao;

@Repository
public class AttendanceInfoDAO extends BaseDao<AttendanceInfoPeer> {
	
	public void deleteByHldyList(HolidayListPeer listPeer) {
		super.createSQLQuery("delete from t_attendance_info where hldy_list_id=?", listPeer.getId()).executeUpdate();
	}

}
