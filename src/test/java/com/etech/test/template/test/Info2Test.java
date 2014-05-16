package com.etech.test.template.test;

import com.etech.test.template.Info2;

//通过泛型方法返回某类特定泛型类型实例
public class Info2Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Info2<Integer> a = func2(10);
		System.out.println(a);
	}
	
	/* compile NG
	public static <T> Info2<T> func(T param) {
		Info2<T> rs = new Info2<T>();
		rs.setVar(param);
		return rs;
	}
	*/
	
	public static <T extends Number> Info2<T> func2(T param) {
		Info2<T> rs = new Info2<T>();
		rs.setVar(param);
		return rs;
	}
}
