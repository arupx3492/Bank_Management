package com.Bank.exception;

public class InvalidOperation extends RuntimeException{

    public InvalidOperation(String message) {
        super(message);
    }
}
