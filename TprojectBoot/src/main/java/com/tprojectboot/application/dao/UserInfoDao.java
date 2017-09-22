package com.tprojectboot.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tprojectboot.application.model.UserInfo;

//해당 Model에 맞는 Dao가 필요. 해당 Dao에서는 관련 테이블에 대한 CRUD작업을 하는 메소드 생성//
//XML작업 필요없이 효과적으로 쿼리맵핑 및 JDBC로직 구성//
public interface UserInfoDao extends CrudRepository<UserInfo, String>{
	List<UserInfo> findAll();
	
	// Custom Query - Use @Query. @Query의 from절에는 Entity로 지정된 클래스명.//
	@Query(nativeQuery = true, value=
			"select c.user_id from userinfo c where c.user_id = :user_id"
	)
	List<UserInfo> findByUserEmail(@Param("user_id") String user_id);
	
	@Query(nativeQuery = true, value=
			"select c.user_id from userinfo c where c.user_email = :user_email AND c.user_id = :user_id"
	)
	List<UserInfo> findByUserPhoneNumber(@Param("user_email") String user_email, @Param("user_id") String user_id);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value=
			"delete from userinfo c where c.user_id = :user_id"
	)
	int deleteUserById(@Param("user_id") String user_id);
}
