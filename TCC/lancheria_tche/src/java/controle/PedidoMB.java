/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Cadastro_produto;
import modelo.Pedido;
import modelo.PedidoItem;
import persistencia.Cadastro_produtoDAO;
import persistencia.PedidoDAO;
import seguranca.Acesso;

/**
 *
 * @author Renato
 */
@ManagedBean
@ViewScoped
public class PedidoMB implements Serializable {

    //O mp3 é um objeto da classe Mp3 e é utilizado para apresentar dados
    //no formulário da página index.xhtml.
    private Pedido pedido;
    private int idProduto;
    private int quantidade;
    private String observacao;
    private PedidoItem pedidoitem;

    //o mp3DAO é um objeto da classe Mp3DAO e é utilizado pelo indexMB para
    //manipulação dos métodos de acesso a dados, tais como: salvar(), alterar(),
    //excluir() e pesquisar().
    private PedidoDAO pedidoDAO;
    private Cadastro_produtoDAO produtoDAO;
    private String mensagem = "";

    private List<Pedido> pedidos;

    //Definimos uma lista de objetos do tipo Genero, os quais serão apresentados
    //no componentes <h:selectOneMenu> da página index.xhtml.
    //private List<Genero> generos;
    //No construtor da classe indexMB são criados os objetos mp3 e mp3DAO.
    //O mp3 é usado pelo mecanismo do JSF para vincular os componentes da
    //página index.xhml com a classe de controle indexMB. Já o mp3DAO fornece
    //o conjunto de métodos para manipulação dos dados no BD.
    public PedidoMB() throws SQLException {
        pedido = new Pedido();
        produtoDAO = new Cadastro_produtoDAO();
        pedidoDAO = new PedidoDAO();
        pedidos = pedidoDAO.getTodosPedido(PedidoDAO.ORDEM_POR_NUMERO);
        // this.listar();

    }

    public String getMensagem() {
        return this.mensagem;
    }

    //Este método será utilizado para retornar a lista de todos as músicas cadastradas.
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public PedidoItem getPedidoitem() {
        return pedidoitem;
    }

    public void setPedidoitem(PedidoItem pedidoitem) {
        this.pedidoitem = pedidoitem;
    }

    //O método novo() limpa os campos da página index.xhtml.
    //Para limpar, cria-se um novo objeto mp3 vazio.
    public void novo() {
        pedido = new Pedido();
        //mensagem = "";
    }

    //Quanto o usuário clicar no botão salvar da página index.xhtml,
    //o indexMB (controlador) chama o método salvar() da classe mp3DAO
    //na camada de persistência (manipulação de dados do BD), passando
    //o objeto mp3 para que o mesmo seja inserido na tabela.
    public void salvar() throws SQLException {

        if (this.pedido.getNumero() == 0) {
            Acesso acesso = new Acesso();
            pedido.setIdcliente(acesso.getUsuarioSession().getId());
            pedidoDAO.salvar(pedido);
            this.mensagem = "Pedido enviado!";
        } else {

            this.mensagem = "algum campo esta incorreto!";
        }
        this.novo();
 
    }

    //Este é o método que será chamado quando o usuário clicar no link
    //editar de uma linha do <h:dataTable>
    public void editar(Pedido dados) {
        this.pedido = dados;
    }

    //Quanto o usuário clicar no botão excluir da página index.xhtml,
    //o indexMB (controlador) chama o método excluir() da classe mp3DAO
    //na camada de persistência (manipulação de dados do BD), passando
    //o objeto mp3 para que o mesmo seja excluído da tabela.
    public void excluir(List<Pedido> pedidos) throws SQLException {
        pedidoDAO.excluir(pedidos);

        //Atualiza a lista após excluir linha da tabela de mp3.
        //pedidos = pedidoDAO.getTodosPedido(PedidoDAO.ORDEM_POR_NUMERO);
    }

    //Quando o usuário clicar no botão pesquisar da página index.xhtml,
    //o indexMB (controlador) chama o método pesquisar() da classe mp3DAO
    //na camada de persistência (manipulação de dados do BD), passando
    //como argumento o id da música a ser pesquisada na tabela. O método
    //pesquisar() retorna um objeto do tipo mp3 que será utilizado para
    //atualizar o atributo mp3 da classe indexMB. Assim, serão apresentados
    //os dados da música no formulário da página index.xhtml.
    // public void pesquisar() throws SQLException {
    //   pedido = pedidoDAO.pesquisar(pedido.getNumero());
    //}
    //Quando o usuário clicar no link de ordenação da tabela por música,
    //a classe de controle (IndexMB) chama o método getTodosMp3() da classe
    //de persistência Mp3DAO passando como parâmetro a constante que
    //identifica a forma de ordenação (id, música, músicobanda, gênero ou ano).
    public void ordenaPorNome() throws SQLException {
        //pedidos = pedidoDAO.getTodosPedido(PedidoDAO.ORDEM_POR_NUMERO);
    }

    public String adicionarItem() throws SQLException {
        PedidoItem pedidoitem = new PedidoItem();
        Cadastro_produto produto = produtoDAO.pesquisar(idProduto);
        pedidoitem.setId(pedido.getNumero());
        pedidoitem.setProduto(produto);
        pedidoitem.setQuantidade(quantidade);
        pedidoitem.setUnitario(produto.getPrecolanche());
        pedidoitem.setObservacao(observacao);
        pedidoitem.setTotal(pedidoitem.getTotal());
        ((ArrayList) this.pedido.getPedidoitem()).add(pedidoitem);
        this.pedido.setTotal(this.pedido.getTotal() + pedidoitem.getUnitario() * pedidoitem.getQuantidade());
        this.observacao = "";
        this.mensagem = "";
        return "visao/pedido.xhtml?faces-redirect=true";
    }

    public void excluirItemCarrinho(PedidoItem pedidoitem) {
        this.pedido.setTotal(this.pedido.getTotal() - pedidoitem.getUnitario() * pedidoitem.getQuantidade());
        ((ArrayList) this.pedido.getPedidoitem()).remove(pedidoitem);
    }

}
