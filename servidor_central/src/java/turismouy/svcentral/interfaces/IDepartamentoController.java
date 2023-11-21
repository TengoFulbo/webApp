package turismouy.svcentral.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;

@WebService
public interface IDepartamentoController {

    @WebMethod
    public abstract void crearDepartamento(String nombre, String descripcion, String url)throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion;
    
    @WebMethod
    @WebResult(name = "dataDepartamento")
    public abstract List<dataDepartamento> listarDepartamentos();
}
