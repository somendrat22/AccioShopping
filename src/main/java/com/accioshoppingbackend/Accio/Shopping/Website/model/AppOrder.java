package com.accioshoppingbackend.Accio.Shopping.Website.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    AppUser buyer;
    @OneToMany
    List<Product> products;
    @Column(nullable = false)
    int totalQuantity;
    @Column(nullable = false)
    int totalPrice;
}
