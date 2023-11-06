package turismouy.svcentral.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.entidades.compra;
import turismouy.svcentral.entidades.compra_cupo;
import turismouy.svcentral.entidades.paquete;
import turismouy.svcentral.entidades.turista;
import turismouy.svcentral.entidades.usuario;
import turismouy.svcentral.excepciones.NoExisteExcepcion;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;
import turismouy.svcentral.interfaces.ICompraController;
import turismouy.svcentral.manejadores.ActividadManejador;
import turismouy.svcentral.manejadores.CompraCupoManejador;
import turismouy.svcentral.manejadores.CompraManejador;
import turismouy.svcentral.manejadores.PaqueteManejador;
import turismouy.svcentral.manejadores.UsuarioManejador;
import turismouy.svcentral.utilidades.log;

public class CompraController implements ICompraController{
	
	public void crearCompraV2(String nombreTurista, String nombreActividad, String nombrePaquete, int cantidad) throws ParametrosInvalidosExcepcion, YaExisteExcepcion, NoExisteExcepcion {
		Boolean debug = true; String debugMsg = "[crearCompraV2] ";
		UsuarioManejador 		um = UsuarioManejador.getinstance();
		ActividadManejador 		am = ActividadManejador.getinstance();
		PaqueteManejador    	pm = PaqueteManejador.getinstance();
		CompraManejador			cm = CompraManejador.getinstance();
		CompraCupoManejador 	ccm = CompraCupoManejador.getinstance();
		int costo = 0;

		paquete paquete = pm.getPaquete(nombrePaquete);

		if (paquete == null) {
			throw new NoExisteExcepcion("El paquete '" + nombrePaquete + "' no existe.");
		}
		if (debug) log.info(debugMsg + "Paquete ok");

		actividad actividad = am.getActividad(nombreActividad);

		if (actividad == null) {
			throw new NoExisteExcepcion("La actividad '" + nombreActividad + "' no existe.");
		}
		if (debug) log.info(debugMsg + "Actividad ok");

		turista turista = (turista) um.getUsuario(nombreTurista);

		if (turista == null) {
			throw new NoExisteExcepcion("El turista '" + nombreTurista + "' no existe.");
		}
		if (debug) log.info(debugMsg + "Turista ok");

		if (pm.TuristaYaComproPaquete(turista, paquete)) {
			throw new YaExisteExcepcion("El usuario '" + nombreTurista + "' ya tiene el paquete '" + nombrePaquete + "' comprado.");
		}
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaVencimiento = fechaActual.plusDays(paquete.getValidez());

		log.info("Empezamos con las actividades del paquete.");
		for (actividad act : paquete.getActividades()) {
			costo += act.getCosteUni() * cantidad;
			log.info("	Actividad: " + act.getNombre() + ". Costo uni: '" + act.getCosteUni() + "' Costo: " + costo);
		}

		compra compra = new compra(LocalDate.now(), cantidad, 2, fechaVencimiento);

		compra.setPaquete(paquete);
		compra.setTurista(turista);

		turista.addCompra(compra);
		paquete.addCompra(compra);

		pm.updatePaquete(paquete);
		um.updateUsuario(turista);

		cm.addCompra(compra);

		List<compra_cupo> cupos = new ArrayList<compra_cupo>();
		compra compraSaved = cm.getCompra(compra.getId());

		for (actividad act : paquete.getActividades()) {
			compra_cupo cupo = new compra_cupo(act, cantidad);
			cupo.setCompra(compraSaved);
			cupos.add(cupo);
			ccm.addCupo(cupo);
		}
	}

	public void crearCompra (LocalDate fecha, int cantTotal, int costoTotal, LocalDate vencimiento, String nombrePaquete, String nombreTurista)throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion, UsuarioNoExisteExcepcion{
		
		PaqueteManejador pm = PaqueteManejador.getinstance();
		UsuarioManejador um = UsuarioManejador.getinstance();
		CompraManejador cm = CompraManejador.getinstance();
		CompraCupoManejador ccm = CompraCupoManejador.getinstance();
		
		paquete paquete = pm.getPaquete(nombrePaquete);
		
        if(paquete == null) {
        	log.error("[compraController] El paquete " + nombrePaquete + " no existe");
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
				
		turista.addCompra(compra);
		paquete.addCompra(compra);
		// turista = um.persistirCompraEnTurista(turista, compra);
		// paquete = pm.persistirCompraEnPaquete(paquete, compra);
		
		pm.updatePaquete(paquete);
		um.updateUsuario(turista);
		
		cm.addCompra(compra);

		List<compra_cupo> cupos = new ArrayList<compra_cupo>();


		compra compraSaved = cm.getCompra(compra.getId());

		for (actividad actividad : paquete.getActividades()) {
			compra_cupo cupo = new compra_cupo(actividad, cantTotal);
			cupo.setCompra(compraSaved);
			cupos.add(cupo);
			ccm.addCupo(cupo);
		}
	}
}
