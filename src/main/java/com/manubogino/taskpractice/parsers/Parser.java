package com.manubogino.taskpractice.parsers;

import com.manubogino.taskpractice.exceptions.ApiException;

public interface Parser {
    <T> T parseToObject(String toParse, Class<T> type) throws ApiException;

    <T> String parseToString(T toParse) throws ApiException;
}
