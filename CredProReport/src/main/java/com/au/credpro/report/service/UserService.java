package com.au.credpro.report.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.au.credpro.report.entity.QueryList;
import com.au.credpro.report.entity.User;
import com.au.credpro.report.exception.UserNotFoundException;
import com.au.credpro.report.repository.QueryListRepository;
import com.au.credpro.report.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	QueryListRepository queryListRepository;

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
//	 public Page<User> getAllUsers(Pageable pageable) {
//	        return userRepository.findAll(pageable);
//	    }



	public void assignQueriesToUser(Long userId, List<Long> queryIds) {
//      User admin = userRepository.findById(adminId).orElseThrow(() -> new EntityNotFoundException("Admin not found"));
		User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

		// Check if the authenticated user is an admin
		// You can implement the logic to check if the user with adminId has admin
		// privileges

		List<QueryList> queries = queryListRepository.findAllById(queryIds);
		user.getQueryList().addAll(queries);
		userRepository.save(user);
	}
	 
	 
	 
//	 public void assignQueriesToUser(Long userId, List<Long> queryIds) {
//		 // User admin = userRepository.findById(adminId).orElseThrow(() -> new EntityNotFoundException("Admin not found")); 
//		 User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found")); // Check if the authenticated user is an admin // You can implement the logic to check if the user with adminId has admin // privileges 
//		 List<QueryList> queries = queryListRepository.findAllById(queryIds);
//		 user.getQueryList().addAll(queries); 
//		 userRepository.save(user);
//		 }
	 
	 

	public User getUserByUserAuId(Long userAuId) {
	   return userRepository.findByUserAuId(userAuId);
	 
	}


	public List<QueryList> getQueriesByUserId(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
		return new ArrayList<>(user.getQueryList());
	}

	

	  // Add a new method to retrieve the admin user based on adminAuId
	  
	    public User getUser(Long userId) {
	        return userRepository.findById(userId)
	                .orElseThrow(() -> new EntityNotFoundException("User not found with userId: " + userId));
	    }

	    public User getAdminUserByAdminAuId(Long adminAuId) {
	        return userRepository.findByUserAuIdAndIsAdmin(adminAuId, true)
	                .orElseThrow(() -> new EntityNotFoundException("Admin user not found with adminAuId: " + adminAuId));
	    }

		
	
}
