package com.systems.concurrent.ejb.dao;

import com.systems.concurrent.ejb.dto.CommentData;

public class CommentDao extends AbstractDao<CommentData> {

	
	private static final CommentDao instance = new CommentDao();
	protected CommentDao() {
		super(CommentData.class);
	}
	
	public static final CommentDao getInstance() {
		return CommentDao.instance;
	}
	
}
