package turismouy.svcentral.manejadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import turismouy.svcentral.EMFactory;
import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.entidades.departamento;
import turismouy.svcentral.utilidades.log;

public class DepartamentoManejador {
    private static DepartamentoManejador instancia = null;

    EntityManagerFactory factory = EMFactory.getEntityManagerFactory();

    private DepartamentoManejador() {}

    // Singleton.
    public static DepartamentoManejador getinstance() {
        if (instancia == null)
            instancia = new DepartamentoManejador();
        return instancia;
    }

    public void updateDepartamento(departamento departamento){
        // Para cada función hay que crear un nuevo em y tx.
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        String nombre = departamento.getNombre();

        try {
            tx.begin();

            // Se actualiza el archivo.
            em.merge(departamento);

            tx.commit();

            log.info("El departamento se actualizó '" + nombre + "' correctamente");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Actualizado el departamento '" + departamento.getNombre() + "' errrono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    };

    public void addDepartamento(departamento departamento) {
        // Para cada función hay que crear un nuevo em y tx.
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();

        String nombre = departamento.getNombre();

        try {
            tx.begin();

            // Se guarda el archivo.
            em.persist(departamento);

            tx.commit();
            log.info("[departamentoManejador] Se agrego correctamente el departamento '" + departamento.getNombre() + "'");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Guardado de Departamento '" + nombre + "' errrono.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public departamento getDepartamento(String nombre) {
	    EntityManager em = factory.createEntityManager();
        ActividadManejador am = ActividadManejador.getinstance();
                
        nombre = nombre.toLowerCase();
        departamento departamento = null;

        try {
            departamento = em.createQuery("SELECT d FROM departamento d LEFT JOIN FETCH d.actividades WHERE LOWER(d.nombre) = :nombre", departamento.class)
                .setParameter("nombre", nombre)
                .getSingleResult();

                // Se crea una lista de actividades que luego se va a setear en el departamento.
                List<actividad> actividades = new ArrayList<actividad>();

                // Va por cada actividad del departamento, para luego traer del manejador de actividad.
                for (actividad act : departamento.getActividades()) {

                    // Se obtiene la actividad y la guarda en la lista de actividades.
                    actividad actividad = am.getActividadWithoutEstado(act.getNombre());
                    actividades.add(actividad);
                }

                // La lista de actividades (que tiene paquetes, departamentos, salidas, etc) se setea al departamento.
                departamento.setActividades(actividades);
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        } finally {
            em.close();
        }

        return departamento;
    }

    public List<departamento> getAllDepartamentos() {
        EntityManager em = factory.createEntityManager();
        
        List<departamento> deptos           = new ArrayList<departamento>();
        List<departamento> departamentos    = new ArrayList<departamento>();

        try {
            deptos = em.createQuery("SELECT DISTINCT d FROM departamento d LEFT JOIN FETCH d.actividades", departamento.class)
                .getResultList();

            for (departamento departamento : deptos) {
                departamentos.add(getDepartamento(departamento.getNombre()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // return null;
        } finally {
            em.close();
        }

        return departamentos;
    }
}
