package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Ingrediente;
import dev.trapalhoes.ChapaQuente.service.IngredienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingrediente")
public class IngredienteController {
    private IngredienteService ingredienteService;

    public IngredienteController(IngredienteService ingredienteService) {
        this.ingredienteService = ingredienteService;
    }

    @GetMapping
    public List<Ingrediente> getAll(){return ingredienteService.getAll();}

    @GetMapping
    public Optional<Ingrediente> get(Integer id){return ingredienteService.get(id);}

    @PostMapping
    public Ingrediente create(@RequestBody Ingrediente ingrediente){return ingredienteService.save(ingrediente);}
}
