package com.systems.concurrent.utils;


public class ValidatorUtil {

	
	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final String PASSWORD_PATTERN =   
		".{7,20}"; 

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public static boolean validateEmail(final String hex) {
		if(hex!=null)
			return hex.matches(EMAIL_PATTERN);
		return false;
	}
	
	public static boolean validatePassword(final String pass){
		if(pass!=null)
			return pass.matches(PASSWORD_PATTERN);
		return false;
	}
}