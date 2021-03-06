/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author Renato
 */
public class PedidoItem {
    
    private int id;
    private int id_cliente;
    private Cadastro_produto produto;
    private int quantidade;
    private float unitario;
    private String observacao;
   
    
    public PedidoItem() {
    produto = new Cadastro_produto();
    }

    public PedidoItem(int id, int id_cliente, Cadastro_produto produto, int quantidade, float unitario, String observacao) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.unitario = unitario;
        this.observacao = observacao;
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
