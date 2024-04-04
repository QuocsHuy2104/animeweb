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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();

		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		try (EntityManager em = JDBCConnect.getManager()) {
			String jpql = "Select o from User o where o.id=:username and o.password=:password";
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("username", user.getId());
			query.setParameter("password", user.getPassword());

			try {

				User foundUser = query.getSingleResult();
				request.getSession().setAttribute("user", foundUser);

				if (foundUser.getAdmin())
					response.sendRedirect(request.getContextPath() + "/admin-home");
				else
					response.sendRedirect(request.getContextPath() + "/trang-chu");
				
			} catch (NoResultException e) {
				request.setAttribute("message", "User not found");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}

		}
	}

}
