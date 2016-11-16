package com.systems.concurrent.ejb.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_TO_PROJECT")
@IdClass(UserProjectData.class)
public class UserProjectData extends AbstractData {

	private static final long serialVersionUID = 6986508370782203274L;

	@Id
	@Column(name = "USR_PROJ_USERID")
	private Long userId;

	@Id
	@Column(name = "USR_PROJ_PROJECTID")
	private Long projectId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub

	}

}
