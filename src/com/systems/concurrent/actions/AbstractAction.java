package com.systems.concurrent.actions;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.Action;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.systems.concurrent.utils.UserContext;

public abstract class AbstractAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 7445313342664436775L;

	protected boolean isLoad;

	protected boolean login;
	protected HttpServletRequest request;
	protected UserContext userContext;
	public final static String USERCTX = "USER_CONTEXT";
	protected String returnUrl;

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}

	public UserContext getUserContext() {
		return userContext;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return this.request;
	}

	public boolean isLogin() {
		return login;
	}

	protected void setAttribute(String name, Object value) {
		request.getSession().setAttribute(name, value);
	}

	protected Object getAttribute(String name) {
		return request != null ? request.getSession().getAttribute(name) : null;
	}

	protected void removeAttribute(String name) {
		request.getSession().removeAttribute(name);
	}

}
