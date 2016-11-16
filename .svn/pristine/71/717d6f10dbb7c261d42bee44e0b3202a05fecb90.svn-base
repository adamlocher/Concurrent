package com.systems.concurrent.ejb.dao;

import java.util.List;

import javax.persistence.Query;

import com.systems.concurrent.ejb.dto.ChatData;
import com.systems.concurrent.ejb.dto.TaskData;
import com.systems.concurrent.ejb.dto.UserData;
import com.systems.concurrent.utils.EMFactory;

public class ChatDao extends AbstractDao<ChatData> {

	private static final ChatDao instance = new ChatDao();

	protected ChatDao() {
		super(ChatData.class);
	}

	public static final ChatDao getInstance() {
		return ChatDao.instance;
	}

}
