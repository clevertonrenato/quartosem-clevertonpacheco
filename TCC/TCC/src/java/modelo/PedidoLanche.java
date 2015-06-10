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
public class PedidoLanche {

    private Cadastro_lanche lanche;
    private int qtd;
    private float preco;
    private float total;
   

    public PedidoLanche(Cadastro_lanche lanche, int qtd, float preco) {
        this.lanche = lanche;
        this.qtd = qtd;
        this.preco = preco;
        this.total = preco * qtd;
        
    }        

       
    public Cadastro_lanche getLanche() {
        return lanche;
    }

    public void setLanche(Cadastro_lanche lanche) {
        this.lanche = lanche;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    
    
}
