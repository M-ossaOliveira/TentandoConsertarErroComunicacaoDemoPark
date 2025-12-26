package com.mballem.demoparkapi.web.exception;

public class CpfUniqueViolationException  extends RuntimeException{
    public CpfUniqueViolationException (String message){
        super (message);
    }
}
