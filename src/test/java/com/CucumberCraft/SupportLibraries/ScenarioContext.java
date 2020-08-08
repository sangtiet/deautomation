package com.CucumberCraft.SupportLibraries;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

public class ScenarioContext {

	private Map<String, Object> scenarioContext;

	public ScenarioContext() {
		scenarioContext = new HashMap<>();
	}

	public void setContext(String key, Object value) {
		scenarioContext.put(key, value);
	}

	public Object getContext(String key) {
		if (scenarioContext.get(key) != null)
			return scenarioContext.get(key);
		else
			Assert.fail("ScenarioContext class - Key not found: " + key);
		return null;
	}

	public Boolean isContains(String key) {
		return scenarioContext.containsKey(key);
	}
}
