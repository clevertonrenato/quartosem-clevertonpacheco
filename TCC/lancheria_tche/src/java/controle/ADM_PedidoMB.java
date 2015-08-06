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
public class ADM_PedidoMB {

    private Pedido pedido;
    private PedidoDAO pedidoDAO;
    private List<Pedido> pedidos;
    private String filtroDescricao;
    private String filtroTipo;
    private PedidoItem pedidoitem;

    public ADM_PedidoMB() throws SQLException {
        this.pedido = new Pedido();
        this.pedidoDAO = new PedidoDAO();
        //this.listar();
        pedidos = pedidoDAO.getTodosPedido(filtroTipo);
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

    public void editar(Pedido pedido) {
        this.pedido = pedido;
    }

    /*   public void salvar() throws SQLException {
     if (this.pedido.getIdcliente() == 0) {
     this.pedidoDAO.inserir(this.pedido);
     } else {
     this.pedidoDAO.alterar(this.pedido);
     }
     this.listar();
     this.novo();
     }

     public void excluir(Pedido pedido) throws SQLException {
     this.pedidoDAO.excluir(pedido);
     this.listar();
     }

     public void listar() throws SQLException {
     this.pedidos = this.pedidoDAO.getPedidos("select * from produtos");
     }

     public Pedido pesquisarPorID() throws SQLException {
     return this.pedidoDAO.getPedidoPorID(this.pedido.getIdcliente());
     }

     public Pedido pesquisaPorNome() throws SQLException {
     return this.pedidoDAO.getPedidoPorNome(this.pedido.getCliente());
     }

     public void ordenaPorID() throws SQLException {
     this.pedidos = this.pedidoDAO.getPedidos(
     "select * from pedido order by id");
     }

     public void ordenaPorNome() throws SQLException {
     this.pedidos = this.pedidoDAO.getPedidos(
     "select * from cliente order by nome");
     }

     public void ordenaPorTipo() throws SQLException {
     this.pedidos = this.pedidoDAO.getPedidos(
     "select * from cliente order by tipo");
     }

     public void ordenaPorValor() throws SQLException {
     this.pedidos = this.pedidoDAO.getPedidos(
     "select * from cliente order by valor");
     }

     public void filtraPorNome() throws SQLException {
     this.pedidos = this.pedidoDAO.getPedidos(
     "select * from cliente where nome ilike '%"
     + this.filtroDescricao + "%'");
     }

     public void filtraPorTipo() throws SQLException {
     this.pedidos = this.pedidoDAO.getPedidos(
     "select * from cliente where tipo ilike '"
     + this.filtroTipo + "%'");
     }*/
}
