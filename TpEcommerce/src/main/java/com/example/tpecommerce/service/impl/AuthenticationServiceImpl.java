package com.example.tpecommerce.service.impl;


import com.example.tpecommerce.config.JwtService;
import com.example.tpecommerce.model.Client;
import com.example.tpecommerce.model.Role;
import com.example.tpecommerce.repository.IClientRepository;
import com.example.tpecommerce.service.IAuthenticationService;
import com.example.tpecommerce.service.ITokenService;
import dto.request.AuthenticationRequest;
import dto.request.RegisterRequest;
import dto.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
// Classe de service pour l'authentification, implémente l'interface IAuthenticationService
public class AuthenticationServiceImpl implements IAuthenticationService {
    // Injection des dépendances via le constructeur
    private final IClientRepository clientRepository;
    private final ITokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    // Méthode pour l'enregistrement d'un nouvel utilisateur
    public AuthenticationResponse register(RegisterRequest request) {
        // Création de l'objet utilisateur à partir des données de la requête
        var client = Client.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))  // Hachage du mot de passe
                .role(new Role("ROLE_USER"))
                .build();
        // Sauvegarde de l'utilisateur dans la base de données
        var savedUser = clientRepository.save(client);
        // Génération d'un token JWT pour l'utilisateur
        var jwtToken = jwtService.generateToken(client);
        // Sauvegarde du token pour l'utilisateur
        tokenService.saveClientToken(savedUser, jwtToken);
        // Retour de la réponse contenant le token d'accès
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    @Override
    // Méthode pour l'authentification d'un utilisateur existant
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Authentification de l'utilisateur
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(),
                        request.getPassword()
                )
        );
        // Récupération de l'utilisateur à partir de son email
        var user = clientRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail())
                .orElseThrow();
        // Génération d'un nouveau token JWT pour l'utilisateur
        var jwtToken = jwtService.generateToken(user);
        // Révocation de tous les tokens précédents de l'utilisateur
        tokenService.revokeAllClientTokens(user);
        // Sauvegarde du nouveau token pour l'utilisateur
        tokenService.saveClientToken(user, jwtToken);
        // Retour de la réponse contenant le nouveau token d'accès
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

}
