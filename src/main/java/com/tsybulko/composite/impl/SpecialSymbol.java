package com.tsybulko.composite.impl;

import com.tsybulko.composite.TextComponent;
import com.tsybulko.composite.TextComponentType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SpecialSymbol implements TextComponent {
    private static Logger logger = LogManager.getLogger();
    private TextComponentType componentType = TextComponentType.SPECIAL_SYMBOL;
    private char value;

    public SpecialSymbol(char letter) {
        this.value = letter;
    }

    @Override
    public List<TextComponent> getChildList() {
        logger.log(Level.WARN, "unsupported operation with this component type");
        throw new UnsupportedOperationException("unsupported operation with this component type");
    }

    @Override
    public void add(TextComponent component) {
        logger.log(Level.WARN, "unsupported operation with this component type");
        throw new UnsupportedOperationException("unsupported operation with this component type");
    }

    @Override
    public void remove(TextComponent component) {
        logger.log(Level.WARN, "unsupported operation with this component type");
        throw new UnsupportedOperationException("unsupported operation with this component type");
    }

    @Override
    public TextComponent getChild(int index) {
        logger.log(Level.WARN, "unsupported operation with this component type");
        throw new UnsupportedOperationException("unsupported operation with this component type");
    }

    @Override
    public TextComponentType getComponentType() {
        return componentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialSymbol)) return false;
        SpecialSymbol that = (SpecialSymbol) o;
        if (value != that.value) return false;
        return componentType == that.componentType;
    }

    @Override
    public int hashCode() {
        int result = componentType != null ? componentType.hashCode() : 0;
        result = 31 * result + (int) value;
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
