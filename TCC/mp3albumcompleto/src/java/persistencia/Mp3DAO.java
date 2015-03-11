
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Genero;
import modelo.Mp3;

//Esta classe será utilizada para implementação dos métodos de acesso aos dados
//no BD. DAO significa Data Access Object (Objeto de Acesso a Dados), pois a
//classe e controle indexMB criará um objeto do tipo Mp3DAO para poder enviar
//e buscar dados da tabela mp3 do banco de dados, através dos métodos descritos
//nesta classe.
public class Mp3DAO {

    private Connection con;          //Objeto utilizado para o estabelecimento de uma conexão.
    private PreparedStatement pstm;  //Objeto utilizado para o envio de instruções SQL.  
    private ResultSet rs;            //Objeto utilizado para guardar o resultado de um SELECT.
    private GeneroDAO generoDAO;     //Objeto utilizado para pesquisar gêneros na tabela de generos.
    
    public static final int ORDEM_POR_ID = 0;
    public static final int ORDEM_POR_MUSICA = 1;
    public static final int ORDEM_POR_MUSICOBANDA = 2;
    public static final int ORDEM_POR_GENERO = 3;
    public static final int ORDEM_POR_ANO = 4;
    
    
    //O construtor da classe cria os recursos necessários para manipulação de dados
    //do banco.
    public Mp3DAO() throws SQLException{
        //Registra o conector com o banco de dados através do DriverManager.
        DriverManager.registerDriver(new org.postgresql.Driver());
        //Aqui criamos uma conexão para o envio de instruções SQL para o PostgreSQL.
        con = DriverManager.getConnection("jdbc:postgresql://localhost/mp3album",
                                          "postgres",
                                          "postgres");        
        generoDAO = new GeneroDAO();  //Cria uma instância de um objeto de acesso a dados a tabela de gêneros.
    }

    //O método salvar envia uma instrução INSERT para o banco de dados PostgreSQL.    
    public void salvar(Mp3 mp3) throws SQLException{
        //O caracter ? indica um parâmetro (valor) que será passado para a instrução.
        pstm = con.prepareStatement(
                "insert into mp3(musica, musicobanda, ano, idgenero) values (?, ?, ?, ?)");
        //Aqui passamos os valores de cada um dos parâmetros, na ordem que estão na instrução
        //SQL, da esquerda para direita.
        pstm.setString(1, mp3.getMusica());
        pstm.setString(2, mp3.getMusicobanda());
        pstm.setInt(3, mp3.getAno());
        pstm.setInt(4, mp3.getGenero().getId());
        pstm.execute();    //Após informar todos os parâmetros, mandamos executar a instrução.        
    }
    
    //O método alterar envia uma instrução UPDATE para o banco de dados PostgreSQL.
    public void alterar(Mp3 mp3) throws SQLException{
        pstm = con.prepareStatement(
                "update mp3 set musica = ?, musicobanda = ?, ano = ?, idgenero = ? where id = ?");
        pstm.setString(1, mp3.getMusica());
        pstm.setString(2, mp3.getMusicobanda());
        pstm.setInt(3, mp3.getAno());
        pstm.setInt(4, mp3.getGenero().getId());
        pstm.setInt(5, mp3.getId());   //Altera de acordo com o ID informado pelo usuário.
        pstm.execute();
    }
    
  //O método excluir envia uma instrução DELETE para o banco de dados PostgreSQL.
    public void excluir(Mp3 mp3) throws SQLException{
        //Deleta o registro da tabela cujo ID é informado no parâmetro da instrução.
        pstm = con.prepareStatement(
                "delete from mp3 where id = ?"); 
        pstm.setInt(1, mp3.getId());  //Exclui de acordo com o ID informado pelo usuário.
        pstm.execute();
    }    
    
  //O método pesquisar será utilizado para buscar um registro da tabela de MP3
    //com base no ID da música, que é a chave primária da tabela MP3.
    public Mp3 pesquisar(int id) throws SQLException{
        Mp3 mp3 = null;
        Genero genero = null;
        pstm = con.prepareStatement("select * from mp3 where id = ?");
        pstm.setInt(1, id);
        rs = pstm.executeQuery();
        if(rs.next()){
            mp3 = new Mp3();
            mp3.setId(rs.getInt("id"));
            mp3.setMusica(rs.getString("musica"));
            mp3.setMusicobanda(rs.getString("musicobanda"));
            mp3.setAno(rs.getInt("ano"));
            genero = generoDAO.pesquisar(rs.getInt("idgenero"));
            mp3.setGenero(genero);
        }
        return mp3;
    }    
    
    //Este método é utilizado pelo IndexMB para alimentar a lista de músicas
    //que será apresentada na página index.xhtml através do componente
    //<h:dataTable>
    public List<Mp3> getTodosMp3(int ordem) throws SQLException{
        ArrayList<Mp3> musicas = new ArrayList();  //Cria uma lista de músicas vazia.
        Mp3 mp3 = null;  //Define um objeto do tipo Mp3 também vazio.
        Genero genero = null; //Define um objeto do tipo Genero vazio.
        
        switch(ordem){
            case ORDEM_POR_ID:
                pstm = con.prepareStatement("select * from mp3 order by id");
                break;
            case ORDEM_POR_MUSICA:
                pstm = con.prepareStatement("select * from mp3 order by musica");
                break;
            case ORDEM_POR_MUSICOBANDA:
                pstm = con.prepareStatement("select * from mp3 order by musicobanda");
                break;
            case ORDEM_POR_GENERO:
                pstm = con.prepareStatement("select * from mp3 order by genero");
                break;
            case ORDEM_POR_ANO:
                pstm = con.prepareStatement("select * from mp3 order by ano");                              
        }              
        
        rs = pstm.executeQuery();  //Executa o comando select.
        while(rs.next()){          //Percorre todas as músicas retornadas pelo select.
            mp3 = new Mp3();
            mp3.setId(rs.getInt("id"));
            mp3.setMusica(rs.getString("musica"));
            mp3.setMusicobanda(rs.getString("musicobanda"));
            mp3.setAno(rs.getInt("ano"));
            genero = generoDAO.pesquisar(rs.getInt("idgenero"));  //busca o objeto genero pelo id.
            mp3.setGenero(genero); 
            musicas.add(mp3);   //Adiciona cada música na lista de músicas.
        }              
        return musicas;
    }



}
