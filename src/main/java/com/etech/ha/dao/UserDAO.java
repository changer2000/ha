package com.etech.ha.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etech.ha.peer.UserPeer;
import com.etech.system.dao.BaseDao;

@Repository
public class UserDAO extends BaseDao<UserPeer> {

	public UserPeer searchByEmpeNum(String empeNum) {
		List<UserPeer> list = getHibernateTemplate().find("from UserPeer as user where user.empe_num=?", empeNum);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	public UserPeer searchByEmpeNum2(String empeNum) {
		List<UserPeer> list = this.find("from UserPeer as user where user.empe_num=?", empeNum);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	public UserPeer searchByEmpeNum3(String empeNum) {
		return this.findPeer("from UserPeer as user where user.empe_num=?", empeNum);
	}
	
}
