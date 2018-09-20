package com.manubogino.taskpractice.parsers.JacksonJsonParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manubogino.taskpractice.exceptions.ParserException;
import com.manubogino.taskpractice.parsers.implementation.JacksonJsonParser;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParseToStringTests {
    private ObjectMapper mapper;
    private JacksonJsonParser parser;

    @Test
    public void parseToStringShouldReturnStringWhenObjectIsValid() throws ParserException {
        final int id = 2;
        final String name = "nameToParse";
        final String parsed = String.format("{\"id\":%d,\"name\":\"%s\"}", id, name);

        EntityToParse objectToParse = new EntityToParse();
        objectToParse.setId(id);
        objectToParse.setName(name);

        mapper = new ObjectMapper();
        parser = new JacksonJsonParser(mapper);
        String result = parser.parseToString(objectToParse);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(parsed, result);
    }

    @Test
    public void parseToStringShouldThrowParserExceptionWhenParserThrowException() throws IOException {
        mapper = mock(ObjectMapper.class);
        when(mapper.writeValueAsString(any())).thenThrow(new JsonProcessingException("") {
        });
        parser = new JacksonJsonParser(mapper);
        assertThrows(ParserException.class, () -> parser.parseToString(new EntityToParse()));
    }

    @Test
    public void parseToStringShouldThrowParserExceptionWhenObjectIsNull() throws IOException {
        mapper = mock(ObjectMapper.class);
        when(mapper.writeValueAsString(any())).thenThrow(new JsonProcessingException("") {
        });
        parser = new JacksonJsonParser(mapper);

        assertThrows(ParserException.class, () -> parser.parseToString(null));
    }
}
