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
import modelo.Cadastro_lanche;

/**
 *
 * @author Renato
 */
public class Cadastro_lancheDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    //private GeneroDAO generoDAO;     //Objeto utilizado para pesquisar gêneros na tabela de generos.

    public static final int ORDEM_POR_ID = 0;

    public Cadastro_lancheDAO() throws SQLException {
        //Registra o conector com o banco de dados através do DriverManager.
        DriverManager.registerDriver(new org.postgresql.Driver());
        //Aqui criamos uma conexão para o envio de instruções SQL para o PostgreSQL.
        con = DriverManager.getConnection("jdbc:postgresql://localhost/lancheria",
                "postgres",
                "postgres");
        //generoDAO = new GeneroDAO();  //Cria uma instância de um objeto de acesso a dados a tabela de gêneros.
    }

    //O método salvar envia uma instrução INSERT para o banco de dados PostgreSQL.    
    public void salvar(Cadastro_lanche cadprod) throws SQLException {
        //O caracter ? indica um parâmetro (valor) que será passado para a instrução.
        pstm = con.prepareStatement(
                "insert into produto( lanche, vlanche, bebida, vbebida) values ( ?, ?, ?, ?)");

        pstm.setString(1, cadprod.getLanche());
        pstm.setFloat(2, cadprod.getPrecolanche());
        pstm.setString(3, cadprod.getBebida());
        pstm.setFloat(4, cadprod.getPrecobebida());

        pstm.execute();    //Após informar todos os parâmetros, mandamos executar a instrução.        
    }

    //O método alterar envia uma instrução UPDATE para o banco de dados PostgreSQL.
    public void alterar(Cadastro_lanche Cadprod) throws SQLException {
        pstm = con.prepareStatement(
                "update produto set  lanche = ?, vlanche = ?, bebida = ?, vbebida = ? where id = ?");

        pstm.setString(1, Cadprod.getLanche());
        pstm.setFloat(2, Cadprod.getPrecolanche());
        pstm.setString(3, Cadprod.getBebida());
        pstm.setFloat(4, Cadprod.getPrecobebida());
        pstm.setInt(5, Cadprod.getId());
        pstm.execute();
    }

    //O método excluir envia uma instrução DELETE para o banco de dados PostgreSQL.
    public void excluir(Cadastro_lanche Cadprod) throws SQLException {
        //Deleta o registro da tabela cujo ID é informado no parâmetro da instrução.
        pstm = con.prepareStatement(
                "delete from produto where id = ?");
        pstm.setInt(1, Cadprod.getId());  //Exclui de acordo com o ID informado pelo usuário.
        pstm.execute();
    }

    //O método pesquisar será utilizado para buscar um registro da tabela de MP3
    //com base no ID da música, que é a chave primária da tabela MP3.
    public Cadastro_lanche pesquisar(int id) throws SQLException {
        Cadastro_lanche Cadprod = null;
        //Genero genero = null;
        pstm = con.prepareStatement("select * from produto where id = ?");
        pstm.setInt(1, id);
        rs = pstm.executeQuery();
        if (rs.next()) {
            Cadprod = new Cadastro_lanche();
            Cadprod.setId(rs.getInt("id"));
            Cadprod.setLanche(rs.getString("lanche"));
            Cadprod.setPrecolanche(rs.getFloat("vlanche"));
            Cadprod.setBebida(rs.getString("bebida"));
            Cadprod.setPrecobebida(rs.getFloat("vbebida"));
            

            //genero = generoDAO.pesquisar(rs.getInt("idgenero"));
            //Cadprod.setGenero(genero);
        }
        return Cadprod;
    }

    //Este método é utilizado pelo IndexMB para alimentar a lista de músicas
    //que será apresentada na página index.xhtml através do componente
    //<h:dataTable>
    public List<Cadastro_lanche> getTodosCadastro_lanche(int ordem) throws SQLException {
        ArrayList<Cadastro_lanche> Cadprods = new ArrayList();  //Cria uma lista de músicas vazia.
        Cadastro_lanche Cadprod = null;  //Define um objeto do tipo Mp3 também vazio.
        //Genero genero = null; //Define um objeto do tipo Genero vazio.

        switch (ordem) {
            case ORDEM_POR_ID:
                pstm = con.prepareStatement("select * from produto order by id");
                break;
            

        }

        rs = pstm.executeQuery();  //Executa o comando select.
        while (rs.next()) {          //Percorre todas as músicas retornadas pelo select.
            Cadprod = new Cadastro_lanche();
             Cadprod.setId(rs.getInt("id"));
            Cadprod.setLanche(rs.getString("lanche"));
            Cadprod.setPrecolanche(rs.getFloat("vlanche"));
            Cadprod.setBebida(rs.getString("bebida"));
            Cadprod.setPrecobebida(rs.getFloat("vbebida"));
            //genero = generoDAO.pesquisar(rs.getInt("idgenero"));  //busca o objeto genero pelo id.
            //mp3.setGenero(genero); 
            Cadprods.add(Cadprod);   //Adiciona cada música na lista de músicas.
        }
        return Cadprods;
    }

}
