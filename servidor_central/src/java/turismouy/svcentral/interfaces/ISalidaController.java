package turismouy.svcentral.interfaces;

import java.time.LocalDate;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import turismouy.svcentral.datatypes.dataSalida;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;

@WebService
public interface ISalidaController {
    
    @WebMethod
    void crearSalida(String nombre, int capacidad, LocalDate fechaAlta, LocalDate fechaSalida, String lugarSalida, String nombreActividad) throws UsuarioYaExisteExcepcion, ParametrosInvalidosExcepcion, UsuarioNoExisteExcepcion;
    
    @WebMethod
    @WebResult(name = "dataSalida")
    List<dataSalida> getAllSalidas();
    
    @WebMethod
    @WebResult(name = "dataSalida")
    dataSalida mostrarDatosSalida(String nombreSalida);
    
    @WebMethod
    @WebResult(name = "dataSalida")
    List<dataSalida> obtenerSalidasVigentesPorActividad(String nombreActividad);
    
    @WebMethod
    void eliminarSalida(String salida);
    
    //List<String> obtenerNombresActividadesAsociadas(salida salida);
}
