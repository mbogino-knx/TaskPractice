package com.manubogino.taskpractice.parsers.JacksonJsonParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manubogino.taskpractice.exceptions.ApiException;
import com.manubogino.taskpractice.exceptions.BadRequestApiException;
import com.manubogino.taskpractice.parsers.implementation.JacksonJsonParser;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParseToObjectTests {
    private ObjectMapper mapper;
    private JacksonJsonParser parser;

    @Test
    public void parseToObjectShouldReturnParsedObjectWhenStringIsValid() throws ApiException {
        final int id = 2;
        final String name = "nameToParse";

        mapper = new ObjectMapper();
        parser = new JacksonJsonParser(mapper);

        String toParse = String.format("{ \"id\": %d, \"name\": \"%s\"}", id, name);
        EntityToParse entity = parser.parseToObject(toParse, EntityToParse.class);

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
    }

    @Test
    public void parseToObjectShouldThrowBadRequestApiExceptionWhenParserThrowException() throws IOException {
        mapper = mock(ObjectMapper.class);
        when(mapper.readValue(anyString(), eq(EntityToParse.class))).thenThrow(new IOException());
        parser = new JacksonJsonParser(mapper);

        assertThrows(BadRequestApiException.class, () -> parser.parseToObject("", EntityToParse.class));
    }

    @Test
    public void parseToObjectShouldThrowBadRequestApiExceptionWhenStringIsNull() {
        mapper = new ObjectMapper();
        parser = new JacksonJsonParser(mapper);

        assertThrows(BadRequestApiException.class, () -> parser.parseToObject(null, EntityToParse.class));
    }
}