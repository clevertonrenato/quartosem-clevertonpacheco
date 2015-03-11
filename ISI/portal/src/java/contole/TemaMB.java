
package contole;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import modelo.Tema;

/**
 *
 * @author Renato
 */
@Named(value = "temaMB")
@Dependent
public class TemaMB implements Serializable {

    private Tema tema;
    
    public TemaMB() {
        tema = new Tema();
       this.carregarTema();
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
    
    public void carregarTema(){
    this.tema.setAutor("Cléverton");
    this.tema.setNome("Política");
   this.tema.setCor_fundo("#000000");
    this.tema.setCor_titulo("#FFFFFF");
    
    }
    
}
