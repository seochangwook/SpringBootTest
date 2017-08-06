package com.tprojectboot.application.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.tprojectboot.application.vo.MemberAuthVO;

@Repository("memberauthdao")
public class MemberAuthDAOImpl implements MemberAuthDAO{
	@Autowired
	private MongoTemplate mongoTemplate;
	
	private String collectionname = "memberdb";
	
	@Override
	public List<MemberAuthVO> getMemberInfo(String username) {
		//몽고디비에서 사용자 이름을 조건으로 해서 정보를 검색//
		Query query = new Query(new Criteria().andOperator(
				Criteria.where("username").is(username)
		));
		
		System.out.println("--> memberdb dao call success...");
		
		return mongoTemplate.find(query, MemberAuthVO.class, collectionname);
	}

}
