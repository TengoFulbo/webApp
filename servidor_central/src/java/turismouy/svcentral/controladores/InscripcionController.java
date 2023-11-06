package turismouy.svcentral.controladores;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import turismouy.svcentral.EMFactory;
import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.entidades.compra;
import turismouy.svcentral.entidades.compra_cupo;
import turismouy.svcentral.entidades.inscripcion;
import turismouy.svcentral.entidades.paquete;
import turismouy.svcentral.entidades.salida;
import turismouy.svcentral.entidades.turista;
import turismouy.svcentral.entidades.usuario;
import turismouy.svcentral.excepciones.NoExisteExcepcion;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;
import turismouy.svcentral.interfaces.IInscripcionController;
import turismouy.svcentral.manejadores.ActividadManejador;
import turismouy.svcentral.manejadores.CompraCupoManejador;
import turismouy.svcentral.manejadores.CompraManejador;
import turismouy.svcentral.manejadores.InscripcionManejador;
import turismouy.svcentral.manejadores.PaqueteManejador;
import turismouy.svcentral.manejadores.SalidaManejador;
import turismouy.svcentral.manejadores.UsuarioManejador;
import turismouy.svcentral.utilidades.log;

public class InscripcionController implements IInscripcionController {

	public void crearInscripcionPago(Boolean pagoGeneral, int cantidad, String nickname, String nombreSalida, String nombreActividad)
			throws ParametrosInvalidosExcepcion, YaExisteExcepcion, NoExisteExcepcion {
			Boolean debug = true; String debugMsg = "[inscripcionController] ";
				
			// Declaramos.
			UsuarioManejador 		um = UsuarioManejador.getinstance();
			ActividadManejador 		am = ActividadManejador.getinstance();
			SalidaManejador 		sm = SalidaManejador.getInstance();
			CompraManejador			cm = CompraManejador.getinstance();
			PaqueteManejador		pm = PaqueteManejador.getinstance();
			InscripcionManejador 	im = InscripcionManejador.getinstance();
			CompraCupoManejador		ccm = CompraCupoManejador.getinstance();

			int costo = 0;
			int compraId = 0;

			// Validamos.
			if (cantidad == 0 || nickname == "" || nombreSalida == "" || nombreActividad == "") {
				throw new ParametrosInvalidosExcepcion();
			}

			// Traemos al usuario.
			usuario usuario = um.getUsuario(nickname);

			// Validamos que exista el usuario.
			if (usuario == null) {
				throw new NoExisteExcepcion("El usuario '" + nickname + "' no existe.");
			}
			
			// Si el usuario no es turista
			if (!(usuario instanceof turista)) {
				throw new ParametrosInvalidosExcepcion();
			}
			if (debug) log.info(debugMsg + "Usuario ok.");
			
			actividad actividad = am.getActividad(nombreActividad);

			if (actividad == null) {
				throw new NoExisteExcepcion("La actividad '" + nombreActividad + "' no existe.");
			}
			if (debug) log.info(debugMsg + "Actividad ok.");

			salida salida = sm.getSalida(nombreSalida);

			if (salida == null) {
				throw new NoExisteExcepcion("La salida '" + nombreSalida + "' no existe.");
			}
			if (debug) log.info(debugMsg + "Salida ok.");

			turista turista = (turista) usuario;

			// TODO: Falta verificar que la inscripción haya vencido.
			if(um.yaEstaInscripto(turista, salida)) {
				throw new YaExisteExcepcion("La inscripcion del turista: '" + turista.getNickname() + "' no pudo realizarse porque ya se encuentra inscripto a la salida: '" + salida.getNombre()  + "'.");
			}

			if(debug) log.info(debugMsg + "Pago: " + (pagoGeneral ? "General" : "Por paquete"));
			if (pagoGeneral) {
				costo = cantidad * actividad.getCosteUni();
			} else {
				List<compra> compras = turista.getCompras();

				if (compras.isEmpty()) {
					throw new NoExisteExcepcion("No se pagar la inscripción a la salida usando el paquete, porque el turista no tiene una compra de paquete válida.");
				}

				for (compra compra : compras) {
					LocalDate fechaHoy = LocalDate.now();
					// Se le suma un día para que el día que dice "Válido hasta el .. inclusive: "
					LocalDate fechaVencimiento = compra.getVencimiento().plusDays(1);
					
					if (fechaHoy.isAfter(fechaVencimiento)) {
						continue;
					}
					if (debug) log.info(debugMsg + "Compra no vencida: " + compra.getId());

					paquete paquete = pm.getPaquete(compra.getPaquete().getNombre());

					Boolean existeActividad = false;
					for (actividad act : paquete.getActividades()) {
						if (act.getNombre().equals(nombreActividad)) {
							existeActividad = true;
						}
					}
					if (debug) log.info(debugMsg + "Actividad existe dentro del paquete? " + ((existeActividad) ? "Si" : "No"));

					boolean existeSalida = false;
					for (salida sal : actividad.getSalidas()) {
						if (sal.getNombre().equals(nombreSalida)) {
							existeSalida = true;
						}
					}
					if (debug) log.info(debugMsg + "Salida existe dentro de la actividad? " + ((existeSalida) ? "Si" : "No"));

					for (actividad act : paquete.getActividades()) {
						costo += act.getCosteUni();
						if (debug) log.info(debugMsg + " - " + act.getNombre() + " | " + act.getCosteUni() + " | " + costo);
					}

					// Multiplicamos el costo total por la cantidad.
					costo = costo * cantidad;
					
					// Calculamos lo que tenemos que descontar.
					// Se hace (int) por temas de división en Java.
					int descuento = (int) (costo * (paquete.getDescuento() / 100.0));
					
					// Descontamos el descuento.
					costo = costo - descuento;
					compraId = compra.getId();
				}
			}

			for (compra_cupo cupo : cm.getCompra(compraId).getCupos()) {
				if (cupo.getActividad().getNombre().equals(nombreActividad)) {
					if (cantidad > cupo.getCantidad()) {
						log.error("Tu cantidad supera la cantidad de cupos disponibles.");
						throw new ParametrosInvalidosExcepcion();
					}
					cupo.disminuirCupos(cantidad);
					ccm.updateCupo(cupo);
				}
			}

			inscripcion inscripcion = new inscripcion(LocalDate.now(), cantidad, costo);

			inscripcion.setSalida(salida);
			inscripcion.setTurista(turista);
			im.addInscripcion(inscripcion);

			turista.addInscripcion(inscripcion);
			um.updateUsuario(usuario);
			// turista = UM.persistirInscripcionEnTurista(turista, inscripcion);
			// UM.updateUsuario(turista);

			salida = sm.persistirInscripcionEnSalida(salida, inscripcion);
			log.warning("Capacidad de la salida: " + salida.getCapacidad());
			salida.setCapacidad(salida.getCapacidad() - cantidad);
			sm.updateSalida(salida);

			am.updateActividad(actividad);
		}


	// TODO: Eliminar la fecha de los parámetros.
	public void crearInscripcion(LocalDate fecha, int cant, String nickName, String nombreSalida, String nombreAct)
			throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion, UsuarioNoExisteExcepcion {

		InscripcionManejador IM = InscripcionManejador.getinstance();
		UsuarioManejador UM = UsuarioManejador.getinstance();
		SalidaManejador SM = SalidaManejador.getInstance();
		ActividadManejador AM = ActividadManejador.getinstance();

		actividad actividad = AM.getActividad(nombreAct);

		if (actividad == null) {
			log.error("La actividad " + nombreAct + " no existe");
			throw new UsuarioNoExisteExcepcion(nombreAct);
		}

		salida salida = SM.getSalida(nombreSalida);

		if (salida == null) {
			log.error("La salida " + nombreSalida + "no existe");
			throw new UsuarioNoExisteExcepcion("La salida " + nombreSalida + " no existe");
		}


		usuario usuario = UM.getUsuario(nickName);

		if (usuario == null) {
			log.error("El usuaro " + nickName + "no existe");
			throw new UsuarioNoExisteExcepcion("El usuaro " + nickName + " no existe");
		}

		if (usuario instanceof turista) {
			turista turista = (turista) usuario;

			if (UM.yaEstaInscripto(turista, salida) == true) {
				log.error("La inscripcion de " + turista.getNickname()
						+ " no pudo realizarse porque ya esta inscripto en la salida " + salida.getNombre());
				throw new UsuarioYaExisteExcepcion("La inscripcion de " + turista.getNickname()
						+ " no pudo realizarse porque ya esta inscripto en la salida " + salida.getNombre());
			}

			int costo = calcularCosto(salida, nombreAct, cant);

			int newCant = salida.getCapacidad() - cant;
			if (newCant < 0) {
				log.error("La cantidad sobrepasa la capacidad de la salida");
				throw new ParametrosInvalidosExcepcion();
			}

			inscripcion inscripcion = new inscripcion(fecha, cant, costo);

			inscripcion.setSalida(salida);
			inscripcion.setTurista(turista);
			IM.addInscripcion(inscripcion);

			turista.addInscripcion(inscripcion);
			UM.updateUsuario(usuario);
			// turista = UM.persistirInscripcionEnTurista(turista, inscripcion);
			// UM.updateUsuario(turista);

			salida = SM.persistirInscripcionEnSalida(salida, inscripcion);
			salida.setCapacidad(salida.getCapacidad() - cant);
			SM.updateSalida(salida);

			AM.updateActividad(actividad);
		}

	}

	public int calcularCosto(salida salida, String nombreAct, int cant) {
		EntityManagerFactory factory = EMFactory.getEntityManagerFactory();
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		salida sal = em
				.createQuery("SELECT s FROM salida s WHERE s.nombreS = '" + salida.getNombre() + "'", salida.class)
				.getSingleResult();

		int costo = 0;
		List<actividad> LActividades = sal.getActividades();
		for (actividad actividad : LActividades) {
			if (actividad.getNombre().equals(nombreAct)) {
				costo = cant * actividad.getCosteUni();
			}
		}

		tx.commit();
		em.close();
		return costo;
	}
}