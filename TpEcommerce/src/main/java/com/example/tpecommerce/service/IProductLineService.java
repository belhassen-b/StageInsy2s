package com.example.tpecommerce.service;

import dto.ProductLineDTO;

import java.util.List;

public interface IProductLineService {

    ProductLineDTO create(ProductLineDTO productLineDTO);

    ProductLineDTO findById(Integer id);

    ProductLineDTO update(Integer id, ProductLineDTO productLineDTO);

    ProductLineDTO deleteById(Integer id);

  List<ProductLineDTO> findAll();

}
