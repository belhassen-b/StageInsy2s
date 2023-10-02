package com.example.tpecommerce.service;


import com.example.tpecommerce.model.Client;

public interface ITokenService {
    void saveClientToken(Client client, String jwtToken);
    void revokeAllClientTokens(Client client);
}
