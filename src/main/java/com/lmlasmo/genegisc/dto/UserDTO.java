package com.lmlasmo.genegisc.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmlasmo.genegisc.model.User;

public class UserDTO {

	@JsonProperty("username")
	private String username;
	
	@JsonProperty("profile_name")
	private String profileName;
	
	@JsonProperty("birth_date")
	private LocalDate birthDate;
	
	public UserDTO() {}
	
	public UserDTO(User user) {
		
		this.username = user.getUsername();
		this.profileName = user.getProfileName();
		this.birthDate = user.getBirthDate();
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}	
	
}
