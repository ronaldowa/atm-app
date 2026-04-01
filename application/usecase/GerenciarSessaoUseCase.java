package application.usecase;

public class GerenciarSessaoUseCase {

    private long ultimoAcesso;

    public void iniciarSessao() {
        this.ultimoAcesso = System.currentTimeMillis();
    }

    public void atualizarAtividade() {
        this.ultimoAcesso = System.currentTimeMillis();
    }

    public boolean sessaoExpirada() {
        long agora = System.currentTimeMillis();
        return (agora - ultimoAcesso) > 10000; // 10 segundos
    }
}