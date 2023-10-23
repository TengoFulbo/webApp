package turismouy.svcentral;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import turismouy.svcentral.excepciones.NoExisteExcepcion;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;
import turismouy.svcentral.interfaces.IActividadController;
import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.interfaces.ICompraController;
import turismouy.svcentral.interfaces.IDepartamentoController;
import turismouy.svcentral.interfaces.IPaqueteController;
import turismouy.svcentral.interfaces.ISalidaController;
import turismouy.svcentral.interfaces.IUsuarioController;
import turismouy.svcentral.manejadores.DepartamentoManejador;
import turismouy.svcentral.utilidades.estadoActividad;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.datatypes.dataCategoria;
import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.datatypes.dataPaquete;
import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.entidades.categoria;
import turismouy.svcentral.entidades.departamento;
// import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.utilidades.log;

public class Main {
    public static void main(String[] args) {
        Fabrica fabrica = Fabrica.getInstance();
        // ICategoriaController ICC = fabrica.getICategoriaController();
        // IActividadController IAC = fabrica.getIActividadController();
        IUsuarioController IUC = fabrica.getIUsuarioController();
        IActividadController IAC = fabrica.getIActividadController();
        ICompraController ICoC = fabrica.getICompraController();
        IPaqueteController IPC = fabrica.getIPaqueteController();
        ICategoriaController ICC = fabrica.getICategoriaController();
        IDepartamentoController IDC = fabrica.getIDepartamentoController();

        // List<dataDepartamento> departamentos = IDC.listarDepartamentos();

        // for (dataDepartamento dep : departamentos) {
        //     log.info(dep.getNombre());
        //     for (dataActividad act : dep.getActividades()) {
        //         log.info("  - " + act.getNombre());
        //         for (String cat : act.getDtCategorias()) {
        //             log.info("  [" + cat + "]");
        //         }
        //     }
        // }

        // List<String> categorias = new ArrayList<String>();
        // categorias.add("paseo");
        // categorias.add("fulbo");

        // try {
        //     IAC.crearActividad("San José", "homeSolutions", "Prueba1", "Prueba1", 2, 200, "San Jose de mayo", LocalDate.now(), categorias);
        // } catch (Exception e) {
        //     log.error(e.toString());
        // }

        // DepartamentoManejador DM = DepartamentoManejador.getinstance();

        // List<departamento> departamentos = DM.getAllDepartamento();

        // for (departamento dep : departamentos) {
        //     log.warning("Departamento: " + dep.getNombre());
        //     for (actividad actividad : dep.getActividades()) {
        //         log.info("    - Actividad: " + actividad.getNombre());
        //         for (categoria categoria : actividad.getCategorias()) {
        //             log.info("        _: " + categoria.getNombre());
        //         }
        //     }
        // }

        // List<dataDepartamento> departamentos = IDC.listarDepartamentos();

        // if (departamentos != null) {
        //     for (dataDepartamento departamento : departamentos) {
        //         log.info(departamento.getNombre());
        //     }
        // } else {
        //     log.error("No hay departamentosclear");
        // }

        // List<dataActividad> actividades = IAC.getAllActividades();

        // for (dataActividad actividad : actividades) {
        //     log.info(actividad.getNombre());
        // }

        // List<dataCategoria> categorias = ICC.listarCategorias();

        // for (dataCategoria categoria : categorias) {
        //     log.info(categoria.getNombre());
        // }


    //    try {
    //        if (IUC.login("eze", "eze")) { log.warning("True to login");
    //        } else { log.warning("False to login"); };

    //        if (IUC.login("eze", "hola123")) { log.warning("True to login");
    //        } else { log.warning("False to login"); };

    //        // if (IUC.login("eze", "eze")) { log.warning("True to login");
    //        // } else { log.warning("False to login"); };
    //    } catch (Exception e) {
    //        log.error(e.toString());
    //    }

        // cargarProveedores();
        // log.warning("########################################################");
        // cargarDepartamentos();
        // log.warning("########################################################");
        // cargarCategorias();
        // log.warning("########################################################");
        // cargarPaquetes();
        // log.warning("########################################################");
        // cargarActividades();
        log.warning("########################################################");
        cargarActividadesPaquetes();
        // try {
		// 	IAC.modificarEstadoActividad("Tour de Vinos en Bodegas", estadoActividad.CONFIRMADA);
		// } catch (NoExisteExcepcion | ParametrosInvalidosExcepcion | YaExisteExcepcion e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }
        
        // try {
		// 	IAC.crearActividad("Canelones", "techGadgets", "a", "Experimenta la vida gaucha a caballo en la campiña.", 240, 80, "C", LocalDate.now());
		// } catch (ParametrosInvalidosExcepcion | UsuarioYaExisteExcepcion | UsuarioNoExisteExcepcion e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }
      
        // try {
		// 	ICoC.crearCompra(LocalDate.now(), 2, 3, LocalDate.now(), "Paquete Aventura", "eze");
		// } catch (ParametrosInvalidosExcepcion | UsuarioYaExisteExcepcion | UsuarioNoExisteExcepcion e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }
        
    //     try {
	// 		IPC.agregarActividadPaquete("Paquete Aventura", "Tour de Arte Urbano");
	// 	} catch (NoExisteExcepcion | YaExisteExcepcion e) {
	// 		// TODO Auto-generated catch block
	// 		e.printStackTrace();
	// 	}
        
    //    try {
	// 	List<dataActividad> LAct = IAC.getActividadesDepartamentoNoPaquete("Paquete Histórico", "Canelones");
		
	//        for(dataActividad DtAct : LAct) {
	//     	   System.out.println(DtAct.getNombre());
	//        }
	// } catch (UsuarioNoExisteExcepcion e) {
	// 	// TODO Auto-generated catch block
	// 	e.printStackTrace();
	// }
        
       
        
        

        // try {
        //     IAC.modificarEstadoActividad("Tour de Vinos en Bodegas", estadoActividad.RECHAZADA);
        //     IAC.modificarEstadoActividad("Recorrido Cultural por Museos", estadoActividad.RECHAZADA);
        // } catch (Exception e) {
        //     log.error(e.getMessage());
        // }
        // log.warning("########################################################");
        // cargarActividades();
        // cargarActividadesPaquetes();

        // try {
        //     ICC.crearCategoria("Aventura");            
        // } catch (Exception e) {
        //     log.error(e.toString());
        // }
        // log.info("Hola");
    }

    public static void cargarProveedores() {
		Fabrica fabrica = Fabrica.getInstance();
        IUsuarioController IUC = fabrica.getIUsuarioController();

        try { IUC.registrarTurista("eze", "Ezequiel", "Blandin", "exzeblan@gmail.com", "Uruguay",  LocalDate.of(2000, 05, 20), "hola123");
            } catch (Exception e) { log.error("Error: " + e.toString()); }		
        try { IUC.registrarProveedor("homeSolutions", "Mariana", "Perez", "mariana@homesolutions.com", "Soluciones para el hogar y decoración", "www.homesolutions.com", LocalDate.of(2004, 12, 3));
            } catch (Exception e) { log.error("Error: " + e.toString()); }			
        try { IUC.registrarProveedor("techGadgets", "Manuel", "Ríos", "manuel@techgadgets.com", "Dispositivos tecnológicos y accesorios", "www.techgadgets.com", LocalDate.of(1996, 5, 15));
            } catch (Exception e) { log.error("Error: " + e.toString()); }			
        try { IUC.registrarProveedor("urbanExplore", "Renata", "Martínez", "renata@urbanexplore.com", "Explora la ciudad con nuestras rutas turísticas", "www.urbanexplore.com", LocalDate.of(1992, 2, 24));
            } catch (Exception e) { log.error("Error: " + e.toString()); }			
        try { IUC.registrarProveedor("designCraft", "Lucas", "Torres", "lucas@designcraft.com", "Artesanía y diseño en productos únicos", "www.designcraft.com", LocalDate.of(1989, 8, 2));
            } catch (Exception e) { log.error("Error: " + e.toString()); }			
        try { IUC.registrarProveedor("musicMania", "María", "Ruiz", "maria@musicmania.com", "Tienda de instrumentos musicales y accesorios", "www.musicmania.com", LocalDate.of(1997, 6, 6));
            } catch (Exception e) { log.error("Error: " + e.toString()); }			
        try { IUC.registrarProveedor("petCompanion", "Camila", "García", "camila@petcompanion.com", "Cuidado y servicios para mascotas", "www.petcompanion.com", LocalDate.of(2003, 10, 20));
            } catch (Exception e) { log.error("Error: " + e.toString()); }			
        try { IUC.registrarProveedor("ecoLiving", "Mateo", "Morales", "mateo@ecoliving.com", "Productos ecológicos y estilo de vida sostenible", "www.ecoliving.com", LocalDate.of(1998, 4, 14));
            } catch (Exception e) { log.error("Error: " + e.toString()); }			
        try { IUC.registrarProveedor("fashionSpot", "Valentina", "Vega", "valentina@fashionspot.com", "Plataforma para compartir tendencias de moda", "www.fashionspot.com", LocalDate.of(1995, 9, 28));
            } catch (Exception e) { log.error("Error: " + e.toString()); }			
        try { IUC.registrarProveedor("deliciousBakery", "Javier", "López", "javier@deliciousbakery.com", "Panadería y repostería con productos artesanales", "www.deliciousbakery.com", LocalDate.of(2000, 1, 3));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
    }

    public static void cargarDepartamentos() {
		Fabrica fabrica = Fabrica.getInstance();
		IDepartamentoController IDC = fabrica.getIDepartamentoController();
        try { IDC.crearDepartamento("Artigas", "Departamento del norte de Uruguay", "www.artigas.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Canelones", "Departamento ubicado en el sur de Uruguay", "www.canelones.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Cerro Largo", "Departamento en el este de Uruguay", "www.cerrolargo.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Colonia", "Departamento costero de Uruguay", "www.colonia.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Durazno", "Departamento en el centro de Uruguay", "www.durazno.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Flores", "Departamento en el suroeste de Uruguay", "www.flores.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Florida", "Departamento en el sur de Uruguay", "www.florida.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Lavalleja", "Departamento en el sureste de Uruguay", "www.lavalleja.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Maldonado", "Departamento en la costa este de Uruguay", "www.maldonado.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Montevideo", "La capital de Uruguay", "www.montevideo.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Paysandú", "Departamento en el noroeste de Uruguay", "www.paysandu.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Río Negro", "Departamento en el oeste de Uruguay", "www.rionegro.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Rivera", "Departamento en el norte de Uruguay", "www.rivera.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Rocha", "Departamento en el este de Uruguay", "www.rocha.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Salto", "Departamento en el noroeste de Uruguay", "www.salto.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("San José", "Departamento en el suroeste de Uruguay", "www.sanjose.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Soriano", "Departamento en el suroeste de Uruguay", "www.soriano.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Tacuarembó", "Departamento en el norte de Uruguay", "www.tacuarembo.com.uy");
		    } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IDC.crearDepartamento("Treinta y Tres", "Departamento en el este de Uruguay", "www.treintaytres.com.uy");
            } catch (Exception e) { log.error("Error: " + e.toString()); }
    }

    public static void cargarCategorias() {
        Fabrica fabrica = Fabrica.getInstance();
        ICategoriaController ICC = fabrica.getICategoriaController();

        try { ICC.crearCategoria("fulbo");
            } catch (Exception e) { log.error("Error: " + e.toString()); }

        try { ICC.crearCategoria("paseo");
            } catch (Exception e) { log.error("Error: " + e.toString()); }

        try { ICC.crearCategoria("playa");
            } catch (Exception e) { log.error("Error: " + e.toString()); }

        try { ICC.crearCategoria("recreativo");
            } catch (Exception e) { log.error("Error: " + e.toString()); }

        try { ICC.crearCategoria("para niños");
            } catch (Exception e) { log.error("Error: " + e.toString()); }

        try { ICC.crearCategoria("aventura");
            } catch (Exception e) { log.error("Error: " + e.toString()); }
    }

    public static void cargarActividades() {
		Fabrica fabrica = Fabrica.getInstance();
		IActividadController IAC = fabrica.getIActividadController();

        List<String> categorias1 = new ArrayList<String>();
        categorias1.add("fulbo");
        categorias1.add("para niños");

        try { IAC.crearActividad("Canelones", "homeSolutions", "Tour de Vinos en Bodegas", "Recorrido por las mejores bodegas de la región.", 120, 50, "Canelones", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }            
        try { IAC.crearActividad("Montevideo", "homeSolutions", "Recorrido Cultural por Museos", "Explora la historia y el arte de la ciudad.", 90, 30, "Montevideo", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IAC.crearActividad("Maldonado", "homeSolutions", "Playa y Surf en Punta del Este", "Día de diversión bajo el sol y clases de surf.", 180, 70, "Punta del Este", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IAC.crearActividad("Rocha", "techGadgets", "Senderismo en el Parque Nacional", "Explora la belleza natural del parque.", 150, 60, "La Paloma", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IAC.crearActividad("Colonia", "techGadgets", "Paseo en Kayak por el Río", "Navega por el río y disfruta de la naturaleza.", 120, 55, "Colonia del Sacramento", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IAC.crearActividad("Cerro Largo", "techGadgets", "Turismo Rural en Estancia", "Vive la experiencia gaucha en una estancia local.", 240, 80, "Melo", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IAC.crearActividad("Durazno", "urbanExplore", "Observación Nocturna de Estrellas", "Admira el cielo estrellado en la noche.", 90, 40, "Durazno", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IAC.crearActividad("Lavalleja", "urbanExplore", "Ruta de Ciclismo de Montaña", "Recorrido en bicicleta por las colinas.", 180, 75, "Minas", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IAC.crearActividad("San José", "urbanExplore", "Avistamiento de Aves en el Humedal", "Observa la vida silvestre en el humedal.", 120, 50, "San José", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IAC.crearActividad("Rivera", "urbanExplore", "Pesca Deportiva en el Río", "Día de pesca en el río con guías locales.", 240, 90, "Rivera", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IAC.crearActividad("Artigas", "urbanExplore", "Tour de Arte Urbano", "Explora las obras de arte en las calles de la ciudad.", 90, 35, "Artigas", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IAC.crearActividad("Treinta y Tres", "urbanExplore", "Caminata por la Quebrada", "Recorrido escénico por la quebrada local.", 120, 45, "Treinta y Tres", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IAC.crearActividad("Río Negro", "urbanExplore", "Visita a la Fortaleza de Santa Teresa", "Explora la histórica fortaleza junto al mar.", 150, 60, "Fray Bentos", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IAC.crearActividad("Salto", "urbanExplore", "Relax en las Termas Naturales", "Disfruta de las aguas termales y sus beneficios.", 180, 70, "Salto", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IAC.crearActividad("Tacuarembó", "urbanExplore", "Ruta de Gauchos a Caballo", "Experimenta la vida gaucha a caballo en la campiña.", 240, 80, "Tacuarembó", LocalDate.now(), categorias1);
            } catch (Exception e) { log.error("Error: " + e.toString()); }
    }

    public static void cargarPaquetes() {
		Fabrica fabrica = Fabrica.getInstance();
        IPaqueteController IPC = fabrica.getIPaqueteController();
        try { IPC.crearPaquete("Paquete Aventura", "Disfruta de emocionantes aventuras al aire libre", 30, 10, LocalDate.now());     // actividad1, actividad2, actividad3
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IPC.crearPaquete("Paquete Cultural", "Explora la cultura y la historia de Uruguay", 45, 15, LocalDate.now());          // actividad4, actividad5, actividad6
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IPC.crearPaquete("Paquete Relax", "Relájate y rejuvenece en entornos naturales", 30, 10, LocalDate.now());             // actividad7, actividad8, actividad9
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IPC.crearPaquete("Paquete Artes y Naturaleza", "Combina arte urbano y belleza natural", 60, 20, LocalDate.now());      // actividad10, actividad11, actividad12
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IPC.crearPaquete("Paquete Histórico", "Explora la historia y el patrimonio de Uruguay", 45, 15, LocalDate.now());      // actividad13, actividad14, actividad15
            } catch (Exception e) { log.error("Error: " + e.toString()); }
    }

    public static void cargarActividadesPaquetes() {
        Fabrica fabrica = Fabrica.getInstance();
        IPaqueteController IPC = fabrica.getIPaqueteController();

        // Tour de Vinos en Bodegas
        // Recorrido Cultural por Museos
        // Playa y Surf en Punta del Este
        try { IPC.agregarActividadPaquete("Paquete Aventura", "Tour de Vinos en Bodegas");
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IPC.agregarActividadPaquete("Paquete Aventura", "Recorrido Cultural por Museos");
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IPC.agregarActividadPaquete("Paquete Aventura", "Playa y Surf en Punta del Este");
            } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Senderismo en el Parque Nacional
        // Paseo en Kayak por el Río
        // Turismo Rural en Estancia
        try { IPC.agregarActividadPaquete("Paquete Cultural", "Senderismo en el Parque Nacional");
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IPC.agregarActividadPaquete("Paquete Cultural", "Paseo en Kayak por el Río");
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IPC.agregarActividadPaquete("Paquete Cultural", "Turismo Rural en Estancia");
            } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Observación Nocturna de Estrellas
        // Ruta de Ciclismo de Montaña
        // Avistamiento de Aves en el Humedal
        try { IPC.agregarActividadPaquete("Paquete Relax", "Observación Nocturna de Estrellas");
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IPC.agregarActividadPaquete("Paquete Relax", "Ruta de Ciclismo de Montaña");
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IPC.agregarActividadPaquete("Paquete Relax", "Avistamiento de Aves en el Humedal");
            } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Pesca Deportiva en el Río
        // Tour de Arte Urbano
        // Caminata por la Quebrada
        try { IPC.agregarActividadPaquete("Paquete Artes y Naturaleza", "Pesca Deportiva en el Río");
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IPC.agregarActividadPaquete("Paquete Artes y Naturaleza", "Tour de Arte Urbano");
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IPC.agregarActividadPaquete("Paquete Artes y Naturaleza", "Caminata por la Quebrada");
            } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Visita a la Fortaleza de Santa Teresa
        // Relax en las Termas Naturales
        // Ruta de Gauchos a Caballo
        try { IPC.agregarActividadPaquete("Paquete Histórico", "Visita a la Fortaleza de Santa Teresa");
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IPC.agregarActividadPaquete("Paquete Histórico", "Relax en las Termas Naturales");
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IPC.agregarActividadPaquete("Paquete Histórico", "Ruta de Gauchos a Caballo");
            } catch (Exception e) { log.error("Error: " + e.toString()); }
    }
}