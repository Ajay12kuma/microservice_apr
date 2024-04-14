package com.userservice.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resourse Not found ........!!");
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
