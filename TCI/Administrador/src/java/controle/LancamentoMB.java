/*
    Classe:     Lancamento.java
    Descrição:  classe de controle (Bean Gerenciado) que realiza o mapeamento
                das ações do usuário na página de registro dos lançamentos
                de movimentação de contas
    Autor:      Carlos Emilio (03/06/2014)
*/

//A classe será criada na camada de controle da aplicação.
package controle;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Cadastro_produto;
import modelo.Lancamento;
import persistencia.Cadastro_produtoDAO;
import persistencia.LancamentoDAO;
import persistencia.Cadastro_pesssoaDAO;

@ManagedBean   //Identifica que a classe é um Bean Gerenciado para o mecanismo do JSF.
@RequestScoped                   //Uma instancia de LancamentoMB é criada a cada requisição de usuário.
public class LancamentoMB {

    //O objeto lancamento é ligado aos componentes da página de registro de lançamentos.
    private Lancamento lancamento;
    //O objeto contas é utilizado para apresentação da lista de contas no componente selectOneMenu.
    private List<Cadastro_produto> contas;
    //O objeto lancamentos é utilizado pelo componente DataTable para apresentar os registros já realizados.
    private List<Lancamento> lancamentos;
    //Tanto LancamentoDAO como Cadastro_produtoDAO são objetos das classes de serviço de acesso a dados.
    private LancamentoDAO lancamentoDAO;
    private Cadastro_produtoDAO contasDAO;
    private Cadastro_pesssoaDAO usuarioDAO;
    
    //O contrutor da classe é utilizado pelo mecanismo do JSF no momento de uma requisição do usuário
    //Assim, as instâncias de lancamento, contaDAO, lancamentoDAO, contas e lancamentos são criadas.
    public LancamentoMB() throws SQLException {
        usuarioDAO = new Cadastro_pesssoaDAO();           
        this.lancamento = new Lancamento();        
        this.contasDAO = new Cadastro_produtoDAO();
        this.lancamentoDAO = new LancamentoDAO();
        //Cria a lista de contas.
        this.contas = contasDAO.getContas("select * from conta");
        this.listar();  //Este método alimenta a lista de lançamentos.
    }

    //A seguir, os gets e sets de todos os atributos que serão vinculados a página de lançamentos.
    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public List<Cadastro_produto> getContas() {
        return contas;
    }

    public void setContas(List<Cadastro_produto> contas) {
        this.contas = contas;
    }   

    //A ação novo somente cria uma instância vazia de um objeto do tipo Lançamento,
    //zerando os campos apresentados na página registro de lançamentos.
    public void novo(){
        lancamento = new Lancamento();
    }
    
    //A ação editar coloca os campos referentes a um lançamento em seus respectivos,
    //componentes na página de registro de lançamentos.
    public void editar(Lancamento lancamento){
        this.lancamento = lancamento;        
    }
    
    //A ação salvar verifica qual método invocar a partir de lancamentoDAO.
    //Caso seja uma ação de inserção de um novo lançamento, invoca-se o método
    //inserir. Caso seja uma ação de alteração dos dados de um lançamento
    //previamente registrado, chama-se o método alterar. Ambos os métodos recebem
    //como argumento a linha selecionada pelo usuário na página de registro de lançamentos.
    public void salvar() throws SQLException{
        if(this.lancamento.getId() == 0)   //Caso o ID for zero é um novo lançamento
           this.lancamentoDAO.inserir(this.lancamento);
        else
            this.lancamentoDAO.alterar(this.lancamento);
        //Após inserir ou alterar remonta a lista de lançamentos a ser apresentada
        //no DataTable e limpa os componentes da página de lançamentos.
        this.listar();
        this.novo();
    }    
    
    //Quando o usuário clica no botão excluir o método é invocado,
    //recebendo como argumento o objeto lancamento selecionado e
    //chamando o serviço apropriado na camada de persistência.
    public void excluir(Lancamento lancamento) throws SQLException{
        this.lancamentoDAO.excluir(lancamento);
        this.listar();
    }        
    
    //O método listar é utilizado sempre que ocorrer uma alteração
    //na tabela de lancamentos no BD, decorrente de uma inserção, alteração,
    //ou exclusão de dados.
    public void listar() throws SQLException{
        lancamentos = this.lancamentoDAO.getLancamentos("select * from lancamento");
    }    
    
    
}
