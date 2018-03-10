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
	//@Column에 테이블 설계 시 사용되었던 제약조건을 추가할 수 있다.//
	@Column(name="user_id") 
	private String user_id;
	
	@Column(name="user_pswd")
	private String user_pswd;
	
	@Column(name="user_address")
	private String user_address;
	
	@Column(name="user_phonenumber")
	private String user_phonenumber;
	
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="mailpush_use")
	private int mailpush_use;
	
	@Column(name="user_no")
	private int user_no;
	
	@Column(name="user_age")
	private int user_age;
	
	@Column(name="user_image")
	private String user_image;
	
	@Column(name="role")
	private String role;

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getUser_age() {
		return user_age;
	}

	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}

	public String getUser_image() {
		return user_image;
	}

	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pswd() {
		return user_pswd;
	}

	public void setUser_pswd(String user_pswd) {
		this.user_pswd = user_pswd;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_phonenumber() {
		return user_phonenumber;
	}

	public void setUser_phonenumber(String user_phonenumber) {
		this.user_phonenumber = user_phonenumber;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getMailpush_use() {
		return mailpush_use;
	}

	public void setMailpush_use(int mailpush_use) {
		this.mailpush_use = mailpush_use;
	}
	
}