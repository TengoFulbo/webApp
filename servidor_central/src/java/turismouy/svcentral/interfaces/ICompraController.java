package turismouy.svcentral.interfaces;

import java.time.LocalDate;

import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;

public interface ICompraController {
	public abstract void crearCompra(LocalDate fecha, int cantTotal, int costoTotal, LocalDate vencimiento, String nombrePaquete, String nombreTurista)throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion, UsuarioNoExisteExcepcion;
}
