package com.etech.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.etech.ha.constants.HaConstants;
import com.etech.system.bean.UserInfo;

public class LoginCheckFilter implements Filter {
	
	private static final Log logger = LogFactory.getLog(LoginCheckFilter.class);
	
	protected FilterConfig filterConfig = null;
	protected String contextPath;
	protected String[] excludePaths;
	
	@Override
	public void destroy() {
		this.filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		//String path = request.getPathInfo();  //返回null
		String path = request.getServletPath();
		if (logger.isDebugEnabled())
			logger.debug(">>> filter : " + path);
		
		if (this.excludePaths!=null && this.excludePaths.length>0) {
			boolean bFound = false;
			for (int i=0; i<this.excludePaths.length; i++) {
				if (path.equals("/") || path.indexOf(this.excludePaths[i])>=0) {
					bFound = true;
					break;
				}
			}
			if (!bFound) {
				if (logger.isDebugEnabled())
					logger.debug(">>> filter check... ");
				
				UserInfo userInfo = (UserInfo) request.getSession().getAttribute(HaConstants.SESSION_KEY_USER_INFO);
				if (userInfo==null) {
					if (logger.isDebugEnabled())
						logger.debug(">>> filter check fail. ");
					response.sendRedirect(this.contextPath + "/");
					return;
				} else {
					if (logger.isDebugEnabled())
						logger.debug(">>> filter check pass. ");
				}
			} else {
				if (logger.isDebugEnabled())
					logger.debug(">>> filter skip.");
			}
		}
		filterChain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		
		this.contextPath = filterConfig.getServletContext().getContextPath();
		
		String excludePath = filterConfig.getInitParameter("excludePath");
		if (StringUtils.isNotBlank(excludePath))
			this.excludePaths = excludePath.split(","); 
	}

}
