package turismouy.svcentral.interfaces;

import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;

public interface ICategoriaController {
    public abstract void crearCategoria(String nombre) throws YaExisteExcepcion, ParametrosInvalidosExcepcion;
}
