package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    public NoticiaDAO() throws SQLException {
        //Registra o conector com o banco de dados através do DriverManager.
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/politica",
                "postgres",
                "postgres");

    }

    //O método salvar envia uma instrução INSERT para o banco de dados PostgreSQL.    
    public void salvar(Noticia noticia) throws SQLException {
        //O caracter ? indica um parâmetro (valor) que será passado para a instrução.
        pstm = con.prepareStatement(
                "insert into noticia(id, titulo, texto, data_hora, fonte) values (?, ?, ?, ?, ?)");
        //Aqui passamos os valores de cada um dos parâmetros, na ordem que estão na instrução
        //SQL, da esquerda para direita.
        pstm.setInt(1, noticia.getId());
        pstm.setString(2, noticia.getTitulo());
        pstm.setString(3, noticia.getTexto());
        pstm.setDate(4, (Date) noticia.getData_hora());
        pstm.setString(5, noticia.getFonte());
        pstm.execute();    //Após informar todos os parâmetros, mandamos executar a instrução.        
    }

    //O método alterar envia uma instrução UPDATE para o banco de dados PostgreSQL.
    public void alterar(Noticia noticia) throws SQLException {
        pstm = con.prepareStatement(
                "update noticia set titulo = ?, texto = ?, data_hora = ?, fonte = ? where id = ?");
        pstm.setString(1, noticia.getTitulo());
        pstm.setString(2, noticia.getTexto());
        pstm.setDate(3, (Date) noticia.getData_hora());
        pstm.setString(4, noticia.getFonte());
        pstm.setInt(5, noticia.getId());   //Altera de acordo com o ID informado pelo usuário.
        pstm.execute();
    }

    //O método excluir envia uma instrução DELETE para o banco de dados PostgreSQL.
    public void excluir(Noticia noticia) throws SQLException {
        //Deleta o registro da tabela cujo ID é informado no parâmetro da instrução.
        pstm = con.prepareStatement(
                "delete from noticia where id = ?");
        pstm.setInt(1, noticia.getId());  //Exclui de acordo com o ID informado pelo usuário.
        pstm.execute();
    }

    //O método pesquisar será utilizado para buscar um registro da tabela de MP3
    //com base no ID da música, que é a chave primária da tabela MP3.
    public Noticia pesquisar(int id) throws SQLException {
        Noticia noticia = null;
        pstm = con.prepareStatement("select * from noticia where id = ?");
        pstm.setInt(1, id);
        rs = pstm.executeQuery();
        if (rs.next()) {
            noticia = new Noticia();
            noticia.setId(rs.getInt("id"));
            noticia.setTitulo(rs.getString("titulo"));
            noticia.setTexto(rs.getString("texto"));
            noticia.setData_hora(rs.getDate("data_hora"));
            noticia.setFonte(rs.getString("fonte"));
        }
        return noticia;
    }

    //Este método é utilizado pelo IndexMB para alimentar a lista de músicas
    //que será apresentada na página index.xhtml através do componente
    //<h:dataTable>
    public List<Noticia> getTodosNoticia() throws SQLException {
        ArrayList<Noticia> noticias = new ArrayList();  //Cria uma lista de músicas vazia.
        Noticia noticia = null;  //Define um objeto do tipo Mp3 também vazio.

        pstm = con.prepareStatement("select * from noticia order by data_hora desc ");

        rs = pstm.executeQuery();  //Executa o comando select.
        while (rs.next()) {          //Percorre todas as músicas retornadas pelo select.
            noticia = new Noticia();
            noticia.setId(rs.getInt("id"));
            noticia.setTitulo(rs.getString("titulo"));
            noticia.setTexto(rs.getString("texto"));
            noticia.setData_hora(rs.getDate("data_hora"));
            noticia.setFonte(rs.getString("fonte"));
            noticias.add(noticia);   //Adiciona cada música na lista de músicas.
        }
        return noticias;
    }

}
