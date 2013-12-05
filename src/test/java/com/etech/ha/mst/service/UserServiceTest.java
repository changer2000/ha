package com.etech.ha.mst.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.etech.ha.peer.UserPeer;

import static org.junit.Assert.*;

@DataSet("ha_users.xls")
public class UserServiceTest extends UnitilsJUnit4 {
	
	@SpringApplicationContext("root-config_test.xml")
	private ApplicationContext applicationContext;
	
	@SpringBean("userService")
	private UserService userService;
	
	@Test
	public void testUserService() {
		assertNotNull(applicationContext);
		assertNotNull(userService);
	}
	
	@Test
	public void testFindUser() {
		UserPeer user =  userService.searchByEmpeNum("1404");
		assertNotNull(user);
		assertEquals(user.getEmpe_name(), "li");
		
		user = userService.searchByEmpeNum("1405");
		assertNull(user);
	}
}
