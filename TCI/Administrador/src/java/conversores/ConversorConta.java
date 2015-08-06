/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conversores;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Cadastro_produto;
import persistencia.Cadastro_produtoDAO;

/**
 *
 * @author emilio
 */
@FacesConverter(forClass = Cadastro_produto.class, value = "conversorConta")
public class ConversorConta implements Converter {

    //O método getAsObject recebe a string que descreve a conta e retorna para a aplicação
    //uma instância do objeto Cadastro_produto.
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String nome) {
        Cadastro_produto conta = null;
        try {
            Cadastro_produtoDAO contaDAO = new Cadastro_produtoDAO();
            conta = contaDAO.getContaPorNome(nome);
        } catch (SQLException ex) {
            Logger.getLogger(ConversorConta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conta;
    }

    //O método getAsString faz o contrário de getAsObject, ou seja, recebe um objeto do tipo
    //Conta e retorna uma string que representa o mesmo para a aplicação.
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Cadastro_produto conta = (Cadastro_produto) value;
        return conta.getLanche();
    }


}
