package com.tprojectboot.application.service;

import java.util.List;
import java.util.Map;

import com.tprojectboot.application.vo.MemberInfoVO;
import com.tprojectboot.application.vo.UserListResponseVO;

public interface HomeService {
	public void testservice(Map<String, Object> mapvalue);
	public List<MemberInfoVO> getMemberInfo(String username);
	public List<UserListResponseVO> getUserListService();
}
