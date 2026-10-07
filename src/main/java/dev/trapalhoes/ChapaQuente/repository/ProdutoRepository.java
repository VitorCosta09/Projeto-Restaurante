package dev.trapalhoes.ChapaQuente.repository;

import dev.trapalhoes.ChapaQuente.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
