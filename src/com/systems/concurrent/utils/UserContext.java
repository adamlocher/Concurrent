package com.systems.concurrent.utils;


public class UserContext  {
	
	Long id;
	String email;
	String permission;
	public UserContext() {
		// TODO Auto-generated constructor stub
	}

	public UserContext(Long id, String email,String permission) {
		super();
		this.id = id;
		this.email = email;
		this.permission=permission;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPermission() {
		return permission;
	}

}
