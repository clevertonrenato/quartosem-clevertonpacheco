
package contole;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.WebService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Renato
 */
@ManagedBean
@RequestScoped
public class PrevisaoMB {

 private String cidade;
 private String tempo;
 private String temperatura;
 private String vento;
private String imagem;

    public String getCidade() {
        return cidade;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
    

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getVento() {
        return vento;
    }

    public void setVento(String vento) {
        this.vento = vento;
    }
 
 
    public PrevisaoMB() {
    }
    
    public void consulta() throws Exception{
        if(this.cidade != null){
    String url = " http://developers.agenciaideias.com.br/tempo/json/"+cidade;
    //chamada da classe webservice
    WebService ws = new WebService();
    String resultado = ws.sendGet(url);
     System.out.println(resultado);
    //ler o json sem usar classe
    
    //objeto para interpretar o json
    JSONParser parser = new JSONParser();
    JSONObject jsonObject = (JSONObject) parser.parse(resultado);
    System.out.println(jsonObject.get("cidade"));
    
    this.cidade = jsonObject.get("cidade").toString();
    
    // Entrando no primeiro nível
    //quero a temperatura que está no "agora"
    String jsonAGORA = jsonObject.get("agora").toString();
    //Entra no segundo nível
    jsonObject = (JSONObject)parser.parse(jsonAGORA);
    String temperatura = jsonObject.get("temperatura").toString();
    //joga na variável que tem saída do jsf
    this.temperatura = temperatura;
        
    String descricao = jsonObject.get("descricao").toString();
    this.tempo = descricao;
    
    String umidade = jsonObject.get("umidade").toString();
    this.vento = umidade;
    
    String imagem = jsonObject.get("imagem").toString();
    this.imagem = imagem;
    
    }
}
}
