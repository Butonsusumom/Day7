package com.tsybulko.reader.impl;


import com.tsybulko.exception.SimpleCompositException;
import com.tsybulko.reader.DataReader;
import com.tsybulko.validator.ReaderValidator;
import com.tsybulko.validator.impl.FileReaderValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextDataReader implements DataReader {
    private static Logger logger = LogManager.getLogger();
    private static ReaderValidator fileValidator = new FileReaderValidator();

    @Override
    public List<String> readDataFromFile(String path) throws SimpleCompositException {
        if (!fileValidator.validateFile(path)) {
            logger.log(Level.ERROR, "DataReader: file read error");
            throw new SimpleCompositException("DataReader: file read error");
        }
        File file = new File(getClass().getClassLoader().getResource(path).getFile());
        Stream<String> stringsStream;
        try {
            stringsStream = Files.lines(Paths.get(file.getPath()), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            logger.log(Level.ERROR, "DataReader: file read error", ex);
            throw new SimpleCompositException("DataReader: file read error", ex);
        }
        List<String> dataList = stringsStream.collect(Collectors.toList());
        logger.log(Level.INFO, "Read data from file successfully completed");
        return dataList;
    }

}
