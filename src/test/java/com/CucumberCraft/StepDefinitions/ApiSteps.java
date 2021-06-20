package com.CucumberCraft.StepDefinitions;

import java.util.Map;

import org.testng.Assert;

import com.CucumberCraft.API.Base.*;
import com.CucumberCraft.API.DTO.Request;
import com.CucumberCraft.API.DTO.Requests.*;
import com.CucumberCraft.API.DTO.Responses.*;
import com.CucumberCraft.SupportLibraries.Helper;
import com.CucumberCraft.SupportLibraries.ObjectMapperUtils;
import com.CucumberCraft.SupportLibraries.ScenarioContext;
import com.CucumberCraft.SupportLibraries.TestController;

import cucumber.api.java.en.Given;
import io.restassured.response.Response;

public class ApiSteps extends SharedContextSteps {
	private Helper helper = TestController.getHelper();
	private String CPS_SERVICE_URL = helper.getConfig("mysms.domain");
	PostsService postsService;
	Posts reponsePosts;
	MySMSService mySMSService;
	MySMSMessage reponseMySMS;

	public ApiSteps(ScenarioContext scenarioContext) {
		super(scenarioContext);
		postsService = new PostsService(scenarioContext, CPS_SERVICE_URL);
		mySMSService = new MySMSService(scenarioContext, CPS_SERVICE_URL);
		// TODO Auto-generated constructor stub
	}

	private String generateDataParam(Map<String, String> dataTable, String dtoClassName) {
		switch (dtoClassName) {
		case "PostData":
			PostData postData = Request.createDTOObjectByDataTable(PostData.class, dataTable);
			return postData.convertDTOObjectToJSONString();
		case "MySMS":
			MySMSData mySMSData = Request.createDTOObjectByDataTable(MySMSData.class, dataTable);
			return mySMSData.convertDTOObjectToJSONString();
		}
		return null;
	}

	@Given("^I get post by the ID \"([^\"]*)\"$")
	public void IgetpostbytheID(String id) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Response response = postsService.requestGetPostsById(id);
		reponsePosts = ObjectMapperUtils.dtoClassMapper(response.getBody().asString(), Posts.class);
		System.out.println("UserID = " + reponsePosts.getUserId());
		System.out.println("Title = " + reponsePosts.getTitle());
		System.out.println("Body = " + reponsePosts.getBody());
	}

	@Given("^I create a new post with data$")
	public void Icreateanewpostwithdata(Map<String, String> dataTable) throws IllegalArgumentException {
		CreateNewPost createNewPostRequest = initializeCreateNewPostDTO(dataTable);
		Response response = postsService.requestCreateNewPost(createNewPostRequest);
		reponsePosts = ObjectMapperUtils.dtoClassMapper(response.getBody().asString(), Posts.class);

		System.out.println("ID = " + reponsePosts.getId());
		System.out.println("UserID = " + reponsePosts.getUserId());
		System.out.println("Title = " + reponsePosts.getTitle());
		System.out.println("Body = " + reponsePosts.getBody());
	}

	@Given("^I create a MySMS request with data$")
	public void IcreateaMySMSrequestwithdata(Map<String, String> dataTable) throws IllegalArgumentException {
		MySMS mySMSRequest = initializeMySMSDTO(dataTable);
		Response response = mySMSService.requestMySMS(mySMSRequest);
		System.out.println("Status code = " + response.getStatusCode());
		this.scenarioContext.setContext("MYSMS_RESPONSE_CODE", response.getStatusCode());
		reponseMySMS = ObjectMapperUtils.dtoClassMapper(response.getBody().asString(), MySMSMessage.class);
	}

	@Given("^The response code should be \"([^\"]*)\"$")
	public void the_response_code_should_be(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(this.scenarioContext.getContext("MYSMS_RESPONSE_CODE").toString(), arg1);
	}

	@Given("^The response message should be displayed$")
	public void the_response_message_should_be_displayed() throws Throwable {
		MessageDataObject[] ar = reponseMySMS.getMessages();

		for (int i = 0; i < ar.length; i++) {
			MessageDataObject x = ar[i];
			System.out.println("Message[" + i + "] = " + x.getMessage());
		}
	}

	/**
	 *
	 * DTO Initialization
	 *
	 */

	private CreateNewPost initializeCreateNewPostDTO(Map<String, String> dataTable) {
		CreateNewPost createNewPostRequest = new CreateNewPost(generateDataParam(dataTable, "PostData"));
		return createNewPostRequest;
	}

	private MySMS initializeMySMSDTO(Map<String, String> dataTable) {
		MySMS mySMSRequest = new MySMS(dataTable.get("address"), Integer.parseInt(dataTable.get("offset")),
				Integer.parseInt(dataTable.get("limit")), dataTable.get("authToken"), dataTable.get("apiKey"));
		return mySMSRequest;
	}
}