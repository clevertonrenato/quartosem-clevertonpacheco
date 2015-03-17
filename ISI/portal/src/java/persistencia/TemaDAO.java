
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Tema;

/**
 *
 * @author Renato
 */
public class TemaDAO {
    
     private Connection con;          //Objeto utilizado para o estabelecimento de uma conexão.
    private PreparedStatement pstm;  //Objeto utilizado para o envio de instruções SQL.  
    private ResultSet rs;            //Objeto utilizado para guardar o resultado de um SELECT.
    
    
     //O construtor da classe cria os recursos necessários para manipulação de dados
    //do banco.
    public TemaDAO() throws SQLException{
        //Registra o conector com o banco de dados através do DriverManager.
        DriverManager.registerDriver(new org.postgresql.Driver());
        //Aqui criamos uma conexão para o envio de instruções SQL para o PostgreSQL.
        con = DriverManager.getConnection("jdbc:postgresql://localhost/noticia",
                                          "postgres",
                                          "postgres");   
        
    }
    
     //O método salvar envia uma instrução INSERT para o banco de dados PostgreSQL.    
    public void salvar(Tema tema) throws SQLException{
        //O caracter ? indica um parâmetro (valor) que será passado para a instrução.
        pstm = con.prepareStatement(
                "insert into noticia(id, nome, autor, cortitulo, corfundo) values (?, ?, ?, ?, ?)");
        //Aqui passamos os valores de cada um dos parâmetros, na ordem que estão na instrução
        //SQL, da esquerda para direita.
        pstm.setInt(1, tema.getId());
        pstm.setString(2, tema.getNome());
        pstm.setString(3, tema.getAutor());
        pstm.setString(4, tema.getCor_titulo());
        pstm.setString(5, tema.getCor_fundo());
        pstm.execute();    //Após informar todos os parâmetros, mandamos executar a instrução.        
    }
    
    //Este método é utilizado pelo IndexMB para alimentar a lista de músicas
    //que será apresentada na página index.xhtml através do componente
    //<h:dataTable>
    public List<Tema> getTodosTema(int ordem) throws SQLException{
        ArrayList<Tema> temas = new ArrayList();  //Cria uma lista de músicas vazia.
        Tema tema = null;  //Define um objeto do tipo Mp3 também vazio.
        
              
        
        rs = pstm.executeQuery();  //Executa o comando select.
        while(rs.next()){          //Percorre todas as músicas retornadas pelo select.
            tema = new Tema();
            tema.setId(rs.getInt("id"));
            tema.setNome(rs.getString("nome"));
            tema.setAutor(rs.getString("autor"));
            tema.setCor_titulo(rs.getString("cortitulo"));
            tema.setCor_fundo(rs.getString("corfundo"));
            temas.add(tema);   //Adiciona cada música na lista de músicas. 
        }              
        return temas;
    }
    
}
