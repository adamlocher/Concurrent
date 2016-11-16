package com.systems.concurrent.ejb.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {
	  private static final String PERSISTENCE_UNIT_NAME = "ejb";
	  private static EntityManagerFactory factory;
	  private static EntityManager em;
	  
	  static{
		  factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		  em = factory.createEntityManager();
	  }
	  public static EntityManager getEntityManager() {
		return em;
	  }
}
