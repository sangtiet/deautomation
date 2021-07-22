package com.CucumberCraft.API.Base;

import com.CucumberCraft.API.DTO.Requests.MySMS;
import com.CucumberCraft.API.DTO.Requests.Pkl;
import com.CucumberCraft.SupportLibraries.Helper;
import com.CucumberCraft.SupportLibraries.ScenarioContext;
import com.CucumberCraft.SupportLibraries.TestController;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PklService extends BaseService {

    private Helper helper = TestController.getHelper();
    private String CONTENT_TYPE = helper.getConfig("api.pkl.contentType");

    public PklService(ScenarioContext scenarioContex, String host) {
        super(scenarioContex, host);
        // TODO Auto-generated constructor stub
    }

    public PklService(ScenarioContext scenarioContext, String protocol, String host, int port) {
        super(scenarioContext, protocol, host, port);
    }

    public Response requestPKL(Pkl pklRequest) {
        RequestSpecification spec = this.customRequestBuilder(CONTENT_TYPE);
        return spec.formParam("grant_type", pklRequest.getDefaultRequestParams().get("grantType"))
                .formParam("client_id", pklRequest.getDefaultRequestParams().get("clientId"))
                .formParam("client_secret", pklRequest.getDefaultRequestParams().get("clientSecret"))
                .log().all(true).post().thenReturn();
    }
}
