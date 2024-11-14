package com.lmlasmo.genegisc.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmlasmo.genegisc.dto.UserDTO;
import com.lmlasmo.genegisc.dto.UserRegisterErro;
import com.lmlasmo.genegisc.dto.UserRegisterResultDTO;
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
	
	public UserRegisterResultDTO alterProfileName(User user, String profileName) {		
		
		UserRegisterResultDTO resultDto = new UserRegisterResultDTO();		
		
		resultDto.setSucess(false);					
		resultDto.setUserDto(null);
		resultDto.getErro().add(UserRegisterErro.ALTER_PROFILE);		
		
		if(user == null || profileName == null) {
			return resultDto;
		}
		
		if(!user.getProfileName().equals(profileName)) {
			
			user.setProfileName(profileName);
			
			Optional<User> userOp = Optional.ofNullable(repository.save(user));
			
			if(userOp.isPresent()) {
				
				if(!userOp.get().getProfileName().equals(profileName)) {					
					userOp = Optional.ofNullable(null);
				}else {
					Optional<UserDTO> dtoOp = UserService.toDTO(userOp.get());
					
					if(dtoOp.isPresent()) {
						resultDto.setSucess(true);
						resultDto.setUserDto(dtoOp.get());					
						resultDto.getErro().removeIf(e -> e.equals(UserRegisterErro.ALTER_PROFILE));
					}
					
				}
				
			}					
			
		}
		
		return resultDto;
		
	}
	
	public UserRegisterResultDTO alterProfileName(String username, String profileName) {
		
		Optional<User> userOp = repository.findByUsername(username);
		
		if(userOp.isPresent()) {
			
			return alterProfileName(userOp.get(), profileName);
			
		}else {
			
			UserRegisterResultDTO resultDto = new UserRegisterResultDTO();
			
			resultDto.setSucess(false);
			resultDto.setUserDto(null);
			resultDto.getErro().add(UserRegisterErro.ALTER_PROFILE);
			
			return resultDto;
			
		}
		
	}
	
	public UserRegisterResultDTO alterPassword(User user, String password){
				
		UserRegisterResultDTO resultDto = new UserRegisterResultDTO();		
		
		resultDto.setSucess(false);
		resultDto.getErro().add(UserRegisterErro.ALTER_PASSWORD);
		resultDto.setUserDto(null);
		
		if(user == null || password == null) {
			return resultDto;
		}
		
		if(!user.getPassword().equals(password)) {
			
			user.setPassword(password);
			
			Optional<User> userOp = Optional.ofNullable(repository.save(user));
			
			if(userOp.isPresent()) {
				
				if(userOp.get().getPassword().equals(password)) {
					userOp = Optional.ofNullable(null);
				}else {
					
					Optional<UserDTO> dtoOp = UserService.toDTO(userOp.get());
					
					if(dtoOp.isPresent()) {
						
						resultDto.setSucess(true);
						resultDto.setUserDto(dtoOp.get());
						resultDto.getErro().removeIf(e -> e.equals(UserRegisterErro.ALTER_PASSWORD));						
						
					}
									
				}
				
			}
			
		}
		
		return resultDto;
		
	}
	
	public UserRegisterResultDTO alterPassword(String username, String password){
		
		Optional<User> userOp = repository.findByUsername(username);
		
		if(userOp.isPresent()) {
			
			return alterPassword(userOp.get(), password);
			
		}else {
			
			UserRegisterResultDTO resultDto = new UserRegisterResultDTO();
			
			resultDto.setSucess(false);
			resultDto.setUserDto(null);
			resultDto.getErro().add(UserRegisterErro.ALTER_PASSWORD);
			
			return resultDto;
			
		}
		
	}
	
	public UserRegisterResultDTO save(UserDTO dto, String password, String passwordConfirm) {
		
		UserRegisterResultDTO resultDto = new UserRegisterResultDTO();
		
		resultDto.setSucess(false);
		resultDto.setUserDto(null);
		
		if(dto == null || dto.getUsername() == null || password == null || passwordConfirm == null) {
			resultDto.getErro().add(UserRegisterErro.REGISTRATION_FAILED);
		}
		
		Optional<User> userOp = repository.findByUsername(dto.getUsername());
		
		if(!userOp.isEmpty()) {
			resultDto.getErro().add(UserRegisterErro.USERNAME_USE);
		}
		
		if(!password.equals(passwordConfirm)) {
			resultDto.getErro().add(UserRegisterErro.PASSWORD_CONFIRMED);
		}
		
		if(resultDto.getErro().size() == 0) {
			
			User user = new User();		
			
			user.setUsername(dto.getUsername());
			user.setProfileName(dto.getProfileName());
			user.setBirthDate(dto.getBirthDate());
			
			userOp = Optional.ofNullable(repository.save(user));
			
			if(userOp.isPresent()) {				
				
				Optional<UserDTO> dtoOp = UserService.toDTO(userOp.get());
				
				if(dtoOp.isPresent()) {
				
					resultDto.setSucess(true);
					resultDto.setUserDto(dtoOp.get());
					
				}
				
			}
			
			if(resultDto.isSucess()) {
				resultDto.getErro().add(UserRegisterErro.REGISTRATION_FAILED);
			}
			
		}		
		
		return resultDto;
		
	}
	
	public UserRegisterResultDTO delete(User user) {		
		
		UserRegisterResultDTO resultDto = new UserRegisterResultDTO();		
		resultDto.setUserDto(null);
		
		repository.delete(user);
		
		if(repository.findById(user.getId()).isEmpty()) {
			
			resultDto.setSucess(true);			
			
		}else {
			
			resultDto.setSucess(false);
			resultDto.getErro().add(UserRegisterErro.DELETION_FAILED);			
			
		}
		
		return resultDto;
		
	}
	
	public UserRegisterResultDTO delete(String username) {		
		
		Optional<User> userOp = repository.findByUsername(username);
		
		if(userOp.isPresent()) {
			
			return delete(userOp.get());
			
		}else {
			
			UserRegisterResultDTO resultDto = new UserRegisterResultDTO();
			
			resultDto.setSucess(false);
			resultDto.setUserDto(null);
			resultDto.getErro().add(UserRegisterErro.DELETION_FAILED);
			
			return resultDto;
			
		}
		
	}
	
}
