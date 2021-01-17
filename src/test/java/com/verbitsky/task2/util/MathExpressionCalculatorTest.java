package com.tsybulko.util;

import com.tsybulko.exception.SimpleCompositException;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MathExpressionCalculatorTest {

    @Test
    public void testCalculateMathExpression() throws SimpleCompositException {
        String text = "2+2*2-2/2+i*j"; //1+3-1+2=7
        String expected = "7.0";
        String actual = MathExpressionCalculator.CalculateMathExpression(text, 1L, 2L);
        Assert.assertEquals(actual, expected);
    }
}