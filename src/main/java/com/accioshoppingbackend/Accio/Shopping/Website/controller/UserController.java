package com.accioshoppingbackend.Accio.Shopping.Website.controller;

import com.accioshoppingbackend.Accio.Shopping.Website.model.ApplicationUser;
import com.accioshoppingbackend.Accio.Shopping.Website.service.AllUserDetailService;
import com.accioshoppingbackend.Accio.Shopping.Website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AllUserDetailService allUserDetailService;

    @PostMapping("/api/register/user")
    public String registerUser(@RequestBody ApplicationUser applicationUser){
        // Logic for saving user information should be written in service layer
        userService.createUser(applicationUser);
        return "User got saved successfully";
    }
    // We want to get all user details by email

    @GetMapping("/api/user")
    public ApplicationUser getUserByEmail(@RequestParam String email){
        // Controller layer will call service layer to get user by email
        ApplicationUser user = userService.getUserByEmail(email);
        return user;
    }

    // You need to write one method such that you will update user details by email

    @PutMapping("/api/user/update")
    public ApplicationUser updateUserByEmail(@RequestBody ApplicationUser applicationUser,
                                             @RequestParam String email){
        userService.updateUserByEmail(applicationUser, email);
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/api/user/delete")
    public String removeUser(@RequestParam String email){
        userService.deleteUser(email);
        return "User got deleted";
    }

    @GetMapping("/api/user/all")
    public List<String> getAllUserName(){
        // We need to call alluserService
        List<String> names = allUserDetailService.getAllUserName();
        return names;
    }

}
