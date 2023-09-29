package turismouy.svcentral.controladores;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import turismouy.svcentral.EMFactory;
import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.entidades.inscripcion;
import turismouy.svcentral.entidades.salida;
import turismouy.svcentral.entidades.turista;
import turismouy.svcentral.entidades.usuario;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.interfaces.IInscripcionController;
import turismouy.svcentral.manejadores.ActividadManejador;
import turismouy.svcentral.manejadores.InscripcionManejador;
import turismouy.svcentral.manejadores.SalidaManejador;
import turismouy.svcentral.manejadores.UsuarioManejador;
import turismouy.svcentral.utilidades.log;

public class InscripcionController implements IInscripcionController {
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

			// List<salida> salidas = actividad.getSalidas();


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