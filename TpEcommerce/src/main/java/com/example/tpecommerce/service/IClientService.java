package com.example.tpecommerce.service;

import dto.ClientDTO;

import java.util.List;
import java.util.Optional;



public interface IClientService {

    Optional<ClientDTO> findByUsernameOrEmail(String username, String email);
    
    ClientDTO create(ClientDTO clientDTO);

    ClientDTO findById(Integer id);

    ClientDTO update(Integer id, ClientDTO clientDTO);

    ClientDTO deleteById(Integer id);

    List<ClientDTO> findAll();

}
