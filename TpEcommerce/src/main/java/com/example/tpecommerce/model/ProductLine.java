package com.example.tpecommerce.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ProductLine {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;
    private int price;

    @ManyToOne (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    private Double total;

    @ManyToOne (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
