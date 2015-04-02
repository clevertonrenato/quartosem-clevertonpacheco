
package modelo;

import java.io.Serializable;


public class Tema implements Serializable {
    private int id;
    private String nome;
    private String autor;
    private String cor_titulo;
    private String cor_fundo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCor_titulo() {
        return cor_titulo;
    }

    public void setCor_titulo(String cor_titulo) {
        this.cor_titulo = cor_titulo;
    }

    public String getCor_fundo() {
        return cor_fundo;
    }

    public void setCor_fundo(String cor_fundo) {
        this.cor_fundo = cor_fundo;
    }
    
    
    
    
}
