package com.tprojectboot.application.service;

import java.util.List;
import java.util.Map;

import com.tprojectboot.application.model.UserInfo;
import com.tprojectboot.application.vo.MemberInfoVO;
import com.tprojectboot.application.vo.UserListResponseVO;
import com.tprojectboot.application.vo.UserInfoVO;

public interface HomeService {
	public void testservice(Map<String, Object> mapvalue);
	public List<MemberInfoVO> getMemberInfo(String username);
	public List<UserListResponseVO> getUserListService();
	public List<UserInfo> getUserInfo();
	public List<UserInfo> getUserEmailInfo(String userid);
	public List<UserInfo> getUserPhoneNumberInfo(String user_email, String user_id);
	public int deleteUser(String user_id);
}
