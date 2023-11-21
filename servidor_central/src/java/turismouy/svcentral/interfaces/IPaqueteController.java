package turismouy.svcentral.interfaces;

import java.time.LocalDate;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import turismouy.svcentral.datatypes.dataPaquete;
import turismouy.svcentral.excepciones.NoExisteExcepcion;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;

@WebService
public interface IPaqueteController {
    
    @WebMethod
    public abstract void crearPaquete(String nombre, String descripcion, int periodoVal, int descuento, LocalDate fechaAlta) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion;
    
    @WebMethod
    @WebResult(name = "dataPaquete")
    public abstract dataPaquete mostrarInfo(String nombre) throws UsuarioNoExisteExcepcion;
    
    @WebMethod
    @WebResult(name = "dataPaquete")
    public abstract List<dataPaquete> listarPaquetes();
    
    @WebMethod
    public abstract void agregarActividadPaquete(String nombrePaquete, String nombreActividad) throws NoExisteExcepcion, YaExisteExcepcion;
    
    @WebMethod
    @WebResult(name = "nombrePaquetes")
    public abstract List<String> obtenerNombresPaquetes();
    
    @WebMethod
    @WebResult(name = "dataPaquete")
    public abstract List<dataPaquete> listarPaquetesSinComprar();
}
