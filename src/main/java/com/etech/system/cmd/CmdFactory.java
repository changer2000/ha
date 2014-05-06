package com.etech.system.cmd;

import net.ambre.spring.annotation.LookupMethod;

import com.etech.ha.mst.cmd.InitAttndncInfoCMD;

public interface CmdFactory {
	
	@LookupMethod(beanRef="initAttndncInfoCMD")
	public InitAttndncInfoCMD getInitAttndncInfoCMD();
}
