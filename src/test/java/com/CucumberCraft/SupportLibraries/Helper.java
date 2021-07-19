package com.CucumberCraft.SupportLibraries;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;

public class Helper {

	private ScenarioContext scenarioContext = new ScenarioContext();
	private Properties properties = Settings.getInstance();
	private JSONObject TestData = null;

	public Helper() {

	}

	/**
	 * Getters and Setters
	 */
	public ScenarioContext getScenarioContext() {
		return scenarioContext;
	}

	public JSONObject getTestData() {
		return TestData;
	}

	public Properties getProperties() {
		return properties;
	}

	/**
	 * Java-Core Functions
	 * 
	 * @throws Exception
	 */
	public void createFolderIfNotExist(String Dir) throws Exception {
		Path path = Paths.get(Dir);
		if (!Files.exists(path))
			Files.createDirectories(path);
	}

	public void copyFile(File from, File to) throws Exception {
		Files.copy(from.toPath(), to.toPath());
	}

	public String getCurrentDateAsString(String format) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}

	public String getConfig(String param) {
		return properties.getProperty(param);
	}

	public String returnCleanString(String s) {
		return s.trim().replaceAll("\\r|\\n", "");
	}

	public String returnRandomString(String repeatedChar, int length) {
		return StringUtils.repeat(repeatedChar, length);
	}

	public <T> T[] jsonObjectArrayToJavaObjectArray(Object data, Class<T[]> objectClass) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		JsonArray jsonArr = new Gson().toJsonTree(data).getAsJsonArray();
		return mapper.readValue(jsonArr.toString(), objectClass);
	}

	public boolean checkSortingASC(ArrayList<String> arraylist) {
		boolean isSorted = true;
		for (int i = 1; i < arraylist.size(); i++) {
			if (arraylist.get(i - 1).compareTo(arraylist.get(i)) > 0) {
				isSorted = false;
				break;
			}
		}
		return isSorted;
	}

	public boolean checkSortingDSC(ArrayList<String> arraylist) {
		boolean isSorted = true;
		for (int i = 1; i < arraylist.size(); i++) {
			if (arraylist.get(i - 1).compareTo(arraylist.get(i)) < 0) {
				isSorted = false;
				break;
			}
		}
		return isSorted;
	}

	/**
	 * Framework Functions
	 */
	public void compare2Text(String actual, String expect) {
		Assert.assertEquals(expect, actual);
	}

	public void writeStepFAIL() {
		Assert.fail();
	}

	public void writeStepFAIL(String message) {
		Assert.fail(message);
	}

	public String extractPageNameFromElementname(String elementName) {
		String pageName = null;
		int subStringEndIndex = elementName.indexOf("PAGE") + 4;
		pageName = elementName.substring(0, subStringEndIndex);
		return pageName;
	}

	public void delaySynchronization(int timeInSeconds) throws InterruptedException {
		Thread.sleep(timeInSeconds * 1000);
	}

	public String loadTestDataIntoParam(String param) throws Exception {
		if (!param.startsWith("<data:"))
			return param;
		else {
			String fieldName = param.replace("<data:", "");
			String testDataFile = this.getScenarioContext().getContext("SCENARIO_NAME").toString().split("-")[0].trim();

			fieldName = fieldName.substring(0, fieldName.length() - 1);
			String jsonPath = "$." + fieldName;

			File jsonFile = new File(
					System.getProperty("user.dir") + "\\src\\test\\resources\\data\\" + testDataFile + ".json");

			return JsonPath.read(jsonFile, jsonPath);
		}
	}

	public String printCurrentTestData() throws Exception {
		String testDataFile = this.getScenarioContext().getContext("SCENARIO_NAME").toString().split("-")[0].trim();
		File jsonFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\data\\" + testDataFile + ".json");
		return new String(Files.readAllBytes(jsonFile.toPath()), StandardCharsets.UTF_8);
	}
}
