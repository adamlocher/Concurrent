package com.systems.concurrent.actions;

import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.systems.concurrent.ejb.dao.UserDao;
import com.systems.concurrent.ejb.dto.UserData;

public class ApproveAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3360525893526511497L;
	private List<UserData> userList;
	private List<UserData> usersNotActive;
	private List<Long> usersId;
	

	public List<UserData> getUsersNotActive() {
		return usersNotActive;
	}
	
	public void setUsersNotActive(List<UserData> usersNotActive) {
		this.usersNotActive = usersNotActive;
	}
	
	public ApproveAction() {
		usersNotActive = UserDao.getInstance().getUsersNotApproved();
		userList=UserDao.getInstance().getItems();
	}
   public String Save(){
	   UserDao.getInstance().modifyFlags(usersId);
	   for(UserData u: userList){
		   if(usersId.contains(u.getId()))
				   u.setApproved(Boolean.TRUE);
	   }
	   Iterator<UserData> users=usersNotActive.iterator();
	  while(users.hasNext()){
		   UserData u=users.next();
		   if(usersId.contains(u.getId()))
			   users.remove();
	   }
	   return Action.INPUT;
   }

	
	public String List() {
		return Action.INPUT;
	}

	public List<UserData> getUserList() {
		return userList;
	}

	public void setUserList(List<UserData> userList) {
		this.userList = userList;
	}

	public List<Long> getUsersId() {
		return usersId;
	}
	public void setUsersId(List<Long> usersId) {
		this.usersId = usersId;
	}
}
