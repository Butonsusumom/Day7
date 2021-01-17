package com.tsybulko.parser.impl;

import com.tsybulko.composite.TextComponent;
import com.tsybulko.composite.TextComponentType;
import com.tsybulko.composite.impl.TextComponentImpl;
import com.tsybulko.parser.PartParser;

public class ParagraphParser implements PartParser {
    private static final String PARAGRAPH_DELIMITER = "\n\t";
    private static final ParagraphParser INSTANCE = new ParagraphParser();

    private ParagraphParser() {
    }

    public static ParagraphParser getInstance() {
        return INSTANCE;
    }

    @Override
    public TextComponent parse(String line) {
        TextComponent text = new TextComponentImpl(TextComponentType.TEXT);
        String[] paragraphs = line.split(PARAGRAPH_DELIMITER);
        for (String element : paragraphs) {
            if (!element.isEmpty()) {
                TextComponent paragraph = SentenceParser.getInstance().parse(element);
                text.add(paragraph);
            }
        }
        return text;
    }
}
