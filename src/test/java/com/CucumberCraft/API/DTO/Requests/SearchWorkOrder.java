package com.CucumberCraft.API.DTO.Requests;

import java.util.List;

import com.CucumberCraft.API.DTO.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchWorkOrder implements Request {
		
	private String carIds;
	
	private String repairDate;
	
	private List<String> statuses;
	
	private Boolean limitToUser;
	
	private String fsac;
	
	private Boolean limitToCIM;
	
	private String splc;
	
	private String user;
}
