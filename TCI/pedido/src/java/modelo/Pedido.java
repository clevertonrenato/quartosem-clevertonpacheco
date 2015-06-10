package modelo;

/**
 *
 * @author Renato
 */
public class Pedido {

    private int numero;
    private String lanche;
    private int qtdlanche;
    private String bebida;
    private int qtdbebida;
    private String formapagamento;
    private float vlanche;

    public Pedido() {
    }

    public float getVlanche() {
        return vlanche;
    }

    public void setVlanche(float vlanche) {
        this.vlanche = vlanche;
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

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public int getQtdbebida() {
        return qtdbebida;
    }

    public void setQtdbebida(int qtdbebida) {
        this.qtdbebida = qtdbebida;
    }

    public String getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(String formapagamento) {
        this.formapagamento = formapagamento;
    }

}
