package turismouy.svcentral.JUniteTest;

import static org.junit.Assert.*;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import turismouy.svcentral.EMFactory;
import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.entidades.proveedor;
import turismouy.svcentral.entidades.salida;
import turismouy.svcentral.manejadores.ActividadManejador;

public class ActividadManejadorTest {
	EntityManagerFactory factory = EMFactory.getEntityManagerFactory();
    private ActividadManejador AM;

	@Test
	public void testGetinstance() {
	    EntityManager em = factory.createEntityManager();

	    actividad actividad = new actividad("pepe", "Recorrido por las mejores bodegas de la región.", 120, 50, "Canelones", LocalDate.now());
	    proveedor proveedor = em.createQuery("SELECT p FROM proveedor p WHERE p.nombre = 'designCraft'", proveedor.class).getSingleResult();
	    actividad.setProveedor(proveedor);
	    salida salida = em.createQuery("SELECT s FROM salida s WHERE s.nombre = 'Salida a la playa' ", salida.class).getSingleResult();
	    actividad.addSalida(salida);
        AM.addActividad(actividad);
        actividad actividadRecuperada = em.createQuery("SELECT a FROM actividad a WHERE a.nombre = '" + actividad.getNombre() + "'", actividad.class).getSingleResult();
        assertNotNull(actividadRecuperada);
        assertEquals("Canelones", actividadRecuperada.getNombre());
	}
/*
	@Test
	public void testAddActividad() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateActividad() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetActividad() {
		fail("Not yet implemented");
	}
*/
}
