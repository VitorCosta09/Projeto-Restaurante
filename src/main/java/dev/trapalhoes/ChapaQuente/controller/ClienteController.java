package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Cliente;
import dev.trapalhoes.ChapaQuente.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getAll(){return clienteService.getAll();}

    @GetMapping
    public Optional<Cliente> get(Integer id){return clienteService.get(id);}

    @PostMapping()
    public Cliente create(@RequestBody Cliente cliente){return clienteService.save(cliente);}

}
