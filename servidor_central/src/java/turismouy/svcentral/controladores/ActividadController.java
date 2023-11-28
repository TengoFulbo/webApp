package turismouy.svcentral.controladores;

import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.entidades.categoria;
import turismouy.svcentral.entidades.departamento;
import turismouy.svcentral.entidades.proveedor;
import turismouy.svcentral.entidades.salida;
// import turismouy.svcentral.entidades.salida;
import turismouy.svcentral.entidades.usuario;
import turismouy.svcentral.entidades.visita;
import turismouy.svcentral.entidades.paquete;
import turismouy.svcentral.excepciones.NoExisteExcepcion;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;
import turismouy.svcentral.interfaces.IActividadController;
import turismouy.svcentral.manejadores.ActividadManejador;
import turismouy.svcentral.manejadores.CategoriaManejador;
import turismouy.svcentral.manejadores.DepartamentoManejador;
import turismouy.svcentral.manejadores.PaqueteManejador;
import turismouy.svcentral.manejadores.UsuarioManejador;
import turismouy.svcentral.manejadores.VisitaManejador;
import turismouy.svcentral.utilidades.estadoActividad;
import turismouy.svcentral.utilidades.log;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class ActividadController implements IActividadController {

    @Override
	public void crearActividad(
        @WebParam(name = "nombreDeptos")    String nombreDepto,
        @WebParam(name = "nombreProv")      String nombreProv,
        @WebParam(name = "nombre")          String nombre,
        @WebParam(name = "desc")            String desc,
        @WebParam(name = "duracion")        int duracion,
        @WebParam(name = "costoUni")        int costoUni,
        @WebParam(name = "ciudad")          String ciudad,
        @WebParam(name = "fechaCrea")       LocalDate fechaCrea,
        @WebParam(name = "categorias")      List<String> sCategorias
        ) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion, UsuarioNoExisteExcepcion{
		
        // Validaciones sobre parametros.
        if (!validarTexto(nombreDepto, 1) || !validarTexto(nombre, 1) || !validarTexto(desc, 1) || !validarTexto(ciudad, 1)) {
            // log.error("Parametros invalidos.");
            // return;
            throw new ParametrosInvalidosExcepcion();
        }
        
        DepartamentoManejador dm = DepartamentoManejador.getinstance();
        departamento depto = dm.getDepartamento(nombreDepto);
        
        if(depto == null) {
        	// log.error("El departamento " + nombreDepto + "no existe");
        	throw new UsuarioNoExisteExcepcion("El departamento " + nombreDepto + " no existe");
        }
        
		ActividadManejador am = ActividadManejador.getinstance();

        actividad actividad = am.getActividad(nombre);

        if(actividad != null) {
            if (actividad.getEstado() == estadoActividad.AGREGADA || actividad.getEstado() == estadoActividad.CONFIRMADA) {
                log.error("1 La actividad '" + actividad.getNombre() + "' ya existe.");
                throw new UsuarioYaExisteExcepcion("La actividad " + nombre + " ya existe");
            }
        	log.error("2 La actividad '" + nombre + "' ya existe pero se crea de igual manera, ya que está rechazada.");
        }
        
        // if(am.getActividad(nombre) != null){
        // 	// log.error("La actividad '" + nombre + "' ya existe.");
        // 	throw new UsuarioYaExisteExcepcion("La actividad " + nombre + " ya existe");
        // }
        
        UsuarioManejador um = UsuarioManejador.getinstance();
        usuario user = um.getUsuario(nombreProv);
        proveedor prov = (proveedor) user;
        
        if(prov == null) {
        	log.error("El proveedor " + nombreProv + " no existe");
        	throw new UsuarioNoExisteExcepcion("El proveedor " + nombreProv + " no existe");
        }
        
        List<categoria> categorias = new ArrayList<categoria>();

        CategoriaManejador cm = CategoriaManejador.getInstance();

        for (String sCategoria : sCategorias) {
            categoria categoria = cm.getCategoria(sCategoria);
            if (categoria != null) {
                categorias.add(categoria);
                log.warning("Se agrega la categoría: " + categoria.getNombre());
            } else {
                log.error("[ActividadController] [altaActividad] Error: No se encuentra la categoria '" + sCategoria + "' en el sistema.");
                throw new UsuarioNoExisteExcepcion("La categoria '" + sCategoria + "' no existe en el sistema");
            }
        }

		actividad act = new actividad(nombre, desc, duracion, costoUni, ciudad, fechaCrea, categorias);

        visita visita = new visita();

        act.setDepartamento(depto);
        act.setProveedor(prov);

        prov.addActividad(act);

        VisitaManejador vm = VisitaManejador.getinstance();
        visita.setActividad(actividad);
        vm.addVisita(visita);
        act.setVisita(visita);

        am.addActividad(act);
        um.updateUsuario(prov);

        depto.agregarActividad(act);
        dm.updateDepartamento(depto);

        // Se trae el objeto luego de guardarlo.
        act = am.getActividadWithoutEstado(nombre);

        // act.setVisita(visita);
        // am.updateActividad(actividad);

        for (categoria categoria : categorias) {
            categoria.addActividad(act);
            cm.updateCategoria(categoria);
        }
	}
    
    public void crearActividadUrl(
        @WebParam(name = "nombreDeptos")    String nombreDepto,
        @WebParam(name = "nombreProv")      String nombreProv,
        @WebParam(name = "nombre")          String nombre,
        @WebParam(name = "desc")            String desc,
        @WebParam(name = "duracion")        int duracion,
        @WebParam(name = "costoUni")        int costoUni,
        @WebParam(name = "ciudad")          String ciudad,
        @WebParam(name = "urlVideo")        String video,
        @WebParam(name = "fechaCrea")       LocalDate fechaCrea,
        @WebParam(name = "categorias")      List<String> sCategorias
        ) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion, UsuarioNoExisteExcepcion{
		
        // Validaciones sobre parametros.
        if (!validarTexto(nombreDepto, 1) || !validarTexto(nombre, 1) || !validarTexto(desc, 1) || !validarTexto(ciudad, 1)) {
            // log.error("Parametros invalidos.");
            // return;
            throw new ParametrosInvalidosExcepcion();
        }
        
        DepartamentoManejador dm = DepartamentoManejador.getinstance();
        departamento depto = dm.getDepartamento(nombreDepto);
        
        if(depto == null) {
        	// log.error("El departamento " + nombreDepto + "no existe");
        	throw new UsuarioNoExisteExcepcion("El departamento " + nombreDepto + " no existe");
        }
        
		ActividadManejador am = ActividadManejador.getinstance();

        actividad actividad = am.getActividad(nombre);

        if(actividad != null) {
            if (actividad.getEstado() == estadoActividad.AGREGADA || actividad.getEstado() == estadoActividad.CONFIRMADA) {
                log.error("1 La actividad '" + actividad.getNombre() + "' ya existe.");
                throw new UsuarioYaExisteExcepcion("La actividad " + nombre + " ya existe");
            }
        	log.error("2 La actividad '" + nombre + "' ya existe pero se crea de igual manera, ya que está rechazada.");
        }
        
        // if(am.getActividad(nombre) != null){
        // 	// log.error("La actividad '" + nombre + "' ya existe.");
        // 	throw new UsuarioYaExisteExcepcion("La actividad " + nombre + " ya existe");
        // }
        
        UsuarioManejador um = UsuarioManejador.getinstance();
        usuario user = um.getUsuario(nombreProv);
        proveedor prov = (proveedor) user;
        
        if(prov == null) {
        	log.error("El proveedor " + nombreProv + " no existe");
        	throw new UsuarioNoExisteExcepcion("El proveedor " + nombreProv + " no existe");
        }
        
        List<categoria> categorias = new ArrayList<categoria>();

        CategoriaManejador cm = CategoriaManejador.getInstance();

        for (String sCategoria : sCategorias) {
            categoria categoria = cm.getCategoria(sCategoria);
            if (categoria != null) {
                categorias.add(categoria);
                log.warning("Se agrega la categoría: " + categoria.getNombre());
            } else {
                log.error("[ActividadController] [altaActividad] Error: No se encuentra la categoria '" + sCategoria + "' en el sistema.");
                throw new UsuarioNoExisteExcepcion("La categoria '" + sCategoria + "' no existe en el sistema");
            }
        }

        if(video != null && video != "") { 
            String patron = "^(https?\\:\\/\\/)?(www\\.)?(youtube\\.com\\/|youtu\\.be\\/).+$";
            Pattern pattern = Pattern.compile(patron);
            Matcher matcher = pattern.matcher(video);

            if(!matcher.matches()){
            	log.error("La URL del video: " + video + " No coincide con el patron valido");
                throw new ParametrosInvalidosExcepcion();
            }
        }else{
            log.error("La URL del video no puede ser vacia");
            throw new ParametrosInvalidosExcepcion(); 
        }
        
		actividad act = new actividad(nombre, desc, duracion, costoUni, ciudad, video, fechaCrea, categorias);

        visita visita = new visita();

        act.setDepartamento(depto);
        act.setProveedor(prov);

        prov.addActividad(act);

        VisitaManejador vm = VisitaManejador.getinstance();
        visita.setActividad(actividad);
        vm.addVisita(visita);
        act.setVisita(visita);

        am.addActividad(act);
        um.updateUsuario(prov);

        depto.agregarActividad(act);
        dm.updateDepartamento(depto);

        // Se trae el objeto luego de guardarlo.
        act = am.getActividad(nombre);

        for (categoria categoria : categorias) {
            categoria.addActividad(act);
            cm.updateCategoria(categoria);
        }
	}
	
    @Override
	public dataActividad mostrarDatos(@WebParam(name = "nombreAct") String nombreAct) throws UsuarioNoExisteExcepcion{
		ActividadManejador am = ActividadManejador.getinstance();
		actividad act = am.getActividad(nombreAct);		
		if (act == null) {
        	log.error("La actividad '" + nombreAct + "' ya existe.");
        	throw new UsuarioNoExisteExcepcion("La actividad " + nombreAct + " no existe");
		}
		dataActividad DTAct = act.toDataType();		
		return DTAct;		
	};
	
    @Override
	public void modificarActividad(
            @WebParam(name = "nombreAct") String nombreAct,
            @WebParam(name = "desc") String desc,
            @WebParam(name = "duracion") int duracion,
            @WebParam(name = "costoUni") int costoUni,
            @WebParam(name = "ciudad") String ciudad,
            @WebParam(name = "fechaCrea") LocalDate fechaCrea
        ) throws ParametrosInvalidosExcepcion, UsuarioNoExisteExcepcion, UsuarioYaExisteExcepcion{
        if (!validarTexto(nombreAct, 1) || !validarTexto(desc, 1) || !validarTexto(ciudad, 1)) {
            log.error("Parametros invalidos.");
            throw new ParametrosInvalidosExcepcion();
        }
	      
        ActividadManejador am = ActividadManejador.getinstance();
        actividad act = am.getActividad(nombreAct);
        
        if(act == null) {
        	log.error("La actividad " + nombreAct + "no existe");
        	throw new UsuarioNoExisteExcepcion("La actividad " + nombreAct + " no existe");
        }
        
        if(act.getEstado().equals(estadoActividad.FINALIZADA)) {
        	log.error("No se puede modificar una actividad Finalizada");
            throw new ParametrosInvalidosExcepcion();
        }
        
        DepartamentoManejador dm = DepartamentoManejador.getinstance();
        List <departamento> LDepto = dm.getAllDepartamentos();
        //Recorro todos los departamentos para poder buscar la actividad que
        //me pasan por parametro
        for(departamento depto: LDepto){
        	List<actividad> LAct = depto.getActividades();
        	for(actividad act2 : LAct) {
        		if(act2.getNombre().equals(nombreAct)) {
        			act2.setDescripcion(desc);
        			act2.setDuracion(duracion);
        			act2.setCosteUni(costoUni);
        			act2.setCiudad(ciudad);
        			act2.setFechaCrea(fechaCrea);
        	        am.updateActividad(act2);
        		}
        	}
        }    
	}  

    public List<dataActividad> getAllActividades() {      
        boolean debug = false; String functionName = "[getAllActividades] ";
        ActividadManejador AM = ActividadManejador.getinstance();
        List<actividad> actividades = AM.getAllActividades();
        List<dataActividad> dtActividades = new ArrayList<dataActividad>();

        if (debug) log.info(functionName + "Cantidad de actividades: " + actividades.size());
        for (actividad actividad : actividades) {
            if (debug) log.info(functionName + (actividades.indexOf(actividad) + 1) + " Actividad: " + actividad.getNombre() + " Estado: " + actividad.getEstado());
            if (actividad.getEstado().equals(estadoActividad.CONFIRMADA)) {
                dtActividades.add(actividad.toDataType());
            }
        }

        return dtActividades;
    }
    
    public List<dataActividad> getAllActividadesTodas() {      
        List<dataActividad> LDtAct = new ArrayList<>();
        DepartamentoManejador dm = DepartamentoManejador.getinstance();
     List<departamento> deptos = dm.getAllDepartamentos();
     
         for (departamento depto : deptos) {
             if (depto != null && depto.getNombre() != null) {
                 List<actividad> LAct = depto.getActividades();
                 if (LAct != null) {
                     for (actividad act : LAct) {
                     	LDtAct.add(act.toDataType());
                     }
                 }
             }
         }
     if (LDtAct.isEmpty())
         return null; 
     return LDtAct;
    }

    @Override
    public List<dataActividad> getAllActividadesDepartamento(@WebParam(name = "nombreDep") String nombreDep) {
        List<dataActividad> LDtAct = new ArrayList<>();
           DepartamentoManejador dm = DepartamentoManejador.getinstance();
        List<departamento> deptos = dm.getAllDepartamentos();
        
        if (deptos != null) {
            for (departamento depto : deptos) {
                if (depto != null && depto.getNombre() != null && depto.getNombre().equals(nombreDep)) {
                    List<actividad> LAct = depto.getActividades();
                    if (LAct != null) {
                        for (actividad act : LAct) {
                        	LDtAct.add(act.toDataType());
                        }
                    }
                    break;
                }
            }
        }
        if (LDtAct.isEmpty())
            return null; 
        return LDtAct;
    }
    
    @Override
    public List<dataActividad> getAllActividadesConfirmadasDepartamento(@WebParam(name = "nombreDep") String nombreDep) {
        List<dataActividad> LDtAct = new ArrayList<>();
           DepartamentoManejador dm = DepartamentoManejador.getinstance();
        List<departamento> deptos = dm.getAllDepartamentos();
        
        if (deptos != null) {
            for (departamento depto : deptos) {
                if (depto != null && depto.getNombre() != null && depto.getNombre().equals(nombreDep)) {
                    List<actividad> LAct = depto.getActividades();
                    if (LAct != null) {
                        for (actividad act : LAct) {
                        	if(act.getEstado().equals(estadoActividad.CONFIRMADA)){
                        		LDtAct.add(act.toDataType());
                        	}
                        }
                    }
                    break;
                }
            }
        }
        if (LDtAct.isEmpty())
            return null; 
        return LDtAct;
    }
    
    @Override
    public List<dataActividad> getAllActividadesAgregadas() {
        List<dataActividad> LDtAct = new ArrayList<>();
           DepartamentoManejador dm = DepartamentoManejador.getinstance();
        List<departamento> deptos = dm.getAllDepartamentos();
        
            for (departamento depto : deptos) {
                if (depto != null && depto.getNombre() != null) {
                    List<actividad> LAct = depto.getActividades();
                    if (LAct != null) {
                        for (actividad act : LAct) {
                        	if(act.getEstado().equals(estadoActividad.AGREGADA)){
                        		LDtAct.add(act.toDataType());
                        	}
                        }
                    }
                }
            }
        if (LDtAct.isEmpty())
            return null; 
        return LDtAct;
    }
    
    @Override
    public List<dataActividad> getActividadesDepartamentoNoPaquete(
            @WebParam(name = "nombrePaquete")       String nombrePaquete,
            @WebParam(name = "nombreDepartamento")  String nombreDepartamento
        ) throws UsuarioNoExisteExcepcion{
    	
        PaqueteManejador pm = PaqueteManejador.getinstance();
        DepartamentoManejador dm = DepartamentoManejador.getinstance();
        paquete paquete = pm.getPaquete(nombrePaquete);
        departamento departamento = dm.getDepartamento(nombreDepartamento);
        
        if (paquete == null) {
            log.error("El paquete: " + nombrePaquete + " no existe.");
        	throw new UsuarioNoExisteExcepcion("La actividad " + nombrePaquete + " ya existe");
        }
        
        if (departamento == null) {
            log.error("El departamento " + nombreDepartamento + " no existe.");
        	throw new UsuarioNoExisteExcepcion("La actividad " + nombreDepartamento + " ya existe");
        }
        
        List<dataActividad> LActPaquete = paquete.toDataType().getActividades();
        List<actividad> LAct = departamento.getActividades();
        List<dataActividad> LActDepto = new ArrayList<dataActividad>();
        
        System.out.println("Actividades Confirmadas");
        for (actividad act : LAct) {
        	if(act.getEstado().equals(estadoActividad.CONFIRMADA)){
        		LActDepto.add(act.toDataType());
        		System.out.println(act.getNombre());
        		System.out.println();
        	}
        }
        
    	List<dataActividad> LActNoPaquete = new ArrayList<dataActividad>();
    	boolean esta = false;
    	
    	for(dataActividad DtAct : LActDepto) {
    		esta = false;
    		for(dataActividad DtActDepto : LActPaquete) {
    			if(DtAct.getNombre().equals(DtActDepto.getNombre())) {
    				System.out.println("Entra al if de bool");
    				esta = true;
    				break;
    			}
    		}
    		if(!esta) {
    			LActNoPaquete.add(DtAct);
    		}
    	}
    	return LActNoPaquete;
    	

    }
    
    @Override
     public List <dataActividad> getActividadesPorCategoria(@WebParam(name = "nombreCategoria") String nombreCategoria) throws NoExisteExcepcion {
    	CategoriaManejador cm = CategoriaManejador.getInstance();    	
    	categoria categoria = cm.getCategoria(nombreCategoria);
    	
    	if(categoria == null) {
        	log.error("La categoria '" + nombreCategoria + "' no existe.");
        	throw new NoExisteExcepcion("La categoria " + nombreCategoria + " no existe");
    		
    	}
    
    	List<actividad> LAct = categoria.getActividades();
    	List<dataActividad> LDtAct = new ArrayList<dataActividad>();
    	
    	for(actividad act : LAct) {
    		dataActividad DtAct = act.toDataType();
    		LDtAct.add(DtAct);
    	}
    	
    	return LDtAct;
    }
    
    @Override
    public void modificarEstadoActividad(@WebParam(name = "nombre") String nombre, @WebParam(name = "estado") estadoActividad estado) throws NoExisteExcepcion, ParametrosInvalidosExcepcion, YaExisteExcepcion {
        if (estado != estadoActividad.CONFIRMADA && estado != estadoActividad.RECHAZADA) {
            log.error("Parametros invalidos por primero.");
            throw new ParametrosInvalidosExcepcion();
        }

        ActividadManejador am = ActividadManejador.getinstance();
        actividad actividad = am.getActividad(nombre);

		if (actividad == null) {
        	log.error("La actividad '" + nombre + "' no existe.");
        	throw new NoExisteExcepcion("La actividad " + nombre + " no existe");
		}

        if (actividad.getEstado() != (estadoActividad.AGREGADA)){
            throw new YaExisteExcepcion("La actividad '" + actividad.getNombre() + "' ya tiene un estado de: '" + actividad.getEstado() + "'");
        }

        log.info("Se modifica el estado de la actividad '" + nombre + "' " + actividad.getEstado() + " a " + estado);
        actividad.setEstado(estado);
        
        DepartamentoManejador dm = DepartamentoManejador.getinstance();
        departamento depto = dm.getDepartamento(actividad.getDepartamento().getNombre());
        
        UsuarioManejador um = UsuarioManejador.getinstance();
        usuario usuario = um.getUsuario(actividad.getProveedor().getNickname());
        proveedor prov =(proveedor) usuario;
        
        // PaqueteManejador pm = PaqueteManejador.getinstance();
        
        // CategoriaManejador cm = CategoriaManejador.getInstance();
        
        depto.remplazarActividad(actividad);
        dm.updateDepartamento(depto);
        
        prov.remplazarActividad(actividad);
        um.updateUsuario(usuario);
        /*
        for(categoria categoria : actividad.getCategorias()) {
        	categoria.remplazarActividad(actividad);
        	cm.updateCategoria(categoria);
        }
        
        for(paquete paq : actividad.getPaquetes()) {
        	paq.remplazarActividad(actividad);
        	pm.updatePaquete(paq);
        }
        */
        am.updateActividad(actividad);
        
    } 
    
    public void finalizarActividad(@WebParam(name = "nombreAct") String nombreAct)throws NoExisteExcepcion, ParametrosInvalidosExcepcion {
    	ActividadManejador am = ActividadManejador.getinstance();
    	actividad act = am.getActividad(nombreAct);
    	
    	if(act == null) {
        	log.error("La actividad '" + nombreAct + "' no existe.");
        	throw new NoExisteExcepcion("La actividad " + nombreAct + " no existe");
    	}
    	
    	List<salida> salidas = act.getSalidas();
    	
    	for(salida salida: salidas) {
    		if(salida.getFechaSalida().isAfter(LocalDate.now()));
    		log.info("La Salida "+ salida.getNombre() + " todavia es vigente");
    		throw new ParametrosInvalidosExcepcion();
    	}
    	
    	if(act.getEstado().equals(estadoActividad.RECHAZADA) || act.getEstado().equals(estadoActividad.AGREGADA) || act.getEstado().equals(estadoActividad.FINALIZADA)) {
        	log.error("El estado de la actividad '" + nombreAct + "' no puede modificarse.");
        	throw new NoExisteExcepcion("El estado de la actividad '" + nombreAct + "' no puede modificarse.");
    	}
    	
    	act.setEstado(estadoActividad.FINALIZADA);
    	am.updateActividad(act);
    }
	
    private static boolean validarTexto(String texto, int nivel) {
        switch (nivel) {
            case 1:
                return validarNivel1(texto);
            default:
                return false;
        }
    }
    
    private static boolean validarNivel1(String texto) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        if (texto.trim().length() != texto.length()) {
            return false;
        }
        return texto.matches(".*[a-zA-Z0-9].*");
    }
}
