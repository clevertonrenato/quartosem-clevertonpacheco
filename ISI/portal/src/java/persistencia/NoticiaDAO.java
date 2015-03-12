
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Noticia;

/**
 *
 * @author Renato
 */
public class NoticiaDAO {
    
     private Connection con;          //Objeto utilizado para o estabelecimento de uma conexão.
    private PreparedStatement pstm;  //Objeto utilizado para o envio de instruções SQL.  
    private ResultSet rs;            //Objeto utilizado para guardar o resultado de um SELECT.
    
    
     //O construtor da classe cria os recursos necessários para manipulação de dados
    //do banco.
    public NoticiaDAO() throws SQLException{
        //Registra o conector com o banco de dados através do DriverManager.
        DriverManager.registerDriver(new org.postgresql.Driver());
        //Aqui criamos uma conexão para o envio de instruções SQL para o PostgreSQL.
        con = DriverManager.getConnection("jdbc:postgresql://localhost/noticia",
                                          "postgres",
                                          "postgres");   
        
    }
    
    //O método salvar envia uma instrução INSERT para o banco de dados PostgreSQL.    
    public void salvar(Noticia noticia) throws SQLException{
        //O caracter ? indica um parâmetro (valor) que será passado para a instrução.
        pstm = con.prepareStatement(
                "insert into noticia(titulo, texto, datahora, fonte) values (?, ?, ?, ?)");
        //Aqui passamos os valores de cada um dos parâmetros, na ordem que estão na instrução
        //SQL, da esquerda para direita.
        pstm.setString(1, noticia.getTitulo());
        pstm.setString(2, noticia.);
        pstm.setInt(3, mp3.getAno());
        pstm.setString(4, mp3.getGenero());
        pstm.execute();    //Após informar todos os parâmetros, mandamos executar a instrução.        
    }
    
}
