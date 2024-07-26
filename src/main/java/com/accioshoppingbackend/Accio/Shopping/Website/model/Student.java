package com.accioshoppingbackend.Accio.Shopping.Website.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    int id;
    String name;
    String course;
    @OneToMany
    ArrayList<Laptop> laptopList;
}
