package com.example.tpecommerce.repository;

import com.example.tpecommerce.model.Product;
import com.example.tpecommerce.model.enumeration.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByCategory(Category category);


//    List<Product> search(String search);
    List<Product> findByNameContaining(String search);

}
