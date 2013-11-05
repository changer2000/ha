package com.etech.system.exceptions;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

import org.apache.commons.lang.StringUtils;

public class ExceptionMsgBean implements Serializable {

	private static final long serialVersionUID = -9039340049126806455L;
	
	private Exception ex;

	public String getErrMessage() {
		StringWriter sw = new StringWriter();
		PrintWriter w = new PrintWriter(sw);
		ex.printStackTrace(w);
		
		return ex.getMessage() 
				//+ "\r\n StackTrace:\r\n" + sw.toString();
				+ "<br/> StackTrace:<br/>" + StringUtils.replace(sw.toString(), "\r\n", "<br/>");
				
	}

	public Exception getEx() {
		return ex;
	}

	public void setEx(Exception ex) {
		this.ex = ex;
	}
	
}
