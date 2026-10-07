package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Prato")
public class Prato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Prato;

    @ManyToOne
    @JoinColumn(name = "id_Produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_Pedido")
    private Pedido pedido;

    public Prato(Integer id_Prato, Produto produto, Pedido pedido) {
        this.id_Prato = id_Prato;
        this.produto = produto;
        this.pedido = pedido;
    }

    public Prato() {
    }

    public Integer getId_Prato() {
        return id_Prato;
    }

    public void setId_Prato(Integer id_Prato) {
        this.id_Prato = id_Prato;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
