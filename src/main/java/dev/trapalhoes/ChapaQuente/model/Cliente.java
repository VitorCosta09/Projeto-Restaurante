package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Cliente;
    private String nome;
    private String senha;
    private String email;
    private Integer totalPedidos;
    private String cpf;

    public Cliente(Integer id_Cliente, String nome, String senha, String email, Integer totalPedidos, String cpf) {
        this.id_Cliente = id_Cliente;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.totalPedidos = totalPedidos;
        this.cpf = cpf;
    }

    public Cliente() {
    }

    public Integer getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(Integer id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(Integer numeroPedidos) {
        this.totalPedidos = numeroPedidos;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
