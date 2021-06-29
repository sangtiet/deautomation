package com.CucumberCraft.API.DTO.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDataObject {
    
	@JsonProperty("messageId")
	private int messageId;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("incoming")
	private boolean incoming;
	
	@JsonProperty("read")
	private boolean read;
	
	@JsonProperty("locked")
	private boolean locked;
	
	@JsonProperty("origin")
	private int origin;
	
	@JsonProperty("status")
	private int status;
	
	@JsonProperty("dateSent")
	private long dateSent;
	
	@JsonProperty("dateStatus")
	private Object dateStatus;

}
