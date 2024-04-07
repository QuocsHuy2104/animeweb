package com.animeweb.dao;

import java.util.List;

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
	
	public List<Video> findAll() {
		String jPQL = "Select o from Video o";
		TypedQuery<Video> query = em.createQuery(jPQL, Video.class);
		List<Video> result = query.getResultList();
		return result;
	}
}
