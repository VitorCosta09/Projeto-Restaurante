package dev.trapalhoes.ChapaQuente.repository;

import dev.trapalhoes.ChapaQuente.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
}
