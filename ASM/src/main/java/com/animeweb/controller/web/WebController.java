package com.animeweb.controller.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.animeweb.dao.VideoDAO;
import com.animeweb.model.User;
import com.animeweb.model.Video;

public class WebController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.contains("detail")) {
			doDetail(req, resp);
		} else if (uri.contains("trang-chu")) {
			doHome(req, resp);
		} else if (uri.contains("profile")) {
			doProfile(req, resp);
		} else if (uri.contains("common-change-page")) {
			doChangePass(req, resp);
		}
	}
	
	private void doHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VideoDAO dao = new VideoDAO();
		List<Video> videos = dao.findAll();
		
		req.setAttribute("videos", videos);
		
		req.getRequestDispatcher("/views/web/home.jsp").forward(req, resp);
	}
	
	private void doDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getParameter("id");
		VideoDAO dao = new VideoDAO();
		List<Video> videos = dao.findAll();
		
		req.setAttribute("videos", videos);
		
		req.setAttribute("url", uri);
		req.setAttribute("views", "/views/web/details.jsp");
		req.getRequestDispatcher("/views/web/details.jsp").forward(req, resp);
	}
	
	private void doProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/web/profile.jsp").forward(req, resp);
	}
	
	private void doChangePass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/common/changepw.jsp").forward(req, resp);
		
		if (req.getMethod().equals("POST")) {
			User user = (User) req.getSession().getAttribute("user");
			
		} 
	}

}
