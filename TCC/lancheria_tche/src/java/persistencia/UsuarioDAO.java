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
public class UsuarioDAO extends Conexao {

    public UsuarioDAO() throws SQLException {
        super();
    }

    public static final int ORDEM_POR_ID = 0;
    public static final int ORDEM_POR_NOME = 1;

    public void inserir(Cadastro_pessoa usuario) throws SQLException {
        this.setPreparedStatement("insert into cliente(cpf, numero, bairro, complemento, nome, email, telefone, endereco, confirmasenha) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        this.getPreparedStatement().setString(1, usuario.getCpf());
        this.getPreparedStatement().setString(2, usuario.getNumero());
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
        this.getPreparedStatement().setString(1, usuario.getCpf());
        this.getPreparedStatement().setString(2, usuario.getNumero());
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
        this.setPreparedStatement("select * from cliente where email=?");
        this.getPreparedStatement().setString(1, email);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        if (this.getResultSet().next()) {
            usuario = new Cadastro_pessoa();
            usuario.setId(this.getResultSet().getInt("id"));
            usuario.setEmail(this.getResultSet().getString("email"));
            usuario.setConf_senha(this.getResultSet().getString("confirmasenha"));
            usuario.setNome(this.getResultSet().getString("nome"));

        }
        return usuario;
    }

    public List<Cadastro_pessoa> getUsuarios() throws SQLException {
        List<Cadastro_pessoa> usuarios = new ArrayList<Cadastro_pessoa>();
        this.setPreparedStatement("select * from cliente order by email");
        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            Cadastro_pessoa usuario = new Cadastro_pessoa();
            usuario.setId(this.getResultSet().getInt("id"));
            usuario.setEmail(this.getResultSet().getString("email"));
            usuario.setNome(this.getResultSet().getString("nome"));
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

}
