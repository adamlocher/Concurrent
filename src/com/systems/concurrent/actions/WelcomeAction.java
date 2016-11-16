package com.systems.concurrent.actions;

import java.util.List;

import com.systems.concurrent.ejb.dao.UserDao;
import com.systems.concurrent.ejb.dto.ProjectData;
import com.systems.concurrent.ejb.dto.UserData;

public class WelcomeAction extends AbstractAction {

	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	
	public List<ProjectData> getUserProjects(){
		UserData user=UserDao.getInstance().getItem(getUserContext().getId());
		return user.getProjects();
	}
}
