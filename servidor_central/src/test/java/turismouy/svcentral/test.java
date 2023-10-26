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
import turismouy.svcentral.datatypes.dataCategoria;
import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.datatypes.dataPaquete;
import turismouy.svcentral.datatypes.dataSalida;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.entidades.categoria;
import turismouy.svcentral.entidades.departamento;
import turismouy.svcentral.entidades.inscripcion;
import turismouy.svcentral.entidades.paquete;
import turismouy.svcentral.entidades.proveedor;
import turismouy.svcentral.entidades.salida;
import turismouy.svcentral.entidades.turista;
import turismouy.svcentral.entidades.usuario;
import turismouy.svcentral.excepciones.NoExisteExcepcion;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;
import turismouy.svcentral.interfaces.IActividadController;
import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.interfaces.ICompraController;
import turismouy.svcentral.interfaces.IDepartamentoController;
import turismouy.svcentral.interfaces.IInscripcionController;
import turismouy.svcentral.interfaces.IPaqueteController;
import turismouy.svcentral.interfaces.ISalidaController;
import turismouy.svcentral.interfaces.IUsuarioController;
import turismouy.svcentral.manejadores.UsuarioManejador;
import turismouy.svcentral.utilidades.estadoActividad;
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
        
        em.close();
    }
    
    @org.junit.Test
    public void test02_CrearDepartamentoRepetido() {
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
        
        em.close();
    	
    }
    
    @org.junit.Test
    public void test05_CrearProveedorRepetido() {
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
        
        em.close();
    	
    }
    
    @org.junit.Test
    public void test08_CrearProveedorRepetidoConContrsaeña() {
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
        
        em.close();
    	
    }
    
    @org.junit.Test
    public void test11_CrearTuristaRep() {
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
        
        em.close();
    	
    }
    
    @org.junit.Test
    public void test14_CrearTuristaRepConContra() {
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
  
    //---------------------------------->TEST DE USUSARIOS<------------------------------------//
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
        em.close();
    }
    
    @org.junit.Test
    public void test18_ModificarDatosDeUsuarioPeroNoExiste() {
    	EntityManager em = factory.createEntityManager();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	List<dataUsuario> LDtUsuario = IUC.listarUsuarios();
        usuario usuarioOLD = em.createQuery("SELECT u FROM usuario u WHERE	u.nickname = 'PruebaTurNicknameConContra321'", usuario.class).getSingleResult();
        turista turistaOLD = (turista) usuarioOLD;
    	
    	
    	try {
			dataUsuario dtUsuario = IUC.mostrarInfo("No Existe");
			
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
        em.close();
    }
    
    @org.junit.Test
    public void test21_DarDeAltaCategoriaCorrectamente() {
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
        
        em.close();
    }
    
    @org.junit.Test
    public void test22_DarDeAltaActividad() {
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ICategoriaController ICA = Fabrica.getInstance().getICategoriaController();
    	
        IAC.getAllActividades();
        
    	ICA.listarCategorias();
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
    	
        actividad actividad = em.createQuery("SELECT a FROM actividad a WHERE a.nombreA = 'PruebaActividad'", actividad.class).getSingleResult();
        assertNotNull(actividad);
    	
        em.close();
    }
    
    @org.junit.Test
    public void test23_ConsultaDeActividadTuristica(){
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ICategoriaController ICA = Fabrica.getInstance().getICategoriaController();
    	
    	
    	IDC.listarDepartamentos();
    	IAC.getAllActividadesDepartamento("Prueba");
    	try {
			IAC.mostrarDatos("PruebaActividad");
		} catch (UsuarioNoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	ICA.listarCategorias();
    	
    	try {
			IAC.getActividadesPorCategoria("PruebaCategoria");
		} catch (NoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @org.junit.Test
    public void test24_AltaDeSalidaTuristica() {
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ISalidaController ISA =Fabrica.getInstance().getISalidaController();
    	
    	IDC.listarDepartamentos();
    	IAC.getAllActividadesConfirmadasDepartamento("Prueba");
    	
    	
    	try {
			ISA.crearSalida("PruebaSalida", 50, LocalDate.now(), LocalDate.of(2024, 1, 2), "LugarDePrueba", "PruebaActividad");
		} catch (UsuarioYaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParametrosInvalidosExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        salida salida = em.createQuery("SELECT s FROM salida s WHERE s.nombreS = 'PruebaSalida'", salida.class).getSingleResult();
        assertNotNull(salida);
        
        em.close();
    }
    @org.junit.Test
    public void test25_ConsultaDeSalidaTuristica() {
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ISalidaController ISC =Fabrica.getInstance().getISalidaController();
    	
    	IDC.listarDepartamentos();
    	IAC.getAllActividadesDepartamento("Prueba");
    	ISC.getAllSalidas();
    	ISC.mostrarDatosSalida("PruebaSalida");
    	
    }
    
    @org.junit.Test
    public void test26_InscripcionASalidaTuristica(){
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ISalidaController ISC = Fabrica.getInstance().getISalidaController();
    	IInscripcionController IIC = Fabrica.getInstance().getIInscripcionController();
    	
    	IDC.listarDepartamentos();
    	IAC.getAllActividadesDepartamento("Prueba");
    	ISC.obtenerSalidasVigentesPorActividad("PruebaActividad");
    	dataSalida DtSalida = ISC.mostrarDatosSalida("PruebaSalida");
    	DtSalida.getNombre();
    	DtSalida.getActividades();
    	DtSalida.getCapacidad();
    	DtSalida.getFechaAlta();
    	DtSalida.getFechaSalida();
    	DtSalida.getLugarSalida();
    	IUC.listarTuristas();
    	
    	try {
			IIC.crearInscripcion(LocalDate.now(), 3, "PruebaTurNickname321", "PruebaSalida", "PruebaActividad");
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
    	
        inscripcion inscripcion = em.createQuery("SELECT i FROM inscripcion i WHERE i.id = 1", inscripcion.class).getSingleResult();
        assertNotNull(inscripcion);
    	
        em.close();
    }
    
    @org.junit.Test
    public void test27_CrearPaqueteDeActividadTuristica() {
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ISalidaController ISC = Fabrica.getInstance().getISalidaController();
    	IInscripcionController IIC = Fabrica.getInstance().getIInscripcionController();
    	IPaqueteController IPC = Fabrica.getInstance().getIPaqueteController();
    	
    	try {
			IPC.crearPaquete("PaquetePrueba", "PruebaDescripcion", 100, 15, LocalDate.now());
		} catch (ParametrosInvalidosExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        paquete paquete = em.createQuery("SELECT p FROM paquete p WHERE p.nombre = 'PaquetePrueba'", paquete.class).getSingleResult();
        assertNotNull(paquete);
        
        em.close();
    }
    
    @org.junit.Test
    public void test28_Aceptar_ActividadTuristica(){
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ISalidaController ISC = Fabrica.getInstance().getISalidaController();
    	IInscripcionController IIC = Fabrica.getInstance().getIInscripcionController();
    	IPaqueteController IPC = Fabrica.getInstance().getIPaqueteController();
    	
    	IAC.getAllActividadesAgregadas();
    	try {
			IAC.modificarEstadoActividad("PruebaActividad",estadoActividad.CONFIRMADA);
		} catch (NoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParametrosInvalidosExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (YaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        actividad actividad = em.createQuery("SELECT a FROM actividad a WHERE a.nombreA = 'PruebaActividad'", actividad.class).getSingleResult();
        if(actividad.getEstado().equals(estadoActividad.CONFIRMADA)) {
        	assertTrue(true);
        }
        em.close();
    }
    
    @org.junit.Test
    public void test29_AgregarActividadAPaquete() {
       	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	IPaqueteController IPC = Fabrica.getInstance().getIPaqueteController();
    	
    	IPC.obtenerNombresPaquetes();
    	IPC.listarPaquetesSinComprar();
    	IDC.listarDepartamentos();
    	
    	
    	try {
			IAC.getActividadesDepartamentoNoPaquete("PaquetePrueba", "Prueba");
		} catch (UsuarioNoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			IPC.agregarActividadPaquete("PaquetePrueba", "PruebaActividad");
		} catch (NoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (YaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        paquete paquete = em.createQuery("SELECT p FROM paquete p WHERE p.nombre = 'PaquetePrueba'", paquete.class).getSingleResult();
        for(actividad act : paquete.getActividades()) {
        	if(act.getNombre().equals("PruebaActividad")) {
        		assertTrue(true);
        	}
        }
    	em.close();
    }
    
    @org.junit.Test
    public void test30_ConsultaDePaquete() {
    	IPaqueteController IPC = Fabrica.getInstance().getIPaqueteController();
    	
    	IPC.listarPaquetes();
    	
    	try {
        	IPC.mostrarInfo("PaquetePrueba");
		} catch (UsuarioNoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
    }
    
    @org.junit.Test
    public void test31_ModificarActividad(){
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	
    	try {
			IAC.modificarActividad("PruebaActividad", "DescripcionModificada", 30, 10, "CiudadModificada", LocalDate.now());
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
    	
        actividad actividad = em.createQuery("SELECT a FROM actividad a WHERE a.nombreA = 'PruebaActividad'", actividad.class).getSingleResult();
        if(actividad.getDescripcion().equals("DescripcionModificada")) {
        	assertTrue(true);
        }
        em.close();
        
    }
    
    @org.junit.Test
    public void test32_Login() {
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();

    	if(IUC.login("PruebaTurNicknameConContra321","PruebaContraseña")) {
    		assertTrue(true);
    	}
    }
    
    @org.junit.Test
    public void test33_LoginConMail() {
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();

    	if(IUC.login("PruebaTurNickNameConContra321@gmail.com","PruebaContraseña")) {
    		assertTrue(true);
    	}
    	
    }
 
    @org.junit.Test
    public void test34_DarDeAltaActividadYaConfrimada() {
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ICategoriaController ICA = Fabrica.getInstance().getICategoriaController();
    	
    		
        IAC.getAllActividades();
        
    	ICA.listarCategorias();
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
			assertTrue(true);
			e.printStackTrace();
		} catch (UsuarioNoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	em.close();
    }
    
    
    @org.junit.Test
    public void test35_DarDeAltaActividadConParamInvalid() {
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ICategoriaController ICA = Fabrica.getInstance().getICategoriaController();
        
        List<dataActividad> LDtAct = IAC.getAllActividades();
    	for(dataActividad DtAct :LDtAct) {
    		DtAct.getNombre();
    		DtAct.getCiudad();
    		DtAct.getCostoUni();
    		DtAct.getDepartamento();
    		DtAct.getDesc();
    		DtAct.getDtCategorias();
    		DtAct.getDtPaquetes();
    		DtAct.getDtSalidas();
    		DtAct.getDuracion();
    		DtAct.getEstado();
    		DtAct.getFechaCrea();
    		DtAct.getProveedor();
    		
    	}
        
    	List<dataCategoria> LDtCat = ICA.listarCategorias();
    	for(dataCategoria DtCat : LDtCat) {
    		DtCat.getNombre();
    	}
    	
    	List<dataDepartamento> LDtDepto = IDC.listarDepartamentos();
    	for(dataDepartamento DtDepto : LDtDepto) {
    		DtDepto.getNombre();
    		DtDepto.getActividades();
    		DtDepto.getDescripcion();
    		DtDepto.getUrl();
    		
    	}
    	
    	
    	List<dataUsuario> LDtUsu = IUC.listarProveedores();
    	for(dataUsuario DtUsu : LDtUsu) {
    		System.out.println(DtUsu.getNickname());
    	}
    	
    	List<String> LCat = new ArrayList<String>();
    	LCat.add("PruebaCategoria");
    	
    	try {
			IAC.crearActividad("","PruebaProveedorNicknameConContra222", "PruebaActividad", "PruebaDescripcion", 10, 23, "PruebaCiudad", LocalDate.now(), LCat);
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
    	
        actividad actividad = em.createQuery("SELECT a FROM actividad a WHERE a.nombreA = 'PruebaActividad'", actividad.class).getSingleResult();
        assertNotNull(actividad);
        
        em.close();
    }

    
    @org.junit.Test
    public void test36_ModificarActividadQueNoExiste(){
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	
    	try {
			IAC.modificarActividad("PruebaAct", "DescripcionModificada", 30, 10, "CiudadModificada", LocalDate.now());
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
    	
        actividad actividad = em.createQuery("SELECT a FROM actividad a WHERE a.nombreA = 'PruebaActividad'", actividad.class).getSingleResult();
        if(actividad.getDescripcion().equals("DescripcionModificada")) {
        	assertTrue(true);
        }
        em.close();
        
    }
    
    @org.junit.Test
    public void test37_DarDeAltaActividadSinDepto() {
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ICategoriaController ICA = Fabrica.getInstance().getICategoriaController();
        
        IAC.getAllActividades();
        
    	ICA.listarCategorias();
    	IDC.listarDepartamentos();
    	List<dataUsuario> LDtUsu = IUC.listarProveedores();
    	for(dataUsuario DtUsu : LDtUsu) {
    		System.out.println(DtUsu.getNickname());
    	}
    	
    	List<String> LCat = new ArrayList<String>();
    	LCat.add("PruebaCategoria");
    	
    	try {
			IAC.crearActividad("awldpalwd","PruebaProveedorNicknameConContra222", "PruebaActividad", "PruebaDescripcion", 10, 23, "PruebaCiudad", LocalDate.now(), LCat);
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
    	
        actividad actividad = em.createQuery("SELECT a FROM actividad a WHERE a.nombreA = 'PruebaActividad'", actividad.class).getSingleResult();
        assertNotNull(actividad);
        
        em.close();
    }
    
    @org.junit.Test
    public void test38_ConsultaDeActividadTuristica(){

    }
   
    @org.junit.Test
    public void test39_ModificarActividadVacia(){
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	
    	try {
			IAC.modificarActividad("", "", 30, 10, "CiudadModificada", LocalDate.now());
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
    	
        actividad actividad = em.createQuery("SELECT a FROM actividad a WHERE a.nombreA = 'PruebaActividad'", actividad.class).getSingleResult();
        if(actividad.getDescripcion().equals("DescripcionModificada")) {
        	assertTrue(true);
        }
        em.close();
        
    }
    
    @org.junit.Test
    public void test40_CrearPaqueteDeActividadTuristicaVacio() {
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ISalidaController ISC = Fabrica.getInstance().getISalidaController();
    	IInscripcionController IIC = Fabrica.getInstance().getIInscripcionController();
    	IPaqueteController IPC = Fabrica.getInstance().getIPaqueteController();
    	
    	try {
			IPC.crearPaquete("", "", 100, 15, LocalDate.now());
		} catch (ParametrosInvalidosExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        em.close();
    }
    
    @org.junit.Test
    public void test41_CrearPaqueteDeActividadTuristicaRepetido() {
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ISalidaController ISC = Fabrica.getInstance().getISalidaController();
    	IInscripcionController IIC = Fabrica.getInstance().getIInscripcionController();
    	IPaqueteController IPC = Fabrica.getInstance().getIPaqueteController();
    	
    	try {
			IPC.crearPaquete("PaquetePrueba", "PruebaDescripcion", 100, 15, LocalDate.now());
		} catch (ParametrosInvalidosExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        em.close();
    }
    
    @org.junit.Test
    public void test42_AgregarActividadAPaqueteConPAqueteNull() {
       	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	IPaqueteController IPC = Fabrica.getInstance().getIPaqueteController();
    	
    	IPC.obtenerNombresPaquetes();
    	IPC.listarPaquetesSinComprar();
    	IDC.listarDepartamentos();
    	
   	
    	try {
			IAC.getActividadesDepartamentoNoPaquete("PaquetePrueba", "Prue");
		} catch (UsuarioNoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			IPC.agregarActividadPaquete("PaquetePru", "PruebaActividad");
		} catch (NoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (YaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        paquete paquete = em.createQuery("SELECT p FROM paquete p WHERE p.nombre = 'PaquetePrueba'", paquete.class).getSingleResult();
        for(actividad act : paquete.getActividades()) {
        	if(act.getNombre().equals("PruebaActividad")) {
        		assertTrue(true);
        	}
        }
    	em.close();
    }
    
    @org.junit.Test
    public void test43_AgregarActividadAPaqueteConActNull() {
       	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	IPaqueteController IPC = Fabrica.getInstance().getIPaqueteController();
    	
    	IPC.obtenerNombresPaquetes();
    	IPC.listarPaquetesSinComprar();
    	IDC.listarDepartamentos();
    	
   	
    	try {
			IAC.getActividadesDepartamentoNoPaquete("PaquetePrue", "Prueba");
		} catch (UsuarioNoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			IPC.agregarActividadPaquete("PaquetePrueba", "PruebaActi");
		} catch (NoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (YaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        paquete paquete = em.createQuery("SELECT p FROM paquete p WHERE p.nombre = 'PaquetePrueba'", paquete.class).getSingleResult();
        for(actividad act : paquete.getActividades()) {
        	if(act.getNombre().equals("PruebaActividad")) {
        		assertTrue(true);
        	}
        }
    	em.close();
    }
    
    @org.junit.Test
    public void test44_ConsultaDePaquete() {
    	IPaqueteController IPC = Fabrica.getInstance().getIPaqueteController();
    	
    	IPC.listarPaquetes();
    	
    	try {
        	IPC.mostrarInfo("PaquetePru");
		} catch (UsuarioNoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    @org.junit.Test
    public void test45_DarDeAltaCategoriaRepetida() {
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
        
        em.close();
    }
    
    @org.junit.Test
    public void test46_DarDeAltaCategoriaParamInvali() {
    	EntityManager em = factory.createEntityManager();
    	ICategoriaController ICA = Fabrica.getInstance().getICategoriaController();
    	
    	try {
			ICA.crearCategoria(" ");
		} catch (YaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParametrosInvalidosExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        em.close();
    }
    
    @org.junit.Test
    public void test47_InscripcionASalidaTuristicaConActNull(){
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ISalidaController ISC = Fabrica.getInstance().getISalidaController();
    	IInscripcionController IIC = Fabrica.getInstance().getIInscripcionController();
    	
    	IDC.listarDepartamentos();
    	IAC.getAllActividadesDepartamento("Prueba");
    	ISC.obtenerSalidasVigentesPorActividad("PruebaActividad");
    	ISC.mostrarDatosSalida("PruebaSalida");
    	IUC.listarTuristas();
    	
    	try {
			IIC.crearInscripcion(LocalDate.now(), 3, "PruebaTurNickname321", "PruebaSalida", "PruebaActiv");
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
    	
        inscripcion inscripcion = em.createQuery("SELECT i FROM inscripcion i WHERE i.id = 1", inscripcion.class).getSingleResult();
        assertNotNull(inscripcion);
    	
        em.close();
    }
    
    @org.junit.Test
    public void test48_InscripcionASalidaTuristicaConSalidaNull(){
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ISalidaController ISC = Fabrica.getInstance().getISalidaController();
    	IInscripcionController IIC = Fabrica.getInstance().getIInscripcionController();
    	
    	IDC.listarDepartamentos();
    	IAC.getAllActividadesDepartamento("Prueba");
    	ISC.obtenerSalidasVigentesPorActividad("PruebaActividad");
    	ISC.mostrarDatosSalida("PruebaSalida");
    	IUC.listarTuristas();
    	
    	try {
			IIC.crearInscripcion(LocalDate.now(), 3, "PruebaTurNickname321", "PruebaSal", "PruebaActividad");
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
    	
        inscripcion inscripcion = em.createQuery("SELECT i FROM inscripcion i WHERE i.id = 1", inscripcion.class).getSingleResult();
        assertNotNull(inscripcion);
    	
        em.close();
    }
    
    @org.junit.Test
    public void test49_InscripcionASalidaTuristicaRepetida(){
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ISalidaController ISC = Fabrica.getInstance().getISalidaController();
    	IInscripcionController IIC = Fabrica.getInstance().getIInscripcionController();
    	
    	IDC.listarDepartamentos();
    	IAC.getAllActividadesDepartamento("Prueba");
    	ISC.obtenerSalidasVigentesPorActividad("PruebaActividad");
    	ISC.mostrarDatosSalida("PruebaSalida");
    	IUC.listarTuristas();
    	
    	try {
			IIC.crearInscripcion(LocalDate.now(), 3, "PruebaTurNickname321", "PruebaSalida", "PruebaActividad");
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
    	
        em.close();
    }
    
    @org.junit.Test
    public void test50_Compra() {
    	ICompraController ICoC = Fabrica.getInstance().getICompraController();
    	IPaqueteController IPC = Fabrica.getInstance().getIPaqueteController();
    	
    	List<dataPaquete> LDtPaquete = IPC.listarPaquetesSinComprar();
    	for(dataPaquete dtPaquete : LDtPaquete) {
    		System.out.println(dtPaquete.getNombre());
    	}
    	
    	try {
			ICoC.crearCompra(LocalDate.now(), 3, 15, LocalDate.of(2024, 10, 2), "PaquetePrueba", "PruebaTurNicknameConContra321");
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
    	
    }
    
    @org.junit.Test
    public void test51_CompraRepetida() {
    	ICompraController ICoC = Fabrica.getInstance().getICompraController();
    	IPaqueteController IPC = Fabrica.getInstance().getIPaqueteController();
    	
    	List<dataPaquete> LDtPaquete = IPC.listarPaquetesSinComprar();
    	for(dataPaquete dtPaquete : LDtPaquete) {
    		System.out.println(dtPaquete.getNombre());
    	}
    	
    	try {
			ICoC.crearCompra(LocalDate.now(), 3, 15, LocalDate.of(2024, 10, 2), "PaquetePrueba", "PruebaTurNicknameConContra321");
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
    	
    }
    
    @org.junit.Test
    public void test52_CompraConPaqueteNull() {
    	ICompraController ICoC = Fabrica.getInstance().getICompraController();
    	IPaqueteController IPC = Fabrica.getInstance().getIPaqueteController();
    	
    	List<dataPaquete> LDtPaquete = IPC.listarPaquetesSinComprar();
    	for(dataPaquete dtPaquete : LDtPaquete) {
    		System.out.println(dtPaquete.getNombre());
    	}
    	
    	try {
			ICoC.crearCompra(LocalDate.now(), 3, 15, LocalDate.of(2024, 10, 2), "PaquetePru", "PruebaTurNicknameConContra321");
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
    	
    }
    
    @org.junit.Test
    public void test53_CompraConTuristaNull() {
    	ICompraController ICoC = Fabrica.getInstance().getICompraController();
    	IPaqueteController IPC = Fabrica.getInstance().getIPaqueteController();
    	
    	List<dataPaquete> LDtPaquete = IPC.listarPaquetesSinComprar();
    	for(dataPaquete dtPaquete : LDtPaquete) {
    		System.out.println(dtPaquete.getNombre());
    	}
    	
    	try {
			ICoC.crearCompra(LocalDate.now(), 3, 15, LocalDate.of(2024, 10, 2), "PaquetePrueba", "PruebaTurNicknameConContra");
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
    	
    }
    
    @org.junit.Test
    public void test54_AltaDeSalidaTuristicaRepetida() {
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ISalidaController ISA =Fabrica.getInstance().getISalidaController();
    	
    	IDC.listarDepartamentos();
    	IAC.getAllActividadesConfirmadasDepartamento("Prueba");
    	
    	
    	try {
			ISA.crearSalida("PruebaSalida", 50, LocalDate.now(), LocalDate.of(2024, 1, 2), "LugarDePrueba", "PruebaActividad");
		} catch (UsuarioYaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParametrosInvalidosExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        salida salida = em.createQuery("SELECT s FROM salida s WHERE s.nombreS = 'PruebaSalida'", salida.class).getSingleResult();
        assertNotNull(salida);
        
        em.close();
    }
    
    @org.junit.Test
    public void test55_AltaDeSalidaTuristicaConActNull() {
    	EntityManager em = factory.createEntityManager();
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ISalidaController ISA =Fabrica.getInstance().getISalidaController();
    	
    	IDC.listarDepartamentos();
    	IAC.getAllActividadesConfirmadasDepartamento("Prueba");
    	
    	
    	try {
			ISA.crearSalida("PruebaSalida", 50, LocalDate.now(), LocalDate.of(2024, 1, 2), "LugarDePrueba", "PruebaActiv");
		} catch (UsuarioYaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParametrosInvalidosExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        salida salida = em.createQuery("SELECT s FROM salida s WHERE s.nombreS = 'PruebaSalida'", salida.class).getSingleResult();
        assertNotNull(salida);
        
        em.close();
    }
    
    @org.junit.Test
    public void test56_DarDeAltaActividadConCatNull() {
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ICategoriaController ICA = Fabrica.getInstance().getICategoriaController();
    	
        IAC.getAllActividades();
        
    	ICA.listarCategorias();
    	IDC.listarDepartamentos();
    	List<dataUsuario> LDtUsu = IUC.listarProveedores();
    	for(dataUsuario DtUsu : LDtUsu) {
    		System.out.println(DtUsu.getNickname());
    	}
    	
    	List<String> LCat = new ArrayList<String>();
    	LCat.add("PruebaCateg");
    	
    	try {
			IAC.crearActividad("Prueba","PruebaProveedorNicknameConContra222", "PruebaActividad2", "PruebaDescripcion", 10, 23, "PruebaCiudad", LocalDate.now(), LCat);
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
    	
    }
    
    @org.junit.Test
    public void test57_DarDeAltaActividadConProvNull() {
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
    	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
    	ICategoriaController ICA = Fabrica.getInstance().getICategoriaController();
    	
        IAC.getAllActividades();
        
    	ICA.listarCategorias();
    	IDC.listarDepartamentos();
    	List<dataUsuario> LDtUsu = IUC.listarProveedores();
    	for(dataUsuario DtUsu : LDtUsu) {
    		System.out.println(DtUsu.getNickname());
    	}
    	
    	List<String> LCat = new ArrayList<String>();
    	LCat.add("PruebaCateg");
    	
    	try {
			IAC.crearActividad("Prueba","PruebaProveedorNicknameConContra", "PruebaActividad2", "PruebaDescripcion", 10, 23, "PruebaCiudad", LocalDate.now(), LCat);
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
    	
    }
    @org.junit.Test
    public void test58_Aceptar_ActividadTuristicaPeroConAgregada(){
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	
    	IAC.getAllActividadesAgregadas();
    	try {
			IAC.modificarEstadoActividad("PruebaActividad",estadoActividad.AGREGADA);
		} catch (NoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParametrosInvalidosExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (YaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @org.junit.Test
    public void test59_Aceptar_ActividadTuristicaPeroConActNull(){
    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
    	
    	IAC.getAllActividadesAgregadas();
    	try {
			IAC.modificarEstadoActividad("PruebaActiv",estadoActividad.AGREGADA);
		} catch (NoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParametrosInvalidosExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (YaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    
    
    @org.junit.Test
    public void test99_EliminarSalida() {
    	ISalidaController ISC = Fabrica.getInstance().getISalidaController();
    	
    	ISC.eliminarSalida("PruebaSalida");

    }
    
}
