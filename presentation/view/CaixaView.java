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

    // 🔐 método de proteção
    private boolean reautenticar() {

        System.out.print("Confirme seu PIN: ");
        int pin = scanner.nextInt();

        try {
            controller.autenticar(pin);
            return true;

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public void iniciar() {

        Conta conta = new Conta(500);

        boolean entrou = entrar();

        if (!entrou) {
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
                    fluxoPosSaldo(conta);
                    break;

                case 2:
                    realizarSaque(conta);
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

    private void realizarSaque(Conta conta) {

        // 🔐 proteção antes do saque
        if (!reautenticar()) {
            return;
        }

        System.out.print("Digite o valor do saque: ");
        double valor = scanner.nextDouble();

        try {
            double saldoAtual = controller.realizarSaque(true, conta, valor);

            System.out.println("Saque realizado com sucesso.");
            System.out.println("Saldo atual: " + saldoAtual);

            // ⏱️ contador regressivo estilo banco
            System.out.print("\nSessão será encerrada em: ");

            for (int i = 5; i >= 1; i--) {
                System.out.print("\rSessão será encerrada em: " + i + " segundos ");
                Thread.sleep(1000);
            }

            System.out.println("\nSessão encerrada por segurança.");
            System.out.println("Obrigado, volte sempre!");

            System.exit(0);

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Erro no tempo de sessão.");
        }
    }

    private void fluxoPosSaldo(Conta conta) {

        System.out.println("\nDeseja realizar um saque?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não (Finalizar)");
        System.out.print("Escolha: ");

        int escolha = scanner.nextInt();

        if (escolha == 1) {
            realizarSaque(conta);
        } else if (escolha == 2) {
            System.out.println("Operação finalizada.");
        } else {
            System.out.println("Opção inválida.");
        }
    }
}