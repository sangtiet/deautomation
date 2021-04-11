package com.CucumberCraft.API.DTO;

import com.CucumberCraft.API.Base.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Request_GetPostsByID implements Request {
	
	@NonNull
	private String id;
	
	public Request_GetPostsByID(String postID) {
		// TODO Auto-generated constructor stub
		this.id = postID;
	}
}
