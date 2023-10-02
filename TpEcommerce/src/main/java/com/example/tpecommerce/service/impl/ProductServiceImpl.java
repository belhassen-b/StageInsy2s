package com.example.tpecommerce.service.impl;


import com.example.tpecommerce.model.Product;
import com.example.tpecommerce.repository.IProductRepository;
import com.example.tpecommerce.service.IProductService;
import dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {


    private final ModelMapper modelMapper = new ModelMapper();

    private final IProductRepository productRepository;

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        return modelMapper.map(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public ProductDTO findById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO update(Integer id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
        Product product1 = modelMapper.map(productDTO, Product.class);
        product.setName(product1.getName());
        product.setDescription(product1.getDescription());
        product.setPrice(product1.getPrice());
        product.setCategory(product1.getCategory());
        product.setStock(product1.getStock());
        product.setImage(product1.getImage());
        product.setProductLine(product1.getProductLine());
        return modelMapper.map(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public ProductDTO deleteById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
        productRepository.deleteById(id);
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO filterByCategory(String category) {
        Product product = productRepository.findByCategory(category).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> list = modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
        }.getType());

        return list;

    }

    @Override
    public List<ProductDTO> search(String searchTerm) {
        List<Product> products = productRepository.findByNameContaining(searchTerm);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchProduct(String search) {
        List<Product> products = productRepository.findByNameContaining(search);
        List<ProductDTO> list = modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
        }.getType());
        return list;
    }

}
