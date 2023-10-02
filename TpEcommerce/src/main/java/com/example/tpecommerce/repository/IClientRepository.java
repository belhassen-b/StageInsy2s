package com.example.tpecommerce.repository;

import com.example.tpecommerce.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface IClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByUsernameOrEmail(String username, String email);
}
