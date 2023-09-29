package turismouy.svcentral.interfaces;

import java.util.List;

import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;

public interface IDepartamentoController {
    public abstract void crearDepartamento(String nombre, String descripcion, String url)throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion;
    public abstract List<dataDepartamento> listarDepartamentos();
}
