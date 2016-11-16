package com.tutorialspoint.struts2;


import com.systems.concurrent.ejb.dto.UserData;

public class HelloWorldAction {
   
	private UserData userData;
	
	public String execute() throws Exception {
		
		//userData.setName("Adam");
		if(userData.getName().equals("Adam"))
			return "SUCCESS";
		else 
			return "ERROR";  
	   /* if ("SECRET".equals(name))
	    {
	       return SUCCESS;
	    }else{
	       return ERROR;  
	    }*/
	 }

 
   public void setUserData(UserData userData) {
	this.userData = userData;
   }
   public UserData getUserData() {
	return userData;
}
}