package com.lmlasmo.genegisc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmlasmo.genegisc.dto.UserDTO;
import com.lmlasmo.genegisc.dto.UserSearchResultDTO;
import com.lmlasmo.genegisc.model.User;
import com.lmlasmo.genegisc.repository.UserRepository;

@Service
public class UserService extends UserRegisterService{
	
	@Autowired
	public UserService(UserRepository repository) {
		super(repository);
	}
	
	public UserSearchResultDTO findAll(){
		
		List<User> userList = this.repository.findAll();
		
		List<UserDTO> dtoList = UserService.toDTO(userList);
		
		UserSearchResultDTO resultDto = new UserSearchResultDTO();
		
		if(dtoList.size() > 0) {
			
			resultDto.setSucess(true);
			resultDto.setUser(dtoList);
			
		}else {
			
			resultDto.setSucess(false);
			resultDto.setUser(null);
			
		}
		
		return resultDto;
		
	}
	
	public UserSearchResultDTO findByUsername(String username){

		Optional<User> userOp = this.repository.findByUsername(username);
		
		UserSearchResultDTO resultDto = new UserSearchResultDTO();
		
		resultDto.setSucess(false);
		
		if(userOp.isPresent()) {
			
			Optional<UserDTO> dtoOp = UserService.toDTO(userOp.get());
			
			if(dtoOp.isPresent()) {
				
				UserDTO dto = dtoOp.get();
				
				List<UserDTO> dtoList = new ArrayList<UserDTO>();
				dtoList.add(dto);
				
				resultDto.setSucess(true);
				resultDto.setUser(dtoList);						
				
			}
			
		}		
		
		return resultDto;	
		
	}
	
	public UserSearchResultDTO findByProfileNameContaining(String profileName){		
		
		List<User> userList = this.repository.findByProfileNameContaining(profileName);
		
		List<UserDTO> dtoList = UserService.toDTO(userList);
		
		UserSearchResultDTO resultDto = new UserSearchResultDTO();
		
		if(dtoList.size() > 0) {
			
			resultDto.setSucess(true);
			resultDto.setUser(dtoList);
			
		}else {
			
			resultDto.setSucess(false);
			resultDto.setUser(null);
			
		}
		
		return resultDto;		
		
	}
	
	public UserSearchResultDTO findByBirthYear(int year){		
		
		List<User> userList = this.repository.findByBirthYear(year);
		
		List<UserDTO> dtoList = UserService.toDTO(userList);
		
		UserSearchResultDTO resultDto = new UserSearchResultDTO();
		
		if(dtoList.size() > 0) {
			
			resultDto.setSucess(true);
			resultDto.setUser(dtoList);
			
		}else {
			
			resultDto.setSucess(false);
			resultDto.setUser(null);
			
		}
		
		return resultDto;	
		
	}
	
	public UserSearchResultDTO findByBirthMount(int mount){		
		
		List<User> userList = this.repository.findByBirthMount(mount);
		
		List<UserDTO> dtoList = UserService.toDTO(userList);
		
		UserSearchResultDTO resultDto = new UserSearchResultDTO();
		
		if(dtoList.size() > 0) {
			
			resultDto.setSucess(true);
			resultDto.setUser(dtoList);
			
		}else {
			
			resultDto.setSucess(false);
			resultDto.setUser(null);
			
		}
		
		return resultDto;	
		
	}
	
	public UserSearchResultDTO findByBirthDateStartingFromYear(int year){		
		
		List<User> userList = this.repository.findByBirthDateStartingFromYear(year);
		
		List<UserDTO> dtoList = UserService.toDTO(userList);
		
		UserSearchResultDTO resultDto = new UserSearchResultDTO();
		
		if(dtoList.size() > 0) {
			
			resultDto.setSucess(true);
			resultDto.setUser(dtoList);
			
		}else {
			
			resultDto.setSucess(false);
			resultDto.setUser(null);
			
		}
		
		return resultDto;	
		
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
