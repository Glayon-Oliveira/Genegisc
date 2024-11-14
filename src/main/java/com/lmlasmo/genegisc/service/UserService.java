package com.lmlasmo.genegisc.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.lmlasmo.genegisc.dto.UserDTO;
import com.lmlasmo.genegisc.model.User;
import com.lmlasmo.genegisc.repository.UserRepository;

public class UserService extends UserRegisterService{
	
	@Autowired
	public UserService(UserRepository repository) {
		super(repository);
	}
	
	public List<User> findAll(){
		return this.repository.findAll();
	}
	
	public Optional<User> findByUsername(String username){
		return this.repository.findByUsername(username);
	}
	
	public List<User> findByProfileNameContaining(String profileName){
		return this.repository.findByProfileNameContaining(profileName);
	}
	
	public List<User> findByBirthYear(int year){
		return this.repository.findByBirthYear(year);
	}
	
	public List<User> findByBirthMount(int mount){
		return this.repository.findByBirthMount(mount);
	}
	
	public List<User> findByBirthDateStartingFromYear(int year){
		return this.repository.findByBirthDateStartingFromYear(year);
	}
	
	public static Optional<UserDTO> toDTO(User user) {
		
		Optional<UserDTO> dtoOp = Optional.ofNullable(null);
		
		if(verifyToDTO(user)) {
			
			dtoOp = Optional.ofNullable(new UserDTO(user));			
			
		}
		
		return dtoOp;
		
	}
	
	public static List<UserDTO> toDTO(List<User> users) {
		
		List<UserDTO> dtoList = users.stream()
									 .filter(u -> verifyToDTO(u))
									 .map(u -> toDTO(u))
									 .filter(u -> u.isPresent())
									 .map(u -> u.get())
									 .collect(Collectors.toList());
		
		return dtoList;
		
	}
	
	private static boolean verifyToDTO(User user) {
		
		boolean cond = (user.getUsername() != null && user.getProfileName() != null && user.getPassword() != null);
		
		return cond;
		
	}
	

}
