package com.accioshoppingbackend.Accio.Shopping.Website.repository;

import com.accioshoppingbackend.Accio.Shopping.Website.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepsoitory extends JpaRepository<AppUser, UUID> {

    public AppUser findByEmail(String email);
}
