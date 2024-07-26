package com.accioshoppingbackend.Accio.Shopping.Website.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Laptop {
    @Id
    int id;
    @Column(unique = true)
    String laptopName;
    @ManyToOne
    Student user;
}
