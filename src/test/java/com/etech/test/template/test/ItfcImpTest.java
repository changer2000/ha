package com.etech.test.template.test;

import com.etech.test.template.IItfc;
import com.etech.test.template.ItfcImp;
import com.etech.test.template.ItfcStrImp;

public class ItfcImpTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IItfc<String> a = new ItfcImp<String>("ItfcImp");
		System.out.println(a.getVar());
		
		IItfc b = new ItfcStrImp("ItfcStrImp");
		System.out.println(b.getVar());
	}

}
