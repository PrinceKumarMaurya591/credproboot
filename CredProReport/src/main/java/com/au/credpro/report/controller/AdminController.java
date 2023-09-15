package com.au.credpro.report.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.au.credpro.report.entity.QueryList;
import com.au.credpro.report.entity.User;
import com.au.credpro.report.exception.NotFoundException;
import com.au.credpro.report.exception.UserNotFoundException;

import com.au.credpro.report.repository.QueryListRepository;
import com.au.credpro.report.repository.UserRepository;
import com.au.credpro.report.service.AdminService;
import com.au.credpro.report.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/credpro/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	private UserService userService;


	
	@Autowired
	private QueryListRepository queryListRepository;
	
	@Autowired
	private UserRepository userRepository;

	
//	@PostMapping("/{adminAuId}/add-user")
//	public ResponseEntity<?> addUserByAdminAuId(@PathVariable Long adminAuId, @RequestBody User user) {
//	    try {
//	        User savedUser = adminService.addUserByAdminAuId(adminAuId, user);
//	        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//	    } catch (DataIntegrityViolationException e) {
//	        // Handle the duplicate email exception
//	        String errorMessage = "User with the same email already exists.";
//	        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
//	    } catch (Exception e) {
//	        // Handle other exceptions
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add user.");
//	    }
//	}
	
	
	@PostMapping("/{adminAuId}/add-user")
	public ResponseEntity<?> addUserByAdminAuId( @RequestBody User user) {
	    try {
	        User savedUser = adminService.addUser( user);
	        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	    } catch (DataIntegrityViolationException e) {
	        // Handle the duplicate email exception
	        String errorMessage = "User with the same email already exists.";
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
	    } catch (Exception e) {
	        // Handle other exceptions
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add user.");
	    }
	}
	

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

//	@GetMapping("/getAllUsers")
//	public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
//        Page<User> users = userService.getAllUsers(pageable);
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
	
//	@PostMapping("/{adminAuId}/assign-queries")
//	public ResponseEntity<String> assignQueriesToUserByAuId(@PathVariable Long adminAuId,@RequestBody QueryIdsWrapper queryIdsWrapper) {
//	    Long userAuId = queryIdsWrapper.getUserAuId();
//	    Set<Long> queryIds = queryIdsWrapper.getQueryIds();
//
//	 // Check if the admin with adminAuId exists in the database
//	    Admin admin = adminRepository.findByAdminAuId(adminAuId);
//	    if (admin == null) {
//	        // Return an error response if the admin is not found
//	    	String errorMessage = "Admin with adminAuId " + adminAuId + " not found.";
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//	    }
//	    
//	    // Find the user by userAuId
//	    User user = userService.getUserByUserAuId(userAuId);
//	    if (user == null) {
//	        throw new NotFoundException("User not found with userAuId: " + userAuId);
//	    }
//
//	    // Check if all queryIds exist in the database
//	    List<QueryList> queries = queryListRepository.findAllById(queryIds);
//	    if (queries.size() != queryIds.size()) {
//	        throw new NotFoundException("One or more queryIds not found in the database.");
//	    }
//
//	    // If all queryIds exist, assign them to the user
//	    user.setQueryList(new HashSet(queries));
//	    User updatedUser = userService.addUser(user);
//	    return ResponseEntity.ok("Queries assigned to the user successfully.");
//	}
	
	
	
	@PostMapping("/{adminAuId}/assign-queries")
	public ResponseEntity<String> assignQueriesToUser( @PathVariable Long adminAuId, @RequestBody QueryIdsWrapper queryIdsWrapper) { 
	    System.out.println("hello-------------------");
	    System.out.println("AdminAuId------"+adminAuId);
	    System.out.println("queryIds---------"+queryIdsWrapper.getQueryIds());
		Long userAuId = queryIdsWrapper.getUserAuId();
	    Set<Long> queryIds = queryIdsWrapper.getQueryIds();
	    // Find the user by userAuId
	    User user = userService.getUserByUserAuId(userAuId);
	    if (user == null) {
	        throw new NotFoundException("User not found with userAuId: " + userAuId);
	    }
	    // Check if all queryIds exist in the database
	    List<QueryList> queries = queryListRepository.findAllById(queryIds);
	    if (queries.size() != queryIds.size()) {
	        throw new NotFoundException("One or more queryIds not found in the database.");
	    }
	    // If all queryIds exist, assign them to the user
	    user.setQueryList(new HashSet(queries));
	    User updatedUser = userService.addUser(user);
	    return ResponseEntity.ok("Queries assigned to the user successfully.");
	    

		}
	
	
	@PutMapping("/{userAuId}")
    public User updateUser(@PathVariable Long userAuId, @RequestBody User updatedUser) {
        return adminService.updateUser(userAuId, updatedUser);
    }
	
	
	

	@DeleteMapping("/{userAuId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userAuId) {
	    try {
	        // Call the service method to delete the user by userAuId
	        adminService.deleteUserByUserAuId(userAuId);
	        return ResponseEntity.ok("User deleted successfully.");
	    } catch (NotFoundException e) {
	        // User not found with the given userAuId
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with userAuId " + userAuId + " not found.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user.");
	    }
	}

}
