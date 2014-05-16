package com.etech.test.template.test;

public class GenericsArrayTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] aryInt = func1(1,2,3,4);
		for (Integer val : aryInt) {
			System.out.println(val);
		}
	}
	
	public static <T> T[] func1(T...ts) {
		System.out.println(ts[3]);
		return ts;
	}

}
