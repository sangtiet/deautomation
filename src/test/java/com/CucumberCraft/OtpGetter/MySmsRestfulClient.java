package com.CucumberCraft.OtpGetter;

import com.CucumberCraft.JsonModel.MySmsCredential;
import com.CucumberCraft.JsonModel.MySmsMessage;
import com.CucumberCraft.supportLibraries.GsonUtils;
import com.google.gson.JsonObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InvalidObjectException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javassist.NotFoundException;


public class MySmsRestfulClient {
    private static final String URL = "https://app.mysms.com/json/user/message/get/by/conversation";

    private final StaticCredentialRepository credentialRepository = new StaticCredentialRepository();

    public MySmsRestfulClient() {        
    }

    public List<MySmsMessage> getMessages(String sender, String receiver, int offset, int limit) throws IOException, NotFoundException {
        MySmsCredential credential = credentialRepository.getForPhoneNumber(receiver);

        HttpUriRequest request = createRequest(credential, sender, offset, limit);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(request);

        return convertResponse(response);
    }

    private HttpPost createRequest(MySmsCredential credential, String sender, int offset, int limit) throws UnsupportedEncodingException {
        JsonObject mySmsRequest = new JsonObject();
        mySmsRequest.addProperty("address", sender);
        mySmsRequest.addProperty("authToken", credential.getAuthToken());
        mySmsRequest.addProperty("apiKey", credential.getApiKey());
        mySmsRequest.addProperty("offset", offset);
        mySmsRequest.addProperty("limit", limit);
        StringEntity entity = new StringEntity(GsonUtils.toJsonString(mySmsRequest));

        HttpPost postRequest = new HttpPost(URL);
        postRequest.setEntity(entity);
        postRequest.setHeader("Accept", "application/json");
        postRequest.setHeader("Content-type", "application/json");

        return postRequest;
    }

    private List<MySmsMessage> convertResponse(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        if (entity == null) {
            throw new InvalidObjectException("Entity not found in response");
        }
        InputStreamReader inputStreamReader = new InputStreamReader(entity.getContent());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        MySmsResponse mySmsResponse = GsonUtils.fromJsonReader(bufferedReader, MySmsResponse.class);
        if (mySmsResponse == null) {
            throw new InvalidObjectException("Invalid MySmsResponse object json");
        }
        return mySmsResponse.messages;
    }
}
