package com.au.credpro.report.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity

public class QueryList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long queryId;

	
	
	@Column(nullable = false, unique = true)
	private String query;

	@Column(nullable = false)
	private String queryName;

	public QueryList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QueryList(Long queryId, String query, String queryName) {
		super();
		this.queryId = queryId;
		this.query = query;
		this.queryName = queryName;
	}

	public Long getQueryId() {
		return queryId;
	}

	public void setQueryId(Long queryId) {
		this.queryId = queryId;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	@Override
	public String toString() {
		return "QueryList [queryId=" + queryId + ", query=" + query + ", queryName=" + queryName + "]";
	}

//	@ManyToMany(mappedBy = "queryList", cascade = CascadeType.ALL)
//	private List<User> user;
//
//	public List<User> getUser() {
//		return user;
//	}
//
//	public void setUser(List<User> user) {
//		this.user = user;
//	}
	
	@ManyToMany(mappedBy = "queryList", cascade = CascadeType.ALL)
	private Set<User> user;

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

}

	
	
	
	


