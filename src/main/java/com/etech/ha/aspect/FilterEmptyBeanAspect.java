package com.etech.ha.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class FilterEmptyBeanAspect {
	private static Log logger = LogFactory.getLog(FilterEmptyBeanAspect.class);
	
	@Before("@annotation(com.etech.ha.annotation.NeedFilterEmptyBean)")
	public void filterEmptyBean(JoinPoint jp) throws Exception {
		logger.debug(">>> "+ jp.getTarget().getClass().toString());
		logger.debug(">>> "+ jp.getSignature().getName());
		if (jp.getArgs()!=null && jp.getArgs().length>0) {
			logger.debug(">>> jp.getArgs():"+jp.getArgs()[0].getClass().toString());
		} else {
			logger.debug(">>> jp.getArgs() is null");
		}
	}
}
