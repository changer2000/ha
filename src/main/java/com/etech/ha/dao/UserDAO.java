package com.etech.ha.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.etech.ha.mst.bean.UserListSearchBean;
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
	
	public UserPeer searchPeerByEmpeNum(String empeNum) {
		return this.findPeer("from UserPeer as user where user.empe_num=?", empeNum);
	}
	
	public List<UserPeer> search(UserListSearchBean searchBean) {
		List<Object> paramsList = new ArrayList<Object>();
		StringBuilder buf = new StringBuilder("from UserPeer a ");
		boolean bAddedCondition = false;
		if (StringUtils.isNotBlank(searchBean.getEmpe_num())) {
			if (!bAddedCondition) {
				buf.append(" where ");
				bAddedCondition = true;
			} else {
				buf.append(" and ");
			}
			buf.append(" a.empe_num like ?");
			paramsList.add(searchBean.getEmpe_num() + "%");
		}
		if (StringUtils.isNotBlank(searchBean.getEmpe_name())) {
			if (!bAddedCondition) {
				buf.append(" where ");
				bAddedCondition = true;
			} else {
				buf.append(" and ");
			}
			buf.append(" a.empe_name like ?");
			paramsList.add(searchBean.getEmpe_name() + "%");
		}
		if (StringUtils.isNotBlank(searchBean.getMobile())) {
			if (!bAddedCondition) {
				buf.append(" where ");
				bAddedCondition = true;
			} else {
				buf.append(" and ");
			}
			buf.append(" a.mobile like ?");
			paramsList.add(searchBean.getMobile() + "%");
		}
		if (StringUtils.isNotBlank(searchBean.getTel_no())) {
			if (!bAddedCondition) {
				buf.append(" where ");
				bAddedCondition = true;
			} else {
				buf.append(" and ");
			}
			buf.append(" a.tel_no like ?");
			paramsList.add(searchBean.getTel_no() + "%");
		}
		if (StringUtils.isNotBlank(searchBean.getEmail())) {
			if (!bAddedCondition) {
				buf.append(" where ");
				bAddedCondition = true;
			} else {
				buf.append(" and ");
			}
			buf.append(" a.email like ?");
			paramsList.add(searchBean.getEmail() + "%");
		}
		
		
		List<UserPeer> list = (List<UserPeer>) this.createQuery(buf.toString(), paramsList.toArray()).list();
		return list;
	}
	
}
