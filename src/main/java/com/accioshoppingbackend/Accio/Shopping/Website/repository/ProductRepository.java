package com.accioshoppingbackend.Accio.Shopping.Website.repository;

import com.accioshoppingbackend.Accio.Shopping.Website.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(value = "select * from product where seller_id =:sellerID ",
        nativeQuery = true
    )
    public List<Product> getAllProductBySellerID(UUID sellerID);
}
