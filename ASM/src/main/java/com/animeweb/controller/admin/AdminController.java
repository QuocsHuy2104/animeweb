package com.animeweb.controller.admin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.animeweb.dao.UserDAO;
import com.animeweb.dao.VideoDAO;
import com.animeweb.model.User;
import com.animeweb.model.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        VideoDAO dao = new VideoDAO();
        Video video = new Video();

        if (uri.contains("edit")) {
            String id = uri.substring(uri.lastIndexOf("/") + 1);
            video = dao.findById(id);
        } else if (uri.contains("create")) {
            doCreateVideo(req, resp);
        } else if (uri.contains("update")) {
            doUpdateVideo(req, resp);
        } else if (uri.contains("del")) {
            doRemoveVideo(req, resp);
        }

        req.setAttribute("form", video);
        req.setAttribute("videos", dao.findAll());
        req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
    }

    protected void doCreateVideo(HttpServletRequest req, HttpServletResponse resp) {
        VideoDAO dao = new VideoDAO();

        String videoID = req.getParameter("ytbId");
        String videoTitle = req.getParameter("ytbTitle");
        String videoDescription = req.getParameter("ytbDescription");
        Boolean videoStatus = Boolean.valueOf(req.getParameter("active"));
        int videoViews = Integer.valueOf(req.getParameter("ytbViews"));
        String videoPath = "special-inosuke.jpg";
        String videoLink = req.getParameter("ytbLink");

        Video video = new Video(videoViews, videoStatus, videoDescription, videoLink, videoPath, videoTitle, videoID);
        dao.create(video);
    }

    protected void doUpdateVideo(HttpServletRequest req, HttpServletResponse resp) {
        String videoID = req.getParameter("ytbId");
        String videoTitle = req.getParameter("ytbTitle");
        String videoDescription = req.getParameter("ytbDescription");
        Boolean videoStatus = Boolean.valueOf(req.getParameter("active"));
        int videoViews = Integer.valueOf(req.getParameter("ytbViews"));
        String videoPath = "special-inosuke.jpg";
        String videoLink = req.getParameter("ytbLink");

        VideoDAO dao = new VideoDAO();
        Video video = new Video(videoViews, videoStatus, videoDescription, videoLink, videoPath, videoTitle, videoID);
        dao.update(video);

    }

    protected void doRemoveVideo(HttpServletRequest req, HttpServletResponse resp) {
        VideoDAO dao = new VideoDAO();
        String id = req.getParameter("ytbId");
        dao.remove(id);
    }

}
