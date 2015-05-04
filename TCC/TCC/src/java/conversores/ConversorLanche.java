/*
package conversores;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import modelo.Cadastro_lanche;


@FacesConverter(forClass = Cadastro_lanche.class, value = "conversorLanche")
public class ConversorLanche {

      
     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Genero genero = null;        
        try { 
            GeneroDAO generoDAO = new GeneroDAO();
            genero = generoDAO.pesquisarPorDescricao(value);
        } catch (SQLException ex) {
            Logger.getLogger(ConversorGenero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genero;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Genero genero = (Genero) value;
        return genero.getDescricao();
    }
    
}

}
*/
