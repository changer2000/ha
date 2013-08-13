package com.etech.ha.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etech.ha.peer.AttendanceStatusPeer;
import com.etech.system.dao.BaseDao;

@Repository
public class AttendanceStatusDAO extends BaseDao<AttendanceStatusPeer>{

	public List<AttendanceStatusPeer> findAll() {
		return (List<AttendanceStatusPeer>)getHibernateTemplate().find("from AttendanceStatusPeer");
	}
	
	
}
