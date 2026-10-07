package dev.trapalhoes.ChapaQuente.repository;

import dev.trapalhoes.ChapaQuente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
