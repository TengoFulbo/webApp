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
import turismouy.svcentral.interfaces.IInscripcionController;
import turismouy.svcentral.interfaces.IPaqueteController;
import turismouy.svcentral.interfaces.ISalidaController;
import turismouy.svcentral.interfaces.IUsuarioController;
import turismouy.svcentral.manejadores.ActividadManejador;
import turismouy.svcentral.manejadores.CategoriaManejador;
import turismouy.svcentral.manejadores.CompraManejador;
import turismouy.svcentral.manejadores.DepartamentoManejador;
import turismouy.svcentral.manejadores.InscripcionManejador;
import turismouy.svcentral.manejadores.PaqueteManejador;
import turismouy.svcentral.manejadores.UsuarioManejador;
import turismouy.svcentral.utilidades.estadoActividad;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.datatypes.dataCategoria;
import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.datatypes.dataPaquete;
import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.entidades.categoria;
import turismouy.svcentral.entidades.compra;
import turismouy.svcentral.entidades.compra_cupo;
import turismouy.svcentral.entidades.departamento;
import turismouy.svcentral.entidades.inscripcion;
import turismouy.svcentral.entidades.paquete;
import turismouy.svcentral.entidades.proveedor;
import turismouy.svcentral.entidades.salida;
import turismouy.svcentral.entidades.turista;
import turismouy.svcentral.entidades.usuario;
// import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.utilidades.log;

public class Main {
   public static void main(String[] args) {
        Fabrica fabrica = Fabrica.getInstance();
        ICategoriaController ICC = fabrica.getICategoriaController();
        IActividadController IAC = fabrica.getIActividadController();
        IUsuarioController IUC = fabrica.getIUsuarioController();
        ICompraController ICoC = fabrica.getICompraController();
        IPaqueteController IPC = fabrica.getIPaqueteController();
        IDepartamentoController IDC = fabrica.getIDepartamentoController();

        log.warning("###################################3");

        CompraManejador cm = CompraManejador.getinstance();
        
        // try { ICoC.crearCompra(LocalDate.now(), 1, 200, LocalDate.now().plusDays(5), "Paquete Aventura", "lucia_22");
        //     } catch (Exception e) { log.error(e.getMessage()); };

        // List<compra> compras = cm.getAllCompras();
        
        // for (compra compra : compras) {
        //     log.info("Compra: " + compra.getId());
        // }

        // compra compra = cm.getCompra(30);

        // log.info("Compra: " + compra.getId());
        // log.info("Cupos: " + compra.getCupos().size());

        List<compra> compras = cm.getAllCompras();

        for (compra compra : compras) {
            log.info("Compra: " + compra.getId());
            log.info("# Paquete: '" + compra.getPaquete().getNombre() + "'");
            log.info("# Turista: '" + compra.getTurista().getNombre() + "'");
            log.info("# Cantidad turistas: '" + compra.getCantTotal() + "'");
            for (compra_cupo cupo : compra.getCupos()) {
                log.info("  - cupo: " + cupo.getId() + ". Cantidad: " + cupo.getCantidad() + ". Actividad: " + cupo.getActividad().getNombre());
            }
        }

        // UsuarioManejador um = UsuarioManejador.getinstance();

        // List<usuario> usuarios = um.getAllUsuario();

        // for (usuario usuario : usuarios) {
        //     log.info(usuario.getNickname());
            
        //     if (usuario instanceof proveedor) {
        //         proveedor prov = (proveedor) usuario;

        //         // log.info("" + prov.getActividades());
        //         for (actividad act : prov.getActividades()) {
        //             log.info("  - Actividades: " + act.getNombre());
        //         }
        //     } else if (usuario instanceof turista) {
        //         turista turista = (turista) usuario;

        //         log.info("  - Cantidad Compra: " + turista.getCompra().size());

        //         for (compra compra : turista.getCompra()) {
        //             log.info("  - Compra: " + compra.getId());
        //         }

        //         log.info("  - Cantidad inscripciones: " + turista.getInscripciones().size());

        //         for (inscripcion inscripcion : turista.getInscripciones()) {
        //             log.info("  - Inscripcion: " + inscripcion.getId());
        //         }
        //     }
        //     // log.info()
        // }

        // PaqueteManejador pm = PaqueteManejador.getinstance();

        // List<paquete> paquetes = new ArrayList<paquete>();

        // paquetes = pm.getAllPaquetes();

        // for (paquete paquete : paquetes) {
        //     log.info(paquete.getNombre());
        //     log.info("  - Compras: " + paquete.getCompra().size());
        //     log.info("  - Actividades: " + paquete.getActividades().size());
        //     for (actividad actividad : paquete.getActividades()) {
        //         log.info("      # Actividad: " + actividad.getNombre());
        //     }
        // }



        // ActividadManejador am = ActividadManejador.getinstance();

        // List<actividad> actividades = am.getAllActividades();

        // for (actividad actividad : actividades) {
        //     log.info(actividad.getNombre());
        //     log.info("  - " + (actividad.getCategorias().isEmpty() ? "No tiene categorias" : "Si tiene categorias"));
        //     for (categoria categoria : actividad.getCategorias()) {
        //         log.info("      # categoria: " + categoria.getNombre());
        //     }
        //     log.info("  - " + (actividad.getSalidas().isEmpty() ? "No tiene salidas" : "Si tiene salidas"));

        //     for (salida salida : actividad.getSalidas()) {
        //         log.info("      # salida: " + salida.getNombre());
        //     }
        //     log.info("  - " + (actividad.getPaquetes().isEmpty() ? "No tiene paquetes" : "Si tiene paquetes"));
        //     for (paquete paquete : actividad.getPaquetes()) {
        //         log.info("      # paquete: " + paquete.getNombre());
        //     }
        // }


        // DepartamentoManejador dm = DepartamentoManejador.getinstance();

        // List<departamento> departamentos = dm.getAllDepartamentos();
        // // List<departamento> departamentos = dm.getAllDepartamento();

        // log.info("Cantidad de departamentos: " + departamentos.size());

        // for (departamento dep : departamentos) {
        //     dataDepartamento departamento = dep.toDataType();
        //     log.info("Departamento: " + departamento.getNombre());
        //     // log.info("  - " + (departamento.getActividades().isEmpty() ? "No tiene" : "Si tiene"));
        //     // for (actividad actividad : departamento.getActividades()) {
        //     for (dataActividad actividad : departamento.getActividades()) {
        //         log.info("  - Actividad: " + actividad.getNombre());
        //         // log.info("  # Paquetes?: " + (actividad.getPaquetes().isEmpty() ? "No tiene" : "Si tiene"));
        //     }
        // }

        // CategoriaManejador CM = CategoriaManejador.getInstance();

        // List<categoria> categorias = CM.getAllCategorias();

        // if (!categorias.isEmpty()) {
        //     for (categoria categoria : categorias) {
        //         log.info("Categoria: " + categoria.getNombre());
        //         log.info("  - " + (categoria.getActividades().isEmpty() ? "No tiene actividades." : "Tiene actividades."));
        //         for (actividad actividad : categoria.getActividades()) {
        //             log.info("  - Actividad: "          + actividad.getNombre());
        //             log.info("    # Departamento "      + (actividad.getDepartamento()  == null ? "es null" : "no es null"));
        //             log.info("    # Proveedor "         + (actividad.getProveedor()     == null ? "es null" : "no es null"));
        //             log.info("    # Lista de Salidas "  + (actividad.getSalidas()       == null ? "es null" : "no es null, cantidad de salidas: " + actividad.getSalidas().size()));
        //             log.info("    # Lista de Paquetes " + (actividad.getPaquetes()      == null ? "es null" : "no es null, cantidad de paquetes: " + actividad.getPaquetes().size()));
        //             // for (categoria categoriaAct : actividad.getCategorias()) {
        //             //     log.info("      - " + categoriaAct.getNombre());
        //             // }
        //         }
        //         log.warning("----");
        //     }
        // } else {
        //     log.error("La lista categorias se encuentra empty");
        // }
       
       
//        //List<dataDepartamento> departamentos = IDC.listarDepartamentos();
///*
//        for (dataDepartamento dep : departamentos) {
//            log.info(dep.getNombre());
//            for (dataActividad act : dep.getActividades()) {
//                log.info("  - " + act.getNombre());
//                for (String cat : act.getDtCategorias()) {
//                    log.info("  [" + cat + "]");
//                }
//            }
//        }
//*/
//        // List<String> categorias = new ArrayList<String>();
//        // categorias.add("paseo");
//        // categorias.add("fulbo");
//
//        // try {
//        //     IAC.crearActividad("San José", "homeSolutions", "Prueba1", "Prueba1", 2, 200, "San Jose de mayo", LocalDate.now(), categorias);
//        // } catch (Exception e) {
//        //     log.error(e.toString());
//        // }
//
//        // DepartamentoManejador DM = DepartamentoManejador.getinstance();
//
//        // List<departamento> departamentos = DM.getAllDepartamento();
//
//        // for (departamento dep : departamentos) {
//        //     log.warning("Departamento: " + dep.getNombre());
//        //     for (actividad actividad : dep.getActividades()) {
//        //         log.info("    - Actividad: " + actividad.getNombre());
//        //         for (categoria categoria : actividad.getCategorias()) {
//        //             log.info("        _: " + categoria.getNombre());
//        //         }
//        //     }
//        // }
//
//        // List<dataDepartamento> departamentos = IDC.listarDepartamentos();
//
//        // if (departamentos != null) {
//        //     for (dataDepartamento departamento : departamentos) {
//        //         log.info(departamento.getNombre());
//        //     }
//        // } else {
//        //     log.error("No hay departamentosclear");
//        // }
//
//        // List<dataActividad> actividades = IAC.getAllActividades();
//
//        // for (dataActividad actividad : actividades) {
//        //     log.info(actividad.getNombre());
//        // }
//
//        // List<dataCategoria> categorias = ICC.listarCategorias();
//
//        // for (dataCategoria categoria : categorias) {
//        //     log.info(categoria.getNombre());
//        // }
//
//
//    //    try {
//    //        if (IUC.login("eze", "eze")) { log.warning("True to login");
//    //        } else { log.warning("False to login"); };
//
//    //        if (IUC.login("eze", "hola123")) { log.warning("True to login");
//    //        } else { log.warning("False to login"); };
//
//    //        // if (IUC.login("eze", "eze")) { log.warning("True to login");
//    //        // } else { log.warning("False to login"); };
//    //    } catch (Exception e) {
//    //        log.error(e.toString());
//    //    }
//

        // cargarTuristas();
        // log.warning("########################################################");
        // cargarProveedores();
        // log.warning("########################################################");
        // cargarDepartamentos();
        // log.warning("########################################################");
        // cargarCategorias();
        // log.warning("########################################################");
        // cargarPaquetes();
        // log.warning("########################################################");
        // cargarActividades();
        // log.warning("########################################################");
        // cargarActividadesPaquetes();
        // log.warning("########################################################");
        // cargarSalidas();
        // log.warning("########################################################");
        // cargarInscripcionSalida();
        // log.warning("########################################################");
         
//         
//         try {
//		 	IAC.modificarEstadoActividad("Tour de Arte Urbano", estadoActividad.CONFIRMADA);
//		 } catch (NoExisteExcepcion | ParametrosInvalidosExcepcion | YaExisteExcepcion e) {
//		 	// TODO Auto-generated catch block
//		 	e.printStackTrace();
//		 }
//         
//         
//         
//         List<dataActividad> LDtAct;
//		try {
//			LDtAct = IAC.getActividadesPorCategoria("fulbo");
//			
//	         for(dataActividad DtAct : LDtAct) {
//	          	System.out.println(DtAct.getNombre());
//	          	System.out.println(DtAct.getEstado());        
//	          	}
//		} catch (NoExisteExcepcion e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//         
//
//        
//        // try {
//		// 	IAC.crearActividad("Canelones", "techGadgets", "a", "Experimenta la vida gaucha a caballo en la campiña.", 240, 80, "C", LocalDate.now());
//		// } catch (ParametrosInvalidosExcepcion | UsuarioYaExisteExcepcion | UsuarioNoExisteExcepcion e) {
//		// 	// TODO Auto-generated catch block
//		// 	e.printStackTrace();
//		// }
//      
//        // try {
//		// 	ICoC.crearCompra(LocalDate.now(), 2, 3, LocalDate.now(), "Paquete Aventura", "eze");
//		// } catch (ParametrosInvalidosExcepcion | UsuarioYaExisteExcepcion | UsuarioNoExisteExcepcion e) {
//		// 	// TODO Auto-generated catch block
//		// 	e.printStackTrace();
//		// }
//        
//    //     try {
//	// 		IPC.agregarActividadPaquete("Paquete Aventura", "Tour de Arte Urbano");
//	// 	} catch (NoExisteExcepcion | YaExisteExcepcion e) {
//	// 		// TODO Auto-generated catch block
//	// 		e.printStackTrace();
//	// 	}
//        
//    //    try {
//	// 	List<dataActividad> LAct = IAC.getActividadesDepartamentoNoPaquete("Paquete Histórico", "Canelones");
//		
//	//        for(dataActividad DtAct : LAct) {
//	//     	   System.out.println(DtAct.getNombre());
//	//        }
//	// } catch (UsuarioNoExisteExcepcion e) {
//	// 	// TODO Auto-generated catch block
//	// 	e.printStackTrace();
//	// }
//        
//       
//        
//        
//
//        // try {
//        //     IAC.modificarEstadoActividad("Tour de Vinos en Bodegas", estadoActividad.RECHAZADA);
//        //     IAC.modificarEstadoActividad("Recorrido Cultural por Museos", estadoActividad.RECHAZADA);
//        // } catch (Exception e) {
//        //     log.error(e.getMessage());
//        // }
//        // log.warning("########################################################");
//        // cargarActividades();
//        // cargarActividadesPaquetes();
//
//        // try {
//        //     ICC.crearCategoria("Aventura");            
//        // } catch (Exception e) {
//        //     log.error(e.toString());
//        // }
//        // log.info("Hola");
   }
//    public static void cargarProveedores() {
// 		Fabrica fabrica = Fabrica.getInstance();
//        IUsuarioController IUC = fabrica.getIUsuarioController();

//        try { IUC.registrarTurista("eze", "Ezequiel", "Blandin", "exzeblan@gmail.com", "Uruguay",  LocalDate.of(2000, 05, 20), "hola123");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }		
//        try { IUC.registrarProveedor("homeSolutions", "Mariana", "Perez", "mariana@homesolutions.com", "Soluciones para el hogar y decoración", "www.homesolutions.com", LocalDate.of(2004, 12, 3));
//            } catch (Exception e) { log.error("Error: " + e.toString()); }			
//        try { IUC.registrarProveedor("techGadgets", "Manuel", "Ríos", "manuel@techgadgets.com", "Dispositivos tecnológicos y accesorios", "www.techgadgets.com", LocalDate.of(1996, 5, 15));
//            } catch (Exception e) { log.error("Error: " + e.toString()); }			
//        try { IUC.registrarProveedor("urbanExplore", "Renata", "Martínez", "renata@urbanexplore.com", "Explora la ciudad con nuestras rutas turísticas", "www.urbanexplore.com", LocalDate.of(1992, 2, 24));
//            } catch (Exception e) { log.error("Error: " + e.toString()); }			
//        try { IUC.registrarProveedor("designCraft", "Lucas", "Torres", "lucas@designcraft.com", "Artesanía y diseño en productos únicos", "www.designcraft.com", LocalDate.of(1989, 8, 2));
//            } catch (Exception e) { log.error("Error: " + e.toString()); }			
//        try { IUC.registrarProveedor("musicMania", "María", "Ruiz", "maria@musicmania.com", "Tienda de instrumentos musicales y accesorios", "www.musicmania.com", LocalDate.of(1997, 6, 6));
//            } catch (Exception e) { log.error("Error: " + e.toString()); }			
//        try { IUC.registrarProveedor("petCompanion", "Camila", "García", "camila@petcompanion.com", "Cuidado y servicios para mascotas", "www.petcompanion.com", LocalDate.of(2003, 10, 20));
//            } catch (Exception e) { log.error("Error: " + e.toString()); }			
//        try { IUC.registrarProveedor("ecoLiving", "Mateo", "Morales", "mateo@ecoliving.com", "Productos ecológicos y estilo de vida sostenible", "www.ecoliving.com", LocalDate.of(1998, 4, 14));
//            } catch (Exception e) { log.error("Error: " + e.toString()); }			
//        try { IUC.registrarProveedor("fashionSpot", "Valentina", "Vega", "valentina@fashionspot.com", "Plataforma para compartir tendencias de moda", "www.fashionspot.com", LocalDate.of(1995, 9, 28));
//            } catch (Exception e) { log.error("Error: " + e.toString()); }			
//        try { IUC.registrarProveedor("deliciousBakery", "Javier", "López", "javier@deliciousbakery.com", "Panadería y repostería con productos artesanales", "www.deliciousbakery.com", LocalDate.of(2000, 1, 3));
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//    }

//    public static void cargarDepartamentos() {
// 		Fabrica fabrica = Fabrica.getInstance();
// 		IDepartamentoController IDC = fabrica.getIDepartamentoController();
//        try { IDC.crearDepartamento("Artigas", "Departamento del norte de Uruguay", "www.artigas.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Canelones", "Departamento ubicado en el sur de Uruguay", "www.canelones.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Cerro Largo", "Departamento en el este de Uruguay", "www.cerrolargo.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Colonia", "Departamento costero de Uruguay", "www.colonia.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Durazno", "Departamento en el centro de Uruguay", "www.durazno.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Flores", "Departamento en el suroeste de Uruguay", "www.flores.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Florida", "Departamento en el sur de Uruguay", "www.florida.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Lavalleja", "Departamento en el sureste de Uruguay", "www.lavalleja.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Maldonado", "Departamento en la costa este de Uruguay", "www.maldonado.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Montevideo", "La capital de Uruguay", "www.montevideo.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Paysandú", "Departamento en el noroeste de Uruguay", "www.paysandu.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Río Negro", "Departamento en el oeste de Uruguay", "www.rionegro.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Rivera", "Departamento en el norte de Uruguay", "www.rivera.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Rocha", "Departamento en el este de Uruguay", "www.rocha.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Salto", "Departamento en el noroeste de Uruguay", "www.salto.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("San José", "Departamento en el suroeste de Uruguay", "www.sanjose.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Soriano", "Departamento en el suroeste de Uruguay", "www.soriano.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Tacuarembó", "Departamento en el norte de Uruguay", "www.tacuarembo.com.uy");
// 		    } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IDC.crearDepartamento("Treinta y Tres", "Departamento en el este de Uruguay", "www.treintaytres.com.uy");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//    }

//    public static void cargarCategorias() {
//        Fabrica fabrica = Fabrica.getInstance();
//        ICategoriaController ICC = fabrica.getICategoriaController();

//        try { ICC.crearCategoria("fulbo");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }

//        try { ICC.crearCategoria("paseo");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }

//        try { ICC.crearCategoria("playa");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }

//        try { ICC.crearCategoria("fulbo");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }

//        try { ICC.crearCategoria("fulbo");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }

//        try { ICC.crearCategoria("fulbo");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//    }

//    public static void cargarActividades() {
// 		Fabrica fabrica = Fabrica.getInstance();
// 		IActividadController IAC = fabrica.getIActividadController();

//        List<String> categorias1 = new ArrayList<String>();
//        categorias1.add("fulbo");

//        try { IAC.crearActividad("Canelones", "homeSolutions", "Tour de Vinos en Bodegas", "Recorrido por las mejores bodegas de la región.", 120, 50, "Canelones", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }            
//        try { IAC.crearActividad("Montevideo", "homeSolutions", "Recorrido Cultural por Museos", "Explora la historia y el arte de la ciudad.", 90, 30, "Montevideo", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IAC.crearActividad("Maldonado", "homeSolutions", "Playa y Surf en Punta del Este", "Día de diversión bajo el sol y clases de surf.", 180, 70, "Punta del Este", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IAC.crearActividad("Rocha", "techGadgets", "Senderismo en el Parque Nacional", "Explora la belleza natural del parque.", 150, 60, "La Paloma", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IAC.crearActividad("Colonia", "techGadgets", "Paseo en Kayak por el Río", "Navega por el río y disfruta de la naturaleza.", 120, 55, "Colonia del Sacramento", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IAC.crearActividad("Cerro Largo", "techGadgets", "Turismo Rural en Estancia", "Vive la experiencia gaucha en una estancia local.", 240, 80, "Melo", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IAC.crearActividad("Durazno", "urbanExplore", "Observación Nocturna de Estrellas", "Admira el cielo estrellado en la noche.", 90, 40, "Durazno", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IAC.crearActividad("Lavalleja", "urbanExplore", "Ruta de Ciclismo de Montaña", "Recorrido en bicicleta por las colinas.", 180, 75, "Minas", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IAC.crearActividad("San José", "urbanExplore", "Avistamiento de Aves en el Humedal", "Observa la vida silvestre en el humedal.", 120, 50, "San José", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IAC.crearActividad("Rivera", "urbanExplore", "Pesca Deportiva en el Río", "Día de pesca en el río con guías locales.", 240, 90, "Rivera", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IAC.crearActividad("Artigas", "urbanExplore", "Tour de Arte Urbano", "Explora las obras de arte en las calles de la ciudad.", 90, 35, "Artigas", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IAC.crearActividad("Treinta y Tres", "urbanExplore", "Caminata por la Quebrada", "Recorrido escénico por la quebrada local.", 120, 45, "Treinta y Tres", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IAC.crearActividad("Río Negro", "urbanExplore", "Visita a la Fortaleza de Santa Teresa", "Explora la histórica fortaleza junto al mar.", 150, 60, "Fray Bentos", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IAC.crearActividad("Salto", "urbanExplore", "Relax en las Termas Naturales", "Disfruta de las aguas termales y sus beneficios.", 180, 70, "Salto", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IAC.crearActividad("Tacuarembó", "urbanExplore", "Ruta de Gauchos a Caballo", "Experimenta la vida gaucha a caballo en la campiña.", 240, 80, "Tacuarembó", LocalDate.now(), categorias1);
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//    }

//    public static void cargarPaquetes() {
// 		Fabrica fabrica = Fabrica.getInstance();
//        IPaqueteController IPC = fabrica.getIPaqueteController();
//        try { IPC.crearPaquete("Paquete Aventura", "Disfruta de emocionantes aventuras al aire libre", 30, 10, LocalDate.now());     // actividad1, actividad2, actividad3
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IPC.crearPaquete("Paquete Cultural", "Explora la cultura y la historia de Uruguay", 45, 15, LocalDate.now());          // actividad4, actividad5, actividad6
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IPC.crearPaquete("Paquete Relax", "Relájate y rejuvenece en entornos naturales", 30, 10, LocalDate.now());             // actividad7, actividad8, actividad9
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IPC.crearPaquete("Paquete Artes y Naturaleza", "Combina arte urbano y belleza natural", 60, 20, LocalDate.now());      // actividad10, actividad11, actividad12
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IPC.crearPaquete("Paquete Histórico", "Explora la historia y el patrimonio de Uruguay", 45, 15, LocalDate.now());      // actividad13, actividad14, actividad15
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//    }

//    public static void cargarActividadesPaquetes() {
//        Fabrica fabrica = Fabrica.getInstance();
//        IPaqueteController IPC = fabrica.getIPaqueteController();

//        // Tour de Vinos en Bodegas
//        // Recorrido Cultural por Museos
//        // Playa y Surf en Punta del Este
//        try { IPC.agregarActividadPaquete("Paquete Aventura", "Tour de Vinos en Bodegas");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IPC.agregarActividadPaquete("Paquete Aventura", "Recorrido Cultural por Museos");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IPC.agregarActividadPaquete("Paquete Aventura", "Playa y Surf en Punta del Este");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }

//        // Senderismo en el Parque Nacional
//        // Paseo en Kayak por el Río
//        // Turismo Rural en Estancia
//        try { IPC.agregarActividadPaquete("Paquete Cultural", "Senderismo en el Parque Nacional");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IPC.agregarActividadPaquete("Paquete Cultural", "Paseo en Kayak por el Río");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IPC.agregarActividadPaquete("Paquete Cultural", "Turismo Rural en Estancia");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }

//        // Observación Nocturna de Estrellas
//        // Ruta de Ciclismo de Montaña
//        // Avistamiento de Aves en el Humedal
//        try { IPC.agregarActividadPaquete("Paquete Relax", "Observación Nocturna de Estrellas");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IPC.agregarActividadPaquete("Paquete Relax", "Ruta de Ciclismo de Montaña");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IPC.agregarActividadPaquete("Paquete Relax", "Avistamiento de Aves en el Humedal");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }

//        // Pesca Deportiva en el Río
//        // Tour de Arte Urbano
//        // Caminata por la Quebrada
//        try { IPC.agregarActividadPaquete("Paquete Artes y Naturaleza", "Pesca Deportiva en el Río");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IPC.agregarActividadPaquete("Paquete Artes y Naturaleza", "Tour de Arte Urbano");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IPC.agregarActividadPaquete("Paquete Artes y Naturaleza", "Caminata por la Quebrada");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }

//        // Visita a la Fortaleza de Santa Teresa
//        // Relax en las Termas Naturales
//        // Ruta de Gauchos a Caballo
//        try { IPC.agregarActividadPaquete("Paquete Histórico", "Visita a la Fortaleza de Santa Teresa");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IPC.agregarActividadPaquete("Paquete Histórico", "Relax en las Termas Naturales");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//        try { IPC.agregarActividadPaquete("Paquete Histórico", "Ruta de Gauchos a Caballo");
//            } catch (Exception e) { log.error("Error: " + e.toString()); }
//    }

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

    public static void cargarTuristas() {
	    Fabrica fabrica = Fabrica.getInstance();
        IUsuarioController IUC = fabrica.getIUsuarioController();

        // try { IUC.registrarTurista("eze_blandin", "Ezequiel", "Blandin", "exzeblan@gmail.com", "Uruguayo", LocalDate.of(2000, 05, 12));
        // } catch (Exception e) { log.error("Error: " + e.toString()); }
        
        try { IUC.registrarTurista("lucia_22", "Lucía", "Gómez", "lucia.gomez@example.com", "Española", LocalDate.of(1990, 5, 22));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IUC.registrarTurista("joseperez", "José", "Perez", "jose.perez@example.com", "Mexicano", LocalDate.of(1985, 3, 12));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IUC.registrarTurista("annasmith", "Anna", "Smith", "anna.smith@example.com", "Estadounidense", LocalDate.of(1988, 7, 30));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IUC.registrarTurista("carlostorres", "Carlos", "Torres", "carlos.torres@example.com", "Colombiano", LocalDate.of(1993, 9, 15));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IUC.registrarTurista("laurawong", "Laura", "Wong", "laura.wong@example.com", "Canadiense", LocalDate.of(1986, 11, 5));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IUC.registrarTurista("mariomartinez", "Mario", "Martínez", "mario.martinez@example.com", "Chileno", LocalDate.of(1991, 2, 10));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IUC.registrarTurista("emilybrown", "Emily", "Brown", "emily.brown@example.com", "Australiana", LocalDate.of(1987, 4, 18));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IUC.registrarTurista("andreagarcia", "Andrea", "García", "andrea.garcia@example.com", "Costarricense", LocalDate.of(1994, 6, 25));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IUC.registrarTurista("peterjohnson", "Peter", "Johnson", "peter.johnson@example.com", "Sudafricano", LocalDate.of(1989, 8, 12));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IUC.registrarTurista("sofiareyes", "Sofía", "Reyes", "sofia.reyes@example.com", "Dominicana", LocalDate.of(1995, 10, 22));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IUC.registrarTurista("khaledali", "Khaled", "Ali", "khaled.ali@example.com", "Egipcio", LocalDate.of(1992, 12, 8));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IUC.registrarTurista("elenafernandez", "Elena", "Fernández", "elena.fernandez@example.com", "Argentina", LocalDate.of(1984, 1, 3));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IUC.registrarTurista("henrikhansen", "Henrik", "Hansen", "henrik.hansen@example.com", "Danés", LocalDate.of(1990, 7, 28));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IUC.registrarTurista("isabellasmith", "Isabella", "Smith", "isabella.smith@example.com", "Británica", LocalDate.of(1983, 4, 15));
            } catch (Exception e) { log.error("Error: " + e.toString()); }
        try { IUC.registrarTurista("michaelbrown", "Michael", "Brown", "michael.brown@example.com", "Irlandés", LocalDate.of(1988, 6, 20));
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

       try { ICC.crearCategoria("aventura");
           } catch (Exception e) { log.error("Error: " + e.toString()); }

       try { ICC.crearCategoria("senderismo");
           } catch (Exception e) { log.error("Error: " + e.toString()); }

       try { ICC.crearCategoria("viaje al exterior");
           } catch (Exception e) { log.error("Error: " + e.toString()); }
   }

   public static void cargarActividades() {
		Fabrica fabrica = Fabrica.getInstance();
		IActividadController IAC = fabrica.getIActividadController();

       List<String> categorias1 = new ArrayList<String>();
       categorias1.add("fulbo");

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

   public static void cargarSalidas() {
        Fabrica fabrica = Fabrica.getInstance();

        ISalidaController ISC = fabrica.getISalidaController();

        // Tour de Vinos en Bodegas
        try { ISC.crearSalida("Tour de Vinos en Bodegas - Salida 1", 30, LocalDate.now(), LocalDate.now().plusDays(15), "Bodega Vinícola XYZ", "Tour de Vinos en Bodegas");
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        try { ISC.crearSalida("Tour de Vinos en Bodegas - Salida 2", 20, LocalDate.now(), LocalDate.now().plusDays(20), "Otra Bodega Vinícola", "Tour de Vinos en Bodegas");
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        try { ISC.crearSalida("Tour de Vinos en Bodegas - Salida 3", 25, LocalDate.now(), LocalDate.now().plusDays(25), "Viñedos del Este", "Tour de Vinos en Bodegas");
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Recorrido Cultural por Museos
        try { ISC.crearSalida("Recorrido Cultural por Museos - Salida 1", 15, LocalDate.now(), LocalDate.now().plusDays(10), "Museo Histórico ABC", "Recorrido Cultural por Museos");
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        try { ISC.crearSalida("Recorrido Cultural por Museos - Salida 2", 25, LocalDate.now(), LocalDate.now().plusDays(18), "Museo de Arte XYZ", "Recorrido Cultural por Museos");
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Playa y Surf en Punta del Este
        try { ISC.crearSalida("Playa y Surf en Punta del Este - Salida 1", 9, LocalDate.now(), LocalDate.now().plusDays(20), "Playa Principal, Punta del Este", "Playa y Surf en Punta del Este");
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Senderismo en el Parque Nacional
        try { ISC.crearSalida("Senderismo en el Parque Nacional - Salida 1", 18, LocalDate.now(), LocalDate.now().plusDays(12), "Centro de Visitantes del Parque Nacional", "Senderismo en el Parque Nacional");
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Paseo en Kayak por el Río
        try { ISC.crearSalida("Paseo en Kayak por el Río - Salida 1", 9, LocalDate.now(), LocalDate.now().plusDays(18), "Embarcadero del Río XYZ", "Paseo en Kayak por el Río");
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Turismo Rural en Estancia
        try { ISC.crearSalida("Turismo Rural en Estancia - Salida 1", 18, LocalDate.now(), LocalDate.now().plusDays(22), "Estancia Campo Verde", "Turismo Rural en Estancia");
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Observación Nocturna de Estrellas
        try { ISC.crearSalida("Observación Nocturna de Estrellas - Salida 1", 12, LocalDate.now(), LocalDate.now().plusDays(17), "Observatorio Estelar XYZ", "Observación Nocturna de Estrellas");
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Ruta de Ciclismo de Montaña
        try { ISC.crearSalida("Ruta de Ciclismo de Montaña - Salida 1", 9, LocalDate.now(), LocalDate.now().plusDays(12), "Punto de Inicio de la Ruta", "Ruta de Ciclismo de Montaña");
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Avistamiento de Aves en el Humedal
        try { ISC.crearSalida("Avistamiento de Aves en el Humedal - Salida 1", 27, LocalDate.now(), LocalDate.now().plusDays(14), "Humedal Natural ABC", "Avistamiento de Aves en el Humedal");
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Pesca Deportiva en el Río
        try { ISC.crearSalida("Pesca Deportiva en el Río - Salida 1", 9, LocalDate.now(), LocalDate.now().plusDays(19), "Embarcadero de Pesca XYZ", "Pesca Deportiva en el Río");
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Tour de Arte Urbano
        try { ISC.crearSalida("Tour de Arte Urbano - Salida 1", 30, LocalDate.now(), LocalDate.now().plusDays(21), "Punto de Encuentro para el Tour", "Tour de Arte Urbano");
        } catch (Exception e) { log.error("Error: " + e.toString()); }

   }

    public static void cargarEstadoActividad() {
        Fabrica fabrica = Fabrica.getInstance();

        IActividadController IAC = fabrica.getIActividadController();

        try { IAC.modificarEstadoActividad("Tour de Vinos en Bodegas", estadoActividad.CONFIRMADA);
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        try { IAC.modificarEstadoActividad("Recorrido Cultural por Museos", estadoActividad.CONFIRMADA);
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        try { IAC.modificarEstadoActividad("Playa y Surf en Punta del Este", estadoActividad.CONFIRMADA);
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        try { IAC.modificarEstadoActividad("Senderismo en el Parque Nacional", estadoActividad.CONFIRMADA);
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        try { IAC.modificarEstadoActividad("Paseo en Kayak por el Río", estadoActividad.CONFIRMADA);
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        try { IAC.modificarEstadoActividad("Turismo Rural en Estancia", estadoActividad.CONFIRMADA);
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        try { IAC.modificarEstadoActividad("Observación Nocturna de Estrellas", estadoActividad.CONFIRMADA);
        } catch (Exception e) { log.error("Error: " + e.toString()); }

        // Tour de Vinos en Bodegas
        // Recorrido Cultural por Museos
        // Playa y Surf en Punta del Este
        // Senderismo en el Parque Nacional
        // Paseo en Kayak por el Río
        // Turismo Rural en Estancia
        // Observación Nocturna de Estrellas
        // Ruta de Ciclismo de Montaña
        // Avistamiento de Aves en el Humedal
        // Pesca Deportiva en el Río
        // Tour de Arte Urbano
        // Caminata por la Quebrada
        // Visita a la Fortaleza de Santa Teresa
        // Relax en las Termas Naturales
        // Ruta de Gauchos a Caballo
    }

    public static void cargarCompraPaquete() {
        // Fabrica fabrica = Fabrica.getInstance();

        // ICompraController ICC = fabrica.getICompraController();

        // ICC.crearCompra(LocalDate.now(), , 0, null, null, null);
    }

    public static void cargarInscripcionSalida() {
        Fabrica fabrica = Fabrica.getInstance();
        IInscripcionController IIC = fabrica.getIInscripcionController();

        try { IIC.crearInscripcion(LocalDate.now(), 1, "joseperez", "Tour de Vinos en Bodegas - Salida 1", "Tour de Vinos en Bodegas");
            } catch (Exception e) { log.error(e.getMessage()); }

        try { IIC.crearInscripcion(LocalDate.now(), 1, "carlostorres", "Recorrido Cultural por Museos - Salida 1", "Recorrido Cultural por Museos");
            } catch (Exception e) { log.error(e.getMessage()); }
    }
}
