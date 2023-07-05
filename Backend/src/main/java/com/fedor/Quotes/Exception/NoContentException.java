package com.fedor.Quotes.Exception;

public class NoContentException extends Exception{
    private final String message;

    public NoContentException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
