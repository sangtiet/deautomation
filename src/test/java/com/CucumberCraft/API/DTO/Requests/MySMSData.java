package com.CucumberCraft.API.DTO.Requests;

import com.CucumberCraft.API.DTO.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MySMSData implements Request {
	
	private String address;
	private String offset;
	private String limit;
	private String authToken;
	private String apiKey;
}
