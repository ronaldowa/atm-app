package presentation.controller;

import application.usecase.AutenticarUseCase;
import application.usecase.RealizarSaqueUseCase;
import application.usecase.VerSaldoUseCase;
import domain.model.Conta;

public class CaixaController {

    private RealizarSaqueUseCase saqueUseCase;
    private VerSaldoUseCase saldoUseCase;
    private AutenticarUseCase autenticarUseCase = new AutenticarUseCase();

    public CaixaController() {
        this.saqueUseCase = new RealizarSaqueUseCase();
        this.saldoUseCase = new VerSaldoUseCase();
    }

    public double realizarSaque(boolean cartaoInserido, Conta conta, double valor) {

        saqueUseCase.executar(cartaoInserido, conta, valor);
        return conta.getSaldo();
    }

    public double verSaldo(Conta conta) {
        return saldoUseCase.executar(conta);
    }

    public void autenticar(int pin) {
        autenticarUseCase.autenticar(pin);
    }
}