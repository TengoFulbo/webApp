package turismouy.svcentral.interfaces;

import java.util.List;

import turismouy.svcentral.datatypes.dataCategoria;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;

public interface ICategoriaController {
    public abstract void crearCategoria(String nombre) throws YaExisteExcepcion, ParametrosInvalidosExcepcion;
    
    public abstract List<dataCategoria> listarCategorias(); 
}
