package com.animeweb.controller.admin;

import com.animeweb.dao.FavDAO;
import com.animeweb.dao.UserDAO;
import com.animeweb.dao.VideoDAO;
import com.animeweb.model.Favorite;
import com.animeweb.model.User;
import com.animeweb.model.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class StatisticalController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("admin-favorites")) {
            doFav(req, resp);
        } else if (uri.contains("admin-fav-user")) {
            doFavUser(req, resp);
        } else if (uri.contains("admin-share-friend")) {
            doShareFriend(req, resp);
        }
    }

    protected void doFav(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("POST")) {

        }
        req.getRequestDispatcher("/views/admin/statistical.jsp").forward(req, resp);
    }

    protected void doFavUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VideoDAO vdao = new VideoDAO();
        List<Video> listVideo = vdao.findAll();
        req.setAttribute("listVideo", listVideo);

        req.getRequestDispatcher("/views/admin/fav-user.jsp").forward(req, resp);
    }

    protected void doShareFriend(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VideoDAO vdao = new VideoDAO();
        List<Video> listVideo = vdao.findAll();
        req.setAttribute("listVideo", listVideo);
        req.getRequestDispatcher("/views/admin/share-friend.jsp").forward(req, resp);
    }

}
