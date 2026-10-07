package dev.trapalhoes.ChapaQuente.service;

import dev.trapalhoes.ChapaQuente.model.Ingrediente;
import dev.trapalhoes.ChapaQuente.repository.IngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {
    private final IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public List<Ingrediente> getAll(){return ingredienteRepository.findAll();}

    public Optional<Ingrediente> get(Integer id) {return ingredienteRepository.findById(id);}

    public Ingrediente save (Ingrediente ingrediente){return ingredienteRepository.save(ingrediente);}

    public void delete(Integer id) {ingredienteRepository.deleteById(id);}
}
