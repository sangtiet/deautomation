package com.CucumberCraft.StepDefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.CucumberCraft.SupportLibraries.ScenarioContext;

public class SharedContextSteps {
	private static final Logger LOGGER = LoggerFactory.getLogger(SharedContextSteps.class);

    protected static final String PARAMETER_KEY = "REQUEST_PARAMETER_KEY";
    /**
     * Contains shared value between context It will access through the TestContext
     * to ensure thread-safe
     */
    protected final ScenarioContext scenarioContext;

    public SharedContextSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @SuppressWarnings({ "java:S2142" })
    protected void delayToProcessAsyncTransaction(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            LOGGER.warn(String.format("Completed delay %d ms", timeout));
        }
    }
}
