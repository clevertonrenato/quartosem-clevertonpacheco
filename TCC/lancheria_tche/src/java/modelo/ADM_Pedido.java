/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Renato
 */
public class ADM_Pedido {

    private Cadastro_pessoa cliente;
    private String produto;
    private Date data;
    private String endereco;
    private float total;
    private List<PedidoItem> itens;
    private ADM_Pedido ped;

    public ADM_Pedido() {
    }

    public ADM_Pedido(Cadastro_pessoa cliente, String produto, Date data, String endereco, float total, List<PedidoItem> itens, ADM_Pedido ped) {
        this.cliente = cliente;
        this.produto = produto;
        this.data = data;
        this.endereco = endereco;
        this.total = total;
        this.itens = itens;
        this.ped = ped;
    }

   

    public ADM_Pedido getPed() {
        return ped;
    }

    public void setPed(ADM_Pedido ped) {
        this.ped = ped;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public List<PedidoItem> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItem> itens) {
        this.itens = itens;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ADM_Pedido other = (ADM_Pedido) obj;
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return true;
    }
}
