package com.accioshoppingbackend.Accio.Shopping.Website.service;

import com.accioshoppingbackend.Accio.Shopping.Website.model.Product;
import com.accioshoppingbackend.Accio.Shopping.Website.reponsebody.ProductResponseBody;
import com.accioshoppingbackend.Accio.Shopping.Website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductUtilService productUtilService;

    public List<ProductResponseBody> getProduct(String filter, String sort){
        if(filter.equals("MAXIMUM-RATING")){
            Double maxRating = productRepository.getMaxRating();
            if(sort.equals("asc")){
                List<Product> products = productRepository.getProductByMaximumRatingAndAsc(maxRating);
                return productUtilService.convertProductToProductResponse(products);

            }else{
                List<Product> products = productRepository.getProductByMaximumRatingAndDesc(maxRating);
                return productUtilService.convertProductToProductResponse(products);
            }
        }else{
            Double minimumRating = productRepository.getMinRating();
            if(sort.equals("asc")){
                List<Product> products = productRepository.getProductByMinimumRatingAndAsc(minimumRating);
                return productUtilService.convertProductToProductResponse(products);
            }else{
                List<Product> products = productRepository.getProductByMinimumRatingAndDesc(minimumRating);
                return productUtilService.convertProductToProductResponse(products);
            }
        }
    }

    public  List<ProductResponseBody> getProduct(String sort){
        if(sort.equals("asc")){
            List<Product> products = productRepository.getProductInAsc();
            return productUtilService.convertProductToProductResponse(products);
        }else{
            List<Product> products = productRepository.getProductInDesc();
            return productUtilService.convertProductToProductResponse(products);
        }
    }

    public  List<ProductResponseBody> getProductByRating(String rating){
        if(rating.equals("MAXIMUM-RATING")){
            Double maxRating = productRepository.getMaxRating();
            List<Product> products = productRepository.getProductByRating(maxRating);
            return productUtilService.convertProductToProductResponse(products);
        }else{
            Double minRating = productRepository.getMinRating();
            List<Product> products = productRepository.getProductByRating(minRating);
            return productUtilService.convertProductToProductResponse(products);
        }
    }

    public List<ProductResponseBody> getProducts(){
        List<Product> products = productRepository.getProducts();
        return productUtilService.convertProductToProductResponse(products);
    }
}
