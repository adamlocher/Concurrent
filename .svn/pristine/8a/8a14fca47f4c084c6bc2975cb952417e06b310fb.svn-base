package com.systems.concurrent.actions;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import com.opensymphony.xwork2.Action;
import com.systems.concurrent.ejb.dao.ProjectdataDao;
import com.systems.concurrent.ejb.dao.UserDao;
import com.systems.concurrent.ejb.dto.ProjectData;
import com.systems.concurrent.ejb.dto.ProjectdataData;
import com.systems.concurrent.ejb.dto.UserData;
import com.systems.concurrent.utils.CacheExt;
import com.systems.concurrent.utils.EncryptionUtil;

public class UserAction extends CRUDAction<UserData> {

	private static final long serialVersionUID = 2832417492746999732L;
	private UserData user;
	private List<UserData> userList = new ArrayList<UserData>();
	private File fileUpload;
	private List<ProjectData> projectList;

	public List<ProjectData> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<ProjectData> projectList) {
		this.projectList = projectList;
	}

	public void setUserList(List<UserData> userList) {
		this.userList = userList;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public void setUser(UserData user) {
		this.user = user;
	}

	public UserData getUser() {
		if (user == null)
			return getItem();
		else
			return user;
	}

	public String SendFile() throws Exception {

		RandomAccessFile f = new RandomAccessFile(fileUpload, "r");
		byte[] bFile = new byte[(int) f.length()];
		f.read(bFile);
		f.close();
		user = new UserData();
		user.setId(getUserContext().getId());
		user.setLogo(bFile);
		user = UserDao.getInstance().sendLogo(user);

		return Action.SUCCESS;

	}

	public String Load() {
		if (!isLoad) {
			/*
			 * if(getId()!=null) user=UserDao.getInstance().getItem(getId());
			 * //after registrtation else
			 */
			user = UserDao.getInstance().getItem(getUserContext().getId()); // after
																			// login
			setAttribute("item", user);
		}
		// setAttribute("item", user);
		// getSession().put("item", user);
		// setItem(user);
		// System.out.println(user);
		return super.Load();

	}

	public String ChangePassword() {
		// UserData user = getUser();
		System.out.println("change pass");
		UserData user = getUser();
		String passw = EncryptionUtil.crypt(user.getPassword() + user.getEmail());
		user.setPassword(passw);
		UserDao.getInstance().modifyItem(user);
		return Action.SUCCESS;
	}

	public String Modify() {

		if ("Modify".equals(getActionName())) {
			// user=UserDao.getInstance().getItem(getUserContext().getId());
			// setItem(user);
			return super.Modify();
		} // if("Save".equals(getActionName()))
		UserDao.getInstance().modifyItem(getUser());
		editMode = false;
		CacheExt.refreshCache();
		return Action.SUCCESS;

		/*
		 * if(!isEditMode()){
		 * 
		 * 
		 * } else return Save();
		 */
	}

	@Override
	public String Create() {
		return Save();

	}

	@Override
	public String Remove() {
		Long id = Long.parseLong(request.getParameter("id"));
		UserDao.getInstance().removeItem(id);
		CacheExt.refreshCache();
		return Action.SUCCESS;

	}

	@Override
	public String Save() {
		// managerBean.createItem(user, UserDao.class);
		UserDao.getInstance().createItem(user);
		CacheExt.refreshCache();
		return super.Save();
	}

	@Override
	public String List() {
		userList = UserDao.getInstance().getItems();
		return Action.SUCCESS;

	}

	public List<UserData> getUserList() {
		return userList;
	}

	public String Details() {
		Long id = Long.parseLong(request.getParameter("id"));
		user = UserDao.getInstance().getItem(id);
		projectList = user.getProjects();
		System.out.println(projectList.size());
		return "details";
	}

}
