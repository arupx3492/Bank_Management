package com.greatLearning.GreatLearningAssignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MyErrorDetails handleAccountNotFoundException(AccountNotFoundException ex){
        return new MyErrorDetails(LocalDateTime.now(),ex.getMessage(),"Account Not Found");
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MyErrorDetails handleInsufficientBalanceException(InsufficientBalanceException ex){
        return new MyErrorDetails(LocalDateTime.now(),ex.getMessage(),"Insufficient Balance");
    }

    @ExceptionHandler(UnauthorizedOperationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public MyErrorDetails handleUnauthorizedOperationException(UnauthorizedOperationException ex){
        return new MyErrorDetails(LocalDateTime.now(),ex.getMessage(),"Unauthorized Operation");
    }

    @ExceptionHandler(InvalidTransactionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public MyErrorDetails handleInvalidTransactionException(InvalidTransactionException ex){
        return new MyErrorDetails(LocalDateTime.now(),ex.getMessage(),"Invalid Transaction");
    }

    @ExceptionHandler(InvalidOperation.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MyErrorDetails handleInvalidOperationException(InvalidOperation ex){
        return new MyErrorDetails(LocalDateTime.now(),ex.getMessage(),"Invalid Operation");
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MyErrorDetails handleGeneralException(Exception ex){
        return new MyErrorDetails(LocalDateTime.now(),ex.getMessage(),"An unexpected error occurred");
    }
}
