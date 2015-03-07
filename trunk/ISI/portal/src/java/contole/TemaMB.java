
package contole;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import modelo.Tema;

/**
 *
 * @author Renato
 */
@Named(value = "temaMB")
@Dependent
public class TemaMB {

    private Tema tema;
    
    public TemaMB() {
       this.carregarTema();
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
    
    public void carregarTema(){
    //this.tema.setAutor("Cléverton");
    this.tema.setNome("Classificados");
   // this.cor = this.tema.setCor_fundo("red");
    //this.tema.setCor_titulo("#000000");
    
    }
    
}
