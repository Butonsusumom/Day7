package com.tsybulko.parser.impl;

import com.tsybulko.composite.TextComponent;
import com.tsybulko.composite.TextComponentType;
import com.tsybulko.composite.impl.Letter;
import com.tsybulko.composite.impl.SpecialSymbol;
import com.tsybulko.composite.impl.TextComponentImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ParserTest {
    private ParagraphParser paragraphParser;
    private SentenceParser sentenceParser;
    private LexemeParser lexemeParser;
    private SymbolParser symbolParser;
    //private String testText = "Paragraph1, sentence1.\n\tParagraph2 sentence1. Paragraph2 sentence2.";
    private TextComponent text;
    private TextComponent paragraph1;
    private TextComponent paragraph2;
    private TextComponent sentence1;
    private TextComponent lexeme1;
    private TextComponent lexeme2;
    private TextComponent sentence2;
    private TextComponent symbol1;
    private TextComponent symbol2;
    private TextComponent symbol3;
    private TextComponent symbol4;
    private TextComponent symbol5;
    private TextComponent symbol6;
    private TextComponent symbol7;
    private TextComponent symbol8;
    private TextComponent symbol9;
    private TextComponent symbol10;



    @BeforeClass
    public void setUp() {
        paragraphParser = ParagraphParser.getInstance();
        sentenceParser = SentenceParser.getInstance();
        lexemeParser = LexemeParser.getInstance();
        symbolParser = SymbolParser.getInstance();
        symbol5 = new SpecialSymbol('.');
        symbol10 = new SpecialSymbol('.');
        symbol1 = new Letter('H');
        symbol2 = new Letter('e');
        symbol3 = new Letter('r');
        symbol4 = new Letter('e');
        symbol6 = new Letter('T');
        symbol7 = new Letter('e');
        symbol8 = new Letter('x');
        symbol9 = new Letter('t');
        lexeme1= new TextComponentImpl(TextComponentType.LEXEME);
        lexeme2= new TextComponentImpl(TextComponentType.LEXEME);
        lexeme1.add(symbol1);
        lexeme1.add(symbol2);
        lexeme1.add(symbol3);
        lexeme1.add(symbol4);
        lexeme1.add(symbol5);
        lexeme2.add(symbol6);
        lexeme2.add(symbol7);
        lexeme2.add(symbol8);
        lexeme2.add(symbol9);
        lexeme2.add(symbol10);
        sentence1 = new TextComponentImpl(TextComponentType.SENTENCE);
        sentence2 = new TextComponentImpl(TextComponentType.SENTENCE);
        sentence1.add(lexeme1);
        sentence2.add(lexeme2);
        paragraph1 = new TextComponentImpl(TextComponentType.PARAGRAPH);
        paragraph1.add(sentence1);
        paragraph2 = new TextComponentImpl(TextComponentType.PARAGRAPH);
        paragraph2.add(sentence2);
        text = new TextComponentImpl(TextComponentType.TEXT);
        text.add(paragraph1);
        text.add(paragraph2);
    }

    @Test
    public void testParagraphParse() {
        String testText = "Here.\n\tText.";
        TextComponent actual = paragraphParser.parse(testText);
        TextComponent expected = text;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSentenceParse() {
        String testText = "Here.\n\t";
        TextComponent actual = sentenceParser.parse(testText).getChild(0);
        TextComponent expected = sentence1;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testLexemeParse() {
        String testText = "Here.";
        TextComponent actual = lexemeParser.parse(testText).getChild(0);
        TextComponent expected = lexeme1;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSymbolParse() {
        String testText = "Here.";
        TextComponent actual = symbolParser.parse(testText).getChild(0);
        TextComponent expected = symbol1;
        Assert.assertEquals(actual, expected);
    }
}