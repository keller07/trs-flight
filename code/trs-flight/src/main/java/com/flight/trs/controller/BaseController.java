package com.flight.trs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月20日 下午10:06:47
 * @version V1.0
 */
public class BaseController {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	
	//每次请求该controller时都会先将request对象和responce对象注入
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
		
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		
	}
	
}
