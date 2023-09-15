package com.au.credpro.report.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.au.credpro.report.entity.QueryList;
import com.au.credpro.report.entity.User;
import com.au.credpro.report.exception.NotFoundException;
import com.au.credpro.report.repository.QueryListRepository;
import com.au.credpro.report.repository.UserRepository;

@Service
public class QueryListService {

	@Autowired
	QueryListRepository queryListRepository;

	@Autowired
	UserRepository userRepository;
	
	 private static final Logger logger = LoggerFactory.getLogger(QueryListService.class);

	 public Page<QueryList> getAllQueryLists(Pageable pageable) {
	        return queryListRepository.findAll(pageable);
	    }

	public QueryList getQueryListById(Long queryId) {
		return queryListRepository.findById(queryId).orElse(null);
	}

	public QueryList saveQueryList(QueryList queryList) {
		return queryListRepository.save(queryList);
	}

	public Set<QueryList> getAllQueriesAssignToUser(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
		return user.getQueryList();
	}
	
	
	 

	@Transactional
	public QueryList updateQuery(QueryList queryList) {
	    Long queryId = queryList.getQueryId();
	    try { QueryList existingQuery = queryListRepository.findById(queryId)
	            .orElseThrow(() -> new NotFoundException("Query not found with ID: " + queryId));
		// Update the query details
		existingQuery.setQuery(queryList.getQuery());
		existingQuery.setQueryName(queryList.getQueryName());
		 logger.info("Query with ID {} updated successfully.", queryId);
		return queryListRepository.save(existingQuery);
	    } catch (NotFoundException e) {
            logger.warn("Query not found error occurred: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error occurred while updating query: {}", e.getMessage());
            throw e;
        }
	}

}
