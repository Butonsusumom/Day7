package com.tsybulko.composite;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component);

    void remove(TextComponent component);

    TextComponent getChild(int index);

    List<TextComponent> getChildList();

    TextComponentType getComponentType();
}
