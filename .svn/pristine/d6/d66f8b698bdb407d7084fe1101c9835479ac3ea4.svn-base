package com.systems.concurrent.utils;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.systems.concurrent.ejb.dao.UserDao;
import com.systems.concurrent.ejb.dto.UserData;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheListener implements ServletContextListener{
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		CacheExt.cm.addCache("cache");
		List<UserData> users=UserDao.getInstance().getUsersPM();
	//3. Get a cache called "cache1"
		Cache cache = CacheExt.cm.getCache("cache");
	
	//4. Put few elements in cache
		//for(UserData u:users)
			cache.put(new Element("USERS",users));
	//5. Get element from cache
	Element ele = cache.get("USERS");
	
	//6. Print out the element
	//String output = (ele == null ? null : ele.getObjectValue().toString());
	//System.out.println(output);
	
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		CacheExt.cm.shutdown();
		
	}
	
}