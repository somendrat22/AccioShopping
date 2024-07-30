package com.accioshoppingbackend.Accio.Shopping.Website.service;

import com.accioshoppingbackend.Accio.Shopping.Website.model.Product;
import com.accioshoppingbackend.Accio.Shopping.Website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product getProductByID(UUID productID){
        return productRepository.findById(productID).orElse(null);
    }

    public boolean validateProductID(UUID productID){
        Product product = getProductByID(productID);
        if(product == null){
            return false;
        }
        return true;
    }

    public void removeProduct(Product product){
        productRepository.delete(product);
    }
}
