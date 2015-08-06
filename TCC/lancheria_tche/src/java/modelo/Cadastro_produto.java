package modelo;

import java.util.Objects;

public class Cadastro_produto {

    private Cadastro_pessoa cliente;
    private int id;
    private String lanche;
    private String itens;
    private float precolanche;
    private String tipo;
    private String obs;

    public Cadastro_produto() {
    }

    public Cadastro_produto(int id, String lanche, String itens, float precolanche, String tipo, String obs) {
        this.id = id;
        this.lanche = lanche;
        this.itens = itens;
        this.precolanche = precolanche;
        this.tipo = tipo;
        this.obs = obs;
    }

    public Cadastro_pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Cadastro_pessoa cliente) {
        this.cliente = cliente;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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
        final Cadastro_produto other = (Cadastro_produto) obj;
        if (!Objects.equals(this.lanche, other.lanche)) {
            return false;
        }
        return true;
    }

}
