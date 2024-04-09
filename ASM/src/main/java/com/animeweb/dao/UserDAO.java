package com.animeweb.dao;

import com.animeweb.model.User;
import com.animeweb.utils.JDBCConnect;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDAO {
	private EntityManager em = JDBCConnect.getManager();
	
	@Override
	protected void finalize() throws Throwable {
		em.close();
		super.finalize();
	}
	
	public User create(User user) {
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			
			return user;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	
	public User update(User user) {
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
			return user;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}

	public User remove(String id) {
		try {
			User user = em.find(User.class, id);
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
			return user;
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw  new RuntimeException();
		}
	}

	public User findById(String id) {
		User user = em.find(User.class, id);
		return user;
	}

	public List<User> findAll() {
		TypedQuery<User> query = em.createQuery("select o from User o", User.class);
		List<User> list = query.getResultList();
		return list;
	}

}
