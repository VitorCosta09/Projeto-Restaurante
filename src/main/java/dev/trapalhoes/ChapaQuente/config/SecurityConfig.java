
package dev.trapalhoes.ChapaQuente.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new ChangeSessionIdAuthenticationStrategy();
    }

    @Bean
    CsrfTokenRepository csrfTokenRepository() {
        return new HttpSessionCsrfTokenRepository();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(
            @Value("${FRONTEND_ORIGIN}") String frontendOrigin) {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(frontendOrigin));
        configuration.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"
        ));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            SecurityContextRepository securityContextRepository,
            CsrfTokenRepository csrfTokenRepository) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource("http://localhost:3000")))

                .csrf(csrf -> csrf
                        .csrfTokenRepository(csrfTokenRepository)
                )

                .securityContext(context -> context
                        .securityContextRepository(securityContextRepository)
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers(HttpMethod.GET,
                                "/", "/index.html", "/css/**",
                                "/js/**", "/images/**", "/favicon.ico",
                                "/error", "/csrf"
                        ).permitAll()

                        .requestMatchers(HttpMethod.POST, "/login", "/cliente",
                                "/verificacao/verificado"
                        ).permitAll()

                        .requestMatchers(HttpMethod.GET, "/produto", "/produto/**")
                        .permitAll()

                        .requestMatchers(HttpMethod.GET, "/verificacao/usuario")
                        .permitAll()

                        .requestMatchers(HttpMethod.GET, "/verificacao/admin-status")
                        .authenticated()

                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,
                                "/cliente", "/cliente/**",
                                "/pedido", "/pedido/**",
                                "/reserva", "/reserva/**",
                                "/item-pedido", "/item-pedido/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST,
                                "/cliente/pontos/**",
                                "/produto", "/produto/**",
                                "/ingrediente", "/ingrediente/**",
                                "/mesa", "/mesa/**",
                                "/pedido", "/pedido/**",
                                "/item-pedido", "/item-pedido/**",
                                "/item-pedido-ingrediente", "/item-pedido-ingrediente/**",
                                "/reserva", "/reserva/**"
                        ).hasRole("ADMIN")

                        .requestMatchers("/ingrediente", "/ingrediente/**",
                                "/mesa", "/mesa/**",
                                "/item-pedido-ingrediente", "/item-pedido-ingrediente/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")

                        .anyRequest().denyAll()
                )

                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpStatus.OK.value());
                            response.setContentType("application/json");
                            response.getWriter().write("{\"autenticado\":false}");
                        })
                        .permitAll()
                )

                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            response.setContentType("application/json");
                            response.getWriter().write("{\"erro\":\"Não autenticado\"}");
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.setContentType("application/json");
                            response.getWriter().write("{\"erro\":\"Acesso negado\"}");
                        })
                );

        return http.build();
    }
}
