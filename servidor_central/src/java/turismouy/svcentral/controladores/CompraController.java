package turismouy.svcentral.controladores;

import java.time.LocalDate;

import turismouy.svcentral.entidades.compra;
import turismouy.svcentral.entidades.paquete;
import turismouy.svcentral.entidades.turista;
import turismouy.svcentral.entidades.usuario;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.interfaces.ICompraController;
import turismouy.svcentral.manejadores.CompraManejador;
import turismouy.svcentral.manejadores.PaqueteManejador;
import turismouy.svcentral.manejadores.UsuarioManejador;
import turismouy.svcentral.utilidades.log;

public class CompraController implements ICompraController{
	
	public void crearCompra (LocalDate fecha, int cantTotal, int costoTotal, LocalDate vencimiento, String nombrePaquete, String nombreTurista)throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion, UsuarioNoExisteExcepcion{
		
		PaqueteManejador pm = PaqueteManejador.getinstance();
		UsuarioManejador um = UsuarioManejador.getinstance();
		CompraManejador cm = CompraManejador.getinstance();
		
		paquete paquete = pm.getPaquete(nombrePaquete);
		
        if(paquete == null) {
        	log.error("El paquete " + nombrePaquete + " no existe");
        	throw new UsuarioNoExisteExcepcion("El paquete " + nombrePaquete + " no existe");
        }
        
		usuario usuario = um.getUsuario(nombreTurista);
		turista turista = (turista) usuario;
		
        if(turista == null) {
        	log.error("El turista" + nombreTurista + " no existe");
        	throw new UsuarioNoExisteExcepcion("El turista" + nombreTurista + " no existe");
        }
        
        if(pm.TuristaYaComproPaquete(turista, paquete)) {
        	log.error("El turista" + nombreTurista + " ya compro el paquete" + paquete.getNombre());
        	throw new UsuarioYaExisteExcepcion("El turista" + nombreTurista + " ya compro el paquete" + paquete.getNombre());
        }
		
		compra compra = new compra(fecha, cantTotal, costoTotal, vencimiento);
		
		compra.setPaquete(paquete);
		compra.setTurista(turista);
		
		turista = um.persistirCompraEnTurista(turista, compra);
		paquete = pm.persistirCompraEnPaquete(paquete, compra);
		
		pm.updatePaquete(paquete);
		um.updateUsuario(turista);
		cm.addCompra(compra);
	}
}
