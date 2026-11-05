
package dev.trapalhoes.ChapaQuente.service;

import dev.trapalhoes.ChapaQuente.model.Cliente;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class ClienteUserDetailsService implements UserDetailsService {

    private final EntityManager entityManager;
    private final String adminEmail;
    private final String adminPasswordHash;

    public ClienteUserDetailsService(
            EntityManager entityManager,
            @Value("${ADMIN_EMAIL:}") String adminEmail,
            @Value("${ADMIN_PASSWORD_HASH:}") String adminPasswordHash) {

        this.entityManager = entityManager;
        this.adminEmail = adminEmail;
        this.adminPasswordHash = adminPasswordHash;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (email == null || email.isBlank()) {
            throw new UsernameNotFoundException("Credenciais inválidas");
        }

        String normalized = email.trim().toLowerCase(Locale.ROOT);

        if (!adminEmail.isBlank()
                && !adminPasswordHash.isBlank()
                && adminEmail.equalsIgnoreCase(normalized)) {

            if (!adminPasswordHash.startsWith("$2")) {
                throw new UsernameNotFoundException("Hash BCrypt do administrador inválido");
            }

            return User.withUsername(normalized)
                    .password(adminPasswordHash)
                    .roles("ADMIN")
                    .build();
        }

        var encontrados = entityManager.createQuery(
                        "select c from Cliente c where lower(c.email) = :email",
                        Cliente.class)
                .setParameter("email", normalized)
                .setMaxResults(2)
                .getResultList();

        if (encontrados.size() != 1) {
            throw new UsernameNotFoundException("Credenciais inválidas");
        }

        Cliente cliente = encontrados.get(0);

        return User.withUsername(normalized)
                .password(cliente.getSenha())
                .roles("CLIENTE")
                .build();
    }
}
