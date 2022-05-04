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
    
    public void depositar(Double valor){
        saldo += valor;
    }
    
    public void sacar(Double valor){
        saldo -= valor;
    }
            
}
