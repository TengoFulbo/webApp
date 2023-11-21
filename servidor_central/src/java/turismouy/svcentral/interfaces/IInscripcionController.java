package turismouy.svcentral.interfaces;

import java.time.LocalDate;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import turismouy.svcentral.entidades.salida;
import turismouy.svcentral.excepciones.NoExisteExcepcion;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;

@WebService
public interface IInscripcionController {

    @WebMethod
	public abstract void crearInscripcion(LocalDate fecha, int cant, String nombreTursita, String nombreSalida, String nombreAct) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion, UsuarioNoExisteExcepcion;
	
	@WebMethod
	public abstract void crearInscripcionPago(Boolean pagoGeneral, int cantidad, String nickname, String nombreSalida, String nombreActividad) throws ParametrosInvalidosExcepcion, YaExisteExcepcion, NoExisteExcepcion;
	
	@WebMethod
	@WebResult(name = "costoTotal")
	public abstract int calcularCosto(salida salida, String nombreAct, int cant);
}