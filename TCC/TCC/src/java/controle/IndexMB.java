
package controle;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import modelo.Pedido;


@Named(value = "indexMB")
@Dependent
public class IndexMB {
    
    //O mp3 é um objeto da classe Mp3 e é utilizado para apresentar dados
    //no formulário da página index.xhtml.
    private Pedido pedido;
    
   

    public IndexMB() {
    }
    
}
