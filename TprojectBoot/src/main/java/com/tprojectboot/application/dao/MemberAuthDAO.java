package com.tprojectboot.application.dao;

import java.util.List;
import java.util.Map;

import com.tprojectboot.application.vo.MemberAuthVO;

public interface MemberAuthDAO {
	public List<Map<String, Object>> getMemberInfo(Map<String, Object> params);
}
