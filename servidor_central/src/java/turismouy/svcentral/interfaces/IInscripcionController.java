package turismouy.svcentral.interfaces;

import java.time.LocalDate;

import turismouy.svcentral.entidades.salida;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;

public interface IInscripcionController {
	public abstract void crearInscripcion(LocalDate fecha, int cant, String nombreTursita, String nombreSalida, String nombreAct) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion, UsuarioNoExisteExcepcion;;
	public abstract int calcularCosto(salida salida, String nombreAct, int cant);
}