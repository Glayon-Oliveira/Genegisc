package com.lmlasmo.genegisc.dto;

public class UserRegisterResult {

	public boolean sucess;
	
	public UserDTO userDto;
	
	public UserRegisterResult() {}

	public boolean isSucess() {
		return sucess;
	}

	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}

	public UserDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}	
	
}
