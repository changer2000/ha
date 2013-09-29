package com.etech.ha.mst.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etech.ha.constants.HaConstants;
import com.etech.ha.dao.UserDAO;
import com.etech.ha.login.bean.LoginForm;
import com.etech.ha.mst.bean.UserListSearchBean;
import com.etech.ha.peer.UserPeer;

@Transactional
@Service
public class UserService {
	
	@Autowired
	private UserDAO userDao;
	
	public UserPeer checkLogin(LoginForm user) {
		UserPeer dbUser = userDao.searchPeerByEmpeNum(user.getEmpe_num());
		if (dbUser==null || !StringUtils.equals(user.getPwd(), dbUser.getPwd())) {
			return null;
		} else {
			return dbUser;
		}
	}
	
	public UserPeer searchByEmpeNum(String empeNum) {
		return userDao.searchByEmpeNum(empeNum);
	}
	
	public List<UserPeer> search(UserListSearchBean searchBean) {
		return userDao.search(searchBean);
	}
	
	public void register(UserPeer peer) {
		userDao.saveOrUpdate(peer);
	}
	
	public void delete(String[] selKey) {
		if (selKey!=null && selKey.length>0) {
			for (String empeNum : selKey) {
				UserPeer peer = new UserPeer();
				peer.setEmpe_num(empeNum);
				userDao.delete(peer);
			}
		}
	}
	
	
	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
}
