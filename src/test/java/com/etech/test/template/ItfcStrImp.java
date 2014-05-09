package com.etech.test.template;

public class ItfcStrImp implements IItfc<String> {
	
	private String var;
	
	public ItfcStrImp(String var) {
		this.var = var;
	}
	
	@Override
	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

}
