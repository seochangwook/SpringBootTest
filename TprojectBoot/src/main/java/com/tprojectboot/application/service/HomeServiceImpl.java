package com.tprojectboot.application.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tprojectboot.application.dao.HomeDAOImpl;
import com.tprojectboot.application.vo.MemberInfoVO;
import com.tprojectboot.application.vo.UserListResponseVO;

@Service("homeservice")
public class HomeServiceImpl implements HomeService{
	@Autowired
	HomeDAOImpl homedaoimpl;

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
}
