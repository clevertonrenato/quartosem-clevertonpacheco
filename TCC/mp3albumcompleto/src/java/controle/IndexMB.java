package controle;

import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import modelo.Genero;
import modelo.Mp3;
import persistencia.GeneroDAO;
import persistencia.Mp3DAO;

@Named(value = "indexMB")
@RequestScoped
public class IndexMB {

    //O mp3 é um objeto da classe Mp3 e é utilizado para apresentar dados
    //no formulário da página index.xhtml.
    private Mp3 mp3;
    
    //o mp3DAO é um objeto da classe Mp3DAO e é utilizado pelo indexMB para
    //manipulação dos métodos de acesso a dados, tais como: salvar(), alterar(),
    //excluir() e pesquisar().
    private Mp3DAO mp3DAO; 
    
    //generoDAO é um objeto da classe GeneroDAO e será usado pelo indexMB na
    //manipulação do método de pesquisa de gêneros.
    private GeneroDAO generoDAO;
    
    //Aqui vamos definir uma lista de objetos do tipo Mp3, os quais serão
    //apresentados no componente <h:dataTable> da página index.xhtml.
    private List<Mp3> musicas;
            
    //Definimos uma lista de objetos do tipo Genero, os quais serão apresentados
    //no componentes <h:selectOneMenu> da página index.xhtml.
    private List<Genero> generos;
    
    //No construtor da classe indexMB são criados os objetos mp3 e mp3DAO.
    //O mp3 é usado pelo mecanismo do JSF para vincular os componentes da
    //página index.xhml com a classe de controle indexMB. Já o mp3DAO fornece
    //o conjunto de métodos para manipulação dos dados no BD.
    public IndexMB() throws SQLException {
        mp3 = new Mp3();        
        mp3DAO = new Mp3DAO();
        musicas = mp3DAO.getTodosMp3(Mp3DAO.ORDEM_POR_ID); //Monta a lista de músicas.
        generoDAO = new GeneroDAO();
        generos = generoDAO.getTodosGeneros();  //Monta a lista de gêneros.
    }

    //Este método será utilizado para retornar a lista de todos as músicas cadastradas.
    public List<Mp3> getMusicas() {
        return musicas;
    }    

    //Este método será utilizado para retornar a lista de todos os gêneros cadastrados.
    public List<Genero> getGeneros() {
        return generos;
    }   
    

    //Todos os atributos de um objeto Mp3 (id, musica, musicobanca, ano e genero),
    //serão vinculados a componentes da página index.xhtml. Portanto, devemos
    //fornecer os métodos get e set para que o mecanismo do JSF possa realizar
    //a tarefa de vinculação. Assim, podemos enviar dados do formulário para
    //o indexMB, bem como, atualizar dados da tela com a modificação dos atributos
    //do objeto mp3 presente no indexMB. Lembre-se que a vinculação é feita no
    //atributo value do componente de tela, ex: <h:inputText value="#{indexMB.id}"/>
    public Mp3 getMp3() {
        return mp3;
    }

    public void setMp3(Mp3 mp3) {
        this.mp3 = mp3;
    }     
    
    //O método novo() limpa os campos da página index.xhtml.
    //Para limpar, cria-se um novo objeto mp3 vazio.
    public void novo(){
        mp3 = new Mp3();
    }
    
    //Quanto o usuário clicar no botão salvar da página index.xhtml,
    //o indexMB (controlador) chama o método salvar() da classe mp3DAO
    //na camada de persistência (manipulação de dados do BD), passando
    //o objeto mp3 para que o mesmo seja inserido na tabela.
    public void salvar() throws SQLException{
        if(this.mp3.getId() == 0)
           mp3DAO.salvar(mp3);
        else
           mp3DAO.alterar(mp3);
        musicas = mp3DAO.getTodosMp3(Mp3DAO.ORDEM_POR_ID);
    }
    
    //Este é o método que será chamado quando o usuário clicar no link
    //editar de uma linha do <h:dataTable>
    public void editar(Mp3 musica){
        this.mp3 = musica;
    }       
        
    //Quanto o usuário clicar no botão excluir da página index.xhtml,
    //o indexMB (controlador) chama o método excluir() da classe mp3DAO
    //na camada de persistência (manipulação de dados do BD), passando
    //o objeto mp3 para que o mesmo seja excluído da tabela.
    public void excluir(Mp3 mp3) throws SQLException{
        mp3DAO.excluir(mp3);
        //Atualiza a lista após excluir linha da tabela de mp3.
        musicas = mp3DAO.getTodosMp3(Mp3DAO.ORDEM_POR_ID);  
    }
    
    //Quando o usuário clicar no botão pesquisar da página index.xhtml,
    //o indexMB (controlador) chama o método pesquisar() da classe mp3DAO
    //na camada de persistência (manipulação de dados do BD), passando
    //como argumento o id da música a ser pesquisada na tabela. O método
    //pesquisar() retorna um objeto do tipo mp3 que será utilizado para
    //atualizar o atributo mp3 da classe indexMB. Assim, serão apresentados
    //os dados da música no formulário da página index.xhtml.
    public void pesquisar() throws SQLException{
        mp3 = mp3DAO.pesquisar(mp3.getId());
    }
    
    //Quando o usuário clicar no link de ordenação da tabela por música,
    //a classe de controle (IndexMB) chama o método getTodosMp3() da classe
    //de persistência Mp3DAO passando como parâmetro a constante que
    //identifica a forma de ordenação (id, música, músicobanda, gênero ou ano).
    public void ordenaPorMusica() throws SQLException{
        musicas = mp3DAO.getTodosMp3(Mp3DAO.ORDEM_POR_MUSICA);
    }

    public void ordenaPorID() throws SQLException{
        musicas = mp3DAO.getTodosMp3(Mp3DAO.ORDEM_POR_ID);
    }

    
}
