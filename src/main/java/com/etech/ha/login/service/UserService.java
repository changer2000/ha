package com.etech.ha.login.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etech.ha.dao.UserDAO;
import com.etech.ha.login.bean.LoginForm;
import com.etech.ha.peer.UserPeer;

@Transactional
@Service
public class UserService {
	
	@Autowired
	private UserDAO userDao;
	
	public UserPeer checkLogin(LoginForm user) {
		//UserPeer dbUser = userDao.searchByEmpeNum2(user.getEmpe_num());
		UserPeer dbUser = userDao.searchByEmpeNum3(user.getEmpe_num());
		if (dbUser==null || !StringUtils.equals(user.getPwd(), dbUser.getPwd())) {
			return null;
		} else {
			return dbUser;
		}
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
}
