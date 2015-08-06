package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Renato
 */
public class Pedido {

    private int numero;
    private String formapagamento;
    private int idpedido;
    private int idcliente;
    private float total;
    private List<PedidoItem> pedidoitem;

    public Pedido() {
        pedidoitem = new ArrayList<>();
    }        

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }


    public String getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(String formapagamento) {
        this.formapagamento = formapagamento;
    }

    public List<PedidoItem> getPedidoitem() {
        return pedidoitem;
    }

    public void setPedidoitem(List<PedidoItem> pedidoitem) {
        this.pedidoitem = pedidoitem;
    }


}
