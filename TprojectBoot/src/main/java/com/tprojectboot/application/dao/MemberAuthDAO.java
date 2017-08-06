package com.tprojectboot.application.dao;

import java.util.List;

import com.tprojectboot.application.vo.MemberAuthVO;

public interface MemberAuthDAO {
	public List<MemberAuthVO> getMemberInfo(String username);
}
