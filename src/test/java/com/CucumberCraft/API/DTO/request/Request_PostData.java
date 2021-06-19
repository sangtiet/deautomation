package com.CucumberCraft.API.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request_PostData implements Request {
	
	private String userId;
	private String title;
	private String body;
}
