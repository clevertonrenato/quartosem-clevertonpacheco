package atividade1;

import java.util.Scanner;

public class Atividade1 {

    public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in);
        
        Controle produto = new Controle();        
             
        System.out.println("digite o Nome do Produto");   
        produto.setNome(entrada.nextLine());
       
        System.out.println("digite o Preço");
        produto.setPreco(Double.parseDouble(entrada.nextLine()));
       
        System.out.println("digite a Qauntidade");
        produto.setQuantidade(Integer.parseInt(entrada.nextLine()));

        System.out.println("Nome do Produto.. " + produto.getNome());
        System.out.println("Preço unitário... " + produto.getPreco());
        System.out.println("Quantidade....... " + produto.getQuantidade());
        System.out.println("Valor total...... " + produto.valor_total());
        System.out.println("Valor sem imposto " + produto.desconto());

    }

}
