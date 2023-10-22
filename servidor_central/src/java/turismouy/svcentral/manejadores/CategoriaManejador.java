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
        nombre = nombre.toLowerCase();

        categoria categoria = null;
        try {
            categoria = em.createQuery("SELECT c from categoria c WHERE LOWER(c.nombre) = :nombre", categoria.class)
                        .setParameter("nombre", nombre)
                        .getSingleResult();     
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }

        return categoria;
    }
    

    public List<categoria> getAllCategorias() {
	    EntityManager em = factory.createEntityManager();
        List<categoria> categorias = new ArrayList<categoria>();

        try {
            categorias = em.createQuery("SELECT c from categoria c JOIN FETCH c.actividades", categoria.class)
                        .getResultList();     
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
        
        return categorias;
    }

    public void updateCategoria(categoria categoria) {
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();

        String nombre = categoria.getNombre();

        try {
            tx.begin();
            
            // Se actualiza el archivo.
            em.merge(categoria);
            
            tx.commit();        
            
            // Se actualiza en la colección.
            log.info("La categoria " + categoria + " se actualizó correctamente");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Actualizado la Categoria '" + nombre + "' erroneo.");
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}
