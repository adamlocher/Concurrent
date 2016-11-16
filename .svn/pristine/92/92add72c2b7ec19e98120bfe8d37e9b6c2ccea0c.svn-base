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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="COMMENTS")
public class CommentData extends AbstractData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5189681303520305405L;

	/**
	 * 
	 */
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COM_ID")
	Long id;
	
	@Column(name="COM_USER_ID")
	Long userId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COM_USER_ID", referencedColumnName = "USR_ID", insertable = false, updatable = false )
	UserData user;
	
	@Column(name="COM_TASK_ID")
	Long taskId;
	
	@Column(name="COM_CONTENT")
	String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="COM_DATE")	
	private Date date;
	
	@Column(name="COM_SYSTEM")
	Boolean system=Boolean.FALSE;
	
		
	public Boolean getSystem() {
		return system;
	}
	public void setSystem(Boolean system) {
		this.system = system;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserData getUser() {
		return user;
	}
	public void setUser(UserData user) {
		this.user = user;
	}
	
}
