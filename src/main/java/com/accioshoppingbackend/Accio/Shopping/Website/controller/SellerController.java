package com.accioshoppingbackend.Accio.Shopping.Website.controller;

import com.accioshoppingbackend.Accio.Shopping.Website.model.Product;
import com.accioshoppingbackend.Accio.Shopping.Website.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/product/register")
    public void addProduct(@RequestBody Product product, @RequestParam UUID sellerID){
        sellerService.addProduct(product, sellerID);
    }
}
