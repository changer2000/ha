package com.etech.ha.mst.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etech.ha.dao.AttendanceInfoDAO;
import com.etech.ha.dao.HolidayListDAO;
import com.etech.ha.mst.bean.HolidayListSearchBean;
import com.etech.ha.mst.cmd.InitAttndncInfoCMD;
import com.etech.ha.peer.HolidayListPeer;
import com.etech.system.service.BaseService;

@Service
@Transactional
public class HolidayListService extends BaseService {
	
	private static final Log logger = LogFactory.getLog(HolidayListService.class);
	
	@Autowired
	private HolidayListDAO hldyListDao;
	
	@Autowired
	private AttendanceInfoDAO attendanceInfoDao;
	
	
	//@Autowired
	//private InitAttndncInfoCMD initAttndcInfocmd;
	
	public List<HolidayListPeer> search(HolidayListSearchBean searchBean) {
		List<HolidayListPeer> list = hldyListDao.search(searchBean);
		
		//filter duplicate data
		if (list!=null && list.size()>0) {
			Map<Long, Long> map = new HashMap<Long, Long>();
			Iterator<HolidayListPeer> iter = list.iterator();
			while (iter.hasNext()) {
				HolidayListPeer listPeer = iter.next();
				if (map.get(listPeer.getId())==null) {
					map.put(listPeer.getId(), listPeer.getId());
				} else {
					iter.remove();
				}
			}
		}
		return list;
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
	
	public boolean initAttndncInfo(HolidayListPeer listPeer) {
		InitAttndncInfoCMD initAttndcInfocmd = this.cmdFactory.getInitAttndncInfoCMD();
		
		if (logger.isDebugEnabled()) {
			logger.debug(">>> listPeer=" + initAttndcInfocmd.getListPeer());
		}
		
		initAttndcInfocmd.setListPeer(listPeer);
		initAttndcInfocmd.doExecute();
		//attendanceInfoDao.deleteByHldyList(listPeer);
		
		
		
		return true;
	}
	
	public void delete(String[] ids) {
		if (ids==null || ids.length==0)
			return;
		
		for (String id : ids) {
			HolidayListPeer peer = new HolidayListPeer();
			peer.setId(NumberUtils.createLong(id));
			hldyListDao.delete(peer);	//It will delete t_holiday_list_dtl.
			
			attendanceInfoDao.deleteByHldyList(peer);
		}
	}
	
	
	public HolidayListDAO getHldyListDao() {
		return hldyListDao;
	}

	public void setHldyListDao(HolidayListDAO hldyListDao) {
		this.hldyListDao = hldyListDao;
	}

	public AttendanceInfoDAO getAttendanceInfoDao() {
		return attendanceInfoDao;
	}

	public void setAttendanceInfoDao(AttendanceInfoDAO attendanceInfoDao) {
		this.attendanceInfoDao = attendanceInfoDao;
	}
	/*
	public InitAttndncInfoCMD getInitAttndcInfocmd() {
		return initAttndcInfocmd;
	}

	public void setInitAttndcInfocmd(InitAttndncInfoCMD initAttndcInfocmd) {
		this.initAttndcInfocmd = initAttndcInfocmd;
	}
	*/
	
}
