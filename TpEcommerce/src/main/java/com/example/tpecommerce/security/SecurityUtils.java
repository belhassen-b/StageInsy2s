package com.example.tpecommerce.security;

import com.example.tpecommerce.error.exception.UsernameNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

// Classe utilitaire pour la sécurité
public class SecurityUtils {

    // Constructeur privé pour empêcher l'instanciation de la classe
    private SecurityUtils() {}


    // Méthode pour obtenir le sous-objet utilisateur courant sous forme d'Optional
    public static Optional<String> getCurrentUserSubOptional() {
        // Récupération du contexte de sécurité
        SecurityContext securityContext = SecurityContextHolder.getContext();
        // Extraction du nom d'utilisateur depuis le principal de l'authentification
        return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));
    }

    // Méthode pour obtenir le sous-objet utilisateur courant
    public static String getCurrentUserSub() {
        // Récupération du contexte de sécurité
        SecurityContext securityContext = SecurityContextHolder.getContext();
        // Extraction du nom d'utilisateur depuis le principal de l'authentification, ou lève une exception si non trouvée
        return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()))
                .orElseThrow(UsernameNotFoundException::new);
    }


    // Méthode privée pour extraire le principal depuis l'authentification
    private static String extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails user) {
            // Si le principal est une instance de UserDetails, retourne le nom d'utilisateur
            return user.getUsername();
        } else if (authentication.getPrincipal() instanceof String username) {
            // Si le principal est une chaîne, retourne cette chaîne
            return username;
        }
        return null;
    }
}
