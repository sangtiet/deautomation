package com.CucumberCraft.API.DTO;

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
public class Response_Posts {
    @JsonProperty("userid")
    private int userId;

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private int title;

    @JsonProperty("body")
    private String body;

}
