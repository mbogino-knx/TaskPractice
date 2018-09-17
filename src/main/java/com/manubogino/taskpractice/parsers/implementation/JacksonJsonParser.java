package com.manubogino.taskpractice.parsers.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manubogino.taskpractice.exceptions.ApiException;
import com.manubogino.taskpractice.exceptions.BadRequestApiException;
import com.manubogino.taskpractice.parsers.Parser;

public class JacksonJsonParser implements Parser {
    private final ObjectMapper objectMapper;

    public JacksonJsonParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T parseToObject(String toParse, Class<T> type) throws ApiException {
        try {
            return objectMapper.readValue(toParse, type);
        } catch (Exception e) {
            throw new BadRequestApiException("Error al deserializar el request.");
        }
    }

    @Override
    public <T> String parseToString(T toParse) throws ApiException {
        try {
            return objectMapper.writeValueAsString(toParse);
        } catch (Exception e) {
            throw new BadRequestApiException("Error al deserializar el request.");
        }
    }
}

