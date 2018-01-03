package com.tprojectboot.application.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.tprojectboot.application.vo.MemberAuthVO;

@Repository("memberauthdao")
public class MemberAuthDAOImpl implements MemberAuthDAO{
	@Autowired
    private SqlSessionTemplate sqlSession;
	
	@Override
	public List<Map<String, Object>> getMemberInfo(Map<String, Object> params) {
		//몽고디비에서 사용자 이름을 조건으로 해서 정보를 검색//
		return sqlSession.selectList("usermanager.searchuser", params);
	}

}
