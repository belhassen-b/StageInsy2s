package com.example.tpecommerce.security;

import com.example.tpecommerce.config.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
// Classe de configuration pour la sécurité web
public class SecurityConfiguration {

    // Injection des dépendances
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    // Liste des chemins autorisés sans authentification
    private static final String[] AUTH_WHITELIST = {
            "/api/auth/**",
            "/api/products/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
    };

    @Bean
    // Configuration de la sécurité web
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Désactivation de la protection CSRF
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Désactivation de la configuration CORS
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(AUTH_WHITELIST) // Autorisation des requêtes dans la liste blanche
                                .permitAll() // Toutes les requêtes de la liste blanche sont permises
                                .anyRequest() // Pour toutes les autres requêtes
                                .authenticated()) // Il faut être authentifié
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Politique de création de session : aucune session n'est créée
                )
                .authenticationProvider(authenticationProvider) // Fournisseur d'authentification
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // Ajout du filtre JWT avant le filtre d'authentification standard
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout") // URL de déconnexion
                        .logoutSuccessUrl("/api/auth/logout") // URL après déconnexion réussie
                        .invalidateHttpSession(true) // Invalider la session HTTP lors de la déconnexion
                        .addLogoutHandler(logoutHandler) // Gestionnaire de déconnexion
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())); // À la déconnexion réussie, on vide le contexte de sécurité

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfigurationSource = new CorsConfiguration();
        corsConfigurationSource.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfigurationSource.setAllowedHeaders(List.of(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT

        ));
        corsConfigurationSource.setAllowedMethods(
                List.of(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name()
                )
        );
        corsConfigurationSource.setAllowCredentials(true);
        corsConfigurationSource.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfigurationSource);
        return source;
    }
}
