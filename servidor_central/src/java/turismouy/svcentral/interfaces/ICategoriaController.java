package turismouy.svcentral.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import turismouy.svcentral.datatypes.dataCategoria;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;

@WebService
public interface ICategoriaController {
    
    @WebMethod
    public abstract void crearCategoria(String nombre) throws YaExisteExcepcion, ParametrosInvalidosExcepcion;
    
    @WebMethod
    @WebResult(name = "dataCategoria")
    public abstract List<dataCategoria> listarCategorias(); 
}
