package turismouy.svcentral.manejadores;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import turismouy.svcentral.EMFactory;
import turismouy.svcentral.entidades.compra;
import turismouy.svcentral.utilidades.log;

// TODO: ⚠️ Pendiente
public class CompraManejador{
    private static CompraManejador instancia = null;

    EntityManagerFactory factory = EMFactory.getEntityManagerFactory();

    private CompraManejador(){}

    // Singleton.
    public static CompraManejador getinstance() {
        if (instancia == null)
            instancia = new CompraManejador();
        return instancia;
    }

    public void updateCompra(compra compra){
        // Para cada función hay que crear un nuevo em y tx.
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Se actualiza el archivo.
            em.merge(compra);

            tx.commit();
            log.info("La compra se actualizó" + compra.getId() + " correctamente");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Actualizado de compra '" + compra.getId() + "' errrono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    };


    public void addCompra(compra compra) {
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Se guarda el archivo.
            em.persist(compra);

            tx.commit();        
            log.info("[CompraManejador] se agrego la inscripcion: " + compra);
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Guardado de compra '" + compra + "' errono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public compra getCompra(String idCompra) {
    	EntityManager em = factory.createEntityManager();
    	
        compra compra;
        try {
            // compra = em.createQuery("SELECT c from compra c WHERE c.id = '" + idCompra + " '", compra.class).getSingleResult();
            compra = em.createQuery("SELECT c FROM compra c WHERE c.id = :id", compra.class)
                .setParameter("id", idCompra)    
                .getSingleResult();
        } catch (Exception e) {
            compra = null;
        } finally {
            em.close();
        }

    	return compra;
    }

    public List<compra> getAllCompras(){
    	EntityManager em = factory.createEntityManager();
    	
    	List<compra> compra = new ArrayList<compra>();
    	
        try {
        	compra = em.createQuery("SELECT c FROM compra c",compra.class).getResultList();
        } catch (Exception e) {
            compra = null;
        } finally {
            em.close();
        }

    	return compra;    	
    }

}
