package com.systems.concurrent.ejb.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.systems.concurrent.ejb.dto.AbstractData;
import com.systems.concurrent.ejb.dto.UserData;
import com.systems.concurrent.ejb.utils.Connection;
import com.systems.concurrent.utils.EMFactory;

public abstract class AbstractDao<TYPE extends AbstractData> {

	protected Class<TYPE> entityClass;
	EntityManager em = null;

	public TYPE getItem(Long id) {
		if (id != null) {
			try {
				em = EMFactory.createEntityManager();
				TYPE item = em.find(entityClass, id);
				em.refresh(item);
				return item;
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<TYPE> getItems() {
		try {
			em = EMFactory.createEntityManager();
			return em.createQuery("select p from " + entityClass.getName() + " p").getResultList();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}
		return null;
	}

	public void createItem(TYPE item) {
		try {
			em = EMFactory.createEntityManager();
			em.getTransaction().begin();
			em.persist(item);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}
	}

	public void removeItem(Long id) {
		try {
			em = EMFactory.createEntityManager();
			TYPE item = em.find(entityClass, id);
			em.getTransaction().begin();
			em.remove(item);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}

	}

	public void modifyItem(TYPE item) {
		try {
			em = EMFactory.createEntityManager();
			em.getTransaction().begin();
			em.merge(item);
			em.getTransaction().commit();
			em.refresh(item);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}

	}

	public AbstractDao(Class entity) {
		this.entityClass = entity;

	}
}
