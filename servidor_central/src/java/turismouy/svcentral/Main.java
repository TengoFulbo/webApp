package turismouy.svcentral;

import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.utilidades.log;

public class Main {
    public static void main(String[] args) {
        Fabrica fabrica = Fabrica.getInstance();
        ICategoriaController ICC = fabrica.getICategoriaController();

        ICC.crearCategoria("Aventura");
        log.info("Hola");
    }
}