
package provedores;

import java.util.Scanner;


public class Provedores {

  
    public static void main(String[] args) {
        
         Scanner entrada = new Scanner(System.in);
        
        Controle plano = new Controle(); 
        
        System.out.println("digite a velocidade do plano:");
        plano.setVelocidade(Integer.parseInt(entrada.nextLine()));
        
         System.out.println("Os provedores encontrados são:" + plano.lista());
    }
    
}
