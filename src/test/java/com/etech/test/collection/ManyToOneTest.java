package com.etech.test.collection;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.FetchType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.etech.ha.peer.HolidayListPeer;
import com.etech.ha.peer.UserPeer;
import com.etech.system.utils.HibernateUtils;

public class ManyToOneTest {
	
	private static final Log logger = LogFactory.getLog(ManyToOneTest.class);
	
	private Session session;
	
	@Before
	public void setUp() throws Exception {
		session = HibernateUtils.getSession();
	}

	@After
	public void tearDown() throws Exception {
		try {
			if (session!=null)
				session.close();
		} catch (Exception e) {
			logger.error("close session error:", e);
		}
	}

	@Test
	public void test() {
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			
			HolidayListPeer hlPeer = (HolidayListPeer) session.get(HolidayListPeer.class, new Long(1));	//XXX 会发出2个sql，因为有个双向关联。
			//1个可行的办法是把HolidayPeer里的fetch=FetchType.EAGER改成FetchType.LAZAY;另外一个估计可以用查询sql来避免
			assertNotNull(hlPeer);
			
			//tx.commit();
			//session.close(); 测试HolidayPeer里fetch=FetchType.EAGER，如果不是FetchType.EAGER，下面的logger.debug会出错。因为对象还没被真正取得
			//session = null;
			
			logger.debug("Holiday start date:" + hlPeer.getHolidayPeer().getHldyList().get(0).getHldy_start());
			
			hlPeer.setEnd_dt(new Date(System.currentTimeMillis()));	//It will call update sql.
			hlPeer.getHolidayPeer().setName("国庆节");	//无论怎么修改HolidayListPeer.holidayPeer处的注解，都无法阻止holidayPeer的update
			
			
			//UserPeer userPeer = (UserPeer) session.get(UserPeer.class, new Long(1));
			//logger.debug(">>> userPeer=" + userPeer);
			
			tx.commit();
		} catch (Exception e) {
			logger.error("error:", e);
			
			tx.rollback();
		}
		
	}

}
