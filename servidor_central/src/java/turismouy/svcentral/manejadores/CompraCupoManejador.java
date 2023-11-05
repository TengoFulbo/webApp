package turismouy.svcentral.manejadores;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import turismouy.svcentral.EMFactory;
import turismouy.svcentral.entidades.compra_cupo;
import turismouy.svcentral.utilidades.log;

public class CompraCupoManejador {
    private static CompraCupoManejador instancia = null;
 
    EntityManagerFactory factory = EMFactory.getEntityManagerFactory();

    private CompraCupoManejador() {};

    // Singleton.
    public static CompraCupoManejador getinstance() {
        if (instancia == null)
            instancia = new CompraCupoManejador();
        return instancia;
    }

    public void addCupo(compra_cupo cupo) {
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(cupo);

            tx.commit();
            log.info("[compraCupoManejador] Se agrego correctamente el cupo: " + cupo.getId() + "' para la actividad: '" + cupo.getActividad().getNombre() + "'");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            // log.error("Guardado de Departamento '" + nombre + "' errrono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateCupo(compra_cupo cupo) {
        // Para cada función hay que crear un nuevo em y tx.
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Se actualiza el archivo.
            em.merge(cupo);

            tx.commit();
            log.info("El cupo se actualizó correctamente. Id:" + cupo.getId() + " correctamente");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Actualizado el cupo '" + cupo.getId() + "' erroneo.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
