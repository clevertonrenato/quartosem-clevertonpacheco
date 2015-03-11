
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Genero;

public class GeneroDAO {

    private Connection con;          //Objeto utilizado para o estabelecimento de uma conexão.
    private PreparedStatement pstm;  //Objeto utilizado para o envio de instruções SQL.  
    private ResultSet rs;            //Objeto utilizado para guardar o resultado de um SELECT.
       
    
    //O construtor da classe cria os recursos necessários para manipulação de dados
    //do banco.
    public GeneroDAO() throws SQLException{
        //Registra o conector com o banco de dados através do DriverManager.
        DriverManager.registerDriver(new org.postgresql.Driver());
        //Aqui criamos uma conexão para o envio de instruções SQL para o PostgreSQL.
        con = DriverManager.getConnection("jdbc:postgresql://localhost/mp3album",
                                          "postgres",
                                          "postgres");        
    }

    //O método salvar envia uma instrução INSERT para o banco de dados PostgreSQL.    
    public void salvar(Genero genero) throws SQLException{
        //O caracter ? indica um parâmetro (valor) que será passado para a instrução.
        pstm = con.prepareStatement(
                "insert into genero(descricao) values (?)");
        //Aqui passamos os valores de cada um dos parâmetros, na ordem que estão na instrução
        //SQL, da esquerda para direita.
        pstm.setString(1, genero.getDescricao());
        pstm.execute();    //Após informar todos os parâmetros, mandamos executar a instrução.        
    }
    
    //O método alterar envia uma instrução UPDATE para o banco de dados PostgreSQL.
    public void alterar(Genero genero) throws SQLException{
        pstm = con.prepareStatement(
                "update genero set descricao = ? where id = ?");
        pstm.setString(1, genero.getDescricao());
        pstm.setInt(2, genero.getId());
        pstm.execute();
    }
    
  //O método excluir envia uma instrução DELETE para o banco de dados PostgreSQL.
    public void excluir(Genero genero) throws SQLException{
        //Deleta o registro da tabela cujo ID é informado no parâmetro da instrução.
        pstm = con.prepareStatement(
                "delete from genero where id = ?"); 
        pstm.setInt(1, genero.getId());  //Exclui de acordo com o ID informado pelo usuário.
        pstm.execute();
    }    
    
  //O método pesquisar será utilizado para buscar um registro da tabela de MP3
    //com base no ID da música, que é a chave primária da tabela MP3.
    public Genero pesquisar(int id) throws SQLException{
        Genero genero = null;
        pstm = con.prepareStatement("select * from genero where id = ?");
        pstm.setInt(1, id);
        rs = pstm.executeQuery();
        if(rs.next()){
            genero = new Genero();
            genero.setId(rs.getInt("id"));
            genero.setDescricao(rs.getString("descricao"));
        }
        return genero;
    }    
    
    public Genero pesquisarPorDescricao(String descricao) throws SQLException{
        Genero genero = null;
        pstm = con.prepareStatement("select * from genero where descricao = ?");
        pstm.setString(1, descricao);
        rs = pstm.executeQuery();
        if(rs.next()){
            genero = new Genero();
            genero.setId(rs.getInt("id"));
            genero.setDescricao(rs.getString("descricao"));
        }
        
        return genero;
    }
    
    //Este método é utilizado pelo IndexMB para alimentar a lista de músicas
    //que será apresentada na página index.xhtml através do componente
    //<h:dataTable>
    public List<Genero> getTodosGeneros() throws SQLException{
        ArrayList<Genero> generos = new ArrayList();  //Cria uma lista de músicas vazia.
        Genero genero = null;  //Define um objeto do tipo Mp3 também vazio.       
        pstm = con.prepareStatement("select * from genero order by descricao");       
        rs = pstm.executeQuery();  //Executa o comando select.
        while(rs.next()){          //Percorre todas as músicas retornadas pelo select.
            genero = new Genero();
            genero.setId(rs.getInt("id"));
            genero.setDescricao(rs.getString("descricao"));
            generos.add(genero);   //Adiciona cada música na lista de músicas.
        }              
        return generos;
    }
    
    
}
