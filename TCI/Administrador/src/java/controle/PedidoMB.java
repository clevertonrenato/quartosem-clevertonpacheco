/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Pedido;
import modelo.PedidoItem;
import persistencia.PedidoDAO;

/**
 *
 * @author Renato
 */
@ManagedBean
@RequestScoped
public class PedidoMB {

    private Pedido pedido;
    private PedidoDAO pedidoDAO;
    private List<Pedido> pedidos;
    private String filtroDescricao;
    private String filtroTipo;
    private PedidoItem pedidoitem;
    private List<Pedido> pedinfo;

    public PedidoMB() throws SQLException {
        this.pedido = new Pedido();
        this.pedidoDAO = new PedidoDAO();
        //this.listar();
        this.pedidos = pedidoDAO.getTodosPedido(filtroTipo);
        this.pedinfo = pedidoDAO.getPedido(filtroTipo);
    }

    public List<Pedido> getPedinfo() {
        return pedinfo;
    }

    public void setPedinfo(List<Pedido> pedinfo) {
        this.pedinfo = pedinfo;
    }

    public PedidoItem getPedidoitem() {
        return pedidoitem;
    }

    public void setPedidoitem(PedidoItem pedidoitem) {
        this.pedidoitem = pedidoitem;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public PedidoDAO getPedidoDAO() {
        return pedidoDAO;
    }

    public void setPedidoDAO(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getFiltroDescricao() {
        return filtroDescricao;
    }

    public void setFiltroDescricao(String filtroDescricao) {
        this.filtroDescricao = filtroDescricao;
    }

    public String getFiltroTipo() {
        return filtroTipo;
    }

    public void setFiltroTipo(String filtroTipo) {
        this.filtroTipo = filtroTipo;
    }

    public void novo() {
        this.pedido = new Pedido();
    }

    public String alterar(Pedido pedido) throws SQLException {
        if (pedido.getSituacao().equals("A")) {
            pedidoDAO.alterar(pedido);
            this.pedidos = pedidoDAO.getTodosPedido(filtroTipo);
        }
        return "pedidos?faces-redirect=true";
    }

    public String fechar(Pedido pedido) throws SQLException {
        if (pedido.getSituacao().equals("P")) {
            pedidoDAO.fechar(pedido);
            this.pedidos = pedidoDAO.getTodosPedido(filtroTipo);
        }
        return "pedidos?faces-redirect=true";
    }

}
