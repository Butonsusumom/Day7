package com.tsybulko.reader;

import com.tsybulko.exception.SimpleCompositException;

import java.util.List;

public interface DataReader {
    List<String> readDataFromFile(String filePath) throws SimpleCompositException;
}
