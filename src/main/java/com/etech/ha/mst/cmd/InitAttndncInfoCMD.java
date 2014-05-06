package com.etech.ha.mst.cmd;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etech.ha.dao.AttendanceInfoDAO;
import com.etech.ha.peer.HolidayListPeer;
import com.etech.system.cmd.BaseCMD;

@Service("initAttndncInfoCMD")
@Transactional
@Scope(value="prototype")
public class InitAttndncInfoCMD extends BaseCMD {
	
	private static final Log logger = LogFactory.getLog(InitAttndncInfoCMD.class);
	
	@Autowired
	private AttendanceInfoDAO attendanceInfoDao;
	
	private HolidayListPeer listPeer;
	
	@Override
	public Object doExecute() throws Exception {
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
}
