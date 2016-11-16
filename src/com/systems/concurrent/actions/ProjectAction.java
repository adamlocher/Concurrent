package com.systems.concurrent.actions;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.systems.concurrent.ejb.dao.ProjectDao;
import com.systems.concurrent.ejb.dao.UserDao;
import com.systems.concurrent.ejb.dao.UserProjectDao;
import com.systems.concurrent.ejb.dto.ProjectData;
import com.systems.concurrent.ejb.dto.TaskData;
import com.systems.concurrent.ejb.dto.UserData;
import com.systems.concurrent.ejb.dto.UserProjectData;
import com.systems.concurrent.utils.CacheExt;

import net.sf.ehcache.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class ProjectAction extends CRUDAction<ProjectData> {

	private static final long serialVersionUID = 2832417492746999732L;
	private ProjectData project;
	private UserProjectData userToProject;
	private UserData user;
	private List<UserData> projectDevelopers;
	private List<UserData> developers;
	private List<ProjectData> projectList;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProjectAction() {
		setActionName("ProjectAction");
		boolean getTesters = false;
		if (getAttribute("item") instanceof TaskData
				&& ((TaskData) getAttribute("item")).getStatus().equals(TaskData.Status.VERIFICATION))
			getTesters = true;
		developers = UserDao.getInstance().getUsersDEV(getTesters);
	}

	@Override
	public String Save() {
		project.setId(((ProjectData) getAttribute("item")).getId());
		ProjectDao.getInstance().modifyItem(project);
		project.setProjectPM(CacheExt.users.get(project.getProjectPMid()));
		editMode = false;
		project.setTasks(((ProjectData) getAttribute("item")).getTasks());
		return "details";
	}

	@Override
	public String execute() throws Exception {
		if (!request.getMethod().equals("GET")) {
			if (getActionName().equals(Actions.CREATE)) {
				ProjectDao.getInstance().createItem(project);
				setEditMode(false);
			} else if (getActionName().equals(Actions.MODIFY)) {
				ProjectDao.getInstance().modifyItem(project);
			} else // remove
				ProjectDao.getInstance().removeItem(1L);

		}
		return NONE;
	}

	@Override
	public String Create() {
		if (request.getMethod().equals("GET")) {
			setActionName(Actions.CREATE);
			setEditMode(true);
			return NONE;
		}

		ProjectDao.getInstance().createItem(project);
		setEditMode(false);
		// setActionName(Actions//);
		return Action.SUCCESS;
	}

	@Override
	public String List() {
		projectList = ProjectDao.getInstance().getItems();
		for (ProjectData p : projectList) {
			p.setProjectPM(CacheExt.users.get(p.getProjectPMid()));
			if (p.getDesc() != null && p.getDesc().length() > 110)
				p.setDesc(p.getDesc().substring(0, 110) + "...");
		}
		Element elem = CacheExt.getCachedObject("USERS");
		// List<UserData> user=(List<UserData>)elem.getObjectValue();
		return "list";
	}

	@Override
	public String Remove() {
		return null;
	}

	public ProjectData getProject() {
		return project;
	}

	public String Adddeveloper() {
		project = (ProjectData) getAttribute("item");
		userToProject = new UserProjectData();
		userToProject.setProjectId(project.getId());
		userToProject.setUserId(id);
		UserProjectDao.getInstance().createItem(userToProject);
		projectDevelopers = project.getUsers();
		user = UserDao.getInstance().getItem(id);
		projectDevelopers.add(user);
		getFreeDevelopers();
		return "details";
	}

	private void getFreeDevelopers() {
		List<Long> ids = new ArrayList<>();
		for (UserData dev : projectDevelopers) {
			ids.add(dev.getId());
		}
		ListIterator<UserData> listIterator = developers.listIterator();
		while (listIterator.hasNext()) {
			UserData u = listIterator.next();
			if (ids.contains(u.getId())) {
				listIterator.remove();
			}
		}
	}

	@Override
	public String Modify() {
		project = (ProjectData) getAttribute("item");
		editMode = true;
		return "details";
	}

	public void setProject(ProjectData project) {
		this.project = project;
	}

	public Map<Long, String> getUsersPM() {
		Map<Long, String> lista = new HashMap<>();
		for (UserData u : UserDao.getInstance().getUsersPM())
			lista.put(u.getId(), u.getEmail());
		return lista;
	}

	public Map<Long, String> getUsersDEV() {
		Map<Long, String> lista = new HashMap<>();
		boolean getTesters = false;
		if (getAttribute("item") instanceof TaskData
				&& ((TaskData) getAttribute("item")).getStatus().equals(TaskData.Status.VERIFICATION))
			getTesters = true;
		for (UserData u : UserDao.getInstance().getUsersDEV(getTesters))
			lista.put(u.getId(), u.getName() + " " + u.getSurname());

		return lista;

	}

	public List<ProjectData> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<ProjectData> projectList) {
		this.projectList = projectList;
	}

	public String Details() {

		Map<String, Object> params = (Map<String, Object>) ActionContext.getContext().getParameters();
		Long id = null;
		try {
			Object projectId = params.get("id");
			id = Long.parseLong(((String[]) projectId)[0]);
		} catch (Exception e) {
			return "list";
		}
		project = ProjectDao.getInstance().getItem(id);
		project.setProjectPM(CacheExt.users.get(project.getProjectPMid()));
		setAttribute("item", project);
		projectDevelopers = project.getUsers();
		getFreeDevelopers();

		return "details";
	}

	public void setUserId(Long id) {
		UserData user = new UserData();
		user.setId(id);
		project.setProjectPM(user);

	}

	String editField;
	String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getEditField() {
		return editField;
	}

	public void setEditField(String editField) {
		this.editField = editField;
	}

	public List<UserData> getProjectDevelopers() {
		return projectDevelopers;
	}

	public void setProjectDevelopers(List<UserData> projectDevelopers) {
		this.projectDevelopers = projectDevelopers;
	}

	public UserProjectData getUserToProject() {
		return userToProject;
	}

	public void setUserToProject(UserProjectData userToProject) {
		this.userToProject = userToProject;
	}

	public UserData getUser() {
		return user;
	}

	public void setUser(UserData user) {
		this.user = user;
	}

	public List<UserData> getDevelopers() {
		return developers;
	}

	public void setDevelopers(List<UserData> developers) {
		this.developers = developers;
	}

}
