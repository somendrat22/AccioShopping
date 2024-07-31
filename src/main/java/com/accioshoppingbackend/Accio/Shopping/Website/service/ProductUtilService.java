package com.accioshoppingbackend.Accio.Shopping.Website.service;

import com.accioshoppingbackend.Accio.Shopping.Website.model.AppUser;
import com.accioshoppingbackend.Accio.Shopping.Website.model.Product;
import com.accioshoppingbackend.Accio.Shopping.Website.reponsebody.ProductResponseBody;
import com.accioshoppingbackend.Accio.Shopping.Website.reponsebody.UserResponseBody;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductUtilService {

    public List<ProductResponseBody> convertProductToProductResponse(List<Product> products){
        List<ProductResponseBody> productResponseBodyList = new ArrayList<>();

        for(Product product : products){
            AppUser seller = product.getSeller();
            ProductResponseBody productResponseBody = new ProductResponseBody();
            UserResponseBody userResponseBody = new UserResponseBody();
            // Setting product values inside product response body
            productResponseBody.setProductName(product.getProductName());
            productResponseBody.setCategory(product.getCategory());
            productResponseBody.setPrice(product.getPrice());
            productResponseBody.setRating(product.getRating());
            productResponseBody.setQuantity(product.getQuantity());
            // Setting user values inside user response body
            userResponseBody.setAddress(seller.getAddress());
            userResponseBody.setAge(seller.getAge());
            userResponseBody.setName(seller.getName());
            userResponseBody.setEmail(seller.getEmail());
            userResponseBody.setPhoneNumber(seller.getPhoneNumber());
            // Set userResponseBody inside product response body
            productResponseBody.setUserResponseBody(userResponseBody);
            // For a specific product product response body is built we need to add product response body in list

            productResponseBodyList.add(productResponseBody);
        }

        return productResponseBodyList;
    }
}
