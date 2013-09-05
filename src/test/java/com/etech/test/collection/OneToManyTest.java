package com.etech.test.collection;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.etech.ha.peer.HolidayListPeer;
import com.etech.ha.peer.HolidayPeer;
import com.etech.system.utils.HibernateUtils;

public class OneToManyTest {
	private static final Log logger = LogFactory.getLog(OneToManyTest.class);
	
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
		Transaction tx = session.beginTransaction();
		
		try {
			HolidayPeer peer = (HolidayPeer) session.get(HolidayPeer.class, new Long(1));
			assertNotNull(peer);
			
			for (HolidayListPeer hlPeer : peer.getHldyList()) {
				hlPeer.setEnd_dt(new Date(System.currentTimeMillis()));	//将会引起update
				logger.debug(">>>>>> HolidayListPeer = " + hlPeer);
			}
			
			peer.setName("国庆节");	//将会引起update
			
			tx.commit();
		} catch (Exception e) {
			logger.error("error:", e);
			
			tx.rollback();
		}
	}

}
