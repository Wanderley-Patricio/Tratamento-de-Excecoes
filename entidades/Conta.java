package entidades;

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
        saldo -= valor;
    }

    /*O que irei fazer na função abaixo é testar se o valor do saque não 
    irá infringir alguma regra de negócio, nesse caso se o primeiro if der falso
    eu testo o segundo if se ele também der falso eu retorno nulo indicando que 
    a função não infringiu nenhuma das duas regras. 
    A gambiarra nesse caso é usar a função retornando uma String com a mensagem 
    de erro se não tiver erro a função retornará nulo. */
    
    public String validarSaque(Double valor) {
        if (valor > getLimiteSaque()) {
            return "Erro de saque: A quantia excede o limite de saque.";
        }// Eu não preciso usar o else porque o return já corta a função, achou o return a função é "cortada".
        if (valor > getSaldo()) {
           return "Erro de saque: Saldo insuficiente.";
        }
        return null;
    }
}
/*Da mesma forma que a aplicação (que mexe com a tela) não tem que implementar regra de negócio lá
na classe de domínio (no caso conta) eu não tenho que mexer com tela, aqui só devo implementar coisas
relativas a classe, ou seja, não devo colocar nenhum print nesta classe.
Aplicação mexe com tela, imprime, lê.
Classe de negócio (model) ela faz a regra de negócio.
*/