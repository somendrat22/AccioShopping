package com.accioshoppingbackend.Accio.Shopping.Website.repository;

import com.accioshoppingbackend.Accio.Shopping.Website.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
