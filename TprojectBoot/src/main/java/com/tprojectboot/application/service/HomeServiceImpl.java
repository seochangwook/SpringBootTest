package com.tprojectboot.application.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tprojectboot.application.dao.HomeDAOImpl;
import com.tprojectboot.application.dao.UserInfoDao;
import com.tprojectboot.application.model.UserInfo;
import com.tprojectboot.application.vo.MemberInfoVO;
import com.tprojectboot.application.vo.UserListResponseVO;
import com.tprojectboot.application.vo.UserInfoVO;

@Service("homeservice")
public class HomeServiceImpl implements HomeService{
	@Autowired
	HomeDAOImpl homedaoimpl;
	@Autowired
	UserInfoDao userInfoDao;

	@Override
	public void testservice(Map<String, Object> mapvalue) {
		// TODO Auto-generated method stub
		System.out.println("service call" + mapvalue.get("value1"));
	}

	@Override
	public List<MemberInfoVO> getMemberInfo(String username) {
		// TODO Auto-generated method stub
		System.out.println("service call search name: " + username);
		
		List<MemberInfoVO>memberinfo = homedaoimpl.getMemberInfo(username);
		
		return memberinfo;
	}

	@Override
	public List<UserListResponseVO> getUserListService() {
		System.out.println("mybatis service call");
		
		List<UserListResponseVO> memberlist = homedaoimpl.getUserListDao();
		
		return memberlist;
	}

	@Override
	public List<UserInfo> getUserInfo() {
		userInfoDao.findAll(); //CRUD작업을 하기 전 JPA 데이터 초기화//
		
		List<UserInfo> userlist = userInfoDao.findAll();
		
		return userlist;
	}

	@Override
	public List<UserInfo> getUserEmailInfo(String userid) {
		userInfoDao.findAll(); //CRUD작업을 하기 전 JPA 데이터 초기화//
		
		List<UserInfo> userlist = userInfoDao.findByUserEmail(userid);
		
		return userlist;
	}

	@Override
	public List<UserInfo> getUserPhoneNumberInfo(String user_email, String user_id) {
		userInfoDao.findAll(); //CRUD작업을 하기 전 JPA 데이터 초기화//
		
		List<UserInfo> userlist = userInfoDao.findByUserPhoneNumber(user_email, user_id);
		
		return userlist;
	}

	@Override
	public int deleteUser(String user_id) {
		userInfoDao.findAll(); //CRUD작업을 하기 전 JPA 데이터 초기화//
		
		int result = userInfoDao.deleteUserById(user_id);
		
		return result;
	}
}
