package com.etech.ha.mst.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etech.ha.dao.HolidayDAO;
import com.etech.ha.peer.HolidayPeer;

@Service
@Transactional
public class HolidayService {
	
	@Autowired
	private HolidayDAO hldyDao;
	
	public List<HolidayPeer> initHldyOptions() {
		List<HolidayPeer> list = listAll();
		if (list==null) {
			list = new ArrayList<HolidayPeer>();
		}
		list.add(0, new HolidayPeer());
		return list;
	}
	
	
	public List<HolidayPeer> listAll() {
		return hldyDao.find("from HolidayPeer order by order_no");
	}
	
	public HolidayPeer findById(Long id) {
		return hldyDao.get(id);	//if use hldyDao.load(id), it will cause a error when user click Modify button on hldy_list.jsp.
								//load(id) will return a proxyed object, and will search data when really use this object. so error happened. 
	}
	
	
	public HolidayPeer register(HolidayPeer peer) {
		hldyDao.saveOrUpdate(peer);
		return peer;
	}
	
	
	public void delete(String[] ids) {
		if (ids==null || ids.length==0)
			return;
		
		for (String id : ids) {
			if (StringUtils.isNotBlank(id)) {
				HolidayPeer peer = new HolidayPeer();
				peer.setId(NumberUtils.createLong(id));
				hldyDao.delete(peer);
			}
		}
	}

	public HolidayDAO getHldyDao() {
		return hldyDao;
	}

	public void setHldyDao(HolidayDAO hldyDao) {
		this.hldyDao = hldyDao;
	}
	
}
