package com.animeweb.controller.common;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.animeweb.model.User;
import com.animeweb.utils.JDBCConnect;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/common/login.jsp");
		rd.forward(request, response);
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		String username = request.getParameter("txtUsername");
//		String password = request.getParameter("txtPassword");
//
//		try (EntityManager em = JDBCConnect.getManager()) {
//			String jpql = "Select o From User o where o.id = :username and o.passWord = :password";
//			TypedQuery<User> query = em.createQuery(jpql, User.class);
//			query.setParameter("username", username);
//			query.setParameter("password", password);
//
//			try {
//				User foundUser = query.getSingleResult();
//				request.getSession().setAttribute("user", foundUser);
//
//				if (foundUser.getAdmin())
//					response.sendRedirect(request.getContextPath() + "/admin-home");
//				else
//					response.sendRedirect(request.getContextPath() + "/trang-chu");
//
//			} catch (NoResultException e) {
//				request.getRequestDispatcher("/views/common/login.jsp").forward(request, response);
//			}
//
//		}
//	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getRequestURI().equals("common-logout-page")) {
			request.getSession().removeAttribute("user");
			response.sendRedirect(request.getContextPath() + "/common-login-page");
		} else {
			String username = request.getParameter("txtUsername");
			String password = request.getParameter("txtPassword");

			try (EntityManager em = JDBCConnect.getManager()) {
				String jpql = "Select o From User o where o.id = :username and o.passWord = :password";
				TypedQuery<User> query = em.createQuery(jpql, User.class);
				query.setParameter("username", username);
				query.setParameter("password", password);

				try {
					User foundUser = query.getSingleResult();
					request.getSession().setAttribute("user", foundUser);

					if (foundUser.getAdmin())
						response.sendRedirect(request.getContextPath() + "/admin-home");
					else
						response.sendRedirect(request.getContextPath() + "/trang-chu");

				} catch (NoResultException e) {
					request.getRequestDispatcher("/views/common/login.jsp").forward(request, response);
				}

			}
		}
	}

}
