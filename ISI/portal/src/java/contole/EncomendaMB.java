/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class EncomendaMB {

    private String origem;
    private String destino;
    private float peso;
    private String pac;
    private String sedex;
    private float vlrdiferenca;

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getPac() {
        return pac;
    }

    public void setPac(String pac) {
        this.pac = pac;
    }

    public String getSedex() {
        return sedex;
    }

    public void setVlrsedex(String sedex) {
        this.sedex = sedex;
    }

    public float getVlrdiferenca() {
        return vlrdiferenca;
    }

    public void setVlrdiferenca(float vlrdiferenca) {
        this.vlrdiferenca = vlrdiferenca;
    }

    public EncomendaMB() {
    }

    public void consulta() throws Exception {
        
            String url = "http://developers.agenciaideias.com.br/correios/frete/json/" + origem + "/" + destino + "/" + peso;
            //chamada da classe webservic
            WebService ws = new WebService();
            String resultado = ws.sendGet(url);
            System.out.println(resultado);

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(resultado);
            // System.out.println(jsonObject.get("sedex"));

            this.sedex = jsonObject.get("sedex").toString();

            String descricao = jsonObject.get("pac").toString();
            this.pac = descricao;

            float s = Float.valueOf(this.sedex).floatValue();
            float p = Float.valueOf(this.pac).floatValue();

            this.vlrdiferenca = s - p;
        }

    }

