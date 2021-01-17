package com.tsybulko.composite.impl;

import com.tsybulko.composite.TextComponent;
import com.tsybulko.composite.TextComponentType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextComponentImpl implements TextComponent {
    private static final String LEXEME_DELIMITER = " ";
    private static final String PARAGRAPH_DELIMITER = "\n\t";
    private TextComponentType componentType;
    private List<TextComponent> componentList;

    public TextComponentImpl(TextComponentType componentType) {
        this.componentType = componentType;
        componentList = new ArrayList<>();
    }

    @Override
    public void add(TextComponent component) {
        if (component != null) {
            componentList.add(component);
        }
    }

    @Override
    public List<TextComponent> getChildList() {
        return Collections.unmodifiableList(componentList);
    }

    @Override
    public void remove(TextComponent component) {
        componentList.remove(component);
    }

    @Override
    public TextComponent getChild(int index) {
        return componentList.get(index);
    }

    @Override
    public TextComponentType getComponentType() {
        return componentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextComponentImpl)) return false;
        TextComponentImpl that = (TextComponentImpl) o;
        if (componentType != that.componentType) return false;
        if (componentList != null && that.componentList != null) {
            if (componentList.size() != that.componentList.size()) {
                return false;
            }
        }
        boolean res = false;
        //compare child elements
        for (int i = 0; i < componentList.size(); i++) {
            TextComponent o1 = componentList.get(i);
            TextComponent o2 = that.componentList.get(i);
            res = o1.equals(o2);
            if (!res) {
                return false;
            }
        }
        return res;
    }

    @Override
    public int hashCode() {
        int result = componentType != null ? componentType.hashCode() : 0;
        result = 31 * result + (componentList != null ? componentList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        for (TextComponent component : componentList) {
            stringBuilder.append(component.toString());
            if (++counter < componentList.size()) {
                if (TextComponentType.PARAGRAPH.equals(component.getComponentType())) {
                    stringBuilder.append(PARAGRAPH_DELIMITER);
                }
                if (TextComponentType.LEXEME.equals(component.getComponentType())) {
                    stringBuilder.append(LEXEME_DELIMITER);
                }
            }
        }
        return stringBuilder.toString();
    }
}
