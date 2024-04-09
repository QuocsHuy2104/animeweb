package com.animeweb.controller.web;

import com.animeweb.dao.FavDAO;
import com.animeweb.dao.UserDAO;
import com.animeweb.model.Favorite;
import jakarta.persistence.EntityManager;
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
        } else if (uri.contains("fav")) {
            doFav(req, resp);
        } else if (uri.contains("share-page")) {
            doShare(req, resp);
        } else if (uri.contains("unlike")) {
            doUnlike(req, resp);
        }
    }

    private void doUnlike(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        VideoDAO dao = new VideoDAO();
        dao.remove(id);

        doDetail(req, resp);
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
        String username = req.getParameter("username");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");

        User user = (User) req.getSession().getAttribute("user");
        UserDAO dao = new UserDAO();
        if (req.getMethod().equals("POST")) {
            User newUser = new User(user.getId(), user.getPassWord(), fullname, email, user.getAdmin());
            dao.update(newUser);
        }

        req.setAttribute("username", user.getId());
        req.setAttribute("fullname", user.getFullName());
        req.setAttribute("email", user.getEmail());

        req.getRequestDispatcher("/views/web/profile.jsp").forward(req, resp);
    }

    private void doChangePass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/common/changepw.jsp").forward(req, resp);

        if (req.getMethod().equals("POST")) {
            User user = (User) req.getSession().getAttribute("user");
            String crPass = req.getParameter("txtCurrentPass");
            String newPass = req.getParameter("txtNewPass");
            String confirm = req.getParameter("txtConfirmPass");

            if (!newPass.equals(confirm)) {
                System.out.println("Mật khẩu không trùng khớp");
                return;
            }

            if (!crPass.equals(user.getPassWord()))
                req.setAttribute("message", "Current Password is incorrect");
            else {
                User newUser = new User(user.getId(), confirm, user.getFullName(), user.getEmail(), user.getAdmin());
                UserDAO dao = new UserDAO();
                dao.update(newUser);
                req.getSession().removeAttribute("user");
                resp.sendRedirect(req.getContextPath() + "/common-login-page");
            }
        }
    }

    protected void doFav(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("POST")) {
        }
        VideoDAO dao = new VideoDAO();
        User user = (User) req.getSession().getAttribute("user");
        List<Video> list = dao.findVideoByUserId(user.getId());
        req.setAttribute("likes", list);

        req.getRequestDispatcher("/views/web/favourite.jsp").forward(req, resp);
    }

    protected void doShare(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("POST")) {

        }

        req.getRequestDispatcher("/views/web/share.jsp").forward(req, resp);
    }

}
