package com.CucumberCraft.API.DTO.Requests;

import com.CucumberCraft.API.DTO.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class MySMS implements Request {
	
	private String data;
}
