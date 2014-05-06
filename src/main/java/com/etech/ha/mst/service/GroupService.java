package com.etech.ha.mst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etech.ha.dao.GroupDAO;
import com.etech.ha.peer.GroupPeer;
import com.etech.system.service.BaseService;

@Service
@Transactional
public class GroupService extends BaseService {
	
	@Autowired
	private GroupDAO groupDao;
	
	public List<GroupPeer> searchAll() {
		return groupDao.searchAll();
	}
	
	public GroupPeer findById(String groupCd) {
		return groupDao.get(groupCd);
	}

	public GroupDAO getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDAO groupDao) {
		this.groupDao = groupDao;
	}
	
}
