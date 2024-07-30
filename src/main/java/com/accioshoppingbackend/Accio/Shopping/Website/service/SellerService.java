package com.accioshoppingbackend.Accio.Shopping.Website.service;

import com.accioshoppingbackend.Accio.Shopping.Website.exception.AcessNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.exception.InvalidProductID;
import com.accioshoppingbackend.Accio.Shopping.Website.exception.UserNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.model.AppUser;
import com.accioshoppingbackend.Accio.Shopping.Website.model.Product;
import com.accioshoppingbackend.Accio.Shopping.Website.reponsebody.ProductResponseBody;
import com.accioshoppingbackend.Accio.Shopping.Website.reponsebody.UserResponseBody;
import com.accioshoppingbackend.Accio.Shopping.Website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SellerService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommonUserService userService;

    @Autowired
    private ProductService productService;

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

    public String removeProduct(UUID sellerID, UUID productID){
        Boolean isSeller = userService.isSeller(sellerID);
        if(isSeller == null){
            throw new UserNotFound(String.format(
                    "User with id %s does not exist",
                    sellerID.toString()
            ));
        }

        if(isSeller == false){
            throw new AcessNotFound(String.format(
                    "User with id %s does not have access to delete product",
                    sellerID.toString()
            ));
        }

        boolean validProduct = productService.validateProductID(productID);
        if(validProduct == false){
            throw new InvalidProductID(String.format(
                    "Product with id %s does not exist in system",
                    productID.toString()
            ));
        }

        // We need to validate is this product belongs to the user whoose sellerID is passed

        Product product = productService.getProductByID(productID);
        System.out.println(product);
        AppUser owner = product.getSeller();

        if(!owner.getId().equals(sellerID)){
            throw new AcessNotFound(String.format(
                    "User with name %s does not have access to remove product %s",
                    owner.getName(),
                    product.getProductName()
            ));
        }


        productService.removeProduct(product);

        return String.format(
                "Seller with name %s removed product with id %s",
                owner.getName(),
                product.getProductName()
        );
    }
    public List<ProductResponseBody> getAllProductBySellerID(UUID sellerID){
        List<Product> products = productRepository.getAllProductBySellerID(sellerID);
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


    public Double getProductRatingByID(UUID productID, UUID sellerID){
        Boolean isSeller = userService.isSeller(sellerID);
        if(isSeller == null){
            throw new UserNotFound(
                    String.format("Seller wih id %s does not exist",
                            sellerID.toString())
            );
        }
        if(isSeller == false){
            throw new AcessNotFound(String.format(
                    "User with id %s does not have access to delete product",
                    sellerID.toString()
            ));
        }
        boolean validProduct = productService.validateProductID(productID);
        if(validProduct == false){
            throw new InvalidProductID(String.format(
                    "Product with id %s does not exist in system",
                    productID.toString()
            ));
        }
        Product product = productService.getProductByID(productID);
        AppUser owner = product.getSeller();
        if(!owner.getId().equals(sellerID)){
            throw new AcessNotFound(String.format(
                    "User with name %s does not have access to remove product %s",
                    owner.getName(),
                    product.getProductName()
            ));
        }

        return product.getRating();
    }

    public Integer getProductTotalQunatitySoldByID(UUID productID, UUID sellerID){
        Boolean isSeller = userService.isSeller(sellerID);
        if(isSeller == null){
            throw new UserNotFound(
                    String.format("Seller wih id %s does not exist",
                            sellerID.toString())
            );
        }
        if(isSeller == false){
            throw new AcessNotFound(String.format(
                    "User with id %s does not have access to delete product",
                    sellerID.toString()
            ));
        }
        boolean validProduct = productService.validateProductID(productID);
        if(validProduct == false){
            throw new InvalidProductID(String.format(
                    "Product with id %s does not exist in system",
                    productID.toString()
            ));
        }
        Product product = productService.getProductByID(productID);
        AppUser owner = product.getSeller();
        if(!owner.getId().equals(sellerID)){
            throw new AcessNotFound(String.format(
                    "User with name %s does not have access to remove product %s",
                    owner.getName(),
                    product.getProductName()
            ));
        }

        return product.getTotalSoldQuantity();
    }
}
