package com.tsybulko.parser;

import com.tsybulko.composite.TextComponent;

public interface PartParser {
    TextComponent parse(String line);
}
