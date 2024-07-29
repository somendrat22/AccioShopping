package com.accioshoppingbackend.Accio.Shopping.Website.exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message){
        super(message);
    }
}
