package com.accioshoppingbackend.Accio.Shopping.Website.controller;

import com.accioshoppingbackend.Accio.Shopping.Website.reponsebody.ProductResponseBody;
import com.accioshoppingbackend.Accio.Shopping.Website.requestbody.OrderProductRequestBody;
import com.accioshoppingbackend.Accio.Shopping.Website.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/buyer")
public class BuyerController {

    @Autowired
    BuyerService buyerService;

    // 200 -> Successfull
    // 201 -> POST and PUT

    // 400 It means problem is at client side -> Bad Request
    // 401 -> UnAuthorized
    // 404 -> Not Found

    // 500 -> Problem is with server

    // page 3 -> 21 to 30
    @GetMapping("/product/all")
    public ResponseEntity getAllProducts(@RequestParam(required = false) String filter,
                                         @RequestParam(required = false) String sort,
                                         @RequestParam int page){
            if(filter == null && sort == null){
                // We will randomly return 10 products from db
                List<ProductResponseBody> products = buyerService.getProducts();
                return new ResponseEntity(products,
                        HttpStatus.OK);
            }else if(filter != null && sort == null){
                // We are supporting only one filter i.e. maximum rating or minimum rating
                List<ProductResponseBody> products = buyerService.getProductByRating(filter);
                return new ResponseEntity(products,
                        HttpStatus.OK);
            }else if(sort != null && filter == null){
                // We are getting sort
                // We are going to return top 10 products which is having the maximum price in complete table
                // if sorting criteria is desc
                // if sorting criteria is asc
                // we will return 10 products which is having minimum price
                List<ProductResponseBody> products = buyerService.getProduct(sort);
                return new ResponseEntity(products,
                        HttpStatus.OK);
            }else{
                // filter-criteria -> if filter criteria is minimum rating
                // and sort -> asc, desc
                // we will return lmit 10 product in term of price(asc, desc) which is having minimum rating
                List<ProductResponseBody> products = buyerService.getProduct(filter, sort);
                return new ResponseEntity(products,
                        HttpStatus.OK);
            }
    }

    @PostMapping("/product/buy")
    public void placeOrder(@RequestParam UUID buyerID,
                           @RequestBody List<OrderProductRequestBody> products){
        // service layer

        try{
            B
        }
    }
}
