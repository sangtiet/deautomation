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
public class WorkOrder {

	@JsonProperty("repairHeaderKeyNbr")
	private String repairHeaderKeyNbr;
	
	@JsonProperty("carInitial")
	private String carInitial;
	
	@JsonProperty("carNbr")
	private String carNbr;
	
	@JsonProperty("equipmentId")
	private String equipmentId;
	
	@JsonProperty("repairDate")
	private String repairDate;
	
	@JsonProperty("splc")
	private String splc;
	
	@JsonProperty("totalAmt")
	private Integer totalAmt;
	
	@JsonProperty("workOrderId")
	private Integer workOrderId;
	
	@JsonProperty("reportingMarkName")
	private String reportingMarkName;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("loadEmptyInd")
	private String loadEmptyInd;
	
	@JsonProperty("carKind")
	private String carKind;
	
	@JsonProperty("lastModifiedByUser")
	private String lastModifiedByUser;
	
	@JsonProperty("workOrder")
	private Object workOrder;
	
	@JsonProperty("repairLine")
	private Object repairLine;
	
	@JsonProperty("billedTo")
	private Object billedTo;
	
	@JsonProperty("manuallyReviewedFlag")
	private String manuallyReviewedFlag;
	
	@JsonProperty("cushioningUnitComponent")
	private Boolean cushioningUnitComponent;
	
	@JsonProperty("lstJobCodes")
	private Object lstJobCodes;
	
	@JsonProperty("componentType")
	private Object componentType;
	
	@JsonProperty("invoiced")
	private Boolean invoiced;

}
