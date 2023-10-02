package com.example.tpecommerce.service.impl;


import com.example.tpecommerce.model.ProductLine;
import com.example.tpecommerce.repository.IProductLineRepository;
import com.example.tpecommerce.service.IProductLineService;
import dto.ProductLineDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductLineServiceImpl implements IProductLineService {

    private final ModelMapper modelMapper = new ModelMapper();

    private IProductLineRepository productLineRepository;




    @Override
    public ProductLineDTO create(ProductLineDTO productLineDTO) {
        ProductLine productLine = modelMapper.map(productLineDTO, ProductLine.class);
        return modelMapper.map(productLineRepository.save(productLine), ProductLineDTO.class);
    }

    @Override
    public ProductLineDTO findById(Integer id) {
        ProductLine productLine = productLineRepository.findById(id).orElseThrow(
                () -> new RuntimeException("ProductLine not found")
        );
        return modelMapper.map(productLine, ProductLineDTO.class);
    }

    @Override
    public ProductLineDTO update(Integer id, ProductLineDTO productLineDTO) {
        ProductLine productLine = productLineRepository.findById(id).orElseThrow(
                () -> new RuntimeException("ProductLine not found")
        );
        ProductLine productLine1 = modelMapper.map(productLineDTO, ProductLine.class);
        productLine.setQuantity(productLine1.getQuantity());
        productLine.setPrice(productLine1.getPrice());
        productLine.setProduct(productLine1.getProduct());
        return modelMapper.map(productLineRepository.save(productLine), ProductLineDTO.class);
    }

    @Override
    public ProductLineDTO deleteById(Integer id) {
        ProductLine productLine = productLineRepository.findById(id).orElseThrow(
                () -> new RuntimeException("ProductLine not found")
        );
        productLineRepository.deleteById(id);
        return modelMapper.map(productLine, ProductLineDTO.class);
    }

@Override
public List<ProductLineDTO> findAll() {
    List<ProductLine> productLines = productLineRepository.findAll();
    return modelMapper.map(productLines, List.class);
}
}