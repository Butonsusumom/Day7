package com.tsybulko.validator.impl;

import com.tsybulko.validator.ReaderValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class FileReaderValidator implements ReaderValidator {
    private static Logger logger = LogManager.getLogger();

    //return true if data file is empty
    private boolean validateEmptyDataFile(String path) {
        File file = new File(getClass().getClassLoader().getResource(path).getFile());
        boolean result = (file.length() == 0);
        if (result) {
            logger.log(Level.INFO, "FileReaderValidator: Empty file: " + path);
        }
        return result;
    }

    //returns true if file path is correct
    @Override
    public boolean validateFile(String path) {
        boolean result = false;
        if (!isEmptyOrNullPath(path)) {
            if (!validateEmptyDataFile(path)) {
                File file = new File(getClass().getClassLoader().getResource(path).getFile());
                if (file.exists()) {
                    if (!file.isDirectory()) {
                        result = true;
                    } else {
                        logger.log(Level.WARN, "FileReaderValidator: Not a file: " + path);
                    }
                } else {
                    logger.log(Level.WARN, "FileReaderValidator: Wrong file path: " + path);
                }
            }
        }
        return result;
    }

    //returns true if path null or empty
    private boolean isEmptyOrNullPath(String path) {
        if (path != null || !path.isEmpty()) {
            return false;
        }
        logger.log(Level.INFO, "FileReaderValidator: Empty or Null file path");
        return true;
    }


}
