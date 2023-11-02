package turismouy.svcentral.manejadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import turismouy.svcentral.EMFactory;
import turismouy.svcentral.entidades.compra;
import turismouy.svcentral.entidades.paquete;
import turismouy.svcentral.entidades.turista;
import turismouy.svcentral.utilidades.log;

public class PaqueteManejador {
    private static PaqueteManejador instancia = null;

    EntityManagerFactory factory = EMFactory.getEntityManagerFactory();

    private PaqueteManejador() {};

    // Singleton.
    public static PaqueteManejador getinstance() {
        if (instancia == null)
            instancia = new PaqueteManejador();
        return instancia;
    }

    public void addPaquete(paquete paquete) {
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();

        // String nombre = paquete.getNombre();

        try {
            tx.begin();

            // Se guarda el archivo.
            em.persist(paquete);

            tx.commit();

            log.info("[PaqueteManejador] se agrego correctamente el paquete: " + paquete.getNombre());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Guardado de Paquete '" + paquete.getNombre() + "' errrono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    public paquete getPaquete(String nombre) {
    	EntityManager em = factory.createEntityManager();

        paquete paquete;
        paquete paqueteWithCompra;

        try {
            paquete = em.createQuery("SELECT p FROM paquete p LEFT JOIN FETCH p.actividades WHERE p.nombre = :nombre", paquete.class)
                .setParameter("nombre", nombre)
                .getSingleResult();

            paqueteWithCompra = em.createQuery("SELECT p FROM paquete p LEFT JOIN FETCH p.compra WHERE p.nombre = :nombre", paquete.class)
                .setParameter("nombre", nombre)
                .getSingleResult();

            paquete.setCompra(paqueteWithCompra.getCompra());
        } catch (Exception e) {
            paquete = null;
        } finally {
            em.close();
        }
        return paquete;
    }


    public List<paquete> getAllPaquetes() {
    	EntityManager em = factory.createEntityManager();

        List<paquete> paqs = new ArrayList<paquete>();
        List<paquete> paquetes = new ArrayList<paquete>();
        
        try {
            paqs = em.createQuery("SELECT p FROM paquete p", paquete.class)
                .getResultList();

            for (paquete paquete : paqs) {
                paquetes.add(getPaquete(paquete.getNombre()));
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            em.close();
        }
        return paquetes;
    }

    public void updatePaquete(paquete paquete) {
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
        
        String nombre = paquete.getNombre();
        // System.out.println(nombre + " ----------------");
        
        try {
            tx.begin();
            
            // Se actualiza el archivo.
            em.merge(paquete);
            
            tx.commit();        
            
            log.info("El paquete " + paquete + " se actualizó correctamente");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Actualizado el Paquete '" + nombre + "' errrono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    public paquete persistirCompraEnPaquete(paquete paquete, compra compra) {
        try {
            // Para cada función hay que crear un nuevo em y tx.
	        EntityManager em = factory.createEntityManager();

            paquete paque = em.createQuery("SELECT p FROM paquete p WHERE p.nombre = '" + paquete.getNombre() + "'", paquete.class)
                                .getSingleResult();
            paque.addCompra(compra);
                
            return paque;
        } catch (Exception e) {
            log.error("Error al persisistir compra en paquete. " + e.toString());
            return null;
        }
    }
    
    public boolean paqueteNoComprado(paquete paquete) {
    	boolean P = false;
    	EntityManagerFactory factory = EMFactory.getEntityManagerFactory();
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    
	    tx.begin();
	    
	    paquete paque = em.createQuery("SELECT p FROM paquete p WHERE p.nombre = '" + paquete.getNombre() + "'",paquete.class).getSingleResult();
	    if(paque.getCompra().isEmpty() || paque.getCompra().equals(null)) {
			P = true;
		}
		tx.commit();
		em.close();
		return P;
    }
    
    public boolean TuristaYaComproPaquete(turista turista,paquete paquete){
    	EntityManagerFactory factory = EMFactory.getEntityManagerFactory();
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    
	    tx.begin();
	    
	    paquete paq = em.createQuery("SELECT p FROM paquete p WHERE p.nombre = '" + paquete.getNombre() + "'",paquete.class).getSingleResult();
	    
		for(compra compra : paq.getCompra()) {
			if(compra.getTurista().getNickname().equals(turista.getNickname())){
				return true;
			}
		}
		tx.commit();
		em.close();
		
		return false;
    }
}
