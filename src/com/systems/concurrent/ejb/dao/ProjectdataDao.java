package com.systems.concurrent.ejb.dao;

import com.systems.concurrent.ejb.dto.ProjectdataData;

public class ProjectdataDao extends AbstractDao<ProjectdataData> {

	private static final ProjectdataDao instance = new ProjectdataDao();

	protected ProjectdataDao() {
		super(ProjectdataData.class);
	}

	public static final ProjectdataDao getInstance() {
		return ProjectdataDao.instance;
	}

}
