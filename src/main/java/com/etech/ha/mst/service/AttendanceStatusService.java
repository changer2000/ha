package com.etech.ha.mst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etech.ha.mst.dao.AttendanceStatusDAO;
import com.etech.ha.peer.AttendanceStatusPeer;

@Transactional
@Service
public class AttendanceStatusService {
	
	@Autowired
	private AttendanceStatusDAO atndcStsDao;
	
	public List<AttendanceStatusPeer> loadAll() {
		return  atndcStsDao.loadAll(); //返回null，搞不懂
		//return atndcStsDao.findAll();
	}

	public AttendanceStatusDAO getAtndcStsDao() {
		return atndcStsDao;
	}

	public void setAtndcStsDao(AttendanceStatusDAO atndcStsDao) {
		this.atndcStsDao = atndcStsDao;
	}
	
}
