package com.example.tpecommerce.service;

import dto.request.AuthenticationRequest;
import dto.request.RegisterRequest;
import dto.response.AuthenticationResponse;

public interface IAuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
