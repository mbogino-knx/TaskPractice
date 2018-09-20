package com.manubogino.taskpractice.parsers.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manubogino.taskpractice.exceptions.ParserException;
import com.manubogino.taskpractice.parsers.Parser;

public class JacksonJsonParser implements Parser {
    private final ObjectMapper objectMapper;

    public JacksonJsonParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T parseToObject(String toParse, Class<T> type) throws ParserException {
        try {
            return objectMapper.readValue(toParse, type);
        } catch (Exception e) {
            throw new ParserException("Error al deserializar: " + e.getMessage());
        }
    }

    @Override
    public <T> String parseToString(T toParse) throws ParserException {
        try {
            return objectMapper.writeValueAsString(toParse);
        } catch (Exception e) {
            throw new ParserException("Error al serializar: " + e.getMessage());
        }
    }
}

