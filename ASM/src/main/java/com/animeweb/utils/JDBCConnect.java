package com.animeweb.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class JDBCConnect implements ServletContextListener {

private static EntityManagerFactory fac;
	
	private static void getFac() {
		fac = Persistence.createEntityManagerFactory("ASM");
	}
	
	public static EntityManager getManager() {
		if (fac == null || fac.isOpen()) 
			getFac();
		return fac.createEntityManager();
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		getFac();
	}
	
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (fac != null)
			fac.close();
	}
	
}
