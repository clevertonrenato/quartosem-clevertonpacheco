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
import modelo.Cadastro_bebida;

/**
 *
 * @author Renato
 */
public class Cadastro_bebidaDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    //private GeneroDAO generoDAO;     //Objeto utilizado para pesquisar gêneros na tabela de generos.

    public static final int ORDEM_POR_ID = 0;

    public Cadastro_bebidaDAO() throws SQLException {
        //Registra o conector com o banco de dados através do DriverManager.
        DriverManager.registerDriver(new org.postgresql.Driver());
        //Aqui criamos uma conexão para o envio de instruções SQL para o PostgreSQL.
        con = DriverManager.getConnection("jdbc:postgresql://localhost/lancheria",
                "postgres",
                "postgres");
        //generoDAO = new GeneroDAO();  //Cria uma instância de um objeto de acesso a dados a tabela de gêneros.
    }

    //O método salvar envia uma instrução INSERT para o banco de dados PostgreSQL.    
    public void salvar(Cadastro_bebida cadbebida) throws SQLException {
        //O caracter ? indica um parâmetro (valor) que será passado para a instrução.
        pstm = con.prepareStatement(
                "insert into bebida( bebida, vbebida) values (?, ?)");

        pstm.setString(1, cadbebida.getBebida());
        pstm.setFloat(2, cadbebida.getPrecobebida());
        pstm.execute();    //Após informar todos os parâmetros, mandamos executar a instrução.        
    }

    //O método alterar envia uma instrução UPDATE para o banco de dados PostgreSQL.
    public void alterar(Cadastro_bebida Cadbebida) throws SQLException {
        pstm = con.prepareStatement(
                "update bebida set  bebida = ?, vbebida = ? where id = ?");

        pstm.setString(1, Cadbebida.getBebida());
        pstm.setFloat(2, Cadbebida.getPrecobebida());
        pstm.setInt(3, Cadbebida.getId());
        pstm.execute();
    }

    //O método excluir envia uma instrução DELETE para o banco de dados PostgreSQL.
    public void excluir(Cadastro_bebida Cadbebida) throws SQLException {
        //Deleta o registro da tabela cujo ID é informado no parâmetro da instrução.
        pstm = con.prepareStatement(
                "delete from bebida where id = ?");
        pstm.setInt(1, Cadbebida.getId());  //Exclui de acordo com o ID informado pelo usuário.
        pstm.execute();
    }

    //O método pesquisar será utilizado para buscar um registro da tabela de MP3
    //com base no ID da música, que é a chave primária da tabela MP3.
    public Cadastro_bebida pesquisar(int id) throws SQLException {
        Cadastro_bebida Cadbebida = null;
        //Genero genero = null;
        pstm = con.prepareStatement("select * from bebida where id = ?");
        pstm.setInt(1, id);
        rs = pstm.executeQuery();
        if (rs.next()) {
            Cadbebida = new Cadastro_bebida();
            Cadbebida.setId(rs.getInt("id"));
            Cadbebida.setBebida(rs.getString("bebida"));
            Cadbebida.setPrecobebida(rs.getFloat("vbebida"));
            //genero = generoDAO.pesquisar(rs.getInt("idgenero"));
            //Cadprod.setGenero(genero);
        }
        return Cadbebida;
    }

    //Este método é utilizado pelo IndexMB para alimentar a lista de músicas
    //que será apresentada na página index.xhtml através do componente
    //<h:dataTable>
    public List<Cadastro_bebida> getTodosCadastro_bebida(int ordem) throws SQLException {
        ArrayList<Cadastro_bebida> Cadbebidas = new ArrayList();  //Cria uma lista de músicas vazia.
        Cadastro_bebida Cadbebida = null;  //Define um objeto do tipo Mp3 também vazio.
        //Genero genero = null; //Define um objeto do tipo Genero vazio.

        switch (ordem) {
            case ORDEM_POR_ID:

                pstm = con.prepareStatement("select * from bebida order by id");
                break;

        }

        rs = pstm.executeQuery();  //Executa o comando select.
        while (rs.next()) {          //Percorre todas as músicas retornadas pelo select.
            Cadbebida = new Cadastro_bebida();
            Cadbebida.setId(rs.getInt("id"));
            Cadbebida.setBebida(rs.getString("bebida"));
            Cadbebida.setPrecobebida(rs.getFloat("vbebida"));
            //genero = generoDAO.pesquisar(rs.getInt("idgenero"));  //busca o objeto genero pelo id.
            //mp3.setGenero(genero); 
            Cadbebidas.add(Cadbebida);   //Adiciona cada música na lista de músicas.
        }
        return Cadbebidas;
    }

}
