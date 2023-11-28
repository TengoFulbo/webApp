package turismouy.svcentral.middlewares;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.ws.Endpoint;

import turismouy.svcentral.Fabrica;
// import turismouy.svcentral.Configuracion;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.datatypes.dataCategoria;
import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.datatypes.dataPaquete;
import turismouy.svcentral.datatypes.dataSalida;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.excepciones.NoExisteExcepcion;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;
import turismouy.svcentral.interfaces.IActividadController;
import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.interfaces.IDepartamentoController;
import turismouy.svcentral.interfaces.IInscripcionController;
import turismouy.svcentral.interfaces.IPaqueteController;
import turismouy.svcentral.interfaces.ISalidaController;
import turismouy.svcentral.interfaces.IUsuarioController;
import turismouy.svcentral.utilidades.LocalDateAdapter;
import turismouy.svcentral.utilidades.log;

@WebService
public class publicador {

	// Configuracion config = new Configuracion();

	// String URI = "http://" + config.getIP() + ":" + config.getPuerto() + config.getPath();
    String ipServidor = "http://localhost";
    String puerto = ":5000";
    String uri = "/API/Servicios";
	Boolean debug = true;

    IUsuarioController 		IUC = Fabrica.getInstance().getIUsuarioController();
	IActividadController 	IAC = Fabrica.getInstance().getIActividadController();
	IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
	ICategoriaController 	ICC = Fabrica.getInstance().getICategoriaController();
	ISalidaController 		ISC = Fabrica.getInstance().getISalidaController();
	IPaqueteController 		IPC = Fabrica.getInstance().getIPaqueteController();
	IInscripcionController  IIC = Fabrica.getInstance().getIInscripcionController();

    private Endpoint endpoint = null;

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish(ipServidor + puerto + uri, this);
        // endpoint = Endpoint.publish(URI, this);
        log.info("Publicando en la direccion: " + ipServidor + puerto + uri);
        // log.info("Publicando en la direccion: " + URI);
    };

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return this.endpoint;
    };

	@WebMethod
	public boolean UsuarioLogin(
			@WebParam(name = "usuario")		String usuario,
			@WebParam(name = "password")	String password
		) {
		boolean valid = false;
		if (debug) log.info("[Publicador] Recibiendo UsuarioLogin");

		try {
            valid = IUC.login(usuario, password);
        } catch (Exception e) {
            log.error("[Publicador] Error: UsuarioLogin");
        }

		return valid;
	};

	@WebMethod
	public dataUsuario UsuarioMostrarInfo(@WebParam(name = "nickname") String nickname) throws NoExisteExcepcion {
		dataUsuario usuario = null;
		if (debug) log.info("[Publicador] Recibiendo UsuarioMostrarInfo");

		try {
			usuario = IUC.mostrarInfo(nickname);
		} catch (UsuarioNoExisteExcepcion e) {
			log.error("[Publicador] Error: UsuarioMostrarInfo");
			throw new NoExisteExcepcion(e.getMessage());
		} catch (Exception e) {
            log.error("[Publicador] Error: UsuarioMostrarInfo");
		}

		return usuario;
    };

	@WebMethod
	public void UsuarioModificarUsuario(
		@WebParam(name = "nickname")	String nickname,
		@WebParam(name = "nombre")		String nombre,
		@WebParam(name = "apellido")	String apellido,
		@WebParam(name = "nacimiento")	@XmlJavaTypeAdapter(LocalDateAdapter.class) LocalDate nacimiento
	) {
		if (debug) log.info("[Publicador] Recibiendo UsuarioModificarUsuario");

		try {
			IUC.modificarUsuario(nickname, nombre, apellido, nacimiento);
		} catch (Exception e) {
			log.info("[Publicador] Error: UsuarioModificarUsuario");
		}
	};

	@WebMethod
	public void UsuarioModificarUsuarioImagen(
		@WebParam(name = "nickname")	String nickname,
		@WebParam(name = "nombre")		String nombre,
		@WebParam(name = "apellido")	String apellido,
		@WebParam(name = "nacimiento")	@XmlJavaTypeAdapter(LocalDateAdapter.class) LocalDate nacimiento,
		@WebParam(name = "imageData") 	byte[] imageData
	) {
		if (debug) log.info("[Publicador] Recibiendo UsuarioModificarUsuarioImagen");

		try {
			IUC.modificarUsuario(nickname, nombre, apellido, nacimiento, imageData);
		} catch (Exception e) {
			log.info("[Publicador] Error: UsuarioModificarUsuarioImagen");
		}
	};

    @WebMethod
    public void UsuarioRegistrarTuristaPassword(
			@WebParam(name = "nickname")		String nickname,
			@WebParam(name = "nombre")			String nombre,
			@WebParam(name = "apellido")		String apellido,
			@WebParam(name = "email")			String email,
			@WebParam(name = "nacionalidad")	String nacionalidad,
			@WebParam(name = "nacimiento") @XmlJavaTypeAdapter(LocalDateAdapter.class)		LocalDate nacimiento,
			@WebParam(name = "password")		String password
		) {
		if (debug) log.info("[Publicador] Recibiendo UsuarioRegistrarTuristaPassword");

        try {
            IUC.registrarTurista(nickname, nombre, apellido, email, nacionalidad, nacimiento, password);
        } catch (Exception e) {
            log.error("[Publicador] Error: UsuarioRegistrarTuristaPassword");
        }
    };

    @WebMethod
    public void UsuarioRegistrarProveedorPassword(
		@WebParam(name = "nickname")	String nickname, 
		@WebParam(name = "nombre")		String nombre, 
		@WebParam(name = "apellido")	String apellido, 
		@WebParam(name = "email")		String email, 
		@WebParam(name = "descripcion")	String descripcion, 
		@WebParam(name = "url")			String url, 
		@WebParam(name = "nacimiento") @XmlJavaTypeAdapter(LocalDateAdapter.class)	LocalDate nacimiento, 
		@WebParam(name = "password")	String password
    ) {
		if (debug) log.info("[Publicador] Recibiendo UsuarioRegistrarProveedorPassword");
		
		try {
			IUC.registrarProveedor(nickname, nombre, apellido, email, descripcion, url, nacimiento, password);
		} catch (Exception e) {
            log.error("[Publicador] Error: UsuarioRegistrarProveedorPassword");
		}
    }

	@WebMethod
	public List<dataUsuario> UsuarioListarUsuarios() {
		List<dataUsuario> usuarios = new ArrayList<dataUsuario>();
		if (debug) log.info("[Publicador] Recibiendo UsuarioListarUsuarios");

		try {
			usuarios = IUC.listarUsuarios();
		} catch (Exception e) {
            log.error("[Publicador] Error: UsuarioListarUsuarios");
		}

		return usuarios;
	};


	@WebMethod
	public List<dataActividad> ActividadGetAllActividades() {
		List<dataActividad> actividades = new ArrayList<dataActividad>();
		if (debug) log.info("[Publicador] Recibiendo ActividadGetAllActividades");

		try {
			actividades = IAC.getAllActividades();
		} catch (Exception e) {
            log.error("[Publicador] Error: ActividadGetAllActividades");
		}

		return actividades;
	}

	@WebMethod
	public dataActividad ActividadGetActividad(String nombreActividad) {
		if (debug) log.info("[Publicador] Recibiendo ActividadGetActividad");
		dataActividad actividad = null;

		try {
			actividad = IAC.mostrarDatos(nombreActividad);
		} catch (Exception e) {
            log.error("[Publicador] Error: ActividadGetAllActividadesDepartamento");
		}

		return actividad;
	}

	@WebMethod
	public void ActividadAumentarVisita(@WebParam(name = "nombreActividad") String nombreActividad) throws NoExisteExcepcion {
		log.info("[Publicador] Recibiendo ActividadAumentarVisita");
		
		try {
			IAC.aumentarVisita(nombreActividad);
		} catch (NoExisteExcepcion e) {
			log.error("[Publicador] Error: ActividadAumentarVisita");
			throw new NoExisteExcepcion(e.getMessage());
		} catch (Exception e) {
			log.error("[Publicador] Error: ActividadAumentarVisita");
		}
	}; 

	@WebMethod
	public List<dataActividad> ActividadGetAllActividadesDepartamento(@WebParam(name = "nombreDep") String nombreDep) {
		List<dataActividad> actividades = new ArrayList<dataActividad>();
		if (debug) log.info("[Publicador] Recibiendo ActividadGetAllActividadesDepartamento");


		try {
			actividades = IAC.getAllActividadesDepartamento(nombreDep);
		} catch (Exception e) {
            log.error("[Publicador] Error: ActividadGetAllActividadesDepartamento");
		}

		return actividades;
	}
	
	@WebMethod
	public void actividadCrearActividad(@WebParam(name = "nombreDepto") String nombreDepto,
										@WebParam(name = "nombreProv") String nombreProv,
										@WebParam(name = "nombre") String nombre,
										@WebParam(name = "desc") String desc,
										@WebParam(name = "duracion") int duracion,
										@WebParam(name = "costoUni") int costoUni,
										@WebParam(name = "ciudad") String ciudad,
										@WebParam(name = "fechaCrea") @XmlJavaTypeAdapter(LocalDateAdapter.class) LocalDate fechaCrea,
										@WebParam(name = "categorias") List<String> categorias) throws ParametrosInvalidosExcepcion, YaExisteExcepcion, NoExisteExcepcion {
		
		try {
			IAC.crearActividad(nombreDepto, nombreProv, nombre, desc, duracion, costoUni, ciudad, fechaCrea, categorias);
		} catch (ParametrosInvalidosExcepcion e) {
			throw new ParametrosInvalidosExcepcion();
		} catch (UsuarioYaExisteExcepcion e) {
			throw new YaExisteExcepcion(e.getMessage());
		} catch (UsuarioNoExisteExcepcion e) {
			throw new NoExisteExcepcion(e.getMessage());
		}
	}
	
	@WebMethod
	public void actividadCrearActividadUrl(@WebParam(name = "nombreDepto") String nombreDepto,
										@WebParam(name = "nombreProv") String nombreProv,
										@WebParam(name = "nombre") String nombre,
										@WebParam(name = "desc") String desc,
										@WebParam(name = "duracion") int duracion,
										@WebParam(name = "costoUni") int costoUni,
										@WebParam(name = "ciudad") String ciudad,
										@WebParam(name = "url") String url,
										@WebParam(name = "fechaCrea") @XmlJavaTypeAdapter(LocalDateAdapter.class) LocalDate fechaCrea,
										@WebParam(name = "categorias") List<String> categorias) throws ParametrosInvalidosExcepcion, YaExisteExcepcion, NoExisteExcepcion {
		
		try {
			IAC.crearActividadUrl(nombreDepto, nombreProv, nombre, desc, duracion, costoUni, ciudad, url, fechaCrea, categorias);
		} catch (ParametrosInvalidosExcepcion e) {
			throw new ParametrosInvalidosExcepcion();
		} catch (UsuarioYaExisteExcepcion e) {
			throw new YaExisteExcepcion(e.getMessage());
		} catch (UsuarioNoExisteExcepcion e) {
			throw new NoExisteExcepcion(e.getMessage());
		}
	}
	
	@WebMethod
	public void actividadFinalizarActividad(@WebParam(name = "nombre") String nombre) throws NoExisteExcepcion, ParametrosInvalidosExcepcion {
		
		try {
			IAC.finalizarActividad(nombre);
		} catch (NoExisteExcepcion e) {
			throw new NoExisteExcepcion(e.getMessage());
		} catch (ParametrosInvalidosExcepcion e) {
			throw new ParametrosInvalidosExcepcion();
		}
	}

	@WebMethod
	public List<dataDepartamento> DepartamentoListarDepartamentos() {
		List<dataDepartamento> departamentos = new ArrayList<dataDepartamento>();
		if (debug) log.info("[Publicador] Recibiendo DepartamentoListarDepartamentos");

		try {
			departamentos = IDC.listarDepartamentos();
		} catch (Exception e) {
            log.error("[Publicador] Error: DepartamentoListarDepartamentos");
		}

		return departamentos;
	}

	@WebMethod
	public List<dataCategoria> CategoriaListarCategorias() {
		List<dataCategoria> categorias = new ArrayList<dataCategoria>();
		if (debug) log.info("[Publicador] Recibiendo CategoriaListarCategorias");

		try {
			categorias = ICC.listarCategorias();
		} catch (Exception e) {
            log.error("[Publicador] Error: CategoriaListarCategorias");
		}

		return categorias;
	}

	@WebMethod
	public void SalidaCrearSalida(
			@WebParam(name = "nombre")			String nombre,
			@WebParam(name = "capacidad")		int capacidad,
			@WebParam(name = "fechaAlta") 	@XmlJavaTypeAdapter(LocalDateAdapter.class)		LocalDate fechaAlta,
			@WebParam(name = "fechaSalida")	@XmlJavaTypeAdapter(LocalDateAdapter.class) 	LocalDate fechaSalida,
			@WebParam(name = "lugarSalida")		String lugarSalida,
			@WebParam(name = "nombreActividad")	String nombreActividad
	) throws ParametrosInvalidosExcepcion, YaExisteExcepcion, NoExisteExcepcion {
		if (debug) log.info("[Publicador] Recibiendo SalidaCrearSalida");

		try {
			ISC.crearSalida(nombre, capacidad, fechaAlta, fechaSalida, lugarSalida, nombreActividad);
		} catch (ParametrosInvalidosExcepcion e) {
			throw new ParametrosInvalidosExcepcion();
		} catch (UsuarioNoExisteExcepcion e) {
			throw new NoExisteExcepcion(e.getMessage());
		} catch (Exception e) {
            log.error("[Publicador] Error: SalidaCrearSalida");
		}
	}
	
	@WebMethod
	public List<dataSalida> SalidaListarSalidas() {
		if (debug) log.info("[Publicador] Recibiendo SalidaListarSalidas");
		List<dataSalida> salidas = ISC.getAllSalidas();
		
		return salidas;
	};

	@WebMethod
	public List<dataSalida> SalidaGetAllSalidas() {
		List<dataSalida> salidas = new ArrayList<dataSalida>();
		if (debug) log.info("[Publicador] Recibiendo SalidaGetAllSalidas");


		try {
			log.info("[Publicador - getAllSalidas]  Antes");
			salidas = ISC.getAllSalidas();
			log.info("[Publicador - getAllSalidas]  Despues");
		} catch (Exception e) {
            log.error("[Publicador] Error: SalidaGetAllSalidas");
		}
		
		if(salidas == null) {
			log.warning("[Publicador] - Salidas Vacio");
		}else {
			log.warning("[Publicador] - Salidas NO Vacio");
			for(dataSalida ref : salidas) {
				log.warning("[Publicador] - Salidas -> " + ref.getNombre());				
			}
		}

		return salidas;
	}
	
	@WebMethod
	public List<dataPaquete> paqueteGetAllPaquetes() {
		List<dataPaquete> paquetes = new ArrayList<dataPaquete>();

		try {
			paquetes = IPC.listarPaquetes();
		} catch (Exception e) {
            log.error("[Publicador] Error: paqueteGetAllPaquetes");
		}

		return paquetes;
	}
	
	@WebMethod
	public dataSalida salidaMostrarDatosSalida(@WebParam(name = "nombre") String nombre){
		
		return ISC.mostrarDatosSalida(nombre);
	}
	
	@WebMethod
	public void inscripcionCrearInscripcion(@WebParam(name = "fecha") @XmlJavaTypeAdapter(LocalDateAdapter.class) LocalDate fecha,
											@WebParam(name = "cantInsc") int cantInsc,
											@WebParam(name = "nickName") String nickName,
											@WebParam(name = "nSalida") String nSalida,
											@WebParam(name = "actividadNombre") String actividadNombre) throws ParametrosInvalidosExcepcion, YaExisteExcepcion, NoExisteExcepcion{
		
		try {
			IIC.crearInscripcion(fecha, cantInsc, actividadNombre, nickName, actividadNombre);
		} catch (ParametrosInvalidosExcepcion e) {
			throw new ParametrosInvalidosExcepcion();
		} catch (UsuarioYaExisteExcepcion e) {
			throw new YaExisteExcepcion(e.getMessage());
		} catch (UsuarioNoExisteExcepcion e) {
			throw new NoExisteExcepcion(e.getMessage());
		}
		
	}
}

