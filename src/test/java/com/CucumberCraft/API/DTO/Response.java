package com.CucumberCraft.API.DTO;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.CucumberCraft.API.Exceptions.TestContextException;
import com.CucumberCraft.SupportLibraries.ObjectMapperUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface Response {
    static final Logger LOGGER = LoggerFactory.getLogger(Response.class);

    @SuppressWarnings({ "java:S2139" })
    default <T> T convertResponseToDTOObject(String responseData, Class<T> dtoClass) {
        try {
            ObjectMapper mapper = ObjectMapperUtils.getMapperInstance();
            return mapper.readValue(responseData, dtoClass);
        } catch (IOException e) {
            LOGGER.error(String.format("Error in mapping DTO class [%s] to response data. %s",
                    dtoClass.getClass().getSimpleName(), e.getMessage()));
            throw new TestContextException(String.format("Error in mapping DTO class [%s] to response data. %s",
                    dtoClass.getClass().getSimpleName(), e.getMessage()), e);
        }
    }
}
