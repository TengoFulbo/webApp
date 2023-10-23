package turismouy.svcentral.manejadores;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import turismouy.svcentral.EMFactory;
import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.utilidades.estadoActividad;
import turismouy.svcentral.utilidades.log;

public class ActividadManejador {
    private static ActividadManejador instancia = null;
    
    // EntityManagerFactory factory = Persistence.createEntityManagerFactory("PA2023");
    EntityManagerFactory factory = EMFactory.getEntityManagerFactory();


    private ActividadManejador(){};
    
    // Singleton.
    public static ActividadManejador getinstance() {
        if (instancia == null)
            instancia = new ActividadManejador();
        return instancia;
    }


    public void addActividad(actividad actividad) {
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();

            // Se guarda el archivo.
            // em.persist(actividad);
            em.merge(actividad);

            tx.commit();       
            log.info("[ActividadManejador] Se crea la actividad " + actividad.getNombre());
            
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Guardado de Actividad '" + actividad.getNombre() + "' errrono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateActividad(actividad actividad){
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Se actualiza el archivo.
            em.merge(actividad);

            tx.commit();       
            log.info("La actividad " + actividad.getNombre() + " se actualizó correctamente");
            // log.info("Salidas: " + actividad.getSalidas().size());

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Actualizacion de Actividad: '" + actividad.getNombre() + "' errrono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    };

    public actividad getActividad(String nombreAct){
    	EntityManager em = factory.createEntityManager();
    	
        actividad actividad;
        try {
            actividad = em.createQuery("SELECT DISTINCT a FROM actividad a " + 
            //  "LEFT JOIN FETCH a.salidas " + 
             "LEFT JOIN FETCH a.categorias WHERE a.nombreA = '" + nombreAct + "' AND a.estado <> :estadoRechazada",actividad.class)
                .setParameter("estadoRechazada", estadoActividad.RECHAZADA.ordinal())                
                .getSingleResult();
        } catch (NoResultException  e) {
            actividad = null;
        } finally {
            em.close();
        }
    	return actividad;
    }
}
