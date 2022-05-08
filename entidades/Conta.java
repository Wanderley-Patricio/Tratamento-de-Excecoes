package entidades;

import exceptions.RegrasDeNegocioExceptions;

public class Conta {

    private Integer cc;
    private String titular;
    private Double saldo;
    private Double limiteSaque;

    public Conta(Integer cc, String titular, Double saldo, Double saque) {
        this.cc = cc;
        this.titular = titular;
        this.saldo = saldo;
        this.limiteSaque = saque;
    }

    public Integer getCc() {
        return cc;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getLimiteSaque() {
        return limiteSaque;
    }

    public void setLimiteSaque(Double saque) {
        this.limiteSaque = saque;
    }

    public void depositar(Double valor) {
        saldo += valor;
    }

    public void sacar(Double valor) {
        validarSaque(valor);
        saldo -= valor;
    }

    /*A função validarSaque() trabalha com uso de exceções. Essa função 
    simplesmente lança uma exceção se acontecer algum erro, se não acontecer 
    nenhum erro a função não faz nada.
    - Uma boa prática ao escrever programas é ter em mente que "quanto mais 
    complexo for o program melhor usar exceções". 
    - No java A cláusula para lançar uma exceção é throw, na sequência instanciasse  
    a exceção com new RuntimeException (Ex.:<throw new RuntimeException>)
    - É importante entender que o throw assim como o return, ele corta o método. 
    Se em algum momento der um throw, acabou, isso significa que a sua função 
    lançou uma exceção ao invés de terminar.
    - Criar uma exceção customizada, permite capturar exceções específicas 
    dependendo do tratamento que se queira dar. Pode ser muito interessante 
    principalmente em sistemas grandes.
    */
    
    private void validarSaque(Double valor){
        if (valor > getLimiteSaque()) {
           throw new RegrasDeNegocioExceptions("Erro de saque: A quantia excede o limite de saque.");
        }// Eu não preciso usar o else porque o return já corta a função, achou o return a função é "cortada".
        if (valor > getSaldo()) {
           throw new RegrasDeNegocioExceptions("Erro de saque: Saldo insuficiente.");
        }
    }
}
/*Da mesma forma que a aplicação (que mexe com a tela) não tem que implementar regra de negócio lá
na classe de domínio (no caso conta) eu não tenho que mexer com tela, aqui só devo implementar coisas
relativas a classe, ou seja, não devo colocar nenhum print nesta classe.
Aplicação mexe com tela, imprime, lê.
Classe de negócio (model) ela faz a regra de negócio.
*/