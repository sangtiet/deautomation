package com.CucumberCraft.StepDefinitions;

import java.util.Map;

import com.CucumberCraft.API.Base.PostsService;
import com.CucumberCraft.API.DTO.Request_GetPostsByID;
import com.CucumberCraft.API.DTO.Response_Posts;
import com.CucumberCraft.SupportLibraries.Helper;
import com.CucumberCraft.SupportLibraries.ObjectMapperUtils;
import com.CucumberCraft.SupportLibraries.ScenarioContext;
import com.CucumberCraft.SupportLibraries.TestController;

import cucumber.api.java.en.Given;
import io.restassured.response.Response;

public class ApiSteps extends SharedContextSteps {
	private Helper helper = TestController.getHelper();
	private String CPS_SERVICE_URL = helper.getConfig("demo.domain");
	PostsService postsService;
	Response_Posts reponsePosts;

	public ApiSteps(ScenarioContext scenarioContext) {
		super(scenarioContext);
		postsService = new PostsService(scenarioContext, CPS_SERVICE_URL);
		// TODO Auto-generated constructor stub
	}

	

//	private String generateDataParam(Map<String, String> dataTable) {
//		TopUpCreateOrderData createOrderData = Request.createDTOObjectByDataTable(TopUpCreateOrderData.class,
//				dataTable);
//		return createOrderData.convertDTOObjectToJSONString();
//	}
	
	@Given("^I get post by the ID \"([^\"]*)\"$")
	public void IgetpostbytheID(String id) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        Response response = postsService.requestGetPostsById(id);
        reponsePosts = ObjectMapperUtils.dtoClassMapper(response.getBody().asString(),
        		Response_Posts.class);
        System.out.println(reponsePosts);
	}
	
	private Request_GetPostsByID createGetPostsByIdDTO(String postID) {
		Request_GetPostsByID getPostsByID_Request = new Request_GetPostsByID(postID);
		return getPostsByID_Request;
	}
}
