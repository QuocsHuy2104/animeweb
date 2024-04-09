package com.animeweb.controller.admin;

import com.animeweb.dao.UserDAO;
import com.animeweb.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ManageUserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        User user = new User();
        UserDAO dao = new UserDAO();

        if (uri.contains("user-edit")) {
            String id = uri.substring(uri.lastIndexOf("/") + 1);
            user = dao.findById(id);
        } else if (uri.contains("user-update")) {
            doUpdate(req, resp);
        } else if (uri.contains("user-del")) {
            doDelete(req, resp);
        }

        req.setAttribute("form", user);
        req.setAttribute("users", dao.findAll());
        req.getRequestDispatcher("/views/admin/mange-user.jsp").forward(req, resp);
    }

    protected void doUpdate(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("userID");
        String fullname = req.getParameter("userFullname");
        String password = req.getParameter("userPass");
        String email = req.getParameter("userEmail");

        User user = new User(id, password, fullname, email);
        UserDAO dao = new UserDAO();
        dao.update(user);

    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        dao.remove(req.getParameter("userID"));
    }
}
