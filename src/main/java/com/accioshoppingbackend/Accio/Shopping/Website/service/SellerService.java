package com.accioshoppingbackend.Accio.Shopping.Website.service;

import com.accioshoppingbackend.Accio.Shopping.Website.exception.AcessNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.exception.UserNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.model.AppUser;
import com.accioshoppingbackend.Accio.Shopping.Website.model.Product;
import com.accioshoppingbackend.Accio.Shopping.Website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SellerService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommonUserService userService;

    public void addProduct(Product product, UUID sellerID){

        // With the help of sellerID we will get seller user object.
        // If we will not get any seller object we will throw exception
        // If we got user object but user is buyer kind of user so we will throw exception
        // AccessNotFind

        // We will call repository layer to save the product
        AppUser user = userService.getUserById(sellerID);

        if(user == null){
            throw new UserNotFound(String.format("User not found with id %s", sellerID.toString()));
        }

        if(user.getUserType().equals("BUYER")){
            throw new AcessNotFound(String.format("USer with id %s does not have access to add product", sellerID.toString()));
        }
        product.setSeller(user);
        productRepository.save(product);



    }
}
