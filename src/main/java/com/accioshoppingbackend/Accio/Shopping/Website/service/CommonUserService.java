package com.accioshoppingbackend.Accio.Shopping.Website.service;

import com.accioshoppingbackend.Accio.Shopping.Website.exception.UserNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.exception.WrongCredentials;
import com.accioshoppingbackend.Accio.Shopping.Website.model.AppUser;
import com.accioshoppingbackend.Accio.Shopping.Website.repository.UserRepsoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import scala.App;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommonUserService {

    @Autowired
    private UserRepsoitory userRepsoitory;

    public String aunthenticateUser(String token){
        // Split token on the basis of colon
        // We will call repository layer to get the user by email
        // We will get user object and we will compare password that user has provided
        // and the password that is present in the user object which we got from db
        // If password is correct so we will return successfull message else we will return
        // failed message
        String [] userCredentials = token.split(":");
        String userEmail = userCredentials[0];
        String userPassword = userCredentials[1];
        // We need to verify user is existing in our system;
        // Verify the password present in our system is equal to password provided by the user
        AppUser user = userRepsoitory.findByEmail(userEmail);
        if(user == null){
            throw new UserNotFound(String.format("User with email %s does not exist in System", userEmail));
        }
        String originalPassword = user.getPassword();
        if(originalPassword.equals(userPassword)){
            return "Authenthication Successfull";
        }
        throw new WrongCredentials("Password provided by user is incorrect");
    }


    public AppUser getUserById(UUID userID){
        AppUser user = userRepsoitory.findById(userID).orElse(null);
        return user;
    }

    public void registerUser(AppUser user){
        // we will simply call repository layer to create user record in database.
        userRepsoitory.save(user);
    }
}
