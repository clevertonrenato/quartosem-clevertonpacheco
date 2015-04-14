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

/**
 *
 * @author Renato
 */
public class PedidoDAO {

    private Connection con;          //Objeto utilizado para o estabelecimento de uma conexão.
    private PreparedStatement pstm;  //Objeto utilizado para o envio de instruções SQL.  
    private ResultSet rs;            //Objeto utilizado para guardar o resultado de um SELECT.
    //private GeneroDAO generoDAO;     //Objeto utilizado para pesquisar gêneros na tabela de generos.

    public static final int ORDEM_POR_NUMERO = 0;
    public static final int ORDEM_POR_LANCHE = 1;
   // public static final int ORDEM_POR_MUSICOBANDA = 2;
    //public static final int ORDEM_POR_GENERO = 3;
    // public static final int ORDEM_POR_ANO = 4;

    //O construtor da classe cria os recursos necessários para manipulação de dados
    //do banco.
    public PedidoDAO() throws SQLException {
        //Registra o conector com o banco de dados através do DriverManager.
        DriverManager.registerDriver(new org.postgresql.Driver());
        //Aqui criamos uma conexão para o envio de instruções SQL para o PostgreSQL.
        con = DriverManager.getConnection("jdbc:postgresql://localhost/lancheria",
                "postgres",
                "postgres");
        //generoDAO = new GeneroDAO();  //Cria uma instância de um objeto de acesso a dados a tabela de gêneros.
    }

    //O método salvar envia uma instrução INSERT para o banco de dados PostgreSQL.    
    public void salvar(Pedido pedido) throws SQLException {
        //O caracter ? indica um parâmetro (valor) que será passado para a instrução.
        pstm = con.prepareStatement(
                "insert into pedido( lanche, qtdlanche, bebida, qtdbebida, formapagamento) values ( ?, ?, ?, ?, ?)");
        //Aqui passamos os valores de cada um dos parâmetros, na ordem que estão na instrução
        //SQL, da esquerda para direita.
        //pstm.setInt(1, pedido.getNumero());
        pstm.setString(1, pedido.getLanche());
        pstm.setInt(2, pedido.getQtdlanche());
        pstm.setString(3, pedido.getBebida());
        pstm.setInt(4, pedido.getQtdbebida());
        pstm.setString(5, pedido.getFormapagamento());

        pstm.execute();    //Após informar todos os parâmetros, mandamos executar a instrução.        
    }

    //O método alterar envia uma instrução UPDATE para o banco de dados PostgreSQL.
    public void alterar(Pedido pedido) throws SQLException {
        pstm = con.prepareStatement(
                "update pedido set  lanche = ?, qtdlanche = ?, bebida = ?, qtdbebida = ?, formapagamento = ? where numero = ?");

        pstm.setString(1, pedido.getLanche());
        pstm.setInt(2, pedido.getQtdlanche());
        pstm.setString(3, pedido.getBebida());
        pstm.setInt(4, pedido.getQtdbebida());
        pstm.setString(5, pedido.getFormapagamento());
        pstm.setInt(6, pedido.getNumero()); //Altera de acordo com o ID informado pelo usuário.
        pstm.execute();
    }

    //O método excluir envia uma instrução DELETE para o banco de dados PostgreSQL.
    public void excluir(Pedido pedido) throws SQLException {
        //Deleta o registro da tabela cujo ID é informado no parâmetro da instrução.
        pstm = con.prepareStatement(
                "delete from pedido where numero = ?");
        pstm.setInt(1, pedido.getNumero());  //Exclui de acordo com o ID informado pelo usuário.
        pstm.execute();
    }

    //O método pesquisar será utilizado para buscar um registro da tabela de MP3
    //com base no ID da música, que é a chave primária da tabela MP3.
    public Pedido pesquisar(int numero) throws SQLException {
        Pedido pedido = null;
        //Genero genero = null;
        pstm = con.prepareStatement("select * from pedido where numero = ?");
        pstm.setInt(1, numero);
        rs = pstm.executeQuery();
        if (rs.next()) {
            pedido = new Pedido();
            pedido.setNumero(rs.getInt("numero"));
            pedido.setLanche(rs.getString("lanche"));
            pedido.setQtdlanche(rs.getInt("qtdlanche"));
            pedido.setBebida(rs.getString("bebida"));
            pedido.setQtdbebida(rs.getInt("qtdbebida"));
            pedido.setFormapagamento(rs.getString("formapagamento"));
           
            //genero = generoDAO.pesquisar(rs.getInt("idgenero"));
            //pedido.setGenero(genero);
        }
        return pedido;
    }

    //Este método é utilizado pelo IndexMB para alimentar a lista de músicas
    //que será apresentada na página index.xhtml através do componente
    //<h:dataTable>
    public List<Pedido> getTodosPedido(int ordem) throws SQLException {
        ArrayList<Pedido> pedidos = new ArrayList();  //Cria uma lista de músicas vazia.
        Pedido pedido = null;  //Define um objeto do tipo Mp3 também vazio.
        //Genero genero = null; //Define um objeto do tipo Genero vazio.

        switch (ordem) {
            case ORDEM_POR_NUMERO:
                pstm = con.prepareStatement("select * from pedido order by numero");
                break;
            case ORDEM_POR_LANCHE:
                pstm = con.prepareStatement("select * from pedido order by lanche");
                break;

        }

        rs = pstm.executeQuery();  //Executa o comando select.
        while (rs.next()) {          //Percorre todas as músicas retornadas pelo select.
            pedido = new Pedido();
            pedido.setNumero(rs.getInt("numero"));
            pedido.setLanche(rs.getString("lanche"));
            pedido.setQtdlanche(rs.getInt("qtdlanche"));
            pedido.setBebida(rs.getString("bebida"));
            pedido.setQtdbebida(rs.getInt("qtdbebida"));
            pedido.setFormapagamento(rs.getString("formapagamento"));
            //genero = generoDAO.pesquisar(rs.getInt("idgenero"));  //busca o objeto genero pelo id.
            //mp3.setGenero(genero); 
            pedidos.add(pedido);   //Adiciona cada música na lista de músicas.
        }
        return pedidos;
    }

}
