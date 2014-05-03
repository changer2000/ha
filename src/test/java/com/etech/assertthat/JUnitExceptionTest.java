package com.etech.assertthat;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JUnitExceptionTest {
	
	private User user;
	
	@Before
	public void init() {
		user = null;
	}
	
	@Test(expected=NullPointerException.class)
	public void test() {
		user.getUserName();
	}

}
