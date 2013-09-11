package com.etech.test.collection;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.FetchType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.CascadeType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.etech.ha.peer.HolidayListPeer;
import com.etech.ha.peer.HolidayPeer;
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

	
	public void test() {
		session.setFlushMode(FlushMode.AUTO);
		
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			
			HolidayListPeer hlPeer = (HolidayListPeer) session.get(HolidayListPeer.class, new Long(1));	//XXX 会发出2个sql，因为有个双向关联。
			//1个可行的办法是把HolidayPeer里的fetch=FetchType.EAGER改成FetchType.LAZAY;另外一个估计可以用查询sql来避免
			assertNotNull(hlPeer);
			
			//tx.commit();
			//session.close(); //测试HolidayPeer里fetch=FetchType.EAGER，如果不是FetchType.EAGER，下面的logger.debug会出错。因为对象还没被真正取得
			//session = null;
			
			//  logger.debug("Holiday start date:" + hlPeer.getHolidayPeer().getHldyList().get(0).getHldy_start());
			
			hlPeer.setEnd_dt(new Date(System.currentTimeMillis()));	//It will call update sql.
			session.persist(hlPeer);
			hlPeer.getHolidayPeer().setName("国庆节");	//无论怎么修改HolidayListPeer.holidayPeer处的注解，都无法阻止holidayPeer的update
			hlPeer.setHolidayPeer(null);
			
			//UserPeer userPeer = (UserPeer) session.get(UserPeer.class, new Long(1));
			//logger.debug(">>> userPeer=" + userPeer);
			
			HolidayListPeer hlPeer2 = new HolidayListPeer();	//这个peer不会被保存进数据库
			hlPeer2.setHldy_year(2013);
			hlPeer2.setHolidayPeer(hlPeer.getHolidayPeer());
			hlPeer2.setHldy_start(new Date());
			hlPeer2.setHldy_end(new Date());
			hlPeer2.setStart_dt(new Date());
			hlPeer2.setEnd_dt(new Date());
			
			
			
			tx.commit();
			System.out.println("hlPeer2.getId() >>> " + hlPeer2.getId());
		} catch (Exception e) {
			logger.error("error:", e);
			
			tx.rollback();
			fail();
		}
		
	}
	
	
	public void test2() {
		logger.debug("----------------------------------------------------");
		logger.debug("----------------------------------------------------");
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			HolidayPeer oldhPeer = null;
			HolidayListPeer hlPeer = (HolidayListPeer) session.get(HolidayListPeer.class, new Long(23));	//XXX 会发出2个sql，因为有个双向关联。
			//1个可行的办法是把HolidayPeer里的fetch=FetchType.EAGER改成FetchType.LAZAY;另外一个估计可以用查询sql来避免
			assertNotNull(hlPeer);
			oldhPeer = hlPeer.getHolidayPeer();
			
			tx.commit();
			session.close(); //测试HolidayPeer里fetch=FetchType.EAGER，如果不是FetchType.EAGER，下面的logger.debug会出错。因为对象还没被真正取得
			session = null;
			
			logger.debug("Holiday start date:" + hlPeer.getHolidayPeer().getHldyList().get(0).getHldy_start());
			
			//下面2行代码，虽然改了数据，依然不会保存进数据库，因为是detached 数据
			hlPeer.setEnd_dt(new Date(System.currentTimeMillis()));	
			hlPeer.getHolidayPeer().setName("国庆节2");
			
			
			//UserPeer userPeer = (UserPeer) session.get(UserPeer.class, new Long(1));
			//logger.debug(">>> userPeer=" + userPeer);
			
			setUp();
			tx = session.getTransaction();
			tx.begin();
			
			HolidayListPeer hlPeer2 = new HolidayListPeer();
			hlPeer2.setHldy_year(2013);
			hlPeer2.setHolidayPeer(hlPeer.getHolidayPeer());
			hlPeer2.setHldy_start(new Date());
			hlPeer2.setHldy_end(new Date());
			hlPeer2.setStart_dt(new Date());
			hlPeer2.setEnd_dt(new Date());
			
			HolidayPeer nhPeer = new HolidayPeer();
			nhPeer.setId(1L);
			nhPeer.setName("test1");
			//hlPeer.setHolidayPeer(nhPeer);	//session.persist(hlPeer) will be error : org.hibernate.PersistentObjectException: detached entity passed to persist: com.etech.ha.peer.HolidayListPeer
			//hlPeer.setHolidayPeer(oldhPeer);	//because HolidayListPeer's CascadeType.SAVE_UPDATE
			//session.persist(hlPeer);
			
			session.saveOrUpdate(hlPeer);	//It is OK.	
											//It will insert HolidayListPeer, and update HolidayPeer.
			
			tx.commit();
			System.out.println("hlPeer2.getId() >>> " + hlPeer2.getId());
		} catch (Exception e) {
			logger.error("error:", e);
			
			tx.rollback();
			fail();
		}
		
	}
	
	@Test
	public void test3() {
		logger.debug("----------------------------------------------------");
		logger.debug("----------------------------------------------------");
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			HolidayPeer oldhPeer = null;
			HolidayListPeer hlPeer = (HolidayListPeer) session.get(HolidayListPeer.class, new Long(23));	//XXX 会发出2个sql，因为有个双向关联。
			//1个可行的办法是把HolidayPeer里的fetch=FetchType.EAGER改成FetchType.LAZAY;另外一个估计可以用查询sql来避免
			assertNotNull(hlPeer);
			oldhPeer = hlPeer.getHolidayPeer();
			
			tx.commit();
			session.close(); //测试HolidayPeer里fetch=FetchType.EAGER，如果不是FetchType.EAGER，下面的logger.debug会出错。因为对象还没被真正取得
			session = null;
			
			logger.debug("Holiday start date:" + hlPeer.getHolidayPeer().getHldyList().get(0).getHldy_start());
			
			//下面2行代码，虽然改了数据，依然不会保存进数据库，因为是detached 数据
			hlPeer.setEnd_dt(new Date(System.currentTimeMillis()));	
			hlPeer.getHolidayPeer().setName("国庆节");	//无论怎么修改HolidayListPeer.holidayPeer处的注解，都无法阻止holidayPeer的update
			
			
			//UserPeer userPeer = (UserPeer) session.get(UserPeer.class, new Long(1));
			//logger.debug(">>> userPeer=" + userPeer);
			
			setUp();
			tx = session.getTransaction();
			tx.begin();
			
			HolidayListPeer hlPeer2 = new HolidayListPeer();
			hlPeer2.setHldy_year(2013);
			//hlPeer2.setHolidayPeer(hlPeer.getHolidayPeer());
			hlPeer2.setHldy_start(new Date());
			hlPeer2.setHldy_end(new Date());
			hlPeer2.setStart_dt(new Date());
			hlPeer2.setEnd_dt(new Date());
			
			HolidayPeer newHPeer = new HolidayPeer();
			newHPeer.setId(1L);
			newHPeer.setName("test1");			//HolidayListPeer's CascadeType.PERSIST
			hlPeer.setHolidayPeer(newHPeer);	//session.persist(hlPeer) will STILL be error : org.hibernate.PersistentObjectException: detached entity passed to persist: com.etech.ha.peer.HolidayListPeer
			
			session.lock(oldhPeer, LockMode.NONE);	//尽管lock了，但oldhPeer依然是detached状态：估计原始状态就是瞬态的(不是游离态)
			session.lock(oldhPeer, LockMode.READ);
			hlPeer.setHolidayPeer(oldhPeer);
			
			session.persist(hlPeer);	//session.persist(hlPeer) will STILL be error
			//session.update(hlPeer);		//It will update HolidayListPeer, but will not update HolidayPeer.
			
			//以下4行会出错，因为hlPeer2的主键为空。
			//因为update()的对象是游离态的
			//session.update(hlPeer2);	//It will be error : org.hibernate.TransientObjectException: The given object has a null identifier:
			//因为replicate()的对象是游离态的
			//session.replicate(hlPeer2, ReplicationMode.IGNORE);	//org.hibernate.TransientObjectException: instance with null id passed to replicate()
			//session.replicate(hlPeer2, ReplicationMode.LATEST_VERSION);	//error 同上
			//session.replicate(hlPeer2, ReplicationMode.OVERWRITE);	//error 同上
			session.save(hlPeer2);	//成功
			
			tx.commit();
			System.out.println("hlPeer2.getId() >>> " + hlPeer2.getId());
		} catch (Exception e) {
			logger.error("error:", e);
			
			tx.rollback();
			fail();
		}
		
	}

	
	public void testSave() {
		logger.debug("----------------------------------------------------");
		logger.debug("----------------------------------------------------");
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			
			HolidayPeer hPeer = new HolidayPeer();
			hPeer.setName("test");
			
			HolidayListPeer hlPeer2 = new HolidayListPeer();
			hlPeer2.setHldy_year(2013);
			hlPeer2.setHolidayPeer(hPeer);
			hlPeer2.setHldy_start(new Date());
			hlPeer2.setHldy_end(new Date());
			hlPeer2.setStart_dt(new Date());
			hlPeer2.setEnd_dt(new Date());
			session.save(hlPeer2);	//当HolidayListPeer中HolidayPeer处的注解为CascadeType.SAVE_UPDATE是，能够正常执行；
									//但当注解是CascadeType.PERSIST时，会报org.hibernate.TransientObjectException: object references an unsaved transient instance - save the transient instance before flushing
			
			tx.commit();
			System.out.println("hPeer.getId() >>> " + hPeer.getId());
			System.out.println("hlPeer2.getId() >>> " + hlPeer2.getId());
		} catch (Exception e) {
			logger.error("error:", e);
			
			tx.rollback();
			fail();
		}
		
	}
	
	
	public void testRemove() {	//更多解释，见readme.txt
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			
			HolidayListPeer hlPeer = (HolidayListPeer) session.get(HolidayListPeer.class, new Long(18));	//XXX 会发出2个sql，因为有个双向关联。
			assertNotNull(hlPeer);
			//hlPeer.setHolidayPeer(null);
			session.delete(hlPeer);
			
			tx.commit();
			
			tearDown();
			setUp();
			
			tx = session.getTransaction();
			tx.begin();
			hlPeer = (HolidayListPeer) session.get(HolidayListPeer.class, new Long(20));	//XXX 会发出2个sql，因为有个双向关联。
			assertNull(hlPeer);
			tx.commit();
		} catch (Exception e) {
			logger.error("error:", e);
			
			tx.rollback();
			fail();
		}
		
	}
}
