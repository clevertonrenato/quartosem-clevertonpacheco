/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Renato
 */
public class PedidoItem {

    private Cadastro_pessoa cliente;
    private int id;
    private int id_cliente;
    private Cadastro_produto produto;
    private int quantidade;
    private float unitario;
    private String observacao;
    private Pedido pedido;
    private float total;

    public PedidoItem() {
    }

    public PedidoItem(int id, int id_cliente, Cadastro_produto produto, int quantidade, float unitario, String observacao, Pedido pedido) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.unitario = unitario;
        this.observacao = observacao;
        this.pedido = pedido;
        this.total = quantidade * unitario;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Cadastro_pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Cadastro_pessoa cliente) {
        this.cliente = cliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getUnitario() {
        return unitario;
    }

    public void setUnitario(float unitario) {
        this.unitario = unitario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Cadastro_produto getProduto() {
        return produto;
    }

    public void setProduto(Cadastro_produto produto) {
        this.produto = produto;
    }

}
