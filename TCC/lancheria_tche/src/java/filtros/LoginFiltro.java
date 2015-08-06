package filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Cadastro_pessoa;

@WebFilter("/visao/*")
public class LoginFiltro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession sessao = ((HttpServletRequest) request).getSession();
        Cadastro_pessoa usr = (Cadastro_pessoa) sessao.getAttribute("usuarioLogado");
        if (usr == null) {
            ((HttpServletResponse) response).sendRedirect("../index.xhtml");
        }
        else
           chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
