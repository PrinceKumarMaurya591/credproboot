package com.au.credpro.report.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.au.credpro.report.entity.User;
import com.au.credpro.report.service.AdminService;
import com.au.credpro.report.service.LoginService;
import com.au.credpro.report.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/credpro/checkuseroradmin")
public class LoginController {

	@Autowired
	AdminService adminService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;

	@GetMapping("/{userAuId}")
	public ResponseEntity<String> checkUserRole(@PathVariable Long userAuId) {
		System.out.println("hello-------");
	  User user= loginService.getUserByAuId(userAuId);

	    if (user != null) {
	    	String role=user.getIsAdmin()?"Admin":"RegularUser";
	        return ResponseEntity.ok(role);
	   
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credientials");
	    }
	}

}
