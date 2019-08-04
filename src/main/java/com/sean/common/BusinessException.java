package com.sean.common;

public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public BusinessException(){}

    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(String message){
        super(message);
    }
}
