package com.tsybulko.comporator;

import com.tsybulko.composite.TextComponent;
import com.tsybulko.composite.TextComponentType;

import java.util.Comparator;

public enum TextComponentComparator implements Comparator<TextComponent> {
    SENTENCE_COUNT {
        @Override
        public int compare(TextComponent o1, TextComponent o2) {
            return Long.compare(o1.getChildList().size(), o2.getChildList().size());
        }
    },
    LEXEME_LENGTH {
        @Override
        public int compare(TextComponent o1, TextComponent o2) {
            return Long.compare(TextComponentComparator.calculateLexemesLength(o1),
                                    TextComponentComparator.calculateLexemesLength(o2));
        }
    },
    WORD_LENGTH {
        @Override
        public int compare(TextComponent o1, TextComponent o2) {
            return Long.compare(TextComponentComparator.calculateWordsLength(o1),
                    TextComponentComparator.calculateWordsLength(o2));
        }
    };

    private static long calculateLexemesLength(TextComponent sentence) {
        long result = 0;
        for (TextComponent lexeme : sentence.getChildList()) {
            result += lexeme.getChildList().size();
        }
        return result;
    }

    private static long calculateWordsLength(TextComponent sentence) {
        long result = 0;
        for (TextComponent lexeme : sentence.getChildList()) {
            result += (lexeme.getChildList().size() - calculateSpecialSymbolsCount(lexeme));
        }
        return result;
    }

    private static long calculateSpecialSymbolsCount (TextComponent lexeme){
        long result = 0;
        for (TextComponent symbol : lexeme.getChildList()) {
            if (symbol.getComponentType() == TextComponentType.SPECIAL_SYMBOL) {
                result++;
            }
        }
        return result;
    }
}
