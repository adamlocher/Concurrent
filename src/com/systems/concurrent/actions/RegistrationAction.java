package com.systems.concurrent.actions;

import java.util.Date;

import com.systems.concurrent.ejb.dao.UserDao;
import com.systems.concurrent.ejb.dto.UserData;
import com.systems.concurrent.utils.CacheExt;
import com.systems.concurrent.utils.EncryptionUtil;
import com.systems.concurrent.utils.ValidatorUtil;

public class RegistrationAction extends CRUDAction<UserData> {

	private static final long serialVersionUID = 843150621728845503L;
	private UserData user;

	public String Register() throws Exception {

		if (request.getMethod().equals("GET"))
			return NONE;

		String passw = EncryptionUtil.crypt(user.getPassword() + user.getEmail());
		user.setPassword(passw);
		try {
			UserDao.getInstance().createItem(user);
			System.out.println(user.getId());
			addActionMessage("Registration was successfull. Please wait for admin verification");
		} catch (Exception e) {
			System.err.println(e.getMessage() + "\n " + e.getStackTrace());
			getActionErrors().add(e.getMessage()); // to fix
			return NONE;
		}
		CacheExt.refreshCache();
		return SUCCESS;
	}

	public void setUser(UserData user) {
		this.user = user;
	}

	public UserData getUser() {
		return user;
	}

	public Date getTodayDate() {

		return new Date();
	}

	@Override
	public void validate() {

		if (user != null) {
			if (user.getName() == null || user.getName().isEmpty())
				addFieldError("user.name", "First name is required.");
			if (user.getSurname() == null || user.getSurname().isEmpty())
				addFieldError("user.surname", "Surname name is required.");
			if (user.getPassword() == null || user.getPassword().isEmpty())
				addFieldError("user.password", "Password is required.");
			else if (!ValidatorUtil.validatePassword(user.getPassword()))
				addFieldError("user.password", "Password should contain more than 6 characters");
			if (user.getBirthday() == null)
				addFieldError("user.birthday", "Birthday field is required.");

		}

	}

	@Override
	public String Create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String List() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Remove() {
		// TODO Auto-generated method stub
		return null;
	}

	Long userId = 100L;

	public void setUserId(Long userId) {
		System.out.println("id=" + userId);
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}
}
