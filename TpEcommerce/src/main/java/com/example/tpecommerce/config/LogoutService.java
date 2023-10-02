package com.example.tpecommerce.config;


import com.example.tpecommerce.repository.ITokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// Service pour gérer la déconnexion d'un utilisateur, implémente LogoutHandler
public class LogoutService implements LogoutHandler {

    // Injection de la dépendance de l'interface ITokenRepository
    private final ITokenRepository tokenRepository;

    // Implémentation de la méthode de déconnexion
    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        // Récupération du header "Authorization" de la requête
        final String authHeader = request.getHeader("Authorization");
        final String jwt;

        // Si le header est manquant ou n'est pas un token JWT, on retourne directement
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        // Extraction du token JWT depuis le header
        jwt = authHeader.substring(7);

        // Recherche du token dans la base de données
        var storedToken = tokenRepository.findByUserToken(jwt)
                .orElse(null);

        // Si le token est trouvé, on le marque comme expiré et révoqué,
        // puis on le sauvegarde dans la base de données
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);

            // On vide ensuite le contexte de sécurité
            SecurityContextHolder.clearContext();
        }
    }
}
