package com.lmlasmo.genegisc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lmlasmo.genegisc.dto.UserDTO;
import com.lmlasmo.genegisc.dto.UserSearchResultDTO;
import com.lmlasmo.genegisc.service.UserService;

@RestController
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/${username}")
	public UserSearchResultDTO getUserByUsername(@RequestParam String username) {
		
		return userService.findByUsername(username);
		
	}
	
	@GetMapping("/profiles")
	public UserSearchResultDTO findByProfileName(@RequestParam(name = "profile_name") String profilename) {
		
		return userService.findByProfileNameContaining(profilename);
		
	}

}
