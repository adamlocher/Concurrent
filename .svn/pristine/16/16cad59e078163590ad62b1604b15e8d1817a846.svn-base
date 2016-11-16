package com.systems.concurrent.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.systems.concurrent.ejb.dao.UserDao;
import com.systems.concurrent.ejb.dto.UserData;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheExt {

	static CacheManager cm = CacheManager.getInstance();
	public static Map<Long,UserData> users= new HashMap<>();
	
	public static Element getCachedObject(String name){
		Cache cache= cm.getCache("cache");
		return cache.get(name);
	}
	static{
		List<UserData> userList=UserDao.getInstance().getItems();
		int i=0;
		for(UserData u: userList){
			if(u.getEmail().equals("system@system.com"))
				users.put(0L, u);
			//System.out.println(u.getId()+" "+i++);
			users.put(u.getId(), u);}
	}
	public static void refreshCache(){
		List<UserData> userList=UserDao.getInstance().getItems();
		for(UserData u: userList){
			u.setLogo(null);
			if(u.getEmail().equals("system@system.com"))
				users.put(0L, u);
			users.put(u.getId(), u);
		}
	}
}
