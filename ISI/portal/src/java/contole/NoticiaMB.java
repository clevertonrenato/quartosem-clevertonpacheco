/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contole;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Noticia;
import persistencia.NoticiaDAO;

/**
 *
 * @author Renato
 */
@ManagedBean
@RequestScoped
public class NoticiaMB {
private Noticia noticia;
    
    private NoticiaDAO noticiaDAO;
    
     List<Noticia> noticias;

    public NoticiaMB() throws SQLException {
        
         noticia = new Noticia();        
        noticiaDAO = new NoticiaDAO();
      noticias = noticiaDAO.getTodosNoticia();
    }
    
     public List<Noticia> getNoticias() {
        return noticias;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }
    
     public void pesquisar() throws SQLException{
        noticia = noticiaDAO.pesquisar(noticia.getId());
    }
     
    

     /*  public List<Noticia> listar() throws ParseException {

        List<Noticia> lista = new ArrayList<Noticia>();

        Noticia not = new Noticia();

     String datahora = "12-03-2015 03:10 PM";
        DateFormat formatado = new SimpleDateFormat("dd-MM-YYYY hh:mm aa");
        not.setData_hora(formatado.parse(datahora));

        not.setFonte("site G1");
        not.setN("Gabrielli diz que era 'impossível' identificar corrupção na estatal");
        not.setTitulo("Cpi da petrobras");

        //add  na lista
        lista.add(not);

        not = new Noticia();
        datahora = "13-09-2015 03:10 PM";
        not.setData_hora(formatado.parse(datahora));

        not.setFonte("site G1");
        not.setNoticia("Brasil tem base sólida contra crise, diz Dilma");
        not.setTitulo("Presidência");

        lista.add(not);

       not = new Noticia();
        datahora = "13-09-2015 03:10 PM";
        not.setData_hora(formatado.parse(datahora));

        not.setFonte("site G1");
        not.setNoticia("Brasil tem base sólida contra crise, diz Dilma");
        not.setTitulo("Presidência");

        lista.add(not);
        return lista;
    }*/


}
