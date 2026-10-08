package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Prato;
import dev.trapalhoes.ChapaQuente.service.PratoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prato")
public class PratoController {
    private PratoService pratoService;

    public PratoController(PratoService pratoService) {
        this.pratoService = pratoService;
    }

    @GetMapping
    public List<Prato> getAll(){return pratoService.getAll();}

    @GetMapping
    public Optional<Prato> get(Integer id){return pratoService.get(id);}

    @PostMapping
    public Prato create(@RequestBody Prato prato){return pratoService.save(prato);}
}
