package com.tsybulko.exception;

public class SimpleCompositException extends Exception {
    public SimpleCompositException() {
        super();
    }

    public SimpleCompositException(String message) {
        super(message);
    }

    public SimpleCompositException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimpleCompositException(Throwable cause) {
        super(cause);
    }
}
