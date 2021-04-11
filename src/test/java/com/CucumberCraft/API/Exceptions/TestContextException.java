package com.CucumberCraft.API.Exceptions;

public class TestContextException extends RuntimeException {
    private static final long serialVersionUID = 0L;

    public TestContextException(String message) {
        super(message);
    }

    public TestContextException(String message, Throwable cause) {
        super(message, cause);
    }
}
