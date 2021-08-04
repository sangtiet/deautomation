package com.CucumberCraft.StepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.CucumberCraft.API.Base.CarLogixService;
import com.CucumberCraft.API.DTO.Requests.SearchWorkOrder;
import com.CucumberCraft.API.DTO.Responses.Facilitiy;
import com.CucumberCraft.API.DTO.Responses.WorkOrder;
import com.CucumberCraft.SupportLibraries.Helper;
import com.CucumberCraft.SupportLibraries.ObjectMapperUtils;
import com.CucumberCraft.SupportLibraries.ScenarioContext;
import com.CucumberCraft.SupportLibraries.TestController;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;

public class CarLogixSteps extends SharedContextSteps {
	private Helper helper = TestController.getHelper();

	private String CPS_SERVICE_URL = helper.getConfig("api.carLogix.baseUrl");
	CarLogixService carLogixService;

	Facilitiy[] facilities;

	SearchWorkOrder searchWorkOrderRequest;
	WorkOrder[] workOrders;

	public CarLogixSteps(ScenarioContext scenarioContext) {
		super(scenarioContext);
		carLogixService = new CarLogixService(scenarioContext, CPS_SERVICE_URL);
		// TODO Auto-generated constructor stub
	}

	@Given("^user creates Get Facility request with \"([^\"]*)\"$")
	public void user_creates_Get_Facility_request_with(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Response response = carLogixService.requestGetFacilityByCompany(arg1);
		this.scenarioContext.setContext("GET_FACILITY_RESPONSE_CODE", response.getStatusCode());
		facilities = ObjectMapperUtils.dtoClassMapper(response.getBody().asString(), Facilitiy[].class);

	}

	@Given("^user creates Search Work Order request with data$")
	public void userCreatesSearchWorkOrderRequestWithData(Map<String, String> dataTable)
			throws IllegalArgumentException {
		searchWorkOrderRequest = initializeSearchWorkOrderDTO(dataTable);
		Response response = carLogixService.requestSearchWorkOrder(searchWorkOrderRequest, dataTable.get("markCode"));
		this.scenarioContext.setContext("SEARCH_WORK_ORDER_RESPONSE_CODE", response.getStatusCode());
		workOrders = ObjectMapperUtils.dtoClassMapper(response.getBody().asString(), WorkOrder[].class);
	}

	@Then("^the Facilities list should contain \"([^\"]*)\" items$")
	public void the_Facilities_list_should_contain_items(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(facilities.length, Integer.parseInt(arg1));
	}

	@Then("^the Work Order list should contain \"([^\"]*)\" items$")
	public void the_Work_Order_list_should_contain_items(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(workOrders.length, Integer.parseInt(arg1));
	}
	
	private SearchWorkOrder initializeSearchWorkOrderDTO(Map<String, String> dataTable) {
		String carIds = (dataTable.get("carIds").equals("null")) ? "" : dataTable.get("carIds");
		String repairDate = (dataTable.get("repairDate").equals("null")) ? "" : dataTable.get("repairDate");
		String[] statusesArr = dataTable.get("statuses").split(",");
		List<String> statuses = new ArrayList<String>();

		for (int i = 0; i < statusesArr.length; i++) {
			statuses.add(statusesArr[i].trim());
		}

		Boolean limitToUser = false;
		String fsac = dataTable.get("fsac");
		Boolean limitToCIM = false;
		String splc = "";
		String user = dataTable.get("user");
		return new SearchWorkOrder(carIds, repairDate, statuses, limitToUser, fsac, limitToCIM, splc, user);
	}
}
