package com.example.tpecommerce.repository;

import com.example.tpecommerce.model.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductLineRepository extends JpaRepository<ProductLine, Integer> {
}
