package presentation.view;

import java.util.Scanner;

import presentation.controller.CaixaController;
import domain.model.Conta;

public class CaixaView {

    private CaixaController controller;
    private Scanner scanner;

    public CaixaView(CaixaController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    private boolean entrar() {

        System.out.println("=== BEM-VINDO AO CAIXA ELETRÔNICO ===");
        System.out.println("1 - Inserir cartão");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");

        int opcao = scanner.nextInt();

        if (opcao != 1) {
            System.out.println("Encerrando...");
            return false;
        }

        System.out.println("Cartão inserido com sucesso.");

        System.out.print("Digite seu PIN: ");
        int pin = scanner.nextInt();

        try {
            controller.autenticar(pin);
            System.out.println("Acesso autorizado.\n");
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public void iniciar() {

        Conta conta = new Conta(500);

        boolean cartaoInserido = entrar();

        if (!cartaoInserido) {
            System.out.println("Obrigado, volte sempre!");
            scanner.close();
            return;
        }

        int opcao;

        do {
            System.out.println("\n=== CAIXA ELETRÔNICO ===");
            System.out.println("1 - Ver saldo");
            System.out.println("2 - Sacar");
            System.out.println("0 - Finalizar");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    exibirSaldo(conta);
                    fluxoPosSaldo(conta, cartaoInserido);
                    break;

                case 2:
                    realizarSaque(conta, cartaoInserido);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    System.out.println("Obrigado, volte sempre!");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private void exibirSaldo(Conta conta) {
        try {
            double saldo = controller.verSaldo(conta);
            System.out.println("Saldo atual: " + saldo);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void realizarSaque(Conta conta, boolean cartaoInserido) {

        System.out.print("Digite o valor do saque: ");
        double valor = scanner.nextDouble();

        try {
            double saldoAtual = controller.realizarSaque(cartaoInserido, conta, valor);
            System.out.println("Saque realizado com sucesso.");
            System.out.println("Saldo atual: " + saldoAtual);

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void fluxoPosSaldo(Conta conta, boolean cartaoInserido) {

        System.out.println("\nDeseja realizar um saque?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não (Finalizar)");
        System.out.print("Escolha: ");

        int escolha = scanner.nextInt();

        if (escolha == 1) {
            realizarSaque(conta, cartaoInserido);
        } else if (escolha == 2) {
            System.out.println("Operação finalizada.");
        } else {
            System.out.println("Opção inválida.");
        }
    }
}