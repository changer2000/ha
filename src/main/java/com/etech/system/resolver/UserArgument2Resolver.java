package com.etech.system.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import com.etech.ha.constants.HaConstants;
import com.etech.system.bean.UserInfo;

public class UserArgument2Resolver implements WebArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
		
		if (methodParameter.getParameterType().equals(UserInfo.class)) {
			return webRequest.getAttribute(HaConstants.SESSION_KEY_USER_INFO, RequestAttributes.SCOPE_SESSION);
		}
		
		return UNRESOLVED;
	}

}
