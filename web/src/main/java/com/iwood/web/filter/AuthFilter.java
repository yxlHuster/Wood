package com.iwood.web.filter;

import com.iwood.web.model.AuthedUser;
import com.iwood.web.util.CookieUtils;
import org.springframework.stereotype.Repository;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: yongleixiao
 * Date: 15-3-1
 * Time: 上午11:27
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class AuthFilter implements Filter {

	public static final String LOGIN_USER_ATTR = "loginUser";


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String uri = request.getRequestURI();
		AuthedUser user = CookieUtils.getLoginUser(request);
		if (user == null) {
			response.sendRedirect("/user/login");
			return;
		}
		request.setAttribute(LOGIN_USER_ATTR, user);
		if (user.refresh()) {
			CookieUtils.setLoginCookie(response, user);
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
