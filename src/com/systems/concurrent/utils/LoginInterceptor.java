package com.systems.concurrent.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.systems.concurrent.actions.AbstractAction;
import com.systems.concurrent.actions.LoginAction;
import com.systems.concurrent.actions.RegistrationAction;

public class LoginInterceptor  extends AbstractInterceptor {

	
	
        @Override
        public String intercept(ActionInvocation invocation) throws Exception {
            	AbstractAction action=(AbstractAction) invocation.getAction();
                //Map<String, Object> session = invocation.getInvocationContext().getSession();
                HttpServletRequest request = ServletActionContext.getRequest();
                String result="";
                String actionName = ServletActionContext.getActionMapping().getName();
                String methodName = ServletActionContext.getActionMapping().getMethod();
                UserContext userContext = (UserContext) request.getSession().getAttribute(AbstractAction.USERCTX);
                String actionPrv=(String)request.getSession().getAttribute("prevAction");
                if(userContext==null && !(action instanceof LoginAction) && !(action instanceof RegistrationAction)){
                	if(!(action instanceof LoginAction) && !(action instanceof RegistrationAction))
                		request.getSession().setAttribute("prevAction", actionName);
                	action.setReturnUrl("login");
                	result="returnurl";
                }else{
                	if(!(action instanceof LoginAction) && !(action instanceof RegistrationAction) && actionPrv!=null){
                		action.setReturnUrl(actionPrv);
                		result="returnurl";
                		request.getSession().removeAttribute("prevAction");
                		return result;
                	}
                	action.setUserContext(userContext);
                	 try{
                     	result=invocation.invoke();
                     }catch(Exception e){
                     	System.err.println(e.getStackTrace());
                     }
                }
                return result;
                
             
        }
}