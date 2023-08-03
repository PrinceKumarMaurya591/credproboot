package com.au.credpro.report.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String username;
	private String password;
    private Long adminAuId;
    private Long adminId;
    
    
    
	public Long getAdminId() {
		return adminId;
	}


	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}


	@Column(nullable = false, unique = true)
	private String AuEmail;
	
	@Column(nullable = false, unique = true)
	private Long userAuId;
	
	
    
public String getAuEmail() {
		return AuEmail;
	}


	public void setAuEmail(String auEmail) {
		AuEmail = auEmail;
	}


	public Long getUserAuId() {
		return userAuId;
	}


	public void setUserAuId(Long userAuId) {
		this.userAuId = userAuId;
	}


	//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", adminId=" + adminId
//				+ "]";
//	}
//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", adminAuId=" + adminAuId
//				+ ", AuEmail=" + AuEmail + ", userAuId=" + userAuId + "]";
//	}
	
	
	

	public Long getAdminAuId() {
		return adminAuId;
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", adminAuId="
				+ adminAuId + ", adminId=" + adminId + ", AuEmail=" + AuEmail + ", userAuId=" + userAuId + "]";
	}


	public void setAdminAuId(Long adminAuId) {
		this.adminAuId = adminAuId;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public User(Long userId, String username, String password, Long adminAuId, String auEmail, Long userAuId,
			Set<QueryList> queryList) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.adminAuId = adminAuId;
		this.AuEmail = auEmail;
		this.userAuId = userAuId;
		this.queryList = queryList;
	}


//	public User(Long userId, String username, String password,Set<QueryList> queryList) {
//		super();
//		this.userId = userId;
//		this.username = username;
//		this.password = password;
//		this.queryList=queryList;
//	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	
	 @JsonIgnore
	@ManyToMany
	private Set<QueryList> queryList;

	public Set<QueryList> getQueryList() {
		return queryList;
	}

	public void setQueryList(Set<QueryList> assignedQueries) {
		this.queryList = assignedQueries;
	}

	
	

}
