package com.systems.concurrent.actions;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.systems.concurrent.ejb.dao.CommentDao;
import com.systems.concurrent.ejb.dao.ProjectDao;
import com.systems.concurrent.ejb.dao.ProjectdataDao;
import com.systems.concurrent.ejb.dao.TaskDao;
import com.systems.concurrent.ejb.dao.UserDao;
import com.systems.concurrent.ejb.dto.CommentData;
import com.systems.concurrent.ejb.dto.ProjectData;
import com.systems.concurrent.ejb.dto.ProjectdataData;
import com.systems.concurrent.ejb.dto.TaskData;
import com.systems.concurrent.ejb.dto.UserData;
import com.systems.concurrent.utils.CacheExt;

public class TaskAction extends CRUDAction<TaskData> {

	TaskData task;
	CommentData comment;
	ProjectdataData projectdataData;
	private List<ProjectdataData> projectdataList;
	private List<ProjectdataData> projectdataList2;
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	private int fileVersion;

	@Override
	public String Create() {
		task.setProjectId((Long) getAttribute("id"));
		task.setStatus(TaskData.Status.OPEN);
		TaskDao.getInstance().createItem(task);
		editMode = false;
		return Action.NONE;
	}

	public List<ProjectdataData> getTaskFiles(Long id) {
		projectdataList = new ArrayList<>();
		projectdataList2 = ProjectdataDao.getInstance().getItems();
		for (ProjectdataData p : projectdataList2) {
			if (p.getTaskId() == id) {
				projectdataList.add(p);
			}
		}
		return null;
	}

	public String Details() {
		Long id = Long.parseLong(request.getParameter("id"));
		setAttribute("id", id);
		task = TaskDao.getInstance().getItem(id);
		setAttribute("item", task);
		getTaskFiles(id);
		setAttribute("files", projectdataList);
		return Action.INPUT;
	}

	public ProjectdataData getProjectdataData() {
		return projectdataData;
	}

	public void setProjectdataData(ProjectdataData projectdataData) {
		this.projectdataData = projectdataData;
	}

	public List<ProjectdataData> getProjectdataList2() {
		return projectdataList2;
	}

	public void setProjectdataList2(List<ProjectdataData> projectdataList2) {
		this.projectdataList2 = projectdataList2;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public int getFileVersion() {
		return fileVersion;
	}

	public void setFileVersion(int fileVersion) {
		this.fileVersion = fileVersion;
	}

	public int ifExist() {
		int ver = 0;
		task = (TaskData) getAttribute("item");
		projectdataList = ProjectdataDao.getInstance().getItems();
		for (ProjectdataData projectdata : projectdataList) {
			if (projectdata.getFileName().equals(fileUploadFileName))
				ver++;
		}
		return ver;
	}

	public String Upload() throws Exception {
		task = (TaskData) getAttribute("item");
		RandomAccessFile f = new RandomAccessFile(fileUpload, "r");
		byte[] bFile = new byte[(int) f.length()];
		f.read(bFile);
		f.close();
		if (ifExist() == 0) {
			fileVersion = 1;
		} else {
			fileVersion = ifExist() + 1;
		}

		projectdataData = new ProjectdataData();
		projectdataData.setCreationDate(new Date());
		projectdataData.setFile(bFile);
		projectdataData.setFileName(fileUploadFileName);
		projectdataData.setFileVersion(fileVersion);
		projectdataData.setFileExtension(FilenameUtils.getExtension(fileUploadFileName));
		// System.out.println(Long.parseLong(request.getParameter("id")));
		projectdataData.setTaskId(task.getId());
		ProjectdataDao.getInstance().createItem(projectdataData);
		String who = CacheExt.users.get(getUserContext().getId()).getName() + " "
				+ CacheExt.users.get(getUserContext().getId()).getSurname();
		saveSystemComment(who + " sent a file: " + fileUploadFileName + " rev. " + fileVersion);
		getTaskFiles(task.getId());
		return Action.INPUT;

	}

	@Override
	public String Remove() {
		// TODO Auto-generated method stub
		return null;
	}

	public String Add() {
		Long id = Long.parseLong(request.getParameter("id"));
		setAttribute("id", id);
		editMode = true;
		return Action.NONE;
	}

	public List<ProjectdataData> getProjectdataList() {
		return projectdataList;
	}

	public void setProjectdataList(List<ProjectdataData> projectdataList) {
		this.projectdataList = projectdataList;
	}

	public TaskData getTask() {
		return task;
	}

	public void setTask(TaskData task) {
		this.task = task;
	}

	public Map<Long, String> getUsersDEVProject() {
		//task = (TaskData) getAttribute("item");
		ProjectData project = ProjectDao.getInstance().getItem((Long)getAttribute("id"));
		List<UserData> projDevelopers = project.getUsers();
		Map<Long, String> lista = new HashMap<>();
		boolean getTesters = false;
		if (getAttribute("item") instanceof TaskData
				&& ((TaskData) getAttribute("item")).getStatus().equals(TaskData.Status.VERIFICATION))
			getTesters = true;
		for (UserData u : projDevelopers)
			lista.put(u.getId(), u.getName() + " " + u.getSurname());
		return lista;
	}
	public Map<Long, String> getUsersDEVTask() {
		//task = (TaskData) getAttribute("item");
		TaskData task = TaskDao.getInstance().getItem((Long)getAttribute("id"));
		List<UserData> projDevelopers = task.getProject().getUsers();
		Map<Long, String> lista = new HashMap<>();
		boolean getTesters = false;
		if (getAttribute("item") instanceof TaskData
				&& ((TaskData) getAttribute("item")).getStatus().equals(TaskData.Status.VERIFICATION))
			getTesters = true;
		for (UserData u : projDevelopers)
			lista.put(u.getId(), u.getName() + " " + u.getSurname());
		if(getTesters){
			for (UserData u : 	UserDao.getInstance().getTesters())
				lista.put(u.getId(), u.getName() + " " + u.getSurname());
			}
		return lista;
	}

	public List<UserData> getTesters() {
		return UserDao.getInstance().getTesters();
	}

	public String Test() {
		task = (TaskData) getAttribute("item");
		if (task.getStatus().equals(TaskData.Status.OPEN)) {
			Long testerId = Long.parseLong(request.getParameter("id"));
			task.setAssignedUserId(testerId);
			TaskDao.getInstance().assigneTester(task.getId(), testerId);
			String who = CacheExt.users.get(getUserContext().getId()).getName() + " "
					+ CacheExt.users.get(getUserContext().getId()).getSurname();
			String tester = CacheExt.users.get(testerId).getName() + " " + CacheExt.users.get(testerId).getSurname();
			saveSystemComment(who + " send to test to " + tester);
			task.setAssignedUser(CacheExt.users.get(testerId));
			((TaskData) getAttribute("item")).setAssignedUserId(testerId);
			((TaskData) getAttribute("item")).setAssignedUser(CacheExt.users.get(testerId));
			task.setStatus(TaskData.Status.VERIFICATION);
		}
		projectdataList = (java.util.List<ProjectdataData>) getAttribute("files");
		return Action.INPUT;
	}

	@Override
	public String Modify() {
		task = (TaskData) getAttribute("item");
		return super.Modify();
	}

	@Override
	public String Save() {
		task.setId(((TaskData) getAttribute("item")).getId());
		task.setProjectId(((TaskData) getAttribute("item")).getProjectId());
		task.setComments(((TaskData) getAttribute("item")).getComments());
		task.setStatus(((TaskData) getAttribute("item")).getStatus());
		task.setEstimatedTime(((TaskData) getAttribute("item")).getEstimatedTime());
		if (task.getAssignedUserId()!=null && !task.getAssignedUserId().equals(((TaskData) getAttribute("item")).getAssignedUserId())) {
			String assFrom = "NONE";
			if (((TaskData) getAttribute("item")).getAssignedUser() != null)
				assFrom = ((TaskData) getAttribute("item")).getAssignedUser().getName() + " "
						+ ((TaskData) getAttribute("item")).getAssignedUser().getSurname();
			String assTo = CacheExt.users.get(task.getAssignedUserId()).getName() + " "
					+ CacheExt.users.get(task.getAssignedUserId()).getSurname();
			String who = CacheExt.users.get(getUserContext().getId()).getName() + " "
					+ CacheExt.users.get(getUserContext().getId()).getSurname();
			saveSystemComment(who + " changed assigned user from: " + assFrom + " to " + assTo);
		}

		TaskDao.getInstance().modifyItem(task);
		editMode = false;
		task.setProject(((TaskData) getAttribute("item")).getProject());
		setAttribute("item", task);
		return Action.INPUT;
	}

	public Long getProjectId() {
		return (Long) getAttribute("id");
	}

	@Override
	public void validate() {
		/*
		 * if(request.getMethod().equals("POST") && task.getEstimatedTime()!=""
		 * && !task.getEstimatedTime().matches("\\d+")) addFieldError(
		 * "task.estimatedTime", "Wrong format." );
		 */

	}

	public CommentData getComment() {
		return comment;
	}

	public void setComment(CommentData comment) {
		this.comment = comment;
	}

	public String SendComment() {
		if (!comment.getContent().trim().equals("")) {
			comment.setTaskId(((TaskData) getAttribute("item")).getId());
			comment.setUserId(getUserContext().getId());
			comment.setDate(new Date());
			CommentDao.getInstance().createItem(comment);
			comment.setUser(CacheExt.users.get(comment.getUserId()));
			task = (TaskData) getAttribute("item");
			task.getComments().add(comment);
			setAttribute("item", task);
			comment = new CommentData();
		}
		projectdataList = (java.util.List<ProjectdataData>) getAttribute("files");
		return Action.INPUT;
	}

	public String Start() {
		task = (TaskData) getAttribute("item");
		if (TaskData.Status.OPEN.equals(task.getStatus())) {
			TaskDao.getInstance().startWork(task.getId());
			task.setStatus(TaskData.Status.IN_PROGRESS);
			((TaskData) getAttribute("item")).setStartWorkDate(new Date());
			String who = CacheExt.users.get(getUserContext().getId()).getName() + " "
					+ CacheExt.users.get(getUserContext().getId()).getSurname();
			saveSystemComment(who + " start working");
		}
		projectdataList = (java.util.List<ProjectdataData>) getAttribute("files");
		return Action.INPUT;
	}

	public String Stop() {
		task = (TaskData) getAttribute("item");
		if (TaskData.Status.IN_PROGRESS.equals(task.getStatus())) {
			task.setStatus(TaskData.Status.OPEN);
			long diff = new Date().getTime() - task.getStartWorkDate().getTime();
			long diffMinutes = diff / (60 * 1000);

			if (task.getElapsedTime() != null)
				diffMinutes += task.getElapsedTime();

			task.setElapsedTime(diffMinutes);
			TaskDao.getInstance().stopWork(task);
			String who = CacheExt.users.get(getUserContext().getId()).getName() + " "
					+ CacheExt.users.get(getUserContext().getId()).getSurname();
			saveSystemComment(who + " stop working");
			task.setStartWorkDate(null);
		}
		projectdataList = (java.util.List<ProjectdataData>) getAttribute("files");
		return Action.INPUT;
	}

	public String StopVerification() {
		task = (TaskData) getAttribute("item");
		if (TaskData.Status.VERIFICATION.equals(task.getStatus())) {
			String result = request.getParameter("result");
			if ("Fixed".equals(result) || "Verified".equals(result)) {
				task.setStatus(TaskData.Status.CLOSED);
				task.setAssignedUserId(null);
			}
			if ("NotFixed".equals(result)) {
				task.setStatus(TaskData.Status.OPEN);
				result = "Not fixed";
			}
			TaskDao.getInstance().changeStatus(task.getId(), task.getStatus());
			String who = CacheExt.users.get(getUserContext().getId()).getName() + " "
					+ CacheExt.users.get(getUserContext().getId()).getSurname();
			saveSystemComment(who + " stopped verification with result '" + result + "'");

		}
		projectdataList = (java.util.List<ProjectdataData>) getAttribute("files");
		return Action.INPUT;
	}

	public String Close() {
		task = (TaskData) getAttribute("item");
		if (!TaskData.Status.CLOSED.equals(task.getStatus())) {
			task.setStatus(TaskData.Status.CLOSED);
			TaskDao.getInstance().changeStatus(task.getId(), TaskData.Status.CLOSED);
			String who = CacheExt.users.get(getUserContext().getId()).getName() + " "
					+ CacheExt.users.get(getUserContext().getId()).getSurname();
			saveSystemComment(who + " close task");
		}
		projectdataList = (java.util.List<ProjectdataData>) getAttribute("files");
		return Action.INPUT;
	}

	public String Reopen() {
		task = (TaskData) getAttribute("item");
		task.setStatus(TaskData.Status.OPEN);
		TaskDao.getInstance().changeStatus(task.getId(), TaskData.Status.OPEN);
		String who = CacheExt.users.get(getUserContext().getId()).getName() + " "
				+ CacheExt.users.get(getUserContext().getId()).getSurname();
		saveSystemComment(who + " reopen task");
		projectdataList = (java.util.List<ProjectdataData>) getAttribute("files");
		return Action.INPUT;
	}

	private void saveSystemComment(String s) {
		comment = new CommentData();
		comment.setContent(s);
		comment.setTaskId(((TaskData) getAttribute("item")).getId());
		comment.setUserId(CacheExt.users.get(0L).getId());
		comment.setUser(CacheExt.users.get(0L));
		comment.setDate(new Date());
		comment.setSystem(Boolean.TRUE);
		CommentDao.getInstance().createItem(comment);
		task.getComments().add(comment);
		comment = new CommentData();
		projectdataList = (java.util.List<ProjectdataData>) getAttribute("files");
	}

	public String Delete() {
		task = (TaskData) getAttribute("item");
		Map<String, Object> params = (Map<String, Object>) ActionContext.getContext().getParameters();
		Object fileId = params.get("id");
		Long id = Long.parseLong(((String[]) fileId)[0]);
		String who = CacheExt.users.get(getUserContext().getId()).getName() + " "
				+ CacheExt.users.get(getUserContext().getId()).getSurname();
		saveSystemComment(who + " deleted a file: " + fileUploadFileName + " rev. " + fileVersion);
		ProjectdataDao.getInstance().removeItem(id);
		getTaskFiles(task.getId());
		return Action.INPUT;
	}

	@Override
	public String List() {
		// TODO Auto-generated method stub
		return null;
	}
}
