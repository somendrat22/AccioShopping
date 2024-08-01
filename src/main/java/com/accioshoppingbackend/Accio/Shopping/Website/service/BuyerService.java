package com.accioshoppingbackend.Accio.Shopping.Website.service;

import com.accioshoppingbackend.Accio.Shopping.Website.exception.AcessNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.exception.InvalidOperationException;
import com.accioshoppingbackend.Accio.Shopping.Website.exception.ProductNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.exception.UserNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.model.AppUser;
import com.accioshoppingbackend.Accio.Shopping.Website.model.Product;
import com.accioshoppingbackend.Accio.Shopping.Website.reponsebody.BillResponseBody;
import com.accioshoppingbackend.Accio.Shopping.Website.reponsebody.ProductResponseBody;
import com.accioshoppingbackend.Accio.Shopping.Website.reponsebody.SpecificProductOrderDetail;
import com.accioshoppingbackend.Accio.Shopping.Website.repository.ProductRepository;
import com.accioshoppingbackend.Accio.Shopping.Website.requestbody.OrderProductRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BuyerService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductUtilService productUtilService;

    @Autowired
    CommonUserService commonUserService;

    public List<ProductResponseBody> getProduct(String filter, String sort) {
        if (filter.equals("MAXIMUM-RATING")) {
            Double maxRating = productRepository.getMaxRating();
            if (sort.equals("asc")) {
                List<Product> products = productRepository.getProductByMaximumRatingAndAsc(maxRating);
                return productUtilService.convertProductToProductResponse(products);

            } else {
                List<Product> products = productRepository.getProductByMaximumRatingAndDesc(maxRating);
                return productUtilService.convertProductToProductResponse(products);
            }
        } else {
            Double minimumRating = productRepository.getMinRating();
            if (sort.equals("asc")) {
                List<Product> products = productRepository.getProductByMinimumRatingAndAsc(minimumRating);
                return productUtilService.convertProductToProductResponse(products);
            } else {
                List<Product> products = productRepository.getProductByMinimumRatingAndDesc(minimumRating);
                return productUtilService.convertProductToProductResponse(products);
            }
        }
    }

    public List<ProductResponseBody> getProduct(String sort) {
        if (sort.equals("asc")) {
            List<Product> products = productRepository.getProductInAsc();
            return productUtilService.convertProductToProductResponse(products);
        } else {
            List<Product> products = productRepository.getProductInDesc();
            return productUtilService.convertProductToProductResponse(products);
        }
    }

    public List<ProductResponseBody> getProductByRating(String rating) {
        if (rating.equals("MAXIMUM-RATING")) {
            Double maxRating = productRepository.getMaxRating();
            List<Product> products = productRepository.getProductByRating(maxRating);
            return productUtilService.convertProductToProductResponse(products);
        } else {
            Double minRating = productRepository.getMinRating();
            List<Product> products = productRepository.getProductByRating(minRating);
            return productUtilService.convertProductToProductResponse(products);
        }
    }

    public List<ProductResponseBody> getProducts() {
        List<Product> products = productRepository.getProducts();
        return productUtilService.convertProductToProductResponse(products);
    }


    public BillResponseBody purchaseProduct(UUID buyerID, List<OrderProductRequestBody> orderProducts){

        // Validate userID exists in our system or not

        AppUser buyer = commonUserService.getUserById(buyerID);
        if(buyer == null){
            throw new UserNotFound(
                    String.format(
                            "User with id %s does not exist",
                            buyerID.toString()
                    )
            );
        }

        if(!buyer.getUserType().equals("BUYER")){
            throw new AcessNotFound(String.format("User with name %s does not have access to purchase product",
                    buyer.getName()
                    ));
        }

        BillResponseBody billResponseBody = new BillResponseBody();
        int totalAmount = 0;
        billResponseBody.setProducts(new ArrayList<>());
        for(OrderProductRequestBody orderProduct : orderProducts){
            String productName = orderProduct.getProductName();
            int productQuantity = orderProduct.getQuantity();
            Product product = productRepository.findByProductName(productName);
            if(product == null){
                throw new ProductNotFound(
                        String.format("Product with name %s does not exist",
                                product.getProductName())
                );
            }

            if(product.getQuantity() < productQuantity){
                throw new InvalidOperationException(
                        String.format("Product with name %s does not enough quantity in system",
                                product.getProductName()
                                )
                );
            }

            int totalSoldQuantity = product.getTotalSoldQuantity() + productQuantity;
            int quantityLeft = product.getQuantity() - productQuantity;
            productRepository.updateQuantity(quantityLeft, buyerID, totalSoldQuantity);

            SpecificProductOrderDetail productOrderDetail = new SpecificProductOrderDetail();
            productOrderDetail.setProductID(product.getId());
            productOrderDetail.setUnitPrice(product.getPrice());
            productOrderDetail.setProductName(productName);
            productOrderDetail.setTotalPurchasedPrice(productQuantity*product.getPrice());
            totalAmount += productQuantity*product.getPrice();
            billResponseBody.getProducts().add(productOrderDetail);
        }

        billResponseBody.setTotalAmount(totalAmount);
        return billResponseBody;

    }
}