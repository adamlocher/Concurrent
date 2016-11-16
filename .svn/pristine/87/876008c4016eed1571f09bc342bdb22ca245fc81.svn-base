package com.systems.concurrent.actions;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.systems.concurrent.ejb.dao.ChatDao;
import com.systems.concurrent.ejb.dao.ProjectDao;
import com.systems.concurrent.ejb.dto.ChatData;
import com.systems.concurrent.ejb.dto.ProjectData;
import com.systems.concurrent.utils.CacheExt;
import com.systems.concurrent.utils.UserContext;

import net.sf.ehcache.Element;

@ServerEndpoint("/chat/chatroom")
public class ChatAction extends AbstractAction {

	private static final long serialVersionUID = 82231336890054102L;
	static Set<Session> chatroomUsers = Collections.synchronizedSet(new HashSet<Session>());
	ChatData chatData;
	ProjectData project;
	private List<ChatData> chatList;

	@OnOpen
	public void handleOpen(Session userSession) throws IOException {
		userSession.getUserProperties().put("username", userSession.getQueryString());
		Iterator<Session> iterator = chatroomUsers.iterator();
		while (iterator.hasNext()) {
			iterator.next().getBasicRemote().sendText(buildJsonMessageData("System",
					userSession.getUserProperties().get("username") + " has joined the chat!"));
		}
		chatroomUsers.add(userSession);
		List(userSession);
	}

	public void List(Session userSession) throws IOException {
		chatList = ChatDao.getInstance().getItems();
		for (ChatData chat : chatList) {
			userSession.getBasicRemote().sendText(buildJsonMessageData(chat.getUsername(), chat.getMessage()));
		}
	}

	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException {
		String username = (String) userSession.getUserProperties().get("username");
		Iterator<Session> iterator = chatroomUsers.iterator();
		chatData = new ChatData();
		chatData.setUsername(username);
		chatData.setMessage(message);
		ChatDao.getInstance().createItem(chatData);
		while (iterator.hasNext()) {
			iterator.next().getBasicRemote().sendText(buildJsonMessageData(username, message));
		}
	}

	@OnClose
	public void handleClose(Session userSession) throws IOException {
		chatroomUsers.remove(userSession);
		Iterator<Session> iterator = chatroomUsers.iterator();
		while (iterator.hasNext()) {
			iterator.next().getBasicRemote().sendText(buildJsonMessageData("System",
					userSession.getUserProperties().get("username") + " has left the chat!"));
		}
	}

	private String buildJsonMessageData(String username, String message) {
		JsonObject jsonObject = Json.createObjectBuilder().add("message", username + ": " + message).build();
		StringWriter stringWriter = new StringWriter();
		try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
			jsonWriter.write(jsonObject);
		}
		return stringWriter.toString();
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

}
