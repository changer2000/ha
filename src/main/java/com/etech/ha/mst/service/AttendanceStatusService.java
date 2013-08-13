package com.etech.ha.mst.service;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etech.ha.dao.AttendanceStatusDAO;
import com.etech.ha.peer.AttendanceStatusPeer;

@Transactional
@Service
public class AttendanceStatusService {
	
	@Autowired
	private AttendanceStatusDAO atndcStsDao;
	
	public List<AttendanceStatusPeer> loadAll() {
		return  atndcStsDao.loadAll(); //返回null，原来是没有定义AttendanceStatusPeer对应的hbm文件
		//return atndcStsDao.findAll();
	}
	
	public AttendanceStatusPeer findById(Long id) {
		return atndcStsDao.get(id);
	}
	
	public AttendanceStatusPeer reisger(AttendanceStatusPeer peer) {
		atndcStsDao.saveOrUpdate(peer);
		return peer;
	}
	
	public void delete(String[] ids) {
		if (ids==null || ids.length==0)
			return;
		
		for (String id : ids) {
			AttendanceStatusPeer peer = new AttendanceStatusPeer();
			peer.setId(NumberUtils.createLong(id));
			atndcStsDao.delete(peer);
		}
	}

	public AttendanceStatusDAO getAtndcStsDao() {
		return atndcStsDao;
	}

	public void setAtndcStsDao(AttendanceStatusDAO atndcStsDao) {
		this.atndcStsDao = atndcStsDao;
	}
	
}
