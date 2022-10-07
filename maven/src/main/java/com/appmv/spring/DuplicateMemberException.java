package com.appmv.spring;

public class DuplicateMemberException extends RuntimeException {
    public DuplicateMemberException(String message){
        super(message);
    }
}
