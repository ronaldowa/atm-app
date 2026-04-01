import presentation.controller.CaixaController;
import presentation.view.CaixaView;

public class Main {

    public static void main(String[] args) {

        CaixaController controller = new CaixaController();
        CaixaView view = new CaixaView(controller);

        view.iniciar();
    }
}