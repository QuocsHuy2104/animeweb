package com.animeweb.dao;

import com.animeweb.model.Favorite;
import com.animeweb.model.User;
import com.animeweb.model.Video;
import com.animeweb.utils.JDBCConnect;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class FavDAO {

    private EntityManager em = JDBCConnect.getManager();

    @Override
    protected void finalize() throws Throwable {
        em.close();
        super.finalize();
    }

    public Favorite create(Favorite fav) {
        try {
            em.getTransaction().begin();
            em.persist(fav);
            em.getTransaction().commit();

            return fav;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new RuntimeException();
        }
    }

    public Favorite update(Favorite fav) {
        try {
            em.getTransaction().begin();
            em.merge(fav);
            em.getTransaction().commit();
            return fav;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new RuntimeException();
        }
    }

    public Favorite del(String id) {
        try {
            em.getTransaction().begin();
            Favorite fav = em.find(Favorite.class, id);
            em.remove(fav);
            em.getTransaction().commit();
            return fav;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new RuntimeException();
        }
    }

    public List<Favorite> findAll() {
        TypedQuery<Favorite> query = em.createQuery("select o from Favorite o", Favorite.class);
        List<Favorite> list = query.getResultList();
        return list;
    }

    public Favorite findByID(String id) {
        Favorite fav = em.find(Favorite.class, id);
        return fav;
    }

}
