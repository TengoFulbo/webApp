package turismouy.svcentral.manejadores;

import turismouy.svcentral.entidades.inscripcion;
import turismouy.svcentral.utilidades.log;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import turismouy.svcentral.EMFactory;

public class InscripcionManejador {
    private static InscripcionManejador instancia = null;

    EntityManagerFactory factory = EMFactory.getEntityManagerFactory();

    private InscripcionManejador(){}

    // Singleton.
    public static InscripcionManejador getinstance() {
        if (instancia == null)
            instancia = new InscripcionManejador();
        return instancia;
    }
/*
    public void updateInscripcion(inscripcion inscripcion){
        // Para cada función hay que crear un nuevo em y tx.
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Se actualiza el archivo.
            em.merge(inscripcion);

            tx.commit();

            log.info("La inscripcion se actualizó" + inscripcion.getId() + "correctamente");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Actualizado de inscripcion '" + inscripcion.getId() + "' errrono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    };
*/


    public void addInscripcion(inscripcion inscripcion) {
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Se guarda el archivo.
            em.persist(inscripcion);

            tx.commit();        

            log.info("[InscripcionManejador] se agrego la inscripcion: " + inscripcion);
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Guardado de Inscripcion '" + inscripcion + "' errrono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
/*
    public inscripcion getInscripcion(String idInscripcion) {
    	EntityManager em = factory.createEntityManager();
    	
        inscripcion inscripcion;
        try {
            inscripcion = em.createQuery("SELECT i from inscripcion i WHERE i.id = '" + idInscripcion + " '", inscripcion.class).getSingleResult();
        } catch (NoResultException  e) {
            inscripcion = null;
        }
    	em.close();
    	return inscripcion;
    	
    }

    public List<inscripcion> getAllInscripciones(){
    	EntityManager em = factory.createEntityManager();
    	
    	List<inscripcion> inscripciones = new ArrayList<inscripcion>();
    	
        try {
        	inscripciones = em.createQuery("SELECT i FROM inscripciones i",inscripcion.class).getResultList();
        } catch (NoResultException  e) {
            inscripciones = null;
        }
    	em.close();
    	return inscripciones;
    
    }
*/
}