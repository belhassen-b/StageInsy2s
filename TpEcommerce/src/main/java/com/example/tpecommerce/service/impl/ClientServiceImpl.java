package com.example.tpecommerce.service.impl;


import com.example.tpecommerce.model.Client;
import com.example.tpecommerce.repository.IClientRepository;
import com.example.tpecommerce.service.IClientService;
import dto.ClientDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {


    private final IClientRepository clientRepository;

    private final ModelMapper modelMapper;

    @Override
    public Optional<ClientDTO> findByUsernameOrEmail(String username, String email) {
        return clientRepository.findByUsernameOrEmail(username, email)
                .map(client -> modelMapper.map(client, ClientDTO.class));
    }

    @Override
    public ClientDTO create(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        return modelMapper.map(clientRepository.save(client), ClientDTO.class);
    }

    @Override
    public ClientDTO findById(Integer id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Client not found")
        );
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public ClientDTO update(Integer id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Client not found")
        );
        Client client1 = modelMapper.map(clientDTO, Client.class);
        client.setFirstname(client1.getFirstname());
        client.setLastname(client1.getLastname());
        client.setUsername(client1.getUsername());
        client.setEmail(client1.getEmail());
        client.setPassword(client1.getPassword());
        return modelMapper.map(clientRepository.save(client), ClientDTO.class);
    }

    @Override
    public ClientDTO deleteById(Integer id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Client not found")
        );
        clientRepository.deleteById(id);
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public List<ClientDTO> findAll() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> list = modelMapper.map(clients, new TypeToken<List<ClientDTO>>() {
        }.getType());
        return list;
    }

}
