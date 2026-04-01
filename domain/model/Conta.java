package domain.model;

public class Conta {

    private double saldo;

    public Conta(double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial não pode ser negativo");
        }
        this.saldo = saldoInicial;
    }

    public double getSaldo() {
        return this.saldo;
    }

    // Saque (débito)
    public void debitar(double valor) {

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor inválido para saque");
        }

        if (this.saldo < valor) {
            throw new IllegalStateException("Saldo insuficiente");
        }

        this.saldo -= valor;
    }

    // Depósito (crédito)
    public void creditar(double valor) {

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor inválido para depósito");
        }

        this.saldo += valor;
    }

    @Override
    public String toString() {
        return "Conta{saldo=" + saldo + "}";
    }
}