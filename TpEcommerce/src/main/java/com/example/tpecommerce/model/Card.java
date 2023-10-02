package com.example.tpecommerce.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Card {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;

    @ManyToOne (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private List<ProductLine> productLines;

}
