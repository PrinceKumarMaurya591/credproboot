package com.au.credpro.report.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.credpro.report.entity.QueryList;
import com.au.credpro.report.entity.User;
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
    
    
    public void assignQueriesToUser( Long userId, List<Long> queryIds) {
//      User admin = userRepository.findById(adminId).orElseThrow(() -> new EntityNotFoundException("Admin not found"));
      User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

      // Check if the authenticated user is an admin
      // You can implement the logic to check if the user with adminId has admin privileges

      List<QueryList> queries = queryListRepository.findAllById(queryIds);
      user.getQueryList().addAll(queries);
      userRepository.save(user);
  }
    
    
 
    
    
    public User getUserByUserAuId(Long userAuId) {
        return userRepository.findByUserAuId(userAuId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with userAuId: " + userAuId));
    }

    public List<QueryList> getQueriesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        return new ArrayList<>(user.getQueryList());
    }
    
    
    
    public Optional<User> isUser(Long userAuId) {
      //  return userRepository.findById(userAuId).orElse(null);
    	return userRepository.findByUserAuId(userAuId);
        
    }
	
}
