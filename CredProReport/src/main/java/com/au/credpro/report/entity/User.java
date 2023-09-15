package com.au.credpro.report.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;



import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users") 
public class User {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long userId; 
	// @Column(nullable = false)
	private String username; 
	// @Column(nullable = false) 
	private String password; 
	// @Column(nullable = false, unique = true) 
	private String auEmail; 
	// @Column(nullable = false)
	private Boolean isAdmin; 
	// @Column(nullable = false) 
	private Boolean isActiveUser; 
	// @Column(nullable = false, unique = true)
	private Long userAuId;

	public String getAuEmail() {
		return auEmail;
	}

	public void setAuEmail(String auEmail) {
		this.auEmail = auEmail;
	}

	public Long getUserAuId() {
		return userAuId;
	}

	public void setUserAuId(Long userAuId) {
		this.userAuId = userAuId;
	}

	public User() { 
		super(); 
	// TODO Auto-generated constructor stub 
		}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", AuEmail=" + auEmail
				+ ", isAdmin=" + isAdmin + ", isActiveUser=" + isActiveUser + ", userAuId=" + userAuId + ", queryList="
				+ queryList + "]";
	}

	public User(Long userId, String username, String password, String auEmail, Boolean isAdmin, Boolean isActiveUser,
			Long userAuId, Set<QueryList> queryList) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.auEmail = auEmail;
		this.isAdmin = isAdmin;
		this.isActiveUser = isActiveUser;
		this.userAuId = userAuId;
		this.queryList = queryList;
	}

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

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getIsActiveUser() {
		return isActiveUser;
	}

	public void setIsActiveUser(Boolean isActiveUser) {
		this.isActiveUser = isActiveUser;
	}

	@JsonIgnore
	@ManyToMany
	private Set<QueryList> queryList;

	public Set<QueryList> getQueryList() {
		return queryList;
	}

	public void setQueryList(Set<QueryList> assignedQueries) { this.queryList = assignedQueries;
	
	}

	
	
	
	
	

	
	
}
