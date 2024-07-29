package com.accioshoppingbackend.Accio.Shopping.Website.controller;

import com.accioshoppingbackend.Accio.Shopping.Website.exception.UserNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.exception.WrongCredentials;
import com.accioshoppingbackend.Accio.Shopping.Website.model.AppUser;
import com.accioshoppingbackend.Accio.Shopping.Website.service.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class CommonController {
    // Common Controller is a controller that will have all the endpoints which for buyer as well as seller

    @Autowired
    CommonUserService commonUserService;

    @GetMapping("/login")
    public String authenthicateUser(@RequestHeader String token){
        try{
            String result = commonUserService.aunthenticateUser(token);
            return result;
        }catch (WrongCredentials wrongCredentials){
           return wrongCredentials.getMessage();
        }catch (UserNotFound userNotFound){
          return userNotFound.getMessage();
        }
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody AppUser appUser){
        commonUserService.registerUser(appUser);
        return "User got saved succesfully";
    }
}
