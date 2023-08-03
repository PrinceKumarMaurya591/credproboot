package com.au.credpro.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false, unique = true)
	private String AuEmail;
	
	@Column(nullable = false, unique = true)
	private Long adminAuId;
	

	public String getAuEmail() {
		return AuEmail;
	}

	public void setAuEmail(String auEmail) {
		AuEmail = auEmail;
	}

	public Long getAdminAuId() {
		return adminAuId;
	}

	public void setAdminAuId(Long adminAuId) {
		this.adminAuId = adminAuId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Admin(Long id, String username, String password, String auEmail, Long adminAuId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		AuEmail = auEmail;
		this.adminAuId = adminAuId;
	}



	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", AuEmail=" + AuEmail
				+ ", adminAuId=" + adminAuId + "]";
	}

//	@Override
//	public String toString() {
//		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", UserId=" + adminAuId + "]";
//	}
	
	

	
	
	
	
}
