package com.tprojectboot.application.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.tprojectboot.application.vo.MemberInfoVO;
import com.tprojectboot.application.vo.UserListResponseVO;

@Repository("homedao")
public class HomeDAOImpl implements HomeDao{
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private SqlSessionTemplate sessiontemplate;
	@PersistenceContext
    EntityManager em;
	
	private String collectionname = "memberdb";
	private String namespace = "usermanager";
	
	@Override
	public List<MemberInfoVO> getMemberInfo(String username) {
		System.out.println("memberinfo dao call");
		
		//몽고디비에서 사용자 이름을 조건으로 해서 정보를 검색//
		Query query = new Query(new Criteria().andOperator(
				Criteria.where("username").is(username)
		));
				
		return mongoTemplate.find(query, MemberInfoVO.class, collectionname);
	}

	@Override
	public List<UserListResponseVO> getUserListDao() {
		System.out.println("mybatis dao call");
		
		List<UserListResponseVO> userlist = new ArrayList<UserListResponseVO>();
		
		return userlist = sessiontemplate.selectList(namespace+".userlist");
	}
}
