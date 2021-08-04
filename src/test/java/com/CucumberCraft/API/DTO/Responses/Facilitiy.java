package com.CucumberCraft.API.DTO.Responses;

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
public class Facilitiy {

	@JsonProperty("facilityId")
	private Integer facilityId;
	
	@JsonProperty("billingMarkName")
	private String billingMarkName;
	
	@JsonProperty("fsacNbr")
	private String fsacNbr;
	
	@JsonProperty("workCtrCode")
	private String workCtrCode;
	
	@JsonProperty("workCtrDescr")
	private String workCtrDescr;
	
	@JsonProperty("facilityType")
	private String facilityType;
	
	@JsonProperty("createdTs")
	private String createdTs;
	
	@JsonProperty("createUserName")
	private String createUserName;
	
	@JsonProperty("modifiedTs")
	private String modifiedTs;
	
	@JsonProperty("modifyUserName")
	private String modifyUserName;
	
	@JsonProperty("parentID")
	private Object parentID;
	
	@JsonProperty("stationName")
	private String stationName;
	
	@JsonProperty("splc")
	private String splc;

}
