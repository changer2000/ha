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

import com.etech.system.bean.Menu;

public class AccessCheckFilter implements Filter {

	private static final Log logger = LogFactory.getLog(AccessCheckFilter.class);
	
	protected FilterConfig filterConfig = null;
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
		

		String path = request.getServletPath();
		if (logger.isDebugEnabled())
			logger.debug(">>> access check : " + path);

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
					logger.debug(">>> access check... ");
				
				Menu menu = (Menu) request.getSession().getAttribute("menu");
				if (logger.isDebugEnabled())
					logger.debug(">>> access check, menu : " + menu);
				
				//TODO
			} else {
				if (logger.isDebugEnabled())
					logger.debug(">>> access skip.");
			}
		}
		filterChain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		String excludePath = filterConfig.getInitParameter("excludePath");
		if (StringUtils.isNotBlank(excludePath))
			this.excludePaths = excludePath.split(","); 
	}
}
