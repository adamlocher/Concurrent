package com.systems.concurrent.ejb.dto;

import java.io.Serializable;

public abstract class AbstractData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2838623348054658599L;

	public abstract Long getId();
	
	public abstract void setId(Long id);
	
}
