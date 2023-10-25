package turismouy.svcentral;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.entidades.categoria;
import turismouy.svcentral.entidades.departamento;
import turismouy.svcentral.entidades.proveedor;
import turismouy.svcentral.entidades.turista;
import turismouy.svcentral.entidades.usuario;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;
import turismouy.svcentral.interfaces.IActividadController;
import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.interfaces.IDepartamentoController;
import turismouy.svcentral.interfaces.IUsuarioController;
import turismouy.svcentral.utilidades.log;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class test {

    EntityManagerFactory factory = EMFactory.getEntityManagerFactory();
    

  //--------------------------------> TEST DE DEPARAMENTOS <------------------------------//
    
    @org.junit.Test
    public void test01_CrearDepartamentoCorrecto() {
        EntityManager em = factory.createEntityManager();
        IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
        
        	IDC.listarDepartamentos();
        try {
            IDC.crearDepartamento("Prueba", "Prueba", "Prueba");
            
        } catch (ParametrosInvalidosExcepcion e) {
        	fail("Se esperaba que lanzara una excepción ParametrosInvalidosExcepcion");
        	e.printStackTrace();
        } catch (UsuarioYaExisteExcepcion e) {
            fail("Ya existe este departamento");
            e.printStackTrace();
        }

        // Verificar si el departamento se ha creado correctamente
        departamento departamento = em.createQuery("SELECT d FROM departamento d WHERE d.nombre = 'Prueba'", departamento.class).getSingleResult();
        assertNotNull(departamento);
    }
    
    @org.junit.Test
    public void test02_CrearDepartamentoRepetido() {
        EntityManager em = factory.createEntityManager();
        IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
        
        IDC.listarDepartamentos();
        try {
            IDC.crearDepartamento("Prueba", "Prueba", "Prueba");
            
        } catch (ParametrosInvalidosExcepcion e) {
        	fail("Se esperaba que lanzara una excepción ParametrosInvalidosExcepcion");
        	e.printStackTrace();
        } catch (UsuarioYaExisteExcepcion e) {
            assertTrue(true);
            e.printStackTrace();
        }
    }
    
    @org.junit.Test()
    public void test03_CrearDepartamentoConParaInvalid() {
        EntityManager em = factory.createEntityManager();
        IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
        
        IDC.listarDepartamentos();
		try {
			IDC.crearDepartamento("", "Prueba1", "Prueba1");
		} catch (ParametrosInvalidosExcepcion e) {
			assertTrue(true);
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			fail("Departamento ya existe");
			e.printStackTrace();
		}
            
    }
    //--------------------------------> TEST DE PROVEEDORES <------------------------------//
    
    @org.junit.Test
    public void test04_CrearProveedorCorrectamente() {
    	EntityManager em = factory.createEntityManager();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	
    	try {
			IUC.registrarProveedor("PruebaProveedorNickname222", "PruebaNombre1", "PruebaApellido1", "Prueba222@gmail.com", "PruebaDescripcion","PruebaURL", LocalDate.of(2000, 1, 3));
		} catch (ParametrosInvalidosExcepcion e) {
			fail("Parametros Invalidos");
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			fail("Ya existe este proveedor");
			e.printStackTrace();
		}
    	
        usuario usuario = em.createQuery("SELECT u FROM usuario u WHERE	u.nickname = 'PruebaProveedorNickname222'", usuario.class).getSingleResult();
        proveedor proveedor = (proveedor) usuario;
        assertNotNull(proveedor);
    	
    }
    
    @org.junit.Test
    public void test05_CrearProveedorRepetido() {
    	EntityManager em = factory.createEntityManager();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	
    	try {
			IUC.registrarProveedor("PruebaProveedorNickname222", "PruebaNombre1", "PruebaApellido1", "Prueba5@gmail.com", "PruebaDescripcion","PruebaURL", LocalDate.of(2000, 1, 3));
		} catch (ParametrosInvalidosExcepcion e) {
			fail("Parametros Invalidos");
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			assertTrue(true);
			e.printStackTrace();
		}
    	
    }
    
    @org.junit.Test
    public void test06_CrearProveedorConMailInvalid() {
    	EntityManager em = factory.createEntityManager();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	
    	try {
			IUC.registrarProveedor("PruebaNickname5", "PruebaNombre1", "PruebaApellido1", "Prueba5gmail.com", "PruebaDescripcion","PruebaURL", LocalDate.of(2000, 1, 3));
		} catch (ParametrosInvalidosExcepcion e) {
			assertTrue(true);
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			fail("Parametros Invalidos");
			e.printStackTrace();
		}
    	
    }
    
    @org.junit.Test
    public void test07_CrearProveedorCorrectamenteConContrsaeña() {
    	EntityManager em = factory.createEntityManager();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	
    	try {
			IUC.registrarProveedor("PruebaProveedorNicknameConContra222", "PruebaNombre1", "PruebaApellido1", "PruebaConContra222@gmail.com", "PruebaDescripcion","PruebaURL", LocalDate.of(2000, 1, 3), "PruebaContraseña");
		} catch (ParametrosInvalidosExcepcion e) {
			fail("Parametros Invalidos");
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			fail("Ya existe este proveedor");
			e.printStackTrace();
		}
    	
        usuario usuario = em.createQuery("SELECT u FROM usuario u WHERE	u.nickname = 'PruebaProveedorNicknameConContra222'", usuario.class).getSingleResult();
        proveedor proveedor = (proveedor) usuario;
        assertNotNull(proveedor);
    	
    }
    
    @org.junit.Test
    public void test08_CrearProveedorRepetidoConContrsaeña() {
    	EntityManager em = factory.createEntityManager();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	
    	try {
    		IUC.registrarProveedor("PruebaProveedorNicknameConContra222", "PruebaNombre1", "PruebaApellido1", "PruebaConContra222@gmail.com", "PruebaDescripcion","PruebaURL", LocalDate.of(2000, 1, 3), "PruebaContraseña");
		} catch (ParametrosInvalidosExcepcion e) {
			fail("Parametros Invalidos");
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			assertTrue(true);
			e.printStackTrace();
		}
    	
    }
    
    @org.junit.Test
    public void test09_CrearProveedorConMailInvalidConContraseña() {
    	EntityManager em = factory.createEntityManager();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	
    	try {
    		IUC.registrarProveedor("PruebaNicknameConContra129", "PruebaNombre1", "PruebaApellido1", "Prueba7gmail.com", "PruebaDescripcion","PruebaURL", LocalDate.of(2000, 1, 3), "PruebaContraseña");
		} catch (ParametrosInvalidosExcepcion e) {
			assertTrue(true);
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			fail("Parametros Invalidos");
			e.printStackTrace();
		}
    	
    }
    
    //--------------------------------> TEST DE TURISTA <------------------------------//
    
    @org.junit.Test
    public void test10_CrearTuristaCorrectamente() {
    	EntityManager em = factory.createEntityManager();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	
    	try {
			IUC.registrarTurista("PruebaTurNickname321", "PruebaNombre", "PruebaApellido", "PruebaTurNickName321@gmail.com", "PruebaNacionalidad", LocalDate.of(2000, 1, 3));
		} catch (ParametrosInvalidosExcepcion e) {
			fail("Parametros Invalidos");
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			fail("Ya existe el turista");
			e.printStackTrace();
		}
        usuario usuario = em.createQuery("SELECT u FROM usuario u WHERE	u.nickname = 'PruebaTurNickname321'", usuario.class).getSingleResult();
        turista turista = (turista) usuario;
        assertNotNull(turista);
    	
    }
    
    @org.junit.Test
    public void test11_CrearTuristaRep() {
    	EntityManager em = factory.createEntityManager();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	
    	try {
			IUC.registrarTurista("PruebaTurNickname321", "PruebaNombre", "PruebaApellido", "PruebaTurNickName321@gmail.com", "PruebaNacionalidad", LocalDate.of(2000, 1, 3));
		} catch (ParametrosInvalidosExcepcion e) {
			fail("Parametros Invalidos");
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			assertTrue(true);
			e.printStackTrace();
		}
    }
    
    
    @org.junit.Test
    public void test12_CrearTuristaConMailInvalid() {
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	
    	try {
    		IUC.registrarTurista("PruebaTurNickname", "PruebaNombre1", "PruebaApellido1", "PruebaTurgmail.com", "PruebaNacionalidad", LocalDate.of(2000, 1, 3));
		} catch (ParametrosInvalidosExcepcion e) {
			assertTrue(true);
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			fail("Parametros Invalidos");
			e.printStackTrace();
		}
    	
    }
    
    @org.junit.Test
    public void test13_CrearTuristaCorrectoConContra() {
    	EntityManager em = factory.createEntityManager();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	
    	try {
			IUC.registrarTurista("PruebaTurNicknameConContra321", "PruebaNombre", "PruebaApellido", "PruebaTurNickNameConContra321@gmail.com", "PruebaNacionalidad", LocalDate.of(2000, 1, 3),"PruebaContraseña");
		} catch (ParametrosInvalidosExcepcion e) {
			fail("Parametros Invalidos");
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			fail("Ya existe el turista");
			e.printStackTrace();
		}
    	
        usuario usuario = em.createQuery("SELECT u FROM usuario u WHERE	u.nickname = 'PruebaTurNicknameConContra321'", usuario.class).getSingleResult();
        turista turista = (turista) usuario;
        assertNotNull(turista);
    	
    }
    
    @org.junit.Test
    public void test14_CrearTuristaRepConContra() {
    	EntityManager em = factory.createEntityManager();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	
    	try {
    		IUC.registrarTurista("PruebaTurNicknameConContra321", "PruebaNombre1", "PruebaApellido1", "PruebaTurNickNameConContra321@gmail.com", "PruebaNacionalidad", LocalDate.of(2000, 1, 3),"PruebaContraseña");
		} catch (ParametrosInvalidosExcepcion e) {
			fail("Parametros Invalidos");
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			assertTrue(true);
			e.printStackTrace();
		}
    }
    
    
    @org.junit.Test
    public void test15_CrearTuristaConMailInvalidConContra() {
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	
    	try {
    		IUC.registrarTurista("PruebaTurNickname", "PruebaNombre1", "PruebaApellido1", "PruebaTurgmail.com", "PruebaNacionalidad", LocalDate.of(2000, 1, 3),"PruebaContraseña");
		} catch (ParametrosInvalidosExcepcion e) {
			assertTrue(true);
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			fail("Parametros Invalidos");
			e.printStackTrace();
		}
    	
    }
  
    @org.junit.Test
    public void test16_ConsultaUsuario() {
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	
    	List<dataUsuario> LDtUsuario = IUC.listarUsuarios();
    	for(dataUsuario DtUsu : LDtUsuario) {
    		DtUsu.getNickname();
    		DtUsu.getNombre();
    		DtUsu.getApellido();
    		DtUsu.getEmail();
    		DtUsu.getDescripcion();
    		DtUsu.getNacimiento();
    		DtUsu.getNacionalidad();
    		DtUsu.getDescripcion();
    		DtUsu.getUrl();
    		DtUsu.getActividades();
    		DtUsu.getSalidas();
    	}
    	//Hacer los casos de uso Consulta de actividad turistica y de salida turistica 
    	
    	
    }
  
    @org.junit.Test
    public void test17_ModificarDatosDeUsuarioCorrectamente() {
    	EntityManager em = factory.createEntityManager();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	List<dataUsuario> LDtUsuario = IUC.listarUsuarios();
        usuario usuarioOLD = em.createQuery("SELECT u FROM usuario u WHERE	u.nickname = 'PruebaTurNicknameConContra321'", usuario.class).getSingleResult();
        turista turistaOLD = (turista) usuarioOLD;
    	
    	
    	try {
			dataUsuario dtUsuario = IUC.mostrarInfo("PruebaTurNicknameConContra321");
			
		} catch (UsuarioNoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			IUC.modificarUsuario("PruebaTurNicknameConContra321","NombreModificado","ApellidoModificado", LocalDate.of(2000, 1, 3) );
		} catch (ParametrosInvalidosExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        usuario usuarioNEW = em.createQuery("SELECT u FROM usuario u WHERE	u.nickname = 'PruebaTurNicknameConContra321'", usuario.class).getSingleResult();
        turista turistaNEW = (turista) usuarioNEW;
        
        if(!turistaNEW.getNombre().equals(turistaOLD.getNombre())) {
        	assertTrue(true);
        	
        }
    }
    @org.junit.Test
    public void test18_DarDeAltaCategoriaCorrectamente() {
    	EntityManager em = factory.createEntityManager();
    	ICategoriaController ICA = Fabrica.getInstance().getICategoriaController();
    	
    	try {
			ICA.crearCategoria("PruebaCategoria");
		} catch (YaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParametrosInvalidosExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        categoria categoria = em.createQuery("SELECT c FROM categoria c WHERE c.nombre = 'PruebaCategoria'", categoria.class).getSingleResult();
        assertNotNull(categoria);
    }
    
    @org.junit.Test
    public void test19_DarDeAltaActividad() {
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	
    	IDC.listarDepartamentos();
    	List<dataUsuario> LDtUsu = IUC.listarProveedores();
    	for(dataUsuario DtUsu : LDtUsu) {
    		System.out.println(DtUsu.getNickname());
    	}
    	
    	List<String> LCat = new ArrayList<String>();
    	LCat.add("PruebaCategoria");
    	
    	try {
			IAC.crearActividad("Prueba","PruebaProveedorNicknameConContra222", "PruebaActividad", "PruebaDescripcion", 10, 23, "PruebaCiudad", LocalDate.now(), LCat);
		} catch (ParametrosInvalidosExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        actividad actividad = em.createQuery("SELECT a FROM actividad a WHERE a.nombreA = :'PruebaActividad'", actividad.class).getSingleResult();
        assertNotNull(actividad);
    	
    }
    
}
