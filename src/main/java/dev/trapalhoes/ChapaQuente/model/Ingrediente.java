package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Ingrediente")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Ingrediente;
    private String nome;

    public Ingrediente(Integer id_Ingrediente, String nome) {
        this.id_Ingrediente = id_Ingrediente;
        this.nome = nome;
    }

    public Ingrediente() {
    }

    public Integer getId_Ingrediente() {
        return id_Ingrediente;
    }

    public void setId_Ingrediente(Integer id_Ingrediente) {
        this.id_Ingrediente = id_Ingrediente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
