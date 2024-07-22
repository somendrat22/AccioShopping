package com.accioshoppingbackend.Accio.Shopping.Website.service;

import com.accioshoppingbackend.Accio.Shopping.Website.model.ApplicationUser;
import com.accioshoppingbackend.Accio.Shopping.Website.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // This service layer is to perform all crud related operations
    // We need to tell springboot hey this class is serviceclass

    // Get User who has ordered maximum products

    // When we are creating property of another class into a class we call it as dependency injectedb
    private UserRepository userRepository = new UserRepository();
    public void createUser(ApplicationUser applicationUser){
        // This method wants to save the user into the database
        // So this method will call repository layer to save the user.
        userRepository.createUser(applicationUser);
    }

    public ApplicationUser getUserByEmail(String email){
        // service layer will call repository layer to get the user by email
        ApplicationUser user = userRepository.getUserByEmail(email);
        return user;
    }

    public void updateUserByEmail(ApplicationUser applicationUser, String email){
        userRepository.updateUser(email, applicationUser);
    }

    public void deleteUser(String email){
        userRepository.deleteUser(email);
    }
}
