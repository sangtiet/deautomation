package com.CucumberCraft.API.DTO.request;

import com.CucumberCraft.API.DTO.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request_MySMS implements Request {
	
	private String address;
	private int offset;
	private int limit;
	private String authToken;
	private String apiKey;
}
