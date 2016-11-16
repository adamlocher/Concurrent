package com.systems.concurrent.ejb.utils;



import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;



import com.systems.concurrent.ejb.dao.UserDao;
import com.systems.concurrent.ejb.dto.UserData;
import com.systems.concurrent.ejb.utils.Connection;


public class Test {
	
	EntityManager em;
	
	public Test() {
		/*Field id=null;
		try {
			id = UserData.class.getDeclaredField("id");
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Column column=id.getDeclaredAnnotation(Column.class);
		System.out.println(column.name());*/
		
		//CrudManagerBean a=new CrudManagerBean();
		   UserData todo = new UserData();
		    todo.setEmail("IMEJL5r");
		    todo.setName("łśźó");
		    todo.setSurname("ąę");
		    todo.setBirthday(new Date());
		    todo.setPassword("pasłord");
		    todo.setAccType("typ");
		    //UserDao s=new UserDao();
		   // s.createItem(todo);
		/*a.save2(todo, UserDao.class,MethodTypes.createItem);
		 todo = new UserData();
		    todo.setEmail("IMEJL4");
		    todo.setName("łśźó");
		    todo.setSurname("ąę");
		    todo.setBirthday(new Date());
		    todo.setPassword("pasłord");
		a.save2(todo, UserDao.class,MethodTypes.getItem,1L);*/
		
	/*	EntityManager em=Connection.getEntityManager();
		Query q = em.createQuery("select t from UserData t");
		 List<UserData> todoList = q.getResultList();
		    for (UserData todo : todoList) 
		      System.out.println(todo);*/
		    
		   /* em.getTransaction().begin();
		    UserData todo = new UserData();
		    todo.setEmail("email2sa3");
		    todo.setName("łśźó");
		    todo.setSurname("ąę");
		    todo.setBirthday(new Date());
		    todo.setPassword("pasłord");
		    em.persist(todo);
		    em.getTransaction().commit();*/
		   // em.close();
		    
	}
	public static void main(String[] args) {
		new Test();
	}
}
