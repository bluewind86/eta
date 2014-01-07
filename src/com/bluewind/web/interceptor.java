package com.bluewind.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class interceptor extends HandlerInterceptorAdapter{
	
	public static final Logger log = Logger.getLogger(interceptor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("front action preHandle....");
		System.out.println("我是拦截器");
		return true;
	}
}
