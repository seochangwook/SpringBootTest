package com.tprojectboot.application.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity : 데이터베이스에 저장하기 위해서 유저가 정의한 클래스. RDBMS에서 Table의 정의
@Entity
/*@Table : 별도의 이름을 가진 데이터베이스 테이블과 매핑. 기본적으로 @Entity로 선언된 클래스의
이름과 매핑하지만 다를 경우 @Table을 이용*/
@Table(name="userinfo") //Table name//
public class UserInfo{
	@Id //Primary key//
	//DB Column.  생략을 할 시 기본 멤버 변수명과 일치하는 데이터베이스 컬럼 매핑. 맴버변수랑 다르게 할 시 @Column사용//
	@Column(name="user_id") 
	private String user_id;
	
	@Column(name="user_password")
	private String user_password;
	
	@Column(name="user_email")
	private String user_email;
	
	@Column(name="user_phonenumber")
	private String user_phonenumber;
	
	@Column(name="user_manage")
	private int user_manage;
	
	@Column(name="user_reg_date")
	private Date user_reg_date;
	
	@Column(name="user_push_use")
	private int user_push_use;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_phonenumber() {
		return user_phonenumber;
	}

	public void setUser_phonenumber(String user_phonenumber) {
		this.user_phonenumber = user_phonenumber;
	}

	public int getUser_manage() {
		return user_manage;
	}

	public void setUser_manage(int user_manage) {
		this.user_manage = user_manage;
	}

	public Date getUser_reg_date() {
		return user_reg_date;
	}

	public void setUser_reg_date(Date user_reg_date) {
		this.user_reg_date = user_reg_date;
	}

	public int getUser_push_use() {
		return user_push_use;
	}

	public void setUser_push_use(int user_push_use) {
		this.user_push_use = user_push_use;
	}
	
}