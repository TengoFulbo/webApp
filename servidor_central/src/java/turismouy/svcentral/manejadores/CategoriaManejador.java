package turismouy.svcentral.manejadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import turismouy.svcentral.EMFactory;
import turismouy.svcentral.entidades.categoria;
import turismouy.svcentral.utilidades.log;

public class CategoriaManejador {
    private static CategoriaManejador instancia = null;

    EntityManagerFactory factory = EMFactory.getEntityManagerFactory();

    // Singleton.
    public static CategoriaManejador getInstance() {
        if (instancia == null)
            instancia = new CategoriaManejador();
        return instancia;
    }

    public void addCategoria(categoria categoria){
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();

        String nombre = categoria.getNombre();

        try {
            tx.begin();

            // Se guarda el archivo.
            em.persist(categoria);

            tx.commit();        

            // Si el archivo se logró guardar en BD, lo guarda en la colección y además muestra cuantos usuarios hay.
            log.info("[CategoriaManejador] se agrego la categoria: " + nombre);
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Guardado de Categoria '" + nombre + "' errrono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public categoria getCategoria(String nombre) {
	    EntityManager em = factory.createEntityManager();
        categoria categoria = null;
        
        try {
            categoria = em.createQuery("SELECT c from categoria c WHERE c.nombre = :nombre", categoria.class)
                        .setParameter("nombre", nombre)
                        .getSingleResult();     
        } catch (Exception e) {
            return null;
        }

        return categoria;
    }

    public List<categoria> getAllCategorias() {
	    EntityManager em = factory.createEntityManager();
        List<categoria> categorias = new ArrayList<categoria>();

        try {
            categorias = em.createQuery("SELECT c from categoria c", categoria.class)
                        .getResultList();     
        } catch (Exception e) {
            return null;
        }
        
        em.close();
        return categorias;
    }
}
