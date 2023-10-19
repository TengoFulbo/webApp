package turismouy.svcentral.interfaces;

import java.time.LocalDate;
import java.util.List;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.excepciones.NoExisteExcepcion;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;
import turismouy.svcentral.utilidades.estadoActividad;

public interface IActividadController {
    /**
     * Caso de uso:
     * @param nombreDepto String
     * @param nombreProv String
     * @param nombre String
     * @param desc String
     * @param duracion int
     * @param costoUni int
     * @param ciudad String
     * @param fechaCrea LocalDate
     * @throws ParametrosInvalidosExcepcion
     * @throws UsuarioYaExisteExcepcion
     * @throws UsuarioNoExisteExcepcion
     */
    public abstract void crearActividad(String nombreDepto, String nombreProv, String nombre, String desc, int duracion, int costoUni, String ciudad, LocalDate fechaCrea) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion, UsuarioNoExisteExcepcion;
    
    /**
     * Caso de uso:
     * @param nombreAct String
     * @return dataActividad
     * @throws UsuarioNoExisteExcepcion
     */
    public abstract dataActividad mostrarDatos(String nombreAct) throws UsuarioNoExisteExcepcion;
    /**
     * Caso de uso:
     * @param nombre String
     * @param desc String
     * @param duracion int
     * @param costoUni int
     * @param ciudad String
     * @param fechaCrea LocalDate
     * @throws ParametrosInvalidosExcepcion
     * @throws UsuarioYaExisteExcepcion
     * @throws UsuarioNoExisteExcepcion
     */
     public abstract void modificarActividad(String nombre, String desc, int duracion, int costoUni, String ciudad, LocalDate fechaCrea) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion, UsuarioNoExisteExcepcion;

    public abstract void modificarEstadoActividad(String nombre, estadoActividad estado) throws NoExisteExcepcion, ParametrosInvalidosExcepcion, YaExisteExcepcion;;

    /**
     * Lista todas las actividades.
     * @return Lista de dataActividad
     */
    public abstract List<dataActividad> getAllActividades();

    /**
     * Lista todas las actividades de un Departamento
     * @param nombreDep String
     * @return Lista de dataActividad
     */
    public abstract List<dataActividad> getAllActividadesDepartamento(String nombreDep);
    public abstract List<dataActividad> getAllActividadesConfirmadasDepartamento(String nombreDep);
    public abstract List<dataActividad> getAllActividadesDepartamentoSinPaquete(String nombreDep, String nombrePaquete);
    public abstract List<String> getNombresSalidasAsociadas(String nombreActividad);
    public abstract List<dataActividad> getActividadesNoPaquete(String nombreDepartamento, String nombrePaquete) throws UsuarioNoExisteExcepcion;
    public abstract List<dataActividad> getActividadesDepartamentoNoPaquete(String nombrePaquete, String nombreDepartamento) throws UsuarioNoExisteExcepcion;
}
