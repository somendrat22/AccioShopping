package com.accioshoppingbackend.Accio.Shopping.Website.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String productName;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int quantity;
    private double rating;
    @Column(nullable = false)
    private int totalSoldQuantity;
    @Column(nullable = false)
    private String category;
    @ManyToOne
    AppUser seller;
}
