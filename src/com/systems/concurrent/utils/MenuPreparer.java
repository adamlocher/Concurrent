package com.systems.concurrent.utils;
import org.apache.tiles.preparer.ViewPreparer;

import com.opensymphony.xwork2.ActionContext;
import com.systems.concurrent.actions.AbstractAction;
import com.systems.concurrent.ejb.dto.UserData;

import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.struts2.ServletActionContext;
import org.apache.tiles.AttributeContext;
 
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
 
public class MenuPreparer implements ViewPreparer {
 
  public void execute(TilesRequestContext context, AttributeContext attributeContext)
    throws PreparerException {
	  HttpServletRequest request= ServletActionContext.getRequest();
	  boolean login=true;
	  UserContext usrContext=(UserContext)request.getSession().getAttribute(AbstractAction.USERCTX);
	  if(usrContext==null)
	    login=false;
	
	ArrayList<MenuPreparer.MenuItem>  menu = new ArrayList<MenuPreparer.MenuItem>();
   
    if(login){
    	menu.add(new MenuItem("Logout", "logOut"));
    	menu.add(new MenuItem("My account", "userLoad"));
    	//menu.add(new MenuItem("Index","welcome"));
    	if(UserData.AccounTypes.ADMIN.equals(usrContext.getPermission())){
    		menu.add(new MenuItem("Add project","projectCreate"));
    	}
    	menu.add(new MenuItem("Users","approveList"));
    	menu.add(new MenuItem("Show projects","projectList"));
    }
   
    context.getRequestScope().put("menu", menu);
    context.getRequestScope().put("login", login);
  }
 
  public static class MenuItem {
    private String url;
    private String caption;
    public MenuItem(String caption, String url) {
        this.caption = caption;
        this.url = url;
    }
    public String getUrl() {return url;}
    public String getCaption() {return caption;}
  }
}