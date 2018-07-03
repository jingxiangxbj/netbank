package com.netbank.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Persion2 extends ActionSupport implements RequestAware,SessionAware {
	Map<String , Object> session;
	Map<String, Object> request;
	
	@Override
	public void setSession(Map<String, Object> arg0) {
			this.session=session;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		
		
	}
	

}
