package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Renato
 */
public class Pedido {

    private Cadastro_pessoa cliente;
    private int numero;
    private String formapagamento;
    private int idpedido;
    private int idcliente;
    private float total;
    private List<PedidoItem> pedidoitem;
    private Date data;
    private PedidoItem pedidoitens;
    private Cadastro_produto produtoss;
    private Pedido pedido;

    public Pedido() {
        pedidoitem = new ArrayList<>();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cadastro_produto getProdutoss() {
        return produtoss;
    }

    public void setProdutoss(Cadastro_produto produtoss) {
        this.produtoss = produtoss;
    }

    public PedidoItem getPedidoitens() {
        return pedidoitens;
    }

    public void setPedidoitens(PedidoItem pedidoitens) {
        this.pedidoitens = pedidoitens;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cadastro_pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Cadastro_pessoa cliente) {
        this.cliente = cliente;
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
