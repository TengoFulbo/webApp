/*package turismouy.svcentral.manejadores;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import turismouy.svcentral.EMFactory;
import turismouy.svcentral.entidades.imagen;
import turismouy.svcentral.utilidades.log;

public class ImagenManejador {
    private static ImagenManejador instancia = null;

    EntityManagerFactory factory = EMFactory.getEntityManagerFactory();

    private ImagenManejador(){}

    // Singleton.
    public static ImagenManejador getinstance() {
        if (instancia == null)
            instancia = new ImagenManejador();
        return instancia;
    }

    public void addImagen(imagen imagen) {
        // Para cada función hay que crear un nuevo em y tx.
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Se guarda el archivo.
            em.persist(imagen);

            tx.commit();

            log.info("[ImagenManejador] se agregó la imagen " + imagen.getId());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("[ImagenManejador] Guardado de compra '" + imagen.getId() + "' errono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateImagen(imagen imagen) {
        // Para cada función hay que crear un nuevo em y tx.
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Se actualiza el archivo.
            em.merge(imagen);

            tx.commit();
            log.info("[ImagenManejador] La imagen se actualizó" + imagen.getId() + " correctamente");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("[ImagenManejador] Actualización de imagen '" + imagen.getId() + "' errono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public imagen getImagen(String nickname) {
    	EntityManager em = factory.createEntityManager();

        imagen imagen = null;

        try {
            imagen = em.createQuery("SELECT i FROM Image i WHERE i.usuario.nickname = '" + nickname + "'", imagen.class).getSingleResult();
        } catch (Exception e) {
            imagen = null;
        } finally {
            em.close();
        }
        return imagen;
    }
}
*/