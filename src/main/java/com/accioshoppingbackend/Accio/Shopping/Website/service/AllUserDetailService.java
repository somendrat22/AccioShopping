package com.accioshoppingbackend.Accio.Shopping.Website.service;

import com.accioshoppingbackend.Accio.Shopping.Website.model.ApplicationUser;
import com.accioshoppingbackend.Accio.Shopping.Website.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AllUserDetailService {

    private UserRepository userRepository = new UserRepository();

    public List<String> getAllUserName(){
        // We need to written name of all the user present in our application
        HashMap<String, ApplicationUser> userMap = userRepository.getAllUser();
        List<String> names = new ArrayList<>();
        for(String key : userMap.keySet()){
            ApplicationUser user = userMap.get(key);
            String name = user.getName();
            names.add(name);
        }
        return names;
    }
}
