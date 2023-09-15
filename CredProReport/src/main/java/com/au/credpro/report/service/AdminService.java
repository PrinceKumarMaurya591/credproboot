package com.au.credpro.report.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.au.credpro.report.entity.QueryList;
import com.au.credpro.report.entity.User;
import com.au.credpro.report.exception.NotFoundException;

import com.au.credpro.report.repository.QueryListRepository;
import com.au.credpro.report.repository.UserRepository;

@Service
public class AdminService {

	

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QueryListRepository queryListRepository;

//	public User addUserByAdminAuId(Long adminAuId, User user) {
//		Admin admin = adminRepository.findByAdminAuId(adminAuId);
//		if (admin == null) {
//			throw new RuntimeException("Admin with adminAuId " + adminAuId + " not found.");
//		}
//
//		user.setAdminAuId(adminAuId);
//		user.setAdminId(admin.getId());
//		return userRepository.save(user);
//	}
	
	
	public User addUser(User user) {
		try {
			return userRepository.save(user);
			
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("same id exit ");
		}catch (Exception e) {
			throw new RuntimeException("failed to add user");
		}
	}

	public User assignQueriesToUser(Long userId, Set<Long> queryIds) {
		User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

		Set<QueryList> queries = queryListRepository.findAllById(queryIds).stream().collect(Collectors.toSet());

		user.setQueryList(queries);
		return userRepository.save(user);
	}

	public User assignQueriesToUserByAuId(Long userAuId, Set<Long> queryIds) {
		User user = userRepository.findByUserAuId(userAuId);
//				.orElseThrow(() -> new EntityNotFoundException("User not found"));

		Set<QueryList> queries = queryListRepository.findAllById(queryIds).stream().collect(Collectors.toSet());

		user.setQueryList(queries);
		return userRepository.save(user);
	}

	public void deleteUserById(Long userId) {
		userRepository.deleteById(userId);
	}
	
	
//	public User updateUser(Long userAuId,User updateUser) {
//	User exitingUser=userRepository.findByUserAuId(userAuId);
//	exitingUser.setAuEmail(updateUser.getAuEmail());
//	exitingUser.setIsAdmin(updateUser.getIsAdmin());
//	exitingUser.setPassword(updateUser.getPassword());
//	exitingUser.setUsername(updateUser.getUsername());
//	return userRepository.save(exitingUser);
//	}
	
	
	
	public User updateUser(Long userAuId, User updatedUser) {
        User existingUser = userRepository.findByUserAuId(userAuId);
               // .orElseThrow(() -> new EntityNotFoundException("User not found"));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setAuEmail(updatedUser.getAuEmail());
        existingUser.setIsActiveUser(updatedUser.getIsActiveUser());
        existingUser.setIsAdmin(updatedUser.getIsAdmin());

        return userRepository.save(existingUser);
    }
	
	
	
	
	
	public void deleteUserByUserAuId(Long userAuId) {
	    User user = userRepository.findByUserAuId(userAuId);
//	            .orElseThrow(() -> new NotFoundException("User not found with userAuId: " + userAuId));
	    userRepository.delete(user);
	}


	

}
