package controle;

import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Cadastro_pessoa;
import persistencia.UsuarioDAO;
import seguranca.Acesso;

/**
 *
 * @author renato
 */
@ManagedBean
@RequestScoped
public class LoginMB {

    private Cadastro_pessoa usuario;
    private UsuarioDAO usuarioDAO;
    private Acesso acesso;
    private String mensagem;

    public LoginMB() throws SQLException {
        this.usuarioDAO = new UsuarioDAO();
        this.acesso = new Acesso();        
        this.usuario = obtemUsuario();
    }

    public Cadastro_pessoa getUsuario() {
        return usuario;
    }

    public void setUsuario(Cadastro_pessoa usuario) {
        this.usuario = usuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String login() throws SQLException {
        this.usuario = usuarioDAO.verificaLogin(this.usuario);
        if (null==this.usuario) {
            mensagem = "Usuário ou senha inválida, tente novamente.";
            return "/index";
        } else {
            mensagem = "";
        }
        //Joga o usuário na sessão.
        this.acesso.putUsuarioSession(this.usuario);
        this.acesso.putUsuarioCookie(this.usuario);        
        return "visao/pedido";
    }

    public String logout() {
        this.acesso.delUsuarioSession();
        return "/index";
    }

    public Cadastro_pessoa obtemUsuario() {
        Cadastro_pessoa usr = this.acesso.getUsuarioSession();
        if (usr == null) {
            return new Cadastro_pessoa();
        }
        return usr;
    }
}
