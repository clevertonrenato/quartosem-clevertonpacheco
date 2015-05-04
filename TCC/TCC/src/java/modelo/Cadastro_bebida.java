/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Renato
 */
public class Cadastro_bebida {
    
    private int id;
    private String bebida;
    private float precobebida;

    public Cadastro_bebida() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public float getPrecobebida() {
        return precobebida;
    }

    public void setPrecobebida(float precobebida) {
        this.precobebida = precobebida;
    }
    
    
    
}
