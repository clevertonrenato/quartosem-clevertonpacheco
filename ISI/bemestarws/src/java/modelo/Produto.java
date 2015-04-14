/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Renato
 */
public class Produto implements Serializable {

    private String alimento;
    private String medida;
    private float kcal;

    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public float getKcal() {
        return kcal;
    }

    public void setKcal(float kcal) {
        this.kcal = kcal;
    }

    public void buscarAlimentos(String alimento) {

      switch(alimento){
          case "arroz":
              this.kcal = 300.0f;
              this.alimento = alimento;
              this.medida = "400g";
              break;
              
              case "feijao":
              this.kcal = 500.0f;
              this.alimento = alimento;
              this.medida = "200g";
              break;
                  
              default:
                  this.kcal = 0.0f;
                  this.alimento = "não encontrado";
                  this.medida = "0";
      
      }
}
}