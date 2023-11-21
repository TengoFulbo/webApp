package turismouy.svcentral.interfaces;

import java.time.LocalDate;

import javax.jws.WebMethod;
import javax.jws.WebService;

import turismouy.svcentral.excepciones.NoExisteExcepcion;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;

@WebService
public interface ICompraController {

	@WebMethod
	public abstract void crearCompra(LocalDate fecha, int cantTotal, int costoTotal, LocalDate vencimiento, String nombrePaquete, String nombreTurista)throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion, UsuarioNoExisteExcepcion;
	
	@WebMethod
	public abstract void crearCompraV2(String nombreTurista, String nombreActividad, String nombrePaquete, int cantidad) throws ParametrosInvalidosExcepcion, YaExisteExcepcion, NoExisteExcepcion;
	// public abstract void crearCompra(int cantidad, String nombrePaquete, String nombreTurista);
}
