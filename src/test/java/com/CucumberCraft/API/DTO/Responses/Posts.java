package com.CucumberCraft.API.DTO.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author hoangdm
 *
 */
@Getter
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Posts {
    
	@JsonProperty("id")
    private int id;
	
	@JsonProperty("title")
    private String title;

    @JsonProperty("body")
    private String body;
	
	@JsonProperty("userid")
    private int userId;

}
