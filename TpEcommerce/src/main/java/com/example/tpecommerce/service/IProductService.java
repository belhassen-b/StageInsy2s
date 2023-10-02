package com.example.tpecommerce.service;

import dto.ProductDTO;

import java.util.List;



public interface IProductService {

    ProductDTO create(ProductDTO productDTO);

    ProductDTO findById(Integer id);

    ProductDTO update(Integer id, ProductDTO productDTO);

    ProductDTO deleteById(Integer id);

    ProductDTO filterByCategory(String category);

    List<ProductDTO> findAll();

    List<ProductDTO> search(String search);


    List<ProductDTO> searchProduct(String search);
}
