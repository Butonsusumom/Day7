package com.tsybulko.parser.impl;

import com.tsybulko.composite.TextComponent;
import com.tsybulko.composite.TextComponentType;
import com.tsybulko.composite.impl.Letter;
import com.tsybulko.composite.impl.SpecialSymbol;
import com.tsybulko.composite.impl.TextComponentImpl;
import com.tsybulko.parser.PartParser;

public class SymbolParser implements PartParser {
    private static final SymbolParser INSTANCE = new SymbolParser();

    private SymbolParser() {
    }

    public static SymbolParser getInstance() {
        return INSTANCE;
    }

    @Override
    public TextComponent parse(String line) {
        TextComponent lexeme = new TextComponentImpl(TextComponentType.LEXEME);
        char[] chars = line.toCharArray();
        for (char symbol : chars) {
            if (Character.isLetterOrDigit(symbol)) {
                lexeme.add(new Letter(symbol));
            } else {
                lexeme.add(new SpecialSymbol(symbol));
            }
        }
        return lexeme;
    }
}

