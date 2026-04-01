package application.usecase;

public class AutenticarUseCase {

    private static final int PIN_CORRETO = 1234;

    public void autenticar(int pin) {

        if (pin != PIN_CORRETO) {
            throw new IllegalArgumentException("PIN incorreto");
        }
    }
}