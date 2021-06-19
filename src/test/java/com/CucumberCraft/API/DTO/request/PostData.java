package com.CucumberCraft.API.DTO.request;

import com.CucumberCraft.API.DTO.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostData implements Request {
	
	private String userId;
	private String title;
	private String body;
}
