package com.example.tpecommerce.controller;


import com.example.tpecommerce.service.impl.ProductLineServiceImpl;
import dto.ProductLineDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/productLines")
public class ProductLineController {

    private final ProductLineServiceImpl productLineService;


    @PostMapping("/save-productLine")
    public ResponseEntity<String> createProductLine(@RequestBody ProductLineDTO productLineDto) {
        System.out.println(productLineDto);
        productLineService.create(productLineDto);
        return ResponseEntity.ok("ProductLine created");
    }



}
