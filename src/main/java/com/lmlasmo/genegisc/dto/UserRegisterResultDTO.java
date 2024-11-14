package com.lmlasmo.genegisc.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisterResultDTO {

	@JsonProperty("sucess")
	public boolean sucess;
	
	@JsonProperty("user")
	public UserDTO userDto;
	
	@JsonProperty("erro_cause")
	public List<UserRegisterErroCause> erro;
	
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

	public List<UserRegisterErroCause> getErro() {
		return erro;
	}

	public void setErro(List<UserRegisterErroCause> erro) {
		this.erro = erro;
	}	
	
}
