package com.etech.test.template;

public class Info2<T extends Number> {
	
	private T var;

	public T getVar() {
		return var;
	}

	public void setVar(T var) {
		this.var = var;
	}
	
	public String toString() {
		return this.var.toString();
	}
}
