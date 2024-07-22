package com.accioshoppingbackend.Accio.Shopping.Website.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RestController -> We are telling Springboot this particular class is the
// Controller class
public class Controller {
    // We need to define some kind of url such that client is going to communicate with
    // our server


    // We want to create account for user.

    // http://localhost:8081/hello
    @GetMapping("/hello")
    public String sayHii(){
        return "Hello World, I am somendra";
    }


}
