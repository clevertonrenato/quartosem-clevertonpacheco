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
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Pedido;
import modelo.PedidoItem;
import seguranca.Acesso;
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

        Acesso acesso = new Acesso();
        //int idusuario = acesso.getUsuarioSession().getId();
        int idusuario = 1;
        con.setAutoCommit(false);
        Savepoint restauracao = con.setSavepoint();

        try {
            pstm = con.prepareStatement("insert into pedidos (data, idcliente) values(?, ?)");
            pstm.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
            pstm.setInt(2, idusuario);
            pstm.execute();
            pstm = con.prepareStatement("select nextval('pedidos_idpedido_seq') as seq");
            ResultSet rs = pstm.executeQuery();
            rs.next();
            int numeroultimopedido = rs.getInt("seq") - 1;
            
            for (PedidoItem pedidoitem : pedido.getPedidoitem()) {
                pstm = con.prepareStatement(
                        "insert into pedidoitem(idpedido, idproduto, quantidade, vlr_unit, obs) values ( ?, ?, ?, ?, ?)");

                pstm.setInt(1, numeroultimopedido);
                pstm.setInt(2, pedidoitem.getProduto().getId());
                pstm.setInt(3, pedidoitem.getQuantidade());
                pstm.setFloat(4, pedidoitem.getUnitario());
                pstm.setString(5, pedidoitem.getObservacao());
                pstm.execute();                
            }
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            con.rollback(restauracao);
            System.out.println("Erro na tentativa de gravação do pedido: " + e.getMessage());
        }

    }

       // pstm = con.prepareStatement("insert into pedidoitem (vlr_unit)SELECT valor from produtos");
    // pstm.execute(); 



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
//    public List<Pedido> getTodosPedido(int ordem) throws SQLException {
//        ArrayList<Pedido> pedidos = new ArrayList();  //Cria uma lista de músicas vazia.
//        Pedido pedido = null;  //Define um objeto do tipo Mp3 também vazio.
//        //Genero genero = null; //Define um objeto do tipo Genero vazio.
//
//        switch (ordem) {
//            case ORDEM_POR_NUMERO:
//                pstm = con.prepareStatement("select * from pedidos order by idpedido");
//                break;
//
//        }
//
//        rs = pstm.executeQuery();  //Executa o comando select.
//        while (rs.next()) {          //Percorre todas as músicas retornadas pelo select.
//            pedido = new Pedido();
//            pedido.setQtdlanche(rs.getInt("idcliente"));
//            pedido.setObs(rs.getString("total"));
//
//            pedidos.add(pedido);
//        }
//        return pedidos;
//    }

    /* public void salvar(Pedido pedido) throws SQLException {
     pstm = con.prepareStatement("insert into pedidos (total, data) values ( ?, ?)");
     pstm.setLong(1, pedido.getNumero());
     pstm.setInt(2, pedido.getQtdlanche());
     pstm.execute();

     }*/
}
