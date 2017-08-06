package com.tprojectboot.application.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthController {
	//Spring Security의 커스터마이징 페이지 경로//
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mv) {
		mv.setViewName("login/loginview");
			
		System.out.println("security login adjust");
		
		return mv;
	}
	
	//로그인 실패 관련 처리 페이지//
	@RequestMapping(value = "/loginerror.do", method = RequestMethod.GET)
		public ModelAndView loginerror(ModelAndView mv) {
		mv.setViewName("error/loginerrorview");
			
		System.out.println("login error");
			
		return mv;
	}
	
	//권한관련 에러처리 페이지//
	@RequestMapping(value = "/autherror.do", method = RequestMethod.GET)
		public ModelAndView authaccesserror(ModelAndView mv) {
		mv.setViewName("error/autherrorview");
				
		System.out.println("login access auth error");
				
		return mv;
	}
	
	@RequestMapping(value = "/adminlogoutajax", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultmap = new HashMap<String, Object>();

		boolean is_insert_success = false;

		//로그아웃//
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println("--------------------------");
		System.out.println("auth name info: " + auth.getName());
		System.out.println("auth detail info: " + auth.getDetails());
		System.out.println("auth detail info: " + auth.getPrincipal());
		System.out.println("--------------------------");
		
		//로그아웃 상태인지 점검//
		//logout()시 HttpSession을 이용하기에 내부적으로 세션을 만료시킴//
		System.out.println("auth info: " + auth);
		
		if(auth != null){
			 new SecurityContextLogoutHandler().logout(request, response, auth);
			 
			 is_insert_success = true;
			 
			 System.out.println("logout success...");
		}
		
		resultmap.put("resultmsg", ""+is_insert_success);
		
		System.out.println("logout process...");
		
		return resultmap;
	}
}
