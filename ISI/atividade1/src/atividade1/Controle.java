

package atividade1;


public class Controle {
    
    private String nome;
    private double preco;
    private int quantidade;
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    Double total;
    public Double valor_total(){
    
    total = this.preco * this.quantidade; 
        return total;
       
    }
    Double desconto;
    public Double desconto(){
    
    desconto = this.valor_total() - (this.valor_total() * 0.15);
    return desconto;
    }
    
}
