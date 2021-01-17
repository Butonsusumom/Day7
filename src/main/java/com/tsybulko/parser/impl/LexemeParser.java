package com.tsybulko.parser.impl;

import com.tsybulko.composite.TextComponent;
import com.tsybulko.composite.TextComponentType;
import com.tsybulko.composite.impl.TextComponentImpl;
import com.tsybulko.parser.PartParser;

public class LexemeParser implements PartParser {
    private static final String LEXEME_DELIMITER = "[ ]+";
    private static final LexemeParser INSTANCE = new LexemeParser();

    private LexemeParser() {
    }

    public static LexemeParser getInstance() {
        return INSTANCE;
    }

    @Override
    public TextComponent parse(String line) {
        TextComponent sentence = new TextComponentImpl(TextComponentType.SENTENCE);
        String[] lexemes = line.split(LEXEME_DELIMITER);
        for (String element : lexemes) {
            TextComponent lexeme = SymbolParser.getInstance().parse(element);
            if (!lexeme.toString().equals("")) {
                sentence.add(lexeme);
            }

        }
        return sentence;
    }
}
