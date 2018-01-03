package com.tprojectboot.application.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tprojectboot.application.dao.MemberAuthDAOImpl;
import com.tprojectboot.application.vo.MemberAuthVO;

@Service("memberauthservice")
public class LoginServiceImpl implements UserDetailsService{
	@Autowired
	MemberAuthDAOImpl memberinfodao;
	@Autowired
	ShaPasswordEncoder passwordEncoder;

	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		System.out.println("***************<Security Check>***************");
		System.out.println("Auth Checking...");
		
		UserDetails user = null;
		
		//데이터베이스에서 데이터가 있는지 검증//
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchId", userid);
		
		List<Map<String, Object>> userinfo = memberinfodao.getMemberInfo(params);
		
		//null이면 해당 유저정보가 없다는 의미//
		if(userinfo == null){
			System.out.println("**********************************************");
			
			throw new UsernameNotFoundException("No user found with userid" + userid);
		}
		
		else if(userinfo != null){
			System.out.println("member info: " + userinfo.get(0).get("USER_ID") + "/" + userinfo.get(0).get("USER_PSWD") + "/" + userinfo.get(0).get("ROLE"));
			System.out.println("password encode: " + passwordEncoder.encodePassword(userinfo.get(0).get("USER_PSWD").toString(), null));
			
			Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();

		    roles.add(new SimpleGrantedAuthority(userinfo.get(0).get("ROLE").toString()));
		    
		    user = new User(userinfo.get(0).get("USER_ID").toString(), passwordEncoder.encodePassword(userinfo.get(0).get("USER_PSWD").toString() , null), roles);

			System.out.println("Auth Checking Success...");
			
			//user를 반환하면 form에서 처리한 값하고 비교를 하게 된다.(Spring Security 인증사용)//
			System.out.println("**********************************************");
			
			return user;
		}
		
		return user;
	}
}
