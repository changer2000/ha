package com.etech.test.template.test;

import com.etech.test.template.Info;


public class TMethodTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer a = testA(1);
		String b = testA("s");
		System.out.println(a);
		System.out.println(b);
		
		testB(1);
		testB("s");
		
		Info<Integer> rs = testC(10);
		System.out.println(rs);
	}
	
	//参数和返回都是Java泛型方法
	public static <T> T testA(T t) {
		System.out.println(t.getClass() + ":" + t);
		return t;
	}
	
	//参数是Java泛型方法
	public static <T> void testB(T t) {
		System.out.println(t.getClass() + ":" + t);
	}
	
	//通过泛型方法返回泛型类型实例
	public static <T> Info<T> testC(T param) {
		Info<T> rs = new Info<T>();
		rs.setVar(param);
		return rs;
	}

}
