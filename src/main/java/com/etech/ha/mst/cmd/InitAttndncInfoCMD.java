package com.etech.ha.mst.cmd;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etech.ha.dao.AttendanceInfoDAO;
import com.etech.ha.peer.HolidayListPeer;
import com.etech.system.core.BaseCMD;

@Service
@Transactional
@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class InitAttndncInfoCMD extends BaseCMD {
	
	@Autowired
	private AttendanceInfoDAO attendanceInfoDao;
	
	private HolidayListPeer listPeer;
	
	private String flg;
	
	public Object doExecute() {
		flg = "testFlg";
		attendanceInfoDao.deleteByHldyList(listPeer);
		
		return null;
	}

	public HolidayListPeer getListPeer() {
		return listPeer;
	}

	public void setListPeer(HolidayListPeer listPeer) {
		this.listPeer = listPeer;
	}

	public AttendanceInfoDAO getAttendanceInfoDao() {
		return attendanceInfoDao;
	}

	public void setAttendanceInfoDao(AttendanceInfoDAO attendanceInfoDao) {
		this.attendanceInfoDao = attendanceInfoDao;
	}

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}
}
