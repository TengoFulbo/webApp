package turismouy.svcentral.interfaces;

import java.time.LocalDate;
import java.util.List;

import turismouy.svcentral.datatypes.dataPaquete;
import turismouy.svcentral.excepciones.NoExisteExcepcion;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;

public interface IPaqueteController {
    public abstract void crearPaquete(String nombre, String descripcion, int periodoVal, int descuento, LocalDate fechaAlta) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion;
    public abstract dataPaquete mostrarInfo(String nombre) throws UsuarioNoExisteExcepcion;
    public abstract List<dataPaquete> listarPaquetes();
    public abstract void agregarActividadPaquete(String nombrePaquete, String nombreActividad) throws NoExisteExcepcion, YaExisteExcepcion;
    public abstract List<String> obtenerNombresPaquetes();
    public abstract List<dataPaquete> listarPaquetesSinComprar();
}
