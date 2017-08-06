package com.tprojectboot.application.dao;

import java.util.List;

import com.tprojectboot.application.vo.MemberInfoVO;
import com.tprojectboot.application.vo.UserListResponseVO;

public interface HomeDao {
	public List<MemberInfoVO> getMemberInfo(String username);
	public List<UserListResponseVO> getUserListDao();
}
