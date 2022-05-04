/*
Agenda:
• Estudo de caso: saque em conta bancária
• Conceito
• Delegação
• Manutenqäo / código limpo
• Soluqöes:
    • 1) Tudo no programa principal
    • 2) Função "gambiarra"
    • 3) Exceções

Exercício
Fazer um programa para ler os dados de uma conta bancária e depois
realizar um saque nesta conta bancária, mostrando o novo saldo. Um
saque não pode ocorrer ou se não houver saldo na conta, ou se o valor
do saque for superior ao limite de saque da conta. Implemente a conta
bancária conforme projeto abaixo:

Fonte:
https://www.youtube.com/watch?v=AiIuJqJ0r8A
https://github.com/acenelio/exceptions2-java
Tratamento de exceções : aprenda na prática : exemplo Java
DevSuperior

@author Adaptado por Wanderley Patrício de Sousa Neto
 */
package aplicacao;

import entidades.Conta;
import java.util.Locale;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {
      
      Locale.setDefault(Locale.US);             // faz com que o programa entenda que o separador de decimal é ponto ao invés de vírgula.
      Scanner scanner = new Scanner(System.in); // instanciando uma variável do tipo scanner.

      System.out.println("Informe os dados da conta");
      System.out.print("Número conta: ");
      int cc = scanner.nextInt();            // após o enter o cursor ficará preso em uma linha vazia 
      System.out.print("Titular: "); 
      scanner.nextLine();                    // esse nextLine está consumindo o enter dado no nextInt.
      String titular = scanner.nextLine();   //nextLine me permite ler nome com espaços. Lembrando que como foi utilizado o nextInt e apertei enter
      System.out.print("Saldo inicial: ");   // esse enter ficar armazenado no buffer de entrada se eu der um único nextLine eu vou apenas consumir
      Double saldo = scanner.nextDouble();   // o enter mas não vou ler nada, por esse motivo eu usei um scanner.nextLine antes desse.
      System.out.print("Limite de saque: ");        
      Double saque = scanner.nextDouble();
      
      Conta conta = new Conta(cc, titular, saldo, saque); // Ctrl + enter para completar 
      
      System.out.println("");
      System.out.print("Informe uma quantia para sacar: ");
      double valor = scanner.nextDouble();

      String error = conta.validarSaque(valor);
      if(error != null){                //se o error for diferente de nulo significa que caiu 
          System.out.println(error);    //em uma das regras e então eu mostro a mensagem de qual erro caiu.
      }
      else{        
            conta.sacar(valor);
            System.out.printf("Novo saldo: %.2f", conta.getSaldo());
            System.out.println("");
      }
      scanner.close();
    } 
}

/*Observação: Eu implementando tudo no programa principal eu não estou delegando as atividades, ou seja,
um código que faz uma certa lógica deve estar na classe que implementa aquela lógica. Por exemplo, o 
if e else utilizados no programa principal estão fazendo verificações que deveriam ser realizadas pela classe
conta.
*/
