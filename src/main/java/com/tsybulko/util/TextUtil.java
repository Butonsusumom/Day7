package com.tsybulko.util;

import com.tsybulko.comporator.TextComponentComparator;
import com.tsybulko.composite.TextComponent;
import com.tsybulko.composite.TextComponentType;
import com.tsybulko.exception.SimpleCompositException;
import com.tsybulko.parser.PartParser;
import com.tsybulko.parser.impl.ExpressionParserImpl;
import com.tsybulko.parser.impl.LexemeParser;
import com.tsybulko.parser.impl.ParagraphParser;
import com.tsybulko.parser.impl.SentenceParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TextUtil {
    private static final String FRACTIONAL_PART = "\\.";
    private static final String EMPTY_SYMBOL = ",";
    private static Logger logger = LogManager.getLogger();

    private TextUtil() {
    }

    public static String replaceArithmeticExpressionWithValue(String text, Long i, Long j) {
        List<String> mathExpressions = ExpressionParserImpl.getInstance().parseMathExpressions(text);
        for (String expression : mathExpressions) {
            try {
                String expressionValue = MathExpressionCalculator.CalculateMathExpression(expression, i, j);
                expressionValue = expressionValue.replaceAll(FRACTIONAL_PART, EMPTY_SYMBOL);
                text = text.replace(expression, expressionValue);
            } catch (SimpleCompositException e) {
                logger.log(Level.WARN, e);
            }
        }
        return text;
    }

    public static List<TextComponent> sortTextByParagraphsSentenceCount(String text) {
        PartParser parser = ParagraphParser.getInstance();
        TextComponent component = parser.parse(text);
        List<TextComponent> paragraphs = new ArrayList<>();
        for (TextComponent child : component.getChildList()) {
            if (child.getComponentType() == TextComponentType.PARAGRAPH) {
                paragraphs.add(child);
            }
        }
        paragraphs.sort(TextComponentComparator.SENTENCE_COUNT);
        return paragraphs;
    }

    public static List<TextComponent> sortTextBySentenceLexemesLength(String text) {
        PartParser parser = SentenceParser.getInstance();
        TextComponent component = parser.parse(text);
        List<TextComponent> sentences = new ArrayList<>();
        for (TextComponent child : component.getChildList()) {
            if (child.getComponentType() == TextComponentType.SENTENCE) {
                sentences.add(child);
            }
        }
        sentences.sort(TextComponentComparator.LEXEME_LENGTH);
        return sentences;
    }

    public static List<TextComponent> sortTextBySentenceWordsLength(String text) {
        PartParser parser = SentenceParser.getInstance();
        TextComponent component = parser.parse(text);
        List<TextComponent> sentences = new ArrayList<>();
        for (TextComponent child : component.getChildList()) {
            if (child.getComponentType() == TextComponentType.SENTENCE) {
                sentences.add(child);
            }
        }
        sentences.sort(TextComponentComparator.WORD_LENGTH);
        return sentences;
    }

    public static List<TextComponent> sortLexemesByEntrySymbol(String text, char symbol) {
        PartParser parser = LexemeParser.getInstance();
        TextComponent sentence = parser.parse(text);
        List<TextComponent> lexemes = new ArrayList<>(sentence.getChildList());
        lexemes.sort((o1, o2) -> {
            IntStream intStream = o1.toString().codePoints();
            long countFirst = intStream
                    .filter((o) -> o == symbol)
                    .count();
            intStream = o2.toString().codePoints();
            long countSecond = intStream
                    .filter((o) -> o == symbol)
                    .count();
            if (countFirst > 0 && countSecond > 0) {
                return Long.compare(countSecond, countFirst);
            }
            if (countFirst > 0 && countSecond == 0) {
                return 1;
            }
            if (countFirst == 0 && countSecond > 0) {
                return 1;
            }
            return o1.toString().compareTo(o2.toString());
        });
        return lexemes;
    }
}
