package com.example.nagoyameshi.event;

import org.springframework.context.ApplicationEvent;

import com.example.nagoyameshi.entity.Users;

import lombok.Getter;

@Getter
public class SignupEvent extends ApplicationEvent {
	private Users user;
	private String requestUrl;
	
	public SignupEvent(Object source,Users user,String requestUrl)
	{
		super(source);
		
		this.user=user;
		this.requestUrl=requestUrl;
	}
	
	
	

}
