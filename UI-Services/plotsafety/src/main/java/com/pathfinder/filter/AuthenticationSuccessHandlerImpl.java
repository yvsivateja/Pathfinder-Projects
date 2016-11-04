package com.pathfinder.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandlerImpl
		implements
			AuthenticationSuccessHandler {

	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Authentication arg2)
			throws IOException, ServletException {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userName = auth.getName();
		System.out.println("In filter(Logged In User) : " + userName);
		redirectToUserPage(httpServletRequest, httpServletResponse);
	}

	private void redirectToUserPage(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse,
				"/viewCustomers.htm");

	}

}
