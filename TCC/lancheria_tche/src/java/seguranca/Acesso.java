/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguranca;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Cadastro_pessoa;

/**
 *
 * @author emilio
 */
public class Acesso {

    public Acesso() {

    }

    public void putUsuarioSession(Cadastro_pessoa usuario) {
        //Joga o usuário na sessão.
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);
        sessaoHttp.setAttribute("usuarioLogado", usuario);
    }

    public Cadastro_pessoa getUsuarioSession() {
        //Pega o usuário da sessão.
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);
        if (((Cadastro_pessoa) sessaoHttp.getAttribute("usuarioLogado")) != null) {
            return (Cadastro_pessoa) sessaoHttp.getAttribute("usuarioLogado");
        }
        return null;
    }

    public void delUsuarioSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);
        sessaoHttp.removeAttribute("usuarioLogado");
    }

    public void putUsuarioCookie(Cadastro_pessoa usuario) {
        HttpServletResponse resposta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        Cookie cookie = new Cookie("usuario", usuario.getEmail());
        cookie.setComment("Usuário logado no sistema.");        
        resposta.addCookie(cookie);
    }

    public String getUsuarioCookie() {
        String usuario = null;
        HttpServletRequest requisicao = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Cookie[] cookies = requisicao.getCookies();

        for (int x = 0; x < cookies.length; x++) {
            if (cookies[x].getName().trim().equalsIgnoreCase("usuario")) {
                usuario = cookies[x].getValue();
                break;
            }
        }
        return usuario;
    }

}
