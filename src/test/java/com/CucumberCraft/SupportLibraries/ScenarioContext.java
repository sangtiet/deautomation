package com.CucumberCraft.SupportLibraries;

public class ScenarioContext {

	private TestContext testContext;

    public ScenarioContext() {
        this.testContext = TestContext.getInstance();
    }

    public void setContext(String key, Object value) {
        this.testContext.setData(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getContext(String key) {
        return (T) this.testContext.getData(key);
    }

    public Boolean isContains(String key) {
        return this.testContext.isContains(key);
    }
}
