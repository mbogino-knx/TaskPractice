package com.manubogino.taskpractice.parsers;

import com.manubogino.taskpractice.exceptions.ParserException;

public interface Parser {
    <T> T parseToObject(String toParse, Class<T> type) throws ParserException;

    <T> String parseToString(T toParse) throws ParserException;
}
