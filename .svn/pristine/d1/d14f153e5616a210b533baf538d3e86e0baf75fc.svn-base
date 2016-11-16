package com.systems.concurrent.ejb.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PROJECT")
public class ProjectData extends AbstractData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5413933842315424593L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRJ_ID")
	Long id;

	@Column(name = "PRJ_NAME")
	String name;

	@Column(name = "PRJ_DESCRIPTION")
	String desc;

	@Column(name = "PRJ_START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	Date startDate;

	/* @Column(name = "PRJ_USER_PM_ID") */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRJ_USER_PM_ID", referencedColumnName = "USR_ID", insertable = false, updatable = false)
	UserData projectPM;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(name = "TSK_PROJECT_ID", referencedColumnName = "PRJ_ID", insertable = false, updatable = false)
	List<TaskData> tasks = new ArrayList<>();

	@Column(name = "PRJ_USER_PM_ID")
	Long projectPMid;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USERS_TO_PROJECT", joinColumns = @JoinColumn(name = "USR_PROJ_PROJECTID", referencedColumnName = "PRJ_ID"), inverseJoinColumns = @JoinColumn(name = "USR_PROJ_USERID", referencedColumnName = "USR_ID"))
	private List<UserData> users = new ArrayList<>();

	public List<UserData> getUsers() {
		return users;
	}

	public void setUsers(List<UserData> users) {
		this.users = users;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/*
	 * public void setProjectPM(Long projectPM) { this.projectPM = projectPM; }
	 * public Long getProjectPM() { return projectPM; }
	 */
	public UserData getProjectPM() {
		return projectPM;
	}

	public void setProjectPM(UserData projectPM) {
		this.projectPM = projectPM;
	}

	public Long getProjectPMid() {
		return projectPMid;
	}

	public void setProjectPMid(Long projectPMid) {
		this.projectPMid = projectPMid;
	}

	public List<TaskData> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskData> tasks) {
		this.tasks = tasks;
	}
}
