package com.systems.concurrent.ejb.dao;

import com.systems.concurrent.ejb.dto.ProjectData;

public class ProjectDao extends AbstractDao<ProjectData> {

	
	private static final ProjectDao instance = new ProjectDao();
	protected ProjectDao() {
		super(ProjectData.class);
	}
	
	public static final ProjectDao getInstance() {
		return ProjectDao.instance;
	}
	
}
