package com.animeweb.dao;

import java.util.List;

import com.animeweb.model.Favorite;
import com.animeweb.model.Video;
import com.animeweb.utils.JDBCConnect;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class VideoDAO {

    private EntityManager em = JDBCConnect.getManager();

    @Override
    protected void finalize() throws Throwable {
        em.close();
        super.finalize();
    }

    public Video findById(String id) {
        Video video = em.find(Video.class, id);
        return video;
    }

    public Video create(Video video) {
        try {
            em.getTransaction().begin();
            em.persist(video);
            em.getTransaction().commit();
            return video;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public Video update(Video video) {
        try {
            em.getTransaction().begin();
            em.merge(video);
            em.getTransaction().commit();
            return video;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public Video remove(String id) {
        try {
            Video video = em.find(Video.class, id);
            em.getTransaction().begin();
            em.remove(video);
            em.getTransaction().commit();
            return video;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new RuntimeException(ex);
        }

    }

    public List<Video> findAll() {
        String jPQL = "Select o from Video o";
        TypedQuery<Video> query = em.createQuery(jPQL, Video.class);
        List<Video> result = query.getResultList();
        return result;
    }

    public List<Video> findVideoByUserId(String id) {
        String jpql = "Select o.video from Favorite o where o.user.id = :username";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setParameter("username", id);
        return query.getResultList();
    }


}
