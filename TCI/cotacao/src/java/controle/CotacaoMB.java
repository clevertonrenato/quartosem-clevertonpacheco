/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Renato
 */
@ManagedBean
@RequestScoped
public class CotacaoMB {

    private float cotacao;
    private float variacao;
    private String moeda;

    public float getCotacao() {
        return cotacao;
    }

    public void setCotacao(float cotacao) {
        this.cotacao = cotacao;
    }

    public float getVariacao() {
        return variacao;
    }

    public void setVariacao(float variacao) {
        this.variacao = variacao;
    }
    
    
           
    public CotacaoMB() {
    }
    
    public void consulta() throws Exception{
        if(this. != null){
    String url = " http://developers.agenciaideias.com.br/tempo/json/"+cidade;
    //chamada da classe webservice
    WebService ws = new WebService();
    String resultado = ws.sendGet(url);
     System.out.println(resultado);
    
}
