package com.tprojectboot.application.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminController {
	@Value("${server.address}")
	private String serverIP;
	
	@Value("${server.port}")
	private String serverPORT;
	
	@RequestMapping(value = "/admin/main", method = RequestMethod.GET)
    public ModelAndView adminmain(ModelAndView mv){
		System.out.println("admin main page");
		
		mv.setViewName("admin/main");
		
		//세션 등록//
		//사용자 정보 출력(세션)//
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("user name :" + user.getUsername());
		
		mv.addObject("serveraddress", "http://"+serverIP+":"+serverPORT+"/chatting.do");
		mv.addObject("sessionid", user.getUsername());
		
		return mv;
    }
	
	@RequestMapping(value = "/admin/mainbasic", method = RequestMethod.GET)
    public ModelAndView adminmainbasic(ModelAndView mv){
		System.out.println("admin mainbasic page");
		
		mv.setViewName("admin/mainbasic");
		
		//세션 등록//
		//사용자 정보 출력(세션)//
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("user name :" + user.getUsername());
		
		mv.addObject("sessionid", user.getUsername());
		
		return mv;
    }
	
	@RequestMapping(value = "/chatting.do", method = RequestMethod.GET)
	public ModelAndView chat(ModelAndView mv) {
		mv.setViewName("chat/chattingview");
		
		//세션 등록//
		//사용자 정보 출력(세션)//
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("user name :" + user.getUsername());
		
		
		
		mv.addObject("chatoutaddress", "http://"+serverIP+":"+serverPORT+"/admin/main");
		mv.addObject("userid", user.getUsername());
		
		return mv;
	}
}
