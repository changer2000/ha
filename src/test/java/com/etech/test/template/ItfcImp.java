package com.etech.test.template;

public class ItfcImp<T> implements IItfc<T> {
	
	private T var;
	
	public ItfcImp(T var) {
		setVar(var);
	}
	
	@Override
	public T getVar() {
		return var;
	}

	public void setVar(T var) {
		this.var = var;
	}

}
