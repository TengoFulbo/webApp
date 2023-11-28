package turismouy.svcentral.manejadores;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import turismouy.svcentral.EMFactory;
import turismouy.svcentral.entidades.visita;
import turismouy.svcentral.utilidades.log;

public class VisitaManejador {
    private static VisitaManejador instancia = null;

    EntityManagerFactory factory = EMFactory.getEntityManagerFactory();

    private VisitaManejador() {};

    public static VisitaManejador getinstance() {
        if (instancia == null) {
            instancia = new VisitaManejador();
        }
        return instancia;
    }

    public void addVisita(visita visita) {
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            em.persist(visita);

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Guardando la visita erronea.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateVisita(visita visita) {
        // Para cada función hay que crear un nuevo em y tx.
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Se actualiza el archivo.
            em.merge(visita);

            tx.commit();

            log.info("La visita correctamente");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Actualizado el visita errrono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
