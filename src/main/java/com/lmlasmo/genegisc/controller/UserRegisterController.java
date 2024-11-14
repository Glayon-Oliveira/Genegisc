package com.lmlasmo.genegisc.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lmlasmo.genegisc.dto.UserDTO;
import com.lmlasmo.genegisc.dto.UserRegisterResultDTO;
import com.lmlasmo.genegisc.service.UserService;

@RestController
public class UserRegisterController {
	
	private UserService userService;
	
	@Autowired
	public UserRegisterController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public UserRegisterResultDTO postUser(@RequestParam String username, 
						   @RequestParam(name = "profile_name") String profileName, 
						   @RequestParam String password, 
						   @RequestParam(name = "password_confirm") String passwordConfirm,
						   @RequestParam(name = "birth_date") LocalDate birthDate) {
		
		
		UserDTO dto = new UserDTO();
		
		dto.setUsername(username);
		dto.setProfileName(profileName);
		dto.setBirthDate(birthDate);
		
		return userService.save(dto, password, passwordConfirm);		
				
	}
	
	@PutMapping("/{username}/alter/profilename")
	public UserRegisterResultDTO putAlterProfileName(@PathVariable String username, @RequestParam("profile_name") String profileName) {
						
		return userService.alterProfileName(username, profileName);		
		
	}
	
	@PutMapping("/{username}/alter/password")
	public UserRegisterResultDTO putAlterPassword(String username, String password, String passwordConfirm) {
		
		return userService.alterPassword(username, password, passwordConfirm);
		
	}
	
}
