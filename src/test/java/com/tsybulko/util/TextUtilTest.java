package com.tsybulko.util;

import com.tsybulko.composite.TextComponent;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TextUtilTest {
    @Test
    public void testReplaceArithmeticExpressionWithValue() {
        String arithmeticText = "I see 11+5-i*j cars.";
        String expected = "I see 10,0 cars.";
        String actual = TextUtil.replaceArithmeticExpressionWithValue(arithmeticText, 2L, 3L);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSortTextByParagraphsSentenceCount() {
        String  baseText = "\tIn this paragraph 2 sentence. first sentence.\n" +
                "\tIn this paragraph 4 sentence. first sentence. Second sentence. Third sentence here.\n" +
                "\tIn this paragraph 3 sentence. first sentence. Second sentence.\n" +
                "\tIn this paragraph 1 sentence.";
        List<TextComponent> fullText = TextUtil.sortTextByParagraphsSentenceCount(baseText);
        String expected = "In this paragraph 1 sentence.";
        String actual = fullText.get(0).toString();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSortTextBySentenceLexemesLength() {
        String text = "Test, test. Test test";
        List<TextComponent> fullText = TextUtil.sortTextBySentenceLexemesLength(text);
        String expected = "Test test";
        String actual = fullText.get(0).toString();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSortTextBySentenceWordsLength() {
        String text = "Test, test. Test test";
        List<TextComponent> fullText = TextUtil.sortTextBySentenceWordsLength(text);
        String expected = "Test, test.";
        String actual = fullText.get(0).toString();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testTextComparatorWithParameter() {
        String text = "Word woord woooord wooord wxc 1wx";
        List<TextComponent> result = TextUtil.sortLexemesByEntrySymbol(text, 'o');
        String actual = result.get(3).toString();
        String expected = "Word";
        Assert.assertEquals(actual, expected);
    }
}