package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Produto;
    private Double valor;
    private String nome;
    private String tipo_Produto;
    private Integer quantidade;

    public Produto(Integer id_Produto, Double valor, String nome, String tipo_Produto, Integer quantidade) {
        this.id_Produto = id_Produto;
        this.valor = valor;
        this.nome = nome;
        this.tipo_Produto = tipo_Produto;
        this.quantidade = quantidade;
    }

    public Produto() {
    }

    public Integer getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(Integer id_Produto) {
        this.id_Produto = id_Produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipo_Produto() {
        return tipo_Produto;
    }

    public void setTipo_Produto(String tipo_Produto) {
        this.tipo_Produto = tipo_Produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
