package com.systems.concurrent.ejb.dao;

import com.systems.concurrent.ejb.dto.UserProjectData;

public class UserProjectDao extends AbstractDao<UserProjectData> {
	private static final UserProjectDao instance = new UserProjectDao();

	protected UserProjectDao() {
		super(UserProjectData.class);
	}

	public static final UserProjectDao getInstance() {
		return UserProjectDao.instance;
	}
}
