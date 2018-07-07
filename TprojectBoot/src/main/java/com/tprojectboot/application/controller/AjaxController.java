package com.tprojectboot.application.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tprojectboot.application.dao.UserInfoDao;
import com.tprojectboot.application.model.UserInfo;
import com.tprojectboot.application.service.AndroidPushNotificationsService;
import com.tprojectboot.application.service.HomeServiceImpl;
import com.tprojectboot.application.vo.MemberInfoVO;
import com.tprojectboot.application.vo.UserListResponseVO;

@RestController
public class AjaxController {
	@Autowired
	HomeServiceImpl homeservice;
	
	@Autowired
	AndroidPushNotificationsService androidPushNotificationsService;
	
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
	
	@RequestMapping(value = "/jpatest.do", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> getUserInfo(@RequestBody Map<String, Object> paramInfo) {
		//DB서비스 호출//
		List<UserInfo> userlist = homeservice.getUserInfo();

		for(int i=0; i<userlist.size(); i++){
			System.out.println("address: " + userlist.get(i).getUser_address());
		}
			
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//

		retVal.put("result", "success");
		
		return retVal;
	}
	
	@RequestMapping(value = "/jpatestsearch.do", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> searchUserEmail(@RequestBody Map<String, Object> paramInfo) {
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
		
		//DB서비스 호출//
		try{
			List<UserInfo> usersearch = homeservice.getUserEmailInfo(paramInfo.get("userid").toString());
			
			if(usersearch.size() == 0){
				retVal.put("result", "fail");
			} else{
				System.out.println("address: " + usersearch.get(0).getUser_address());
				
				retVal.put("result", "success");
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return retVal;
	}
	
	@RequestMapping(value = "/jpatestsearchemail.do", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> searchUserPhonenumber(@RequestBody Map<String, Object> paramInfo) {
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
		
		//DB서비스 호출//
		try{
			List<UserInfo> usersearch = homeservice.getUserPhoneNumberInfo(paramInfo.get("useremail").toString(), paramInfo.get("userid").toString());
			
			if(usersearch.size() == 0){
				retVal.put("result", "fail");
			} else{
				System.out.println("phonenumber: " + usersearch.get(0).getUser_phonenumber());
				
				retVal.put("result", "success");
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return retVal;
	}
	
	@RequestMapping(value = "/jpatestdeleteuser.do", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody Map<String, Object> deleteUser(@RequestBody Map<String, Object> paramInfo) {
		Map<String, Object> retVal = new HashMap<String, Object>(); //諛섑솚�븷 ���엯�쓽 �겢�옒�뒪瑜� �꽑�뼵//
		
		//DB서비스 호출//
		try{
			int result = homeservice.deleteUser(paramInfo.get("userid").toString());
			
			if(result == 0){
				retVal.put("result", "fail");
			} else{
				System.out.println("delete success " + paramInfo.get("userid").toString());
				
				retVal.put("result", "success");
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return retVal;
	}
	
	@RequestMapping(value = "/sendmessage", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody ResponseEntity<String> send(@RequestBody Map<String, Object> paramInfo) throws JSONException{
		Map<String, Object> retVal = new HashMap<String, Object>();
		
		//System.out.println("token: " + paramInfo.get("token"));
		//System.out.println("message: " + paramInfo.get("message"));
		
		//FCM 메시지 전송//
		JSONObject body = new JSONObject();
		
		//DB에 저장된 여러개의 토큰(수신자)을 가져와서 설정할 수 있다.//
		List<String> tokenlist = new ArrayList<String>();
		//DB과정 생략(직접 대입)//
		tokenlist.add("token value 1");
		tokenlist.add("token value 2");
		tokenlist.add("token value 3");
		
		JSONArray array = new JSONArray();
		
		for(int i=0; i<tokenlist.size(); i++) {
			array.put(tokenlist.get(i));
		}
		
		body.put("registration_ids", array); //여러개의 메시지일 경우 registration_ids, 단일 메세지는 to사용//
 
		JSONObject notification = new JSONObject();
		notification.put("title", "FCM Test App");
		notification.put("body", paramInfo.get("message"));
		
		body.put("notification", notification);
		
		System.out.println(body.toString());
		
		HttpEntity<String> request = new HttpEntity<>(body.toString());
		 
		CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
		CompletableFuture.allOf(pushNotification).join();
 
		try {
			String firebaseResponse = pushNotification.get();
			
			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
 
		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	}
}
