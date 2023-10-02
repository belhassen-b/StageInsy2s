package com.example.tpecommerce.repository;

import com.example.tpecommerce.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICardRepository extends JpaRepository<Card, Integer> {

}
