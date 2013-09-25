package com.etech.ha.mst.service;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etech.ha.dao.HolidayListDAO;
import com.etech.ha.mst.bean.HolidayListSearchBean;
import com.etech.ha.peer.HolidayListPeer;

@Service
@Transactional
public class HolidayListService {
	
	@Autowired
	private HolidayListDAO hldyListDao;
	
	public List<HolidayListPeer> search(HolidayListSearchBean searchBean) {
		return hldyListDao.search(searchBean);
	}
	
	public List<HolidayListPeer> listAll() {
		return hldyListDao.loadAll();
	}
	
	public HolidayListPeer findById(Long id) {
		return hldyListDao.get(id);
	}
	
	public HolidayListPeer register(HolidayListPeer peer) {
		boolean isCreate = false;
		if (peer.getId()!=null) {
			HolidayListPeer dbPeer = findById(peer.getId());
			if (dbPeer==null) {
				isCreate = true;
				peer.setId(null);
			} else {
				//hldyListDao.getSession().evict(dbPeer);
				hldyListDao.getSession().clear();
			}
		} else {
			isCreate = true;
		}
		
		hldyListDao.saveOrUpdate(peer);
		if (!isCreate) {
			//TODO do initialize holiday or work date status. 
		}
		return peer;
	}
	
	public void initDate(HolidayListPeer peer) {
		
	}
	
	public void delete(String[] ids) {
		if (ids==null || ids.length==0)
			return;
		
		for (String id : ids) {
			HolidayListPeer peer = new HolidayListPeer();
			peer.setId(NumberUtils.createLong(id));
			hldyListDao.delete(peer);	//TODO need confirm whether delete t_holiday_list_dtl.
		}
	}
	
	
	public HolidayListDAO getHldyListDao() {
		return hldyListDao;
	}

	public void setHldyListDao(HolidayListDAO hldyListDao) {
		this.hldyListDao = hldyListDao;
	}
	
}
