
package modelo;

import java.util.Date;

public class Lancamento {

    private int id;
    private Cadastro_produto conta;
    private Cadastro_pessoa usuario;
    private String historico;
    private float valor;
    private Date data;

    public Lancamento() {

    }
    
    public Lancamento(int id, Cadastro_produto conta, String historico, float valor, Date data) {
        this.setId(id);
        this.setConta(conta);
        this.setHistorico(historico);
        this.setValor(valor);
        this.setData(data);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cadastro_pessoa getCadastro_pessoa() {
        return usuario;
    }

    public void setCadastro_pessoa(Cadastro_pessoa usuario) {
        this.usuario = usuario;
    }
    

    public Cadastro_produto getConta() {
        return conta;
    }

    public void setConta(Cadastro_produto conta) {
        this.conta = conta;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }             
            
}
