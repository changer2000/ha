package com.etech.test.template.test;

import com.etech.test.template.Info;

public class TypeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Info<String> info = new Info<String>();
		info.setVar("test");
		func1(info);
		
		Info<Integer> info2 = new Info<Integer>();
		info2.setVar(10);
		func1(info2);
		
		//func2(info);		<=== NG
		func2(info2);
		
		func3(info);
		func3(info2);
		//func3(new Object());		<=== NG
		
		func4(info);
		//func4(info2);		<=== NG
		//func4(new Object());	<=== NG
		
		//func5(info);		<=== NG
		func5(info2);
		//func5(new Object());	<=== NG
		
		//Java泛型无法向上转型
		Info<Object> info3 = null;
		//info3 = info;		<=== NG
		
	}
	
	//private static void func1(Info<Object> info)  <=== NG
	//private static void func1(Info<String> info)  <=== OK
	private static void func1(Info<?> info) {
		System.out.println(info.toString());
	}
	
	private static void func2(Info<? extends Number> info) {
		System.out.println(info.toString());
	}
	
	private static void func3(Info<? extends Object> info) {
		System.out.println(info.toString());
	}
	
	private static void func4(Info<? super String> info) {
		System.out.println(info.toString());
	}
	
	private static void func5(Info<? super Integer> info) {
		System.out.println(info.toString());
	}

}
