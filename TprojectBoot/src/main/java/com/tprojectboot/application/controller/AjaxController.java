package com.tprojectboot.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tprojectboot.application.service.HomeServiceImpl;
import com.tprojectboot.application.vo.MemberInfoVO;
import com.tprojectboot.application.vo.UserListResponseVO;

@RestController
public class AjaxController {
	@Autowired
	HomeServiceImpl homeservice;
	
	
	@RequestMapping(value = "/ajaxtest", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> makerepo(@RequestBody Map<String, Object> info) {	
		System.out.println("value1: " + info.get("value1") + " / value2: " + info.get("value2"));
		
		homeservice.testservice(info);
		
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
		
		retVal.put("result", "success!!");
		
		return retVal;
	}
	
	@RequestMapping(value = "/memberinfocall", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> memberinfo(@RequestBody Map<String, Object> info) {	
		System.out.println("search name: " + info.get("name"));
		
		List<MemberInfoVO> memberlist = homeservice.getMemberInfo(info.get("name").toString());
		
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
		
		retVal.put("result", "success!!");
		retVal.put("memberlist", memberlist);
		
		return retVal;
	}
	
	@RequestMapping(value = "/memberinfocalloracle", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> memberinfooracle(@RequestBody Map<String, Object> info) {	
		System.out.println("member list call");
		
		List<UserListResponseVO> memberlist = homeservice.getUserListService();
		
		System.out.println("size: " + memberlist.size());
		
		for(int i=0; i<memberlist.size(); i++){
			System.out.println("name: " + memberlist.get(i).getUserName());
		}
		
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
		
		retVal.put("result", "success!!");
		retVal.put("memberlist", memberlist);
		
		return retVal;
	}
}
