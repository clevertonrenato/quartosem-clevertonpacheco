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
import modelo.Cadastro_bebida;
import persistencia.Cadastro_bebidaDAO;

/**
 *
 * @author Renato
 */
@ManagedBean
@RequestScoped
public class CadbebidaMB {

   //O mp3 é um objeto da classe Mp3 e é utilizado para apresentar dados
    //no formulário da página index.xhtml.
    private Cadastro_bebida cadbebida;

    //o mp3DAO é um objeto da classe Mp3DAO e é utilizado pelo indexMB para
    //manipulação dos métodos de acesso a dados, tais como: salvar(), alterar(),
    //excluir() e pesquisar().
    private Cadastro_bebidaDAO cadbebidaDAO;
    private String mensagem = "";

    //generoDAO é um objeto da classe GeneroDAO e será usado pelo indexMB na
    //manipulação do método de pesquisa de gêneros.
    // private GeneroDAO generoDAO;
    //Aqui vamos definir uma lista de objetos do tipo Mp3, os quais serão
    //apresentados no componente <h:dataTable> da página index.xhtml.
    private List<Cadastro_bebida> cadbebidas;

    //Definimos uma lista de objetos do tipo Genero, os quais serão apresentados
    //no componentes <h:selectOneMenu> da página index.xhtml.
    //private List<Genero> generos;
    //No construtor da classe indexMB são criados os objetos mp3 e mp3DAO.
    //O mp3 é usado pelo mecanismo do JSF para vincular os componentes da
    //página index.xhml com a classe de controle indexMB. Já o mp3DAO fornece
    //o conjunto de métodos para manipulação dos dados no BD.
    public CadbebidaMB() throws SQLException {
        cadbebida = new Cadastro_bebida();
        cadbebidaDAO = new Cadastro_bebidaDAO();
        cadbebidas = cadbebidaDAO.getTodosCadastro_bebida(Cadastro_bebidaDAO.ORDEM_POR_ID); //Monta a lista de músicas.
        // generoDAO = new GeneroDAO();
        //generos = generoDAO.getTodosGeneros();  //Monta a lista de gêneros.
    }

    public String getMensagem() {
        return this.mensagem;
    }

    //Este método será utilizado para retornar a lista de todos as músicas cadastradas.
    public List<Cadastro_bebida> getCadbebidas() {
        return cadbebidas;
    }

    //Este método será utilizado para retornar a lista de todos os gêneros cadastrados.
    // public List<Genero> getGeneros() {
    //   return generos;
    // }   
    //Todos os atributos de um objeto Mp3 (id, musica, musicobanca, ano e genero),
    //serão vinculados a componentes da página index.xhtml. Portanto, devemos
    //fornecer os métodos get e set para que o mecanismo do JSF possa realizar
    //a tarefa de vinculação. Assim, podemos enviar dados do formulário para
    //o indexMB, bem como, atualizar dados da tela com a modificação dos atributos
    //do objeto mp3 presente no indexMB. Lembre-se que a vinculação é feita no
    //atributo value do componente de tela, ex: <h:inputText value="#{indexMB.id}"/>
    public Cadastro_bebida getCadastro_bebida() {
        return cadbebida ;
    }

    public void setCadastro_bebida(Cadastro_bebida cadbebida) {
        this.cadbebida = cadbebida;
    }

    //O método novo() limpa os campos da página index.xhtml.
    //Para limpar, cria-se um novo objeto mp3 vazio.
    public void novo() {
        cadbebida = new Cadastro_bebida();
        //mensagem = "";
    }

    //Quanto o usuário clicar no botão salvar da página index.xhtml,
    //o indexMB (controlador) chama o método salvar() da classe mp3DAO
    //na camada de persistência (manipulação de dados do BD), passando
    //o objeto mp3 para que o mesmo seja inserido na tabela.
    public void salvar() throws SQLException {
      

        if (this.cadbebida.getId() == 0) {
         cadbebidaDAO.salvar(cadbebida);
         this.mensagem = "Cadastro_bebida enviado!";
         } else {
         cadbebidaDAO.alterar(cadbebida);
         this.mensagem = "algum campo esta incorreto!";
         }
         cadbebidas = cadbebidaDAO.getTodosCadastro_bebida(Cadastro_bebidaDAO.ORDEM_POR_ID);
        this.novo();
    }
    //Este é o método que será chamado quando o usuário clicar no link
    //editar de uma linha do <h:dataTable>
    public void editar(Cadastro_bebida dados) {
        this.cadbebida = dados;
    }

    //Quanto o usuário clicar no botão excluir da página index.xhtml,
    //o indexMB (controlador) chama o método excluir() da classe mp3DAO
    //na camada de persistência (manipulação de dados do BD), passando
    //o objeto mp3 para que o mesmo seja excluído da tabela.
    public void excluir(Cadastro_bebida cadbebida) throws SQLException {
        cadbebidaDAO.excluir(cadbebida);
        //Atualiza a lista após excluir linha da tabela de mp3.
        cadbebidas = cadbebidaDAO.getTodosCadastro_bebida(Cadastro_bebidaDAO.ORDEM_POR_ID);
    }

    //Quando o usuário clicar no botão pesquisar da página index.xhtml,
    //o indexMB (controlador) chama o método pesquisar() da classe mp3DAO
    //na camada de persistência (manipulação de dados do BD), passando
    //como argumento o id da música a ser pesquisada na tabela. O método
    //pesquisar() retorna um objeto do tipo mp3 que será utilizado para
    //atualizar o atributo mp3 da classe indexMB. Assim, serão apresentados
    //os dados da música no formulário da página index.xhtml.
    public void pesquisar() throws SQLException {
        cadbebida = cadbebidaDAO.pesquisar(cadbebida.getId());
    }

    //Quando o usuário clicar no link de ordenação da tabela por música,
    //a classe de controle (IndexMB) chama o método getTodosMp3() da classe
    //de persistência Mp3DAO passando como parâmetro a constante que
    //identifica a forma de ordenação (id, música, músicobanda, gênero ou ano).
    public void ordenaPorNome() throws SQLException {
        cadbebidas = cadbebidaDAO.getTodosCadastro_bebida(Cadastro_bebidaDAO.ORDEM_POR_ID);
    }

   

}

  
    
    
