package com.deloitte.hk.boot;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("myFilter")
public class AccesssFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(AccesssFilter.class);
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
//		String url = request.getSession().getServletContext().getRealPath(File.separator);	
		final String origin = request.getHeader("origin");
		if (StringUtils.isNotEmpty(origin))
		{
			response.setHeader("Access-Control-Allow-Origin", origin);
		}
		else
		{
			response.setHeader("Access-Control-Allow-Origin", "*");
		}
		logger.info("Origin:" + origin);

		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
		response.setHeader("Access-Control-Allow-Headers",
				"content-type,access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,serviceName,locale");

		if ("OPTIONS".equalsIgnoreCase(request.getMethod()))
		{
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else
		{
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
