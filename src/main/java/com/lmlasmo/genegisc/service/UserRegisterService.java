package com.lmlasmo.genegisc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmlasmo.genegisc.dto.UserDTO;
import com.lmlasmo.genegisc.model.User;
import com.lmlasmo.genegisc.repository.UserRepository;

@Service
@Transactional
public class UserRegisterService {
		
	public UserRepository repository;	
	
	@Autowired
	public UserRegisterService(UserRepository repository) {
		this.repository = repository;
	}
	
	public Optional<User> alterProfileName(User user, String profileName) {
		
		Optional<User> userOp = Optional.ofNullable(null);
		
		if(!user.getProfileName().equals(profileName)) {
			
			user.setProfileName(profileName);
			
			userOp = Optional.ofNullable(repository.save(user));
			
			if(userOp.isPresent()) {
				
				if(!userOp.get().getProfileName().equals(profileName)) {					
					userOp = Optional.ofNullable(null);
				}
				
			}						
			
		}
		
		return userOp;
		
	}
	
	public Optional<User> alterPassword(User user, String password){
		
		Optional<User> userOp = Optional.ofNullable(null);
		
		if(!user.getPassword().equals(password)) {
			
			user.setPassword(password);
			
			userOp = Optional.ofNullable(repository.save(user));
			
			if(userOp.isPresent()) {
				
				if(userOp.get().getPassword().equals(password)) {
					userOp = Optional.ofNullable(null);
				}
				
			}
			
		}
		
		return userOp;
		
	}
	
	public Optional<User> save(UserDTO dto, String password) {
		
		User user = new User();
		
		user.setUsername(dto.getUsername());
		user.setProfileName(dto.getProfileName());
		user.setBirthDate(dto.getBirthDate());		
		
		return Optional.ofNullable(repository.save(user));
		
	}
	
	public boolean delete(User user) {		
		
		repository.delete(user);
		
		return repository.findById(user.getId()).isEmpty();
		
	}
	
}
