package com.au.credpro.report.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.credpro.report.entity.User;
import com.au.credpro.report.repository.UserRepository;

@Service
public class LoginService {

	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserByAuId(Long userAuId) {
		return userRepository.findByUserAuId(userAuId);
	}
}
