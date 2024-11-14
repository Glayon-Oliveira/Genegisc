package com.lmlasmo.genegisc.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSearchResultDTO {

	@JsonProperty("sucess")
	private boolean sucess = false;
	
	@JsonProperty("user")
	private List<UserDTO> user;
	
	public UserSearchResultDTO() {}

	public boolean isSucess() {
		return sucess;
	}

	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}

	public List<UserDTO> getUser() {
		return user;
	}

	public void setUser(List<UserDTO> user) {
		this.user = user;
	}
	
}
