package com.tsybulko.util;

import com.tsybulko.exception.SimpleCompositException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MathExpressionCalculator {
    private static Logger logger = LogManager.getLogger();

    private MathExpressionCalculator() {
    }

    public static String CalculateMathExpression (String text, Long i, Long j) throws SimpleCompositException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("JavaScript");
        engine.put("i", i);
        engine.put("j", j);
        Object result;
        try {
            result = engine.eval(text);
        } catch (ScriptException e) {
            logger.log(Level.WARN, "impossible calculate expression", e);
            throw new SimpleCompositException("impossible calculate expression", e);
        }
        return result.toString();
    }
}
