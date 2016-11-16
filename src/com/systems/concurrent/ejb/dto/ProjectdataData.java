package com.systems.concurrent.ejb.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PROJECTS_DATA")
public class ProjectdataData extends AbstractData {

	private static final long serialVersionUID = 1883888288381927778L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRJ_DATA_ID")
	private Long id;

	@Column(name = "PRJ_DATA_FILE")
	private byte[] file;

	@Column(name = "PRJ_DATA_FILENAME")
	private String fileName;

	@Column(name = "PRJ_DATA_CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column(name = "PRJ_DATA_FILEVERSION")
	private int fileVersion;

	@Column(name = "PRJ_DATA_FILEEXTENSION")
	private String fileExtension;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRJ_DATA_TASK_ID", referencedColumnName = "TSK_ID", insertable = false, updatable = false)
	TaskData task;

	@Column(name = "PRJ_DATA_TASK_ID")
	Long taskId;

	public TaskData getTask() {
		return task;
	}

	public void setTask(TaskData task) {
		this.task = task;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public int getFileVersion() {
		return fileVersion;
	}

	public void setFileVersion(int fileVersion) {
		this.fileVersion = fileVersion;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
