package com.etech.ha.mst.service;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etech.ha.dao.HolidayListDAO;
import com.etech.ha.peer.HolidayListPeer;

@Service
@Transactional
public class HolidayListService {
	
	@Autowired
	private HolidayListDAO hldyListDao;
	
	
	public List<HolidayListPeer> listAll() {
		return hldyListDao.loadAll();
	}
	
	public HolidayListPeer findById(Long id) {
		return hldyListDao.get(id);
	}
	
	public HolidayListPeer register(HolidayListPeer peer) {
		hldyListDao.saveOrUpdate(peer);
		return peer;
	}
	
	public void delete(String[] ids) {
		if (ids==null || ids.length==0)
			return;
		
		for (String id : ids) {
			HolidayListPeer peer = new HolidayListPeer();
			peer.setId(NumberUtils.createLong(id));
			hldyListDao.delete(peer);
		}
	}
	
	
	public HolidayListDAO getHldyListDao() {
		return hldyListDao;
	}

	public void setHldyListDao(HolidayListDAO hldyListDao) {
		this.hldyListDao = hldyListDao;
	}
	
}
