package turismouy.svcentral.interfaces;

import java.time.LocalDate;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.excepciones.NoExisteExcepcion;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;
import turismouy.svcentral.utilidades.estadoActividad;

@WebService
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
    @WebMethod
    public abstract void crearActividad(
        String nombreDepto,
        String nombreProv,
        String nombre,
        String desc,
        int duracion,
        int costoUni,
        String ciudad,
        LocalDate fechaCrea,
        List<String> categorias
    ) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion, UsuarioNoExisteExcepcion;
        
    public abstract void crearActividadUrl(
        String nombreDepto,
        String nombreProv,
        String nombre,
        String desc,
        int duracion,
        int costoUni,
        String ciudad,
        String urlVideo,
        LocalDate fechaCrea,
        List<String> categorias
    ) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion, UsuarioNoExisteExcepcion;
        
    /**
     * Caso de uso:
     * @param nombreAct String
     * @return dataActividad
     * @throws UsuarioNoExisteExcepcion
     */
    @WebMethod
    @WebResult(name = "dataActividad")
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
    @WebMethod
    public abstract void modificarActividad(String nombre, String desc, int duracion, int costoUni, String ciudad, LocalDate fechaCrea) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion, UsuarioNoExisteExcepcion;
    
    @WebMethod
    public abstract void modificarEstadoActividad(String nombre, estadoActividad estado) throws NoExisteExcepcion, ParametrosInvalidosExcepcion, YaExisteExcepcion;;

    /**
     * Lista todas las actividades.
     * @return Lista de dataActividad
     */
    @WebMethod
    @WebResult(name = "dataActividad")
    public abstract List<dataActividad> getAllActividades();

    /**
     * Lista todas las actividades de un Departamento
     * @param nombreDep String
     * @return Lista de dataActividad
     */
    @WebMethod
    @WebResult(name = "dataActividad")
    public abstract List<dataActividad> getAllActividadesDepartamento(String nombreDep);

    @WebMethod
    @WebResult(name = "dataActividad")
    public abstract List<dataActividad> getAllActividadesConfirmadasDepartamento(String nombreDep);

    @WebMethod
    @WebResult(name = "dataActividad")
    public abstract List<dataActividad> getActividadesDepartamentoNoPaquete(String nombrePaquete, String nombreDepartamento) throws UsuarioNoExisteExcepcion;

    @WebMethod
    @WebResult(name = "dataActividad")
    public abstract List<dataActividad> getAllActividadesAgregadas();

    @WebMethod
    @WebResult(name = "dataActividad")
    public abstract List<dataActividad> getActividadesPorCategoria(String nombreCategoria) throws NoExisteExcepcion;
    
    @WebMethod
    @WebResult(name = "dataActividad")
    public abstract void finalizarActividad(String nombreAct)throws NoExisteExcepcion;
}
