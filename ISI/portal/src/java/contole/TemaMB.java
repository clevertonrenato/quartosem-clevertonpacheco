/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contole;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Noticia;
import modelo.Tema;
import modelo.WebService;
import persistencia.TemaDAO;

/**
 *
 * @author Renato
 */
@ManagedBean
@RequestScoped
public class TemaMB {

   private Tema tema;
    
    private TemaDAO temaDAO;
    
     List<Tema> temas;

    public TemaMB() throws SQLException {
        
        temaDAO = new TemaDAO();
      temas = temaDAO.getTodosTema();
      tema = temas.get(0);
    }
    
     public List<Tema> getTemas() {
        return temas;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
    
     public void pesquisar() throws SQLException, Exception{
        
          WebService ws = new WebService();
         String retorno = ws.sendGet("http://localhost:8080/PortalNoticiasWS/webresources/portal/getTema");
         Gson g = new Gson();
        
         java.lang.reflect.Type tipo = new TypeToken<Tema>(){}.getType();
         
         tema = g.fromJson(retorno, tipo);
      
    }
    
}
