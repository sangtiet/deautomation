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
public class MySMSMessage {
    
	@JsonProperty("errorCode")
    private int errorCode;
	
	@JsonProperty("messages")
    private MessageDataObject[] messages;

}
