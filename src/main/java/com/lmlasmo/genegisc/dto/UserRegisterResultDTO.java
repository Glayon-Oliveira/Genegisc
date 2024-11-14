package com.lmlasmo.genegisc.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisterResultDTO {

	@JsonProperty("sucess")
	private boolean sucess;
	
	@JsonProperty("user")
	private UserDTO userDto;
	
	@JsonProperty("erro_cause")
	private List<UserRegisterErro> erro = new ArrayList<UserRegisterErro>();
	
	public UserRegisterResultDTO() {}

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

	public List<UserRegisterErro> getErro() {
		return erro;
	}

	public void setErro(List<UserRegisterErro> erro) {
		this.erro = erro;
	}	
	
}
