package com.CucumberCraft.StepDefinitions;

import java.util.Map;

import com.CucumberCraft.API.Base.PostsService;
import com.CucumberCraft.API.DTO.Request;
import com.CucumberCraft.API.DTO.Request_CreateNewPost;
import com.CucumberCraft.API.DTO.Request_PostData;
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

	private String generateDataParam(Map<String, String> dataTable, String dtoClassName) {
		switch(dtoClassName){    
		case "PostData":    
			Request_PostData postData = Request.createDTOObjectByDataTable(Request_PostData.class,
					dataTable);  
			return postData.convertDTOObjectToJSONString();
		}
		return null;
	}

	@Given("^I get post by the ID \"([^\"]*)\"$")
	public void IgetpostbytheID(String id) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Response response = postsService.requestGetPostsById(id);
		reponsePosts = ObjectMapperUtils.dtoClassMapper(response.getBody().asString(), Response_Posts.class);
		System.out.println("UserID = " + reponsePosts.getUserId());
		System.out.println("Title = " + reponsePosts.getTitle());
		System.out.println("Body = " + reponsePosts.getBody());
	}
	
	@Given("^I create a new post with data$")
    public void Icreateanewpostwithdata(Map<String, String> dataTable)
            throws IllegalArgumentException {
		Request_CreateNewPost createNewPostRequest = initializeCreateNewPostDTO(dataTable);
        Response response = postsService.requestCreateNewPost(createNewPostRequest);
        reponsePosts = ObjectMapperUtils.dtoClassMapper(response.getBody().asString(),
        		Response_Posts.class);
        //this.scenarioContext.setContext(VariableContext.CREATE_ORDER_RESPONSE, topUpCreateOrderResponse);
        System.out.println("ID = " + reponsePosts.getId());
        System.out.println("UserID = " + reponsePosts.getUserId());
		System.out.println("Title = " + reponsePosts.getTitle());
		System.out.println("Body = " + reponsePosts.getBody());
    }

	private Request_CreateNewPost initializeCreateNewPostDTO(Map<String, String> dataTable) {
		Request_CreateNewPost createNewPostRequest = new Request_CreateNewPost(generateDataParam(dataTable,"PostData"));
		return createNewPostRequest;
	}
}
