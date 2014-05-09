package com.etech.test.template;

public class ItfcImpTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IItfc<String> a = new ItfcImp<String>("ItfcImp");
		System.out.println(a.getVar());
		
		IItfc<String> b = new ItfcStrImp("ItfcStrImp");
		System.out.println(b.getVar());
	}

}
