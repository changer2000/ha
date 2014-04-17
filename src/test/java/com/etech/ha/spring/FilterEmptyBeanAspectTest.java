package com.etech.ha.spring;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.etech.ha.peer.HolidayListDtlPeer;
import com.etech.ha.peer.HolidayListPeer;

public class FilterEmptyBeanAspectTest {
	private Log logger = LogFactory.getLog(FilterEmptyBeanAspectTest.class);
	
	private static ApplicationContext context = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("classpath:root-config.xml");
	}

	@Test
	public void test() {
		HolidayListPeer peer = (HolidayListPeer) context.getBean("holidayListPeer");
		logger.debug("begin peer.getHldyListDtlList()...");
		peer.getHldyListDtlList();
		logger.debug("end peer.getHldyListDtlList()");
		
		logger.debug("begin peer.setHldyListDtlList()...");
		peer.setHldyListDtlList(new ArrayList<HolidayListDtlPeer>());
		logger.debug("end peer.setHldyListDtlList()");
	}

}
