package application.usecase;

import domain.model.Conta;

public class RealizarSaqueUseCase {

    public void executar(boolean cartaoInserido, Conta conta, double valorSaque) {

        if (!cartaoInserido) {
            throw new IllegalStateException("Cartão não inserido");
        }

        conta.debitar(valorSaque); // entidade valida tudo
    }
}