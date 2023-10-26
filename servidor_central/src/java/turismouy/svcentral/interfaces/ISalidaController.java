package turismouy.svcentral.interfaces;

import java.time.LocalDate;
import java.util.List;

import turismouy.svcentral.datatypes.dataSalida;
import turismouy.svcentral.entidades.salida;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;

public interface ISalidaController {
    void crearSalida(String nombre, int capacidad, LocalDate fechaAlta, LocalDate fechaSalida, String lugarSalida, String nombreActividad) throws UsuarioYaExisteExcepcion, ParametrosInvalidosExcepcion, UsuarioNoExisteExcepcion;
    List<dataSalida> getAllSalidas();
    dataSalida mostrarDatosSalida(String nombreSalida);
    //List<String> obtenerNombresActividadesAsociadas(salida salida);
    List<dataSalida> obtenerSalidasVigentesPorActividad(String nombreActividad);
    void eliminarSalida(String salida);
    
}
