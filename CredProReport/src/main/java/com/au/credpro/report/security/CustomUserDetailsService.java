//package com.au.credpro.report.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.au.credpro.report.entity.User;
//import com.au.credpro.report.repository.UserRepository;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//	private UserRepository userRepository;
//    
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		 Long userAuId = Long.parseLong(username);
//		return (UserDetails) userRepository.findByUserAuId(userAuId);
////                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//	}
//    
//    
//}