package com.CucumberCraft.API.Base;

import java.util.HashMap;
import java.util.Map;

import com.CucumberCraft.API.DTO.Requests.MySMS;
import com.CucumberCraft.API.DTO.Requests.SearchWorkOrder;
import com.CucumberCraft.SupportLibraries.Helper;
import com.CucumberCraft.SupportLibraries.ScenarioContext;
import com.CucumberCraft.SupportLibraries.TestController;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CarLogixService extends BaseService {

	private Helper helper = TestController.getHelper();
	private Map<String, String> headers = new HashMap<String, String>();
	private String REQUEST_GET_FACILITY = helper.getConfig("api.carLogix.endpoint.getFacility");
	private String REQUEST_SEARCH_WORK_ORDER = helper.getConfig("api.carLogix.endpoint.searchWorkOrder");

	public CarLogixService(ScenarioContext scenarioContex, String host) {
		super(scenarioContex, host);
		setAuthenticationHeader();
		// TODO Auto-generated constructor stub
	}

	public CarLogixService(ScenarioContext scenarioContext, String protocol, String host, int port) {
		super(scenarioContext, protocol, host, port);
	}

	public Response requestGetFacilityByCompany(String companyCode) {
		RequestSpecification spec = this.defaultRequestBuilder(REQUEST_GET_FACILITY + "/" + companyCode);
		return spec.get();
	}
	
	public Response requestSearchWorkOrder(SearchWorkOrder searchWorkOrderRequest, String markCode) {
		setSelectedMarkHeader(markCode);
		RequestSpecification spec = this.defaultRequestBuilder(REQUEST_SEARCH_WORK_ORDER);
		return spec.body(searchWorkOrderRequest.getDefaultRequestParams()).log().all(true).post().thenReturn();
	}

	public void setLoginCookie() {
		Map<String, String> cookies = new HashMap<String, String>();
		String cookie = helper.getConfig("api.carLogix.cookie.login");
		String cookieName;
		String cookieValue;
		String[] cookiesArr = cookie.split(";");

		for (int i = 0; i < cookiesArr.length; i++) {
			cookieName = cookiesArr[i].split("=")[0].trim();
			cookieValue = cookiesArr[i].split("=")[1].trim();
			cookies.put(cookieName, cookieValue);
		}

		this.setCookies(cookies);
	}
	
	public void setAuthenticationHeader() {		
		String bearerToken = helper.getConfig("api.carLogix.authentication.bearer");
		headers.put("Authorization", "Bearer " + bearerToken);
		this.setHeaders(headers);
	}
	
	public void setSelectedMarkHeader(String markCode) {				
		headers.put("x-selected-mark", markCode);
		this.setHeaders(headers);
	}
}
