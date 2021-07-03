package com.CucumberCraft.StepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.json.simple.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;



public class TestIndexOf {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String source = "LANDING_PAGE_LEADING_MISSION_LABEL";
		int subStringEndIndex = source.indexOf("PAGE") + 4;

		//String jsonPath = "$.elements[?(@.name=='LANDING_PAGE_LEADING_MISSION_LABEL')].locators.android";
		String jsonPath = "$.detectors.android";
		String pageName = source.substring(0, subStringEndIndex);
		File jsonFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\pages\\" + pageName + ".json");

		Configuration conf = Configuration.builder().jsonProvider(new GsonJsonProvider()).build();

	    String file01 = JsonPath.using(conf).parse(jsonFile).read(jsonPath).toString();
	    System.out.println(file01.replaceAll("^\"+|\"+$", ""));
	    //JsonObject jo = (JsonObject) file01.get(0);

	    // prints out {"id":"0001"}
	    //System.out.println(jo.getAsJsonObject().get("selector").toString());
		
		//System.out.println("element: " + JsonPath.read(jsonFile, jsonPath));
	}

}
