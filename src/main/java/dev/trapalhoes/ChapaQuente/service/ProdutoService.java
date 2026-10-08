package dev.trapalhoes.ChapaQuente.service;

import dev.trapalhoes.ChapaQuente.model.Produto;
import dev.trapalhoes.ChapaQuente.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getAll(){return produtoRepository.findAll();}

    public Optional<Produto> get(Integer id){return produtoRepository.findById(id);}

    public Produto save(Produto produto){return produtoRepository.save(produto);}

    public void delete(Integer id){produtoRepository.deleteById(id);}
}
