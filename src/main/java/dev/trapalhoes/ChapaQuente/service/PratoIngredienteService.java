package dev.trapalhoes.ChapaQuente.service;

import dev.trapalhoes.ChapaQuente.model.PratoIngrediente;
import dev.trapalhoes.ChapaQuente.repository.PratoIngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PratoIngredienteService {
    private final PratoIngredienteRepository pratoIngredienteRepository;

    public PratoIngredienteService(PratoIngredienteRepository pratoIngredienteRepository) {
        this.pratoIngredienteRepository = pratoIngredienteRepository;
    }

    public List<PratoIngrediente> getAll(){return pratoIngredienteRepository.findAll();}

    public Optional<PratoIngrediente> get(Integer id){return pratoIngredienteRepository.findById(id);}

    public PratoIngrediente save(PratoIngrediente pratoIngrediente){return pratoIngredienteRepository.save(pratoIngrediente);}

    public void delete(Integer id){pratoIngredienteRepository.deleteById(id);}
}
