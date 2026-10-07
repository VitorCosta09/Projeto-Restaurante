package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PratoIngrediente")
public class PratoIngrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_PratoIngrediente;

    @ManyToOne
    @JoinColumn(name = "id_Prato")
    private Prato Prato;

    @ManyToOne
    @JoinColumn(name = "id_Ingrediente")
    private Ingrediente ingrediente;

    private Integer quantidade;
    private String unidade;

    public PratoIngrediente(Integer id_PratoIngrediente, Prato prato, Integer quantidade, Ingrediente ingrediente, String unidade) {
        this.id_PratoIngrediente = id_PratoIngrediente;
        Prato = prato;
        this.quantidade = quantidade;
        this.ingrediente = ingrediente;
        this.unidade = unidade;
    }

    public PratoIngrediente() {
    }

    public Integer getId_PratoIngrediente() {
        return id_PratoIngrediente;
    }

    public void setId_PratoIngrediente(Integer id_PratoIngrediente) {
        this.id_PratoIngrediente = id_PratoIngrediente;
    }

    public Prato getPrato() {
        return Prato;
    }

    public void setPrato(Prato prato) {
        Prato = prato;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
}
