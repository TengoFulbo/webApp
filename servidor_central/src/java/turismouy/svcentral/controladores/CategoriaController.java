package turismouy.svcentral.controladores;

import turismouy.svcentral.entidades.categoria;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;
import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.manejadores.CategoriaManejador;
import turismouy.svcentral.utilidades.log;

public class CategoriaController implements ICategoriaController {
    
    public void crearCategoria(String nombre) throws YaExisteExcepcion, ParametrosInvalidosExcepcion {
        if (!validarTexto(nombre, 1)) {
            log.error("[crearCategoria] Parametros invalidos.");
            throw new ParametrosInvalidosExcepcion();
        }
        
        CategoriaManejador cm = CategoriaManejador.getInstance();
        categoria c = cm.getCategoria(nombre);
        
        if (c != null) {
            log.error("La categoria: '" + c.getNombre() + "' ya existe.");
            throw new YaExisteExcepcion("La categoria: '" + nombre + "' ya existe.");
        }

        categoria categoria = new categoria(nombre);
        cm.addCategoria(categoria);
    };

    /*
        * Level 1: Textos simples. Valida que no sea vacio, que no empiece o termine con espacio y que al menos tenga 1 letra o número.
        * Livel 2: Nicknames. Valida lo mismo que el nivel 1, además valida que no contenga espacios. Sirve para nombres de usuario.
    */
    private static boolean validarTexto(String texto, int nivel) {
        switch (nivel) {
            case 1:
                return validarNivel1(texto);
            case 2:
                return validarNivel1(texto) && !texto.contains(" ");
            default:
                return false;
        }
    }

    private static boolean validarNivel1(String texto) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        if (texto.trim().length() != texto.length()) {
            return false;
        }
        return texto.matches(".*[a-zA-Z0-9].*");
    }
}
