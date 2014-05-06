package com.etech.system.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.etech.system.cmd.CmdFactory;

public class BaseService {

	@Autowired
	protected CmdFactory cmdFactory;

	public CmdFactory getCmdFactory() {
		return cmdFactory;
	}

	public void setCmdFactory(CmdFactory cmdFactory) {
		this.cmdFactory = cmdFactory;
	}
}
