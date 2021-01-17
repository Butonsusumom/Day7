package com.tsybulko.parser.impl;

import com.tsybulko.parser.ExpressionParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParserImpl implements ExpressionParser {
    private static final String MATH_EXPRESSION = "(\\s)(\\(?[(i|j)\\d\\+\\-\\*\\/)\\(\\n\\s])+\\s";
    private static final ExpressionParserImpl INSTANCE = new ExpressionParserImpl();
    private static final String SPACES_SYMBOLS = "[\\s]{0,}|\n";

    public static ExpressionParserImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<String> parseMathExpressions(String text) {
        Pattern pattern = Pattern.compile(MATH_EXPRESSION);
        Matcher matcher = pattern.matcher(text);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            String expr = matcher.group().trim();
            if (!expr.matches(SPACES_SYMBOLS) && expr.length() > 2) {
                result.add(expr);
            }
        }
        return result;
    }
}

