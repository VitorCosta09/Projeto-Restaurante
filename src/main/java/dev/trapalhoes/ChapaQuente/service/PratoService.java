package dev.trapalhoes.ChapaQuente.service;

import dev.trapalhoes.ChapaQuente.model.Prato;
import dev.trapalhoes.ChapaQuente.repository.PratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PratoService {
    private final PratoRepository pratoRepository;

    public PratoService(PratoRepository pratoRepository) {
        this.pratoRepository = pratoRepository;
    }

    public List<Prato> getAll(){return pratoRepository.findAll();}

    public Optional<Prato> get(Integer id){return pratoRepository.findById(id);}

    public Prato save(Prato prato){return pratoRepository.save(prato);}

    public void delete(Integer id){pratoRepository.deleteById(id);}
}
