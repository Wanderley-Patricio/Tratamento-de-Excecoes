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
import exceptions.RegrasDeNegocioExceptions;
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

      try{        
            conta.sacar(valor);
            System.out.printf("Novo saldo: %.2f", conta.getSaldo());
            System.out.println("");
      }
      catch(RegrasDeNegocioExceptions e){
          System.out.println(e.getMessage());
          
      }
      scanner.close();
    } 
}

/*
Entendendo os blocos try, catch
- try (tradução tentar), ou seja quando aparecer o try significa que ele vai 
tentar excecutar aquele trecho de código.
- A função sacar() pode lançar uma exceção , caso ela dispare uma exceção, eu 
posso agora capturar essa exceção.
- catch(tipo_da_exceção apelido_da_exceção) 
Ex.:(<catch(RegrasDeNegocioExceptions e>)
feito isso, agora dentro do bloco catch eu coloco o que eu quiser, ou seja, eu 
posso colocar o tratamento que eu quiser.
- Quando eu instanciei a exceção na função validarSaque() dentro da classe Conta 
Ex.: (<throw new RegrasDeNegocioExceptions("Erro de saque: .....")>), eu passei 
uma String, essa String foi armazenada dentro do objeto da exceção na classe 
RegrasDeNegocioExceptions (Ex.: <public RegrasDeNegocioExceptions(String message>), 
então quando eu capturar a exceção no bloco catch da classe Programa 
(Ex.: <catch (RegrasDeNegocioExceptions e)>), dentro desse objeto "e" vai estar 
aquela mensagem armazenada na classe RegrasDeNegocioExceptions, dessa forma eu 
posso simplesmente mostrar a mensagem utilizando o System.out.println(e.getMessage()), 
ou seja, o getMessage() pega a mensagem que eu armazenei em RegrasDeNegocioExceptions.
- A leitura correta da estrutura de exceções para esse código é a senguinte:
. Chamei a função sacar() 
. Dentro da função sacar() chama-se a função validarSaque()
. Dentro da fução validarSaque() são testadas as condições e caso alguma falhe é 
lançada uma exceção com a mensagem inserida no trecho que falhou.
. Como lançou a exceção, o bloco try falhou, e caiu no catch, ai o bloco catch 
pega a exceção e mostra a mensagem dela.


Diferença da RunTimeException e Exception
O java faz distinção da exceção que:
- somos obrigados a tratar, e aquelas que é
- opcional tratar (que podemos deixar ela estourar e o programa quebrar).
Por exemplo se no programa retiramos os blocos try catch o programa irá rodar e estourar a exceção com a mensagem no console para usuário tentar entender, essa forma não é legal
- O RunTimeException não me obriga a colocar os blocos try/catch
- Se eu mudar na minha classe RegrasDeNegocioExceptions e fazê-la herdar da classe Exception do java, eu vou começar a ter erros dizendo que a exceção não foi tratada, eu posso não tratar a exceção naquele trecho do código colocando a informação throws RegrasDeNegocioExceptions (Ex.: <private void validarSaque(Double valor) throws RegrasDeNegocioExceptions {>), para avisar que o meu método pode lançar uma exceção, ai o compilador deixa passar naquele trecho de código a exceção, mas vai quebrar em outro trecho do código, se eu não for tratar naquele método que a messagem apareceu eu tenho que informar na assinatura do método o throws RegrasDeNegocioExceptions, ai podemos fazer isso até o compilador não ter mais para onde ir e nos obrigar a tratar a exceção com o try/cacth.

Resumido 
- se eu herdar de exception sou obrigado a tratar a exceção.
- se eu herdar de RunTimeException é opcional tratar a exceção.

O código como foi escrito é uma boa engenharia de software, pois resolve tantos 
os problemas de:
• Delegação (pois cada parte resolve o que lhe cabe sem misturar as funções)
• Manutenqäo / código limpo

*/
