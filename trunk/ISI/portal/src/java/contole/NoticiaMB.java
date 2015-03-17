package contole;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import modelo.Noticia;
import persistencia.NoticiaDAO;

/**
 *
 * @author Renato
 */
@Named(value = "noticiaMB")
@Dependent
public class NoticiaMB {

    private Noticia noticia;
    
    private NoticiaDAO noticiaDAO;

    public NoticiaMB() throws SQLException {
        
         noticia = new Noticia();        
        noticiaDAO = new NoticiaDAO();
       // noticias = noticiaDAO.getTodosMp3(NoticiaDAO.ORDEM_POR_ID);
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public List<Noticia> listar() throws ParseException {

        List<Noticia> lista = new ArrayList<Noticia>();

        Noticia not = new Noticia();

        String datahora = "12-03-2015 03:10 PM";
        DateFormat formatado = new SimpleDateFormat("dd-MM-YYYY hh:mm aa");
        not.setData_hora(formatado.parse(datahora));

        not.setFonte("site G1");
        not.setNoticia("Gabrielli diz que era 'impossível' identificar corrupção na estatal");
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
    }

}
