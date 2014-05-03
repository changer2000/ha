package com.etech.assertthat;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class JUnit4ParameterTest {
	
	private int val;
	private String expectedCharNumber;
	
	public JUnit4ParameterTest(int val, String expectedCharNumber) {
		this.val = val;
		this.expectedCharNumber = expectedCharNumber;
	}
	
	@Parameters
	public static Collection<Object[]> getParamters() {
		Object[][] ary = {
				{1, "一"},
				{2, "二"},
				{3, "三"}
		};
		return Arrays.asList(ary);
	}
	
	@Test
	public void test() {
		assertEquals(expectedCharNumber, NumberConvert.convert(val));
	}

}
