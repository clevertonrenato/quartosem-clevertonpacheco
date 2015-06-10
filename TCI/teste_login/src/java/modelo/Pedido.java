package modelo;

/**
 *
 * @author Renato
 */
public class Pedido {

    private int numero;
    private String lanche;
    private int qtdlanche;
    private String obs;
    private String formapagamento;
    private int idpedido;
    private int idproduto;

    public Pedido() {
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLanche() {
        return lanche;
    }

    public void setLanche(String lanche) {
        this.lanche = lanche;
    }

    public int getQtdlanche() {
        return qtdlanche;
    }

    public void setQtdlanche(int qtdlanche) {
        this.qtdlanche = qtdlanche;
    }

    public String getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(String formapagamento) {
        this.formapagamento = formapagamento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

}
