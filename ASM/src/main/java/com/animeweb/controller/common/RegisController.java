package com.animeweb.controller.common;

import java.io.IOException;


import com.animeweb.dao.UserDAO;
import com.animeweb.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/common/regis.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User(request.getParameter("txtUsername"), request.getParameter("txtPassword"), request.getParameter("txtFullname"), request.getParameter("txtEmail"), false);
		UserDAO dao = new UserDAO();
		try {
			dao.create(user);
			response.sendRedirect(request.getContextPath() + "/common-login-page");
			
		} catch (Exception e) {
			request.getRequestDispatcher("/views/common/regis.jsp").forward(request, response);
		}
	}
}
