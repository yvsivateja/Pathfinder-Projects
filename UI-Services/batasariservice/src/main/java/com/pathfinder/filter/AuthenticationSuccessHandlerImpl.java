package com.pathfinder.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.pathfinder.business.LoginFilterBusiness;
import com.pathfinder.constants.RealConstants;

@Component
public class AuthenticationSuccessHandlerImpl implements
		AuthenticationSuccessHandler {

	@Autowired
	private LoginFilterBusiness loginFilterBusiness;

	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Authentication arg2)
			throws IOException, ServletException {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userName = auth.getName();

		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean == null) {
			// loginFilterBusiness = new LoginFilterBusiness();
			SessionBean sessionObj = new SessionBean();
			// HttpSession pageSession = request.getSession();//to invalidate
			if (userName != null) {
				sessionObj = loginFilterBusiness.getSessionDetails(userName);
				sessionObj.setImpersonated(false);
			}
			httpServletRequest.getSession().setAttribute("sessionUserDetails",
					sessionObj);
		} else {

		}

		redirectToUserPage(httpServletRequest, httpServletResponse);
	}

	private void redirectToUserPage(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse,
				"/" + RealConstants.USER_HOME);

	}

}
