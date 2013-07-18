package com.etech.ha.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etech.ha.dao.UserDAO;
import com.etech.ha.peer.UserPeer;

@Transactional
@Service
public class UserService {
	
	@Autowired
	private UserDAO userDao;
	
	public boolean checkLogin(UserPeer user) {
		UserPeer dbUser = userDao.searchByEmpeNum(user.getEmpe_num());
		if (dbUser==null || !StringUtils.equals(user.getPwd(), dbUser.getPwd())) {
			return false;
		} else {
			return true;
		}
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
}
