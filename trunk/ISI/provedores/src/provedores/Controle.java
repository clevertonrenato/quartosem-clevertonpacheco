package provedores;

public class Controle {

    private String provedor;
    private int velocidade;
    private double preco;

    public String getProvedor() {
        return provedor;
    }

    public void setProvedor(String provedor) {
        this.provedor = provedor;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    String lista;

    public String lista() {
        /* if(this.getVelocidade() == 10){
         lista = "\n Bagual Net: 155,00 \n Pedradanoface: 175,00";  
         }
    
         if(this.getVelocidade() == 5){
         lista = "\n Bagual Net: 101,50 \n Velozes e Furiosos: 100,00";  
         }
     
     
         if(this.getVelocidade() == 1){
         lista = "\n Bagual Net: 101,50 \n Pedradanoface: 60,00 \n Velozes e Furiosos: 8,00";  
         }
    
         if(this.getVelocidade()!= 10 && this.getVelocidade()!= 5 && this.getVelocidade()!= 1){
         lista = "\n Nenhum provedor encontrado";
         }*/
        switch (this.getVelocidade()) {
            case 1:
                lista = "\n Bagual Net:.........101,50 \n Pedradanoface:......60,00 \n Velozes e Furiosos: 8,00";
                break;

            case 5:
                lista = "\n Bagual Net:.........101,50 \n Velozes e Furiosos: 100,00";
                break;

            case 10:
                lista = "\n Bagual Net:....155,00 \n Pedradanoface: 175,00";
                break;

            default:
                lista = "\n Nenhum provedor encontrado";

        }

        return lista;
    }

}
