package com.etech.assertthat;

public class NumberConvert {
	
	private static final String[] charNumber = new String[] {
		"一", "二", "三", "四", "五", 
		"六", "七", "八", "九", "十" 
	};
	
	public static String convert(int val) {
		return charNumber[val-1];
	}
	
}
