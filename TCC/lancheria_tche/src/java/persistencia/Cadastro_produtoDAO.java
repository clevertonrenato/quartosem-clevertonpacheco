/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cadastro_produto;

/**
 *
 * @author Renato
 */
public class Cadastro_produtoDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    //private GeneroDAO generoDAO;     //Objeto utilizado para pesquisar gêneros na tabela de generos.

    public static final int ORDEM_POR_ID = 0;

    public Cadastro_produtoDAO() throws SQLException {
        //Registra o conector com o banco de dados através do DriverManager.
        DriverManager.registerDriver(new org.postgresql.Driver());
        //Aqui criamos uma conexão para o envio de instruções SQL para o PostgreSQL.
        con = DriverManager.getConnection("jdbc:postgresql://localhost/lancheria1",
                "postgres",
                "postgres");
        //generoDAO = new GeneroDAO();  //Cria uma instância de um objeto de acesso a dados a tabela de gêneros.
    }

    //O método salvar envia uma instrução INSERT para o banco de dados PostgreSQL.    
    public void salvar(Cadastro_produto cadprod) throws SQLException {
        //O caracter ? indica um parâmetro (valor) que será passado para a instrução.
        pstm = con.prepareStatement(
                "insert into produtos( nomeprod, valor, tipo) values (?, ?, ?)");

        pstm.setString(1, cadprod.getLanche());
        pstm.setFloat(2, cadprod.getPrecolanche());
        pstm.setString(3, cadprod.getItens());
        pstm.execute();    //Após informar todos os parâmetros, mandamos executar a instrução.  
        
         //pstm = con.prepareStatement("INSERT INTO pedidoitem(idproduto) SELECT idproduto from produtos;");
       
         
    }

    //O método alterar envia uma instrução UPDATE para o banco de dados PostgreSQL.
    public void alterar(Cadastro_produto Cadprod) throws SQLException {
        pstm = con.prepareStatement(
                "update produtos set  nomeprod = ?, valor = ?, tipo = ? where idproduto = ?");

        pstm.setString(1, Cadprod.getLanche());
        pstm.setFloat(2, Cadprod.getPrecolanche());
        pstm.setString(3, Cadprod.getItens());
        pstm.setInt(4, Cadprod.getId());
        pstm.execute();
    }

    //O método excluir envia uma instrução DELETE para o banco de dados PostgreSQL.
    public void excluir(Cadastro_produto Cadprod) throws SQLException {
        //Deleta o registro da tabela cujo ID é informado no parâmetro da instrução.
        pstm = con.prepareStatement(
                "delete from produtos where idproduto = ?");
        pstm.setInt(1, Cadprod.getId());  //Exclui de acordo com o ID informado pelo usuário.
        pstm.execute();
    }

    //O método pesquisar será utilizado para buscar um registro da tabela de MP3
    //com base no ID da música, que é a chave primária da tabela MP3.
    public Cadastro_produto pesquisar(int id) throws SQLException {
        Cadastro_produto Cadprod = null;
        //Genero genero = null;
        pstm = con.prepareStatement("select * from produtos where idproduto = ?");
        pstm.setInt(1, id);
        rs = pstm.executeQuery();
        if (rs.next()) {
            Cadprod = new Cadastro_produto();
            Cadprod.setId(rs.getInt("idproduto"));
            Cadprod.setLanche(rs.getString("nomeprod"));
            Cadprod.setPrecolanche(rs.getFloat("valor"));
           // Cadprod.setBebida(rs.getString("bebida"));
           // Cadprod.setPrecobebida(rs.getFloat("vbebida"));
            Cadprod.setItens(rs.getString("tipo"));

            //genero = generoDAO.pesquisar(rs.getInt("idgenero"));
            //Cadprod.setGenero(genero);
        }
        return Cadprod;
    }

    
    public Cadastro_produto pesquisarPorLanche(String lanche) throws SQLException {
        Cadastro_produto Cadprod = null;
        //Genero genero = null;
        pstm = con.prepareStatement("select * from produtos where nomeprod = ?");
        pstm.setString(1, lanche);
        rs = pstm.executeQuery();
        if (rs.next()) {
            Cadprod = new Cadastro_produto();
            Cadprod.setId(rs.getInt("idproduto"));
            Cadprod.setLanche(rs.getString("nomeprod"));
            Cadprod.setPrecolanche(rs.getFloat("valor"));
           // Cadprod.setBebida(rs.getString("bebida"));
           // Cadprod.setPrecobebida(rs.getFloat("vbebida"));
            Cadprod.setItens(rs.getString("tipo"));

            //genero = generoDAO.pesquisar(rs.getInt("idgenero"));
            //Cadprod.setGenero(genero);
        }
        return Cadprod;
    }
    
    
    
    //Este método é utilizado pelo IndexMB para alimentar a lista de músicas
    //que será apresentada na página index.xhtml através do componente
    //<h:dataTable>
    public List<Cadastro_produto> getTodosCadastro_lanche(int ordem) throws SQLException {
        ArrayList<Cadastro_produto> Cadprods = new ArrayList();  //Cria uma lista de músicas vazia.
        Cadastro_produto Cadprod = null;  //Define um objeto do tipo Mp3 também vazio.
        //Genero genero = null; //Define um objeto do tipo Genero vazio.

        switch (ordem) {
            case ORDEM_POR_ID:
                pstm = con.prepareStatement("select * from produtos order by tipo desc");
                break;

        }

        rs = pstm.executeQuery();  //Executa o comando select.
        while (rs.next()) {          //Percorre todas as músicas retornadas pelo select.
            Cadprod = new Cadastro_produto();
            Cadprod.setId(rs.getInt("idproduto"));
            Cadprod.setLanche(rs.getString("nomeprod"));
            Cadprod.setPrecolanche(rs.getFloat("valor"));
           // Cadprod.setBebida(rs.getString("bebida"));
            //Cadprod.setPrecobebida(rs.getFloat("vbebida"));
            Cadprod.setItens(rs.getString("tipo"));
            //genero = generoDAO.pesquisar(rs.getInt("idgenero"));  //busca o objeto genero pelo id.
            //mp3.setGenero(genero); 
            Cadprods.add(Cadprod);   //Adiciona cada música na lista de músicas.
        }
        return Cadprods;
    }

}
