package com.example.tpecommerce.controller;


import com.example.tpecommerce.service.impl.ProductServiceImpl;
import dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping("/save-product")
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDto) {
        System.out.println(productDto);
//        String selectedCategoryValue = String.valueOf(productDto.getCategory());
        productService.create(productDto);
        return ResponseEntity.ok("Product created");
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProduct(@RequestParam("search") String search) {
        List<ProductDTO> products = productService.searchProduct(search);
        if (products == null || products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.findAll();
        if (products == null || products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Integer id) {
        if (productService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteById(id);
        return ResponseEntity.ok("Product deleted");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") Integer id) {
        if (productService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.findById(id).toString());
    }

    @GetMapping("/filter/{category}")
    public ResponseEntity<String> filterByCategory(@PathVariable("category") String category) {
        if (productService.filterByCategory(category) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.filterByCategory(category).toString());
    }

}
