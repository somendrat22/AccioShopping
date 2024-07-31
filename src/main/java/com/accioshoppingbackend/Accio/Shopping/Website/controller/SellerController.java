package com.accioshoppingbackend.Accio.Shopping.Website.controller;

import com.accioshoppingbackend.Accio.Shopping.Website.exception.AcessNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.exception.InvalidProductID;
import com.accioshoppingbackend.Accio.Shopping.Website.exception.UserNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.model.Product;
import com.accioshoppingbackend.Accio.Shopping.Website.reponsebody.ProductResponseBody;
import com.accioshoppingbackend.Accio.Shopping.Website.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @DeleteMapping("/product/remove")
    public String removeProduct(@RequestParam UUID sellerID, @RequestParam UUID productID){
        try{
            String result = sellerService.removeProduct(sellerID, productID);
            return result;
        }catch (UserNotFound userNotFound){
            return userNotFound.getMessage();
        }catch (InvalidProductID invalidProductID){
            return invalidProductID.getMessage();
        }catch (AcessNotFound acessNotFound){
            return acessNotFound.getMessage();
        }
    }

    @GetMapping("/product/all")
    public List<ProductResponseBody> getAllProducts(@RequestParam UUID sellerID){
        return sellerService.getAllProductBySellerID(sellerID);
    }

    // anayltics = "TOTALQUANTITYSOLD", "RATING"
    @GetMapping("/product/analytics")
    public ResponseEntity getAnalytics(@RequestParam UUID sellerID,
                                       @RequestParam UUID productID,
                                       @RequestParam String analytics){
        try{
            if(analytics.equals("TOTALQUNATITYSOLD")){
                return new ResponseEntity(sellerService.getProductTotalQunatitySoldByID(productID, sellerID), HttpStatus.OK);
            }else if(analytics.equals("RATING")){
                return new ResponseEntity(sellerService.getProductRatingByID(productID, sellerID), HttpStatus.OK);
            }else{
                return new ResponseEntity("Invalid Analytics", HttpStatus.OK);
            }
        }catch(UserNotFound userNotFound){
            return new ResponseEntity(userNotFound.getMessage(), HttpStatus.NOT_FOUND); // 404
        }catch (InvalidProductID invalidProductID){
            return new ResponseEntity(invalidProductID.getMessage(), HttpStatus.BAD_REQUEST); // 400
        }catch (AcessNotFound acessNotFound){
            return new ResponseEntity(acessNotFound.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }
}
