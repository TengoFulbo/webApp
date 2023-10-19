package turismouy.svcentral.manejadores;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import turismouy.svcentral.EMFactory;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.entidades.proveedor;
import turismouy.svcentral.entidades.usuario;
import turismouy.svcentral.utilidades.log;
import turismouy.svcentral.entidades.compra;
import turismouy.svcentral.entidades.inscripcion;
import turismouy.svcentral.entidades.salida;
import turismouy.svcentral.entidades.turista;

public class UsuarioManejador {
    private Map<String, usuario> usuariosNickname;
    private static UsuarioManejador instancia = null;
    
    EntityManagerFactory factory = EMFactory.getEntityManagerFactory();

    private UsuarioManejador() {
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();

        usuariosNickname = new HashMap<String, usuario>();
        List<usuario> usuarios = em.createQuery("SELECT u FROM usuario u LEFT JOIN FETCH u.actividades", usuario.class).getResultList();
        
        if (usuarios != null) {
            for (usuario usuario : usuarios) {
                usuariosNickname.put(usuario.getNickname(), usuario);
            }
        }
        log.info("Usuarios Cargados: " + usuariosNickname.size());
        em.close();
    };

    // Singleton.
    public static UsuarioManejador getinstance() {
        if (instancia == null)
            instancia = new UsuarioManejador();
        return instancia;
    }

    public void addUsuario(usuario usuario) {
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();

        String nickname = usuario.getNickname();
        
        try {
            tx.begin();

            // Se guarda el archivo.
            em.persist(usuario);

            tx.commit();        

            // Si el archivo se logró guardar en BD, lo guarda en la colección y además muestra cuantos usuarios hay.
            usuariosNickname.put(nickname, usuario);
            // log.info("[UsuarioManejador] se agrego correctamente el usuario: " + usuario.getNickname());
            
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Guardado de Usuario '" + usuario.getNickname() + "' errrono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateUsuario(usuario usuario){
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();

        String nickname = usuario.getNickname();

        try {
            tx.begin();

            // Se actualiza el archivo.
            em.merge(usuario);

            tx.commit();        

            // Se actualiza en la colección.
            usuariosNickname.put(nickname, usuario);
            // log.info("El usuario se actualizó" + nickname + "correctamente");
            // log.info("Usuarios: " + usuariosNickname.size());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Actualizado el Usuario '" + usuario.getNickname() + "' errrono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    };

    public usuario getUsuario(String nickname) {
        return ((usuario) usuariosNickname.get(nickname));
    }

    public usuario getUsuarioEmail(String email) {
        try {
            // Para cada función hay que crear un nuevo em y tx.
	        EntityManager em = factory.createEntityManager();

            usuario usuario = em.createQuery("SELECT u FROM usuario u LEFT JOIN FETCH u.actividades WHERE u.email = :email", usuario.class)
                                .setParameter("email", email)
                                .getSingleResult();

            if (usuario == null) {
                return null;
            }
                
            return usuario;
        } catch (Exception e) {
            log.error("Error al getUsuarioEmail. " + e.toString());
            return null;
        }
    }


    public List<proveedor> getAllProveedor(){
        List<dataUsuario> dataProveedores = new ArrayList<dataUsuario>();
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();

        usuariosNickname = new HashMap<String, usuario>();
        // List<proveedor> proveedores = em.createQuery("SELECT p FROM usuario p WHERE tipo_usuario = 'P' JOIN FETCH p.actividades", proveedor.class).getResultList();
        // List<proveedor> proveedores = em.createQuery("SELECT DISTINCT p FROM proveedor p LEFT JOIN FETCH p.actividades", proveedor.class).getResultList();
        List<proveedor> proveedores = em.createQuery(
            "SELECT DISTINCT p FROM proveedor p " +
            "LEFT JOIN FETCH p.actividades a " +
            "LEFT JOIN FETCH a.salidas", 
            proveedor.class
        ).getResultList();

        if (proveedores == null) {
            em.close();
            return null;
        }
        
        for (proveedor proveedor : proveedores) {
            dataProveedores.add(proveedor.toDataType());
        }
        em.close();
        return proveedores;
    }

    public boolean getEmail(String email) {
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    // EntityTransaction tx = em.getTransaction();

        TypedQuery<Long> query = em.createQuery("SELECT COUNT(e) FROM usuario e WHERE e.email = :email", Long.class);
        query.setParameter("email", email);

        Long count = query.getSingleResult();
        em.close();

        return count > 0;
    }

    public List<usuario> getAllUsuario() {
        if (usuariosNickname.isEmpty()) { return null; };
        
        List<usuario> users = new ArrayList<>(usuariosNickname.values());
        return users;
    }

    public turista persistirInscripcionEnTurista(turista turista, inscripcion inscripcion){
    	EntityManagerFactory factory = EMFactory.getEntityManagerFactory();
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    
	    tx.begin();
	    
	    usuario user = em.createQuery("SELECT u FROM usuario u WHERE u.nickname = '" + turista.getNickname() + "'",usuario.class).getSingleResult();
	    turista turista1 = (turista) user;
		turista1.addInscripcion(inscripcion);
		
		tx.commit();
		em.close();
		
		return turista1;
    }
    
    public turista persistirCompraEnTurista(turista turista, compra compra) {
            try {
                // Para cada función hay que crear un nuevo em y tx.
    	        EntityManager em = factory.createEntityManager();

                usuario usuario = em.createQuery("SELECT u FROM usuario u WHERE u.nickname = '" + turista.getNickname() + "'", usuario.class)
                                    .getSingleResult();
                turista turista1 = (turista) usuario;
                turista1.addCompra(compra);
                    
                return turista;
            } catch (Exception e) {
                log.error("Error al getUsuarioEmail. " + e.toString());
                return null;
            }
        
    }
    
    public boolean yaEstaInscripto(turista turista,salida salida){
    	EntityManagerFactory factory = EMFactory.getEntityManagerFactory();
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    
	    tx.begin();
	    
	    usuario user = em.createQuery("SELECT u FROM usuario u WHERE u.nickname = '" + turista.getNickname() + "'",usuario.class).getSingleResult();
	    turista turista1 = (turista) user;
	    
		List<inscripcion> LInscripciones = turista1.getInscripciones();
		for(inscripcion inscripcion : LInscripciones){
			if(inscripcion.getSalida().getNombre().equals(salida.getNombre())){
				return true;
			}
		}
		tx.commit();
		em.close();
		
		return false;
		
    }
}