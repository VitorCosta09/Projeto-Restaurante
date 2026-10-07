package dev.trapalhoes.ChapaQuente.service;

import dev.trapalhoes.ChapaQuente.model.Cliente;
import dev.trapalhoes.ChapaQuente.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService  {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAll(){return clienteRepository.findAll();}
    
    public Optional<Cliente> get(Integer id) {return clienteRepository.findById(id);}

    public Cliente save (Cliente cliente) {return clienteRepository.save(cliente);}

    public void delete(Integer id){clienteRepository.deleteById(id);}
}
