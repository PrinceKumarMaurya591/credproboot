package com.au.credpro.report.controller;

import java.util.Set;

public class QueryIdsWrapper {
	private Long userAuId;
	
    public Long getUserAuId() {
		return userAuId;
	}

	public void setUserAuId(Long userAuId) {
		this.userAuId = userAuId;
	}

	private Set<Long> queryIds;

    public Set<Long> getQueryIds() {
        return queryIds;
    }

    public void setQueryIds(Set<Long> queryIds) {
        this.queryIds = queryIds;
    }
}
