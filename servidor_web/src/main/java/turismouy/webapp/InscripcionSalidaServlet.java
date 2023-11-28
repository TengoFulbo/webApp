package turismouy.webapp;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import turismouy.svcentral.middlewares.DataActividad;
import turismouy.svcentral.middlewares.DataSalida;
import turismouy.svcentral.middlewares.DataUsuario;
import turismouy.svcentral.middlewares.NoExisteExcepcion_Exception;
import turismouy.svcentral.middlewares.ParametrosInvalidosExcepcion_Exception;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;
import turismouy.svcentral.middlewares.YaExisteExcepcion;
import turismouy.svcentral.middlewares.YaExisteExcepcion_Exception;
//import turismouy.svcentral.interfaces.IUsuarioController;
//import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
//import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
//import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
//import turismouy.svcentral.interfaces.IInscripcionController;
//import turismouy.svcentral.interfaces.ISalidaController;
//import turismouy.svcentral.Fabrica;
//import turismouy.svcentral.datatypes.dataUsuario;
//import turismouy.svcentral.entidades.actividad;
//import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
//import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
//import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
//import turismouy.svcentral.datatypes.dataSalida;
//import turismouy.svcentral.datatypes.dataActividad;
//import turismouy.svcentral.utilidades.log;
import turismouy.webapp.utils.log;

@WebServlet("/insSalida")
public class InscripcionSalidaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("pageTitle", "Mis Salidas - TurismoUY");


        // Muestra el formulario de inicio de sesión
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/misSalidas.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        log.warning("      /// Entro al post ///");
        HttpSession session = request.getSession();

        DataUsuario usuario = (DataUsuario) session.getAttribute("dataUsuario");
        String datosInscripcion = (String) request.getSession().getAttribute("datosInscripcion");

        
        int cantidadIns = Integer.parseInt(request.getParameter("cantidadIns"));
        String formaPago = request.getParameter("formaPago");
        String nSalida = request.getParameter("nombreSalida");

        if (formaPago != null) {
            if (formaPago.equals("general")) {
                log.info("General 🍆🍆");
            } else if (formaPago.equals("paquete")) {
                log.info("Paquete 😩😩");
            } 
        }else{
            log.warning("   [Invalid Radio]");
        }

        log.info("--------------------------------------------------------------");
        System.out.println("Cantidad de personas a inscribirse: " + cantidadIns);
        System.out.println("Forma de Pago seleccionada: " + formaPago);
        System.out.println("Usuario nickname: " + usuario.getNickname());
        System.out.println("Nombre de la salida: " + nSalida);
        log.info("--------------------------------------------------------------");
        // public abstract void crearInscripcion(LocalDate fecha, int cant, String nombreTursita, String nombreSalida, String nombreAct
        Publicador API = new PublicadorService().getPublicadorPort();
        LocalDate fecha = LocalDate.now();
        
        DataSalida salida = API.salidaMostrarDatosSalida(nSalida);
        List<DataActividad> listActividad = salida.getActividades();
        
        String actividadNombre = "";
        
        if(listActividad != null) {
        	for(DataActividad act : listActividad) {
        		actividadNombre = act.getNombre();
        	}
        }

			try {
				API.inscripcionCrearInscripcion(fecha.toString(), cantidadIns, usuario.getNickname(), nSalida, actividadNombre);
			} catch (NoExisteExcepcion_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParametrosInvalidosExcepcion_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (YaExisteExcepcion_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



        // try {
        //     void crearSalida(String nombre, int capacidad, LocalDate fechaAlta, LocalDate fechaSalida, String lugarSalida, String nombreActividad)
        //     ISC.crearSalida(nombreSalida, capacidad, LocalDate.now(), fechaSalida, lugarSalida, nombreActividad);
        // }catch (Exception e){
        //     log.error("[AltaSalidaServlet] Error al crear salida");
        // }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/misSalidas.jsp");
        dispatcher.forward(request, response);
    }
}
