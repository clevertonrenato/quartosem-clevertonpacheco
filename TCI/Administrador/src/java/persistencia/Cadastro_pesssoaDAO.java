/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Cadastro_pessoa;

/**
 *
 * @author emilio
 */
public class Cadastro_pesssoaDAO extends Conexao {

    public Cadastro_pesssoaDAO() throws SQLException {
        super();
    }

    public static final int ORDEM_POR_ID = 0;
    public static final int ORDEM_POR_NOME = 1;

    public void inserir(Cadastro_pessoa usuario) throws SQLException {
        this.setPreparedStatement("insert into cliente(cpf, numero, bairro, complemento, nome, email, telefone, endereco, confirmasenha) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        this.getPreparedStatement().setLong(1, usuario.getCpf());
        this.getPreparedStatement().setInt(2, usuario.getNumero());
        this.getPreparedStatement().setString(3, usuario.getComplemento());
        this.getPreparedStatement().setString(4, usuario.getNome());
        this.getPreparedStatement().setString(5, usuario.getEmail());
        this.getPreparedStatement().setString(6, usuario.getTelefone());
        this.getPreparedStatement().setString(7, usuario.getEndereco());
        this.getPreparedStatement().setString(8, usuario.getConf_senha());
        this.getPreparedStatement().executeUpdate();
    }

    public void editar(Cadastro_pessoa usuario) throws SQLException {
        this.setPreparedStatement("update usuario set login=?, senha=? where id=?");
        this.getPreparedStatement().setLong(1, usuario.getCpf());
        this.getPreparedStatement().setInt(2, usuario.getNumero());
        this.getPreparedStatement().setString(3, usuario.getComplemento());
        this.getPreparedStatement().setString(4, usuario.getNome());
        this.getPreparedStatement().setString(5, usuario.getEmail());
        this.getPreparedStatement().setString(6, usuario.getTelefone());
        this.getPreparedStatement().setString(7, usuario.getEndereco());
        this.getPreparedStatement().setString(8, usuario.getConf_senha());
        this.getPreparedStatement().setInt(9, usuario.getId());
        this.getPreparedStatement().executeUpdate();
    }

    public void excluir(Cadastro_pessoa usuario) throws SQLException {
        this.setPreparedStatement("delete from usuario where id=?");
        this.getPreparedStatement().setInt(1, usuario.getId());
        this.getPreparedStatement().executeUpdate();
    }

    public Cadastro_pessoa getUsuario(int id) throws SQLException {
        Cadastro_pessoa usuario = null;
        this.setPreparedStatement("select * from usuario where id=?");
        this.getPreparedStatement().setInt(1, id);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        if (this.getResultSet().next()) {
            usuario = new Cadastro_pessoa();
            usuario.setId(this.getResultSet().getInt("id"));
            usuario.setEmail(this.getResultSet().getString("email"));
        }
        return usuario;
    }

    public Cadastro_pessoa getUsuarioPorLogin(String email) throws SQLException {
        Cadastro_pessoa usuario = null;
        this.setPreparedStatement("select * from admin where email=?");
        this.getPreparedStatement().setString(1, email);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        if (this.getResultSet().next()) {
            usuario = new Cadastro_pessoa();
            usuario.setId(this.getResultSet().getInt("id"));
            usuario.setEmail(this.getResultSet().getString("email"));
            usuario.setConf_senha(this.getResultSet().getString("confirmasenha"));
        }
        return usuario;
    }

    public List<Cadastro_pessoa> getUsuarios() throws SQLException {
        List<Cadastro_pessoa> usuarios = new ArrayList<Cadastro_pessoa>();
        this.setPreparedStatement("select * from admin order by email");
        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            Cadastro_pessoa usuario = new Cadastro_pessoa();
            usuario.setId(this.getResultSet().getInt("id"));
            usuario.setEmail(this.getResultSet().getString("email"));
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public Cadastro_pessoa verificaLogin(Cadastro_pessoa usuario) throws SQLException {
        Cadastro_pessoa usr = this.getUsuarioPorLogin(usuario.getEmail());
        if (usr != null && usr.getConf_senha().equals(usuario.getConf_senha())) {
            
            return usr;
        }
        return null;
    }
    
   /*  public List<Usuario> getTodosCadastro_pessoa(int ordem) throws SQLException {
        ArrayList<Usuario> informacoes = new ArrayList();  //Cria uma lista de músicas vazia.
        Cadastro_pessoa cadastro = null;  //Define um objeto do tipo Mp3 também vazio.
        //Genero genero = null; //Define um objeto do tipo Genero vazio.

        switch (ordem) {
            case ORDEM_POR_ID:
                this.setPreparedStatement("select * from cliente order by id");
                break;
            case ORDEM_POR_NOME:
                this.setPreparedStatement("select * from cliente order by nome");
                break;

        }

      rs = this.setPreparedStatement.executeQuery();  //Executa o comando select.
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
    }*/

}
