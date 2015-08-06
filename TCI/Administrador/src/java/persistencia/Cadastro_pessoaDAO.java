package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cadastro_pessoa;
import modelo.Pedido;

/**
 *
 * @author Renato
 */
public class Cadastro_pessoaDAO {

    private Connection con;          
    private PreparedStatement pstm;    
    private ResultSet rs;            

    public static final int ORDEM_POR_ID = 0;
    public static final int ORDEM_POR_NOME = 1;
 

   
    public Cadastro_pessoaDAO() throws SQLException {
        con = DriverManager.getConnection("jdbc:postgresql://localhost/lancheria1",
                "postgres",
                "postgres");
      
    }
   
    public void salvar(Cadastro_pessoa cadastro) throws SQLException {
     
        pstm = con.prepareStatement(
                "insert into cliente(cpf, numero, bairro, complemento, nome, email, telefone, endereco, confirmasenha) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
       
        pstm.setLong(1, cadastro.getCpf());
        pstm.setInt(2, cadastro.getNumero());
        pstm.setString(3, cadastro.getBairro());
        pstm.setString(4, cadastro.getComplemento());
        pstm.setString(5, cadastro.getNome());
        pstm.setString(6, cadastro.getEmail());
        pstm.setString(7, cadastro.getTelefone());
        pstm.setString(8, cadastro.getEndereco());
        pstm.setString(9, cadastro.getConf_senha());
        pstm.execute();      

      
    }

    public void salvar(Pedido pedido) throws SQLException {
        pstm = con.prepareStatement("insert into pedidos(idcliente) values(?) ");
       // pstm.setInt(1, pedido.getIdcliente());
        pstm.execute();        
    }

    public void alterar(Cadastro_pessoa cadastro) throws SQLException {
        pstm = con.prepareStatement(
                "update cliente set numero = ?, bairro = ?, complemento = ?, nome = ?, email = ?, telefone = ?, endereco =?, confirmasenha = ?, cpf = ? where id = ?");
        pstm.setInt(1, cadastro.getNumero());
        pstm.setString(2, cadastro.getBairro());
        pstm.setString(3, cadastro.getComplemento());
        pstm.setString(4, cadastro.getNome());
        pstm.setString(5, cadastro.getEmail());
        pstm.setString(6, cadastro.getTelefone());
        pstm.setString(7, cadastro.getEndereco());
        pstm.setString(8, cadastro.getConf_senha());
        pstm.setLong(9, cadastro.getCpf());
        pstm.setInt(10, cadastro.getId());
        pstm.execute();
    }

    public void excluir(Cadastro_pessoa cadastro) throws SQLException {
        pstm = con.prepareStatement("delete from cliente where id = ?");
        pstm.setInt(1, cadastro.getId()); 
        pstm.executeUpdate();
    }

  
    public Cadastro_pessoa pesquisar(int id) throws SQLException {
        Cadastro_pessoa cadastro = null;
        //Genero genero = null;
        pstm = con.prepareStatement("select * from cliente where id = ?");
        pstm.setInt(1, id);
        rs = pstm.executeQuery();
        if (rs.next()) {
            cadastro = new Cadastro_pessoa();
            cadastro.setId(rs.getInt("id"));
            cadastro.setNumero(rs.getInt("numero"));
            cadastro.setBairro(rs.getString("bairro"));
            cadastro.setComplemento(rs.getString("complemento"));
            cadastro.setNome(rs.getString("nome"));
            cadastro.setEmail(rs.getString("email"));
            cadastro.setTelefone(rs.getString("telefone"));
            cadastro.setEndereco(rs.getString("endereco"));
            cadastro.setCpf(rs.getLong("cpf"));
            // cadastro.setSenha(rs.getString("senha"));
            cadastro.setConf_senha(rs.getString("confirmasenha"));
            //genero = generoDAO.pesquisar(rs.getInt("idgenero"));
            //cadastro.setGenero(genero);
        }
        return cadastro;
    }

    //Este método é utilizado pelo IndexMB para alimentar a lista de músicas
    //que será apresentada na página index.xhtml através do componente
    //<h:dataTable>
    public List<Cadastro_pessoa> getTodosCadastro_pessoa(int ordem) throws SQLException {
        ArrayList<Cadastro_pessoa> informacoes = new ArrayList();  //Cria uma lista de músicas vazia.
        Cadastro_pessoa cadastro = null;  //Define um objeto do tipo Mp3 também vazio.
        //Genero genero = null; //Define um objeto do tipo Genero vazio.

        switch (ordem) {
            case ORDEM_POR_ID:
                pstm = con.prepareStatement("select * from cliente order by id");
                break;
            case ORDEM_POR_NOME:
                pstm = con.prepareStatement("select * from cliente order by nome");
                break;

        }

        rs = pstm.executeQuery();  //Executa o comando select.
        while (rs.next()) {          //Percorre todas as músicas retornadas pelo select.
            cadastro = new Cadastro_pessoa();
            cadastro.setId(rs.getInt("id"));
            cadastro.setNumero(rs.getInt("numero"));
            cadastro.setBairro(rs.getString("bairro"));
            cadastro.setComplemento(rs.getString("complemento"));
            cadastro.setNome(rs.getString("nome"));
            cadastro.setEmail(rs.getString("email"));
            cadastro.setTelefone(rs.getString("telefone"));
            cadastro.setEndereco(rs.getString("endereco"));
            cadastro.setCpf(rs.getLong("cpf"));
            //cadastro.setSenha(rs.getString("senha"));
            cadastro.setConf_senha(rs.getString("confirmasenha"));
            //genero = generoDAO.pesquisar(rs.getInt("idgenero"));  //busca o objeto genero pelo id.
            //mp3.setGenero(genero); 
            informacoes.add(cadastro);   //Adiciona cada música na lista de músicas.
        }
        return informacoes;
    }

}
