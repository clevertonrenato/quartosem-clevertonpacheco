
package modelo;

import java.util.Objects;


public class Cadastro_lanche {
    private int id;
    private String lanche;
    private String itens;
    private float precolanche;
   

    public Cadastro_lanche() {
    }

    public String getItens() {
        return itens;
    }

    public void setItens(String itens) {
        this.itens = itens;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanche() {
        return lanche;
    }

    public void setLanche(String lanche) {
        this.lanche = lanche;
    }

    public float getPrecolanche() {
        return precolanche;
    }

    public void setPrecolanche(float precolanche) {
        this.precolanche = precolanche;
    }

    @Override
    public String toString() {
        return this.lanche;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.lanche);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cadastro_lanche other = (Cadastro_lanche) obj;
        if (!Objects.equals(this.lanche, other.lanche)) {
            return false;
        }
        return true;
    }
   
    
}
