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
import modelo.Pedido;
//import modelo.PedidoLanche;

/**
 *
 * @author Renato
 */
public class PedidoDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    public static final int ORDEM_POR_NUMERO = 0;
    //public static final int ORDEM_POR_LANCHE = 1;

    //O construtor da classe cria os recursos necessários para manipulação de dados
    //do banco.
    public PedidoDAO() throws SQLException {
        //Registra o conector com o banco de dados através do DriverManager.
        DriverManager.registerDriver(new org.postgresql.Driver());
        //Aqui criamos uma conexão para o envio de instruções SQL para o PostgreSQL.
        con = DriverManager.getConnection("jdbc:postgresql://localhost/lancheria1",
                "postgres",
                "postgres");
        //generoDAO = new GeneroDAO();  //Cria uma instância de um objeto de acesso a dados a tabela de gêneros.
    }

    public void salvar(Pedido pedido) throws SQLException {
        pstm = con.prepareStatement(
                "insert into pedidoitem( quantidade, obs, idpedido) values ( ?, ?, ? )");
        //pstm.setInt(1, pedido.getNumero());
        pstm.setInt(1, pedido.getQtdlanche());
        pstm.setString(2, pedido.getObs());
        pstm.setInt(3,pedido.getIdpedido());
        pstm.execute();

        pstm = con.prepareStatement("insert into pedidos (data)SELECT CURRENT_DATE");
        pstm.execute();
        
        
    }

    //O método alterar envia uma instrução UPDATE para o banco de dados PostgreSQL.

    public void alterar(Pedido pedido) throws SQLException {
        pstm = con.prepareStatement("");
        pstm.execute();
    }

    //O método excluir envia uma instrução DELETE para o banco de dados PostgreSQL.
    public void excluir(Pedido pedido) throws SQLException {
        //Deleta o registro da tabela cujo ID é informado no parâmetro da instrução.
        pstm = con.prepareStatement(
                "delete from pedidos where idpedido = ?");
        pstm.setInt(1, pedido.getNumero());  //Exclui de acordo com o ID informado pelo usuário.
        pstm.execute();
    }

    //O método pesquisar será utilizado para buscar um registro da tabela de MP3
    //com base no ID da música, que é a chave primária da tabela MP3.
   /* public Pedido pesquisar(int numero) throws SQLException {
        Pedido pedido = null;
        //Genero genero = null;
        pstm = con.prepareStatement("select * from pedidos where numero = ?");
        pstm.setInt(1, numero);
        rs = pstm.executeQuery();
        if (rs.next()) {
            pedido = new Pedido();
            pedido.setNumero(rs.getInt("idpedido"));
            pedido.setObs(rs.getString("obs"));
            pedido.setQtdlanche(rs.getInt("qtdlanche"));
            
            pedido.setFormapagamento(rs.getString("formapagamento"));

            //genero = generoDAO.pesquisar(rs.getInt("idgenero"));
            //pedido.setGenero(genero);
        }
        return pedido;
    }*/

    //Este método é utilizado pelo IndexMB para alimentar a lista de músicas
    //que será apresentada na página index.xhtml através do componente
    //<h:dataTable>
    public List<Pedido> getTodosPedido(int ordem) throws SQLException {
        ArrayList<Pedido> pedidos = new ArrayList();  //Cria uma lista de músicas vazia.
        Pedido pedido = null;  //Define um objeto do tipo Mp3 também vazio.
        //Genero genero = null; //Define um objeto do tipo Genero vazio.

        switch (ordem) {
            case ORDEM_POR_NUMERO:
                pstm = con.prepareStatement("select * from pedidos order by idpedido");
                break;
           

        }

        rs = pstm.executeQuery();  //Executa o comando select.
        while (rs.next()) {          //Percorre todas as músicas retornadas pelo select.
            pedido = new Pedido();
            pedido.setQtdlanche(rs.getInt("idcliente"));
            pedido.setObs(rs.getString("total"));
            

            pedidos.add(pedido);
        }
        return pedidos;
    }

    /* public void salvar(Pedido pedido) throws SQLException {
     pstm = con.prepareStatement("insert into pedidos (total, data) values ( ?, ?)");
     pstm.setLong(1, pedido.getNumero());
     pstm.setInt(2, pedido.getQtdlanche());
     pstm.execute();

     }*/
}
