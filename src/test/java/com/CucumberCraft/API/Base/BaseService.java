package com.CucumberCraft.API.Base;

import java.util.Map;

import com.CucumberCraft.SupportLibraries.Helper;
import com.CucumberCraft.SupportLibraries.ScenarioContext;
import com.CucumberCraft.SupportLibraries.TestController;
import com.google.api.client.util.ArrayMap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author minhhn3
 *
 */
public abstract class BaseService {
    protected ScenarioContext scenarioContex;
    private Helper helper = TestController.getHelper();
    private String host;
    private String protocol = "https";
    private int port = 443;
    private String isEnableProxy = helper.getConfig("default.proxy");
    private String proxyHost = helper.getConfig("default.proxy.host");
    private String proxyPort = helper.getConfig("default.proxy.port");
    private Map<String, ?> cookies = new ArrayMap<>();
    private Map<String, ?> headers = new ArrayMap<>();

    protected BaseService(ScenarioContext scenarioContex, String protocol, String host, int port) {
        this(scenarioContex, host);
        this.protocol = protocol;
        this.port = port;
    }

    protected BaseService(ScenarioContext scenarioContex, String host) {
        this.scenarioContex = scenarioContex;
        this.host = host;
    }

    protected RequestSpecification requestBuilderWithProxy(String apiPath, String proxyHost, int proxyPort) {
        return RestAssured.given().contentType(ContentType.URLENC.withCharset("UTF-8")).baseUri(this.getAPIUrl())
                .basePath(apiPath).relaxedHTTPSValidation().cookies(cookies).headers(headers)
                .proxy(proxyHost, proxyPort);
    }

    protected RequestSpecification defaultRequestBuilder(String apiPath) {
        RequestSpecification requestSpecification = RestAssured.given()
                .contentType(ContentType.URLENC.withCharset("UTF-8")).baseUri(this.getAPIUrl()).basePath(apiPath)
                .relaxedHTTPSValidation().cookies(cookies).headers(headers);
        if (this.isEnableProxy.equals("true")) {
            requestSpecification.proxy(this.proxyHost, Integer.parseInt(this.proxyPort));
        }
        return requestSpecification;
    }

    public String getAPIUrl() {
        return String.format("%s://%s:%d", this.protocol, this.host, this.port);
    }

    @SuppressWarnings("java:S1452")
    public Map<String, ?> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, ?> cookies) {
        this.cookies = cookies;
    }

    @SuppressWarnings("java:S1452")
    public Map<String, ?> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, ?> headers) {
        this.headers = headers;
    }
}
