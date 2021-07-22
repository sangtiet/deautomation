package com.CucumberCraft.API.DTO.Requests;

import com.CucumberCraft.API.DTO.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pkl implements Request {
	
	private String grantType;
	private String clientId;
	private String clientSecret;
}
