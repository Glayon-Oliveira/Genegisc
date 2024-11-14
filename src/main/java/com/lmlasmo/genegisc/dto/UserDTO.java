package com.lmlasmo.genegisc.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {

	@JsonProperty("username")
	private String username;
	
	@JsonProperty("profile_name")
	private String profileName;
	
	@JsonProperty("birth_date")
	private LocalDate birthDate;

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
