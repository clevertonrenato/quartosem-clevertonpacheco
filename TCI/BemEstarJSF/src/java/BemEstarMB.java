/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Renato
 */
@ManagedBean
@RequestScoped
public class BemEstarMB {

    String urlWS;//endereço do WS
    String frasedia;
    String complemento;
    String indice;
    String altura;
    String peso;
   String indice1;

    public String getIndice1() {
        return indice1;
    }

    public void setIndice1(String indice1) {
        this.indice1 = indice1;
    }
    

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String idice) {
        this.indice = indice;
    }

    public String getUrlWS() {
        return urlWS;
    }

    public void setUrlWS(String urlWS) {
        this.urlWS = urlWS;
    }

    public String getFrasedia() {
        return frasedia;
    }

    public void setFrasedia(String frasedia) {
        this.frasedia = frasedia;
    }

    
    public void calcular(){
        
        try {
            indice1 = sendGet(urlWS + "/imc/" + altura + "/" + peso);
        } catch (Exception ex) {
            Logger.getLogger(BemEstarMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public BemEstarMB() {
        urlWS = "http://localhost:8080/bemestarws/webresources/bemestarws";

        try {
            frasedia = sendGet(urlWS + "/dicadia");
            //frase do dia
        } catch (Exception ex) {
            Logger.getLogger(BemEstarMB.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            indice = sendGet(urlWS + "/imc/1.70/85");

        } catch (Exception ex) {
            Logger.getLogger(BemEstarMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        return response.toString();
    }
}
