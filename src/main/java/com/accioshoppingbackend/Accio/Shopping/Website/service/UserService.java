package com.accioshoppingbackend.Accio.Shopping.Website.service;

import com.accioshoppingbackend.Accio.Shopping.Website.model.ApplicationUser;
import com.accioshoppingbackend.Accio.Shopping.Website.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    // We need to tell springboot hey this class is serviceclass

    // Get User who has ordered maximum products
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
}
