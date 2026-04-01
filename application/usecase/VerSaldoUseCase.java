package application.usecase;

import domain.model.Conta;

public class VerSaldoUseCase {

    public double executar(Conta conta) {
        return conta.getSaldo();
    }
}