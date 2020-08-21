package com.CucumberCraft.SupportLibraries;

import java.io.File;
import java.io.FileReader;
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

	public String readGlobalParam(String param) {
		return properties.getProperty(param);
	}

	public void wait(int secs) throws Exception {
		Thread.sleep(secs * 1000);
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

	public void loadTestDataFromJson(String TestcaseID, String JsonFile) throws Exception {
		Object data = null;
		JSONParser jsonParser = new JSONParser();
		FileReader reader;
		try {
			reader = new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\data\\" + JsonFile + ".json");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reader = new FileReader(System.getProperty("user.dir") + "/src/test/resources/data/" + JsonFile + ".json");
		}
		data = jsonParser.parse(reader);
		JSONArray dataset = (JSONArray) data;

		for (int i = 0; i < dataset.size(); i++) {
			JSONObject tc = (JSONObject) dataset.get(i);
			if (tc.containsKey(TestcaseID)) {
				TestData = (JSONObject) tc.get(TestcaseID);
				return;
			}
		}
		writeStepFAIL("Unable to load test data of: " + TestcaseID);
	}

	public String retrieveOTPfromSMS(String sender, String receiver) throws Exception {
		MySmsUtils otpGetter = new MySmsUtils(receiver, sender);
		return otpGetter.getOTP();
	}
}
