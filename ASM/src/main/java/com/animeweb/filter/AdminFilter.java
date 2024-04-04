package com.animeweb.filter;

import java.io.IOException;

import com.animeweb.model.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebFilter("/admin-home")
public class AdminFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null && user.getAdmin()) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/common-login-page");
		}
	}
}
