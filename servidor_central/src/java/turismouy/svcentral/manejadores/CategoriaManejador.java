package turismouy.svcentral.manejadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import turismouy.svcentral.EMFactory;
import turismouy.svcentral.entidades.actividad;
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


    public void addCategoria(categoria categoria) {
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
        ActividadManejador am = ActividadManejador.getinstance();

        nombre = nombre.toLowerCase();
        categoria categoria = null;

        try {
            categoria = em.createQuery("SELECT c from categoria c LEFT JOIN FETCH c.actividades WHERE LOWER(c.nombre) = :nombre", categoria.class)
                        .setParameter("nombre", nombre)
                        .getSingleResult();

            List<actividad> actividades = new ArrayList<actividad>();

            for (actividad act : categoria.getActividades()) {
                actividades.add(am.getActividad(act.getNombre()));
            }

            categoria.setActividades(actividades);
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }

        return categoria;
    }
    

    public List<categoria> getAllCategorias() {
	    EntityManager em = factory.createEntityManager();
        List<categoria> categorias  = new ArrayList<categoria>();
        List<categoria> cats        = new ArrayList<categoria>();

        try {
            cats = em.createQuery("SELECT c from categoria c JOIN FETCH c.actividades", categoria.class)
                        .getResultList();

            for (categoria categoria : cats) {
                categorias.add(getCategoria(categoria.getNombre()));
            }
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
        
        return categorias;
    }


    public List<categoria> getAllCategoriasV2() {
	    EntityManager em = factory.createEntityManager();
        List<categoria> categorias = new ArrayList<categoria>();
        try {
            categorias = em.createQuery("SELECT c from categoria c LEFT JOIN FETCH c.activiades", categoria.class)
                        .getResultList();
            
            for (categoria categoria : categorias) {
                log.info("[categoriaManejador] categoria: " + categoria.getNombre());
                List<actividad> actividades = new ArrayList<actividad>();

                for (actividad actividad : categoria.getActividades()) {

                    ActividadManejador am = ActividadManejador.getinstance();
                    actividad actividadM = am.getActividad(actividad.getNombre());
                    actividades.add(actividadM);
                }
                categoria.setActividades(actividades);
            }
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
