package com.fedor.Quotes.Exception;

public class NotFoundException extends RuntimeException{
    private final String message;

    public  NotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
