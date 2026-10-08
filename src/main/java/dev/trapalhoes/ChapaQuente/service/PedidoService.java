package dev.trapalhoes.ChapaQuente.service;

import dev.trapalhoes.ChapaQuente.model.Pedido;
import dev.trapalhoes.ChapaQuente.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> getAll(){return pedidoRepository.findAll();}

    public Optional<Pedido> get(Integer id){return pedidoRepository.findById(id);}

    public Pedido save(Pedido pedido){return pedidoRepository.save(pedido);}

    public void delete(Integer id){pedidoRepository.deleteById(id);}
}
