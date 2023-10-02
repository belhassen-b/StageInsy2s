package com.example.tpecommerce.service.impl;


import com.example.tpecommerce.model.Client;
import com.example.tpecommerce.model.Token;
import com.example.tpecommerce.model.enumeration.TokenType;
import com.example.tpecommerce.repository.ITokenRepository;
import com.example.tpecommerce.service.ITokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ITokenServiceImpl implements ITokenService {
    private final ITokenRepository tokenRepository;

    @Override
    public void saveClientToken(Client client, String jwtToken) {
        var token = Token.builder()
                .client(client)
                .userToken(jwtToken)
                .tokenType(TokenType.BEARER)
                .isExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(token);
    }

    @Override
    public void revokeAllClientTokens(Client client) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(client.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
