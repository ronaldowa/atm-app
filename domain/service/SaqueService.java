package domain.service;

import domain.model.Conta;

public class SaqueService {

    public boolean podeSacar(Conta conta, double valor) {
        return conta.getSaldo() >= valor;
    }

    public void sacar(Conta conta, double valor) {
        conta.debitar(valor);
    }
}