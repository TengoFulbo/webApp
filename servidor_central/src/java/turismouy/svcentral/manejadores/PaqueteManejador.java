package turismouy.svcentral.manejadores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import turismouy.svcentral.EMFactory;
import turismouy.svcentral.entidades.compra;
import turismouy.svcentral.entidades.inscripcion;
import turismouy.svcentral.entidades.paquete;
import turismouy.svcentral.entidades.salida;
import turismouy.svcentral.entidades.turista;
import turismouy.svcentral.entidades.usuario;
import turismouy.svcentral.utilidades.log;

public class PaqueteManejador {
    private Map<String, paquete> paqueteNombre;
    private static PaqueteManejador instancia = null;

    EntityManagerFactory factory = EMFactory.getEntityManagerFactory();

    private PaqueteManejador() {
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();

        paqueteNombre = new HashMap<String, paquete>();
        List<paquete> paquetes = em.createQuery("SELECT p FROM paquete p JOIN FETCH p.actividades", paquete.class).getResultList();
        // List<paquete> paquetes = em.createQuery("SELECT DISTINCT p FROM paquete p " +
                                                // "LEFT JOIN FETCH p.actividades a " +
                                                // "LEFT JOIN FETCH a.categorias", paquete.class)
        // .getResultList();

        if (paquetes != null) {
            for (paquete paquete : paquetes) {
                // log.warning("[P] " + paquete.getNombre() + " " + paquete.getActividades().size());
                // if (paquete.getActividades() != null) {
                //     for (actividad actividad : paquete.getActividades()) {
                //         log.warning("    " + actividad.getNombre());
                //     }
                // }
                paqueteNombre.put(paquete.getNombre(), paquete);
            }
        }
        log.info("Paquetes cargados: " + paqueteNombre.size());
        em.close();
    };

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

        String nombre = paquete.getNombre();

        try {
            tx.begin();

            // Se guarda el archivo.
            em.persist(paquete);

            tx.commit();

            // Si el archivo se logró guardar en BD, lo guarda en la colección y además muestra cuantos paquetes hay.
            paqueteNombre.put(nombre, paquete);
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

    public paquete getPaquete(String nombre){
        // return ((paquete) paqueteNombre.get(nombre));
    	EntityManager em = factory.createEntityManager();

        paquete paquete;
        try {
            paquete = em.createQuery("SELECT p FROM paquete p JOIN FETCH p.actividades JOIN FETCH a.categorias WHERE p.nombre = '" + nombre + "'", paquete.class).getSingleResult();
        } catch (Exception e) {
            paquete = null;
        } finally {
            em.close();
        }
        return paquete;
    }

    public List<paquete> getAllPaquetes() {
        if (paqueteNombre.isEmpty()) { return null; };

        List<paquete> paquetes = new ArrayList<>(paqueteNombre.values());
        return paquetes;
    }

    public void updatePaquete(paquete paquete) {
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
        
        String nombre = paquete.getNombre();
        System.out.println(nombre + " ----------------");
        
        try {
            tx.begin();
            
            // Se actualiza el archivo.
            em.merge(paquete);
            
            tx.commit();        
            
            // Se actualiza en la colección.
            System.out.println(paquete.getNombre() + "++++++++++++++++++++++++++");
            paqueteNombre.put(nombre, paquete);
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
