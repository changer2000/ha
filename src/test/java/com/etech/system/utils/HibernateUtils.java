package com.etech.system.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.etech.ha.peer.AttendanceStatusPeer;
import com.etech.ha.peer.HolidayListPeer;
import com.etech.ha.peer.HolidayPeer;
import com.etech.ha.peer.UserPeer;

public class HibernateUtils {
	
	private static final Log logger = LogFactory.getLog(HibernateUtils.class);
	private static final SessionFactory sessionFactory =new AnnotationConfiguration()
		.addPackage("com.eteth.ha.peer")
		.addAnnotatedClass(AttendanceStatusPeer.class)
		.addAnnotatedClass(HolidayPeer.class)
		.addAnnotatedClass(HolidayListPeer.class)
		.addAnnotatedClass(UserPeer.class)
		.configure("hibernate.cfg.xml")
		.buildSessionFactory()
		;
	
	public static Session getSession() {
		return sessionFactory.openSession();
	}
}
