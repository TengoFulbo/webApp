package turismouy.svcentral.middlewares;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.datatypes.dataCategoria;
import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.datatypes.dataPaquete;
import turismouy.svcentral.datatypes.dataSalida;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.excepciones.NoExisteExcepcion;
import turismouy.svcentral.interfaces.IActividadController;
import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.interfaces.IDepartamentoController;
import turismouy.svcentral.interfaces.IPaqueteController;
import turismouy.svcentral.interfaces.ISalidaController;
import turismouy.svcentral.interfaces.IUsuarioController;
import turismouy.svcentral.utilidades.log;

@WebService
public class publicador {
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

    private Endpoint endpoint = null;

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish(ipServidor + puerto + uri, this);
        log.info("Publicando en la direccion: " + ipServidor + puerto + uri);
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
	public dataUsuario UsuarioMostrarInfo(@WebParam(name = "nickname") String nickname) {
		dataUsuario usuario = null;
		if (debug) log.info("[Publicador] Recibiendo UsuarioMostrarInfo");

		try {
			usuario = IUC.mostrarInfo(nickname);
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
		@WebParam(name = "nacimiento")	LocalDate nacimiento
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
		@WebParam(name = "nacimiento")	LocalDate nacimiento,
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
			@WebParam(name = "nacimiento")		LocalDate nacimiento,
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
		@WebParam(name = "nacimiento")	LocalDate nacimiento, 
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
			@WebParam(name = "fechaAlta")		LocalDate fechaAlta,
			@WebParam(name = "fechaSalida")		LocalDate fechaSalida,
			@WebParam(name = "lugarSalida")		String lugarSalida,
			@WebParam(name = "nombreActividad")	String nombreActividad
	) {
		if (debug) log.info("[Publicador] Recibiendo SalidaCrearSalida");

		try {
			ISC.crearSalida(nombre, capacidad, fechaAlta, fechaSalida, lugarSalida, nombreActividad);
		} catch (Exception e) {
            log.error("[Publicador] Error: SalidaCrearSalida");
		}
	}

	@WebMethod
	public List<dataSalida> SalidaGetAllSalidas() {
		List<dataSalida> salidas = new ArrayList<dataSalida>();
		if (debug) log.info("[Publicador] Recibiendo SalidaGetAllSalidas");


		try {
			salidas = ISC.getAllSalidas();
		} catch (Exception e) {
            log.error("[Publicador] Error: SalidaGetAllSalidas");
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
	};
}
