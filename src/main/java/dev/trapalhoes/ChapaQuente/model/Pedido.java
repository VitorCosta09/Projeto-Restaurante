package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Pedido;

    @ManyToOne
    @JoinColumn(name = "id_Cliente")
    private Cliente cliente;

    private Double valor;
    private Double desconto;
    private Double valorTotal;

    public Pedido(Integer id_Pedido, Cliente cliente, Double desconto, Double valor, Double valorTotal) {
        this.id_Pedido = id_Pedido;
        this.cliente = cliente;
        this.desconto = desconto;
        this.valor = valor;
        this.valorTotal = valorTotal;
    }

    public Pedido() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getId_Pedido() {
        return id_Pedido;
    }

    public void setId_Pedido(Integer id_Pedido) {
        this.id_Pedido = id_Pedido;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
}
