package com.tsybulko.parser;

import java.util.List;

public interface ExpressionParser {
    List <String> parseMathExpressions (String text);
}
