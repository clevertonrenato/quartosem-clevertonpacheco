package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cadastro_pessoa;

/**
 *
 * @author Renato
 */
public class Cadastro_pessoaDAO {

    private Connection con;          //Objeto utilizado para o estabelecimento de uma conexão.
    private PreparedStatement pstm;  //Objeto utilizado para o envio de instruções SQL.  
    private ResultSet rs;            //Objeto utilizado para guardar o resultado de um SELECT.
    //private GeneroDAO generoDAO;     //Objeto utilizado para pesquisar gêneros na tabela de generos.

    public static final int ORDEM_POR_ID = 0;
    public static final int ORDEM_POR_NOME = 1;
   // public static final int ORDEM_POR_MUSICOBANDA = 2;
    //public static final int ORDEM_POR_GENERO = 3;
    // public static final int ORDEM_POR_ANO = 4;

    //O construtor da classe cria os recursos necessários para manipulação de dados
    //do banco.
    public Cadastro_pessoaDAO() throws SQLException {
        //Registra o conector com o banco de dados através do DriverManager.
        DriverManager.registerDriver(new org.postgresql.Driver());
        //Aqui criamos uma conexão para o envio de instruções SQL para o PostgreSQL.
        con = DriverManager.getConnection("jdbc:postgresql://localhost/lancheria",
                "postgres",
                "postgres");
        //generoDAO = new GeneroDAO();  //Cria uma instância de um objeto de acesso a dados a tabela de gêneros.
    }

    //O método salvar envia uma instrução INSERT para o banco de dados PostgreSQL.    
    public void salvar(Cadastro_pessoa cadastro) throws SQLException {
        //O caracter ? indica um parâmetro (valor) que será passado para a instrução.
        pstm = con.prepareStatement(
                "insert into cadastro_usuario(cpf, numero, bairro, complemento, nome, email, telefone, endereco, confirmasenha) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        //Aqui passamos os valores de cada um dos parâmetros, na ordem que estão na instrução
        //SQL, da esquerda para direita.
        pstm.setLong(1, cadastro.getCpf());
        pstm.setInt(2, cadastro.getNumero());
        pstm.setString(3, cadastro.getBairro());
        pstm.setString(4, cadastro.getComplemento());
        pstm.setString(5, cadastro.getNome());
        pstm.setString(6, cadastro.getEmail());
        pstm.setString(7, cadastro.getTelefone());
        pstm.setString(8, cadastro.getEndereco());
        //pstm.setString(9, cadastro.getSenha());
        pstm.setString(9, cadastro.getConf_senha());
        pstm.execute();    //Após informar todos os parâmetros, mandamos executar a instrução.        
    }

    //O método alterar envia uma instrução UPDATE para o banco de dados PostgreSQL.
    public void alterar(Cadastro_pessoa cadastro) throws SQLException {
        pstm = con.prepareStatement(
                "update cadastro_usuario set numero = ?, bairro = ?, complemento = ?, nome = ?, email = ?, telefone = ?, endereco =?, confirmasenha = ?, cpf = ? where id = ?");
        pstm.setInt(1, cadastro.getNumero());
        pstm.setString(2, cadastro.getBairro());
        pstm.setString(3, cadastro.getComplemento());
        pstm.setString(4, cadastro.getNome());
        pstm.setString(5, cadastro.getEmail());
        pstm.setString(6, cadastro.getTelefone());
        pstm.setString(7, cadastro.getEndereco());
        //pstm.setString(8, cadastro.getSenha());
        pstm.setString(8, cadastro.getConf_senha());
        pstm.setLong(9, cadastro.getCpf());
        pstm.setInt(10, cadastro.getId());//Altera de acordo com o ID informado pelo usuário.
        pstm.execute();
    }

    //O método excluir envia uma instrução DELETE para o banco de dados PostgreSQL.
    public void excluir(Cadastro_pessoa cadastro) throws SQLException {
        //Deleta o registro da tabela cujo ID é informado no parâmetro da instrução.
        pstm = con.prepareStatement(
                "delete from cadastro_usuario where id = ?");
        pstm.setLong(1, cadastro.getId());  //Exclui de acordo com o ID informado pelo usuário.
        pstm.execute();
    }

    //O método pesquisar será utilizado para buscar um registro da tabela de MP3
    //com base no ID da música, que é a chave primária da tabela MP3.
    public Cadastro_pessoa pesquisar(int id) throws SQLException {
        Cadastro_pessoa cadastro = null;
        //Genero genero = null;
        pstm = con.prepareStatement("select * from cadastro_usuario where id = ?");
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
                pstm = con.prepareStatement("select * from cadastro_usuario order by id");
                break;
            case ORDEM_POR_NOME:
                pstm = con.prepareStatement("select * from cadastro_usuario order by nome");
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
