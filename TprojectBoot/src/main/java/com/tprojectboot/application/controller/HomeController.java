package com.tprojectboot.application.controller;
//com.tprojectboot.application 이것의 하위 컴포넌트를 스캔하기에 경로를 요기까지 default로 설정해준다.//
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
//@RestController = @Controller + @ResponseBody
public class HomeController {
	@RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView mv){
		System.out.println("normal home page");
		
		mv.setViewName("home");
		
		return mv;
    }
	
	@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public ModelAndView normaluser(ModelAndView mv){
		System.out.println("normal home page");
		
		mv.setViewName("user");
		
		return mv;
    }
}
