package com.etech.test.query;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.etech.ha.mst.bean.HolidayListBean;
import com.etech.ha.peer.HolidayListPeer;
import com.etech.ha.peer.UserPeer;
import com.etech.system.utils.HibernateUtils;
import com.etech.test.collection.ManyToOneTest;

public class QueryTest {
		
	private static final Log logger = LogFactory.getLog(QueryTest.class);
	
	private Session session;
	
	@Before
	public void setUp() throws Exception {
		session = HibernateUtils.getSession();
	}

	@After
	public void tearDown() throws Exception {
		if (session!=null) {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	
	public void test4WholeObject() {
		try {
			Query query = session.createQuery("from UserPeer");
			List<UserPeer> list = query.list();
			if (list!=null && list.size()>0) {
				Iterator<UserPeer> iter = list.iterator();
				while (iter.hasNext()) {
					UserPeer peer = iter.next();
					System.out.println(peer);
					assertNotNull(peer);
				}
			}
			
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}
	
	//@Test
	public void test4OneColumns() {
		try {
			Query query = session.createQuery("select empe_num from UserPeer");
			List<String> list = query.list();
			if (list!=null && list.size()>0) {
				Iterator<String> iter = list.iterator();
				while (iter.hasNext()) {
					String fieldVal = iter.next();
					System.out.println(fieldVal);
					assertNotNull(fieldVal);
				}
			}
			
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}
	
	//@Test
	public void test4SomeColumns() {
		try {
			Query query = session.createQuery("select empe_num,empe_name from UserPeer");
			List<Object[]> list = query.list();
			if (list!=null && list.size()>0) {
				Iterator<Object[]> iter = list.iterator();
				while (iter.hasNext()) {
					Object[] fieldsAry = iter.next();
					System.out.println(fieldsAry[0] + ":" + fieldsAry[1]);
					assertNotNull(fieldsAry);
				}
			}
			
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}
	
	//@Test
	public void test4SomeColumnsAsList() {
		try {
			Query query = session.createQuery("select new list(empe_num,empe_name) from UserPeer");
			List<List> list = query.list();
			for (List list1: list) {
				System.out.println(list1.get(0)+":"+list1.get(1));
				assertNotNull(list1);
			}
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}
	
	//@Test
	public void test4SomeColumnsAsMap() {
		try {
			Query query = session.createQuery("select new map(empe_num,empe_name) from UserPeer");	//XXX
			List<Map> list = query.list();
			for (Map map: list) {
				System.out.println(map.get("0")+":"+map.get("1"));		//XXX
				assertNotNull(map);
			}
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}
	//@Test
	public void test4SomeColumnsAsMap2() {
		try {
			Query query = session.createQuery("select new map(empe_num as empe_num,empe_name as empe_name) from UserPeer");	//XXX
			List<Map> list = query.list();
			for (Map map: list) {
				System.out.println(map.get("empe_num")+":"+map.get("empe_name"));		//XXX
				assertNotNull(map);
			}
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}
	
	//返回的是对象
	//@Test
	public void test4SomeColumnsAsObject() {
		try {
			Query query = session.createQuery("select new UserPeer(id, empe_num ,empe_name) from UserPeer");	//XXX
			List<UserPeer> list = query.list();
			for (UserPeer peer: list) {
				System.out.println(peer.getEmpe_num()+":"+peer.getEmpe_name());
				assertNotNull(peer);
			}
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}
	
	//按照参数进行查询
	//@Test
	public void testSearchCondition() {
		try {
			Query query = session.createQuery("select new UserPeer(id, empe_num ,empe_name) from UserPeer where empe_num=? and empe_name=?");	//XXX
			//第一种方法
			//query.setParameter(0, "1404", Hibernate.STRING);
			//query.setParameter(1, "lixichun", Hibernate.STRING);
			
			//第二种方法
			query.setParameter(0, "1404");
			query.setParameter(1, "lixichun");
			List<UserPeer> list = query.list();
			for (UserPeer peer: list) {
				System.out.println(peer.getEmpe_num()+":"+peer.getEmpe_name());
				assertNotNull(peer);
			}
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}
	
	//按照名字参数进行查询
	//@Test
	public void testSearchCondition2() {
		try {
			Query query = session.createQuery("select new UserPeer(id, empe_num ,empe_name) from UserPeer where empe_num=:empe_num and empe_name=:empe_name");	//XXX
			
			query.setParameter("empe_num", "1404");
			query.setParameter("empe_name", "lixichun");
			List<UserPeer> list = query.list();
			for (UserPeer peer: list) {
				System.out.println(peer.getEmpe_num()+":"+peer.getEmpe_name());
				assertNotNull(peer);
			}
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}
	
	//测试in语句
	//@Test
	public void testInSql() {
		try {
			Query query = session.createQuery("select new UserPeer(id, empe_num ,empe_name) from UserPeer where empe_num in (:empe_num)");	//XXX
			
			query.setParameterList("empe_num", new Object[] {"1404","1234"});
			List<UserPeer> list = query.list();
			for (UserPeer peer: list) {
				System.out.println(peer.getEmpe_num()+":"+peer.getEmpe_name());
				assertNotNull(peer);
			}
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}
	
	//测试数据库函数
	//@Test
	public void testDbFunction() {
		try {
			Query query = session.createQuery("from HolidayListPeer where date_format(hldy_end,'%Y-%m-%d')=?");	//XXX
			
			query.setParameter(0, "2013-09-09");
			List<HolidayListPeer> list = query.list();
			for (HolidayListPeer peer: list) {
				System.out.println(peer.getId());
				assertNotNull(peer);
			}
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}
	
	//测试关联查询
	//@Test
	public void testRelationSearch() {
		try {
			Query query = session.createQuery("from HolidayListPeer a where a.end_dt=? and a.holidayPeer.name=?");	//XXX
			//Calendar c = Calendar.getInstance();
			//c.set(Calendar.DAY_OF_MONTH, 9);
			Date dt = DateUtils.parseDate("2013-09-11", new String[] {"yyyy-MM-dd"});//new Date(c.getTimeInMillis());
			
			query.setParameter(0, dt);
			query.setParameter(1, "国庆节2");
			List<HolidayListPeer> list = query.list();
			for (HolidayListPeer peer: list) {
				System.out.println(peer.getId());
				assertNotNull(peer);
			}
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}

	//测试内连接查询:返回的list中每个元素是一个对象数组
	//@Test
	public void testRelationInnerJoin() {
		try {
			Query query = session.createQuery("from HolidayListPeer a join a.holidayPeer b where a.end_dt=?");	//XXX
			//Calendar c = Calendar.getInstance();
			//c.set(Calendar.DAY_OF_MONTH, 9);
			Date dt = DateUtils.parseDate("2013-09-11", new String[] {"yyyy-MM-dd"});//new Date(c.getTimeInMillis());
			
			query.setParameter(0, dt);
			List<Object[]> list = query.list();
			for (Object[] rsAry: list) {
				System.out.println(rsAry[0]);
				System.out.println(rsAry[1]);
				assertNotNull(rsAry[0]);
			}
			assertNotNull(list);
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}

	//测试昨连接查询:返回的list中每个元素是一个对象数组
	//@Test
	public void testRelationLeftJoin() {
		try {
			Query query = session.createQuery("from HolidayListPeer a left join a.holidayPeer b where a.end_dt=?");	//XXX
			//Calendar c = Calendar.getInstance();
			//c.set(Calendar.DAY_OF_MONTH, 9);
			Date dt = DateUtils.parseDate("2013-09-11", new String[] {"yyyy-MM-dd"});//new Date(c.getTimeInMillis());
			
			query.setParameter(0, dt);
			List<Object[]> list = query.list();
			for (Object[] rsAry: list) {
				System.out.println(rsAry[0]);
				System.out.println(rsAry[1]);
				assertNotNull(rsAry[0]);
			}
			assertNotNull(list);
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}
	
	//测试集合查询方法，返回list中每个元素是Object数组
	//@Test
	public void testGroupSearch() {
		try {
			Query query = session.createQuery("select a.holidayPeer.name,count(a) from HolidayListPeer a group by a.holidayPeer.id order by a.holidayPeer.name");	//XXX
			List<Object[]> list = query.list();
			for (Object[] rsAry: list) {
				System.out.println(rsAry[0]);
				System.out.println(rsAry[1]);
				assertNotNull(rsAry[0]);
			}
			assertNotNull(list);
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}

	//测试集合查询方法,返回对象
	@Test
	public void testGroupSearchAsObject() {
		try {
			Query query = session.createQuery("select new com.etech.ha.mst.bean.HolidayListBean(a.holidayPeer.name,count(a)) from HolidayListPeer a group by a.holidayPeer.id order by a.holidayPeer.name");	//XXX
			List<HolidayListBean> list = query.list();
			for (HolidayListBean bean: list) {
				System.out.println(bean.getName());
				System.out.println(bean.getCnt());
				assertNotNull(bean);
			}
			assertNotNull(list);
		} catch (Exception e) {
			logger.error("", e);
			fail("error");
		}
	}
}
