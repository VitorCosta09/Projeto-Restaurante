package dev.trapalhoes.ChapaQuente.repository;

import dev.trapalhoes.ChapaQuente.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
