package com.etech.ha.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etech.ha.peer.UserPeer;

@Repository
public class UserDAO extends BaseDAO {

	public UserPeer searchByEmpeNum(String empeNum) {
		List<UserPeer> list = getHibernateTemplate().find("from UserPeer as user where user.empe_num=?", empeNum);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
}
