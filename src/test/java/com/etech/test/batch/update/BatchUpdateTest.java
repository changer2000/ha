package com.etech.test.batch.update;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.etech.ha.peer.HolidayListPeer;
import com.etech.system.utils.HibernateUtils;

public class BatchUpdateTest {

	private static final Log logger = LogFactory.getLog(BatchUpdateTest.class);
	
	private Session session=null;
	
	@Before
	public void setUp() throws Exception {
		session = HibernateUtils.getSession();
	}

	@After
	public void tearDown() throws Exception {
		try {
			if (session!=null && session.isOpen()) {
				session.close();
			}
		} catch (Exception e) {
			logger.error(null, e);
		}
	}
	
	//先执行这个sql ：update t_holiday_list set hldy_id=null where id=27;
	
	@Test
	public void test() {

		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			
			List<HolidayListPeer> list = session.createQuery("from HolidayListPeer").list();
			
			//error session.createQuery("update HolidayListPeer a set a.hldy_id=1 where a.holidayPeer is null");
			session.createSQLQuery("update t_holiday_list a set a.hldy_id=1 where a.hldy_id is null")
				.executeUpdate();	//XXX 尽管做了批量更新，但下面这个查询sql得到的最后一个HolidayListPeer的holidayPeer依然是null。原因就是session的缓存。
			
			//session.clear();	//XXX 但是，如果把这一行的注释去掉，那么下面这个查询sql就能够得到最新的数据了
			
			List<HolidayListPeer> list2 = session.createQuery("from HolidayListPeer").list();
			//assertNotNull(list2);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			
			logger.error(null, e);
			fail("error");
		}
		assertTrue(true);
	}
	
}
