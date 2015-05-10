
package conversores;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Cadastro_lanche;
import persistencia.Cadastro_lancheDAO;


@FacesConverter(forClass = Cadastro_lanche.class, value = "conversorLanche")
public class ConversorLanche implements Converter{

      
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Cadastro_lanche lanche = null;        
        try { 
            Cadastro_lancheDAO lancheDAO = new Cadastro_lancheDAO();
            lanche = lancheDAO.pesquisarPorLanche(value);
        } catch (SQLException ex) {
            Logger.getLogger(ConversorLanche.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lanche;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Cadastro_lanche lanche = (Cadastro_lanche) value;
        return lanche.getLanche();
    }
    
}



