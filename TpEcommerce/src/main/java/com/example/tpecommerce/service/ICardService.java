package com.example.tpecommerce.service;

import dto.CardDTO;

import java.util.List;

public interface ICardService {
    CardDTO create(CardDTO cardDTO);

    CardDTO findById(Integer id);

    CardDTO update(Integer id, CardDTO cardDTO);

    CardDTO deleteById(Integer id);

    List<CardDTO> findAll();


}
