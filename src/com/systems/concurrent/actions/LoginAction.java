package com.systems.concurrent.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.systems.concurrent.ejb.dao.UserDao;
import com.systems.concurrent.ejb.dto.UserData;
import com.systems.concurrent.utils.EncryptionUtil;
import com.systems.concurrent.utils.UserContext;
import com.systems.concurrent.utils.ValidatorUtil;

public class LoginAction extends AbstractAction {// implements SessionAware{

	private static final long serialVersionUID = -6950411248750851686L;
	private UserData user;
	// private Map<String, Object> session;

	/*
	 * public String home() { return SUCCESS; }
	 */

	public String Login() throws Exception {

		if (request.getMethod().equals("GET"))
			return LOGIN;

		String passw = EncryptionUtil.crypt(user.getPassword() + user.getEmail());
		if (user.getEmail() == null || user.getEmail().isEmpty()) {
			addActionError("Login can't be empty");
			return LOGIN;
		} else {

			user = UserDao.getInstance().login(user.getEmail(), passw);
			if (user != null) {
				if (!user.getApproved()) {
					addActionError("You are not approved by administrator");
					return LOGIN;
				}
				// getSession().put("user", user);
				// session.put("loginId", user.getId());
				setUserContext(new UserContext(user.getId(), user.getEmail(), user.getAccType()));
				// setAttribute("user", user);
				setAttribute(USERCTX, getUserContext());
				return SUCCESS;
			} else {
				addActionError("Email or password incorrect!");
			}
			return LOGIN;
		}
	}
	/*
	 * public Map<String, Object> getSession() { return session; }
	 */

	public String logOut() {
		removeAttribute(USERCTX);
		// session.remove("loginId");
		addActionMessage("You have been Successfully Logged Out");
		return SUCCESS;
	}

	public void setUser(UserData user) {
		this.user = user;
	}

	public UserData getUser() {
		return user;
	}

	public String Display() throws Exception {
		user = new UserData();
		user.setEmail("lukasz@onet.pl");
		return Action.NONE;
	}

	@Override
	public void validate() {
		if (user != null) {
			if (user.getEmail() == null || user.getEmail().isEmpty())
				addFieldError("user.email", "Email is required.");
			else if (!ValidatorUtil.validateEmail(user.getEmail()))
				addFieldError("user.email", "Incorrect email");
			if (user.getPassword() == null || user.getPassword().isEmpty())
				addFieldError("user.password", "Password is required.");

		}

	}

	/*
	 * public void setSession(Map<String, Object> session) {
	 * this.session=session;
	 * 
	 * }
	 */
}
