package com.systems.concurrent.ejb.dao;

import java.util.List;

import javax.persistence.Query;

import com.systems.concurrent.ejb.dto.UserData;
import com.systems.concurrent.utils.EMFactory;

public class UserDao extends AbstractDao<UserData> {

	private static final UserDao instance = new UserDao();

	protected UserDao() {
		super(UserData.class);
	}

	public static final UserDao getInstance() {
		return UserDao.instance;
	}

	Object session;

	@Override
	public UserData getItem(Long id) {
		return super.getItem(id);
	}

	@Override
	public void modifyItem(UserData item) {
		String skills = item.getSkills().trim();
		if (skills.trim().length() > 0) {
			if (skills.charAt(skills.length() - 1) == ',') {
				skills = skills.substring(0, skills.length() - 1);
				item.setSkills(skills);
			}
		}

		super.modifyItem(item);

	}

	@Override
	public void createItem(UserData item) {
		super.createItem(item);
	}

	public List<UserData> getUsersNotApproved() {
		List<UserData> users = null;
		try {
			em = EMFactory.createEntityManager();
			Query query = em.createQuery("select p from " + entityClass.getName() + " p where p.approved=:app ");
			query.setParameter("app", Boolean.FALSE);
			users = (List<UserData>) query.getResultList();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}

		return users;
	}

	public UserData login(String email, String password) {
		UserData user = null;
		try {
			em = EMFactory.createEntityManager();
			Query query = em.createQuery(
					"select p from " + entityClass.getName() + " p where p.email=:login and p.password=:pass");
			query.setParameter("login", email);
			query.setParameter("pass", password);
			user = (UserData) query.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}
		return user;
	}

	public UserData sendLogo(UserData u) {
		UserData item = null;
		try {
			em = EMFactory.createEntityManager();
			item = em.find(entityClass, u.getId());
			em.getTransaction().begin();
			item.setLogo(u.getLogo());
			em.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}
		return item;
	}

	public List<UserData> getUsersPM() {
		List<UserData> users = null;
		try {
			em = EMFactory.createEntityManager();
			Query query = em.createQuery("select p from " + entityClass.getName() + " p where p.accType=:pm ");
			query.setParameter("pm", "PM");
			users = (List<UserData>) query.getResultList();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}

		return users;
	}

	public List<UserData> getUsersDEV(boolean getTesters) {
		List<UserData> users = null;
		try {
			em = EMFactory.createEntityManager();
			String sql = "select p from " + entityClass.getName() + " p where p.accType=:dev ";
			if (getTesters)
				sql += " or p.accType=:tester";
			Query query = em.createQuery(sql);
			query.setParameter("dev", "Developer");
			if (getTesters)
				query.setParameter("tester", "Tester");
			users = (List<UserData>) query.getResultList();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}

		return users;
	}

	public List<UserData> getTesters() {
		List<UserData> users = null;
		try {
			em = EMFactory.createEntityManager();
			Query query = em.createQuery("select p from " + entityClass.getName() + " p where p.accType=:tester ");
			query.setParameter("tester", "Tester");
			users = (List<UserData>) query.getResultList();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}

		return users;
	}

	public void modifyFlags(List<Long> ids) {
		try {
			if (ids.size() > 0) {
				em = EMFactory.createEntityManager();
				em.getTransaction().begin();
				String sql = "update " + entityClass.getName() + " user set user.approved = 1 where user.id in :ids";
				Query q = em.createQuery(sql);
				q.setParameter("ids", ids);
				q.executeUpdate();
				em.getTransaction().commit();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}
	}
}
