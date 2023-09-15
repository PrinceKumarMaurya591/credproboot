package com.au.credpro.report.controller;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.au.credpro.report.entity.QueryList;
import com.au.credpro.report.entity.User;
import com.au.credpro.report.service.QueryListService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/credpro/api/querylists")
public class QueryController {

	@Autowired
	QueryListService queryListService;

	@PostMapping("/addQuery")
	public ResponseEntity<Object> addQueryList(@RequestBody QueryList queryList) {
		  try {
	            QueryList savedQueryList = queryListService.saveQueryList(queryList);
	            String successMessage = "Query with ID " + savedQueryList.getQueryId() + " added successfully.";
	            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
	        } catch (DataIntegrityViolationException e) {
	            String errorMessage = "Failed to add query: Query already exists with Query ID " + queryList.getQueryId();
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	        } catch (Exception e) {
	            String errorMessage = "Failed to add query: " + e.getMessage();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	        }
	    
	    
	}
	
	

//	@GetMapping("/getAllQueries")
//	public ResponseEntity<List<QueryList>> getAllQueryLists() {
//		List<QueryList> queryLists = queryListService.getAllQueryLists();
//		return new ResponseEntity<>(queryLists, HttpStatus.OK);
//	}

	 @GetMapping("/getAllQueries")
	    public ResponseEntity<Page<QueryList>> getAllQueryLists(Pageable pageable) {
	        Page<QueryList> queryLists = queryListService.getAllQueryLists(pageable);
	        return new ResponseEntity<>(queryLists, HttpStatus.OK);
	    }
	
	@GetMapping("/{queryId}")
	public ResponseEntity<?> getQueryListById(@PathVariable Long queryId) {
		QueryList queryList = queryListService.getQueryListById(queryId);
        if (queryList != null) {
            return ResponseEntity.ok(queryList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Query with queryId " + queryId + " does not exist.");
        }
    
	}

	@GetMapping("/queries/{userId}")
	public ResponseEntity<Set<QueryList>> getAllQueriesForUser(@PathVariable Long userId) {
		Set<QueryList> queries = queryListService.getAllQueriesAssignToUser(userId);
		return ResponseEntity.ok(queries);
	}

	@PutMapping("/updateQuery")
	public ResponseEntity<String> updateQueryDetails(

			@RequestBody QueryList queryList) {
	    try {
            QueryList updatedQuery = queryListService.updateQuery(queryList);
            if (updatedQuery == null) {
                // Query with the given queryId does not exist
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Query with queryId " + queryList.getQueryId() + " does not exist.");
            }
            return ResponseEntity.ok("Query updated successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Query not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
    }
}
