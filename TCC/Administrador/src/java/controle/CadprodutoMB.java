package controle;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Cadastro_produto;
import persistencia.Cadastro_produtoDAO;

@ManagedBean
@RequestScoped
public class CadprodutoMB {

    private Cadastro_produto conta;
    private Cadastro_produtoDAO contaDAO;
    private List<Cadastro_produto> contas;
    private String filtroDescricao;
    private String filtroTipo;

    public CadprodutoMB() throws SQLException {
        this.conta = new Cadastro_produto();
        this.contaDAO = new Cadastro_produtoDAO();
        this.listar();
    }

    public Cadastro_produto getConta() {
        return this.conta;
    }

    public void setConta(Cadastro_produto conta) {
        this.conta = conta;
    }

    public List<Cadastro_produto> getContas() {
        return this.contas;
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
        this.conta = new Cadastro_produto();
    }

    public void editar(Cadastro_produto conta) {
        this.conta = conta;
    }

    public void salvar() throws SQLException {
        if (this.conta.getId() == 0) {
            this.contaDAO.inserir(this.conta);
        } else {
            this.contaDAO.alterar(this.conta);
        }
        this.listar();
        this.novo();
    }

    public void excluir(Cadastro_produto conta) throws SQLException {
        this.contaDAO.excluir(conta);
        this.listar();
    }

    public void listar() throws SQLException {
        this.contas = this.contaDAO.getContas("select * from produtos");
    }

    public Cadastro_produto pesquisarPorID() throws SQLException {
        return this.contaDAO.getContaPorID(this.conta.getId());
    }

    public Cadastro_produto pesquisaPorNome() throws SQLException {
        return this.contaDAO.getContaPorNome(this.conta.getLanche());
    }

    public void ordenaPorID() throws SQLException {
        this.contas = this.contaDAO.getContas(
                "select * from produtos order by idproduto");
    }

    public void ordenaPorNome() throws SQLException {
        this.contas = this.contaDAO.getContas(
                "select * from produtos order by nomeprod");
    }

    public void ordenaPorTipo() throws SQLException {
        this.contas = this.contaDAO.getContas(
                "select * from produtos order by tipo");
    }
    public void ordenaPorValor() throws SQLException {
        this.contas = this.contaDAO.getContas(
                "select * from produtos order by valor");
    }

    public void filtraPorNome() throws SQLException {
        this.contas = this.contaDAO.getContas(
                "select * from produtos where nome ilike '%"
                + this.filtroDescricao + "%'");
    }

    public void filtraPorTipo() throws SQLException {
        this.contas = this.contaDAO.getContas(
                "select * from produtos where tipo ilike '"
                + this.filtroTipo + "%'");
    }

}
