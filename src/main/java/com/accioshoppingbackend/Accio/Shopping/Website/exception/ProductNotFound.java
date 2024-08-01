package com.accioshoppingbackend.Accio.Shopping.Website.exception;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound(String message){
        super(message);
    }
}
