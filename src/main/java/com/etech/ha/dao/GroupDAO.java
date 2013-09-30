package com.etech.ha.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etech.ha.peer.GroupPeer;
import com.etech.system.dao.BaseDao;


@Repository
public class GroupDAO extends BaseDao<GroupPeer>{
	
	public List<GroupPeer> searchAll() {
		return (List<GroupPeer>) createQuery("from GroupPeer order by group_cd", null).list();
	}
}
