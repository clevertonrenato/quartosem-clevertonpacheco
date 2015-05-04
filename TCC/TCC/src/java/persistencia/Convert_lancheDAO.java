
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Convert_lancheDAO {
    
  /*   private Connection con;          //Objeto utilizado para o estabelecimento de uma conexão.
    private PreparedStatement pstm;  //Objeto utilizado para o envio de instruções SQL.  
    private ResultSet rs;            //Objeto utilizado para guardar o resultado de um SELECT.
       
    
    //O construtor da classe cria os recursos necessários para manipulação de dados
    //do banco.
    public Convert_lancheDAO() throws SQLException{
        //Registra o conector com o banco de dados através do DriverManager.
        DriverManager.registerDriver(new org.postgresql.Driver());
        //Aqui criamos uma conexão para o envio de instruções SQL para o PostgreSQL.
        con = DriverManager.getConnection("jdbc:postgresql://localhost/lancheria",
                                          "postgres",
                                          "postgres");        
    }

    //O método salvar envia uma instrução INSERT para o banco de dados PostgreSQL.    
    public void salvar(Convert_lanche convert_lanche) throws SQLException{
        //O caracter ? indica um parâmetro (valor) que será passado para a instrução.
        pstm = con.prepareStatement(
                "insert into convert_lanche(descricao) values (?)");
        //Aqui passamos os valores de cada um dos parâmetros, na ordem que estão na instrução
        //SQL, da esquerda para direita.
        pstm.setString(1, convert_lanche.getDescricao());
        pstm.execute();    //Após informar todos os parâmetros, mandamos executar a instrução.        
    }
    
    //O método alterar envia uma instrução UPDATE para o banco de dados PostgreSQL.
    public void alterar(Convert_lanche convert_lanche) throws SQLException{
        pstm = con.prepareStatement(
                "update convert_lanche set descricao = ? where id = ?");
        pstm.setString(1, convert_lanche.getDescricao());
        pstm.setInt(2, convert_lanche.getId());
        pstm.execute();
    }
    
  //O método excluir envia uma instrução DELETE para o banco de dados PostgreSQL.
    public void excluir(Convert_lanche convert_lanche) throws SQLException{
        //Deleta o registro da tabela cujo ID é informado no parâmetro da instrução.
        pstm = con.prepareStatement(
                "delete from convert_lanche where id = ?"); 
        pstm.setInt(1, convert_lanche.getId());  //Exclui de acordo com o ID informado pelo usuário.
        pstm.execute();
    }    
    
  //O método pesquisar será utilizado para buscar um registro da tabela de MP3
    //com base no ID da música, que é a chave primária da tabela MP3.
    public Convert_lanche pesquisar(int id) throws SQLException{
        Convert_lanche convert_lanche = null;
        pstm = con.prepareStatement("select * from convert_lanche where id = ?");
        pstm.setInt(1, id);
        rs = pstm.executeQuery();
        if(rs.next()){
            convert_lanche = new Convert_lanche();
            convert_lanche.setId(rs.getInt("id"));
            convert_lanche.setDescricao(rs.getString("descricao"));
        }
        return convert_lanche;
    }    
    
    public Convert_lanche pesquisarPorDescricao(String descricao) throws SQLException{
        Convert_lanche convert_lanche = null;
        pstm = con.prepareStatement("select * from convert_lanche where descricao = ?");
        pstm.setString(1, descricao);
        rs = pstm.executeQuery();
        if(rs.next()){
            convert_lanche = new Convert_lanche();
            convert_lanche.setId(rs.getInt("id"));
            convert_lanche.setDescricao(rs.getString("descricao"));
        }
        
        return convert_lanche;
    }
    
    //Este método é utilizado pelo IndexMB para alimentar a lista de músicas
    //que será apresentada na página index.xhtml através do componente
    //<h:dataTable>
    public List<Convert_lanche> getTodosConvert_lanches() throws SQLException{
        ArrayList<Convert_lanche> convert_lanches = new ArrayList();  //Cria uma lista de músicas vazia.
        Convert_lanche convert_lanche = null;  //Define um objeto do tipo Mp3 também vazio.       
        pstm = con.prepareStatement("select * from convert_lanche order by descricao");       
        rs = pstm.executeQuery();  //Executa o comando select.
        while(rs.next()){          //Percorre todas as músicas retornadas pelo select.
            convert_lanche = new Convert_lanche();
            convert_lanche.setId(rs.getInt("id"));
            convert_lanche.setDescricao(rs.getString("descricao"));
            convert_lanches.add(convert_lanche);   //Adiciona cada música na lista de músicas.
        }              
        return convert_lanches;
    }
    
    
}*/

}
