package com.systems.concurrent.ejb.dao;

import com.systems.concurrent.ejb.dto.TaskData;
import com.systems.concurrent.utils.EMFactory;

public class TaskDao extends AbstractDao<TaskData> {

	private static final TaskDao instance = new TaskDao();

	public static final TaskDao getInstance() {
		return TaskDao.instance;
	}

	private TaskDao() {
		super(TaskData.class);
	}

	public void startWork(Long id) {
		try {
			em = EMFactory.createEntityManager();
			em.getTransaction().begin();
			String sql = "update project_tasks set tsk_start_work = sysdate(),tsk_status = ?1 where tsk_id = ?2";
			em.createNativeQuery(sql).setParameter(1, TaskData.Status.IN_PROGRESS).setParameter(2, id).executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}
	}

	public void stopWork(TaskData task) {
		try {
			em = EMFactory.createEntityManager();
			em.getTransaction().begin();
			String sql = "update project_tasks set tsk_start_work = null ,tsk_status = ?1, tsk_elapsed_time= ?2 where tsk_id = ?3";
			em.createNativeQuery(sql).setParameter(1, TaskData.Status.OPEN).setParameter(2, task.getElapsedTime())
					.setParameter(3, task.getId()).executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}

	}

	public void changeStatus(Long id, String status) {
		try {
			em = EMFactory.createEntityManager();
			em.getTransaction().begin();
			String sql = "update project_tasks set tsk_status = ?1 where tsk_id = ?2";
			em.createNativeQuery(sql).setParameter(1, status).setParameter(2, id).executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}

	}

	public void assigneTester(Long id, Long tester) {
		try {
			em = EMFactory.createEntityManager();
			em.getTransaction().begin();
			String sql = "update project_tasks set tsk_status = ?1 , TSK_ASSIGNED_USER_ID =?2 where tsk_id = ?3";
			em.createNativeQuery(sql).setParameter(1, TaskData.Status.VERIFICATION).setParameter(2, tester)
					.setParameter(3, id).executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (em != null)
				em.close();
		}

	}

}
